package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.ListenerAlreadyRegisteredException;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.repository.ListenerRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ListenerService {
    private final ListenerRepository listenerRepository;

    @Transactional
    public void createListener(ListenerDTO listenerDTO) throws SnilsAlreadyRegisteredException, ListenerAlreadyRegisteredException {

        if(listenerDTO.getSnils()!= null && !listenerDTO.getSnils().isEmpty()){
            checkSnilsFree(listenerDTO.getSnils());
        }

        checkUserMainDataFree(listenerDTO);

        Listener listener = Listener.getFromDTO(listenerDTO);
        listenerRepository.save(listener);
    }

    private void checkSnilsFree(String snils) throws SnilsAlreadyRegisteredException {
        if(listenerRepository.existsBySnils(snils)){
            throw new SnilsAlreadyRegisteredException();
        }
    }

    private void checkUserMainDataFree(ListenerDTO listenerDTO) throws ListenerAlreadyRegisteredException {
        if(listenerDTO.getPatronymic()!= null && !listenerDTO.getPatronymic().isEmpty()){
            if(listenerRepository.existsByFullName(
                    listenerDTO.getSurname(),
                    listenerDTO.getName(),
                    listenerDTO.getPatronymic())){
                throw new ListenerAlreadyRegisteredException();
            }
        }else{
            if(listenerRepository.existsBySurnameAndName(
                    listenerDTO.getSurname(),
                    listenerDTO.getName()
            )){
                throw new ListenerAlreadyRegisteredException();
            }
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());


        if(listenerRepository.existsByDateOfBirth(
                LocalDate.parse(listenerDTO.getDateOfBirth(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC)
        )){
            throw new ListenerAlreadyRegisteredException();
        }
    }

    public List<ListenerShortInfoDTO> getListenersBySurname(String surname){
        return listenerRepository.findAllBySurname(surname)
                .stream()
                .map(ListenerShortInfoDTO::getFromEntity)
                .collect(Collectors.toList());
    }

    public List<ListenerShortInfoDTO> getListeners(){
        return listenerRepository.findAll()
                .stream()
                .map(ListenerShortInfoDTO::getFromEntity)
                .collect(Collectors.toList());
    }

}
