package pl.jaczewski.sklepdemo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@NoArgsConstructor
@Getter
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Nazwa użytkownika nie może być pusta")
    @Size(min = 3, message = "Nazwa użytkownika musi składać się z przynajmniej 3 znaków")
    private String userName;
    @Email
    @NotBlank(message = "E-mail nie może być pusty")
    private String email;
    @NotBlank(message = "Hasło nie może być puste")
    @Size(min = 6, message = "Hasło musi składać się z przynajmniej 6 znaków")
    private String password;
    private String phone;
    private boolean isAdmin;
    private boolean isAdult;
    // inne pola (ulica, miasto, kod pocztowy, drugi adres wysyłki itp.)

    public User(Long id, String userName, String email, String password, String phone, boolean isAdmin, boolean isAdult) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.isAdmin = isAdmin;
        this.isAdult = isAdult;
    }
}
