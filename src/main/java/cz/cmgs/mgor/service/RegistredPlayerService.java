package cz.cmgs.mgor.service;

import cz.cmgs.mgor.entity.RegistredPlayer;
import cz.cmgs.mgor.repository.RegistredPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegistredPlayerService {
    @Autowired
    RegistredPlayerRepository registredPlayerRepository;

    public List<RegistredPlayer> getRegistredPlayers() {
        return (List<RegistredPlayer>) registredPlayerRepository.findAll();
    }

    public List<RegistredPlayer> findByRegNr(String regNr) {
        return registredPlayerRepository.findByRegNr(regNr);
    }

    public List<RegistredPlayer> findByKeyword(String keyword) {
        return registredPlayerRepository.findByKeyword(keyword.toUpperCase());
    }

}
