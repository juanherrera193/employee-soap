package co.parameta.employeesoap.service;

import co.parameta.employeesoap.employee.*;
import co.parameta.employeesoap.model.EmployeeEntity;
import co.parameta.employeesoap.repository.EmployeeRepository;
import co.parameta.employeesoap.service.mapper.GetEmployeeMapper;
import co.parameta.employeesoap.service.mapper.SaveEmployeeRequestMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

  private static final String NOT_EMPTY_FIELDS_ERROR_MSG = "Query fields cannot be empty";
  private static final String EMPLOYEE_NOT_FOUND_ERROR_MSG = "The employee does not exist";

  private final EmployeeRepository employeeRepository;
  private final GetEmployeeMapper getEmployeeMapper;
  private final SaveEmployeeRequestMapper saveEmployeeRequestMapper;

  @Override
  public GetEmployeeByDocumentResponse getEmployeeByDocument(
      final GetEmployeeByDocumentRequest request) {
    GetEmployeeByDocumentResponse response = new GetEmployeeByDocumentResponse();
    if (isValidGetEmployeeRequest(request)) {
      final EmployeeEntity entity =
          employeeRepository.getByDocumentTypeAndDocumentNumber(
              request.getDocumentType(), request.getDocumentNumber());
      if (Objects.nonNull(entity)) {
        response.setEmployee(getEmployeeMapper.transformModelToResponse(entity));
        response.setServiceStatus(buildServiceStatus(HttpStatus.OK, null));
      } else {
        response.setServiceStatus(
            buildServiceStatus(HttpStatus.NOT_FOUND, EMPLOYEE_NOT_FOUND_ERROR_MSG));
      }
    } else {
      response.setServiceStatus(
          buildServiceStatus(HttpStatus.BAD_REQUEST, NOT_EMPTY_FIELDS_ERROR_MSG));
    }
    return response;
  }

  @Override
  public SaveEmployeeResponse saveEmployee(SaveEmployeeRequest request) {
    SaveEmployeeResponse response = new SaveEmployeeResponse();
    if (Objects.nonNull(request) && isValidEmployeeRequest(request.getEmployee())) {
      employeeRepository.save(
          saveEmployeeRequestMapper.transformRequestToModel(request.getEmployee()));
      response.setServiceStatus(buildServiceStatus(HttpStatus.CREATED, null));
    } else {
      response.setServiceStatus(
          buildServiceStatus(HttpStatus.BAD_REQUEST, NOT_EMPTY_FIELDS_ERROR_MSG));
    }

    return response;
  }

  private boolean isValidGetEmployeeRequest(final GetEmployeeByDocumentRequest request) {
    return Objects.nonNull(request)
        && StringUtils.isNotEmpty(request.getDocumentNumber())
        && StringUtils.isNotEmpty(request.getDocumentType());
  }

  private boolean isValidEmployeeRequest(EmployeeRequest request) {
    if (Objects.nonNull(request)) {
      return Stream.of(
                  request.getDateOfBirth(),
                  request.getDateOfJoiningCompany(),
                  request.getSalary())
              .allMatch(Objects::nonNull)
          && Stream.of(
                  request.getDocumentNumber(),
                  request.getDocumentType(),
                  request.getNames(),
                  request.getSurname(),
                  request.getPosition())
              .allMatch(StringUtils::isNotEmpty);
    }
    return false;
  }

  private ServiceStatus buildServiceStatus(HttpStatus status, final String message) {
    ServiceStatus serviceStatus = new ServiceStatus();
    serviceStatus.setStatusCode(status.getReasonPhrase());
    if (Objects.nonNull(message)) {
      serviceStatus.setMessage(message);
    }

    return serviceStatus;
  }
}
