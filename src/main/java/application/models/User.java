package application.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", unique = true)
    private String username;
    @Column(name = "EMAIL", unique = true)
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "PROFILE_PHOTO_URL")
    private String profilePhotoUrl;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "OCCUPATION")
    private String occupation;

    @Column(name = "DATE_OF_BIRTH")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @Column(name = "GENDER")
    private String gender;

    @Column(name = "ROLE")
    private String role;

    public User(String username, String password, String firstName, String lastName) {
        this.email = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}