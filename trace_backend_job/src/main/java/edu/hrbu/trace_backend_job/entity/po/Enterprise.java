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
public class Enterprise {
    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;
    private String name;
    private String legalPerson;
    private String tel;
    private String socialCode;
    private String address;
    private String zipCode;
    private Integer del;
    @TableField("type")
    private Integer ilk;
    @TableField(exist = false)
    private Integer type;
}
