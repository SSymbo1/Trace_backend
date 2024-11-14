package edu.hrbu.trace_backend_job.cron.service;

public interface TrashDataClearService {

    void removeUnusedAvatar();

    void removeUnusedGoodsPhoto();

    void removeExpireAccountOperate();

    void removeExpireEnterpriseOperate();

    void removeExpireRoleOperate();

}
