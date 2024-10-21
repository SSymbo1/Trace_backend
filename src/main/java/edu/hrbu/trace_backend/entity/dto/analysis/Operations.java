package edu.hrbu.trace_backend.entity.dto.analysis;

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
public class Operations {
    private List<Map<String, Integer>> originData;
    private List<Map<String, Double>> YOYData;
    private List<Map<String, Double>> QOQData;
}
