package com.hb;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hb.mapper.UserMapper;
import com.hb.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class Mybatisplus01ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    void contextLoads() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("hb");
        user.setId(6L);
        user.setAge(22);
        user.setEmail("281187@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user);
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setName("hbupdate");
        user.setId(6L);
        int i = userMapper.updateById(user);
        System.out.println(i);
    }


    @Test
    public void testOptimisticLocker(){
        //查询用户信息
        User user = userMapper.selectById(1);
        //修改用户信息
        user.setName("hbbbb");
        user.setEmail("11111@qq.com");
        //执行更新操作
        int i = userMapper.updateById(user);
    }



    @Test//多线程
    public void testOptimisticLocker2(){
        User user = userMapper.selectById(1);
        user.setName("hbbbb111111");
        user.setEmail("11111@qq.com");

        //另一个操作插队了
        User user2 = userMapper.selectById(1);
        user2.setName("hbbbb2222");
        user2.setEmail("22222@qq.com");
        userMapper.updateById(user2);

        userMapper.updateById(user);
    }


    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1);
        System.out.println(user);
    }

    @Test
    public void testSelectByBathId(){
        List<User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
        System.out.println(users);
    }

    @Test
    public void testSelectByMap(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("name","Jack");
        List<User> users = userMapper.selectByMap(map);
        System.out.println(users);
    }



    @Test//fenye
    public void testPage(){
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page, null);
        page.getRecords().forEach(System.out::println);
    }


    @Test//删除
    public void testDelete() {
        int i = userMapper.deleteById(2);
    }

}
