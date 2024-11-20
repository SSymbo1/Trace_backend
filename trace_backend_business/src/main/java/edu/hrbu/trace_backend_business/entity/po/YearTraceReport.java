package edu.hrbu.trace_backend_business.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import edu.hrbu.trace_backend_business.entity.po.json.AreaData;
import edu.hrbu.trace_backend_business.entity.po.json.PointStruct;
import edu.hrbu.trace_backend_business.entity.po.json.TraceDataCollect;
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
@TableName(value = "year_trace_report", autoResultMap = true)
public class YearTraceReport {
    @TableId(type = IdType.AUTO)
    private Integer rid;
    private String operateDate;
    private String date;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect marketApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect marketEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect batchApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect batchEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect butchApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect butchEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect processApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect processEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect plantApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect plantEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect farmApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect farmEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect animalApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private TraceDataCollect animalEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<PointStruct> focusEnterpriseData;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<AreaData> areaData;
}
