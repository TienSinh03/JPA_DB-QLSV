import dao.Dao_Student;
import entities.*;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

/**
 * @ (#) Test_Student.java      3/12/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/12/2024
 */

public class Test_Student {
    public EntityManagerFactory entityManagerFactory() {
        return Persistence.createEntityManagerFactory("JPADemo_QLSV");
    }

    private Dao_Student dao_student;

    @BeforeEach
    public void setUp() {
        dao_student = new Dao_Student();
    }

    @Test
    public void testAddStudent() {
        doInJPA(this::entityManagerFactory, em -> {
            Class_St classSt = em.find( Class_St.class,"C02");

            Address address = new Address("123 Nguyen Van Linh", "TPHCM", "HCM", 12543);
            Student student = new PartTimeStudent("SV04", "Phan Quang Vương", "phansinh@gmail.com", LocalDate.of(2003, 4, 1), "Khanh Linh");
            student.setGender(Gender.MALE);
            student.setAddress(address);
            student.setPhones(new HashSet<>(List.of("0367494904")));
            student.setClassSt(classSt);

            em.persist(student);
        });
    }

    @Test
    public void testFindStudentByName()  {
//        doInJPA(this::entityManagerFactory, em -> {
//            String query = "Select s from Student s where s.name = :namess";
//           List<Student> student = em.createQuery(query, Student.class).setParameter("namess", "Phan Quang Vuong").getResultList();
//            System.out.println("student = " + student);
//        });
        Student student = dao_student.findStudentByName();
        Assertions.assertEquals("SV04", student.getId());

    }

    @Test
    public void testUpdateStudent() {
        doInJPA(this::entityManagerFactory, em -> {
            Class_St classSt = em.find(Class_St.class, "C02");
            Address address = new Address("123 Nguyen Thai son", "TPHCM", "HCM", 12543);
            dao_student.testUpdateStudent("SV02", classSt, Gender.MALE,address);

        });
    }
}
