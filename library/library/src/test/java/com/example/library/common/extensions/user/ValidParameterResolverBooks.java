package com.example.library.common.extensions.user;

import com.example.library.dto.books.BooksDto;
import com.example.library.entities.books.Books;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;


import java.time.LocalDate;
import java.util.Arrays;

import java.util.List;
import java.util.Random;

@Slf4j
public class ValidParameterResolverBooks implements ParameterResolver {
    public static List<BooksDto> validUser = Arrays.asList(
           new BooksDto(1L,
                   "Sun",
                   "Averkov",
                   "Fan",
                   LocalDate.now()),
           new BooksDto(2L,
                   "Moon",
                   "Kurako",
                   "Fan",
                   LocalDate.now()));


    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return parameterContext.getParameter().getType()==BooksDto.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext)
            throws ParameterResolutionException {
        return validUser.get(new Random().nextInt(validUser.size()));
    }
}
