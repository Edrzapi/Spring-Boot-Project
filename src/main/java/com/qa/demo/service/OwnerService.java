package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.domain.Owner;
import com.qa.demo.dto.OwnerDTO;
import com.qa.demo.exceptions.OwnerNotFoundException;
import com.qa.demo.repo.OwnerRepo;

@Service
public class OwnerService {

	private OwnerRepo repo;

	private ModelMapper mapper;

	@Autowired
	public OwnerService(OwnerRepo repo, ModelMapper mapper) {
		this.repo = repo;
		this.mapper = mapper;
	}

	private OwnerDTO mapToDTO(Owner Owner) {
		return this.mapper.map(Owner, OwnerDTO.class);
	}

	private Owner mapFromDTO(OwnerDTO Owner) {
		return this.mapper.map(Owner, Owner.class);
	}

	public OwnerDTO createOwner(OwnerDTO Owner) {
		Owner toSave = this.mapFromDTO(Owner);
		Owner saved = this.repo.save(toSave);
		return this.mapToDTO(saved);
	}

	public boolean deleteOwner(Long id) {
		if (!this.repo.existsById(id)) {
			throw new OwnerNotFoundException();
		}
		this.repo.deleteById(id);
		return !this.repo.existsById(id);
	}

	public OwnerDTO findOwnerByID(Long id) {
		return this.mapToDTO(this.repo.findById(id).orElseThrow(OwnerNotFoundException::new));
	}

	public List<OwnerDTO> readOwners() {
		return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public OwnerDTO updateOwner(OwnerDTO Owner, Long id) {
		Owner toUpdate = this.repo.findById(id).orElseThrow(OwnerNotFoundException::new);
		toUpdate.setName(Owner.getName());
		return this.mapToDTO(this.repo.save(toUpdate));
	}

}