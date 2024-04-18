package com.samah.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

//@Transactional
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "USER_DETAILS")
@Table(name = "USERS", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"email"})
})

@Inheritance(strategy = InheritanceType.JOINED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 2, max=50, message = "name should be between 2 and 50 characters")
    @Column(name = "name", nullable = false )
    private String name;

    @Size(min = 8, max=50, message = "Password should be between 8 and 50 characters")
    @JsonIgnore
    private String password;

    @Email
    //@Column(unique = true)
    private String email;

    //@Pattern(regexp ="^\\+[0-9]+$")
    private String phone;

    @Embedded
    private Address address;

    @CreationTimestamp
    @Column(name="created_at")
    private LocalDate createdAt;

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false, updatable = false)
    private LocalDate updatedAt;

    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    private Role role;

}
