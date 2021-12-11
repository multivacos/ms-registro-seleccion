package com.minera.seleccion.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.minera.seleccion.entity.Formulario;
import com.minera.seleccion.service.FormularioService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/formularios")
public class FormularioController {
	@Autowired
	private FormularioService formularioService;
	
	@GetMapping
	@RequestMapping(value = "consultarFormularios", method = RequestMethod.GET)
	public ResponseEntity<?> consultarFormularios() {
		List<Formulario> formulariosConsultados = this.formularioService.consultarFormulario();
		return ResponseEntity.ok(formulariosConsultados);
	}
	
	@PostMapping
	@RequestMapping(value = "guardarFormulario", method = RequestMethod.POST)
	public ResponseEntity<?> guardarFormulario(@RequestBody Formulario formulario) {
		Formulario formularioGuardado = this.formularioService.guardarFormulario(formulario);
		return ResponseEntity.status(HttpStatus.CREATED).body(formularioGuardado);
	}
	
	@PutMapping
	@RequestMapping(value = "actualizarFormulario", method = RequestMethod.PUT)
	public ResponseEntity<?> actualizarFormulario(@RequestBody Formulario formulario) {
		Formulario formularioActualizado = this.formularioService.actualizarFormulario(formulario);
		return ResponseEntity.status(HttpStatus.CREATED).body(formularioActualizado);
	}
	
	@DeleteMapping
	@RequestMapping(value = "eliminarFormulario/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> eliminarFormulario(@PathVariable Long id) {
		this.formularioService.eliminarFormulario(id);
		return ResponseEntity.ok().build();
	}
}
