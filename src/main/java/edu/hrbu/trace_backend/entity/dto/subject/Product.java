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
public class Product {
    private Integer pid;
    private String name;
    private String code;
    private String unit;
    private Integer isMajor;
    private Integer cid;
    private Integer num;
}
