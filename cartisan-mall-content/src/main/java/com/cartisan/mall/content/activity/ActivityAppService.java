package com.cartisan.mall.content.activity;

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
public class ActivityAppService {
    private final ActivityRepository repository;

    private final ActivityConverter converter = ActivityConverter.CONVERTER;

    public ActivityAppService(ActivityRepository repository) {
        this.repository = repository;
    }

    public PageResult<ActivityDto> searchActivities(@NonNull ActivityQuery activityQuery, @NonNull Pageable pageable) {
        final Page<Activity> searchResult = repository.findAll(querySpecification(activityQuery),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));

        return new PageResult<>(searchResult.getTotalElements(), searchResult.getTotalPages(),
                converter.convert(searchResult.getContent()));
    }

    public ActivityDto getActivity(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public ActivityDto addActivity(ActivityParam activityParam) {
        final Activity activity = new Activity(activityParam.getTitle(),
        activityParam.getStart(),
        activityParam.getEnd(),
        activityParam.getContent(),
        activityParam.getStatus());

        return converter.convert(repository.save(activity));
    }

    @Transactional(rollbackOn = Exception.class)
    public ActivityDto editActivity(Long id, ActivityParam activityParam) {
        final Activity activity = requirePresent(repository.findById(id));

        activity.describe(activityParam.getTitle(),
        activityParam.getStart(),
        activityParam.getEnd(),
        activityParam.getContent(),
        activityParam.getStatus());

        return converter.convert(repository.save(activity));
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeActivity(long id) {
        repository.deleteById(id);
    }
}
