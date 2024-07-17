package edu.hrbu.trace_backend.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
public class Account {
    @TableId(value = "aid",type = IdType.AUTO)
    private Integer aid;
    private String username;
    private String password;
    private Integer rid;
    private Integer del;
    private Integer ban;
    private String memo;
}
