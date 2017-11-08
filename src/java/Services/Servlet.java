/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import dao.PersonaDAO;
import dao.TipoPersonaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import pojo.Persona;
import pojo.TipoPersona;

/**
 *
 * @author Julio
 */
@WebServlet(name = "Servlet", urlPatterns = {"/Servlet"})
public class Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException
    {
            String nombre=request.getParameter("nombre");
            String paterno=request.getParameter("paterno");
            String materno=request.getParameter("materno");
            String telefono=request.getParameter("telefono");
            int tipo=Integer.parseInt(request.getParameter("tipo"));
            
            TipoPersonaDAO tDAO=new TipoPersonaDAO();
            TipoPersona tipoBD=tDAO.getTipoPersonaById(tipo);
            
            
            PersonaDAO personita=new PersonaDAO(); //Se crea un objeto "personita" tipo "PersonaDAO"
            Persona personaProNuevoPablo=new Persona(); //Se crea un objeto "personaProNuevoPablo" tipo "Persona"
            personaProNuevoPablo.setNombre(nombre); //En el objeto personaNuevoPablo se escribe el nombre
            personaProNuevoPablo.setMaterno(materno);//En el objeto personaNuevoPablo se escribe el apellido materno
            personaProNuevoPablo.setPaterno(paterno);//En el objeto personaNuevoPablo se escribe el apellido paterno
            personaProNuevoPablo.setTelefono(telefono);//En el objeto personaNuevoPablo se escribe el telefono
            personaProNuevoPablo.setTipoPersona(tipoBD);//En el objeto personaNuevoPablo se escribe el tipo de persona
            //personita.updateById(2, personaProNuevoPablo); // Se actualiza el objeto "personita" en el ID= 4 con los los nuevos valores del objeto "personaProNuevoPablo"
            personita.savePersona(nombre, materno, paterno, telefono, tipo); //Se almacena una nueva persona
            
            JSONObject json=new JSONObject();
            
            json.put("resultado", true);
            response.setContentType("application/json utf-8"); //Especifico el tipo de dato que voy a enviar
            PrintWriter out=response.getWriter();
            out.print(json.toString());
            destroy();

    }

}
