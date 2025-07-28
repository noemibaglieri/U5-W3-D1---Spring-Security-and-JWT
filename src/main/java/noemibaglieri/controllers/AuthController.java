package noemibaglieri.controllers;

import noemibaglieri.entities.Employee;
import noemibaglieri.exceptions.ValidationException;
import noemibaglieri.payloads.LoginDTO;
import noemibaglieri.payloads.LoginRespDTO;
import noemibaglieri.payloads.NewEmployeeDTO;
import noemibaglieri.payloads.NewEmployeeRespDTO;
import noemibaglieri.services.AuthService;
import noemibaglieri.services.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/login")
    public LoginRespDTO login(@RequestBody LoginDTO body) {
        String accessToken = authService.checkCredentialsAndGenerateToken(body);
        return new LoginRespDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewEmployeeRespDTO save(@RequestBody @Validated NewEmployeeDTO payload, BindingResult validationResult) {
        if(validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors()
                    .stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Employee newEmployee = this.employeesService.save(payload);
            return new NewEmployeeRespDTO(newEmployee.getId());
        }
    }
}
