package org.xavisalvador.lottery.system.jpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Line implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "ticketId", referencedColumnName = "id", nullable = false, updatable = false)
	@JsonBackReference
	private Ticket ticket;

	@Column(nullable = false, length = 3)
	private int lineNumbers;

	@Column(nullable = false, length = 2)
	private int result;

	private long serialNumber;

	@Override
	public String toString() {
		String ticketIdString = null;
		if (ticket != null) {
			ticketIdString = ", ticketId=" + ticket.getId();
		}
		return "Line{" +
				"id=" + id +
				ticketIdString +
				", lineNumbers=" + lineNumbers +
				", result=" + result +
				", serialNumber=" + serialNumber +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Line line = (Line) o;
		return lineNumbers == line.lineNumbers && result == line.result && serialNumber == line.serialNumber
				&& Objects.equals(id, line.id) && Objects.equals(ticket.getId(), line.ticket.getId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, ticket.getId(), lineNumbers, result, serialNumber);
	}

}
