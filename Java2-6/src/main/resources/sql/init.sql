-- полное удаление прежней базы данных
DROP DATABASE IF EXISTS university_java2_6_db;

-- создание новой базы данных
CREATE DATABASE university_java2_6_db WITH ENCODING 'UTF8' TEMPLATE template0;

-- подключение к базе данных (psql)
\c library_db;

-- Примечания. Данные об академических группах: название, 
-- количество человек (не может превышать на очное форме обучения 20 человек, на заочной 30 человек), 
-- специальность, курс, форма обучения (очная или заочная).
-- создание таблицы
CREATE TABLE groups (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    amount_students INTEGER NOT NULL CHECK (amount_students >= 0),
    specialization VARCHAR(255) NOT NULL,
    course INTEGER NOT NULL CHECK (course BETWEEN 1 AND 6),
    study_form VARCHAR(10) NOT NULL CHECK (study_form IN ('очная', 'заочная'))
);

-- Данные о студентах: группа, фамилия, имя, отчество, пол, дата рождения, платник/бюджетник, количество не сданных экзаменов в последнюю сессию 
-- (не может превышать 2).
CREATE TABLE students (
    id SERIAL PRIMARY KEY,
    group_id INTEGER NOT NULL REFERENCES groups(id) ON DELETE CASCADE,
    last_name VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255) NOT NULL,
    sex VARCHAR(1) NOT NULL CHECK (sex IN ('м', 'ж')),
    birthday DATE NOT NULL,
    funding_type VARCHAR(20) NOT NULL CHECK (funding_type IN ('платник', 'бюджетник')),
    failed_exams INTEGER NOT NULL CHECK (failed_exams BETWEEN 0 AND 2)
);

CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    login VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL CHECK (role IN ('common', 'dean', 'admin'))
);

INSERT INTO users (login, password, role)
VALUES
('user', '12345', 'common'),
('dean', '12345', 'dean'),
('admin', '12345', 'admin');

INSERT INTO groups (name, amount_students, specialization, course, study_form)
VALUES
    ('25ИСИТ1д', 3, 'Информационные системы и технологии', 1, 'очная'),
    ('25МИ1д', 3, 'Физико-математическое образование (математика и информатика)', 1, 'очная'),
    ('25МФ1д', 3, 'Физико-математическое образование (математика и физика)', 1, 'очная'),
    ('25ПИ1з', 3, 'Прикладная информатика', 1, 'заочная'),
    ('25ПИнж1д', 2, 'Программная инженерия', 1, 'очная'),
    ('25ПМ1з', 2, 'Прикладная математика', 1, 'заочная'),
    ('25УИР1д', 2, 'Управление информационными ресурсами', 1, 'очная'),

    ('24ИСИТ1д', 2, 'Информационные системы и технологии', 2, 'очная'),
    ('24КБ1з', 2, 'Кибербезопасность', 2, 'заочная'),
    ('24МИ1д', 2, 'Физико-математическое образование (математика и информатика)', 2, 'очная');

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
VALUES
    (1, 'Иванов', 'Алексей', 'Петрович', 'м', '2004-03-12', 'бюджетник', 0),
    (1, 'Смирнова', 'Екатерина', 'Игоревна', 'ж', '2003-07-25', 'платник', 1),
    (1, 'Козлов', 'Денис', 'Артёмович', 'м', '2004-09-10', 'бюджетник', 0),

    (2, 'Кузнецов', 'Дмитрий', 'Андреевич', 'м', '2005-01-18', 'бюджетник', 2),
    (2, 'Попова', 'Анна', 'Сергеевна', 'ж', '2004-11-03', 'бюджетник', 0),
    (2, 'Васильева', 'Дарья', 'Ильинична', 'ж', '2005-06-21', 'платник', 1),

    (3, 'Соколов', 'Никита', 'Владимирович', 'м', '2002-09-14', 'платник', 1),
    (3, 'Морозова', 'Ольга', 'Денисовна', 'ж', '2001-12-30', 'платник', 2),
    (3, 'Фёдоров', 'Илья', 'Павлович', 'м', '2003-05-11', 'бюджетник', 0),

    (4, 'Зайцев', 'Максим', 'Олегович', 'м', '2002-02-18', 'платник', 1),
    (4, 'Новикова', 'Татьяна', 'Алексеевна', 'ж', '2003-10-15', 'платник', 0),
    (4, 'Сидорова', 'Елена', 'Михайловна', 'ж', '2002-07-09', 'платник', 2),

    (5, 'Волков', 'Илья', 'Артёмович', 'м', '2003-05-21', 'бюджетник', 0),
    (5, 'Лебедева', 'Мария', 'Николаевна', 'ж', '2002-08-09', 'платник', 1);

