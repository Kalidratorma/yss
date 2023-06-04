insert into contract (id, contract_number, contract_subject, contract_type, contractor_contract_number, exp_date,
                      payment_terms)
values (1, 'Тестовый Договор №1', 'Текст договора', 'CLIENT', null, '2024-01-01', 'Net 15');

insert into public.position (id, name, short_name)
values (1, 'Нападающий', 'Нападающий');

insert into player
(id,surname,name, patronymic, birth_date,  number,  photo, sex,  team_year, contract_id, position_id)
values (1, 'Иванов', 'Иван', 'Иванович','2016-04-02', 77, null, 'MALE', 2016, 1, 1);

insert into public.parent (id, surname, name, patronymic, sex, phone_number, email, login, password)
values (1, 'Иванов', 'Иван', 'Федорович', 'MALE', '+7 (999) 999-99-99'
       , 'example@example.org', 'ivanov', 'password');

insert into public.parent (id, surname, name, patronymic, sex, phone_number, email, login, password)
values (2, 'Иванова', 'Лариса', 'Ивановна', 'FEMALE', '+7 (999) 999-99-98'
       , 'example1@example.org', 'ivanova', 'password');

insert into player_parents (player_id, parents_id)
values (1,1), (1,2);