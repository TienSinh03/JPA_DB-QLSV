/**
 * @ (#) Class_St.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
//@AllArgsConstructor
@Table(name = "classes")
public class Class_St {
    @Id
    @Column(name = "class_id")
    private String id;
    @Column(columnDefinition = "nvarchar(100)", nullable = false, unique = true) //Viet co dau va khong duoc null
    private String name;
    private int noOfStudents;

    // Quan he 1-n giua student va class ( 1 lop co nhieu sinh vienp)
    // Ben One
    @ToString.Exclude // Khong in ra
    @OneToMany(mappedBy = "classSt", fetch = FetchType.LAZY)
    private List<Student> students;

    public Class_St(String id, String name, int noOfStudents) {
        this.id = id;
        this.name = name;
        this.noOfStudents = noOfStudents;
    }
}
