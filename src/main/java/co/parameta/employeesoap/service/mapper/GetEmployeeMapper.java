package co.parameta.employeesoap.service.mapper;

import co.parameta.employeesoap.employee.EmployeeResponse;
import co.parameta.employeesoap.model.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GetEmployeeMapper {

  EmployeeResponse transformModelToResponse(EmployeeEntity entity);
}
