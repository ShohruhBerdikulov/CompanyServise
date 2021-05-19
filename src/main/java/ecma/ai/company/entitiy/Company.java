package ecma.ai.company.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Company(corpName, directorName, Address)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "corpName bo'sh bo'lmasin!")
    @Column(unique = true)
    private String corpName;

    @NotNull(message = "directorName bo'sh bo'lmasin!")
    private String directorName;

    @OneToOne
    @NotBlank(message = "address qo'shilmadi")
    @NotNull(message = "address bo'sh bo'lmasin!")
    private Address address;

    public Company(Integer id) {
        this.id = id;
    }
}
