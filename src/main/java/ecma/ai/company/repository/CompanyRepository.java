package ecma.ai.company.repository;

import ecma.ai.company.entitiy.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpNameOrAddress_Id(
            @NotNull(message = "corpName bo'sh bo'lmasin!") String corpName,
            Integer address_id);

    //IQ LVL 99 ))
    boolean existsByCorpNameAndIdNotOrAddress_IdAndIdNot(
            @NotNull(message = "corpName bo'sh bo'lmasin!") String corpName,
            Integer id, Integer address_id, Integer id2);


}
