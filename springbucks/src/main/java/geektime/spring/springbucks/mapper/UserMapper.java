package geektime.spring.springbucks.mapper;

import geektime.spring.springbucks.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> getUser(int id);

    List<User> getAllUser();

    int addUser(@Param("user") User user);

    int updateUser(@Param("user") User user);

    int deleteUser(@Param("user") User user);
}
