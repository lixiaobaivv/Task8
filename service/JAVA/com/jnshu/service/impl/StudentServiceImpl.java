package com.jnshu.service.impl;


import com.alibaba.fastjson.JSON;
import com.jnshu.entity.Student;

import com.jnshu.mapper.StudentDao;

import com.jnshu.service.StudentService;
import com.jnshu.tools.MemcachedUtil;
import com.jnshu.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import redis.clients.jedis.Jedis;


import java.util.List;

@Service("studentServiceImpl")
public class StudentServiceImpl implements StudentService {

    private Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentDao studentDao;

    @Override
    public List<Student> findAll() {
        return studentDao.findAll();
    }
/*

    @Override
    public Student getID(Long id){
        Student student;
        if (redisUtil.get("name1")!=null){
            student = (Student) redisUtil.get("name1"+id);
            logger.info("这是缓存里的数据");
            System.out.println("这是缓存里的数据");
            return student;
        }else {
            student = studentDao.getID(id);
            if (studentDao.getID(id)!=null){
                redisUtil.set("student"+id,student);
                logger.info("如果查询这个数据不为空的时候，直接从缓存里拿数据");
                System.out.println("如果查询这个数据不为空的时候直接从缓存里拿到数据");
                return student;
            }else {
                redisUtil.set("student"+id,student,60);
                logger.info("student"+id,student,"如果查询数据为空的时候，将该值写入缓存中，设计一个很短的时间");
                System.out.println("如果查询数据为空的时候，将该值写入缓存中 设计一个换短的时间");
                return student;
            }
        }
    }


    //redis
    @Override
    public List<Student> findAll(){
        List<Student> students;
        if (redisUtil.get("students")!=null){
            students = (List<Student>)redisUtil.get("students");
            logger.info("这是从缓存里取出来得数据，"+ students + "============");
            System.out.println("从缓存里取出数据"+ students + "=================");
            return students;
        }else {
            students = studentDao.findAll();
            if(studentDao.findAll()!=null){
                //如果查询数据库不为空，将数据写入缓存中
                logger.info("如果查询数据库不为空的时候，将数据写入缓存中");
                System.out.println("如果查询数据不为空的时候，将数据写入缓存中");
                redisUtil.set("students",students);
                return students;
            }else {
                //如果查询数据为空，将该值写入缓存，设计一个很短的失效时间
                redisUtil.set("studens",students,60);
                logger.info("如果查询数据为空的时候，将该值写入缓存中，这时会有一个很短的失效时间");
                return students;
            }
        }
    }

*/




















/*    @Override
    public List<Student> findAll() {
        List<Student> students;
        if (redisUtil.get("student") != null) {
            students = (List<Student>) redisUtil.get("students");
            logger.info("这是从redis缓存里取出来的数据" + students + "============");
            System.out.println("从缓存里去除数据" + students + "___-__-_-_-______--__-____-");
            return students;
        } else {
            students = studentDao.findAll();
            redisUtil.set("students", studentDao.findAll());
            logger.info("这是从数据库拿出来的数据");
            return students;
        }
    }*/
}


/*        List<Student> students;
        if (MemcachedUtil.get("Student")!=null){
            students=(List<Student>)MemcachedUtil.get("Student");
            logger.info("从缓存里面取移动端" + students + "----------------+--------------");
            System.out.println("从缓存里取出数据" + students + "-------------+-----------");
            return students;
        }else {
            students = studentDao.findAll();
            MemcachedUtil.put("Student",students,0);
            return students;
        }*/


