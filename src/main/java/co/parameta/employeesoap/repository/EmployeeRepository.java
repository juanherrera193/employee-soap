package co.parameta.employeesoap.repository;

import co.parameta.employeesoap.model.EmployeeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer> {

  EmployeeEntity getByDocumentTypeAndDocumentNumber(String documentType, String documentNumber);
}
