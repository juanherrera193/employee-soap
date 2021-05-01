package co.parameta.employeesoap.service;

import co.parameta.employeesoap.employee.*;
import co.parameta.employeesoap.model.EmployeeEntity;
import co.parameta.employeesoap.repository.EmployeeRepository;
import co.parameta.employeesoap.service.mapper.GetEmployeeMapper;
import co.parameta.employeesoap.service.mapper.SaveEmployeeRequestMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

  @InjectMocks
  private EmployeeServiceImpl employeeService;

  @Mock
  private EmployeeRepository employeeRepository;

  @Mock
  private GetEmployeeMapper getEmployeeMapper;

  @Mock
  private SaveEmployeeRequestMapper saveEmployeeRequestMapper;

  @Test
  void testGetEmployeeByDocumentOk() {

    when(employeeRepository.getByDocumentTypeAndDocumentNumber(
            ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
        .thenReturn(Mockito.mock(EmployeeEntity.class));

    when(getEmployeeMapper.transformModelToResponse(ArgumentMatchers.any(EmployeeEntity.class)))
        .thenReturn(Mockito.mock(EmployeeResponse.class));

    GetEmployeeByDocumentRequest request = new GetEmployeeByDocumentRequest();
    request.setDocumentType("CC");
    request.setDocumentNumber("123456789");
    final GetEmployeeByDocumentResponse response = employeeService.getEmployeeByDocument(request);

    assertNotNull(response);
    assertNotNull(response.getServiceStatus());
    assertEquals(HttpStatus.OK.getReasonPhrase(), response.getServiceStatus().getStatusCode());

    verify(employeeRepository)
            .getByDocumentTypeAndDocumentNumber(ArgumentMatchers.anyString(), ArgumentMatchers.anyString());
    verify(getEmployeeMapper)
            .transformModelToResponse(ArgumentMatchers.any(EmployeeEntity.class));

    verifyNoMoreInteractions(employeeRepository, getEmployeeMapper);
  }

  @Test
  void testGetEmployeeByDocumentBadRequest() {

    final GetEmployeeByDocumentRequest request = new GetEmployeeByDocumentRequest();
    final GetEmployeeByDocumentResponse response = employeeService.getEmployeeByDocument(request);

    assertNotNull(response);
    assertNotNull(response.getServiceStatus());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getServiceStatus().getStatusCode());
    assertEquals("Query fields cannot be empty", response.getServiceStatus().getMessage());

    verifyNoInteractions(employeeRepository, getEmployeeMapper);
  }

  @Test
  void testGetEmployeeByDocumentNotFound() {

    when(employeeRepository.getByDocumentTypeAndDocumentNumber(
            ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
            .thenReturn(null);

    GetEmployeeByDocumentRequest request = new GetEmployeeByDocumentRequest();
    request.setDocumentType("CC");
    request.setDocumentNumber("123456789");
    final GetEmployeeByDocumentResponse response = employeeService.getEmployeeByDocument(request);

    assertNotNull(response);
    assertNotNull(response.getServiceStatus());
    assertEquals(HttpStatus.NOT_FOUND.getReasonPhrase(), response.getServiceStatus().getStatusCode());
    assertEquals("The employee does not exist", response.getServiceStatus().getMessage());

    verifyNoMoreInteractions(employeeRepository);
    verifyNoInteractions(getEmployeeMapper);
  }

  @Test
  void testSaveEmployeeOk() {
    when(saveEmployeeRequestMapper.transformRequestToModel(ArgumentMatchers.any(EmployeeRequest.class)))
            .thenReturn(Mockito.mock(EmployeeEntity.class));

    when(employeeRepository.save(
            ArgumentMatchers.any(EmployeeEntity.class))).thenReturn(Mockito.mock(EmployeeEntity.class));

    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setNames("TEST");
    employeeRequest.setSurname("SURNAME");
    employeeRequest.setDocumentType("CC");
    employeeRequest.setDocumentNumber("123456789");
    employeeRequest.setPosition("ECONOMIST");
    employeeRequest.setSalary(BigDecimal.TEN);
    employeeRequest.setDateOfBirth(Mockito.mock(XMLGregorianCalendar.class));
    employeeRequest.setDateOfJoiningCompany(Mockito.mock(XMLGregorianCalendar.class));

    SaveEmployeeRequest request = new SaveEmployeeRequest();
    request.setEmployee(employeeRequest);
    final SaveEmployeeResponse response = employeeService.saveEmployee(request);

    assertNotNull(response);
    assertNotNull(response.getServiceStatus());
    assertEquals(HttpStatus.CREATED.getReasonPhrase(), response.getServiceStatus().getStatusCode());

    verify(employeeRepository)
            .save(ArgumentMatchers.any(EmployeeEntity.class));
    verify(saveEmployeeRequestMapper)
            .transformRequestToModel(ArgumentMatchers.any(EmployeeRequest.class));

    verifyNoMoreInteractions(employeeRepository, getEmployeeMapper);
  }

  @Test
  void testSaveEmployeeBadRequest() {
    final SaveEmployeeRequest request = new SaveEmployeeRequest();
    final SaveEmployeeResponse response = employeeService.saveEmployee(request);

    assertNotNull(response);
    assertNotNull(response.getServiceStatus());
    assertEquals(HttpStatus.BAD_REQUEST.getReasonPhrase(), response.getServiceStatus().getStatusCode());

    verifyNoInteractions(employeeRepository, getEmployeeMapper);
  }
}
