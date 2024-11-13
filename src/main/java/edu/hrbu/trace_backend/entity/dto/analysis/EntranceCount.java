package edu.hrbu.trace_backend.entity.dto.analysis;

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
public class EntranceCount {
    private String date;
    private Integer total;
    private Double yoy;
    private Double qoq;
}
