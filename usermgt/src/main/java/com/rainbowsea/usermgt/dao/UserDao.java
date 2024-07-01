package com.rainbowsea.usermgt.dao;

import com.rainbowsea.usermgt.bean.User;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository  // 交给 Spring IOC 容器管理
public class UserDao {
    private static List<User> users = new ArrayList<>();


    static {
        // 类加载是初始化数据
        // 创建 User 对象
        User user1 = new User(1001L, "张三", 2, "zhangsan@rainbowsea.com");
        User user2 = new User(1002L, "李四", 2, "lisin@rainbowsea.com");
        User user3 = new User(1003L, "李华", 2, "lihua@rainbowsea.com");
        User user4 = new User(1004L, "小红", 1, "xiaohong@rainbowsea.com");
        User user5 = new User(1005L, "小智", 1, "xiaozhi@rainbowsea.com");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        // 将 User 对象存储到 List 集合中

    }

    /**
     * 查询所有的用户信息
     *
     * @return
     */
    public List<User> selectAll() {
        return users;
    }

    /**
     * 专门生成 ID的
     * 找到其中的最大值  + 1
     *
     * @return
     */
    public Long generateld() {
        // 使用 Steam API
        Long maxId = users.stream().map(user -> user.getId()).reduce((id1, id2) -> id1 > id2 ? id1 : id2).get();

        return maxId + 1;
    }

    /**
     * 保存用户信息
     *
     * @param user
     */
    public void insert(User user) {
        // 生成 id
        Long id = generateld();
        // 给 user 对象 id 属性赋值
        user.setId(id);
        // 保存
        users.add(user);
    }


    /**
     * 根据 id 查询用户信息
     *
     * @param id
     * @return
     */
    public User selectById(Long id) {
        // Steam API
        User user2 = users.stream().filter(user -> user.getId().equals(id)).findFirst().get();
        return user2;
    }


    /**
     * 修改用户信息
     * @param user
     */
    public void upate(User user) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(user.getId())) {
                users.set(i,user);
                return;
            }
        }
    }


    /**
     * 删除用户信息
     * @param id
     */
    public void deleteByid(Long id) {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id)) {
                // 移除
                users.remove(i);
                return;
            }
        }
    }
}
