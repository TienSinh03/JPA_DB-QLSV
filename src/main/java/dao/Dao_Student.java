/**
 * @ (#) Dao_Student.java      3/12/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/12/2024
 */
public class Dao_Student {
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("JPADemo_QLSV");
    }

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo_QLSV");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    public void insertStudent() {
        doInJPA(this::entityManagerFactory, em -> {
            Class_St classSt = em.find(Class_St.class, "C02");

            Address address = new Address("123 Nguyen Van Linh", "TPHCM", "HCM", 12543);
            Student student = new PartTimeStudent("SV04", "Phan Quang Vương", "phansinh@gmail.com", LocalDate.of(2003, 4, 1), "Khanh Linh");
            student.setGender(Gender.MALE);
            student.setAddress(address);
            student.setPhones(new HashSet<>(List.of("0367494904")));
            student.setClassSt(classSt);

            em.persist(student);
        });
    }


    public Student findStudentByName() {
//        doInJPA(this::entityManagerFactory, em -> {
//            String query = "Select s from Student s where s.name = :namess";
//            return  em.createQuery(query, Student.class).setParameter("namess", "Phan Quang Vuong").getSingleResult();
//        });
        Student student = null;
        try {
            tx.begin();
            String query = "Select s from Student s where s.name = :namess";
            student = em.createQuery(query, Student.class).setParameter("namess", "Phan Quang Vuong").getSingleResult();
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }
        return student;

    }
    public void testUpdateStudent(String id, Class_St classSt, Gender gender,Address address) {
        try {
            tx.begin();
            Student student = em.find(Student.class, id);
            student.setClassSt(classSt);
            student.setGender(gender);
            student.setAddress(address);
            em.merge(student);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }

    }
}
