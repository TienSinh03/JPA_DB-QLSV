/**
 * @ (#) Address.java      3/12/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;


/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/12/2024
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@Embeddable
@AllArgsConstructor
public class Address {
    private String street;
    private String city;
    private String state;
    @Column(name = "postal_code")
    private int postalCode;

}
