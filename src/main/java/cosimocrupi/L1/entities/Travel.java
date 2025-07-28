package cosimocrupi.L1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
@ToString
public class Travel {
    @Id
    @GeneratedValue
    private UUID id;
    private String destination;
    private LocalDate date;
    private String stateTravel;
    private String reservationId;

    public Travel(String destination, LocalDate date, String stateTravel, String reservationId) {
        this.destination = destination;
        this.date = date;
        this.stateTravel = stateTravel;
        this.reservationId = reservationId;
    }
}
