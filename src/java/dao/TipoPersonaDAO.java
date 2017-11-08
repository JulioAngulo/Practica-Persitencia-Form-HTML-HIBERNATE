/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hbm.HibernateUtil;
import org.hibernate.Session;
import pojo.TipoPersona;

/**
 *
 * @author Julio
 */
public class TipoPersonaDAO {
    
    Session session;
    
    public TipoPersonaDAO(){
        session=HibernateUtil.getLocalSession();
    }
    
    
    public TipoPersona getTipoPersonaById(int id){
        return (TipoPersona)session.load(TipoPersona.class, id);
    }
}

