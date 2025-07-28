package cosimocrupi.L1.controller;

import cosimocrupi.L1.entities.Travel;
import cosimocrupi.L1.exceptions.ValidationException;
import cosimocrupi.L1.payloads.TravelDTO;
import cosimocrupi.L1.payloads.TravelRespDTO;
import cosimocrupi.L1.services.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/travel")
public class TravelController {

    @Autowired
    TravelService travelService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TravelRespDTO save(@RequestBody @Validated TravelDTO payload, BindingResult validRes){
        if (validRes.hasErrors()){
            throw new ValidationException(validRes.getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList());
        }else {
            Travel newT = this.travelService.save(payload);
            return new TravelRespDTO(newT.getId());
        }
    }
    @GetMapping("/{travelId}")
    public Travel getById(@PathVariable UUID travelId){
        return this.travelService.findById(travelId);
    }

    @GetMapping("/travel")
    public List<Travel> getAll(){
        return this.travelService.findAll();
    }

    @PutMapping("/{travelId}")
    public Travel getByIdAndUpdate(@PathVariable UUID travelId, @RequestBody TravelDTO payload){
        return this.travelService.findByIdAndUpdate(travelId, payload);
    }
    @DeleteMapping("/{travelId}")
    public void getByIdAndDelete(UUID travelID){
        this.travelService.findByIdAndDelete(travelID);
    }
}
