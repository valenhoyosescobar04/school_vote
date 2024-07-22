package co.edu.cue.escolarvote.service;

import co.edu.cue.escolarvote.domain.entities.Administrator;

import java.util.List;

public interface AdministratorService {

    void createAdmin(String username, String password);

    List<Administrator> getAdministrators();

    Administrator getAdministratorById(Long id);
}
