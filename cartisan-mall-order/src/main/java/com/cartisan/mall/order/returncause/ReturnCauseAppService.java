package com.cartisan.mall.order.returncause;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.PageResult;
import com.cartisan.exception.CartisanException;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.repository.ConditionSpecifications.querySpecification;
import static com.cartisan.util.AssertionUtil.requirePresent;
import static java.util.stream.Collectors.toList;

@Service
public class ReturnCauseAppService {
    private final ReturnCauseRepository repository;

    private final ReturnCauseConverter converter = ReturnCauseConverter.CONVERTER;

    public ReturnCauseAppService(ReturnCauseRepository repository) {
        this.repository = repository;
    }

    public PageResult<ReturnCauseDto> searchReturnCauses(@NonNull ReturnCauseQuery returnCauseQuery, @NonNull Pageable pageable) {
        final Page<ReturnCause> searchResult = repository.findAll(querySpecification(returnCauseQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
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
