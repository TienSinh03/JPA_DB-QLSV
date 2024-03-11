import entities.Class_St;
import entities.Course;
import entities.Enrollment;
import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import jakarta.transaction.Transaction;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

/**
 * @ (#) Testing.java      3/11/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/11/2024
 */
public class Testing {
//    public static void main(String[] args) {
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo_QLSV"); // Dung de quan ly tat ca cac property persistence unit
//
//        EntityManager em = emf.createEntityManager(); // Quan ly cac entities
//
//        EntityTransaction tx = em.getTransaction();
//
//        //Them sinh vine
//        Student student = new Student("SV01", "Sinh", "za@gmail.com", LocalDate.of(2000, 1, 1));
//
//        try {
//            tx.begin();
//
//            em.persist(student); // Them sinh vien
//
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            tx.rollback();
//        } finally {
//            em.close();
//            emf.close();
//        }
//
//    }

    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("JPADemo_QLSV");
    }

    @Test
    public void testAddStudentToClass() {

        doInJPA(this::entityManagerFactory, enentityManager -> {
            Class_St classSt = new Class_St("C01", "C01", 10);
            Student st = enentityManager.find(Student.class,"SV01");
            st.setClassSt(classSt);

            //Create new class
            enentityManager.persist(classSt);
            //Update student
            enentityManager.merge(st);

        });
    }

    @Test
    public void testAddCourse() {
        doInJPA(this::entityManagerFactory, em -> {
            Course course = new Course("Cs01", "Java", 4);
            em.persist(course);
        });
    }

    @Test
    public void testAddEnrollement() {
        doInJPA(this::entityManagerFactory, em -> {
            Student  st = em.find(Student.class, "SV01");
            Course course = em.find(Course.class, "Cs01");
            Enrollment enrollment = new Enrollment(st, course,"HK2", 2021, 9);


            em.persist(enrollment);
//            em.merge(enrollment);
        });
    }
}