package edu.hrbu.trace_backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import edu.hrbu.trace_backend.entity.po.AccountInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface AccountInfoMapper extends BaseMapper<AccountInfo> {

    IPage<AccountInfo> selectAccountInfoByCondition(IPage<AccountInfo> page, @Param("condition") Map<String, Object> condition);

    AccountInfo selectAccountInfoByAccountId(Integer accountId);

}
