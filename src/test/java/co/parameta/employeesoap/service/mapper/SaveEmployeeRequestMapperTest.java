package co.parameta.employeesoap.service.mapper;

import co.parameta.employeesoap.employee.EmployeeRequest;
import co.parameta.employeesoap.model.EmployeeEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class SaveEmployeeRequestMapperTest {

  private final SaveEmployeeRequestMapper mapper =
      Mappers.getMapper(SaveEmployeeRequestMapper.class);

  @Test
  void testTransformRequestToModel() throws DatatypeConfigurationException {
    EmployeeRequest employeeRequest = new EmployeeRequest();
    employeeRequest.setNames("TEST");
    employeeRequest.setSurname("SURNAME");
    employeeRequest.setDocumentType("CC");
    employeeRequest.setDocumentNumber("123456789");
    employeeRequest.setPosition("ECONOMIST");
    employeeRequest.setSalary(BigDecimal.TEN);

    XMLGregorianCalendar xCal = DatatypeFactory.newInstance().newXMLGregorianCalendar();
    xCal.setYear(2021);
    xCal.setMonth(10);
    xCal.setDay(1);
    employeeRequest.setDateOfBirth(xCal);
    employeeRequest.setDateOfJoiningCompany(xCal);

    final EmployeeEntity entity = mapper.transformRequestToModel(employeeRequest);

    assertNotNull(entity);
    assertEquals("123456789", entity.getDocumentNumber());
    assertEquals("CC", entity.getDocumentType());
    assertEquals("TEST", entity.getNames());
    assertEquals("SURNAME", entity.getSurname());
    assertEquals(BigDecimal.TEN, entity.getSalary());
    assertEquals("ECONOMIST", entity.getPosition());
    assertEquals(LocalDate.of(2021, 10,  1), entity.getDateOfBirth());
    assertEquals(LocalDate.of(2021, 10,  1),entity.getDateOfJoiningCompany());
  }
}
