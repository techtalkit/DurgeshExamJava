package com.exam.entity;
import lombok.*;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long userRole;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    @ManyToOne
    private Role role;

}
