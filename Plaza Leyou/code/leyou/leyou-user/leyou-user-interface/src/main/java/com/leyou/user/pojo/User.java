package com.leyou.user.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Table(name = "tb_user")
@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Length(min = 5, max = 30, message = "Usuario mínimo 5 caracteres, máximo 30 caracteres")
    private String username;// username

    @Length(min = 5, max = 80, message = "Password mínimo 5 caracteres, máximo 40 caracteres")
    @JsonIgnore //once response, ignore password
    private String password;

   @Pattern(regexp = "^(6|7)[ -]*([0-9][ -]*){8}$", message = "El número de teléfono no es váido")
    private String phone;

    private Date created;

    @JsonIgnore // once response, ignore salt
    private String salt;// secret
}