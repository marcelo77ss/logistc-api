package com.logistc.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logistc.domain.model.Entrega;
import com.logistc.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {
	
	private BuscaEntregaService buscaEntregaService;
	
	private EntregaRepository entregaRepository;
	
	@Transactional
	public void finalizar(Long idEntrega) {
		Entrega entrega = buscaEntregaService.buscar(idEntrega);
		
		entrega.finalizar();
		
		entregaRepository.save(entrega);
	}

}
