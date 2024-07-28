package edu.hrbu.trace_backend.entity.po;

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
public class EnterpriseOperate{
    @TableId(value = "iid", type = IdType.AUTO)
    private Integer iid;
    private Integer oid;
    @TableField(exist = false)
    private AccountInfo operator;
    private Integer eid;
    @TableField(exist = false)
    private Enterprise enterprise;
    private String operate;
    private String operateTime;
}
