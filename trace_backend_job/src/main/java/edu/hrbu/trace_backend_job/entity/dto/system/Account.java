package edu.hrbu.trace_backend_job.entity.dto.system;

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
public class Account {
    private Integer aid;
    private String username;
    private String password;
    private Integer role;
    private Integer enterprise;
    private String name;
    private String gander;
    private String tel;
    private String email;
    private String address;
    private String zipCode;
    private String avatar;
}
