package net.javaguides.todomanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name="TODO",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"title"})
)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key as auto increment
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "description",nullable = false)
    private String description;

    @Column(name = "completed",nullable = false)
    private boolean completed;
}
