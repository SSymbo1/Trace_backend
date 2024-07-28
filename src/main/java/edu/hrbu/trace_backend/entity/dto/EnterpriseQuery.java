package edu.hrbu.trace_backend.entity.dto;

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
public class EnterpriseQuery {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String legalPerson;
    private String tel;
    private String socialCode;
    private String address;
    private String zipCode;
    private Integer del;
}
