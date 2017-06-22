package es.udc.fic.tfg.gis.web.app;






import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import es.udc.fic.tfg.gis.services.UsuarioService;
import es.udc.fic.tfg.gis.model.*;




@RestController
public class AppRestController {
	
	@Autowired
	UsuarioService usuarioService;
long numero =55;
	
	
	@RequestMapping(value="/user", method = RequestMethod.GET)
    public @ResponseBody Usuario verUsuario() {
	
	
		return 	  usuarioService.buscarUsuarioPorId(numero);
		
			
    }
		
	
	
}
