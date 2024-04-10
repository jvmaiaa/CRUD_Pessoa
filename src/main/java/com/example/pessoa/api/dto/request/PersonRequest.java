package com.example.pessoa.api.dto.request;


import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    @NotBlank(message = "The name field cannot be blank")
    @Length(max = 100, message = "You have exceeded the 100 character length")
    private String name;

    @NotNull(message = "The age field cannot be blank")
    @Positive(message = "The age field must be a positive number")
    private Integer age;

    @Email(message = "Invalid E-mail format")
    @NotBlank(message = "The email field cannot be blank")
    @Length(max = 50, message = "You have exceeded the 100 character length")
    private String email;

    @NotNull(message = "Id address field cannot be null")
    private Long idAddress;
}
