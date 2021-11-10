package com.cartisan.mall.order.returncause;

import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
public class ReturnCauseAppService {
    private final ReturnCauseRepository repository;

    private final ReturnCauseConverter converter = ReturnCauseConverter.CONVERTER;

    public ReturnCauseAppService(ReturnCauseRepository repository) {
        this.repository = repository;
    }

    public List<ReturnCauseDto> searchReturnCauses() {
        return converter.convert(repository.findAll());
    }

    public ReturnCauseDto getReturnCause(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ReturnCauseDto addReturnCause(ReturnCauseParam returnCauseParam) {
        final ReturnCause returnCause = new ReturnCause(returnCauseParam.getCause(),
                returnCauseParam.getEnable(),
                returnCauseParam.getSequence());

        return converter.convert(repository.save(returnCause));
    }

    @Transactional(rollbackOn = Exception.class)
    public ReturnCauseDto editReturnCause(Long id, ReturnCauseParam returnCauseParam) {
        final ReturnCause returnCause = requirePresent(repository.findById(id));

        returnCause.describe(returnCauseParam.getCause(),
                returnCauseParam.getEnable(),
                returnCauseParam.getSequence());

        return converter.convert(repository.save(returnCause));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeReturnCause(long id) {
        repository.deleteById(id);
    }
}
