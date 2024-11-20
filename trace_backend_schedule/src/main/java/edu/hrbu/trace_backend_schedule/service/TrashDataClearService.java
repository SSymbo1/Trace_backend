package edu.hrbu.trace_backend_schedule.service;

public interface TrashDataClearService {

    void removeUnusedAvatar();

    void removeUnusedGoodsPhoto();

    void removeExpireAccountOperate();

    void removeExpireEnterpriseOperate();

    void removeExpireRoleOperate();

}
