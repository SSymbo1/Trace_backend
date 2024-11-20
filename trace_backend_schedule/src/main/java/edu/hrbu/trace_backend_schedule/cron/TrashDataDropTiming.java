package edu.hrbu.trace_backend_schedule.cron;

import edu.hrbu.trace_backend_schedule.service.TrashDataClearService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
@Component
@EnableScheduling
//  定时删除垃圾数据
public class TrashDataDropTiming {

    @Resource
    private TrashDataClearService trashDataClearService;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteTrashAvatar() {
        trashDataClearService.removeUnusedAvatar();
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteTrashGoodsPhoto() {
        trashDataClearService.removeUnusedGoodsPhoto();
    }

    @Scheduled(cron = "0 0 0 31 12 *")
    public void deleteTrashAccountOperate() {
        trashDataClearService.removeExpireAccountOperate();
    }

    @Scheduled(cron = "0 0 0 31 12 *")
    public void deleteTrashEnterpriseOperate() {
        trashDataClearService.removeExpireEnterpriseOperate();
    }

    @Scheduled(cron = "0 0 0 31 12 *")
    public void deleteTrashRoleOperate() {
        trashDataClearService.removeExpireRoleOperate();
    }

}
