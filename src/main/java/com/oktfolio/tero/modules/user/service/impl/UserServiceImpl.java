package com.oktfolio.tero.modules.user.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.oktfolio.tero.dao.mapper.UserMapper;
import java.util.List;
import com.oktfolio.tero.model.user.UserExample;
import com.oktfolio.tero.model.user.UserDO;
import com.oktfolio.tero.modules.user.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public long countByExample(UserExample example) {
        return userMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(UserExample example) {
        return userMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(UserDO record) {
        return userMapper.insert(record);
    }

    @Override
    public int insertOrUpdate(UserDO record) {
        return userMapper.insertOrUpdate(record);
    }

    @Override
    public int insertOrUpdateSelective(UserDO record) {
        return userMapper.insertOrUpdateSelective(record);
    }

    @Override
    public int insertSelective(UserDO record) {
        return userMapper.insertSelective(record);
    }

    @Override
    public List<UserDO> selectByExample(UserExample example) {
        return userMapper.selectByExample(example);
    }

    @Override
    public UserDO selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByExampleSelective(UserDO record, UserExample example) {
        return userMapper.updateByExampleSelective(record,example);
    }

    @Override
    public int updateByExample(UserDO record, UserExample example) {
        return userMapper.updateByExample(record,example);
    }

    @Override
    public int updateByPrimaryKeySelective(UserDO record) {
        return userMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(UserDO record) {
        return userMapper.updateByPrimaryKey(record);
    }

    @Override
    public int updateBatch(List<UserDO> list) {
        return userMapper.updateBatch(list);
    }

    @Override
    public int batchInsert(List<UserDO> list) {
        return userMapper.batchInsert(list);
    }

}
