package com.cartisan.mall.goods.brand;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author colin
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = BrandVerify.Validator.class)
public @interface BrandVerify {
    String message() default "品牌不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<BrandVerify, Long> {
        private final BrandRepository brandRepository;

        public Validator(BrandRepository brandRepository) {
            this.brandRepository = brandRepository;
        }

        @Override
        public boolean isValid(Long value, ConstraintValidatorContext context) {
            return brandRepository.existsById(value);
        }
    }
}
