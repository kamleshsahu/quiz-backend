package com.example.echo.models;

import com.google.gson.InstanceCreator;

import javax.naming.Context;
import java.lang.reflect.Type;

public class  QuestionBankInstanceCreator implements InstanceCreator<QuestionBank> {
    private Context context;

    @Override
    public QuestionBank createInstance(Type type) {
        // create new object with our additional property
        QuestionBank userContext = new QuestionBank(context);

        // return it to gson for further usage
        return userContext;
    }
}
