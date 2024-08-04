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
public class ProductQuery {
    private Integer currentPage;
    private Integer pageSize;
    private String name;
    private String enterprise;
    private String classification;
    private Integer isMajor;
    private Integer importType;
    private Integer statue;
}
