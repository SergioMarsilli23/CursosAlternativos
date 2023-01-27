package mx.com.tutum.model.score.infrastructure.persistence.postgresql.dto;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto.CourseDto;
import mx.com.tutum.model.student.infrastructure.persistence.postgresql.dto.StudentDto;
import mx.com.tutum.shared.infrastructure.SharedConstants;


@Entity(name = "Score")
@Table(name = "t_calificaciones", schema = SharedConstants.DB_SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class ScoreDto implements Cloneable {

	@Id
	@Column(name = "id_t_calificaciones", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "calificacion", nullable = false)
	private Float score;
	
	@Column(name = "fecha_registro", nullable = false, updatable = false)
	@CreationTimestamp
	private Date registrationDate;
	
	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = CourseDto.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinColumn(name = "id_t_materias", referencedColumnName = "id_t_materias", nullable = false, insertable = true, updatable = false)
	private CourseDto course;

	@ManyToOne(cascade = CascadeType.REFRESH, targetEntity = StudentDto.class, fetch = FetchType.EAGER)
	@Fetch(value = FetchMode.JOIN)
	@JoinColumn(name = "id_t_usuarios", referencedColumnName = "id_t_usuarios", nullable = false, insertable = true, updatable = false)
	private StudentDto student;
	
	public ScoreDto createClone () {
		try {
			return (ScoreDto) this.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}