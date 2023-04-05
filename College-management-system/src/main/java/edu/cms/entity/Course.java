package edu.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="course")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Course {

	//column name in course table
@Id
	private int subId;
@Column(name="subName",length=20,nullable=false)
	private String subName;
@Column(length=20,nullable=false)
	private double price;
	
	@ManyToOne
	private Teacher teacher;
}
