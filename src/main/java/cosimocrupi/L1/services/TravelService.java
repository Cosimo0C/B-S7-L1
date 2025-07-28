package cosimocrupi.L1.services;

import cosimocrupi.L1.entities.Travel;
import cosimocrupi.L1.exceptions.BadRequestException;
import cosimocrupi.L1.exceptions.NotFoundException;
import cosimocrupi.L1.payloads.TravelDTO;
import cosimocrupi.L1.repositories.TravelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class TravelService {
    @Autowired
    TravelRepository travelRepository;

    public Travel save(TravelDTO payload){
        this.travelRepository.findByReservationId(payload.reservationId()).ifPresent(travel -> {
            throw new BadRequestException("C'è già un viaggio intestato");
        });
        Travel newT = new Travel(payload.destination(), payload.date(), payload.stateTravel(), payload.reservationId());
        Travel saveT = this.travelRepository.save(newT);
        log.info("Il viaggio con id " + saveT.getId() + " è stato salvato correttamente!");
        return saveT;
    }
    public List<Travel> findAll(){
        return this.travelRepository.findAll();
    }

    public Travel findById(UUID travelId){
        return this.travelRepository.findById(travelId).orElseThrow(()-> new NotFoundException(travelId));
    }
    public Travel findByIdAndUpdate(UUID travelId, TravelDTO payload){
        Travel fnd =this.findById(travelId);

        if (!fnd.getReservationId().equals(payload.reservationId()))
            this.travelRepository.findByReservationId(payload.reservationId()).ifPresent(travel -> {
                throw new BadRequestException("L'id della prenotazione " + payload.reservationId() + " è già in uso");
            });
        fnd.setDestination(payload.destination());
        fnd.setDate(payload.date());
        fnd.setStateTravel(payload.stateTravel());
        fnd.setReservationId(payload.reservationId());
        Travel updateEmp = this.travelRepository.save(fnd);
        log.info("Il viaggio con id " + fnd.getId() + " è stato modificato correttamente!");
        return updateEmp;
    }
    public void findByIdAndDelete(UUID travelId){
        Travel fnd = this.findById(travelId);
        this.travelRepository.delete(fnd);
    }
}
