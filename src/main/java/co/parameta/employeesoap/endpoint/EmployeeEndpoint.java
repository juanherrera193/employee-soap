package co.parameta.employeesoap.endpoint;

import co.parameta.employeesoap.employee.GetEmployeeByDocumentRequest;
import co.parameta.employeesoap.employee.GetEmployeeByDocumentResponse;
import co.parameta.employeesoap.employee.SaveEmployeeRequest;
import co.parameta.employeesoap.employee.SaveEmployeeResponse;
import co.parameta.employeesoap.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class EmployeeEndpoint {

  public static final String NAMESPACE_URI = "http://www.parameta.co/xml/employee";
  private static final String GET_EMPLOYEE_BY_DOCUMENT_LOCAL_PART = "getEmployeeByDocumentRequest";
  private static final String SAVE_EMPLOYEE_LOCAL_PART = "saveEmployeeRequest";

  private final EmployeeService employeeService;

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = GET_EMPLOYEE_BY_DOCUMENT_LOCAL_PART)
  @ResponsePayload
  public GetEmployeeByDocumentResponse getEmployeeByDocument(
      @RequestPayload GetEmployeeByDocumentRequest request) {
    return employeeService.getEmployeeByDocument(request);
  }

  @PayloadRoot(namespace = NAMESPACE_URI, localPart = SAVE_EMPLOYEE_LOCAL_PART)
  @ResponsePayload
  public SaveEmployeeResponse saveEmployee(@RequestPayload SaveEmployeeRequest request) {
    return employeeService.saveEmployee(request);
  }
}
