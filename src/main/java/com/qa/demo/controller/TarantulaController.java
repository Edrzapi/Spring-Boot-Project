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

import com.qa.demo.dto.TarantulaDTO;
import com.qa.demo.service.TarantulaService;

@RequestMapping
@RestController("/t")
public class TarantulaController {
//
//	@Autowired
//	private TarantulaService service;
//
////	@GetMapping : 	Fetching data FROM a database "@RequestMapping(method = RequestMethod.GET)."
////	@RequestMapping: Class level request config
////	@PostMapping: 	Send data TO the database
////	@DeleteMapping: Remove data from the database
////	@PutMapping: 	Replace an existing RECORD in a database
////	@PatchMapping: 	Partially update a RECORD in a database
////	@RequestBody : 	Get information from the USER
////	@PathVariable:  Template holder for a value that we get from the USER
//
//	// First demo piece ->
//
//	@GetMapping
//	public String testRequests() {
//		return "Gooooood morning everyboooody!";
//	}
//
//	// Second demo piece ->
//
//	@GetMapping("/tara")
//	public Tarantula testObjects() {
//		return new Tarantula(15, "Terestrial", true, "Moody", "Hot", "Grammostola", "Rose");
//	}
//
//	// Third demo piece ->
//	@GetMapping("/taras")
//	public List<Tarantula> manualGet() {
//		List<Tarantula> list = Arrays.asList(
//				new Tarantula(15, "Terestrial", true, "Moody", "Hot", "Grammostola", "Rose"),
//				new Tarantula(20, "Terestrial", true, "Moody", "Hot", "Grammostola", "Rose"));
//		return list;
//
//	}
//
//		
//	// service version
//
//	@GetMapping("/serviceT")
//	public ResponseEntity<List<Tarantula>> read() {
//		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//		return new ResponseEntity<List<Tarantula>>(this.service.read(), HttpStatus.OK);
//
//	}
//
//	// repo version
//
//	@GetMapping("/getAll")
//	public ResponseEntity<List<Tarantula>> getAll() {
//		return new ResponseEntity<List<Tarantula>>(this.service.getAll(), HttpStatus.OK);
//	}
//
//	@GetMapping("/get/{id}")	
//	public ResponseEntity<Tarantula> get(@PathVariable Long id) {
//		return new ResponseEntity<Tarantula>(this.service.getById(id), HttpStatus.OK);
//	}
//
//	@PostMapping("/create")
//	public ResponseEntity<Tarantula> create(@RequestBody Tarantula t) {
//		return new ResponseEntity<Tarantula>(this.service.create(t), HttpStatus.CREATED);
//	}
//
//	@PutMapping("/update/{id}")
//	public ResponseEntity<Tarantula> update(@PathVariable Long id, @RequestBody Tarantula t) {
//		return new ResponseEntity<Tarantula>(this.service.update(id, t), HttpStatus.ACCEPTED);
//	}
//
//	@DeleteMapping("/delete/{id}")
//	public ResponseEntity<Boolean> delete(@PathVariable Long id) {
//		return new ResponseEntity<Boolean>(this.service.delete(id), HttpStatus.NO_CONTENT);
//	}
//
//	@GetMapping("/getName/{name}")
//	public ResponseEntity<List<Tarantula>> getByName(@PathVariable String name) {
//		return new ResponseEntity<List<Tarantula>>(this.service.getByName(name), HttpStatus.OK);
//	}

	private TarantulaService service;

	@Autowired
	public TarantulaController(TarantulaService service) {
		super();
		this.service = service;
	}

	@PostMapping("/createTarantula")
	public ResponseEntity<TarantulaDTO> createTarantula(@RequestBody TarantulaDTO Tarantula) {
		return new ResponseEntity<>(this.service.createTarantula(Tarantula), HttpStatus.CREATED);
	}

	@DeleteMapping("/deleteTarantula/{id}")
	public ResponseEntity<TarantulaDTO> deleteTarantula(@PathVariable Long id) {
		return this.service.deleteTarantula(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<TarantulaDTO> getTarantula(@PathVariable Long id) {
		return ResponseEntity.ok(this.service.findTarantulaByID(id));
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<TarantulaDTO>> getAllTarantulas() {
		return ResponseEntity.ok(this.service.readTarantulas());
	}

	@PutMapping("/updateTarantula/{id}")
	public ResponseEntity<TarantulaDTO> updateTarantula(@PathVariable Long id, @RequestBody TarantulaDTO Tarantula) {
		return new ResponseEntity<>(this.service.updateTarantula(Tarantula, id), HttpStatus.ACCEPTED);
	}

}
