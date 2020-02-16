package com.eshare.service;

import com.alibaba.cola.command.CommandBusI;
import com.alibaba.cola.dto.Response;
import com.alibaba.cola.dto.SingleResponse;
import com.alibaba.cola.exception.BizException;
import com.eshare.QuotaStatusUpdateCmdTypeEnum;
import com.eshare.QuotaUpdateCmdTypeEnum;
import com.eshare.api.CreditFacilityServiceI;
import com.eshare.dto.*;
import com.eshare.dto.domainmodel.CustomerLimit;
import com.eshare.dto.domainmodel.ProductLimit;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author Evan Leung
 * <p>
 * 额度服务类
 */
@Service
public class CreditFacilityServiceImpl implements CreditFacilityServiceI {

    @Autowired
    private CommandBusI commandBus;

    @Override
    public SingleResponse<ProductLimit> registerAccount(CreditLimitRegisterCmd creditLimitRegisterCmd) {
        return (SingleResponse<ProductLimit>) commandBus.send(creditLimitRegisterCmd);
    }

    @Override
    public Response freezeQuota(QuotaFreezeCmd quotaFreezeCmd) {
        return commandBus.send(quotaFreezeCmd);
    }

    @Override
    public Response unfreezeQuota(QuotaUnfreezeCmd quotaUnfreezeCmd) {
        return commandBus.send(quotaUnfreezeCmd);
    }

    @Override
    public Response subtractQuota(QuotaSubtractionCmd quotaSubtractionCmd) {
        return commandBus.send(quotaSubtractionCmd);
    }

    @Override
    public Response recoverQuota(QuotaRecoveryCmd quotaRecoveryCmd) {
        return commandBus.send(quotaRecoveryCmd);
    }

    @Override
    public Response freezeProductQuotaBySys(ProductQuotaSysFreezeCmd productQuotaSysFreezeCmd) {
        return commandBus.send(productQuotaSysFreezeCmd);
    }

    @Override
    public Response freezeProductQuotaByMan(ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd) {
        return commandBus.send(productQuotaManualFreezeCmd);
    }

    @Override
    public Response unfreezeProductQuota(ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd) {
        return commandBus.send(productQuotaUnfreezeCmd);
    }

    @Override
    public Response forceUnfreezeProductQuota(ProductQuotaForcedUnfreezeCmd productQuotaUnfreezeCmd) {
        return commandBus.send(productQuotaUnfreezeCmd);
    }

    @Override
    public Response inactivateProductQuota(ProductQuotaInactiveCmd productQuotaInactiveCmd) {
        return commandBus.send(productQuotaInactiveCmd);
    }

    @Override
    public Response activateProductQuota(ProductQuotaActivateCmd productQuotaActivateCmd) {
        return commandBus.send(productQuotaActivateCmd);
    }

    @Override
    public Response abandonProductQuota(ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd) {
        return commandBus.send(productQuotaAbandonmentCmd);
    }

    @Override
    public Response changeQuota(QuotaChangeCmd qotaChangeCmd) {
        return commandBus.send(qotaChangeCmd);
    }

    @Override
    public Response dispatchQuotaChangeCmd(BaseQuotaAmountUpdateCmd cmd) {
        QuotaUpdateCmdTypeEnum quotaUpdateCmdTypeEnum = QuotaUpdateCmdTypeEnum.fromValue(cmd.getCommandType());
        switch (quotaUpdateCmdTypeEnum) {
            case CHANGE_QUOTA:
                QuotaChangeCmd quotaChangeCmd = new QuotaChangeCmd();
                BeanUtils.copyProperties(cmd, quotaChangeCmd);
                return commandBus.send(quotaChangeCmd);
            case FREEZE_QUOTA:
                QuotaFreezeCmd quotaFreezeCmd = new QuotaFreezeCmd();
                BeanUtils.copyProperties(cmd, quotaFreezeCmd);
                return commandBus.send(quotaFreezeCmd);
            case UNFREEZE_QUOTA:
                QuotaUnfreezeCmd quotaUnfreezeCmd = new QuotaUnfreezeCmd();
                BeanUtils.copyProperties(cmd, quotaUnfreezeCmd);
                return commandBus.send(quotaUnfreezeCmd);
            case SUBTRACT_QUOTA:
                QuotaSubtractionCmd quotaSubtractionCmd = new QuotaSubtractionCmd();
                BeanUtils.copyProperties(cmd, quotaSubtractionCmd);
                return commandBus.send(quotaSubtractionCmd);

            case RECOVER_QUOTA:
                QuotaRecoveryCmd quotaRecoveryCmd = new QuotaRecoveryCmd();
                BeanUtils.copyProperties(cmd, quotaRecoveryCmd);
                return commandBus.send(quotaRecoveryCmd);
            default:
                throw new BizException("Command Type Not Found, uniqueIdentity=" + cmd.getBizScenario().getUniqueIdentity());
        }
    }

