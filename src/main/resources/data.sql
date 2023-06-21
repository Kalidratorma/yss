--  drop database yss with (FORCE);
--  create database yss;

insert into public.sec_user (id, email, enabled, password, role, username)
values (1, 'example@example.org', true
, '$2a$10$rFqEhVwY46/vv.c9fBcjje3dhnkl0yzclfb614jBLuBPmk3wtBA0e', 'ADMIN', 'admin');

insert into public.sec_user (id, email, enabled, password, role, username)
values (2, 'example1@example.org', true
, '$2a$10$LKBCTqEASWRq3zeohixf..XDnkhwc2RS9QNMIYcaaBpdHv2o2q7zO', 'CLIENT', 'client');

insert into public.sec_user (id, email, enabled, password, role, username)
values (3, 'example2@example.org', true
, '$2a$10$F3WnNMf5k/4bedj0QNbX2OOcwhXoiehhipmgUE9vXYntSDnDKg/KC', 'CLIENT', 'client2');

insert into public.sec_user (id, email, enabled, password, role, username)
values (4, 'example3@example.org', true
, '$2a$10$ZWcw/u9dDmuKBpMX1WPHRO.WCqQdygmFY3JkQcDHdHw7FmUN604SK', 'CLIENT', 'client3');

insert into public.sec_user (id, email, enabled, password, role, username)
values (5, 'coach@example.org', true
, '$2a$10$QssOoL1MnTX7W/RWDrJIMOtJrCJa2K1gFdLIbW/BHKscfCal4lbwu', 'COACH', 'coach');

insert into contract (id, contract_number, contract_subject, contract_type, contractor_contract_number, exp_date,
                      payment_terms)
values (1, 'Тестовый Договор Клиента №1', 'Текст договора клиента', 'CLIENT', null, '2024-01-01', 'Net 15');

insert into contract (id, contract_number, contract_subject, contract_type, contractor_contract_number, exp_date,
                      payment_terms)
values (2, 'Тестовый Договор Клиента №2', 'Текст договора клиента', 'CLIENT', null, '2024-05-01', 'Net 15');

insert into contract (id, contract_number, contract_subject, contract_type, contractor_contract_number, exp_date,
                      payment_terms)
values (3, 'Тестовый Договор Тренера №1', 'Текст договора тренера', 'COACH', null, '2026-01-01', '50 на 50');

insert into contract (id, contract_number, contract_subject, contract_type, contractor_contract_number, exp_date,
                      payment_terms)
values (4, 'Тестовый Договор Клиента №3', 'Текст договора клиента', 'CLIENT', null, '2024-01-01', 'Net 15');

insert into public.position (id, name, short_name)
values (1, 'Нападающий', 'Нападающий');

insert into public.position (id, name, short_name)
values (2, 'Защитник', 'Защитник');

insert into public.position (id, name, short_name)
values (3, 'Вратарь', 'Вратарь');

insert into player
(id,surname,name, patronymic, birth_date,  number,  photo, sex,  team_year, contract_id, position_id)
values (1, 'Иванов', 'Иван', 'Иванович','2016-04-02', 77
, 'https://gidgood.ru/wp-content/uploads/2018/09/мальчик-6-лет_2-600x500.jpg', 'MALE', 2016, 1, 1);

insert into physiology (id, "date", grip, height, weight)
values (1, '2023-06-04', 'RIGHT', 1200, 30000);

insert into player_physiology_list (player_id, physiology_list_id)
values (1,1);

insert into stat (id, "date", goal, assist, penalty, points)
values (1, '2023-06-04', 10, 55, 20, 322);

insert into player_stats (player_id, stats_id)
values (1,1);

insert into public.parent (id, surname, name, patronymic, sex, phone_number, user_id)
values (1, 'Иванов', 'Иван', 'Васильевич', 'MALE', '+7 (999) 999-99-99', 2);

insert into public.parent (id, surname, name, patronymic, sex, phone_number, user_id)
values (2, 'Иванова', 'Лариса', 'Ивановна', 'FEMALE', '+7 (999) 999-99-98', 3);

insert into player_parents (player_id, parents_id)
values (1,1), (1,2);

