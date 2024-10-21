package edu.hrbu.trace_backend.entity.po;

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
public class EntranceClassCount {
    private String date;
    private Integer fresh;
    private Integer product;
    private Integer drink;
    private Integer food;
}
