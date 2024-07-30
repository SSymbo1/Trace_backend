package edu.hrbu.trace_backend.entity.dto.system;

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
public class UserQuery {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String gander;
    private String tel;
    private String email;
    private String role;
    private String address;
    private String zipCode;
    private Integer ban;
    private Integer del;

}
