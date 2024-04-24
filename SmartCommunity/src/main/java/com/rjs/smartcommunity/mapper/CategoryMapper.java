package com.rjs.smartcommunity.mapper;

import com.rjs.smartcommunity.entity.Category;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;



/**
 * @author rjs
 */
@Mapper
public interface CategoryMapper {

    /**
     * 新增一个Category记录到数据库中。
     * 
     * @param category 要插入的Category对象，包含完整的类别信息。
     * @return 返回受影响的行数。如果插入成功，返回1；否则，返回0或其他负值表示插入失败。
     */
    int insert(Category category);

    /**
     * 根据给定的ID从数据库中删除对应的Category记录。
     * 
     * @param id 需要删除的Category记录的唯一标识符（主键）。
     * @return 返回受影响的行数。如果删除成功，返回1；否则，返回0或其他负值表示删除失败。
     */
    int deleteById(Integer id);

    /**
     * 更新数据库中与给定Category对象匹配的记录。
     * 
     * @param category 包含更新后信息的Category对象。其主键字段应设置为待更新记录的ID。
     * @return 返回受影响的行数。如果更新成功，返回1；否则，返回0或其他负值表示更新失败。
     */
    int updateById(Category category);

    /**
     * 根据指定的ID从数据库中查询并返回对应的Category记录。
     * 
     * @param id 要查询的Category记录的唯一标识符（主键）。
     * @return 返回与指定ID匹配的Category对象。如果没有找到对应记录，则返回null。
     */
    Category selectById(Integer id);

    /**
     * 查询数据库中的所有Category记录，可按传入的Category对象进行条件筛选。
     * 
     * @param category 可选的筛选条件对象。通常用于设置查询的过滤条件，如按特定字段值进行筛选。若未设置任何筛选条件，将返回全部Category记录。
     * @return 返回包含所有匹配Category记录的List。如果未找到符合条件的记录，返回空列表。
     */
    List<Category> selectAll(Category category);

}
