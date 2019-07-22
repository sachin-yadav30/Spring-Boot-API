package org.gn.udip.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "category")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CategoryModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@NotNull
	@Column(name = "id")	
	private Long categoryId;
	
	@Column(name = "name")	
	private String categoryName;
	
	@Column(name = "mapping_json")
	private String mappingJson ;	
	
	@Column(name = "input_blobtype")	
	private String inputBlobType;
	
	@Column(name = "output_blobtype")	
	private String outputBlobType;
	
	
	public String getInputBlobType() {
		return inputBlobType;
	}

	public void setInputBlobType(String inputBlobType) {
		this.inputBlobType = inputBlobType;
	}

	public String getOutputBlobType() {
		return outputBlobType;
	}

	public void setOutputBlobType(String outputBlobType) {
		this.outputBlobType = outputBlobType;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getMappingJson() {
		return mappingJson;
	}

	public void setMappingJson(String mappingJson) {
		this.mappingJson = mappingJson;
	}
	
}
