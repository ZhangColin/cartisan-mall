package com.cartisan.mall.goods.category;

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
@Constraint(validatedBy = CategoryVerify.Validator.class)
public @interface CategoryVerify {
    String message() default "分类不存在";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    boolean allowVirtualTopCategory() default false;

    class Validator implements ConstraintValidator<CategoryVerify, Long> {
        private final CategoryRepository categoryRepository;
        private boolean allowVirtualTopCategory;

        public Validator(CategoryRepository categoryRepository) {
            this.categoryRepository = categoryRepository;
        }

        @Override
        public void initialize(CategoryVerify constraintAnnotation) {
            allowVirtualTopCategory = constraintAnnotation.allowVirtualTopCategory();
        }

        @Override
        public boolean isValid(Long value, ConstraintValidatorContext context) {
            if (allowVirtualTopCategory && value == 0) {
                return true;
            }

            return categoryRepository.existsById(value);
        }
    }
}
