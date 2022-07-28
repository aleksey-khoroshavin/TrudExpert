package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.domain.ListenerDetails;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.repository.ListenerRepository;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ListenerService {
    private final ListenerRepository listenerRepository;

    @Transactional
    public void createListener(ListenerDetails details) throws SnilsAlreadyRegisteredException {
        checkSnilsFree(details.getSnils());

        Listener listener = new Listener()
                .setSurname(details.getSurname())
                .setName(details.getName())
                .setPatronymic(details.getPatronymic())
                .setDateOfBirth(details.getDateOfBirth())
                .setSnils(details.getSnils())
                .setGender(details.getGender())
                .setPhoneNumber(details.getPhoneNumber())
                .setCitizenshipCode(details.getCitizenshipCode());

        //not necessary fields
        if(!details.getDriverLicense().isEmpty()){
            listener.setDriverLicense(details.getDriverLicense());
        }

        listenerRepository.save(listener);
    }

    private void checkSnilsFree(String snils) throws SnilsAlreadyRegisteredException {
        if(listenerRepository.existsBySnils(snils)){
            throw new SnilsAlreadyRegisteredException();
        }
    }
}
