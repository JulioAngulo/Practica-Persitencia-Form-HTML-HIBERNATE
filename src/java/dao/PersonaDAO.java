/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hbm.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import pojo.Persona;
import pojo.TipoPersona;

/**
 *
 * @author RigoBono
 */
public class PersonaDAO {
    Session session;
    
    public PersonaDAO(){
        session=HibernateUtil.getLocalSession(); //Se asigna una sesion al usuario
    }
    
    //Se obtienen datos de una persona con ayuda de su ID
    public  Persona getPersonaById(int id){
        return (Persona)session.load(Persona.class, id);
    }
    
    //Método para obtener un nombre de una base de datos
    public List<Persona>  getPersonaByName(String nombre){
        List<Persona> listaDePersonas=(List<Persona>)
                session.createCriteria(Persona.class)
                .add(Restrictions.eq("Nombre", nombre));
        return listaDePersonas;
    }
    
    //Método para actualizar la base de datos de acuerdo a un ID ingresado
    public boolean updateById(int id,Persona persona){
        Persona personaAModificar=getPersonaById(id);
        try{
            Transaction transaccion=session.beginTransaction();
            personaAModificar.setNombre(persona.getNombre());
            personaAModificar.setPaterno(persona.getPaterno());
            session.update(personaAModificar);
            transaccion.commit();
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    //Método para almacenar un nuevo dato en la base de datos (nombre, materno, paterno, telefono, idTipoPersona)
    public boolean savePersona(String nombre,String materno,String paterno,String telefono,int idTipoPersona){
        Persona personaDeTanque=new Persona();
        TipoPersona tipoDeTanque=(TipoPersona)session.load(TipoPersona.class, idTipoPersona);
        personaDeTanque.setNombre(nombre);
        personaDeTanque.setMaterno(materno);
        personaDeTanque.setPaterno(paterno);
        personaDeTanque.setTelefono(telefono);
        personaDeTanque.setTipoPersona(tipoDeTanque);
        try{
            Transaction transaccion=session.beginTransaction();
            session.save(personaDeTanque);
            transaccion.commit();
            return true;
        }catch(Exception e){
            
        }finally{
            
        }
        return true;
    }
    
}
