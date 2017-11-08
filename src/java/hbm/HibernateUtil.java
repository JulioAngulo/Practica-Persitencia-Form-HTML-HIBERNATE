/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hbm;

import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author RigoBono
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory; //Se declara una sesion
    private static final ThreadLocal localSession; //Es el hilo que se le asigna a la sesion
    
    static {
        try {
           Configuration config = new Configuration(); //Se crea el objeto "config" tipo Configuracion
            config.configure("hibernate.cfg.xml"); //Se configura la conexión entre Java y la base de datos (MySQL)
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder(). //Se configuran los registros para poder crear la conexión entre Java y la base de datos (MySQL)
            applySettings(config.getProperties()); //Se aplican las configuraciones
            sessionFactory = config.buildSessionFactory(builder.build()); //Se crea la sesion
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex); //Falla la creación de la sesion
            throw new ExceptionInInitializerError(ex);
        }
        localSession = new ThreadLocal(); //Se crea una sesion local
    }
    
    //Creador de sesiones
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
    //Para indicar cuando se inicia la sesion 
    public static Session getLocalSession() {
        Session session = (Session) localSession.get();
        if (session == null) {
            session = sessionFactory.openSession();
            localSession.set(session);
            System.out.println("\nsesion iniciada");
        }
        return session;
    }
     
    //Para indicar cuando se cierra la sesion 
    public static void closeLocalSession() {
        Session session = (Session) localSession.get();
        if (session != null) session.close();
        localSession.set(null);
        System.out.println("sesion cerrada\n");
    }
}
