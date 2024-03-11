/**
 * @ (#) Enrollment.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.*;
import lombok.*;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student_id;

    @Id
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course_id;

    @Id
    private String semester;

    @Id
    private int year;

    @EqualsAndHashCode.Exclude
    private int score;

    @Override
    public String toString() {
        return "Enrollment{" +
                "student_id=" + student_id.getId() +
                ", course_id=" + course_id.getId() +
                ", semester='" + semester + '\'' +
                ", year=" + year +
                ", score=" + score +
                '}';
    }
}
