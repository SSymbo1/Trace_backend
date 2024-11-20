package edu.hrbu.trace_backend_business.entity.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Enterprise {
    private Integer eid;
    private String name;
    private Integer type;
    private Integer ilk;
    private String legalPerson;
    private String tel;
    private String socialCode;
    private String address;
    private String zipCode;
    private Integer del;
}
