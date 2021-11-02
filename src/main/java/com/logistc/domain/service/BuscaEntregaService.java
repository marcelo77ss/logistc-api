package com.logistc.domain.service;

import org.springframework.stereotype.Service;

import com.logistc.domain.model.Entrega;
import com.logistc.domain.repository.EntregaRepository;
import com.logistc.exceptionhandler.EntidadeNaoEncontradaException;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long idEntrega) {
		return entregaRepository.findById(idEntrega)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Enterga não encontrada."));


	}
}
