package com.library.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "tbl_book_category")
public class BookCategory {

    @Id
    @SequenceGenerator(name = "category_sequence"
                       ,sequenceName = "category_sequence"
                       ,allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE
                    ,generator = "category_sequence")
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Book> bookList;

}
