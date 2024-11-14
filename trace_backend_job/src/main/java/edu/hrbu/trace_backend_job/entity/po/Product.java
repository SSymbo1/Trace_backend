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
public class Product {
    @TableId(value = "pid", type = IdType.AUTO)
    private Integer pid;
    private Integer cid;
    private Integer eid;
    private String photo;
    private String code;
    private String name;
    private String unit;
    private Integer isMajor;
    @TableField(exist = false)
    private String enterpriseName;
    @TableField(exist = false)
    private String className;
    @TableField(exist = false)
    private ProductRecord productRecord;
}
