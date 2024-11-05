package edu.hrbu.trace_backend.entity.po;

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
public class ImportantEnterprise {
    @TableId
    private Integer eid;
    private Integer oid;
    @TableField(exist = false)
    private String enterprise;
    @TableField(exist = false)
    private String operator;
    @TableField(exist = false)
    private boolean important;
    private String date;
}
