package com.sain.securedocuments.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sain.securedocuments.enumeration.Authority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@JsonInclude(NON_DEFAULT)
public class RolesEntity extends Auditable{
    private String name;
    private Authority authorities;
}
