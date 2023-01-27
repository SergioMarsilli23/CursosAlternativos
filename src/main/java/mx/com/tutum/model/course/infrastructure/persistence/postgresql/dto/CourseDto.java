package mx.com.tutum.model.course.infrastructure.persistence.postgresql.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import mx.com.tutum.shared.infrastructure.SharedConstants;

@Entity(name = "Course")
@Table(name = "t_materias", schema = SharedConstants.DB_SCHEMA)
@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class CourseDto implements Cloneable {

	@Id
	@Column(name = "id_t_materias", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "nombre", nullable = false)
	private String name;

	@Column(name = "activo", nullable = false)
	private Boolean enabled;
	
	//@OneToMany(mappedBy = "course", targetEntity = ScoreDto.class, cascade = {CascadeType.REMOVE, CascadeType.REFRESH}, fetch = FetchType.LAZY)
	//@Fetch(value = FetchMode.SUBSELECT)
	//private List<ScoreDto> scores;
	
	public CourseDto createClone () {
		try {
			return (CourseDto) this.clone();
		} catch (CloneNotSupportedException e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}
}