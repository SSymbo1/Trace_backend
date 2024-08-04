package edu.hrbu.trace_backend.cron.service;

public interface TrashDataClearService {

    void removeUnusedAvatar();

    void removeUnusedGoodsPhoto();

    void removeExpireAccountOperate();

    void removeExpireEnterpriseOperate();

    void removeExpireRoleOperate();

}
