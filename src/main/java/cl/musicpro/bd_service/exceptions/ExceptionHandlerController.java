package cl.musicpro.bd_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cl.musicpro.bd_service.model.dto.CommonResponseDTO;



@ControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler(java.sql.SQLSyntaxErrorException.class)
    public ResponseEntity<CommonResponseDTO> handleException(java.sql.SQLSyntaxErrorException ex) {
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("500");
        commonResponseDTO.setResponseMessage("ERROR");
        commonResponseDTO.setData(ex.getMessage());
        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<CommonResponseDTO> handleException(MissingRequestHeaderException ex) {
        CommonResponseDTO commonResponseDTO = new CommonResponseDTO();
        commonResponseDTO.setResponseCode("400");
        commonResponseDTO.setResponseMessage("Bad Request");
        commonResponseDTO.setData(ex.getMessage());
        return new ResponseEntity<CommonResponseDTO>(commonResponseDTO, HttpStatus.BAD_REQUEST);

    }
}
