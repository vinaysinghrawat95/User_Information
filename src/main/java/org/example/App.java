package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;

import java.util.Scanner;

public class App 
{
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc);

        User_Info usr = new User_Info();

        System.out.print("Enter your Name : ");
        String name = sc.nextLine();
        usr.setName(name);

        System.out.print("Enter your Email Id : ");
        String emailId = sc.nextLine();
        usr.setEmailId(emailId);

        System.out.print("Enter your Phone Number : ");
        String phoneNo = sc.nextLine();
        usr.setPhoneNo(phoneNo);

        System.out.println("\t\t USER INFORMATION");
        System.out.println("_________________________________________________________");

        System.out.println("Name : " + usr.getName());
        System.out.println("Email : " + usr.getEmailId());
        System.out.println("Phone Number : " + usr.getPhoneNo());

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();

        Transaction tx = null;

        try
        {
            tx = session.beginTransaction();
            session.persist(usr);
            tx.commit();
            System.out.println("Information saved successfully");
        }catch (Exception e)
        {
            e.getStackTrace();
            System.out.println("Something went wrong");
        }
        finally {
            if(session != null)
                session.close();
        }

    }
}
