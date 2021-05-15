package ecma.ai.company.entitiy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//Worker(name, phoneNumber, Address, Department)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Name bo'sh bo'lmasin!")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "phoneNumber bo'sh bo'lmasin!")
    private String phoneNumber;

    //not unique. Maybe have 2 workers in one home.
    @OneToOne
    @NotNull(message = "address bo'sh bo'lmasin!")
    private Address address;

    @OneToOne
    @NotNull(message = "department bo'sh bo'lmasin!")
    private Department department;

}
