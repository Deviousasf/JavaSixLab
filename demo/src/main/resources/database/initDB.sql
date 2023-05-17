CREATE TABLE IF NOT EXISTS results              -- создание таблицы
(
    id INTEGER PRIMARY KEY ,
    param1 DOUBLE PRECISION NOT NULL,
    param2 DOUBLE PRECISION NOT NULL,
    summ DOUBLE PRECISION NOT NULL,
    sub DOUBLE PRECISION NOT NULL,
    mul DOUBLE PRECISION NOT NULL,
    div DOUBLE PRECISION NOT NULL
);

-- генерация ID
CREATE SEQUENCE IF NOT EXISTS results_id_seq START WITH 1 INCREMENT BY 1;
--DROP TABLE IF EXISTS results;                 -- удаление таблицы
--DROP SEQUENCE IF EXISTS results_id_seq;       -- удаление генерации ID