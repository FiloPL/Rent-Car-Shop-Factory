package FiloPL.carrentshop.employee;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    Optional<Employee> findByUsername(String username);
    Set<Employee> findByFirstnameContainsIgnoreCase(String firstname);
    Set<Employee> findBySurnameContainsIgnoreCase(String surname);
    Set<Employee> findByRoleAuthorityContainsIgnoreCase(String name);
    Set<Employee> findByRentPointNameContainsIgnoreCase(String name);
    Set<Employee> findByRentPointAddressContainsIgnoreCase(String address);
    Set<Employee> findByRentPointPostCodeContainsIgnoreCase(String postCode);
    Set<Employee> findByRentPointCityContainsIgnoreCase(String city);
    List<Employee> findByEmploymentDate(LocalDate employmentDate);
    Set<Employee> findByEmploymentDateAfter(LocalDate employmentDate);
    Set<Employee> findByEmploymentDateBefore(LocalDate employmentDate);
    List<Employee> findByEmploymentDateBetween(LocalDate employmentDate1, LocalDate employmentDate2);
    List<Employee> findByReleaseDate(LocalDate releaseDate);
    Set<Employee> findByReleaseDateAfter(LocalDate releaseDate);
    Set<Employee> findByReleaseDateBefore(LocalDate releaseDatee);
    List<Employee> findByReleaseDateBetween(LocalDate releaseDate1, LocalDate releaseDate2);
    Set<Employee> findByReleaseDateIsNull();
    Set<Employee> findByReleaseDateIsNotNull();
}
