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
public class AccountStatue {
    private Integer aid;
    private Integer del;
    private Integer ban;
}
