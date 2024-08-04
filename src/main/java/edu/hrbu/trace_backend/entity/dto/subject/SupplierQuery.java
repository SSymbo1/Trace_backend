package edu.hrbu.trace_backend.entity.dto.subject;

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
public class SupplierQuery {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String code;
    private Integer generate;
    private Integer isTrace;
}
