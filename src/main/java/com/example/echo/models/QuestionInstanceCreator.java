package com.example.echo.models;

import com.google.gson.InstanceCreator;

import javax.naming.Context;
import java.lang.reflect.Type;

public class  QuestionInstanceCreator implements InstanceCreator<Question> {
    private Context context;

    @Override
    public Question createInstance(Type type) {
        // create new object with our additional property
        Question userContext = new Question(context);

        // return it to gson for further usage
        return userContext;
    }
}
