package com.minera.seleccion.service;

import java.util.List;

import com.minera.seleccion.entity.Formulario;

public interface FormularioService {
	List<Formulario> consultarFormulario();
	
	Formulario guardarFormulario(Formulario formulario);
	
	Formulario actualizarFormulario(Formulario formulario);
	
	void eliminarFormulario(Long id);
}
