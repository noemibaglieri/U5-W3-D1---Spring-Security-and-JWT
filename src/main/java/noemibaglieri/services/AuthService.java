package noemibaglieri.services;

import noemibaglieri.entities.Employee;
import noemibaglieri.exceptions.UnauthorisedException;
import noemibaglieri.payloads.LoginDTO;
import noemibaglieri.tools.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private EmployeesService employeesService;

    @Autowired
    private JWTTools jwtTools;

    public String checkCredentialsAndGenerateToken(LoginDTO body) {
        Employee found = this.employeesService.findByEmail(body.email());

        if(found.getPassword().equals(body.password())) {
            String accessToken = jwtTools.createToken(found);
            return accessToken;
        } else {
            throw new UnauthorisedException("Wrong credentials!");
        }
    }

}
