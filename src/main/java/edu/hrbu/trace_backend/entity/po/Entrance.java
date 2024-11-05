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
// 出场表
public class Entrance {
    @TableId(value = "eid", type = IdType.AUTO)
    private Integer eid;
    private Integer bid;
    private String businessTime;
    private String name;
    private String code;
    private String batch;
    private Integer num;
    private String unit;
    private String trace;
    private Integer buyerType;
    private Integer cid;
    @TableField(exist = false)
    private String enterpriseName;
    @TableField(exist = false)
    private String enterpriseCode;
    @TableField(exist = false)
    private String supplierName;
    @TableField(exist = false)
    private String className;
}
