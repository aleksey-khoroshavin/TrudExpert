package ru.trudexpert.server.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class OrganizationDTO {

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


}
