package com.uniovi.sdi.notaneitor.validators;

import com.uniovi.sdi.notaneitor.entities.Mark;
import com.uniovi.sdi.notaneitor.services.MarksService;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class MarkFormValidator implements Validator {

    private final MarksService marksService;

    public MarkFormValidator(MarksService marksService){
        this.marksService = marksService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        Mark mark = (Mark) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");

        if(mark.getScore()<0 || mark.getScore()>10){
            errors.rejectValue("score", "Error.mark.score.range");}
        if(mark.getDescription().length() < 20){
            errors.rejectValue("score", "Error.mark.description.length");}
    }
}
