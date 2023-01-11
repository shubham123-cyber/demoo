package com.example.demoo.DemoService;

import com.example.demoo.Model.Entitymodel;
import com.example.demoo.Repo.Sql;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DemoServ {
    @Autowired
    private Sql sql;

    private final Logger logger= LoggerFactory.getLogger(DemoServ.class);
    public List<Entitymodel> GetAll() {

        logger.info("inside GetAll method of DemoServ");
        List<Entitymodel> ll= sql.findAll();
        return ll;
    }

    public Entitymodel Save(Entitymodel entitymodel) {
        logger.info("inside Save method of DemoServ");
        Entitymodel l=sql.save(entitymodel);
        return l;
    }
    public Entitymodel getByIdd(Integer idd) {
        logger.info("inside getbyId method of DemoServ");
        Entitymodel entitymodel1= sql.findById(idd).get();
        return entitymodel1;
    }

    public void DelById(Integer id) {
        logger.info("inside delbyId method of DemoServ");

        sql.deleteById(id);
    }

    public void Update(Entitymodel entitymodel) {
        //Optional<Entitymodel> entitymodel1=sql.findById(entitymodel.getIdd());


        logger.info("inside Update method of DemoServ");
        //u can remove Optional by using .get() at last--->
        Optional<Entitymodel> entitymodel1=sql.findById(entitymodel.getIdd());
        if(entitymodel1.isPresent()) sql.save(entitymodel);
    }
}



//"message": "Type definition error: [simple type, class org.hibernate.proxy.pojo.bytebuddy.----->>>
//ANSWER:->
//    @Override
//    public YourEntityClass findYourEntityClassById(Long id) {
//        return YourEntityClassRepositorie.getOne(id);
//    }
//Change this to:
//
//@Override
//public YourEntityClass findYourEntityClassById(Long id) {
//        return YourEntityClassRepositorie.findById(id).get();
//        }
//@JsonIgnore
//Or If you have any lazy loaded properties having a relationship. You can use this annotation at top of the property.
//
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})