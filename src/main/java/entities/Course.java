/**
 * @ (#) Course.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @Column(name = "course_id")
    private String id;
    @Column(columnDefinition = "nvarchar(100)", nullable = false, unique = true) //Viet co dau va khong duoc null
    private String name;
    private int credit;
}