DROP TABLE IF EXISTS 
    groups, 
    students
CASCADE;

ALTER TABLE groups DROP COLUMN amount_students;

TRUNCATE TABLE students RESTART IDENTITY;

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
SELECT 
    3, 
    'Иванов_' || i, 
    'Иван', 
    'Иванович', 
    'м', 
    '2005-01-01', 
    'бюджетник', 
    0
FROM generate_series(1, 19) AS i;

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
SELECT 
    6, 
    'Заочников_' || i, 
    'Петр', 
    'Петрович', 
    'м', 
    '2003-05-15', 
    'платник', 
    0
FROM generate_series(1, 29) AS i;

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
SELECT 
    8, 
    (ARRAY['Романов', 'Захаров', 'Соловьев', 'Поляков', 'Никитин', 'Борисов', 'Жуков', 'Андреев', 'Титов', 'Марков'])[floor(random() * 10 + 1)],
    (ARRAY['Даниил', 'Никита', 'Егор', 'Матвей', 'Ярослав', 'Роман', 'Тимофей', 'Владислав', 'Игорь', 'Глеб'])[floor(random() * 10 + 1)],
    'Викторович', 'м', '2004-09-10'::date + (random() * 300)::int, 
    'бюджетник', floor(random() * 2)
FROM generate_series(1, 19);

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
SELECT 
    10, 
    (ARRAY['Игнатьева', 'савельева', 'Щербакова', 'Фролова', 'Кудрявцева', 'Куликова', 'Калинина', 'Павлова', 'Гусева', 'Киселева'])[floor(random() * 10 + 1)],
    (ARRAY['София', 'Анна', 'Мария', 'Алиса', 'Ева', 'Виктория', 'Полина', 'Варвара', 'Александра', 'Вероника'])[floor(random() * 10 + 1)],
    'Сергеевна', 'ж', '2004-03-20'::date + (random() * 300)::int, 
    'бюджетник', 0
FROM generate_series(1, 19);

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
SELECT 
    2, 
    (ARRAY['Тарасов', 'Белов', 'Комаров', 'Киселев', 'Макаров', 'Андреев', 'Носов', 'Антонов', 'Тихонов', 'Григорьев'])[floor(random() * 10 + 1)],
    (ARRAY['Константин', 'Богдан', 'Артемий', 'Юрий', 'Вадим', 'Денис', 'Руслан', 'Тимур', 'Степан', 'Марк'])[floor(random() * 10 + 1)],
    'Петрович', 'м', '2005-02-15'::date + (random() * 300)::int, 
    'платник', floor(random() * 3)
FROM generate_series(1, 19);

INSERT INTO students (group_id, last_name, first_name, middle_name, sex, birthday, funding_type, failed_exams)
SELECT 
    12, 
    (ARRAY['Осипов', 'Зайцев', 'Герасимов', 'Щербаков', 'Кудрявцев', 'Куликов', 'Баранов', 'Ефимов', 'Медведев', 'Сорокин'])[floor(random() * 10 + 1)],
    (ARRAY['Артур', 'Григорий', 'Леонид', 'Валерий', 'Олег', 'Станислав', 'Филипп', 'Эдуард', 'Альберт', 'Борис'])[floor(random() * 10 + 1)],
    'Юрьевич', 'м', '2004-11-01'::date + (random() * 300)::int, 
    'бюджетник', 1
FROM generate_series(1, 19);