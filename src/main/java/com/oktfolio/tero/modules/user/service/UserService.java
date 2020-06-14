package com.oktfolio.tero.modules.user.service;

import java.util.List;

import com.oktfolio.tero.model.user.UserExample;
import com.oktfolio.tero.model.user.UserDO;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public interface UserService {

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserDO record);

    int insertOrUpdate(UserDO record);

    int insertOrUpdateSelective(UserDO record);

    int insertSelective(UserDO record);

    List<UserDO> selectByExample(UserExample example);

    UserDO selectByPrimaryKey(Long id);

    int updateByExampleSelective(UserDO record, UserExample example);

    int updateByExample(UserDO record, UserExample example);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    int updateBatch(List<UserDO> list);

    int batchInsert(List<UserDO> list);

}
