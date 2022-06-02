package geektime.spring.springbucks.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import geektime.spring.springbucks.mapper.UserMapper;
import geektime.spring.springbucks.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getUser(int id){
        return userMapper.getUser(id);
    }

    public PageInfo<User> getUserByPage(int pageNum, int pagesize){
        //开始分页
        PageHelper.startPage(pageNum,pagesize);
        List<User> user=userMapper.getAllUser();
        PageInfo<User> userPageInfo=new PageInfo<>(user);
        return userPageInfo;
    }

    @Transactional
    public void addUser(User user){
        int size = userMapper.addUser(user);
        if(size != 1 ){
            System.out.println("插入失败");
        }
    }

    @Transactional
    public void updateUser(User user){
        int size = userMapper.updateUser(user);
        if(size != 1 ){
            System.out.println("更新失败");
        }
    }

    @Transactional
    public void deleteUser(User user){
        int size = userMapper.deleteUser(user);
        if(size != 1 ){
            System.out.println("删除失败");
        }
    }
}