    @Override
    public Response dispatchQuotaStatusChangeCmd(BaseQuotaStatusChangeCmd cmd) {
        QuotaStatusUpdateCmdTypeEnum quotaStatusUpdateCmdTypeEnum = QuotaStatusUpdateCmdTypeEnum.fromValue(cmd.getCommandType());
        switch (quotaStatusUpdateCmdTypeEnum) {
            case SYS_FREEZE:
                ProductQuotaSysFreezeCmd poductQuotaSysFreezeCmd = new ProductQuotaSysFreezeCmd();
                BeanUtils.copyProperties(cmd, poductQuotaSysFreezeCmd);
                return commandBus.send(poductQuotaSysFreezeCmd);
            case MANUAL_FREEZE:
                ProductQuotaManualFreezeCmd productQuotaManualFreezeCmd = new ProductQuotaManualFreezeCmd();
                BeanUtils.copyProperties(cmd, productQuotaManualFreezeCmd);
                return commandBus.send(productQuotaManualFreezeCmd);
            case UNFREEZE:
                ProductQuotaUnfreezeCmd productQuotaUnfreezeCmd = new ProductQuotaUnfreezeCmd();
                BeanUtils.copyProperties(cmd, productQuotaUnfreezeCmd);
                return commandBus.send(productQuotaUnfreezeCmd);
            case FORCED_UNFREEZE:
                ProductQuotaForcedUnfreezeCmd productQuotaForcedUnfreezeCmd = new ProductQuotaForcedUnfreezeCmd();
                BeanUtils.copyProperties(cmd, productQuotaForcedUnfreezeCmd);
                return commandBus.send(productQuotaForcedUnfreezeCmd);

            case ACTIVE:
                ProductQuotaActivateCmd productQuotaActivateCmd = new ProductQuotaActivateCmd();
                BeanUtils.copyProperties(cmd, productQuotaActivateCmd);
                return commandBus.send(productQuotaActivateCmd);
            case INACTIVE:
                ProductQuotaInactiveCmd productQuotaInactiveCmd = new ProductQuotaInactiveCmd();
                BeanUtils.copyProperties(cmd, productQuotaInactiveCmd);
                return commandBus.send(productQuotaInactiveCmd);
            case ABANDON:
                ProductQuotaAbandonmentCmd productQuotaAbandonmentCmd = new ProductQuotaAbandonmentCmd();
                BeanUtils.copyProperties(cmd, productQuotaAbandonmentCmd);
                return commandBus.send(productQuotaAbandonmentCmd);
            default:
                throw new BizException("Command Type Not Found, uniqueIdentity=" + cmd.getBizScenario().getUniqueIdentity());
        }
    }

    @Override
    public SingleResponse<CustomerLimit> fetchCustomerLimit(CustomerLimitQryCmd customerLimitQryCmd) {
        return (SingleResponse<CustomerLimit>) commandBus.send(customerLimitQryCmd);
    }

    @Override
    public SingleResponse<ProductLimit> fetchProductLimit(ProductLimitQryCmd productLimitQryCmd) {
        return (SingleResponse<ProductLimit>) commandBus.send(productLimitQryCmd);
    }
}
