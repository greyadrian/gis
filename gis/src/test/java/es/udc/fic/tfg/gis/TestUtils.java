package es.udc.fic.tfg.gis;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.fic.tfg.gis.model.*;
import es.udc.fic.tfg.gis.services.*;

public class TestUtils {

	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private OrganizacionService organizacionService;
	
	
	public final long timeout = 50;
	
	public Timestamp fecha1;
	public Timestamp fecha2;
	public Timestamp fecha3;
	public Timestamp fecha4;
	public Timestamp fecha5;
	public Timestamp fecha6;
	
	public Usuario usuario1;
	public Usuario usuario2;
	public Usuario usuario3;
	public Usuario usuario4;
	public Usuario usuario5;
	public Usuario usuario6;
	
	public Set<String> telefonos1;
	public Set<String> telefonos2;
	public Set<String> telefonos3;
	public Set<String> telefonos4;
	public Set<String> telefonos5;
	public Set<String> telefonos6;
	
	public Set<String> telefonos1empresa;
	public Set<String> telefonos2empresa;
	public Set<String> telefonos3empresa;
	
	public Direccion direccion1;
	public Direccion direccion2;
	public Direccion direccion3;
	
	public Organizacion organizacion1;
	public Organizacion organizacion2;
	public Organizacion organizacion3;
	
	public void creaSetDatosProba() {

		
		//Creamos Direcciones
		
		direccion1 = new Direccion("Pais1","Provincia1","ciudad1","calle1",1,15701);
		direccion2 = new Direccion("Pais2","Provincia2","ciudad2","calle2",2,15702);
		direccion3 = new Direccion("Pais3","Provincia3","ciudad3","calle3",3,15703);
		
		//Creamos telefonos empresa
		telefonos1empresa = new HashSet<String>();
		telefonos1empresa.add("981135351");
		telefonos1empresa.add("981135352");
		
		telefonos2empresa = new HashSet<String>();
		telefonos2empresa.add("981235353");
		telefonos2empresa.add("981235354");
		
		telefonos3empresa = new HashSet<String>();
		telefonos3empresa.add("981335355");
		telefonos3empresa.add("981335356");
		
				
		//Creamos Organizaciones

		organizacion1 = new Organizacion("nombre1","11111111A","desc1",direccion1,telefonos1empresa,"empresa1@gmail.com");
		organizacion2 = new Organizacion("nombre2","11111111B","desc2",direccion2,telefonos2empresa,"empresa2@gmail.com");
		organizacion3 = new Organizacion("nombre1","11111111C","desc3",direccion3,telefonos3empresa,"empresa3@gmail.com");		
		
		
		//Creamos telefonos Usuarios
		telefonos1 = new HashSet<String>();
		telefonos1.add("981535351");
		telefonos1.add("981535352");
		
		telefonos2 = new HashSet<String>();
		telefonos2.add("981535353");
		telefonos2.add("981535354");
		
		telefonos3 = new HashSet<String>();
		telefonos3.add("981535355");
		telefonos3.add("981535356");
		
		telefonos4 = new HashSet<String>();
		telefonos4.add("981535357");
		telefonos4.add("981535358");
		
		telefonos5 = new HashSet<String>();
		telefonos5.add("981535301");
		telefonos5.add("981535302");
		
		telefonos6 = new HashSet<String>();
		telefonos6.add("981535303");
		telefonos6.add("981535304");
		
		//Fechas alta usuarios
		fecha1 = new Timestamp(Calendar.getInstance().getTimeInMillis());
		fecha2 = new Timestamp(Calendar.getInstance().getTimeInMillis());
		fecha3 = new Timestamp(Calendar.getInstance().getTimeInMillis());
		fecha4 = new Timestamp(Calendar.getInstance().getTimeInMillis());
		fecha5 = new Timestamp(Calendar.getInstance().getTimeInMillis());
		fecha6 = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		//Creamos Usuarios
		usuario1 = new Usuario("nombre1","apellido1","apellido1","mail1@gmail.com","password1",telefonos1,false,fecha1,organizacion1);
		usuario2 = new Usuario("nombre2","apellido2","apellido2","mail2@gmail.com","password2",telefonos2,false,fecha2,organizacion1);
		
		usuario3 = new Usuario("nombre3","apellido3","apellido3","mail3@gmail.com","password3",telefonos3,false,fecha3,organizacion2);
		usuario4 = new Usuario("nombre4","apellido4","apellido4","mail4@gmail.com","password4",telefonos4,false,fecha4,organizacion2);
		
		usuario5 = new Usuario("nombre5","apellido5","apellido5","mail5@gmail.com","password5",telefonos5,false,fecha5,organizacion3);
		usuario6 = new Usuario("nombre6","apellido6","apellido6","mail6@gmail.com","password6",telefonos6,false,fecha6,organizacion3);

		
		//Insertamos las organizaciones
		organizacionService.registrarOrganizacion(organizacion1);
		organizacionService.registrarOrganizacion(organizacion2);
		organizacionService.registrarOrganizacion(organizacion3);
		
		//Insertamos los usuarios
		usuarioService.registrarUsuario(usuario1);
		usuarioService.registrarUsuario(usuario2);
		usuarioService.registrarUsuario(usuario3);
		usuarioService.registrarUsuario(usuario4);
		usuarioService.registrarUsuario(usuario5);
		usuarioService.registrarUsuario(usuario6);
		


	}
	
	public void eliminaSetDatosProba() {

		//Borramos usuarios
		usuarioService.eliminarUsuario(usuario1.getIdUsuario());
		usuarioService.eliminarUsuario(usuario2.getIdUsuario());
		usuarioService.eliminarUsuario(usuario3.getIdUsuario());
		usuarioService.eliminarUsuario(usuario4.getIdUsuario());
		usuarioService.eliminarUsuario(usuario5.getIdUsuario());
		usuarioService.eliminarUsuario(usuario6.getIdUsuario());
		

		// Borramos organizaciones
		
		organizacionService.borrarOrganizacion(organizacion1.getIdOrganizacion());
		organizacionService.borrarOrganizacion(organizacion2.getIdOrganizacion());
		organizacionService.borrarOrganizacion(organizacion3.getIdOrganizacion());


	}
}
