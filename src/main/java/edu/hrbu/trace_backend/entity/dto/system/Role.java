package edu.hrbu.trace_backend.entity.dto.system;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Integer rid;
    private String name;
    private String memo;
    private List<Integer> menues;
}
