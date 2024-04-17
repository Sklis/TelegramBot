package com.javarush.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.Map;

import static com.javarush.telegrambot.TelegramBotContent.*;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {
    public static final String NAME = "jru_sklis_bot"; // TODO: добавьте имя бота в кавычках
    public static final String TOKEN = "6805103983:AAFYRlDRY_muBIQyhXRzPy9A4R0VjbLnUmY"; //TODO: добавьте токен бота в кавычках

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update updateEvent) {
        // начало игры - "Нужно взломать холодильник"
        if (getMessageText().equals("/start")) {
            setUserGlory(0);
            sendPhotoMessageAsync("step_1_pic");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Взломать холодильника", "step_1_btn"));
        }

        if (getCallbackQueryButtonKey().equals("step_1_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("Взять сосиску! + 20 славы.", "step_2_btn",
                            "Взять рыбку! + 20 славы.", "step_2_btn",
                            "Скинуть банку с солеными огурцами. + 20 славы.", "step_2_btn"));
        }

        // взламываем робот-пылесос
        if (getCallbackQueryButtonKey().equals("step_2_btn")) {
            setUserGlory(20);
            sendPhotoMessageAsync("step_3_pic");
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_3_TEXT,
                    Map.of("Взломать робот-пылесос", "step_3_btn"));
        }

        // взломать GoPro
        if (getCallbackQueryButtonKey().equals("step_3_btn")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_4_pic");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Отаравить робота-пылесоса за едой! + 30 славы.", "step_4_btn",
                            "Покататься на роботе-пылесосе! + 30 славы.", "step_4_btn",
                            "Убежать от робота-пылесоса. + 30 славы.", "step_4_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_4_btn")) {
            setUserGlory(20);
            sendTextMessageAsync(STEP_5_TEXT,
                    Map.of("надень и включи GoPro!", "step_5_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_5_btn")) {
            setUserGlory(40);
            sendTextMessageAsync(STEP_6_TEXT,
                    Map.of("Эксклюзивное интервью с известным спортсменом.", "step_6_btn",
                            "Бесплатный прыжок с парашютом.", "step_6_btn",
                            "Занятие с профессиональным фотографом.", "step_6_btn"));
        }

        //взламываем компьютер
        if (getCallbackQueryButtonKey().equals("step_6_btn")) {
            sendTextMessageAsync(STEP_7_TEXT,
                    Map.of("Использование утилит для восстановления паролей. + 50 славы.", "step_7_btn",
                            "Использование стандартных функций восстановления паролей. + 50 славы.", "step_7_btn",
                            "Установка новой учетной записи. + 50 славы.", "step_7_btn"));
        }
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            setUserGlory(50);
            sendTextMessageAsync(STEP_8_TEXT,
                    Map.of("На сегодня приключений достаточно! Пора пойти к другим котам.", "step_8_btn"));
        }

        // хвастаемся перед другими котами
        if (getCallbackQueryButtonKey().equals("step_7_btn")) {
            setUserGlory(50);
            sendTextMessageAsync(FINAL_TEXT);
        }
        }

        public static void main (String[]args) throws TelegramApiException {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new MyFirstTelegramBot());
        }
    }
//
//        if (getMessageText().equals("Привет")) sendTextMessageAsync("*Привет!!!*");
//        if (getMessageText().equals("/bye")) sendTextMessageAsync("Asta la vista, baby!");
//
//// создание кнопок
//        if (getMessageText().contains("животное"))  sendTextMessageAsync("Ваше любимое животное?",
//                                                                         Map.of("Кот", "cat", "Собака", "dog"));
//        if (getCallbackQueryButtonKey().equals("cat")) sendPhotoMessageAsync("step_4_pic");
//        if (getCallbackQueryButtonKey().equals("dog")) sendPhotoMessageAsync("step_6_pic");
//
//// редактирование сообщения
//        if (getMessageText().equals("smile")){
//var message = getLastSentMessage();
//editTextMessageAsync(message.getMessageId(), message.getText() + " :) ");
//        }