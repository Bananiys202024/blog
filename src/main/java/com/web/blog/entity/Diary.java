package com.web.blog.entity;

import java.nio.ByteBuffer;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@NoArgsConstructor
@Entity
@Table(value = "diary")		
@AllArgsConstructor
public @Data class Diary {

	@PrimaryKey
    @Column(value = "id")
	@NotNull private UUID id;

	@Column(value = "java")
	@ColumnDefault("false")
	@NotNull private boolean Java;
	
	@Column(value = "sport")
	@ColumnDefault("false")
	@NotNull private boolean Sport;
	
	@Column(value = "electro")
	@ColumnDefault("false")
	@NotNull private boolean Electro;
	
	@Column(value = "university")
	@ColumnDefault("false")
	@NotNull private boolean University;
	
	@Column(value = "english")
	@ColumnDefault("false")
	@NotNull private boolean English;
	
	@Column(value = "german")
	@ColumnDefault("false")
	@NotNull private boolean German;
	
	@Column(value = "interviewJava")
	@ColumnDefault("false")
	@NotNull private boolean InterviewJava;
	
	@Column(value = "interviewElectro")
	@ColumnDefault("false")
	@NotNull private boolean InterviewElectro;
	
	@Column(value="date")
	@NotNull private Date date;
	
	@Column(value = "xaxa")
	@ColumnDefault("false")
	@NotNull private boolean XaXa;
	
	
	//constructor
	public Diary(UUID id, Date date) {
		this.id=id;
		this.date=date;
	}
	
}
