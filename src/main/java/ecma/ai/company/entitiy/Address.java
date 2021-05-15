package ecma.ai.company.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Street bo'sh bo'lmasin!")
    private String street;

    @NotNull(message = "uy raqami bo'sh bo'lmasin!")
    private String homeNumber;

    public Address(Integer id) {
        this.id = id;
    }

    public Address(@NotNull(message = "Street bo'sh bo'lmasin!") String street,
                   @NotNull(message = "uy raqami bo'sh bo'lmasin!") String homeNumber) {
        this.street = street;
        this.homeNumber = homeNumber;
    }
}
