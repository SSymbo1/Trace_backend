package edu.hrbu.trace_backend_business.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Builder
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class Decode {
    private Integer aid;
    @NotEmpty(message = "所需解码密码不能为空!")
    private String encodePass;
    @NotEmpty(message = "校验验证码不能为空!")
    private String verify;
    @NotEmpty(message = "验证码不能为空!")
    private String captcha;
    @NotNull(message = "时间戳不能为空!")
    private Long timestamp;
}
