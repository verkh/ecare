package com.ecare.servlets;

import com.ecare.models.SubscriberPO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

//public class Register extends HttpServlet {
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String firstName = request.getParameter("firstName");
//        String lastName  = request.getParameter("lastName");
//        String phone     = request.getParameter("phoneNumber");
//        String email     = request.getParameter("email");
//        String password  = request.getParameter("password");
//        String passport  = request.getParameter("passport");
//        String address   = request.getParameter("address");
//        String date      = request.getParameter("birthDate");
//
//        RequestDispatcher req = request.getRequestDispatcher(request.getContextPath() + "SignUpFinish.jsp");
//        req.forward(request, response);
//
//        EntityManagerFactory factory = Persistence.createEntityManagerFactory("eCareDB");
//        EntityManager entityManager = factory.createEntityManager();
//
//        entityManager.getTransaction().begin();
//
//        SubscriberPO sub = new SubscriberPO();
//        sub.setName(firstName);
//        sub.setLastName(lastName);
//        sub.setPassport(passport);
//        sub.setPasswordHash(password);
//        sub.setEmail(email);
//        sub.setAddress(address);
//        sub.setDate(Date.valueOf(date));
//
//        entityManager.persist(sub);
//        entityManager.getTransaction().commit();
//        entityManager.close();
//        factory.close();
//    }
//}