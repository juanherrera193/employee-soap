package co.parameta.employeesoap.service;

import co.parameta.employeesoap.employee.GetEmployeeByDocumentRequest;
import co.parameta.employeesoap.employee.GetEmployeeByDocumentResponse;
import co.parameta.employeesoap.employee.SaveEmployeeRequest;
import co.parameta.employeesoap.employee.SaveEmployeeResponse;

public interface EmployeeService {

  GetEmployeeByDocumentResponse getEmployeeByDocument(
      GetEmployeeByDocumentRequest request);

  SaveEmployeeResponse saveEmployee(SaveEmployeeRequest request);
}
