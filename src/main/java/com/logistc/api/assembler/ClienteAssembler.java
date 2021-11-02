package com.logistc.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.logistc.domain.model.Cliente;
import com.logistc.model.ClienteModel;
import com.logistc.model.input.ClienteInput;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ClienteAssembler {
	
	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente clientes) {
		return modelMapper.map(clientes, ClienteModel.class);
	}
	
	public List<ClienteModel> toCollectionModel(List<Cliente> clientes) {
		return clientes.stream()
				.map(cliente -> this.toModel(cliente))
				.collect(Collectors.toList());
	}
	
	public Cliente toEntity(ClienteInput clienteInput) {
		return modelMapper.map(clienteInput, Cliente.class);
	}
}
