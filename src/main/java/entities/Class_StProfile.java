/**
 * @ (#) Class_StProfile.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "class_profiles")
public class Class_StProfile {
    @Id
    @Column(name = "profile_id")
    private String profile_id;
    private LocalDate createdDate;
    private String decription;

    @OneToOne
    @JoinColumn(name = "class_id", nullable = false, unique = true)
    private Class_St classSt;

    public Class_StProfile(String profile_id, LocalDate createdDate, String decription) {
        this.profile_id = profile_id;
        this.createdDate = createdDate;
        this.decription = decription;
    }

}
