package co.parameta.employeesoap.service.mapper;

import co.parameta.employeesoap.employee.EmployeeRequest;
import co.parameta.employeesoap.model.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaveEmployeeRequestMapper {

  @Mapping(target = "id", ignore = true)
  EmployeeEntity transformRequestToModel(EmployeeRequest request);
}
