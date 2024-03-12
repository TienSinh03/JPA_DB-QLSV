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
import java.util.Set;

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
@Inheritance(strategy = InheritanceType.JOINED)
public class Student {
    @Id
    @Column(name = "student_id")
    protected String id;
    @Column(columnDefinition = "nvarchar(100)", nullable = false, unique = true) //Viet co dau va khong duoc null
    protected String name;
    @Column(nullable = false, unique = true) // email is not null and unique
    protected String email;
    protected LocalDate dob;

    // Quan he 1-n giua student va class (1 sinh vien chi thuoc 1 lop)
    //Ben Many
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    protected Class_St classSt;

    //Add Enum
    @Enumerated(EnumType.STRING)
    protected Gender gender;

    // Nhung class address
    @Embedded
    protected Address address;

    @ElementCollection
    @CollectionTable(name = "phones", joinColumns = @JoinColumn(name = "student_id"))
    @Column(name = "phone")
    protected Set<String> phones;


    public Student(String id, String name, String email, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.dob = dob;
    }


}
