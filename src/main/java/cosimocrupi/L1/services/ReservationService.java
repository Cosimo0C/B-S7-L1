package cosimocrupi.L1.services;

import cosimocrupi.L1.entities.Reservation;
import cosimocrupi.L1.exceptions.BadRequestException;
import cosimocrupi.L1.exceptions.NotFoundException;
import cosimocrupi.L1.payloads.ReservationDTO;
import cosimocrupi.L1.repositories.ReservationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation save(ReservationDTO payload){
        Reservation nreR = new Reservation(payload.dateRequest(), payload.noteOrPreferences(), payload.employeeId());
        log.info("La prenotazione è stat salvata correttamente!");
        return nreR;
    }
    public List<Reservation> findAll(){
        return this.reservationRepository.findAll();
    }
    public Reservation findById(UUID reserId){
        return this.reservationRepository.findById(reserId).orElseThrow(()-> {throw new NotFoundException(reserId);
        });
    }
    public Reservation findByIdAndUpdate(UUID reserId, ReservationDTO payload){
        Reservation fnd = this.findById(reserId);
        if (!fnd.getEmployeeId().equals(payload.employeeId()))
        this.reservationRepository.findByEmployeeId(payload.employeeId()).ifPresent(reservation -> {
            throw new BadRequestException("La prenotazione con il dipendente con questo id "+ payload.employeeId()+ "è già in uso!");
        });
            fnd.setDateRequest(payload.dateRequest());
            fnd.setNoteOrPreferences(payload.noteOrPreferences());
            fnd.setEmployeeId(payload.employeeId());
            Reservation updateRes= this.reservationRepository.save(fnd);
            log.info("La prenotazione è stata aggiornata!");
            return updateRes;
    }
    public void findByIdAndDelete(UUID reserId){
        Reservation fnd= this.findById(reserId);
        this.reservationRepository.delete(fnd);
    }

}
