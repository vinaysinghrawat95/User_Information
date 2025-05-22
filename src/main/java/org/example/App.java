package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;
import java.util.Scanner;

public class App 
{
    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.println(sc);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();;
        Transaction tx = null;

        while(true) {
            System.out.println(" 1.Create" +
                    " \n 2.Read " +
                    "\n 3.Update " +
                    "\n 4.Delete " +
                    "\n 5.Exit");
            System.out.print("Enter what you want to do : ");
            int choose = sc.nextInt();
            sc.nextLine();


            switch (choose) {
                case 1:
                    User_Info usr = new User_Info();
                    try(Session session = sessionFactory.openSession())
                    {
                        tx = session.beginTransaction();
                    System.out.print("Enter your Name : ");
                    String name = sc.nextLine();
                    usr.setName(name);

                    System.out.print("Enter your Email Id : ");
                    String emailId = sc.nextLine();
                    usr.setEmailId(emailId);

                    System.out.print("Enter your Phone Number : ");
                    String phoneNo = sc.nextLine();
                    usr.setPhoneNo(phoneNo);

                    System.out.println("\t\t\t\t USER INFORMATION");
                    System.out.println("_________________________________________________________");

                    System.out.println("Name : " + usr.getName());
                    System.out.println("Email : " + usr.getEmailId());
                    System.out.println("Phone Number : " + usr.getPhoneNo());


                        session.persist(usr);
                        tx.commit();
                        System.out.println("Information saved successfully");
                        session.close();
                    } catch (Exception e) {
                        e.getStackTrace();
                        System.out.println("Something went wrong");
                    }
                    break;

                case 2:
                   try(Session session = sessionFactory.openSession()) {
                       List<User_Info> users = session.createQuery("FROM User_Info", User_Info.class).list();

                       System.out.println("+----+----------------+------------------------+--------------+");
                       System.out.printf("| %-2s | %-14s | %-22s | %-12s |\n", "ID", "Name", "Email", "Phone");
                       System.out.println("+----+----------------+------------------------+--------------+");

                       for (User_Info user : users) {
                           System.out.printf("| %-2d | %-14s | %-22s | %-12s |\n",
                                   user.getId(), user.getName(), user.getEmailId(), user.getPhoneNo());
                       }

                       System.out.println("+----+----------------+------------------------+--------------+");
                       session.close();
                   }
                       break;


                case 3:
                   try(Session session = sessionFactory.openSession()) {
                       tx = session.beginTransaction();
                       System.out.print("Enter id which user you want update : ");
                       long id = sc.nextLong();
                       sc.nextLine();

                       usr = session.get(User_Info.class, id);

                       if (usr == null) {
                           System.out.println("User not found");
                       } else {
                           System.out.print("Do you want to change the User Name? (Y / N) : ");
                           if (sc.nextLine().equalsIgnoreCase("Y")) {
                               System.out.print("Enter new name : ");
                               String newName = sc.nextLine();
                               usr.setName(newName);
                           }

                           System.out.print("Do you want to change the User Email? (Y / N) : ");
                           if (sc.nextLine().equalsIgnoreCase("Y")) {
                               System.out.print("Enter new Email : ");
                               String email = sc.nextLine();
                               usr.setEmailId(email);
                           }

                           System.out.print("Do you want to change the User Number? (Y / N) : ");
                           if (sc.nextLine().equalsIgnoreCase("Y")) {
                               System.out.print("Enter new Number : ");
                               String num = sc.nextLine();
                               usr.setPhoneNo(num);
                           }
                           session.merge(usr);
                           tx.commit();
                           System.out.println("User update successfully");
                       }
                       session.close();
                   }
                    break;

                case 4:
                    try(Session session = sessionFactory.openSession()) {
                        System.out.print("Enter id for deleting user : ");
                        long Id = sc.nextLong();
                        sc.nextLine();
                        tx = session.beginTransaction();
                        usr = session.get(User_Info.class, Id);
                        if (usr != null) {
                            session.remove(usr);
                            tx.commit();
                            System.out.println("User deleted successfully");
                        }
                    }
                    break;

                case 5:
                    return;

                default:
                    System.out.println("Invalid input , please try again");
                    break;
            }
        }


    }

}
