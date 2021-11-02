package com.logistc.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.logistc.api.assembler.ClienteAssembler;
import com.logistc.domain.model.Cliente;
import com.logistc.domain.repository.ClienteRepository;
import com.logistc.domain.service.CatalogoClienteService;
import com.logistc.model.ClienteModel;
import com.logistc.model.input.ClienteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private ClienteAssembler clienteAssembler;
	
	private ClienteRepository clienteRepository;
	
	private CatalogoClienteService catalogoClienteService;
	
	@GetMapping
	public List<ClienteModel> listar() {
		return clienteAssembler.toCollectionModel(clienteRepository.findAll());	
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel>  buscar (@PathVariable Long clienteId) {
		return clienteRepository.findById(clienteId)			
				.map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public ClienteModel adicionar(@Valid @RequestBody ClienteInput clienteInput) {
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente = catalogoClienteService.salvar(cliente);
		return clienteAssembler.toModel(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable Long clienteId,
			@RequestBody @Valid ClienteInput clienteInput){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		Cliente cliente = clienteAssembler.toEntity(clienteInput);
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		ClienteModel clienteModel = clienteAssembler.toModel(cliente);
		return ResponseEntity.ok(clienteModel);
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> excluir(@PathVariable Long clienteId) {
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		catalogoClienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
}
