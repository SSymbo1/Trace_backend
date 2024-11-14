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
public class ProductRecord {
    @TableId(value = "rid", type = IdType.AUTO)
    private Integer rid;
    private Integer pid;
    private Integer aid;
    private Integer num;
    private Integer approver;
    private String processTime;
    private String insertTime;
    private Integer importType;
    private Integer statue;
    @TableField(exist = false)
    private String accountName;
}
