package com.example.jpademo.utils;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.MethodArgumentNotValidException;

import com.example.jpademo.viewmodels.DomainErrorViewModel;
import com.example.jpademo.viewmodels.ValidationErrorPageViewModel;

public class AppUtils {
    public static ValidationErrorPageViewModel getValidationErrorPage(MethodArgumentNotValidException exception) {
        List<DomainErrorViewModel> domainErrors = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new DomainErrorViewModel(fe.getField(), fe.getDefaultMessage()))
                .sorted(Comparator.comparing(DomainErrorViewModel::getField))
                .collect(Collectors.toList());

        return new ValidationErrorPageViewModel(
                exception.getBindingResult().getTarget().getClass().getName(),
                LocalDateTime.now(),
                domainErrors
        );
    }
}