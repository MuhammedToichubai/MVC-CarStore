package java16.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phoneNumber;
    private String role;
    private LocalDate registrationDate;
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<Car> cars;

    @PrePersist
    public void prePersist() {
        this.registrationDate = LocalDate.now();
    }

    public User(String email, String password, String phoneNumber, String role, String name) {
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.name = name;
    }

}

