package com.javaweb.controllerAdvice;
//khi nao loi thi vo day check
//sua lai khi loi ma van hien 200-> nha lai dung loi 50x

import com.javaweb.model.errorResponeDTO;
import com.javaweb.customexception.RequiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice

public class ControllerAdvisor extends ResponseEntityExceptionHandler {
// cu gap khong chia cho 0 nhay vao
    @ExceptionHandler(value = {ArithmeticException.class})
    public ResponseEntity<Object> handleArithmeticException(
            ArithmeticException ex, WebRequest request) {

        errorResponeDTO errorResponeDTO = new errorResponeDTO();
        errorResponeDTO.setError(ex.getMessage());
        List<String> detail = new ArrayList<String>();
        detail.add("khong chia dc cho 0");
        errorResponeDTO.setDetail(detail);


        return new ResponseEntity<>(errorResponeDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {RequiredException.class})
    public ResponseEntity<Object> handleRequiredException( RequiredException ex, WebRequest request ) {
        errorResponeDTO errorResponeDTO = new errorResponeDTO();
        errorResponeDTO.setError(ex.getMessage());
        List<String> detail= new ArrayList<String>();
        detail.add("check lai ten dang null e");
        errorResponeDTO.setDetail(detail);
        return new ResponseEntity<>(errorResponeDTO, HttpStatus.BAD_GATEWAY);
    }



}
