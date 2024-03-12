/**
 * @ (#) Gender.java      3/12/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package entities;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/12/2024
 */
public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");

    private String name;

    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Gender{" +
                "name='" + name + '\'' +
                '}';
    }
}
