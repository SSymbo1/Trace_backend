package edu.hrbu.trace_backend.function;

import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.entity.po.Enterprise;
import edu.hrbu.trace_backend.entity.po.Supplier;
import edu.hrbu.trace_backend.mapper.EnterpriseMapper;
import edu.hrbu.trace_backend.mapper.SupplierMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;
import java.util.Random;

@Slf4j
@SpringBootTest
public class AddSupplierTest {

    @Resource
    private SupplierMapper supplierMapper;
    @Resource
    private EnterpriseMapper enterpriseMapper;

    @Test
    public void addSupplier() {
        List<Enterprise> enterpriseList = enterpriseMapper.selectList(null);
        enterpriseList.forEach(list -> {
            Supplier addSupplier = Supplier.builder()
                    .eid(list.getEid())
                    .code(ObjectId.next()).build();
            supplierMapper.insert(addSupplier);
        });
    }

    @Test
    public void randomTypeTest() {
        QueryWrapper<Supplier> supplierQueryWrapper = new QueryWrapper<>();
        supplierQueryWrapper.ne("eid", 1);
        List<Supplier> supplierList = supplierMapper.selectList(supplierQueryWrapper);
        supplierList.forEach(list -> {
            QueryWrapper<Supplier> updateSupplierWrapper = new QueryWrapper<>();
            updateSupplierWrapper.eq("eid", list.getEid());
            Supplier updateType = Supplier.builder()
                    .type(RandomUtil.randomInt(1, 3)).build();
            supplierMapper.update(updateType, updateSupplierWrapper);
        });
    }

}
