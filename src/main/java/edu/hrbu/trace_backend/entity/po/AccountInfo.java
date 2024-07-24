package edu.hrbu.trace_backend.entity.po;

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
public class AccountInfo {
    @TableId(value = "aid")
    private Integer aid;
    private Integer eid;
    private Integer rid;
    private String name;
    private String gander;
    private String tel;
    private String avatar;
    private String email;
    private String address;
    private String zipCode;
    @TableField(exist = false)
    private Enterprise enterprise;
    @TableField(exist = false)
    private Role role;
    @TableField(exist = false)
    private Account account;
}
