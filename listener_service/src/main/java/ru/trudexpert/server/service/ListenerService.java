package ru.trudexpert.server.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.trudexpert.server.dto.ListenerDTO;
import ru.trudexpert.server.dto.ListenerShortInfoDTO;
import ru.trudexpert.server.entity.Listener;
import ru.trudexpert.server.exception.ListenerAlreadyRegisteredException;
import ru.trudexpert.server.exception.ListenerNotFoundException;
import ru.trudexpert.server.exception.SnilsAlreadyRegisteredException;
import ru.trudexpert.server.repository.ListenerRepository;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ListenerService {
    private final ListenerRepository listenerRepository;

    @Transactional
    public void saveListener(ListenerDTO listenerDTO) throws SnilsAlreadyRegisteredException, ListenerAlreadyRegisteredException {

        checkSnilsFree(listenerDTO);
        checkUserMainDataFree(listenerDTO);

        Listener listener = Listener.getFromDTO(listenerDTO);
        listenerRepository.save(listener);
    }

    @Transactional
    public void updateListener(ListenerDTO dto) throws ListenerNotFoundException, SnilsAlreadyRegisteredException {
        checkSnilsFree(dto);

        Listener listener = listenerRepository.findById(dto.getId()).orElse(null);

        if(listener == null){
            throw new ListenerNotFoundException();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());

        listener.setId(dto.getId())
                .setSurname(dto.getSurname())
                .setName(dto.getName())
                .setPatronymic(dto.getPatronymic())
                .setDateOfBirth(LocalDate.parse(dto.getDateOfBirth(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC))
                .setSnils(dto.getSnils())
                .setGender(dto.getGender())
                .setPhoneNumber("+7-" + dto.getPhoneNumber())
                .setCitizenshipCode(dto.getCitizenshipCode())
                .setDriverLicense(dto.getDriverLicense())
                .setAddress(dto.getAddress())
                .setPassportSeries(dto.getPassportSeries())
                .setPassportNumber(dto.getPassportNumber())
                .setPassportIssuedBy(dto.getPassportIssuedBy())
                .setPassportIssuedAt(dto.getPassportIssuedAt() != null && !dto.getPassportIssuedAt().isEmpty() ?
                        LocalDate.parse(dto.getPassportIssuedAt(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC) :
                        null)
                .setEducationType(dto.getEducationType())
                .setEducationDocument(dto.getEducationDocument())
                .setEducationDocumentIssuedAt(dto.getEducationDocumentIssuedAt() != null && !dto.getEducationDocumentIssuedAt().isEmpty()?
                        LocalDate.parse(dto.getEducationDocumentIssuedAt(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC) :
                        null);

        listenerRepository.save(listener);

    }

    private void checkSnilsFree(ListenerDTO listenerDTO) throws SnilsAlreadyRegisteredException {
        if (
                listenerDTO.getSnils() != null
                        && !listenerDTO.getSnils().isEmpty()
                        && listenerRepository.existsBySnils(listenerDTO.getSnils())) {
            throw new SnilsAlreadyRegisteredException();
        }
    }

    private void checkUserMainDataFree(ListenerDTO listenerDTO) throws ListenerAlreadyRegisteredException {

        try{
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
        }
        catch (ListenerAlreadyRegisteredException exception){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.systemDefault());


            if(listenerRepository.existsByDateOfBirth(
                    LocalDate.parse(listenerDTO.getDateOfBirth(), formatter).atStartOfDay().toInstant(ZoneOffset.UTC)
            )){
                throw new ListenerAlreadyRegisteredException();
            }
        }
    }

    public List<ListenerShortInfoDTO> getListenersBySurname(String surname){
        return listenerRepository.findAllBySurname('%' + surname + '%')
                .stream()
                .map(ListenerShortInfoDTO::getFromEntity)
                .toList();
    }

    public List<ListenerShortInfoDTO> getListeners(){
        return listenerRepository.findAll()
                .stream()
                .map(ListenerShortInfoDTO::getFromEntity)
                .toList();
    }

    public ListenerDTO getListenerById(Long id) throws ListenerNotFoundException {
        Listener listener = listenerRepository.findById(id).orElse(null);
        if(listener == null){
            throw new ListenerNotFoundException();
        }
        return ListenerDTO.getFromEntity(listener);
    }

}
