package pl.jaczewski.sklepdemo.model;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
public class User {

    private Long id;
    @NotBlank
    private String userName;
    @Email
    @NotBlank
    private String email;
    @NotBlank
    @Min(6)
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
