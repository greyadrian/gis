package es.udc.fic.tfg.gis.web.app;






import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fic.tfg.gis.services.UsuarioService;
import es.udc.fic.tfg.gis.services.OrganizacionService;
import es.udc.fic.tfg.gis.model.*;




@RestController
public class AppRestController {
	
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	OrganizacionService organizacionService;
	
	long numero =55;
	
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
    public @ResponseBody Usuario verUsuario() {
	
	
		return 	  usuarioService.buscarUsuarioPorId(numero);
		
			
    }
	
	@RequestMapping(value="/registrarOrganizacion", method = RequestMethod.GET)
    public @ResponseBody Long registrarOrganizacion(@RequestParam(value = "nombre") String nombre,
    														@RequestParam(value = "cif") String cif,
    														@RequestParam(value = "descripcion")String descripcion,
    														@RequestParam(value = "pais")String pais,
    														@RequestParam(value = "provincia") String provincia,
    														@RequestParam(value = "ciudad")String ciudad,
    														@RequestParam(value =  "calle")String calle,
    														@RequestParam(value = "numero")int numero,
    														@RequestParam(value = "codigoPostal")int codigoPostal,
    														@RequestParam(value = "telefono")String telefono,
    														@RequestParam(value = "email")String email) {
		
			
			Direccion miDireccion = new Direccion (pais, provincia, ciudad, calle, numero, codigoPostal);
			final  Set<String> telefonos = new HashSet<String>();
			telefonos.add(telefono);
			Organizacion miOrganizacion = new Organizacion(nombre, cif, descripcion, miDireccion, telefonos, email);
		
		try{	
				
			return organizacionService.registrarOrganizacion(miOrganizacion);
					
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
    }
	
	@RequestMapping(value="/actualizaOrganizacion", method = RequestMethod.GET)
    public @ResponseBody void actualizarOrganizacion(@RequestParam(value = "nombre") String nombre,
    														@RequestParam(value = "cif") String cif,
    														@RequestParam(value = "descripcion")String descripcion,
    														@RequestParam(value = "pais")String pais,
    														@RequestParam(value = "provincia") String provincia,
    														@RequestParam(value = "ciudad")String ciudad,
    														@RequestParam(value =  "calle")String calle,
    														@RequestParam(value = "numero")int numero,
    														@RequestParam(value = "codigoPostal")int codigoPostal,
    														@RequestParam(value = "telefono")String telefono,
    														@RequestParam(value = "email")String email) {
		
			
			Direccion miDireccion = new Direccion (pais, provincia, ciudad, calle, numero, codigoPostal);
			final  Set<String> telefonos = new HashSet<String>();
			telefonos.add(telefono);
			Organizacion miOrganizacion = new Organizacion(nombre, cif, descripcion, miDireccion, telefonos, email);
		
		try{	
				
			 organizacionService.actualizarOrganizacion(miOrganizacion);
					
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
    }
	
	@RequestMapping(value="/borrarOrganizacion", method = RequestMethod.GET)
    public @ResponseBody void borrarOrganizacion(@RequestParam(value = "idOrganizacion") Long idOrganizacion) {
			
		try{	
				
			 organizacionService.borrarOrganizacion(idOrganizacion);
					
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
    }
	
	@RequestMapping(value="/buscarOrganizacionPorId", method = RequestMethod.GET)
    public @ResponseBody void buscarOrganizacionPorId(@RequestParam(value = "idOrganizacion") Long idOrganizacion) {
			
		try{	
				
			 organizacionService.buscarOrganizacionPorId(idOrganizacion);
					
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
    }

//	@RequestMapping(value="/registrarUsuario", method = RequestMethod.GET)
//    public @ResponseBody void registrarUsuario(@RequestParam(value = "email") String email,
//    										   @RequestParam(value = "nombre") String nombre,
//    										   @RequestParam(value = "apellido1") String apellido1,
//    										   @RequestParam(value = "apellido2") String apellido2,
//    										   @RequestParam(value = "password") String password,
//    										   @RequestParam(value = "esAdministrador") Boolean esAdministrador,
//    										   @RequestParam(value = "telefono") String telefono) {
//			
//		try{	
//			 final  Set<String> telefonos = new HashSet<String>();
//			 telefonos.add(telefono);
//			 //solo le quiero pasar el id de la Organizacion
//			 Usuario nuevoUsuario = new Usuario(nombre, apellido1, apellido2, email, password, telefonos, esAdministrador,  organizacion)				
//			 usuarioService.registrarUsuario(nuevoUsuario);
//					
//		}catch(Exception e){
//			throw new RuntimeException(e);
//		}	
//	}
//	
//	@RequestMapping(value="/actualizarUsuario", method = RequestMethod.GET)
//    public @ResponseBody void actualizarUsuario(@RequestParam(value = "email") String email,
//    										   @RequestParam(value = "nombre") String nombre,
//    										   @RequestParam(value = "apellido1") String apellido1,
//    										   @RequestParam(value = "apellido2") String apellido2,
//    										   @RequestParam(value = "password") String password,
//    										   @RequestParam(value = "esAdministrador") Boolean esAdministrador,
//    										   @RequestParam(value = "telefono") String telefono) {
//			
//		try{	
//			 final  Set<String> telefonos = new HashSet<String>();
//			 telefonos.add(telefono);
//			 Usuario nuevoUsuario = new Usuario(nombre, apellido1, apellido2, email, password, telefonos, esAdministrador,  organizacion)				
//			 usuarioService.actualizarUsuario(nuevoUsuario);
//					
//		}catch(Exception e){
//			throw new RuntimeException(e);
//		}	
//	}
	
	@RequestMapping(value="/eliminarUsuario", method = RequestMethod.GET)
    public @ResponseBody void eliminarUsuario(@RequestParam(value = "idUsuario") Long idUsuario) {
			
		try{	
				
			 usuarioService.eliminarUsuario(idUsuario);
					
		}catch(Exception e){
			throw new RuntimeException(e);
		}		
    }
		
	
	
}
