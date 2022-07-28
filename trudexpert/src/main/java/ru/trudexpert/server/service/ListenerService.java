package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.repository.ListenerRepository;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ListenerService {
    private final ListenerRepository listenerRepository;

    @Transactional
    public void createListener(Listener listener) throws SnilsAlreadyRegisteredException {
        checkSnilsFree(listener.getSnils());
        listenerRepository.save(listener);
    }

    private void checkSnilsFree(String snils) throws SnilsAlreadyRegisteredException {
        if(listenerRepository.existsBySnils(snils)){
            throw new SnilsAlreadyRegisteredException();
        }
    }
}
