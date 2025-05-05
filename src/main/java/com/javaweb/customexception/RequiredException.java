package com.javaweb.customexception;
//sua loi exception k bat dc sai nhung van tra ve 200- cau hinh lai exception
// doi lai gia tri cua exceptin
public class RequiredException  extends RuntimeException{
public RequiredException(String msg){
    super(msg);
}
}
