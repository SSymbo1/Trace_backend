package edu.hrbu.trace_backend.entity.dto;

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
public class Decode {
    private Integer aid;
    private String encodePass;
    private String verify;
    private String captcha;
    private Long timestamp;
}
