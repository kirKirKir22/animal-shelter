package pro.sky.telegrambotanimalshelter.constants;

public enum Constants {

    START("/start"),
    WELCOME("Добро пожаловать, в наш приют"),
    FIND_INFORMATION("Здесь вы сможете найти всю необходимую информацию."),
    CAT("Кошка"),
    DOG("Собака"),
    EMPTY(""),
    VOLUNTEER_URL("https://t.me/mr_Talks"),
    MAIN_MENU("Главное меню"),
    RETURN_MENU("Вернуться в меню"),
    SHELTER_INFO_MENU("Информация о приюте"),
    ABOUT_ANIMAL_SHELTER("О приюте"),
    HI(" привет, меня зовут бот Макс, я помогу тебе с усыновлением собаки или кошки, для этого выбери нужную кнопку!"),
    SAY_HI("Привет"),
    SAY_HI2("привет"),
    CHOOSE_PET("Выберите, кого хотите приютить:"),
    SET_CAT_ANIMAL("Вы выбрали кошачий приют."),
    SET_DOG_ANIMAL("Вы выбрали собачий приют"),
    HOW_GET_ANIMAL("Как взять питомца из приюта"),
    DRIVER_SCHEME("Схема проезда, пропуск"),
    FOR_SAFETY("Техника безопасности"),
    TIPS_AND_RECOMMENDATIONS("Советы и рекомендации"),
    DOCUMENTS("Необходимые документы"),
    GET_ANIMAL_WITH_DEFECTS("Взять питомца с ограниченными возможностями"),
    SEND_REPORT("Прислать отчет о питомце"),
    INFO_ABOUT_BOT_BUTTON("Информация о возможностях бота"),
    GET_USER_CONTACT("Оставить контактные данные"),
    SEND_MESSAGE_VOLUNTEER("Обратиться к волонтеру"),
    ALREADY_IN_DB("Вы уже в базе!"),
    ADD_TO_DB("Вас успешно добавили в базу. Скоро вам перезвонят."),
    VOLUNTEER_QUESTION("Чтобы задать свой вопрос волонтеру, перейдите по ссылке:  "),
    EMPTY_MESSAGE("Пустое сообщение, измените свой вопрос!"),
    UNKNOWN_MESSAGE("Я не знаю такой команды, возможно вам стоит обратиться к волонтеру"),
    ALREADY_SEND_REPORT("Вы уже отправляли отчет сегодня"),
    TRIAL_PERIOD_PASSED("Вы прошли испытательный срок!"),
    INCORRECT_REPORT("Некорректно заполнен отчет."),
    USER_ADDED_PHONE_NUMBER_TO_DB(" Добавил(а) свой номер в базу"),
    REPORT_IS_OK("Отчет успешно принят!"),
    REPORT_RECEIVED("Отчет успешно принят от: "),
    REPORT_NOTIFICATION("Вы забыли прислать отчет, скорее поторопитесь сделать это!"),
    UPLOAD_PHOTO_ERROR("Ошибка загрузки фото!"),
    INFO_ABOUT_BOT("Информация о возможностях бота \n- Бот может показать информацию о приюте \n" +
            "- Покажет какие документы нужны \n- Бот может принимать ежедневный отчет о питомце\n" +
            "- Может передать контактные данные волонтерам для связи"),
    INFO_ABOUT_SHELTER_DOG("Наш сайт с информацией о приюте для собак \nhttps://сайт.com/ \n" +
            "Контактные данные \nsoapDOG@GMAIL.COM \n+7(555)555-55-55\n" +
            "Общие рекомендации \nhttps://www.сайтDog.com/\n" +
            ""),
    INFO_ABOUT_SHELTER_CAT("Наш сайт с информацией о приюте для кошек \nhttps://сайт.com/ \n" +
            "Контактные данные \nsoapCAT@GMAIL.COM\n \n+7(555)555-55-55\n" +
            "Общие рекомендации \nhttps://www.сайтCat/\n" +
            ""),
    SCHEMA_2GIS("Наш адрес:   г.  Замбалия, ул.  Фантазийная улица Дом № 42½." +
            "\nДля построения маршрута перейдите по ссылке:" +
            " \n https://ru.wikipedia.org/wiki/%D0%9C%D1%8B%D1%81_%D0%94%D0%BE%D0%B1%D1%80%D0%BE%D0%B9_%D0%9D%D0%B0%D0%B4%D0%B5%D0%96#/maplink/1" +
            "\nПропуск на автомобиль можно оформить в пункте охраны по предварительному звонку: т. 8 (555) 444-44-44"),
    SAFETY("Техника безопасности при нахождении в приюте:" +
            "\n 1. Ходить только по обозначенным тротуарам" +
            "\n 2. Животных кормить только разрешенным кормом" +
            "\n 3. Не фотографировать со вспышкой - это может напугать питомцев" +
            "\n 4. Не пихать руки в закрытые вольеры" +
            "\n 5. Не оставлять детей одних, без присмотра"),
    INFO_ABOUT_DOCUMENTS("Для того, чтобы взять питомца из приюта вам необходимо иметь при себе:" +
            "\n1. Паспорт." +
            "\n2. Заявление о усыновлении питомца." +
            "\n3. Заполненную анкету с информацией о номере телефона, адресе проживания."),
    INFO_ABOUT_ANIMAL_WITH_DEFECTS("Если вы хотите взять питомца с ограниченными возможностями, " +
            "ознакомьтесь с информацией: \nhttps://www.сайт.com/"),
    INFO_ABOUT_DOGS("Правила знакомства с собаками: \nwww.www.сайт.com/ \n" +
            "Список документов: \nПаспорт, либо другой документ, удостоверяющий личность. Заявление.\n" +
            "Список рекомендаций: \nhttps://www.сайт.com/\n" +
            "Советы кинолога: \nhttps://www.сайт.com\n" +
            "Прочая информация: \nhttps://www.сайт.com/\n" +
            ""),
    INFO_ABOUT_CATS("Правила знакомства с кошками \nwww.www.сайт.com/ \n" +
            "Список документов: \nПаспорт, либо другой документ, удостоверяющий личность. Заявление\n" +
            "Список рекомендаций: \nhttps://www.сайт.com/\n" +
            "Прочая информация: \nhttps://newchance.kz/\n" +
            ""),
    INFO_ABOUT_REPORT("Для отчета нужна следующая информация:\n" +
            "- Фото животного.  \n" +
            "- Рацион животного\n" +
            "- Общее самочувствие и привыкание к новому месту\n" +
            "- Изменение в поведении: отказ от старых привычек, приобретение новых.\nСкопируйте следующий пример. Не забудьте прикрепить фото"),
    REPORT_EXAMPLE("Рацион: ваш текст\n" +
            "Самочувствие: ваш текст\n" +
            "Поведение: ваш текст"),
    REGEX_MESSAGE("(Рацион:)(\\s)(\\W+)\n;" +
            "(Самочувствие:)(\\s)(\\W+)\n;" +
            "(Поведение:)(\\s)(\\W+);"),
    TELEGRAM_CHAT_VOLUNTEERS(544252810L);

    private final String value;
    private final Long longValue;

    Constants(String value) {
        this.value = value;
        this.longValue = null;
    }

    Constants(Long longValue) {
        this.value = null;
        this.longValue = longValue;
    }

    public String getValue() {
        return value;
    }

    public Long getLongValue() {
        return longValue;
    }

    public static Constants getEnum(String text) {
        for (Constants constants : Constants.values()) {
            if (constants.getValue().equalsIgnoreCase(text)) {
                return constants;
            }
        }
        throw new IllegalArgumentException("No constant with text " + text + " found");
    }
}
