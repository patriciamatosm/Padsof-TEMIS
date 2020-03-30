
import org.junit.Test;
import model.*;
import model.Proyecto.Estado;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class ProyectoTest {

	@Test
	public void votarTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoInfraestructura p = new ProyectoInfraestructura("titulo", "descripcion", 200, u, "abc", "def", "ghi");
		
		p.votar(u);
		assertTrue(u.getListaProyecto().contains(p));
	}

	@Test
	public void votarUsuariosTest() {
		Integer flag = 0;
		
		Usuario u1 = new Usuario("Patricia", "00000000B", "12345");
		Usuario u2 = new Usuario("Daniel", "11111111A", "67890");
		Usuario u3 = new Usuario("Silvia", "22222222C", "45678");
		ProyectoSocial p = new ProyectoSocial("titulo", "descripcion", 200, u1, "jkl", true);
	
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(u1);
		usuarios.add(u2);
		usuarios.add(u3);
		
		p.votarUsuarios(usuarios);
		for(Usuario u: usuarios) {
			if(!u.getListaProyecto().contains(p)) {
				flag = 1;
			}
		}
		assertTrue(flag == 0);
		assertFalse(flag == 1);
	} 
	
	@Test
	public void caducadoTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoInfraestructura p = new ProyectoInfraestructura("titulo", "descripcion", 200, u, "abc", "def", "ghi");
		
		p.caducado();
		assertTrue(p.getEstado() == Estado.CADUCADO);
	}
	
	@Test
	public void aceptarProyectoTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoSocial p = new ProyectoSocial("titulo", "descripcion", 200, u, "jkl", true);
		p.aceptarProyecto();
		assertTrue(p.getEstado() == Estado.ACTIVO);
	}
	
	@Test
	public void aceptarFinanciacionTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoInfraestructura p = new ProyectoInfraestructura("titulo", "descripcion", 200, u, "abc", "def", "ghi");
		p.aceptarFinanciacion();
		assertTrue(p.getEstado() == Estado.FINANCIADO);
	}
	
	@Test
	public void esperarFinancTest() {
		Usuario u = new Usuario("Patricia", "00000000B", "12345");
		ProyectoSocial p = new ProyectoSocial("titulo", "descripcion", 200, u, "jkl", true);
	
		assertFalse(p.esperarFinanc());
		p.setNumVotos(60);
		assertTrue(p.esperarFinanc());
	}
	
}
