package com.jnshu.service.impl;

import com.jnshu.entity.Profession;
import com.jnshu.mapper.ProfessionDao;
import com.jnshu.service.ProfessionService;
import com.jnshu.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("professionServiceImpl")
public class ProfessionServiceImpl implements ProfessionService {

    private static Logger logger = LoggerFactory.getLogger(ProfessionServiceImpl.class);

    @Autowired
    private ProfessionDao professionDao;

/*    @Override
    public List<Profession> findAlls() {
        List<Profession> professions;
        if (redisUtil.get("profession") != null) {
            professions = (List<Profession>) redisUtil.get("professions");
            logger.info("如果不为空就从缓存里拿到数据" + professions + "-_-_-__-__-___-___-_____-");
            return professions;
        }else {
            professions = professionDao.findAlls();
            redisUtil.set("profession",professionDao.findAlls());
            logger.info("这里是从数据库里拿到的数据");
            return professions;
        }
    }*/



    @Override
    public int findName() {
        return professionDao.findName();
    }

    @Override
    public List<Profession> findAlls(){
        return professionDao.findAlls();
    }

    @Override
    public Profession addlist(Profession profession){
        return professionDao.addlist(profession);
    }
}
