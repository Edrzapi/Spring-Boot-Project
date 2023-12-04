package com.qa.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.demo.dto.OwnerDTO;
import com.qa.demo.service.OwnerService;

@RestController
@RequestMapping("/o")
public class OwnerController {

	private OwnerService service;

	@Autowired
	public OwnerController(OwnerService service) {
		super();
		this.service = service;
	}

	@PostMapping("/post")
	public ResponseEntity<OwnerDTO> createOwner(@RequestBody OwnerDTO Owner) {
		return new ResponseEntity<>(this.service.createOwner(Owner), HttpStatus.CREATED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<OwnerDTO> deleteOwner(@PathVariable Long id) {
		return this.service.deleteOwner(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/read/{id}")
	public ResponseEntity<OwnerDTO> getOwner(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findOwnerByID(id));
	}

	@GetMapping("/read")
	public ResponseEntity<List<OwnerDTO>> getAllOwners() {
		return ResponseEntity.ok(this.service.readOwners());
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<OwnerDTO> updateOwner(@PathVariable Long id, @RequestBody OwnerDTO Owner) {
		return new ResponseEntity<>(this.service.updateOwner(Owner, id), HttpStatus.ACCEPTED);
	}

}
