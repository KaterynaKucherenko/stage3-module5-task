package com.mjc.school.main;

import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.commands.ControllerHandler;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Menu {
    private ControllerHandler controllerHandler;


    public Menu(ControllerHandler controllerHandler) {
        this.controllerHandler = controllerHandler;
    }


    public void executeOperation(String numOfMethod) throws InvocationTargetException, IllegalAccessException {
        Method[] methodsForMenu = ControllerHandler.class.getDeclaredMethods();
        List<Method> methods = Arrays.stream(methodsForMenu).filter(x -> x.isAnnotationPresent(CommandHandler.class)).filter(x -> x.getAnnotation(CommandHandler.class).value().equals(numOfMethod)).collect(Collectors.toList());
        Method res = methods.get(0);
        res.invoke(controllerHandler);
    }

    public void start() throws InvocationTargetException, IllegalAccessException {
        while (true) {
            System.out.println("OPERATIONS WITH NEWS: " + "\n" +
                    "1 - read all news" + "\n" +
                    "2 - read news by ID" + "\n" +
                    "3 - create news" + "\n" +
                    "4 - update news" + "\n" +
                    "5 - delete news by ID" + "\n" +
                    "6 - get news by some parameters" + "\n" +
                    "OPERATIONS WITH AUTHORS: " + "\n" +
                    "8 - read author by ID" + "\n" +
                    "9 - create author" + "\n" +
                    "10 - update author" + "\n" +
                    "11 - delete author by ID" + "\n" +
                    "OPERATIONS WITH TAGS: " + "\n" +
                    "12 - read all tags " + "\n" +
                    "13 - read tag by ID" + "\n" +
                    "14 - create tag" + "\n" +
                    "15 - update tag" + "\n" +
                    "16 - delete tag by ID" + "\n" +
                    "0 - exit");
            Scanner scanner = new Scanner(System.in);
            String numOfMethod = scanner.nextLine();
            if (numOfMethod != "0") {
                executeOperation(numOfMethod);
            } else {
                System.exit(0);
            }
        }
    }


}
