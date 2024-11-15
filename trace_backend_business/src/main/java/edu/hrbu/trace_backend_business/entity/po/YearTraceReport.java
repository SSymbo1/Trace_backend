package edu.hrbu.trace_backend_business.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import edu.hrbu.trace_backend_business.entity.po.json.ReportApproach;
import edu.hrbu.trace_backend_business.entity.po.json.ReportEnterprise;
import edu.hrbu.trace_backend_business.entity.po.json.ReportEntrance;
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
public class YearTraceReport {

    @TableId(type = IdType.AUTO)
    private Integer rid;
    private String operateDate;
    private String date;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach marketApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance marketEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach batchApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance batchEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach butchApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance butchEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach processApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance processEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach plantApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance plantEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach farmApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance farmEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportApproach animalApproach;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private ReportEntrance animalEntrance;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEnterprise> enterpriseData;
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<ReportEnterprise> focusEnterpriseData;

}
