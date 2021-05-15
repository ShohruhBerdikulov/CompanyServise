package ecma.ai.company.repository;

import ecma.ai.company.entitiy.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByPhoneNumber(@NotNull(message = "phoneNumber bo'sh bo'lmasin!") String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(
            @NotNull(message = "phoneNumber bo'sh bo'lmasin!") String phoneNumber, Integer id);
}
