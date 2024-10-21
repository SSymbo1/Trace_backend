package edu.hrbu.trace_backend.cron.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.io.FileUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import edu.hrbu.trace_backend.cron.service.TrashDataClearService;
import edu.hrbu.trace_backend.entity.enums.Folder;
import edu.hrbu.trace_backend.entity.po.*;
import edu.hrbu.trace_backend.mapper.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TrashDataClearServiceImpl implements TrashDataClearService {

    @Resource
    private AccountInfoMapper accountInfoMapper;
    @Resource
    private AccountOperateMapper accountOperateMapper;
    @Resource
    private EnterpriseOperateMapper enterpriseOperateMapper;
    @Resource
    private RoleOperateMapper roleOperateMapper;
    @Resource
    private ProductMapper productMapper;

    @Override
    public void removeUnusedAvatar() {
        log.info("执行定时清理无用用户头像任务");
        List<AccountInfo> accountInfos = accountInfoMapper.selectList(null);
        List<String> accountAvatarDataBase = accountInfos
                .stream()
                .map(AccountInfo::getAvatar)
                .collect(Collectors.toList());
        File[] avatars = FileUtil.ls(
                edu.hrbu.trace_backend.util.FileUtil.getSystemPath() + Folder.AVATAR.getValue()
        );
        for (File avatar : avatars) {
            if (!accountAvatarDataBase.contains(avatar.getName()) && !avatar.getName().equals("default.png")) {
                FileUtil.del(avatar);
                log.info("已清除:{}", avatar);
            }
        }
        log.info("定时清理无用用户头像任务已完成");
    }

    @Override
    public void removeUnusedGoodsPhoto() {
        log.info("执行定时清理无用产品图片任务");
        List<Product> products = productMapper.selectList(null);
        List<String> productPhotoDataBase = products
                .stream()
                .map(Product::getPhoto)
                .collect(Collectors.toList());
        File[] photos = FileUtil.ls(
                edu.hrbu.trace_backend.util.FileUtil.getSystemPath() + Folder.GOODS.getValue()
        );
        for (File photo : photos) {
            if (!productPhotoDataBase.contains(photo.getName()) && !photo.getName().equals("default.png")) {
                FileUtil.del(photo);
                log.info("已清除:{}", photo);
            }
        }
        log.info("定时清理无用产品图片任务已完成");
    }

    @Override
    public void removeExpireAccountOperate() {
        log.info("执行定时清理账户操作记录任务");
        DateTime operateTime = new DateTime(DateTime.now());
        QueryWrapper<AccountOperate> deleteQueryWrapper = new QueryWrapper<>();
        deleteQueryWrapper.like("operate_time", operateTime.toString("yyyy"));
        accountOperateMapper.delete(deleteQueryWrapper);
        log.info("定时清理账户操作记录任务已完成");
    }

    @Override
    public void removeExpireEnterpriseOperate() {
        log.info("执行定时清理企业操作记录任务");
        DateTime operateTime = new DateTime(DateTime.now());
        QueryWrapper<EnterpriseOperate> deleteQueryWrapper = new QueryWrapper<>();
        deleteQueryWrapper.like("operate_time", operateTime.toString("yyyy"));
        enterpriseOperateMapper.delete(deleteQueryWrapper);
        log.info("定时清理企业操作记录任务已完成");
    }

    @Override
    public void removeExpireRoleOperate() {
        log.info("执行定时清理角色操作记录任务");
        DateTime operateTime = new DateTime(DateTime.now());
        QueryWrapper<RoleOperate> deleteQueryWrapper = new QueryWrapper<>();
        deleteQueryWrapper.like("operate_time", operateTime.toString("yyyy"));
        roleOperateMapper.delete(deleteQueryWrapper);
        log.info("定时清理角色操作记录任务已完成");
    }
}
