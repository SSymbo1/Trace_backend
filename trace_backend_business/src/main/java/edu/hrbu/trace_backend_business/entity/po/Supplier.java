package edu.hrbu.trace_backend_business.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Supplier {
    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;
    private Integer eid;
    private String code;
    private Integer type;
    private Integer generate;
    private Integer isTrace;
    private Integer del;
    @TableField(exist = false)
    private Enterprise enterprise;
}
