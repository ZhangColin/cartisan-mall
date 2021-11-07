package com.cartisan.mall.goods.goods.domain;

import com.cartisan.mall.goods.goods.repository.SpuLogRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author colin
 */
@Service
public class SpuLogService {
    private final SpuLogRepository repository;

    public SpuLogService(SpuLogRepository repository) {
        this.repository = repository;
    }

    public List<SpuLog> getSpuLogs(Long spuId) {
        return repository.findBySpuId(spuId);
    }

    @Transactional(rollbackOn = Exception.class)
    public void addSpuLog(Long spuId, String content, String operator) {
        final SpuLog spuLog = new SpuLog(spuId, content, operator);

        repository.save(spuLog);
    }

}
