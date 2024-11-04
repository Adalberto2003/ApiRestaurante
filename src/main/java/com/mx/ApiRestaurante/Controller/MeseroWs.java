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

import com.mx.ApiRestaurante.Service.MeseroImp;
import com.mx.ApiRestaurante.model.Mesero;


@RestController
@RequestMapping(path = "MeseroWs")
@CrossOrigin
public class MeseroWs {

	@Autowired
	MeseroImp meseroImp;
	
	//Url : http://localhost:9000/MeseroWs/listar
	@GetMapping(path = "listar")
	public List<Mesero> listar(){
		return meseroImp.listar();
	}
	
	//Url : http://localhost:9000/MeseroWs/guardar
	@PostMapping(path = "guardar")
	public ResponseEntity<?> guardar(@RequestBody Mesero mesero){
		String response = meseroImp.guardar(mesero);
		
		if(response.equals("IdExiste"))
			return new ResponseEntity<>("Ese id ya esta en existencia",HttpStatus.OK);
		else if(response.equals("NombreCompletoExiste"))
			return new ResponseEntity<>("Ese nombre ya esta en existencia",HttpStatus.OK);
		else 
			return new ResponseEntity<>(mesero,HttpStatus.OK);


	}
	//Url : http://localhost:9000/MeseroWs/buscar
	@PostMapping(path = "buscar")
	public Mesero buscar(@RequestBody Mesero mesero) {
		return meseroImp.buscar(mesero.getId());
	}
	
	//Url : http://localhost:9000/MeseroWs/editar
		@PostMapping(path = "editar")
		public ResponseEntity<?> editar(@RequestBody Mesero mesero) {
			boolean response = meseroImp.editar(mesero);
			
			if(response == false)
				return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
			else
				return new ResponseEntity<>(mesero, HttpStatus.CREATED);
		}

		//Url : http://localhost:9000/MeseroWs/eliminar
		@PostMapping(path = "eliminar")
		public ResponseEntity<?> eliminar(@RequestBody Mesero mesero){
			boolean response = meseroImp.eliminar(mesero.getId());
			
			if(response==false)
				return new ResponseEntity<>("Ese registro no existe", HttpStatus.OK);
			else
				return new ResponseEntity<>("se elimino", HttpStatus.OK);
		
		}
		
}
