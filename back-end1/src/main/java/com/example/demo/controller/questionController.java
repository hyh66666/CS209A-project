package com.example.demo.controller;

import com.example.demo.enity.Owner;
import com.example.demo.enity.bag;
import com.example.demo.enity.question;
import com.example.demo.mapper.OwnerMapper;
import com.example.demo.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class questionController {
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private OwnerMapper ownerMapper;

    @RequestMapping("/Qyi1")
    public Map<String, String> Qyi1() {
        Map<String, String> result = new HashMap<>();
        List<question> questionList = questionMapper.Qyi1();
        int count = questionList.size();
        int total = (questionMapper.findAllQuestion()).size();
        float per1 = (float) (count / (total * 1.0));
        float per2 = 1 - per1;
        result.put("没有答案的", String.valueOf(per1));
        result.put("有答案的", String.valueOf(per2));

        return result;
    }

    @GetMapping("/Qyi2")
    public Map<String, String> Qyi2() {
        Map<String, String> result = new HashMap<>();
        float avg = questionMapper.Qyi2yi();
        float max = questionMapper.Qyi2y();
        result.put("avg", String.valueOf(avg));
        result.put("max", String.valueOf(max));
        return result;
    }

    @GetMapping ("/Qyi3")
    public List<bag> Qyi3() {
        return questionMapper.Qyi3();
    }

    @GetMapping ("/Qsan1")
    public List<bag> Qsan1() {
        return questionMapper.Qsan1();
    }

    @GetMapping ("/Qsan2yi")
    public List<bag> Qsan2yi() {
        return questionMapper.Qsan2yi();
    }
    @GetMapping ("/Qsan2er")
    public List<bag> Qsan2er() {
        return questionMapper.Qsan2er();
    }

    @GetMapping ("/Qsan3yi")
    public List<bag> Qsan3yi() {
        return questionMapper.Qsan3yi();
    }
    @GetMapping ("/Qsan3er")
    public List<bag> Qsan3er() {
        return questionMapper.Qsan3er();
    }
    @GetMapping ("/Qsi1")
    public List<bag> Qssi1() {
        return questionMapper.Qsi1();
    }
    @GetMapping ("/Qsi2yi")
    public List<bag> Qssi2yi() {
        return questionMapper.Qsi2yi();
    }

    @GetMapping ("/Qsi2er")
    public List<bag> Qssi2er() {
        return questionMapper.Qsi2er();
    }
    @GetMapping ("/Qsi3")
    @ResponseBody
    public ResponseEntity<Map<String, Owner>> Qssi3() {
        List<bag> temp = questionMapper.Qsi3();
        Map<String,Owner> re=new LinkedHashMap<>();
        for(int i = 0;i<temp.size();i++){
            Owner owner = ownerMapper.findOwnerByUserId(temp.get(i).getNumber());
            re.put(temp.get(i).getCount()+"-"+i,owner);
        }
        return ResponseEntity.ok(re);
    }

}
