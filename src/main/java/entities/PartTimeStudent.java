/**
 * @ (#) PartTimeStudent.java      3/12/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/12/2024
 */
@Setter
@Getter
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "part_time_students")
public class PartTimeStudent extends Student {
    private String supervisor;

    public PartTimeStudent(String id, String name, String email, LocalDate dob, String supervisor) {
        super(id, name, email, dob);
        this.supervisor = supervisor;
    }
}
