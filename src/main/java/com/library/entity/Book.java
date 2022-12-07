package com.library.entity;

import lombok.*;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "tbl_books")
public class Book {

    @Id
    @SequenceGenerator(name = "book_sequence"
                       ,sequenceName = "book_sequence"
                       ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator = "book_sequence")
    @EqualsAndHashCode.Include
    private Long id;
    private String title;
    private String author;
    private LocalDate publicationDate;
    private Long copiesAvailable;

    @ManyToOne
    private BookCategory category;

    @ManyToOne
    @LazyCollection(value =LazyCollectionOption.TRUE)
    private BookingHistory bookingHistory;

}
