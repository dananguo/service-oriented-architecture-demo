package com.soa.personservice.controller;

import com.rabbitmq.client.Channel;
import com.soa.personservice.Service.PersonService;
import com.soa.personservice.pojo.Person;
import com.soa.personservice.pojo.PersonInfo;
import com.soa.personservice.pojo.Sign_up_params;
import com.soa.personservice.pojo.Stand_Result;
import com.spring4all.swagger.EnableSwagger2Doc;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EnableSwagger2Doc
@RestController
@CrossOrigin(maxAge = 3600,origins = "*")
public class PersonController {
    @Autowired
    private PersonService personService;

    //新建个人信息
    @RabbitListener(queues = "NewPerson")
    public void NewPerson(@Payload Sign_up_params sign_up_params, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deleverTag) throws IOException
    {
        try {
            Person person=new Person();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            person.setCreateTime(new Timestamp(System.currentTimeMillis()));
            person.setPhone(null);
            person.setEmail(null);
            person.setSignature("无");
            person.setCountry("China");
            person.setSex(null);
            person.setAge(0);
            person.setName("佚名");
            person.setId(sign_up_params.id);
            personService.save(person);
            channel.basicAck(deleverTag,false);

        }catch (Exception e)
        {
            e.printStackTrace();
            channel.basicReject(deleverTag,true);
        }


    }
//    @PostMapping("/v1/Person")
//    public Stand_Result NewPerson(@RequestBody PersonInfo personInfo) {
//        System.out.println(personInfo);
//        Person person=new Person();
//        person.setId(personInfo.getId());
//        person.setName(personInfo.getName());
//        person.setAge(personInfo.getAge());
//        person.setSex(personInfo.getSex());
//        person.setCountry(personInfo.getCountry());
//        person.setSignature(personInfo.getSignature());
//        person.setEmail(personInfo.getE_mail());
//        person.setPhone(personInfo.getPhone());
//        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//        person.setCreateTime(new Timestamp(System.currentTimeMillis()));
//        personService.save(person);
//
//        //返回标准的信息
//        Stand_Result result=new Stand_Result();
//        result.setSucceed(true);
//        result.setWrongCode("0");
//        return result;
//    }

    //查询个人信息

    @GetMapping("/v1/Person/{id}")
    public PersonInfo QueryPerson(@PathVariable("id") String id) {
        Person person=personService.findById(id);
        PersonInfo personInfo=new PersonInfo();
        personInfo.setAge(person.getAge());
        personInfo.setName(person.getName());
        personInfo.setId(person.getId());
        personInfo.setSex(person.getSex());
        personInfo.setCountry(person.getCountry());
        personInfo.setSignature(person.getSignature());
        personInfo.setPhone(person.getPhone());
        personInfo.setE_mail(person.getEmail());
        return personInfo;
    }

    @GetMapping("/get-all")
    public List findAll(){
        return personService.findAll();
    }


    @GetMapping("/v1/person-by-name/{name}")
    public List QueryPersonByName(@PathVariable(value = "name")String name){
        List personList=new ArrayList();
        for(Person person:personService.findByName(name)){
            PersonInfo personInfo=new PersonInfo();
            personInfo.setAge(person.getAge());
            personInfo.setName(person.getName());
            personInfo.setId(person.getId());
            personInfo.setSex(person.getSex());
            personInfo.setCountry(person.getCountry());
            personInfo.setSignature(person.getSignature());
            personInfo.setPhone(person.getPhone());
            personInfo.setE_mail(person.getEmail());
            personList.add(personInfo);

        }
        return personList;
    }

    //修改个人信息

    @PutMapping("/v1/Person")
    public Stand_Result Update(@RequestBody PersonInfo personInfo) {
        Person person=new Person();
        person.setId(personInfo.getId());
        person.setName(personInfo.getName());
        person.setAge(personInfo.getAge());
        person.setSex(personInfo.getSex());
        person.setCountry(personInfo.getCountry());
        person.setSignature(personInfo.getSignature());
        person.setEmail(personInfo.getE_mail());
        person.setPhone(personInfo.getPhone());
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        person.setCreateTime(new Timestamp(System.currentTimeMillis()));
        personService.save(person);

        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        /*未添加时间*/
        return result;
    }



    //删除个人信息

    @DeleteMapping("/v1/Person/{id}")
    public Stand_Result Delete(@PathVariable("id") String id) {
        personService.delete(id);
        Stand_Result result=new Stand_Result();
        result.setWrongCode("0");
        result.setSucceed(true);
        /*未添加时间*/
        return result;
    }
}
