package com.example.demoo.DemoController;

import com.example.demoo.DemoService.DemoServ;
import com.example.demoo.Model.Entitymodel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DemoCont {
    @Autowired
    private DemoServ demoServ;

    private final Logger logger= LoggerFactory.getLogger(DemoCont.class);
    @GetMapping("/home")
    public ResponseEntity<String> hh() {
        logger.info("Inside home of DemoCont");

        return new ResponseEntity<>("hello", HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Entitymodel>> getAll() {
        logger.info("Inside getAll method of DemoCont");
        return new ResponseEntity<>(demoServ.GetAll(),HttpStatus.OK);
    }


    @PostMapping("/add")
    public ResponseEntity<Entitymodel> Add(@RequestBody Entitymodel entitymodel) {
        logger.info("Inside Add method of DemoCont");
        Entitymodel entitymodel1=demoServ.Save(entitymodel);
        if(entitymodel1==null) {
            return null;
        }
        return new ResponseEntity<>(entitymodel1,HttpStatus.CREATED);


    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Entitymodel> UpdateById(@PathVariable(value = "id") Integer id, @RequestBody Entitymodel entitymodel) {
        logger.info("Inside UpdateById of DemoCont");
        Entitymodel entitymodel1=demoServ.getByIdd(id);
                //.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));
        entitymodel1.setIdd(entitymodel.getIdd());
        entitymodel1.setYou(entitymodel.getYou());
        demoServ.Update(entitymodel1);
        return new ResponseEntity<>(entitymodel1,HttpStatus.OK);


    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> Deletebyid(@PathVariable(value = "id") Integer id) {
        logger.info("Inside deletebyID of DemoCont");
        demoServ.DelById(id);
        return new ResponseEntity<>("Deletedd!!",HttpStatus.OK);
    }

}
