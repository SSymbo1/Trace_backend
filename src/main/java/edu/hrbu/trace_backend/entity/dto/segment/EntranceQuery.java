package edu.hrbu.trace_backend.entity.dto.segment;

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
public class EntranceQuery {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String batch;
    private String trace;
    private String buyerType;
}
