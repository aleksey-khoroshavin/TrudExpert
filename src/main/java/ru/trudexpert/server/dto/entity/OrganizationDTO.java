package ru.trudexpert.server.dto.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.trudexpert.server.entity.Organization;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationDTO {

    private Long id;

    private String organizationName;

    private String lawAddress;

    private String factAddress;

    private String phone;

    private String inn;

    private String kpp;

    private String orgn;

    private String checkingAccount;

    private String correspondentAccount;

    private String email;

    private String bik;

    private String okpo;

    private String okved;

    public static OrganizationDTO getFromEntity(Organization entity) {
        if (entity == null) {
            return null;
        }

        return new OrganizationDTO()
                .setId(entity.getId())
                .setOrganizationName(entity.getName())
                .setLawAddress(entity.getLawAddress())
                .setFactAddress(entity.getFactAddress())
                .setPhone(entity.getPhone())
                .setInn(entity.getInn())
                .setKpp(entity.getKpp())
                .setOrgn(entity.getOrgn())
                .setCheckingAccount(entity.getCheckingAccount())
                .setCorrespondentAccount(entity.getCorrespondentAccount())
                .setEmail(entity.getEmail())
                .setBik(entity.getBik())
                .setOkpo(entity.getOkpo())
                .setOkved(entity.getOkved());
    }

}
