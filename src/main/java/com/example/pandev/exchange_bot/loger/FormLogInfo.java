package com.example.pandev.exchange_bot.loger;

public class FormLogInfo {


  public static String getInfo(String message) {
    StackTraceElement STACK_TRACE_ELEMENT = new Throwable().getStackTrace()[1];
    String METHOD_NAME = STACK_TRACE_ELEMENT.getMethodName();
    String CLASS_NAME = STACK_TRACE_ELEMENT.getClassName();
    return "Старт метода " +
            "\"" +
            METHOD_NAME +
            "\"" +
            " из класса " +
            "\"" +
            CLASS_NAME +
            "\"" +
            "\n" +
            message +
            "\n";
  }
  public static String getCatch() {
    StackTraceElement STACK_TRACE_ELEMENT = new Throwable().getStackTrace()[1];
    String METHOD_NAME = STACK_TRACE_ELEMENT.getMethodName();
    String CLASS_NAME = STACK_TRACE_ELEMENT.getClassName();
    return " ВНИМАНЕИЕ: " +
            " Вызван catch в методе " +
            "\"" +
            METHOD_NAME +
            "\"" +
            " из класса " +
            "\"" +
            CLASS_NAME +
            "\"";
  }

  public static String getException() {
    StackTraceElement STACK_TRACE_ELEMENT = new Throwable().getStackTrace()[2];
    String METHOD_NAME = STACK_TRACE_ELEMENT.getMethodName();
    String CLASS_NAME = STACK_TRACE_ELEMENT.getClassName();
    return " ВНИМАНЕИЕ: " +
            " исключение в методе " +
            "\"" +
            METHOD_NAME +
            "\"" +
            " из класса " +
            "\"" +
            CLASS_NAME +
            "\"";
  }

}
