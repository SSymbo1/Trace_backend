package edu.hrbu.trace_backend_job.entity.po.json;

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
public class ReportEntrance {
    private String name;
    private String code;
    private String type;
    private String total;
}
