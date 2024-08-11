package edu.hrbu.trace_backend.entity.dto.applet;

import edu.hrbu.trace_backend.entity.po.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class TraceProduct {
    private String trace;
    private String batch;
    private Product product;
    private List<Map<String,Object>> traceData;
}
