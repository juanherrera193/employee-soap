package co.parameta.employeesoap.endpoint;

import co.parameta.employeesoap.employee.GetEmployeeByDocumentRequest;
import co.parameta.employeesoap.employee.GetEmployeeByDocumentResponse;
import co.parameta.employeesoap.employee.SaveEmployeeRequest;
import co.parameta.employeesoap.employee.SaveEmployeeResponse;
import co.parameta.employeesoap.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeEndpointTest {

  @InjectMocks
  private EmployeeEndpoint employeeEndpoint;

  @Mock
  private EmployeeService employeeService;

  @Test
  void testGetEmployeeByDocument() {
    when(employeeService.getEmployeeByDocument(
            ArgumentMatchers.any(GetEmployeeByDocumentRequest.class)))
        .thenReturn(Mockito.mock(GetEmployeeByDocumentResponse.class));

    final GetEmployeeByDocumentResponse response =
        employeeEndpoint.getEmployeeByDocument(Mockito.mock(GetEmployeeByDocumentRequest.class));

    assertNotNull(response);

    verify(employeeService)
        .getEmployeeByDocument(ArgumentMatchers.any(GetEmployeeByDocumentRequest.class));
    verifyNoMoreInteractions(employeeService);
  }

  @Test
  void testSaveEmployee() {
    when(employeeService.saveEmployee(ArgumentMatchers.any(SaveEmployeeRequest.class)))
        .thenReturn(Mockito.mock(SaveEmployeeResponse.class));

    final SaveEmployeeResponse response =
        employeeEndpoint.saveEmployee(Mockito.mock(SaveEmployeeRequest.class));

    assertNotNull(response);

    verify(employeeService).saveEmployee(ArgumentMatchers.any(SaveEmployeeRequest.class));
    verifyNoMoreInteractions(employeeService);
  }
}
