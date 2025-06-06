package edu.hrbu.trace_backend_business.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Classification {
    @TableId(value = "cid", type = IdType.AUTO)
    private Integer cid;
    private String name;
    private Integer parent;
    private String memo;
    private Integer del;
    @TableField(exist = false)
    private Integer value;
    @TableField(exist = false)
    private String label;
    @TableField(exist = false)
    private List<Classification> children;
}
