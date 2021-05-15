package ecma.ai.company.repository;

import ecma.ai.company.entitiy.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByHomeNumberAndStreet(
            @NotNull(message = "uy raqami bo'sh bo'lmasin!") String homeNumber,
            @NotNull(message = "Street bo'sh bo'lmasin!") String street);
    boolean existsByHomeNumberAndStreetAndIdNot(
            @NotNull(message = "uy raqami bo'sh bo'lmasin!") String homeNumber,
            @NotNull(message = "Street bo'sh bo'lmasin!") String street,
            Integer id);

}
