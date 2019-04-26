package com.hit.secondhouse.controller;


import com.google.gson.Gson;
import com.hit.secondhouse.domain.House;
import com.hit.secondhouse.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
public class HouseController {

    @Autowired
    public HouseService houseService;

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String say(){return "house center is saying hello";}

    @RequestMapping(value = "/release",method = RequestMethod.GET)
    public void releaseHouseInfo(@RequestParam(value = "location",required = true) String location,
                                 @RequestParam(value = "price") int price,
                                 @RequestParam(value = "description") String description,
                                 @RequestParam(value="longitude") String longitude,
                                 @RequestParam(value = "latitude") String latitude){
        int id = 0;
        houseService.insertHouseService(id,location,price,description,latitude,longitude);
    }

    //@CrossOrigin(origins = "*")
    @RequestMapping(value = "getAllHouse",method = RequestMethod.GET)
    @ResponseBody
    public String getAllHouse(@RequestParam(value = "callback",required = false) String callback){
        Map map = new HashMap();
        List<House> houseList = houseService.getAllHouse();
        map.put("houseList",houseList);
        Gson gson = new Gson();
        return callback+"("+gson.toJson(map)+")";
    }


    @RequestMapping(value = "getHouse/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getHouseById(@RequestParam(value = "callback",required = false) String callback, @PathVariable(value = "id",required = true) int id){
        Map map = new HashMap();
        map.put("id",houseService.findHouseByIdService(id).getId());
        map.put("location",houseService.findHouseByIdService(id).getLocation());
        map.put("price",houseService.findHouseByIdService(id).getPrice());
        map.put("description",houseService.findHouseByIdService(id).getDescription());
        Gson gson = new Gson();
        return callback+"("+gson.toJson(map)+")";
    }

    @RequestMapping(value = "/buy",method = RequestMethod.GET)
    public void register(@RequestParam(value = "uid") int uid , @RequestParam("hid") int hid,@RequestParam("price") int price){
        int id = 0;
        houseService.addOrderService(id,uid,hid,price);


    }
}
