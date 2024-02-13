package com.uniovi.sdi.notaneitor.validators;

import com.uniovi.sdi.notaneitor.entities.User;
import com.uniovi.sdi.notaneitor.services.UsersService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class SignUpFormValidator implements Validator {

    private final UsersService usersService;

    public SignUpFormValidator(UsersService usersService){
        this.usersService = usersService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dni", "Error.empty");

        if (user.getDni().length() < 5 || user.getDni().length() > 24) {
            errors.rejectValue("dni", "Error.signup.dni.length");}

        if (usersService.getUserByDni(user.getDni()) != null) {
            errors.rejectValue("dni", "Error.signup.dni.duplicate");}
        if (user.getName().length() < 5 || user.getName().length() > 24) {
            errors.rejectValue("name", "Error.signup.name.length");}
        if (user.getLastName().length() < 5 || user.getLastName().length() > 24) {
            errors.rejectValue("lastName", "Error.signup.lastName.length");}
        if (user.getPassword().length() < 5 || user.getPassword().length() > 24) {
            errors.rejectValue("password", "Error.signup.password.length");}
        if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm",
                    "Error.signup.passwordConfirm.coincidence");}



        if (user.getDni().length() != 9 && !Character.isLetter(user.getDni().toCharArray()[8])) {
            errors.rejectValue("score", "Error.user.dni.length.letter");
        }
        if (user.getFullName() == null || user.getFullName().trim().isEmpty()) {
            errors.rejectValue("name", "Error.name.required");
        } else if (user.getFullName().startsWith(" ") || user.getFullName().endsWith(" ")) {
            errors.rejectValue("name", "Error.name.whitespace");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            errors.rejectValue("lastName", "Error.lastName.required");
        } else if (user.getLastName().startsWith(" ") || user.getLastName().endsWith(" ")) {
            errors.rejectValue("lastName", "Error.lastName.whitespace");
        }

    }

}
