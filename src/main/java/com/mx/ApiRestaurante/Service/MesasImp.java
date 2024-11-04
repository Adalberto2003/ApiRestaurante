package com.mx.ApiRestaurante.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestaurante.Dao.MesasDao;
import com.mx.ApiRestaurante.Dao.MeseroDao;
import com.mx.ApiRestaurante.model.Mesas;




@Service
public class MesasImp {

	@Autowired
	MesasDao mesaDao;
	@Autowired
	MeseroDao meseroDao;
	
	@Transactional(readOnly = true)
	public List<Mesas> listar(){
		return mesaDao.findAll();
	}
	
	@Transactional
	public String guardar(Mesas mesa) {
		String respuesta = "";
	    boolean idMeseroExiste = false;
	    boolean numMesaExiste = false;
	    
	    for (Mesas m : mesaDao.findAll()) {
	        // Verificar si el ID del mesero existe
	        if (m.getMesero().getId().equals(mesa.getMesero().getId())) {
	            idMeseroExiste = true;
	        }

	        // Verificar si el número de mesa ya existe
	        if (m.getMunMesa().equals(mesa.getMunMesa())) {
	            numMesaExiste = true;
	            respuesta = "NumMesaExiste"; 
	        }
	    }
	    
	    if (idMeseroExiste && !numMesaExiste) {
	        mesaDao.save(mesa);
	        respuesta = "Guardado";
	    } else if (!idMeseroExiste) {
	        respuesta = "IdMeseroNoExiste";
	    }

	    return respuesta;
	}
	@Transactional(readOnly = true)
	public Mesas buscar(Long id) {
		
		return mesaDao.findById(id).orElse(null);
	}
	
	@Transactional
	public String editar(Mesas mesa) {
		 String respuesta = "";
		    boolean bandera = false;
		    for (Mesas m : mesaDao.findAll()) {
		        if (m.getId().equals(mesa.getId())) {
		        	bandera = true; // La mesa existe

		           
		            if (!m.getMunMesa().equals(mesa.getMunMesa()) && 
		                mesaDao.findByMunMesa(mesa.getMunMesa()) != null) {
		                respuesta = "numMesaExiste"; 
		            }
		            break; 
		        }
		    }
		    if (!bandera) {
		        respuesta = "idMesaNoExiste"; 
		    }
		    if (respuesta.isEmpty()) {
		        mesaDao.save(mesa);
		        return "editada"; 
		    }

		    return respuesta; 
	}
	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera= false;
		for(Mesas m: mesaDao.findAll()) {
			if (m.getId().equals(id)) {
				mesaDao.deleteById(id);
				
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
}
