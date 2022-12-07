package com.library.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "tbl_user"
        ,uniqueConstraints = @UniqueConstraint(
                             name = "emailid_unique",columnNames = "email_id"))
public class User {

    @Id
    @SequenceGenerator(name = "sequence_generator"
                       ,sequenceName = "sequence_generator"
                       ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator = "sequence_generator")
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNo;

    @Column(name = "email_id",nullable = false)
    private String emailId;
    private String password;
    private LocalDate joinedDate;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<BookingHistory> orders;
}
