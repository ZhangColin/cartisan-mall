package com.cartisan.mall.content.ad;

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
public class AdAppService {
    private final AdRepository repository;

    private final AdConverter converter = AdConverter.CONVERTER;

    public AdAppService(AdRepository repository) {
        this.repository = repository;
    }

    public PageResult<AdDto> searchAds(@NonNull AdQuery adQuery, @NonNull Pageable pageable) {
        final Page<Ad> searchResult = repository.findAll(querySpecification(adQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public AdDto getAd(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public AdDto addAd(AdParam adParam) {
        final Ad ad = new Ad(adParam.getName(),
        adParam.getPosition(),
        adParam.getStart(),
        adParam.getEnd(),
        adParam.getStatus(),
        adParam.getImage(),
        adParam.getUrl(),
        adParam.getRemarks());

        return converter.convert(repository.save(ad));
    }

    @Transactional(rollbackOn = Exception.class)
    public AdDto editAd(Long id, AdParam adParam) {
        final Ad ad = requirePresent(repository.findById(id));

        ad.describe(adParam.getName(),
        adParam.getPosition(),
        adParam.getStart(),
        adParam.getEnd(),
        adParam.getStatus(),
        adParam.getImage(),
        adParam.getUrl(),
        adParam.getRemarks());

        return converter.convert(repository.save(ad));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeAd(long id) {
        repository.deleteById(id);
    }
}
