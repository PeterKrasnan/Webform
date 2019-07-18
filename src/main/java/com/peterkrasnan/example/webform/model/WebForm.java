package com.peterkrasnan.example.webform.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class WebForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Policy number is mandatory")
    @Pattern(regexp = "^[\\p{Alnum}]{1,32}$")
    private String policyNumber;

    @NotBlank(message = "Name is mandatory")
    @Pattern(regexp = "^[\\p{Alpha}]{1,32}$")
    private String name;

    @NotBlank(message = "Surname is mandatory")
    @Pattern(regexp = "^[\\p{Alpha}]{1,32}$")
    private String surname;

    @Column(name = "REQUEST_TYPE")
    @Enumerated(EnumType.STRING)
    @NotNull
    private RequestType requestType;

    @NotBlank(message = "Request is mandatory")
    @Size(min = 2, max = 1000)
    private String request;
}
