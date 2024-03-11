/**
 * @ (#) Student.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
@Table(name = "students")
public class Student {
    @Id
    @Column(name = "student_id")
    private String id;
    @Column(columnDefinition = "nvarchar(100)", nullable = false, unique = true) //Viet co dau va khong duoc null
    private String name;
    @Column(nullable = false, unique = true) // email is not null and unique
    private String email;
    private LocalDate dob;

    // Quan he 1-n giua student va class (1 sinh vien chi thuoc 1 lop)
    //Ben Many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private Class_St classSt;
    public Student(String id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }


}