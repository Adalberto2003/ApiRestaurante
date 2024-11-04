package com.mx.ApiRestaurante.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.ApiRestaurante.Service.MesasImp;
import com.mx.ApiRestaurante.model.Mesas;



@RestController
@RequestMapping(path = "MesasWs")
@CrossOrigin
public class MesasWs {

	@Autowired
	MesasImp mesaImp;
	
	//http://localhost:9000/MesasWs/listar
	@GetMapping(path = "listar")
	public List<Mesas> listar(){
		return mesaImp.listar();
	}
	// http://localhost:9000/MesasWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesas mesa) {
	    String response = mesaImp.guardar(mesa);

	    if (response.equals("NumMesaExiste")) {
	        return new ResponseEntity<>("No se guardó, ese número de mesa ya existe", HttpStatus.OK);
	    } else if (response.equals("IdMeseroNoExiste")) {
	        return new ResponseEntity<>("No se guardó, el ID del mesero no existe", HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(mesa, HttpStatus.CREATED);
	    }
	}
	// http://localhost:9000/MesasWs/buscar
		@PostMapping(path = "buscar")
		public ResponseEntity<?> buscar(@RequestBody Long id) {
			Mesas modBuscar = mesaImp.buscar(id);

			if (modBuscar == null) {
				return new ResponseEntity<>("No se encontró una mesa con ese ID", HttpStatus.NOT_FOUND);
			} else {
				return new ResponseEntity<>(modBuscar, HttpStatus.OK);
			}
		}
		
		// http://localhost:9000/MesasWs/editar
		@PostMapping(path = "editar")
	    public ResponseEntity<?> editar(@RequestBody Mesas mesa) {
	        
	        Mesas modExistente = mesaImp.buscar(mesa.getId());

	        if (modExistente == null) {
	            return new ResponseEntity<>("No se encontró una mesa con ese ID", HttpStatus.NOT_FOUND);
	        } else {
	            
	            mesaImp.editar(mesa); 
	            return new ResponseEntity<>("Mesa editada con éxito", HttpStatus.OK);
	        }
	    }
		// http://localhost:9000/MesasWs/eliminar
		@PostMapping(path = "eliminar")
		public ResponseEntity<?> eliminar(@RequestBody Mesas mesa){
			boolean response =mesaImp.eliminar(mesa.getId());
			if (response == false)
				return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
			else
				return new ResponseEntity<>("se elimino", HttpStatus.OK);
		}
}

