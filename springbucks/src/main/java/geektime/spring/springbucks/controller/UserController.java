package geektime.spring.springbucks.controller;

import com.github.pagehelper.PageInfo;
import geektime.spring.springbucks.model.Student;
import geektime.spring.springbucks.model.User;
import geektime.spring.springbucks.service.CacheService;
import geektime.spring.springbucks.service.UserService;
import geektime.spring.springbucks.util.BitMap;
import geektime.spring.springbucks.util.IdGenerateUtil;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/userInfo")
@Slf4j
@Api(tags="用户模块--作业")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CacheService cacheService;

    /*（1）实现：查询，查询时需要实现翻页 查询时增加将结果缓存到 redis*/
    //访问地址：http://localhost:8088/userInfo/getUserInfoByPage
    @GetMapping("/getUserInfoByPage")
    public void getUserInfoByPage(){
        //分页查询--将结果缓存到 redis
        int pageNum = 1;   //页数
        int pageSize = 2;  //每页大小
        PageInfo<User> userPageInfo = userService.getUserByPage(pageNum,pageSize);
        List<User> userPageList = userPageInfo.getList();
        for(User user:userPageList){
            //查询时增加将结果缓存到 redis
            cacheService.add(user.getUsername(),user.getPassword());
            //输出缓存结果
            System.out.println(cacheService.get(user.getUsername()));
        }
    }

    /*（1）实现：查询，查询时需要实现根据主键批量查询 查询时增加将结果缓存到 redis*/
    //访问地址：http://localhost:8088/userInfo/getUserInfoById
    @GetMapping("/getUserInfoById")
    public void getUserInfoById(){
        //根据主键查询--将结果缓存到 redis
        int id = 3;
        List<User> userList = userService.getUser(id);
        for(User user:userList){
            //查询时增加将结果缓存到 redis
            cacheService.add(user.getUsername(),user.getPassword());
            //输出缓存结果
            System.out.println(cacheService.get(user.getUsername()));
        }
    }

    /*（1）新增-加入事务管理，通过注解*/
    //访问地址：http://localhost:8088/userInfo/addUserInfo
    @GetMapping("/addUserInfo")
    public void addUserInfo(){
        User user = new User();
        user.setUsername("测试新增");
        user.setPassword(String.valueOf(System.currentTimeMillis()));
        userService.addUser(user);
    }

    /*（1）修改加入事务管理，通过注解*/
    //访问地址：http://localhost:8088/userInfo/updateUserInfo
    @GetMapping("/updateUserInfo")
    public void updateUserInfo(){
        int id = 3;
        List<User> userList = userService.getUser(id);
        User user =userList.get(0);
        user.setPassword(String.valueOf(System.currentTimeMillis()));
        userService.updateUser(user);
    }

    /*（1）删除加入事务管理，通过注解*/
    //访问地址：http://localhost:8088/userInfo/deleteUserInfo
    @GetMapping("/deleteUserInfo")
    public void deleteUserInfo(){
        int id = 4;
        List<User> userList = userService.getUser(id);
        if(userList.size()>0){
            User user =userList.get(0);
            userService.deleteUser(user);
        }else{
            System.out.println("该用户不存在");
        }
    }


    /*（5）实现分数排名或者排行榜；*/
    //访问地址：http://localhost:8088/userInfo/getStudentScore
    @GetMapping("/getStudentScore")
    public void getStudentScore(){
        List<Student> list =new ArrayList<>();
        list.add(new Student("张三", new BigDecimal("99.01")));
        list.add(new Student("李四", new BigDecimal("80")));
        list.add(new Student("王五", new BigDecimal("90.85")));
        list.add(new Student("赵六", new BigDecimal("60")));

        list.sort(new Comparator<Student>() {
            @Override
            public int compare(Student student1, Student student2) {
                BigDecimal score1 = student1.getScore();
                BigDecimal score2 = student2.getScore();
                return score2.compareTo(score1);
            }
        });
        int i = 1;
        for (Student stu : list) {
            System.out.println("姓名:"+stu.getUsername()+"---"+"成绩:"+stu.getScore()+"---"+"名次:"+i);
            i++;
        }
    }

    /*（5）实现全局 ID 生成；*/
    //访问地址：http://localhost:8088/userInfo/getIdGenerate
    @GetMapping("/getIdGenerate")
    public void getIdGenerate(){
        IdGenerateUtil util = new IdGenerateUtil();
        System.out.println(util.nextId());
    }

    /*（5）基于 Bitmap 实现 id 去重；*/
    //访问地址：http://localhost:8088/userInfo/bitMapQuChong
    @GetMapping("/bitMapQuChong")
    public void bitMapQuChong(){
        new BitMap().findDuplicate();
        new BitMap().findDup_jdk();
    }
}
