package co.parameta.employeesoap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "employee")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Integer id;

  @Column(name = "names", nullable = false)
  private String names;

  @Column(name = "surname", nullable = false)
  private String surname;

  @Column(name = "document_type", nullable = false)
  private String documentType;

  @Column(name = "document_number", nullable = false)
  private String documentNumber;

  @Column(name = "date_of_birth", nullable = false)
  private LocalDate dateOfBirth;

  @Column(name = "date_of_joining_company", nullable = false)
  private LocalDate dateOfJoiningCompany;

  @Column(name = "position", nullable = false)
  private String position;

  @Column(name = "salary", nullable = false)
  private BigDecimal salary;
}
