package edu.hrbu.trace_backend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import edu.hrbu.trace_backend.entity.po.json.ReportApproach;
import edu.hrbu.trace_backend.entity.po.json.ReportEnterprise;
import edu.hrbu.trace_backend.entity.po.json.ReportEntrance;
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
public class YearTraceReport {

    @TableId(type = IdType.AUTO)
    private Integer rid;
    private String operateDate;
    private String date;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> marketApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> marketEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> batchApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> batchEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> butchApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> butchEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> processApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> processEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> plantApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> plantEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> farmApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> farmEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportApproach> animalApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEntrance> animalEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEnterprise> enterpriseData;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEnterprise> focusEnterpriseData;

}
