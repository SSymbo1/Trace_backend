package edu.hrbu.trace_backend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class Menue {
    @TableId(value = "mid", type = IdType.AUTO)
    private Integer mid;
    private String name;
    private String path;
    private String icon;
    private String color;
    private Integer parent;
    private String memo;
    private Integer del;
    @TableField(exist = false)
    private List<Menue> children;
}
