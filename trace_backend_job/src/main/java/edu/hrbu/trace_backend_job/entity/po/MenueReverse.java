package edu.hrbu.trace_backend_job.entity.po;

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
public class MenueReverse {
    private Integer son;
    private Integer parent;
    private Integer father;
}
