package com.qa.demo.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class OwnerDTO {

	private String name;

	private List<TarantulaDTO> tarantulas = new ArrayList<>();
}
