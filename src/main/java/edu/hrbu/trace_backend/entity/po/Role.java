package edu.hrbu.trace_backend.entity.po;

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
public class Role {
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;
    private String name;
    private String memo;
    private Integer del;
    private Integer ban;
    @TableField(exist = false)
    private String menues;
    @TableField(exist = false)
    private List<String> menueList;
}
