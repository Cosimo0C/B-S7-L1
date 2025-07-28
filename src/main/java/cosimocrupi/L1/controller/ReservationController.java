package cosimocrupi.L1.controller;

import cosimocrupi.L1.entities.Reservation;
import cosimocrupi.L1.exceptions.ValidationException;
import cosimocrupi.L1.payloads.ReservationDTO;
import cosimocrupi.L1.payloads.ReservationRespDTO;
import cosimocrupi.L1.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/reservation")
public class ReservationController {
    @Autowired
    ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationRespDTO save(@RequestBody @Validated ReservationDTO payload, BindingResult validRes){
        if (validRes.hasErrors()){
            throw new ValidationException(validRes.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }else {
            Reservation newR = this.reservationService.save(payload);
            return new ReservationRespDTO(newR.getId());
        }
    }
    @GetMapping("/{reserId}")
    public Reservation getById(@PathVariable UUID reserId){
        return this.reservationService.findById(reserId);
    }

    @GetMapping("/reservation")
    public List<Reservation> getAll(){
        return this.reservationService.findAll();
    }

    @PutMapping("/{reserId}")
    public Reservation getByIdAndUpdate(@PathVariable UUID employeeId, @RequestBody ReservationDTO payload){
        return this.reservationService.findByIdAndUpdate(employeeId, payload);
    }
    @DeleteMapping("/{reserId}")
    public void getByIdAndDelete(UUID reserID){
        this.reservationService.findByIdAndDelete(reserID);
    }
}
