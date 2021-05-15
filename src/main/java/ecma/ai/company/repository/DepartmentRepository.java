package ecma.ai.company.repository;

import ecma.ai.company.entitiy.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    boolean existsByNameAndCompany_Id(@NotBlank(message = "Name bo'sh bo'lmasin!") String name, Integer company_id);
    boolean existsByNameAndCompany_IdAndIdNot(
            @NotBlank(message = "Name bo'sh bo'lmasin!") String name,
            Integer company_id, Integer id);
}
