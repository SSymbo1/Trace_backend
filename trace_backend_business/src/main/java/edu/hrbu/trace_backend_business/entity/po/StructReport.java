package edu.hrbu.trace_backend_business.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import edu.hrbu.trace_backend_business.entity.po.json.PointFocus;
import edu.hrbu.trace_backend_business.entity.po.json.PointRate;
import edu.hrbu.trace_backend_business.entity.po.json.PointStruct;
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
@TableName(value = "struct_report", autoResultMap = true)
public class StructReport {
    @TableId(type = IdType.AUTO)
    private Integer sid;
    private String generate;
    private String date;
    private Integer plantTotal;
    private Integer farmTotal;
    private Integer batchTotal;
    private Integer butchTotal;
    private Integer animalTotal;
    private Integer processTotal;
    private Integer marketTotal;
    private Integer plantPoint;
    private Integer farmPoint;
    private Integer batchPoint;
    private Integer butchPoint;
    private Integer animalPoint;
    private Integer processPoint;
    private Integer marketPoint;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PointStruct> pointStruct;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PointFocus> pointFocus;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PointRate> pointRate;
    private Integer type;
    @TableField(exist = false)
    private String range;
}
