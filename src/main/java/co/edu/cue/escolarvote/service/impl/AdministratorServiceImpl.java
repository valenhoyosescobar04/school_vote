package co.edu.cue.escolarvote.service.impl;

import co.edu.cue.escolarvote.domain.entities.Administrator;
import co.edu.cue.escolarvote.service.AdministratorService;

import java.util.List;

public record AdministratorServiceImpl(School school) implements AdministratorService {

    @Override
    public void createAdmin(String username, String password) {
        school.administratorRepository.save(new Administrator(username, password));
    }

    @Override
    public List<Administrator> getAdministrators() {
        return school.administratorRepository.list();
    }

    @Override
    public Administrator getAdministratorById(Long id) {
        return school.administratorRepository.byId(id);
    }


}
