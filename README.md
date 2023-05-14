Программа работает по данному заданию: 
***************************************
Есть две хэш таблицы.

Ключами являются URLы.
Значениями - html код страниц, находящихся по этим урлам.

Обе таблицы отражают состояние всех страниц некоторого заданного
множества веб сайтов.

Первая таблица - состояние этих сайтов на вчера.
Вторая - на сегодня.

Задача - написать код который бы, пользуясь этими таблицами составил
письмо следующего формата:

Здравствуйте, дорогая и.о. секретаря

За последние сутки во вверенных Вам сайтах произошли следующие изменения:

Исчезли следующие страницы:{здесь список урлов}
Появились следующие новые страницы {здесь список урлов}
Изменились следующие страницы {здесь список урлов}

С уважением,
автоматизированная система
мониторинга.
***************************************
.
.
.
Содержит 2 класса: StateUrl, содержащий 2 хеш таблицы в полях и имеющий методы для загрузки данных в эти таблицы(первая загрузка значений,
обновление таблицы, содержащую состояние сайтов на сегодня), и класс MailCreator, создающий на основе данных объекта StateUrl текстовый файл с письмом.
StateUrl работает с структурой файлов как из папки resources.
Main содержит метод test, который создаёт объект StateUrl, загружая данных из файлов yesterday.json и today.json,
создаёт объект MailCreator, который формирует по первому объекту текстовый файл. Поток уходит в сон на 5 сек. Обновляется
созданный обхект StateUrl, путём вызова метода, обновляющий сегодняшнюю таблицу данными из файла tomorrow.json. Объект
MailCreator создаёт новый текстовый файл по обновленному объекту StateUrl.