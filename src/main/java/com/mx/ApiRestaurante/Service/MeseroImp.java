package com.mx.ApiRestaurante.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.ApiRestaurante.Dao.MeseroDao;
import com.mx.ApiRestaurante.model.Mesero;

@Service
public class MeseroImp {

	@Autowired
	MeseroDao meseroDao;
	
	@Transactional(readOnly = true)
	public List<Mesero> listar(){
		return meseroDao.findAll();
	}
	
	@Transactional
	public String guardar(Mesero mesero) {
		 String respuesta = "";
		    boolean bandera = false;

		    for (Mesero m : meseroDao.findAll()) {
		        //  si el ID ya existe
		        if (m.getId().equals(mesero.getId())) {
		            bandera = true;
		            respuesta = "IdExiste";
		            break;
		        }
		        // Verificar si el nombre completo ya existe
		        else if (m.getNombre().equals(mesero.getNombre()) &&
		                 m.getApp().equals(mesero.getApp()) &&
		                 m.getApm().equals(mesero.getApm())) {
		            bandera = true;
		            respuesta = "NombreCompletoExiste";
		            break;
		        }
		    }

		    
		    if (!bandera) {
		        meseroDao.save(mesero);
		        respuesta = "Guardado";
		    }

		    return respuesta;
	}
	@Transactional
	public Mesero buscar(Long id) {
		return meseroDao.findById(id).orElse(null);
	}
	
	@Transactional
	public boolean editar (Mesero mesero) {
		boolean bandera = false;
		for(Mesero m: meseroDao.findAll()) {
			if(m.getId().equals(mesero.getId())) {
				meseroDao.save(mesero);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	@Transactional
	public boolean eliminar(Long id) {
		boolean bandera= false;
		for(Mesero m: meseroDao.findAll()) {
			if(m.getId().equals(id)) {
				meseroDao.deleteById(id);
				bandera = true;
				break;
			}
		}
		return bandera;
	}
	
}
