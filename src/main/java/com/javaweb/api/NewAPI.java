package com.javaweb.api;

import com.javaweb.model.poiDTO;
import com.javaweb.model.testDTO;
import com.javaweb.customexception.RequiredException;
import com.javaweb.service.PoiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//map tim kiem get - post body them
@RestController
public class NewAPI {
    @Autowired
    private PoiService PoiService;

    @GetMapping(value = "/api/test")
    public List<poiDTO> getPoi(@RequestParam(value = "name",required=false) String name,
                               @RequestParam(value="address",required=false) String address,
                               @RequestParam(value="poi_id",required=false) List<Integer> poi_id) {
        List<poiDTO> result = PoiService.findAll(name,address,poi_id);

        return result;
    }

    //EXCEPTION
    public void checkName(testDTO testDTO) {
        if (testDTO.getName() == null) {
            throw new RequiredException("quanh null");
        } else if (testDTO.getName().equals("quanh")) {
            throw new RequiredException("quanh xinh vai o");
        }
    }

    //	@PostMapping(value = "/api/test")
//	public void postTest(@RequestBody Map<String,String> params){
//		System.out.print("ok");
//	}
    @PostMapping(value = "api/test/")
    public testDTO postTest(@RequestBody testDTO testDTO) {
        testDTO result = new testDTO();
        return testDTO;
    }

    //xoa san pham// {co dinh phai nhap vao}
    @DeleteMapping(value = "api/test/{id}")
    public void deleteTest(@PathVariable Integer id) {

        System.out.print("da xoa" + id);
    }


}
//		testDTO result = new testDTO();
//        System.out.print(5 / 0);
//		result.setName(name);
//		result.setNumber(number);
//        checkName(testDTO);
//
//        List<testDTO> listTest = new ArrayList<testDTO>();
//        testDTO testDTO1 = new testDTO();
//        testDTO1.setName("quanh");
//        testDTO1.setNumber(3);
//        testDTO testDTO2 = new testDTO();
//        testDTO2.setName("quanh2");
//        testDTO2.setNumber(4);
//        listTest.add(testDTO1);
//        listTest.add(testDTO2);
//        return listTest;