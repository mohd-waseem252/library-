package com.library.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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

    @ManyToOne(fetch = FetchType.LAZY)
    private BookCategory category;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

}
