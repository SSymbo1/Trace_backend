package edu.hrbu.trace_backend_job.entity.po;

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
public class RoleOperate{
    @TableId(value = "iid", type = IdType.AUTO)
    private Integer iid;
    private Integer oid;
    @TableField(exist = false)
    private AccountInfo operator;
    private Integer rid;
    @TableField(exist = false)
    private Role role;
    private String operate;
    private String operateTime;
}
