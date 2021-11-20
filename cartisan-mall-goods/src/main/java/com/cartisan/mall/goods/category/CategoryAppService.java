package com.cartisan.mall.goods.category;

import com.cartisan.constant.CodeMessage;
import com.cartisan.dto.TreeNode;
import com.cartisan.exception.CartisanException;
import com.cartisan.mall.goods.category.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static com.cartisan.dto.TreeNode.buildTree;
import static com.cartisan.util.AssertionUtil.requirePresent;

/**
 * @author colin
 */
@Service
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

    public CategoryDto getCategory(Long id) {
        return converter.convert(requirePresent(repository.findById(id)));
    }

    @Transactional(rollbackOn = Exception.class)
    public void addCategory(CategoryParam categoryParam) {
        final Category category = new Category(
                categoryParam.getParentId(),
                categoryParam.getName(),
                categoryParam.getIsShow(),
                categoryParam.getIsMenu());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void editCategory(Long id, CategoryParam categoryParam) {
        final Category category = requirePresent(repository.findById(id));

        category.describe(
                categoryParam.getName(),
                categoryParam.getIsShow(),
                categoryParam.getIsMenu());

        repository.save(category);
    }

    @Transactional(rollbackOn = Exception.class)
    public void moveCategories(List<MoveCategoryCommand> commands) {
        commands.forEach(command->{
            final Category category = requirePresent(repository.findById(command.getId()));
            category.move(command.getParentId(), command.getSequence());

            repository.save(category);
        });
    }

    @Transactional(rollbackOn = Exception.class)
    public void removeCategory(long id) {
        if (repository.existsByParentId(id)) {
            throw new CartisanException(CodeMessage.VALIDATE_ERROR.fillArgs("分类下存在子分类，不能删除。"));
        }

        repository.deleteById(id);
    }
}
