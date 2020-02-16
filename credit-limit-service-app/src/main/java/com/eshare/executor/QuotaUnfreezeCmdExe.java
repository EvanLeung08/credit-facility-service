package com.eshare.executor;

import com.alibaba.cola.command.Command;
import com.alibaba.cola.command.CommandExecutorI;
import com.alibaba.cola.dto.Response;
import com.eshare.domain.creditlimit.ProductLimit;
import com.eshare.dto.QuotaFreezeCmd;
import com.eshare.dto.QuotaUnfreezeCmd;
import com.eshare.repository.ProductLimitRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 额度解冻执行类
 * @Author Evan Leung
 *
 */
@Command
public class QuotaUnfreezeCmdExe implements CommandExecutorI<Response, QuotaUnfreezeCmd> {

    private final ProductLimitRepository productLimitRepository;


    @Autowired
    public QuotaUnfreezeCmdExe(ProductLimitRepository productLimitRepository) {
        this.productLimitRepository = productLimitRepository;
    }

    @Override
    public Response execute(QuotaUnfreezeCmd cmd) {
        ProductLimit productLimit = productLimitRepository.find(cmd.getCardId());
        // 冻结额度
        productLimitRepository.unfreezeAmount(productLimit, cmd.getOperationAmount());
        return Response.buildSuccess();
    }
}
