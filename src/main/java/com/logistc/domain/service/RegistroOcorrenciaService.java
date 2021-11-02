package com.logistc.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistc.domain.model.Entrega;
import com.logistc.domain.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long idEntrega, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(idEntrega);
		
		return entrega.adicionarOcorrencia(descricao);
	}
}
