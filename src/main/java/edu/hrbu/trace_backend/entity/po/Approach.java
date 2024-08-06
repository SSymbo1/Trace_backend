package edu.hrbu.trace_backend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

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
public class Approach {
    @TableId(value = "aid", type = IdType.AUTO)
    private Integer aid;
    private Integer eid;
    private String name;
    private String code;
    private String batch;
    private Integer num;
    private String unit;
    private String trace;
    private Integer cid;
    private Integer sid;
    private String businessTime;
    @TableField(exist = false)
    private String enterpriseName;
    @TableField(exist = false)
    private String supplierName;
    @TableField(exist = false)
    private String enterpriseCode;
    @TableField(exist = false)
    private String className;
}
