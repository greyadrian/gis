package es.udc.fic.tfg.gis;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import es.udc.fic.tfg.gis.model.*;
import es.udc.fic.tfg.gis.services.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring-config.xml", "/test-spring-config.xml"})
@ActiveProfiles("test")
public class TestUsuario {
	
	private Logger log = Logger.getLogger("Gis");

	@Autowired
	private TestUtils testUtils;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private OrganizacionService organizacionService;
	
	@Before
	public void setUp() throws Exception { 
		log.info ("Inicializando datos para caso de proba: " + this.getClass().getName());
		testUtils.creaSetDatosProba();
		log.info ("Datos inicializados con éxito");
	}

	@After
	public void tearDown() throws Exception {
		log.info ("Eliminando datos para caso de proba: " + this.getClass().getName());
//		testUtils.eliminaSetDatosProba();  
		log.info ("Datos eliminados con éxito");
	}
	
	@Test
	public void testBasicoUsuario() {
		assertTrue(true);
		
		//Creamos Direcciones
		
		Direccion direccion = new Direccion("Pais","Provincia","ciudad","calle",1,15701);
				
		//Creamos telefonos empresa
		Set<String> telefonosEmpresa = new HashSet<String>();
					telefonosEmpresa.add("981135300");
					telefonosEmpresa.add("981135300");
					
		//Creamos Organizaciones

		Organizacion organizacionRegistro = new Organizacion("OrganizacionRegistro","99999999A","desc1",direccion,telefonosEmpresa,"empresaRegistro@gmail.com");

		//Creamos telefonos Usuarios
		Set<String> telefonos1 = new HashSet<String>();
		telefonos1.add("981535300");
		telefonos1.add("981535300");
		
		//Fechas alta usuarios
		Timestamp fechaAlta = new Timestamp(Calendar.getInstance().getTimeInMillis());
		
		//Creamos Usuarios
		Usuario usuarioRegistro = new Usuario("UsuarioRegistro","apellido1","apellido1","UsuarioRegistro@gmail.com","password1",telefonos1,false,fechaAlta,organizacionRegistro);

		
		
		//T1.Registrar organizacion
		organizacionService.registrarOrganizacion(organizacionRegistro);
		assertNotNull(organizacionRegistro.getIdOrganizacion());
		assertEquals(organizacionRegistro, (Organizacion) organizacionService.buscarOrganizacionPorId(organizacionRegistro.getIdOrganizacion()));
		
		// T2.Registar usuario
		usuarioService.registrarUsuario(usuarioRegistro);
		assertNotNull(usuarioRegistro.getIdUsuario());
    	assertEquals(usuarioRegistro, (Usuario) usuarioService.buscarUsuarioPorId(usuarioRegistro.getIdUsuario()));

	
		// T2. Rexistrar usuario duplicado
    	
    	Set<String> telefonosUsuarioDuplicado = new HashSet<String>();
    				telefonosUsuarioDuplicado.add("981535300");
    				telefonosUsuarioDuplicado.add("981535300");
    			
    	Timestamp fechaAltaDuplicada = new Timestamp(Calendar.getInstance().getTimeInMillis());
    			
		Usuario usuarioRegistroDuplicado = new Usuario("UsuarioRegistroDuplicado","apellido1","apellido1","usuarioDuplicado@gmail.com","password1",telefonosUsuarioDuplicado,false,fechaAltaDuplicada,organizacionRegistro);
    			
    	
		Boolean duplicado=false;
		try {usuarioService.registrarUsuario(usuarioRegistroDuplicado);} 
			catch (DataIntegrityViolationException e) {duplicado=true;}
		assertFalse(duplicado);

		duplicado=false;
		try {usuarioService.registrarUsuario(usuarioRegistroDuplicado);} 
			catch (DataIntegrityViolationException e) {duplicado=true;}
		assertTrue(duplicado);
		
		//T3.ModificarUsuario
		
		usuarioRegistroDuplicado.setEmail("miNuevoMail@gmail.com");
		usuarioService.actualizarUsuario(usuarioRegistroDuplicado);
		assertEquals(usuarioRegistroDuplicado.getEmail(),"miNuevoMail@gmail.com");
		
		// T4. Borrar Usuario
		usuarioService.eliminarUsuario(usuarioRegistro.getIdUsuario());
		assertNull(usuarioService.buscarUsuarioPorId(usuarioRegistro.getIdUsuario()));
		
		
		
		// T5. Borrar organizacion
		
		// intentamos borrar una organizacion con usuarios
		 duplicado=false;
		 try {organizacionService.borrarOrganizacion(organizacionRegistro.getIdOrganizacion());} 
		      catch (DataIntegrityViolationException e) {duplicado=true;}
			  assertTrue(duplicado);
			  
		//Borramos el usuario
		usuarioService.eliminarUsuario(usuarioRegistroDuplicado.getIdUsuario());
		assertNull(usuarioService.buscarUsuarioPorId(usuarioRegistroDuplicado.getIdUsuario()));
		
		//Borramos la organizacion
		duplicado=false;
		try {organizacionService.borrarOrganizacion(organizacionRegistro.getIdOrganizacion());} 
			 catch (DataIntegrityViolationException e) {duplicado=true;}
				assertFalse(duplicado);

//		// T3. Comprobamos que a autenticación funciona
//		
//		Estudante meuEstudanteAutenticado = (Estudante) usuarioService.autenticarUsuario(meuEstudante.getNomeUsuario(), meuEstudante.getPassword());
//		assertNotNull(meuEstudanteAutenticado);
//		assertEquals(meuEstudante, meuEstudanteAutenticado);
//		
//		Docente meuDocenteAutenticado = (Docente) usuarioService.autenticarUsuario(meuDocente.getNomeUsuario(), meuDocente.getPassword());
//		assertNotNull(meuDocenteAutenticado);
//		assertEquals(meuDocente, meuDocenteAutenticado);
//
//		// T4. Probar autenticación erronea
//			
//		Usuario meuUsuarioNonAutenticado = usuarioService.autenticarUsuario(meuEstudante.getNomeUsuario(), "pataca");
//		assertNull(meuUsuarioNonAutenticado);
//		
//		meuUsuarioNonAutenticado = usuarioService.autenticarUsuario(meuDocente.getNomeUsuario(), "pataca");
//		assertNull(meuUsuarioNonAutenticado);
//		
//			
//		// T5 Probar cambio datos docente e estudante
//		
//		meuEstudanteAutenticado.setPassword("passwordnuevecito");
//		meuEstudanteAutenticado.setSaldo(new Float(12345)); 
//		usuarioService.actualizarUsuario(meuEstudanteAutenticado);
//		assertEquals (meuEstudanteAutenticado.getPassword(), (usuarioService.buscarUsuarioPorLogin(meuEstudanteAutenticado.getNomeUsuario())).getPassword());
//		assertEquals (meuEstudanteAutenticado.getSaldo(), ((Estudante)(usuarioService.buscarUsuarioPorLogin(meuEstudanteAutenticado.getNomeUsuario()))).getSaldo());
//
//		meuDocenteAutenticado.setPassword("passwordnuevecito");
//		meuDocenteAutenticado.setNumHoras(new Long(99)); 
//		usuarioService.actualizarUsuario(meuDocenteAutenticado);
//		assertEquals (meuDocenteAutenticado.getPassword(), (usuarioService.buscarUsuarioPorLogin(meuDocenteAutenticado.getNomeUsuario())).getPassword());
//		assertEquals (meuDocenteAutenticado.getNumHoras(), ((Docente)usuarioService.buscarUsuarioPorLogin(meuDocenteAutenticado.getNomeUsuario())).getNumHoras());
//
//		// T6. Probar baixa de docente e estudante (sen cursos vinculados)
//
//		usuarioService.eliminarUsuario(meuEstudanteAutenticado);
//		assertNull(usuarioService.buscarUsuarioPorId(meuEstudanteAutenticado.getIdUsuario()));
//		assertNull(usuarioService.buscarUsuarioPorLogin(meuEstudanteAutenticado.getNomeUsuario()));
//		
//		usuarioService.eliminarUsuario(meuDocenteAutenticado);
//		assertNull(usuarioService.buscarUsuarioPorId(meuDocenteAutenticado.getIdUsuario()));
//		assertNull(usuarioService.buscarUsuarioPorLogin(meuDocenteAutenticado.getNomeUsuario()));
//		
//		// T7 Probar listado de usuarios
//		
//		List<Usuario> miListaU = usuarioService.obterTodosUsuarios();
//		assertEquals(4, miListaU.size());
//		assertEquals(testUtils.estudante1, miListaU.get(0));
//		assertEquals(testUtils.docente1, miListaU.get(1));
//		assertEquals(testUtils.estudante2, miListaU.get(2));
//		assertEquals(testUtils.docente2, miListaU.get(3));
//		miListaU.clear();
//		
//		List<Docente> listaD = usuarioService.obterTodosDocentes();
//		assertEquals(2, listaD.size());
//		assertEquals(testUtils.docente1, listaD.get(0));
//		assertEquals(testUtils.docente2, listaD.get(1));
//		listaD.clear();
//
//		List<Estudante> listaE = usuarioService.obterTodosEstudantes();
//		assertEquals(2, listaE.size());
//		assertEquals(testUtils.estudante1, listaE.get(0));
//		assertEquals(testUtils.estudante2, listaE.get(1));
//		listaE.clear();
//

	}


//	@Test
//	public void testDocenteEstudante() {
//
//		// T1 Engadir novo curso coa súa emisión inicial. Engadir segunda emisión.
//		
//		Curso meuCurso = new Curso("CursoTest", new Timestamp(Calendar.getInstance().getTimeInMillis()), new Float(0), testUtils.docente1, testUtils.mod11, testUtils.catMedia);
//		Emision minhaEmision1 = new Emision("Presentación", new Timestamp(Calendar.getInstance().getTimeInMillis()), meuCurso);	
//		usuarioService.rexistrarNovoCurso(meuCurso);
//		assertNotNull(meuCurso.getIdCurso());
//		assertEquals (meuCurso, usuarioService.buscarCursoPorId(meuCurso.getIdCurso()));
//		assertEquals (meuCurso, usuarioService.buscarCursoPorNome(meuCurso.getNome()));
//		assertEquals (minhaEmision1, usuarioService.obterUltimaEmision(meuCurso));
//		//minhaEmision=usuarioService.obterUltimaEmision(meuCurso);
//	
//		Emision minhaEmision2 = new Emision("Modelos conceptuais", new Timestamp(Calendar.getInstance().getTimeInMillis()), meuCurso);
//		usuarioService.rexistrarNovaEmision(minhaEmision2);
//		assertNotNull(minhaEmision2.getIdEmision());
//		assertEquals (minhaEmision2, usuarioService.obterUltimaEmision(meuCurso));
//		
//		// T2 Listar emisións (a mais recente primeiro)
//		List<Emision> lista = usuarioService.obterEmisionsCurso(meuCurso);
//		assertEquals(2, lista.size());
//		assertEquals (minhaEmision2, lista.get(0));
//		assertEquals (minhaEmision1, lista.get(1));
//		lista.clear();
//	
//		// T3 Actualizar Curso
//		
//		meuCurso.setNome("Curso con titulo novo");
//		meuCurso.setNumVotos(meuCurso.getNumVotos()+1);
//		usuarioService.actualizarCurso(meuCurso);
//		assertEquals(meuCurso.getNome(), usuarioService.buscarCursoPorId(meuCurso.getIdCurso()).getNome());
//		assertEquals(meuCurso.getNumVotos(), usuarioService.buscarCursoPorId(meuCurso.getIdCurso()).getNumVotos());
//		
//		// T4 Dar de baixa unha das emisións do curso
//		
//		meuCurso.getEmisions().remove(minhaEmision2);
//		usuarioService.eliminarEmision(minhaEmision2);
//		lista = usuarioService.obterEmisionsCurso(meuCurso);
//		assertEquals(1, lista.size());
//		assertEquals (minhaEmision1, lista.get(0));
//		lista.clear();
//	
//		// T5 Fallar ao daren de baixa un curso con subscritores
//		
//		testUtils.estudante1.getCursos().add(meuCurso);
//		usuarioService.actualizarUsuario(testUtils.estudante1);
// 		assertEquals (2, usuarioService.obterCursosEstudante(testUtils.estudante1).size());
//	 	
//	    Boolean cursoConSubscritores=false;
//		try { usuarioService.eliminarCurso(meuCurso);}
//		catch (DataIntegrityViolationException e) { cursoConSubscritores=true;}
//		assertTrue (cursoConSubscritores);
//		
//		// T6 Fallar ao daren de baixa un docente con cursos que contan con subscritores
//		
//		Boolean docenteConSubscritores=false;
//		try {usuarioService.eliminarUsuario(testUtils.docente1);}
//		catch (DataIntegrityViolationException e) { docenteConSubscritores=true;}
//		assertTrue (docenteConSubscritores);
//		// Verificar que non se eliminou NINGÚN dos cursos (non so o que tiña subscritores)
//		assertEquals(3,usuarioService.obterCursosDocente(testUtils.docente1).size());
//		
//		
//		// T7 Probar baixa de estudante con subscricions activas
//		
//		Estudante meuEstudante = new Estudante ("estudanteTest", "test13", "Nokito", "Nada", "Nada", "Nokito Nada", "Matriculado", new Float(50));
//		usuarioService.rexistrarNovoUsuario(meuEstudante);		
//		meuEstudante.getCursos().add(meuCurso);
//		usuarioService.actualizarUsuario(meuEstudante);
//		
//		assertEquals (2, usuarioService.obterEstudantesCurso(meuCurso).size());
//		usuarioService.eliminarUsuario(meuEstudante);
//		assertEquals (1, usuarioService.obterEstudantesCurso(meuCurso).size());
//
//		// T8 Cancelar todos os subscritores dun curso
//		
//		assertEquals (2, usuarioService.obterCursosEstudante(testUtils.estudante1).size());
//		assertEquals (1, usuarioService.obterEstudantesCurso(meuCurso).size());
//		usuarioService.cancelarSubscricionsCurso(meuCurso);
//		assertEquals (1, usuarioService.obterCursosEstudante(testUtils.estudante1).size());
//		assertEquals (0, usuarioService.obterEstudantesCurso(meuCurso).size());
//
//		// T9 Eliminar cursos sen subscritores (e as súas emisións)
//		
//		usuarioService.eliminarCurso(meuCurso);
//		assertNull(usuarioService.buscarCursoPorId(meuCurso.getIdCurso()));
//		assertNull(usuarioService.buscarCursoPorNome(meuCurso.getNome()));
//		assertNull(usuarioService.buscarEmisionPorId(minhaEmision1.getIdEmision()));
		
/**/
//	}
}

