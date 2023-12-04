package com.qa.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qa.demo.domain.Tarantula;
import com.qa.demo.dto.TarantulaDTO;
import com.qa.demo.exceptions.TarantulaNotFoundException;
import com.qa.demo.repo.TarantulaRepo;

@Service
public class TarantulaService {
//
//	// Dependency
//	private TarantulaRepo repo;
//
//	public TarantulaService(TarantulaRepo repo) {
//		this.repo = repo;
//	}
//
//	// Manual version
//
//	// Read
//	public List<Tarantula> read() {
//		List<Tarantula> list = Arrays.asList(
//				new Tarantula(15, "Terestrial", true, "Moody", "Hot", "Grammostola", "Rose"),
//				new Tarantula(20, "Terestrial", true, "Moody", "Hot", "Grammostola", "Rose"));
//		return list;
//	}
//
//	// Notice how much easier it gets when we're able to use repo methods?
//
//	// Read all
//	public List<Tarantula> getAll() {
//		return this.repo.findAll();
//	}
//
//	// Update
//	public Tarantula update(Long id, Tarantula t) {
//		Tarantula existing = this.repo.getById(id);
//		existing.setCommonName(t.getCommonName()); // Set field with new input
//		existing.setDimorphism(t.isDimorphism());
//		existing.setGenus(t.getGenus());
//		existing.setClimate(t.getClimate());
//		existing.setLegSpan(t.getLegSpan());
//		existing.setTemperament(t.getTemperament());
//		existing.setHabitat(t.getHabitat());
//		Tarantula updated = this.repo.save(existing); // Overwrite the obj
//		return updated;
//	}
//
//	// Create
//	public Tarantula create(Tarantula t) {
//		return this.repo.saveAndFlush(t);
//	}
//
//	// Delete
//	public Boolean delete(Long id) {
//		this.repo.deleteById(id);
//		return this.repo.existsById(id);
//	}
//
//	// Get id
//	public Tarantula getById(Long id) {
//		final Tarantula T_FOUND = this.repo.findById(id).orElseThrow(() -> {
//			return new ResponseStatusException(HttpStatus.NOT_FOUND,
//					String.format("Tarantula with: [id=%d], was not found!", id));
//		});
//		return T_FOUND;
//	}
//	// Custom
//	public List<Tarantula> getByName(String name) {
//		return this.repo.findByName(name);
//
//	}


		private TarantulaRepo repo;

		private ModelMapper mapper;

		@Autowired
		public TarantulaService(TarantulaRepo repo, ModelMapper mapper) {
			this.repo = repo;
			this.mapper = mapper;
		}

		private TarantulaDTO mapToDTO(Tarantula Tarantula) {
			return this.mapper.map(Tarantula, TarantulaDTO.class);
		}

		private Tarantula mapFromDTO(TarantulaDTO Tarantula) {
			return this.mapper.map(Tarantula, Tarantula.class);
		}

		public TarantulaDTO createTarantula(TarantulaDTO Tarantula) {
			Tarantula toSave = this.mapFromDTO(Tarantula);
			Tarantula saved = this.repo.save(toSave);
			return this.mapToDTO(saved);
		}

		public boolean deleteTarantula(Long id) {
			if (!this.repo.existsById(id)) {
				throw new TarantulaNotFoundException();
			}
			this.repo.deleteById(id);
			return !this.repo.existsById(id);
		}

		public TarantulaDTO findTarantulaByID(Long id) {
			return this.mapToDTO(this.repo.findById(id).orElseThrow(TarantulaNotFoundException::new));
		}

		public List<TarantulaDTO> readTarantulas() {
			return this.repo.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
		}

		public TarantulaDTO updateTarantula(TarantulaDTO Tarantula, Long id) {
			Tarantula toUpdate = this.repo.findById(id).orElseThrow(TarantulaNotFoundException::new);
			toUpdate.setCommonName(Tarantula.getCommonName());
			return this.mapToDTO(this.repo.save(toUpdate));
		}

}