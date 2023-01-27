package mx.com.tutum.model.course.domain;

import java.io.Serializable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;


@Getter
@Setter(value = AccessLevel.PRIVATE)
@EqualsAndHashCode
@ToString
@Slf4j
public class Course implements Serializable, Cloneable {

	private static final long serialVersionUID = -4547827826219493698L;

	private Long id;
	private String name;
	private Boolean enabled;

	private Course(Long id, String name, Boolean enabled) {
		this.setId(id);
		this.setName(name);
		this.setEnabled(enabled);
	}
	
	public static Course create(Long id, String name, Boolean enabled) {
		return new Course(id, name, enabled);
	}

	public void modify(String name, Boolean enabled) {
		this.setName(name);
		this.setEnabled(enabled);
	}

	private void setId(Long id) {
		this.id = id;
	}

	public Course createClone() {
		try {
			return (Course) this.clone();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return null;
		}
	}

}