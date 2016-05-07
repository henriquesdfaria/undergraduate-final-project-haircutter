package br.com.haircutter.core.model;

import br.com.haircutter.core.enums.WeekdayEnum;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "weekday")
public class Weekday implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "weekday")
	private WeekdayEnum weekday;

	public Weekday() {
	}

	public Weekday(WeekdayEnum weekday) {
		this.weekday = weekday;
	}
}
