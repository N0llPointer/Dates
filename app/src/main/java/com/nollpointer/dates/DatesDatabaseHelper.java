package com.nollpointer.dates;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Scanner;


public class DatesDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="dates";
    private static final int DB_VERSION = 4;
    public DatesDatabaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE D10");
        db.execSQL("DROP TABLE D1");
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE D10");
        sqLiteDatabase.execSQL("DROP TABLE D1");
        onCreate(sqLiteDatabase);
    }
    private void insertDate(SQLiteDatabase db,String name, String date,String event,int k){
        ContentValues ct = new ContentValues();
        ct.put("DATE",date);
        ct.put("EVENT",event);
        ct.put("MISTAKES",k);
        db.insert(name,null,ct);
    }
    private void parseString(SQLiteDatabase db,String table_name,String text){
        Scanner scan = new Scanner(text);
        String[] str;
        while (scan.hasNextLine()) {
            str = scan.nextLine().split("#");
            insertDate(db, table_name, str[0].trim(), str[1].trim(),0);
        }
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE D10 (_id INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT, EVENT DATE, MISTAKES INTEGER);");
        db.execSQL("CREATE TABLE D1 (_id INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT, EVENT DATE, MISTAKES INTEGER);");
        parseString(db,"D1","862 # Призвание Рюрика в Новгород\n" +
                "988–990 # Принятие христианства\n" +
                "1147 # Первое летописное упоминание о Москве\n" +
                "1223 # Битва на р. Калке (первое столкновение с монголо-татарами, начало нашествия)\n" +
                "1240 # Невская битва\n" +
                "1242 # Ледовое побоище\n" +
                "1380 # Куликовская битва\n" +
                "1478 # Присоединение Новгорода к Московскому государству\n" +
                "1480 # Стояние на р. Угре, конец монголо-татарского ига\n" +
                "1497 # \"Судебник\" Ивана III\n" +
                "1549 # Первый Земский собор\n" +
                "1550 # \"Судебник\" Ивана Грозного\n" +
                "1558–1582 # Ливонская война\n" +
                "1603–1613 # Смута\n" +
                "1613 # Избрание на царство Михаила Романова; начало правления династии Романовых\n" +
                "1649 # \"Соборное уложение\"\n" +
                "1654 # Церковный Собор, одобривший церковную реформу патриарха Никона\n" +
                "1654 # Переяславская Рада, присоединение к России Левобережной Украины\n" +
                "1670–1671 # Крестьянско-казацкое движение под предводительством Степана Разина\n" +
                "1700–1721 # Северная война\n" +
                "1703 # Основание Санкт-Петербурга\n" +
                "27 июня 1709 г. # Полтавская битва\n" +
                "1714 # \"Указ о единонаследии\". Поместья уравняли с вотчинами\n" +
                "1721 # Принятие Петром I императорского титула. Россия становится империей\n" +
                "1722 # \"Табель о рангах\"\n" +
                "1762 # \"Манифест о вольности дворянской\"\n" +
                "1773–1775 # Восстание под руководством Е.И. Пугачева\n" +
                "1785 # Жалованные грамоты дворянству и городам\n" +
                "1805 # Сражение под Аустерлицем\n" +
                "1812 (июнь-дек.) # Отечественная война\n" +
                "26 августа 1812 # Бородинское сражение\n" +
                "1813–1814 # Заграничный поход русской армии\n" +
                "14 декабря 1825 # Восстание декабристов на Сенатской площади\n" +
                "1837 # Открытие первой железной дороги, начало промышленного переворота\n" +
                "1853–1856 # Крымская война\n" +
                "19 февраля 1861 # Манифест об отмене крепостного права\n" +
                "1877–1878 # Русско-турецкая война\n" +
                "1881 # Убийство Александра II народовольцами\n" +
                "1903 # II съезд РСДРП, оформление большевизма\n" +
                "1904–1905 # Русско-японская война\n" +
                "1904 # Оборона Порт-Артура\n" +
                "1905 # Цусимское сражение\n" +
                "9 января 1905 г. # \"Кровавое воскресенье\", начало первой русской революции\n" +
                "1905–1907 # Первая русская революция\n" +
                "17 октября 1905 # Манифест Николая II \"О совершенствовании государственного порядка\"\n" +
                "1906 # I Государственная Дума\n" +
                "1906–1911 # Аграрная реформа П.А. Столыпина\n" +
                "1914–1918 # Первая мировая война\n" +
                "27 февраля 1917 # Февральская революция. Конец самодержавия\n" +
                "1917, 25 августа – 1 сентября # Корниловский мятеж\n" +
                "1917, 25 октября (7 ноября) # Октябрьское вооруженное восстание в Москве, II Всероссийский съезд Советов рабочих и солдатских депутатов, переход власти к большевикам\n" +
                "1918, 6 января #  Разгон Учредительного собрания\n" +
                "1918, 3 марта # Брестский мир (подписан в Брест-Литовске, аннулирован в ноябре 1918 г. в связи с окончанием Первой мировой войны)\n" +
                "1918 # Принятие Конституции РСФСР («первая советская конституция»)\n" +
                "1921 # Кронштадтский мятеж\n" +
                "1921 # Новая экономическая политика (нэп)\n" +
                "1922 # Генуэзская конференция\n" +
                "1922 # Образование СССР\n" +
                "1924, 30 декабря # Утверждение Конституции СССР\n" +
                "1927–1932 # Первая пятилетка\n" +
                "1929 # Начало сплошной коллективизации в СССР, курс на уничтожение кулачества как класса (Год \"великого перелома\")\n" +
                "1933–1937 # Вторая пятилетка\n" +
                "1934 # Принятие СССР в Лигу Наций\n" +
                "1936 # Принятие Конституции СССР («Конституция победившего социализма»)\n" +
                "1939, 23 августа # Советско-германский договор о ненападении с секретным протоколом о разделе зон влияния (пакт Молотова-Риббентропа)\n" +
                "1939, 1 сентября # Начало Второй мировой войны. Нападение Германии на Польшу.\n" +
                "22 июня 1941 г. #  Нападение Германии на СССР. Начало Великой Отечественной войны\n" +
                "30 сентября – декабрь 1941 г. # Битва за Москву\n" +
                "сентябрь 1941 – январь 1943 – январь 1944 г. # Блокада Ленинграда\n" +
                "ноябрь 1942 – февраль 1943 гг. # Сталинградская битва\n" +
                "июль–август 1943 г. # Курская битва\n" +
                "28 ноября–1 декабря 1943 # Тегеранская конференция глав правительств СССР, США, Великобритании\n" +
                "июнь 1944 г. # Открытие второго фронта в Европе\n" +
                "февраль 1945 # Крымская (Ялтинская) конференция \"большой тройки\"\n" +
                "апрель-май 1945 г. # Битва за Берлин\n" +
                "8 мая 1945 г. # Подписание акта о безоговорочной капитуляции Германии.\n" +
                "июль – август 1945 г. # Потсдамская (Берлинская) конференция о послевоенном устройстве мира в Европе\n" +
                "2 сентября 1945 г. # Подписание акта о безоговорочной капитуляции Японии. Окончание Второй мировой войны\n" +
                "1949 # Создание Совета экономической взаимопомощи (СЭВ)\n" +
                "1953 # Смерть И.В. Сталина\n" +
                "1953–1964 # Деятельность Н.С. Хрущева как лидера СССР (первый секретарь ЦК КПСС, председатель Совета министров СССР, председатель Совета Обороны СССР)\n" +
                "1955 # Создание Организации Варшавского договора (ОВД)\n" +
                "1956 # XX съезд КПСС, постановление о разоблачении культа личности в СССР\n" +
                "1956 #  Ввод войск в Будапешт, подавление революции в Венгрии\n" +
                "1957, 4 октября # Первый запуск искусственного спутника земли\n" +
                "1961, 12 апреля # Первый полет человека в космос (Ю. Гагарин)\n" +
                "1962 # Карибский (\"ракетный\") кризис\n" +
                "1975 # Подписание Заключительного акта Хельсинкского совещания\n" +
                " 1979 # Ввод советских войск в Афганистан\n" +
                "1985–1991 # Период \"перестройки\" в СССР\n" +
                "1990, 12 июня # Принятие Декларации о суверенитете РСФСР\n" +
                "1991, 19–21 августа # Попытка государственного переворота в СССР, ГКЧП (путч)\n" +
                "1991, 8 декабря # Беловежская встреча лидеров СССР, России, Белоруссии, Казахстана; роспуск СССР и создание СНГ\n" +
                "1992, 1 января # Либерализация цен. Начало рыночной реформы, «шоковая терапия»\n" +
                "1993, октябрь # Конституционный кризис. Противостояние Президента РФ и Верховного Совета РФ");
        parseString(db,"D10", "VI в. # Легенда о князе Кие - основателе города Киева.\n" +
                "IX в. # Образование Древнерусского государства\n" +
                "860 # Поход русов на Константинополь.\n" +
                "882 # Объединение Новгорода и Киева при князе Олеге.\n" +
                "907, 911 # Походы Олега на Царьград. Договор с греками.\n" +
                "944  # Договор Игоря с Византией.\n" +
                "945  # Восстание древлян.\n" +
                "957 # Посольство Ольги в Константинополь.\n" +
                "964-972 # Походы Святослава.\n" +
                "980-1015 # Правление Владимира I.\n" +
                "988 # Принятие Русью христианства.\n" +
                "1015 # Восстание в Новгороде против варягов.\n" +
                "1019-1054 # Правление Ярослава Мудрого.\n" +
                "1068-1072 # Народные выступления в Киеве, Новгороде, Ростово-Суздальской, Черниговской землях.\n" +
                "1097 # Любечский съезд русских князей.\n" +
                "1113 # Восстание в Киеве.\n" +
                "1113-1125 # Правление Владимира Мономаха.\n" +
                "1136 # Установление республики в Новгороде.\n" +
                "1147 # Первое упоминание в летописи о Москве.\n" +
                "Начало ХII-конец XV вв. # Феодальная раздробленность Руси.\n" +
                "1169 # Взятие Киева войсками Андрея Боголюбского.\n" +
                "1202 # Образование Ордена меченосцев.\n" +
                "1206-1227 # Правление Чингисхана.\n" +
                "1219-1221 # Завоевание монголо-татарами Средней Азии.\n" +
                "1223, 31 мая # Битва на реке Калке.\n" +
                "Начало XIII в. # Образование Литовского государства.\n" +
                "1227-1255 # Правление Батыя.\n" +
                "1235-1243 # Завоевание монголо-татарами Закавказья.\n" +
                "1236  # Завоевание монголо-татарами Волжской Болгарии.\n" +
                "1237-1240 # Завоевание Руси монголо-татарами.\n" +
                "1237  # Образование Ливонского ордена.\n" +
                "1238, 4 марта # Битва на реке Сити.\n" +
                "1240, 15 июля # Невская битва.\n" +
                "1240 # Разгром монголо-татарами Киева.\n" +
                "1242, 5 апреля # Ледовое побоище.\n" +
                "1243 # Образование государства Золотая Орда.\n" +
                "1247 # Образование Тверского княжества.\n" +
                "1252-1263 # Александр Невский - великий князь Владимирский.\n" +
                "1262 # Восстания в русских городах против монголо-татар.\n" +
                "1276 # Образование Московского княжества.\n" +
                "1299 # Переезд митрополита из Киева во Владимир.\n" +
                "1301  # Присоединение Коломны к Москве.\n" +
                "1302  # Вхождение Переяславля-Залесского в состав Московского княжества.\n" +
                "1303  # Присоединение Можайска к Москве.\n" +
                "1310 # Принятие ислама как государственной религии Золотой Орды.\n" +
                "Ок.1313-1392 # Сергий Радонежский.\n" +
                "1327  # Восстание в Твери против золотоордынцев.\n" +
                "1328  # Перенесение центра митрополии в Москву.\n" +
                "1359-1389 # Правление Дмитрия Донского в Москве (с 1363 г. - великий князь Владимирский).\n" +
                "Ок. 1360-1430 # Андрей Рублев.\n" +
                "1363 # Победа литовских войск над ордынцами при Синих Водах. Вхождение Киева в состав Литвы.\n" +
                " 1367 # Строительство белокаменного Кремля в Москве.\n" +
                "1378 # Первая победа над золотоордынцами на р.Воже.\n" +
                "1380, 8 сентября # Куликовская битва.\n" +
                "1382 # Разгром Москвы Тохтамышем.\n" +
                "1385 # Кревская уния между Литвой и Польшей.\n" +
                "1393 # Присоединение Нижнего Новгорода к Москве.\n" +
                "1395 # Разорение Тимуром Золотой Орды.\n" +
                "1410, 15 июля # Грюнвальдская битва.\n" +
                "1425-1453 # Большая феодальная война между сыновьями и внуками Дмитрия Донского.\n" +
                "1437 # Образование Казанского ханства.\n" +
                "1439 # Флорентийская уния.\n" +
                "1443 # Образование Крымского ханства.\n" +
                "1448 # Избрание Ионы на русскую митрополию. Автокефалия русской православной церкви.\n" +
                "1453 # Падение Византийской империи.\n" +
                "1462-1505 # Правление Ивана III\n" +
                "1463 # Присоединение к Москве Ярославского княжества.\n" +
                "1469-1472 # Путешествие Афанасия Никитина в Индию.\n" +
                "1471 # Сражение на р. Шелони московских и новгородских войск.\n" +
                "1474 # Присоединение к Москве Ростова Великого.\n" +
                "1478 # Присоединение Новгорода Великого к Москве.\n" +
                "1480 # Стояние на р.Угре. Окончательное свержение монголо-татарского ига.\n" +
                "1484-1508 # Строительство нынешнего Московского Кремля. Сооружение соборов и Грановитой палаты, кирпичных стен.\n" +
                "1485 # Присоединение Твери к Москве.\n" +
                "1489 # Присоединение к Москве Вятской земли.\n" +
                "1497 # Судебник Ивана III.\n" +
                "Конец XV-начало XVI вв. # Образование Российского централизованного государства.\n" +
                "1500-1503, 1507-1508, 1512-1522, 1534-1537 # Русско-литовские войны.\n" +
                "1502 # Конец Золотой Орды.\n" +
                "1503  # Церковный собор по вопросу о монастырском землевладении (Нил Сорский - Иосиф Волоцкий).\n" +
                "1505-1533 # Правление Василия III.\n" +
                "1510 # Присоединение Пскова к Москве.\n" +
                "1514 # Присоединение Смоленска к Москве.\n" +
                "1521 # Присоединение Рязанской и Северской земель к Москве.\n" +
                "1533-1584 # Правление Ивана IV Грозного.\n" +
                "1547 # Восстание в Москве.\n" +
                "1549  # Начало созыва Земских соборов.\n" +
                "1550 # Судебник Ивана IV.\n" +
                "1551  # Стоглавый собор.\n" +
                "1552  # Присоединение Казанского ханства к Москве.\n" +
                "1552-1557 # Вхождение Поволжья в состав России.\n" +
                "1556 # Присоединение Астраханского ханства к России.\n" +
                "1558-1583 # Ливонская война.\n" +
                "1561 # Разгром Ливонского ордена.\n" +
                "1564 # Начало книгопечатания в России. «Апостол».\n" +
                "1565-1572 # Опричнина.\n" +
                "1569 # Люблинская уния. Образование Речи Посполитой.\n" +
                "1581 # Первое упоминание о заповедных годах.\n" +
                "1581  # Поход Ермака в Сибирь.\n" +
                "1582  # Ям-Запольское перемирие с Польшей.\n" +
                "1583  # Плюсский мир со Швецией.\n" +
                "1589 # Учреждение патриаршества. Патриарх Иов.\n" +
                "1591  # Смерть царевича Дмитрия в Угличе.\n" +
                "1592 # Составление писцовых и переписных книг.\n" +
                "1595  # Тявзинский мир со Швецией.\n" +
                "1596  # Брестская церковная уния.\n" +
                "1597  # Указ о пятилетнем сыске беглых.\n" +
                "1598-1605 # Правление Б.Ф.Годунова.\n" +
                "1603-1604 # Восстание Хлопка.\n" +
                "1605-1606 # Правление Лжедмитрия I.\n" +
                "1606-1607 # Восстание И.И.Болотникова.\n" +
                "1606-1610 # Правление Василия Шуйского.\n" +
                "1607 # Указ о пятнадцатилетнем сыске беглых.\n" +
                "1607-1610 # Лжедмитрий II. Тушинский лагерь.\n" +
                "1610-1613 # Семибоярщина.\n" +
                "1611,  март-июль # Первое ополчение.\n" +
                "1612,  26 октября # Освобождение от интервентов Москвы народным ополчением под руководством К.Минина и Д.Пожарского.\n" +
                "1613,  21 февраля # Избрание М.Ф.Романова на царство.\n" +
                "1617  # Столбовский мир со Швецией.\n" +
                "1618  # Деулинское перемирие с Польшей.\n" +
                "1645-1676 # Правление Алексея Михайловича.\n" +
                "1648-1654 # Освободительная война украинского народа против поляков под руководством Б.Хмельницкого.\n" +
                "1649 # Соборное уложение царя Алексея Михайловича.\n" +
                "1649 # Зборовский мир.\n" +
                "1651 # Белоцерковский мир.\n" +
                "1651 # Начало реформ патриарха Никона. Раскол.\n" +
                "1654, 8, января # Переяславская Рада.\n" +
                "1654-1667 # Война с Речью Посполитой за Украину.\n" +
                "1661  # Кардисский мир со Швецией.\n" +
                "1662 # «Медный бунт» в Москве.\n" +
                "1667 # Андрусовское перемирие с Речью Посполитой.\n" +
                "1667-1669 # «Поход за зипунами».\n" +
                "1667 # Новоторговый устав.\n" +
                "1667-1676 # Соловецкое восстание.\n" +
                "1670-1671 # Крестьянская война под предводительством С.Т.Разина.\n" +
                "1676-1682 # правление Федора Алексеевича.\n" +
                "1676-1681 # Война России с Турцией.\n" +
                "1682, 1698 # Стрелецкие восстания в Москве.\n" +
                "1682 # Отмена местничества.\n" +
                "1682-1689 # Правление Софьи.\n" +
                "1682-1725 # Царствование Петра I, до 1696 г. совместно с Иваном V (с 1682 по 1689 - при регентстве Софьи).\n" +
                "1686 # «Вечный мир» с Польшей.\n" +
                "1687  # Открытие Славяно-греко-латинской академии.\n" +
                "1687, 1689 # Крымские походы В.В.Голицына.\n" +
                "1689 # Нерчинский договор с Китаем.\n" +
                "1695, 1696 # Азовские походы Петра I.\n" +
                "1697-1698 # «Великое посольство».\n" +
                "1700-1721 # Северная война.\n" +
                "1700, 1 января # Введение нового летосчисления.\n" +
                "1703, 16 мая # Основание Санкт-Петербурга.\n" +
                "1707-1708 # Восстание под предводительством К.Булавина.\n" +
                "1708-1710 # Учреждение губерний.\n" +
                "1709, 27 июня # Полтавская битва.\n" +
                "1710-1711 # Прутский поход.\n" +
                "1711 # Учреждение Сената.\n" +
                "1713  # Перенесение столицы в Санкт-Петербург.\n" +
                "1714  # Указ о единонаследии.\n" +
                "1714, 27 июля # Победа русского флота при мысе Гангут.\n" +
                "1718-1721 # Учреждение коллегий.\n" +
                "1720  # Победа русского флота у острова Гренгам.\n" +
                "1721  # Разрешение покупать крестьян к заводам.\n" +
                "1721, 30 августа # Ништадтский мир со Швецией.\n" +
                "1721  # Учреждение Синода.\n" +
                "1721, 22 октября # Провозглашение Петра I императором.\n" +
                "1722 # Табель о рангах.\n" +
                "1722 # Указ о престолонаследии: император сам может назначить себе наследника\n" +
                "1722-1723 # Каспийский поход.\n" +
                "1725 # Открытие Академии наук в Петербурге.\n" +
                "1725-1727 # Царствование Екатерины I.\n" +
                "1726-1730 # Верховный тайный совет.\n" +
                "1727-1730 # Правление Петра П.\n" +
                "1730-1740 # Правление Анны Иоанновны. Бироновщина.\n" +
                "1731 # Отмена единонаследия.\n" +
                "1741-1761 # Правление Елизаветы Петровны.\n" +
                "1750 # Открытие первого русского театра в Ярославле.\n" +
                "1755, 25 января # Основание Московского университета.\n" +
                "1756-1763 # Семилетняя война.\n" +
                "1761-1762# Правление Петра III.\n" +
                "1762 # Манифест о вольности дворянства.\n" +
                "1762-1796 #Правление Екатерины II.\n" +
                "1764 # Секуляризация церковных имуществ.\n" +
                "1764 # Ликвидация гетманства на Украине.\n" +
                "1768 # Начало выпуска ассигнаций.\n" +
                "1767-1768 # Уложенная комиссия,\n" +
                "1768-1774 # Русско-турецкая война. Кючук-Кайнарджийский мир.\n" +
                "1771#Чумной бунт в Москве.\n" +
                "1772, 1793, 1795 # Разделы Польши.\n" +
                "1773-1775 # Восстание Е.И.Пугачева.\n" +
                "1775 # Учреждение о губерниях Российской империи.\n" +
                "1780 # Декларация о вооруженном нейтралитете.\n" +
                "1783 # Георгиевский трактат. Переход Восточной Грузии; под протекторат России.\n" +
                "1785 # Жалованные грамоты дворянству и городам.\n" +
                "1787-1791 #Русско-турецкая война. Ясский мир.\n" +
                "1796-1801 # Царствование Павла I\n" +
                "1797 # Манифест о трехдневной барщине.\n" +
                "1801-1825 # Царствование Александра I Павловича.\n" +
                "1802 # Учреждение министерств в России.\n" +
                "1803  # Указ о «вольных хлебопашцах».\n" +
                "1804-1813 # Русско-иранская война.\n" +
                "1805-1807 # Участие России в III и IV антинаполеоновских коалициях.\n" +
                "1805, ноябрь # Сражение при Аустерлице.\n" +
                "1806-1812 # Русско-турецкая война.\n" +
                "1807 # Тильзитский мир.\n" +
                "1810 # Создание Государственного совета.\n" +
                "1812, 12 июня-21 декабря # Отечественная война.\n" +
                "1812, 22 июля # Соединение 1-й и 2-й русских армий под Смоленском.\n" +
                "1812, 3-6 августа# Битва за Смоленск.\n" +
                "1812, 8 августа # Назначение М.И.Кутузова главнокомандующим русской армии.\n" +
                "1812, 26 августа # Сражение при Бородино.\n" +
                "1812, 1 сентября # Совещание в Филях.\n" +
                "1812, 7 октября # Уход французской армии из Москвы.\n" +
                "1812, 12 октября # Сражение под Малоярославцем.\n" +
                "1812, 14-17 ноября # Переправа французских войск через р.Березину.\n" +
                "1812, 21 декабря # Приказ М.И.Кутузова по армии об изгнании французской армии из пределов России.\n" +
                "1813-1814 # Заграничные походы русской армии.\n" +
                "1813 # «Битва народов» при Лейпциге.\n" +
                "1814, сентябрь-1815, май # Венский конгресс.\n" +
                "1816-1817 # Деятельность «Союза спасения».\n" +
                "1818-1821 # Деятельность «Союза благоденствия».\n" +
                "1820 # Восстание в Семеновском полку.\n" +
                "1821  # Образование Южного общества.\n" +
                "1822  # Образование Северного общества.\n" +
                "1823 # Образование Общества соединенных славян.\n" +
                "1825, 14 декабря # Восстание декабристов в Петербурге.\n" +
                "1825, 29 декабря -1826, 3 января # Восстание Черниговского полка.\n" +
                "1825-1855 # Царствование Николая I Павловича.\n" +
                "1826 # Издание «чугунного» цензурного устава.\n" +
                "1826-1828 # Русско-иранская война.\n" +
                "1828-1829 # Русско-турецкая война.\n" +
                "1837 # Строительство железной дороги из Петербурга в Царское Село.\n" +
                "1837-1841 # Проведение П.Д.Киселевым реформы управления государственными крестьянами. \n" +
                "1839-1843 # Денежная реформа Е.Ф.Канкрина.\n" +
                "1842 # Издание указа об «обязанных крестьянах».\n" +
                "1844-1849 # Деятельность тайного кружка М.В.Буташевича-Петрашевского.\n" +
                "1845 # Образование Славянского общества св.Кирилла и Мефодия.\n" +
                "1853-1856 # Создание «Вольной русской типографии».\n" +
                "1853,  ноябрь # Сражение при Синопе.\n" +
                "1854, 2 сентября # высадка англо-французских войск в Крыму.\n" +
                "1854, 2 сентября # Затопление русского флота в бухте Севастополя.\n" +
                "1854, 8 сентября # Сражение на  р.Альме.\n" +
                "1854,  сентябрь-1855, август # Оборона Севастополя.\n" +
                "1855-1881 # Царствование Александра II Николаевича.\n" +
                "1855,  август # Сражение на Черной речке.\n" +
                "1855  # Подписание Симодского договора России с Японией.\n" +
                "1855, ноябрь # Взятие русскими войсками крепости Каре.\n" +
                "1856  # Парижский конгресс.\n" +
                "1860 # Пекинский договор России с Китаем.\n" +
                "1861-1863 # Деятельность тайного кружка «Великорусе».\n" +
                "1861, 19 февраля # Отмена крепостного права в России.\n" +
                "1861-1864 # Деятельность организации «Земля и воля».\n" +
                "1864 # Судебная, земская и школьная реформы.\n" +
                "1864-1885 # Завоевание Средней Азии Россией.\n" +
                "1866 # Образование Туркестанского генерал-губернаторства.\n" +
                "1868 # Установление вассальной зависимости Бухарского эмирата от России.\n" +
                "1870 # Основание русской секции I Интернационала.\n" +
                "1870 # Издание «Городового положения».\n" +
                "1873  # Создание Союза трех императоров.\n" +
                "1874  # Военная реформа. Введение всеобщей воинской повинности.\n" +
                "1874  # Первое «хождение в народ».\n" +
                "1875  # Трактат России и Японии о разделе владений на Курильских островах и острове Сахалин.\n" +
                "1876  # Вхождение Кокандского ханства в состав России.\n" +
                "1876-1879 # Деятельность организации «Земля и воля».\n" +
                "1876 # Второе «хождение в народ».\n" +
                "1877-1878 # Русско-турецкая война.\n" +
                "1877, июль-декабрь # Оборона Шипкинского перевала.\n" +
                "1878 # Подписание Сан-Стефанского мирного договора.\n" +
                "1878 # Берлинский конгресс.\n" +
                "1879-1881 # Деятельность организации «Народная воля».\n" +
                "1879-1881 # Деятельность организации «Черный передел».\n" +
                "1881, 1 марта # Убийство Александра П.\n" +
                "1881-1894 # Царствование Александра III Алексадровича.\n" +
                "1881  # Принятие «Положения о мерах к охранению государственной безопасности и общественного спокойствия».\n" +
                "1882 # Перевод крестьян на обязательный выкуп.\n" +
                "1885 # Стачка на Никольской мануфактуре Т.С.Морозова в Орехово-Зуеве.\n" +
                "1887 # Циркуляр о «кухаркиных детях».\n" +
                "1889  # Принятие «Положения о земских начальниках».\n" +
                "1890 # Принятие «Положения о губернских и уездных земских учреждениях» (земская контрреформа).\n" +
                "1891-1894 # Оформление франко-русского союза.\n" +
                "1892 # Принятие «Городового положения» (городская контрреформа).\n" +
                "1894-1917 # Царствование Николая II Александровича.\n" +
                "1895 # Создание «Союза борьбы за освобождение рабочего класса».\n" +
                "1897 # Первая всеобщая перепись населения в России.\n" +
                "1897  # Денежная реформа С.Ю.Витте.\n" +
                "1898 # I съезд РСДРП.\n" +
                "1900, декабрь # Создание газеты «Искра».\n" +
                "1901  # «Обуховская оборона».\n" +
                "1902  # Объединение неонароднических кружков. Создание «Партии социалистов-революционеров».\n" +
                "1903, июль # Создание «Союза освобождения».\n" +
                "1903,  июль-август # Создание «Союза земцев-конституционалистов».\n" +
                "1904-1905 # Русско-японская война.\n" +
                "1904,  26-27 января # Нападение японских кораблей на русские эскадры в Порт-Артуре и Чемульпо.\n" +
                "1904,  август-1905, декабрь # Оборона Порт-Артура.\n" +
                "1905,  9 января # «Кровавое воскресенье».\n" +
                "1905, февраль # Сражение под Мукденом.\n" +
                "1905, май # Гибель русского флота у о.Цусима.\n" +
                "1905, июнь # Восстание на броненосце «Потемкин».\n" +
                "1905, июль-август # Создание Крестьянского союза.\n" +
                "1905, август # Подписание Портсмутского мира.\n" +
                "1905, октябрь # Всероссийская политическая стачка.\n" +
                "1905, октябрь # Создание «Конституционно-демократической партии» (кадеты).\n" +
                "1905, 17 октября # Издание Манифеста «Об усовершенствовании государственного порядка».\n" +
                "1905 # Создание «Союза русского народа».\n" +
                "1905, ноябрь # Отмена выкупных платежей.\n" +
                "1905, ноябрь # Создание «Союза 17 октября».\n" +
                "1905,  декабрь # Вооруженное восстание в Москве.\n" +
                "1906, апрель-июль # Деятельность I Государственной Думы.\n" +
                "1906, июль # Восстания в Свеаборге и Кронштадте.\n" +
                "1906,  9 ноября # Указ о выходе крестьян из общины.\n" +
                "1907 # Создание «Союза Михаила Архангела».\n" +
                "1907,  февраль-июнь # Деятельность II Государственной Думы.\n" +
                "1907, 3 июня # Роспуск Государственной Думы и принятие нового избирательного закона.\n" +
                "1907-1912 # Деятельность III Государственной Думы.\n" +
                "1912, 4 апреля # Ленский расстрел.\n" +
                "1912, ноябрь # Начало деятельности IV Государственной Думы.\n" +
                "1914, июль-1918, ноябрь # Первая мировая война.\n" +
                "1914, август # Восточно-Прусская операция русской армии.\n" +
                "1914,  август-сентябрь # Галицийская операция русской армии.\n" +
                "1915,  май-октябрь # Отступление русской армии.\n" +
                "1915,  август # Создание Прогрессивного блока.\n" +
                "1916,  май # «Брусиловский прорыв» русских войск.\n" +
                "1917,  23 февраля # Демонстрация в Петрограде.\n" +
                "1917, 25 февраля # Всеобщая политическая забастовка в Петрограде.\n" +
                "1917, 27 февраля # Образование Комитета Государственной Думы и Петроградского совета рабочих и солдатских депутатов.\n" +
                "1917, 2 марта # Отречение Николая II от престола. Образование Временного правительства. Установление двоевластия в России.\n" +
                "1917, июнь # VI съезд РСДРП(б). Курс большевиков на вооруженное восстание.\n" +
                "1917, август # Выступление генерала Л.Г.Корнилова.\n" +
                "1917,  24-26 октября # Вооруженное восстание в Петрограде. II Всероссийский съезд Советов. Образование Советского правительства. (Великая Октябрьская социалистическая революция).\n" +
                "1918,  5-6 января # Учредительное собрание.\n" +
                "1918, 3 марта # Брестский мир.\n" +
                "1918, май – 1920, декабрь # Гражданская война в России.\n" +
                "1918,  июль # Конституция РСФСР.\n" +
                "1919,  март # VIII съезд РКП(б).\n" +
                "1920,  март # X съезд РКП(б), переход к НЭПу.\n" +
                "1922, 30 декабря # Образование СССР.\n" +
                "1924,  январь # Конституция СССР.\n" +
                "1925, декабрь # XIV съезд ВКП(б). Курс на индустриализацию.\n" +
                "1927, декабрь # XV съезд ВКП(б). Курс на коллективизацию.\n" +
                "1929 # Начало сплошной коллективизации.\n" +
                "1936, декабрь # Конституция СССР.\n" +
                "1939, 23 августа # Пакт о ненападении с Германией.\n" +
                "1939, ноябрь - 1940, март # Советско-финляндская война.\n" +
                "1941, 22 июня - 1945, 9 мая # Великая Отечественная война.\n" +
                "1941,  5-6 декабря # Начало наступления под Москвой.\n" +
                "1942,  19 ноября - 1943, 2 февраля # Сталинградская битва. Начало коренного перелома.\n" +
                "1943,  июль – август - 1943, ноябрь # Курская битва.\n" +
                "1945, 8 мая # Капитуляция Германии.\n" +
                "1945, 8 августа - 2 сентября # Советско-японская война\n" +
                "1949, январь # Создание Совета экономической взаимопомощи (СЭВ).\n" +
                "1953, сентябрь # Избрание Первым секретарем ЦК КПСС Н.С.Хрущева.\n" +
                "1955, май # Создание Организации Варшавского Договора.\n" +
                "1955,  июль # Пленум по НТП.\n" +
                "1956,  февраль # XX съезд КПСС.\n" +
                "1957 # Реформа управления гражданской промышленностью. Создание совнархозов.\n" +
                "1959 # Визит Н.С. Хрущева в США.\n" +
                "1959-1965 # Семилетка.\n" +
                "1961, 12 апреля # Полет Ю.А.Гагарина в космос.\n" +
                "1961,  октябрь # XXII съезд КПСС. Принятие третьей программы КПСС.\n" +
                "1962,  октябрь # Карибский кризис.\n" +
                "1964, октябрь # Избрание Первым секретарем ЦК КПСС Л.И.Брежнева.\n" +
                "1970 # XXIV съезд КПСС.\n" +
                "1975  # Совещание по безопасности и сотрудничеству в Европе (Хельсинки).\n" +
                "1976  # XXV съезд КПСС.\n" +
                "1977, октябрь # Конституция СССР.\n" +
                "1981  # XXVI съезд КПСС.\n" +
                "1982  # Принятие Продовольственной программы.\n" +
                "1985, март # Избрание Генеральным секретарем ЦК КПСС М.С.Горбачева.\n" +
                "1986 # XXVII съезд КПСС.\n" +
                "1987-1991 # Период “перестройки” в СССР.\n" +
                "1988 # XIX Всесоюзная партконференция.\n" +
                "1989,  май-июнь # I Съезд народных депутатов СССР.\n" +
                "1990, март # III Съезд народных депутатов СССР. Избрание М.С.Горбачева Президентом СССР.\n" +
                "1991# Роспуск Совета Экономической Взаимопомощи и Организации Варшавского Договора.\n" +
                "1991,  12 июня # Избрание Б.Н.Ельцина Президентом РСФСР.\n" +
                "1991, 19-22 августа # Попытка государственного переворота (ГКЧП)\n" +
                "1991,  8 декабря # Беловежское соглашение о роспуске CCCI и создании Содружества независимых государств (СНГ).\n" +
                "1992,  март # Подписание Федеративного договора.\n" +
                "1993,  21 сентября # Указ Президента Б.Н.Ельцина о начале конституционной реформы в России и роспуске Верховного Совета.\n" +
                "1993, 3-4 октября # Вооруженные столкновения сторонников Верховного Совета с правительственными войсками в Москве.\n" +
                "1993, 12 декабря # Выборы в Государственную Думу и Совет Федерации. Принятие конституции РФ.\n" +
                "1995,  декабрь # Выборы в VI Государственную Думу.\n" +
                "1996,  16 июля – 3 июля # Выборы Президента Российской Федерации.\n" +
                "1999, 19 декабря # Выборы в VII Государственную Думу.\n" +
                "1999,  31 декабря # Указ о прекращении исполнения полномочий Президента РФ Ельциным Б. Н.\n" +
                "2000,  26 марта # Избрание В.В.Путина Президентом РФ.");
    }
}
