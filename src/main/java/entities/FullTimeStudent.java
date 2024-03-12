/**
 * @ (#) FullTimeStudent.java      3/12/2024
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
@Table(name = "full_time_students")
public class FullTimeStudent extends Student{
    private String facutly;
    private String department;

    public FullTimeStudent(String id, String name, String email, LocalDate dob, String facutly, String department) {
        super(id, name, email, dob);
        this.facutly = facutly;
        this.department = department;
    }
}
