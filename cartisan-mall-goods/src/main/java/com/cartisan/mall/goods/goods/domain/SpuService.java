package com.cartisan.mall.goods.goods.domain;

import com.cartisan.mall.goods.goods.repository.SpuRepository;
import com.cartisan.mall.goods.goods.request.SpuParam;
import com.cartisan.util.SnowflakeIdWorker;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class SpuService {
    private final SpuRepository repository;
    private final SnowflakeIdWorker idWorker;
    private final SpuLogService spuLogService;

    public SpuService(SpuRepository repository, SnowflakeIdWorker idWorker, SpuLogService spuLogService) {
        this.repository = repository;
        this.idWorker = idWorker;
        this.spuLogService = spuLogService;
    }

    public Spu getSpu(Long id) {
        return requirePresent(repository.findById(id));
    }

    @Transactional(rollbackOn = Exception.class)
    public Spu addSpu(SpuParam spuParam) {
        final Spu spu = new Spu(idWorker.nextId(),
                spuParam.getSn(),
                spuParam.getName(),
                spuParam.getCaption(),
                spuParam.getBrandId(),
                spuParam.getCategory1Id(),
                spuParam.getCategory2Id(),
                spuParam.getCategory3Id(),
                spuParam.getTemplateId(),
                spuParam.getImage(),
                spuParam.getImages(),
                spuParam.getSaleService(),
                spuParam.getIntroduction(),
                spuParam.getSpecificationItems(),
                spuParam.getParameterItems());

        final Map<String, Object> logData = getLogData(spu);

        logSpuData(spu.getId(), new HashMap<>(), logData);

        return repository.save(spu);
    }

    @Transactional(rollbackOn = Exception.class)
    public Spu editSpu(Long id, SpuParam spuParam) {
        final Spu spu = requirePresent(repository.findById(id));

        final Map<String, Object> originLogData = getLogData(spu);

        spu.describe(spuParam.getSn(),
                spuParam.getName(),
                spuParam.getCaption(),
                spuParam.getBrandId(),
                spuParam.getCategory1Id(),
                spuParam.getCategory2Id(),
                spuParam.getCategory3Id(),
                spuParam.getTemplateId(),
                spuParam.getImage(),
                spuParam.getImages(),
                spuParam.getSaleService(),
                spuParam.getIntroduction(),
                spuParam.getSpecificationItems(),
                spuParam.getParameterItems());

        repository.save(spu);

        final Map<String, Object> newLogData = getLogData(spu);

        logSpuData(spu.getId(), originLogData, newLogData);

        return spu;
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeSpu(long id) {
        repository.deleteById(id);
    }

    @Transactional(rollbackOn = Exception.class)
    public void audit(Long spuId, Integer status) {
        final Spu spu = requirePresent(repository.findById(spuId));
        final Map<String, Object> originLogData = getLogData(spu);

        spu.setAuditStatus(status);
        repository.save(spu);

        final Map<String, Object> newLogData = getLogData(spu);

        logSpuData(spu.getId(), originLogData, newLogData);
    }

    @Transactional(rollbackOn = Exception.class)
    public void pull(Long spuId) {
        final Spu spu = requirePresent(repository.findById(spuId));
        final Map<String, Object> originLogData = getLogData(spu);

        spu.setIsMarketable(false);
        repository.save(spu);

        final Map<String, Object> newLogData = getLogData(spu);
        logSpuData(spu.getId(), originLogData, newLogData);
    }

    @Transactional(rollbackOn = Exception.class)
    public void put(Long spuId) {
        final Spu spu = requirePresent(repository.findById(spuId));

        if (spu.getAuditStatus() != 1) {
            throw new RuntimeException("此商品未通过审核。");
        }
        final Map<String, Object> originLogData = getLogData(spu);

        spu.setIsMarketable(true);
        repository.save(spu);

        final Map<String, Object> newLogData = getLogData(spu);
        logSpuData(spu.getId(), originLogData, newLogData);
    }

    private Map<String, Object> getLogData(Spu spu) {
        final HashMap<String, Object> logData = new HashMap<>();

        logData.put("isMarketable", spu.getIsMarketable());
        logData.put("auditStatus", spu.getAuditStatus());

        return logData;
    }

    private void logSpuData(Long spuId, Map<String, Object> originData, Map<String, Object> newData) {
        final Gson gson = new Gson();
        final HashMap<String, Map<String, Object>> data = new HashMap<>();

        data.put("origin", originData);
        data.put("new", newData);

        spuLogService.addSpuLog(spuId,  gson.toJson(data), "admin");
    }
}
