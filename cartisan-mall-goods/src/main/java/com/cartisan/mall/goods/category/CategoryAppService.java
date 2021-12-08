package com.cartisan.mall.goods.category;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.TreeNode;
import com.cartisan.exception.CartisanException;
import com.cartisan.mall.goods.category.mapper.CategoryMapper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.dto.TreeNode.buildTree;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
@Slf4j
public class CategoryAppService {
    private final CategoryRepository repository;
    private final CategoryMapper categoryMapper;

    private final CategoryConverter converter = CategoryConverter.CONVERTER;


    public CategoryAppService(CategoryRepository repository, CategoryMapper categoryMapper) {
        this.repository = repository;
        this.categoryMapper = categoryMapper;
    }

    public List<TreeNode> getCategoryTree() {
        return buildTree(categoryMapper.getCategoryTree());
    }

    public CategoryDto getCategory(@NonNull Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public void addCategory(@NonNull CategoryParam categoryParam) {
        final Category category = new Category(
                categoryParam.getParentId(),
                categoryParam.getName(),
                categoryParam.getIcon());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editCategory(@NonNull Long id, @NonNull CategoryParam categoryParam) {
        final Category category = requirePresent(repository.findById(id));

        category.describe(categoryParam.getName(), categoryParam.getIcon());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void moveCategories(@NonNull List<MoveCategoryCommand> commands) {
        commands.forEach(command->{
            final Category category = requirePresent(repository.findById(command.getId()));
            category.move(command.getParentId(), command.getSequence());

            repository.save(category);
        });
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(@NonNull Long id) {
        if (repository.existsByParentId(id)) {
            throw new CartisanException(CodeMessage.VALIDATE_ERROR.fillArgs("分类下存在子分类，不能删除。"));
        }

        repository.deleteById(id);
    }
}
