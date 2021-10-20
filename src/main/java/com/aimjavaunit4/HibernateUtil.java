package com.aimjavaunit4;

import org.hibernate.*;
import org.hibernate.cfg.*;

public class HibernateUtil 
{

  private static SessionFactory sessionFactory;
 
  private static SessionFactory buildSessionFactory() 
  {
    try
    {
      return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
    }
    catch (Throwable ex)
    {
      System.err.println("SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
 
  public static SessionFactory getSessionFactory() 
  {
    if(sessionFactory == null)
    {
      sessionFactory = buildSessionFactory();
    }
    
    return sessionFactory;
  }
 
  public static void shutdown() 
  {
    getSessionFactory().close();
  }
  
}
