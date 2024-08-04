package edu.hrbu.trace_backend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
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
    @TableField(exist = false)
    private Integer type;
}
