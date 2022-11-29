package com.example.library.common.extensions.user;

import com.example.library.dto.books.BooksDto;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class InvalidParameterResolverBooks implements ParameterResolver {
    public static List<BooksDto> inValidUser = Arrays.asList(
            new BooksDto(1,
                    null,
                    "Averkov",
                    "Fan",
                    LocalDate.now()),
            new BooksDto(5,
                    null,
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
        return inValidUser.get(new Random().nextInt(inValidUser.size()));
    }
}
