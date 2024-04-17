package com.samah.userservice.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Set;

//@Transactional
@Entity(name = "USER_DETAILS")
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name"}),
        @UniqueConstraint(columnNames = {"email"})
})

@Data  @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Size(min = 2, max=50, message = "name should be between 2 and 50 characters")
    @Column(name = "name", nullable = false )
    private String name;

    @Email
    //@Column(unique = true)
    private String email;

    //@Pattern(regexp ="^\\+[0-9]+$")
    private String phone;

    @Embedded
    private Address address;

    private String jobTitle;

    @NotBlank(message = "Role is mandatory field")
    private String role;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false, updatable = false)
    private LocalDate updatedAt;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;
}
