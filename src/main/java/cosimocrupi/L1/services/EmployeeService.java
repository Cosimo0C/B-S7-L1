package cosimocrupi.L1.services;

import cosimocrupi.L1.entities.Employee;
import cosimocrupi.L1.exceptions.BadRequestException;
import cosimocrupi.L1.exceptions.NotFoundException;
import cosimocrupi.L1.exceptions.UnauthorizedException;
import cosimocrupi.L1.payloads.EmployeeDTO;
import cosimocrupi.L1.payloads.LoginDTO;
import cosimocrupi.L1.repositories.EmployeeRepository;
import cosimocrupi.L1.tools.JWTTools;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    private JWTTools jwtTools;

    public Employee save(EmployeeDTO payload){
        this.employeeRepository.findByEmail(payload.email()).ifPresent(employee -> {
            throw new BadRequestException("L'email " + employee.getEmail() + " è già in uso!");
        });
        Employee newE = new Employee(payload.username(), payload.name(), payload.surname(), payload.email());
        newE.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        Employee saveE = this.employeeRepository.save(newE);
        log.info("Il dipendente con username " + saveE.getUsername() + " è stato salvato correttamente!");
        return saveE;
    }
    public List<Employee> findAll(){
        return this.employeeRepository.findAll();
    }

    public Employee findById(UUID employeeId){
        return this.employeeRepository.findById(employeeId).orElseThrow(()-> new NotFoundException(employeeId));
    }

    public Employee findByIdAndUpdate(UUID employeeId, EmployeeDTO payload){
        Employee fnd =this.findById(employeeId);

        if (!fnd.getEmail().equals(payload.email()))
            this.employeeRepository.findByEmail(payload.email()).ifPresent(employee -> {
                throw new BadRequestException("L'email " + payload.email() + " è già in uso");
            });
        fnd.setUsername(payload.username());
        fnd.setName(payload.name());
        fnd.setSurname(payload.surname());
        fnd.setEmail(payload.email());
        fnd.setAvatarUrl("https://ui-avatars.com/api/?name=" + payload.name() + "+" + payload.surname());
        Employee updateEmp = this.employeeRepository.save(fnd);
        log.info("Il dipendente con id " + fnd.getId() + " èstato modificato correttamente!");
        return updateEmp;
    }
    public void findByIdAndDelete(UUID employeeId){
        Employee fnd = this.findById(employeeId);
        this.employeeRepository.delete(fnd);
    }
    public Employee findByEmail(String email) {
        return this.employeeRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("Il dipendente con l'email " + email + " non è stato trovato!"));
    }
    public String checkCredentialsAndGenerateToken(LoginDTO payload){
        Employee fnd = this.findByEmail(payload.email());
        if (fnd.getPassword().equals(payload.password())){
            String accT = jwtTools.createToken(fnd);
            return accT;
        }else {
            throw new UnauthorizedException("Credenziali errate!");
        }
    }
}
