//package com.xxx.mvn.mq.demo;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
//import org.springframework.util.concurrent.FailureCallback;
//import org.springframework.util.concurrent.ListenableFuture;
//import org.springframework.util.concurrent.SuccessCallback;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.client.AsyncRestTemplate;
//import org.springframework.web.client.RestTemplate;
//
///**
// * MQ 测试
// *
// * @author:TuoTuo
// * @createDate:2022/6/29 15:06
// * @description:
// */
//@Slf4j
//@Controller
//@RequestMapping("mq")
//public class BugController {
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Autowired
//    private AsyncRestTemplate asyncRestTemplate;
//
//    @Autowired
//    private BugService bugService;
//
//    @ResponseBody
//    @RequestMapping("bug/save")
//    public Msg Bug(String url,String desc,Integer code) {
//        Bug bug = new Bug();
//        bug.setUrl(url);
//        bug.setDesc(desc);
//        bug.setCode(code);
////        bugService.save(bug);
//        HttpEntity entity = new HttpEntity(bug);
//        ListenableFuture<ResponseEntity<String>> result =asyncRestTemplate.exchange("http://localhost:8080/h5",HttpMethod.GET,entity,String.class);
////        String json = restTemplate.postForObject("http://localhost:8080/v1/bug/",bug,String.class);
//        result.addCallback(new SuccessCallback<ResponseEntity<String>>() {
//            @Override
//            public void onSuccess(ResponseEntity<String> result) {
//                log.info("success");
//                System.out.println(result.toString());
//                System.out.println(result.getHeaders());
//                System.out.println(result.getStatusCode());
//                System.out.println(result.getStatusCodeValue());
//                System.out.println(result.getBody());
//                if(200 ==  result.getStatusCodeValue()){
//                    bugService.save(bug);
//                }
//            }
//        }, new FailureCallback() {
//            @Override
//            public void onFailure(Throwable ex) {
//                log.info("error");
//                ex.printStackTrace();
//            }
//        });
//        log.info("json "+result.toString());
//        return Msg.createSucMsg();
//    }
//}
