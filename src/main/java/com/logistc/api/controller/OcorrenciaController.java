package com.logistc.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.logistc.api.assembler.OcorrenciaAssembler;
import com.logistc.domain.model.Entrega;
import com.logistc.domain.model.Ocorrencia;
import com.logistc.domain.service.BuscaEntregaService;
import com.logistc.domain.service.RegistroOcorrenciaService;
import com.logistc.model.OcorrenciaModel;
import com.logistc.model.input.OcorrenciaInput;

public class OcorrenciaController {
	
	private RegistroOcorrenciaService registroOcorrenciaService;
	
	private OcorrenciaAssembler ocorrenciaAssembler;
	
	private BuscaEntregaService buscaEntregaService;
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId,
			@RequestBody @Valid OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService
				.registrar(entregaId, ocorrenciaInput.getDescricao());
			
		return ocorrenciaAssembler.toModel(ocorrenciaRegistrada);
		
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}

}
