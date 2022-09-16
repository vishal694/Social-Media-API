package com.example.demo.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@ApiModel
public class File {
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	private String fileName;

	private String fileType;

	@Lob
	private byte[] data;
	
	@ManyToOne
	private Post image;
	
	public File(String fileName, String fileType, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
	}
}
