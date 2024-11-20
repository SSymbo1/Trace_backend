package edu.hrbu.trace_backend_business.entity.po.json;

import lombok.*;
import lombok.experimental.Accessors;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TraceDataCollect {
    private String name;
    private String total;
    private String yoy;
    private String qoq;
    private String popular;
}
