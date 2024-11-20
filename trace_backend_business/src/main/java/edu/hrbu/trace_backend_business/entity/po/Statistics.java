package edu.hrbu.trace_backend_business.entity.po;

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
public class Statistics {
    private Integer approach;
    private Integer entrance;
    private Integer process;
    private Integer submit;
}
