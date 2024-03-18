/**
 * @ (#) Dap_Class.java      3/18/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

package dao;

import entities.Class_St;
import entities.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.*;

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/18/2024
 */
public class Dao_Class {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo_QLSV_MariaDB");
    EntityManager em = emf.createEntityManager();
    EntityTransaction tx = em.getTransaction();

    /**
     * Tính sỉ số sinh viên theo từng lớp học, giảm dần theo sỉ số.
     *
     * @return
     */
    public Map<Class_St, Integer> getSisoByLophoc() {
        String query = "select c.id, count(s.id) as slsv from Student s  INNER JOIN s.classSt c \n" +
                "group by  c.id \n" +
                "order by slsv desc";
        Map<Class_St, Integer> mapSV = new LinkedHashMap<>();
        try {
            tx.begin();
            em.createQuery(query).getResultList().forEach(e -> {
                Object[] obj = (Object[]) e;
                Class_St classSt = em.find(Class_St.class, obj[0]);
                mapSV.put(classSt, Integer.parseInt(obj[1].toString()));
            });

            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return mapSV;
    }

    /**
     * Tính điểm trung bình của các môn học của các sinh viên listSinhvienDiemTB()
     *
     * @return Map<Sinhvien, Float>
     */
    public Map<Student, Float> listSinhvienDiemTB() {
        String query = "select s.student_id ,AVG(e.score) as dtb from students s Join enrollments e On s.student_id = e.student_id join courses c on e.course_id = c.course_id\n" +
                "group by s.student_id";

//        String query = "select s.id ,AVG(e.score) as dtb from Student s Join Enrollment e On s.id = e.student_id join Course c on e.course_id = c.id\n" +
//                "group by s.id";
        Map<Student, Float> mapSv = new HashMap<>();
        try {
            tx.begin();

            em.createNativeQuery(query).getResultList().forEach(e -> {
                Object[] obj = (Object[]) e;
                Student student = em.find(Student.class, obj[0]);
                Float scoreTB = Float.parseFloat(obj[1].toString());
                mapSv.put(student, scoreTB);
            });
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return mapSv;
    }

    public List<Student> listSinhvienGioiJava() {
        String query = "select s from Student s Join Enrollment e On s.id = e.student_id join Course c on e.course_id = c.id \n" +
                "where e.score = (select  MAX (e.score)  as score from  Enrollment e join Course c on e.course_id = c.id \n" +
                "where c.name = 'Java')";
//        String query = "select s.student_id from students s Join enrollments e On s.student_id = e.student_id join courses c on e.course_id = c.course_id\n" +
//                "where e.score LIKE (select  MAX (e.score)  as score from  enrollments e join courses c on e.course_id = c.course_id\n" +
//                "where c.name = 'Java')";
        List<Student> listSv = null;
        try {
            tx.begin();
            // C1: Su dung JPQL
            listSv = em.createQuery(query, Student.class).getResultList();

            // C2: Su dung NativeQuery SQL
//                em.createNativeQuery(query).getResultList().forEach(e -> {
//                    Student student = em.find(Student.class, e);
//                    listSv.add(student);
//                });

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        return listSv;
    }

    public void close() {
        em.close();
        emf.close();
    }


    public static void main(String[] args) {
        Dao_Class dap_class = new Dao_Class();
        Map<Class_St, Integer> map = dap_class.getSisoByLophoc();
        map.forEach((k, v) -> {
            System.out.println(k + " - " + v);
        });

//        Map<Student, Float> map1 = dap_class.listSinhvienDiemTB();
//        map1.forEach((k, v) -> {
//            System.out.println(k.getId() + " - " + v);
//        });

//        List<Student> list = dap_class.listSinhvienGioiJava();
//        list.forEach(e -> {
//            System.out.println(e);
//        });
//
        dap_class.close();


    }

}
