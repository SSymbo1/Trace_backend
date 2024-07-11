package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import edu.hrbu.trace_backend.entity.dto.Account;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AccountMapper extends BaseMapper<Account> {
    
}
