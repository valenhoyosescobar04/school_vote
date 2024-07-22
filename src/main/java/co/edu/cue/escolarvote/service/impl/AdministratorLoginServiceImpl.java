package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.service.LoginService;

import java.util.Optional;

public record AdministratorLoginServiceImpl(School school) implements LoginService<Administrator> {

    @Override
    public Boolean login(Administrator administrator) {
        Optional<Administrator> resp=school.administratorRepository.list().stream()
                .filter(x->x.getUsername().equals(administrator.getUsername())).findFirst();
        return resp.isPresent();
    }
}
