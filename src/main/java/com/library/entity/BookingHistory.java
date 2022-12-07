package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookingHistory {

    @Id
    @SequenceGenerator(name = "booking_sequence"
                       ,sequenceName = "booking_sequence"
                       ,initialValue = 10101
                       ,allocationSize = 10)
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator = "booking_sequence")
    private Long id;
    private LocalDate bookingDate;
    private LocalDate returnDate;
    private Double fineAmount;

    @OneToMany(mappedBy = "bookingHistory")
    private List<Book> books;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


}
