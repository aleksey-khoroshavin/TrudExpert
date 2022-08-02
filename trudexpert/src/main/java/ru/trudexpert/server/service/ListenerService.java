package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.repository.ListenerRepository;
import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListenerService {
    private final ListenerRepository listenerRepository;

    @Transactional
    public void createListener(ListenerDTO listenerDTO) throws SnilsAlreadyRegisteredException {
        checkSnilsFree(listenerDTO.getSnils());
        Listener listener = Listener.getFromDTO(listenerDTO);
        listenerRepository.save(listener);
    }

    private void checkSnilsFree(String snils) throws SnilsAlreadyRegisteredException {
        if(listenerRepository.existsBySnils(snils)){
            throw new SnilsAlreadyRegisteredException();
        }
    }

//    public List<ListenerShortInfoDTO> getListenersBySurname(String surname){
//
//    }
//
//    public List<ListenerShortInfoDTO> getListeners(){
//
//    }

}