insert into player
(id,surname,name, patronymic, birth_date,  number,  photo, sex,  team_year, contract_id, position_id)
values (2, 'Петров', 'Петр', 'Петрович','2016-06-06', 10
       , 'https://img.7ya.ru/pub/img/13080/mat-vo_01.JPG', 'MALE', 2016, 2, 2);

insert into physiology (id, "date", grip, height, weight)
values (2, '2023-06-04', 'LEFT', 1300, 35000);

insert into player_physiology_list (player_id, physiology_list_id)
values (2,2);

insert into stat (id, "date", goal, assist, penalty, points)
values (2, '2023-06-07', 5, 20, 2, 100);

insert into player_stats (player_id, stats_id)
values (2,2);

insert into public.parent (id, surname, name, patronymic, sex, phone_number, user_id)
values (3, 'Петров', 'Петр', 'Алексеевич', 'MALE', '+7 (999) 999-99-97', 4);

insert into player_parents (player_id, parents_id)
values (2,3);

insert into player
(id,surname,name, patronymic, birth_date,  number,  photo, sex,  team_year, contract_id, position_id)
values (3, 'Иванов', 'Андрей', 'Иванович','2015-03-02', 1
       , 'http://cdn1.imgbb.ru/user/23/233593/201411/b0f39d34be8a26e81e9344e0c0d3ef5d.jpg', 'MALE', 2015, 4, 3);

insert into physiology (id, "date", grip, height, weight)
values (3, '2023-06-04', 'RIGHT', 1350, 40000);

insert into player_physiology_list (player_id, physiology_list_id)
values (3,3);

insert into stat (id, "date", goal, assist, penalty, points)
values (3, '2023-06-04', 1, 2, 0, 322);

insert into player_stats (player_id, stats_id)
values (3,3);

insert into player_parents (player_id, parents_id)
values (3,1), (3,2);

insert into coach (id, coach_type, surname, name, patronymic, sex, education, phone_number,  contract_id, user_id)
values (1, 'Главный тренер', 'Смирнов', 'Андрей', 'Сергеевич', 'MALE', 'Российский университет спорта «ГЦОЛИФК»'
       , '+7 (999) 999-99-96', 3, 5);

insert into task (id, coach_id, name, qty, minutes, description)
values (1, 1, 'Ведение шайбы между стойками', 1, 15, 'Цель упражнения: Улучшить навыки контроля и ведения шайбы в тесных условиях, развить ловкость и маневренность на льду.

Оборудование: Хоккейная шайба, хоккейная клюшка, конусы или стойки для создания препятствий.

Инструкции:

1. Расположите стойки или конусы на льду вдоль прямой линии на расстоянии около 1 метра друг от друга. Это будет зона, через которую вам нужно будет провести шайбу.
2. Встаньте в начальной позиции перед первой стойкой, держа хоккейную клюшку в руках с шайбой на ней.
3. Начните движение, ведя шайбу между стойками. Цель состоит в том, чтобы маневрировать шайбой так, чтобы она не задела ни одной стойки.
4. При ведении шайбы используйте короткие и точные движения клюшкой. Контролируйте скорость и направление шайбы, чтобы она прошла через зону без соприкосновения со стойками.
5. Постепенно увеличивайте сложность упражнения, увеличивая скорость ведения шайбы или сокращая расстояние между стойками.');

insert into task_players (task_id, players_id)
values (1,1), (1,2);

insert into just_date ("date")
values ('2023-06-19');

insert into just_date ("date")
values ('2023-06-21');

insert into just_date ("date")
values ('2023-06-23');

insert into task_schedule (task_id, schedule_date)
values (1, '2023-06-19'), (1, '2023-06-21'), (1, '2023-06-23');

insert into parent_contracts (parent_id, contracts_id)
values (1,1), (2,1), (1,4) , (2,4), (3, 2);

insert into task_report (id, report, report_date, task_date, player_id, task_id)
values (1,
        'Дорогой дневник, мне не подобрать слов чтобы описать ту боль и унижение которые я испытал сегодня, моя жизнь поломана навсегда. Я не нашел в шкафу Нарнию! Но зато выполнил задание тренера!',
        '2023-06-19', '2023-06-19', 1, 1);