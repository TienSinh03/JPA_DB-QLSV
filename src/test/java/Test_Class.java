import entities.Class_St;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;

import static org.hibernate.testing.transaction.TransactionUtil.doInJPA;

/**
 * @ (#) Test_Class.java      3/12/2024
 * <p>
 * Copyright (c) 2024 IUH. All rights reserved
 */

/*
 * @description:
 * @author: Sinh Phan Tien
 * @date: 3/12/2024
 */
public class Test_Class {
    public EntityManagerFactory entityManagerFactory(){
        return Persistence.createEntityManagerFactory("JPADemo_QLSV_MariaDB");
    }

    @Test
    public void testInsertClass() {
        doInJPA(this::entityManagerFactory, em ->{
           String query = "INSERT INTO classes VALUES ('C02', 'SQL Sever', 10)";
           int n = em.createNativeQuery(query).executeUpdate();
           System.out.println("n = " + n);
        });
    }
}
