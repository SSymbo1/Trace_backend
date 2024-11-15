package edu.hrbu.trace_backend_business.entity.dto.subject;

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
public class Product {
    private Integer pid;
    @NotEmpty(message = "产品名称不能为空")
    private String name;
    @NotEmpty(message = "产品编码不能为空")
    private String code;
    @NotEmpty(message = "产品单位不能为空")
    private String unit;
    @NotNull(message = "是否为重要产品标记不为空")
    private Integer isMajor;
    @NotNull(message = "产品分类不能为空")
    private Integer cid;
    @NotNull(message = "产品数量不能为空")
    private Integer num;
    private Integer enterprise;
    private Integer statue;
}
