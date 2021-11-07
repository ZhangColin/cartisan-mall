package com.cartisan.mall.goods.goods.domain;

import com.cartisan.mall.goods.goods.repository.SpuAuditRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author colin
 */
@Service
public class SpuAuditService {
    private final SpuAuditRepository repository;

    public SpuAuditService(SpuAuditRepository repository) {
        this.repository = repository;
    }

    public List<SpuAudit> getSpuAudits(Long spuId) {
        return repository.findBySpuId(spuId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void addSpuAudit(Long spuId, String result, String feedback, String auditor) {
        final SpuAudit spuAudit = new SpuAudit(spuId, result, feedback, auditor);

        repository.save(spuAudit);
    }

}
