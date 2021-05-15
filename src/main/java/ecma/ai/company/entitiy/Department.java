package ecma.ai.company.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Department(name, Company)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name bo'sh bo'lmasin!")
    private String name;

    @ManyToOne
    @NotNull(message = "company bo'sh bo'lmasin!")
    private Company company;

    public Department(Integer id) {
        this.id = id;
    }

    public Department(@NotBlank(message = "Name bo'sh bo'lmasin!") String name, @NotNull(message = "company bo'sh bo'lmasin!") Company company) {
        this.name = name;
        this.company = company;
    }
}
