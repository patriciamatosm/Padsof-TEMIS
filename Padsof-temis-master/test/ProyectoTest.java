
import org.junit.Test;
import model.*;
import model.Proyecto.Estado;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

@SuppressWarnings("unused")
public class ProyectoTest {

	@Test
	public void votarTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoInfraestructura p = new ProyectoInfraestructura("titulo", "descripcion",
				200, u, "abc", "def", "ghi");
		
		p.votar(u);
		assertTrue(u.getListaProyecto().contains(p));
	}

	@Test
	public void votarUsuariosTest() {
		boolean flag = true;
		
		Usuario u1 = new Usuario("Patricia", "00000000B", "12345");
		Usuario u2 = new Usuario("Daniel", "11111111A", "67890");
		Usuario u3 = new Usuario("Silvia", "22222222C", "45678");
		ProyectoSocial p = new ProyectoSocial("titulo", "descripcion", 200, u1,
				"jkl", true);
	
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		
		p.votarUsuarios(usuarios);
		for(Usuario u: usuarios) {
			if(!u.getListaProyecto().contains(p)) {
				flag = false;
			}
		}
		assertEquals(true, flag);
	} 
	
	@Test
	public void caducadoTest() {
		LocalDate fechaUlt = LocalDate.now();
		
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoInfraestructura p = new ProyectoInfraestructura("titulo", "descripcion",
				200, u, "abc", "def", "ghi");
		assertEquals(p.getEstado(), Estado.EN_ESPERA);
		
		p.setFechaUltimoVoto(fechaUlt.minusDays(40));
		p.caducado();
		assertEquals(p.getEstado(), Estado.CADUCADO);
	}
	
	@Test
	public void aceptarProyectoTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoSocial p = new ProyectoSocial("titulo", "descripcion", 200, u,
				"jkl", true);
		p.aceptarProyecto();
		assertEquals(p.getEstado(), Estado.ACTIVO);
	}
	
	@Test
	public void aceptarFinanciacionTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoInfraestructura p = new ProyectoInfraestructura("titulo", "descripcion",
				200, u, "abc", "def", "ghi");
		
		assertEquals(p.getEstado(), Estado.EN_ESPERA);
		p.setEstado(Estado.FINANCIADO);
		p.aceptarFinanciacion();
		assertEquals(p.getEstado(), Estado.FINANCIADO);
	}
	
	@Test
	public void esperarFinancTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoSocial p = new ProyectoSocial("titulo", "descripcion", 200, u,
				"jkl", true);
		p.setEstado(Estado.CADUCADO);
		assertFalse(p.esperarFinanc());
		
		p.setEstado(Estado.ACTIVO);
		p.setMinVotos(30);
		p.setNumVotos(60);
		assertTrue(p.esperarFinanc());
	}
	
}
