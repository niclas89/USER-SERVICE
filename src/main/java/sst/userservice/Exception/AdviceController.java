package sst.userservice.Exception;







import org.apache.log4j.Logger;
import org.hibernate.PropertyValueException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLIntegrityConstraintViolationException;


@ControllerAdvice
public class AdviceController {

    private static final Logger logger = Logger.getLogger(AdviceController.class);

    @ExceptionHandler
    public ResponseEntity<String> handleException(SQLIntegrityConstraintViolationException exc) {
        logger.info("Test message");

        String message = "";
        if (exc.getMessage().contains("user_name")){
            message = "username";
        }else if (exc.getMessage().contains("email")){
            message = "email";
        }
        return ResponseEntity.badRequest().body("Please choose a different " + message +'!');
    }

    @ExceptionHandler
    public ResponseEntity<String> handleException(PropertyValueException exc){
        return ResponseEntity.badRequest().body("Incorrectly built entity");
    }


}
