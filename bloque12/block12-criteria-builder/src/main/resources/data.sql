INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (1000, 'Usuario', '1234', 'Rodolfo', 'Surmano', 'bosonit@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1990-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (2000, 'Suario', '1234', 'Rodolfo', 'Surmano', 'bosonit1@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1980-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (3000, 'Susuario', '1234', 'Rodolfo', 'Surmano', 'bosonit2@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '2000-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (4000, 'Asusuario', '1234', 'Rodolfo', 'Surmano', 'bosonit3@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1970-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (5000, 'Wusuario', '1234', 'Rodolfo', 'Surmano', 'bosonit4@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1960-01-01', 'www.url.com', '2000-01-01');


INSERT INTO Profesor (id_profesor, id, comentarios, rama)
VALUES (1000, 1000, 'No estudia nada', 'Ingeniería Software');

INSERT INTO Profesor (id_profesor, id, comentarios, rama)
VALUES (2000, 2000, 'No estudia nada', 'Ingeniería Software');

INSERT INTO Estudiantes (id_student, id, horas_por_semana, comentarios, id_profesor, rama)
VALUES (1000, 3000, 12, 'No estudia nada', 1000, 'Ingeniería Software');

INSERT INTO Estudiantes (id_student, id, horas_por_semana, comentarios, id_profesor, rama)
VALUES (2000, 4000, 12, 'No estudia nada', 2000, 'Ingeniería Software');

INSERT INTO Estudios (id_study, id_profesor, asignatura, comentarios, initial_date, finish_date)
VALUES (1000, 1000, 'Disenio Algoritmos', 'No estudia nada', '1994-12-15', '1994-12-30');

INSERT INTO Estudios (id_study, id_profesor, asignatura, comentarios, initial_date, finish_date)
VALUES (2000, 2000, 'Disenio Algoritmos', 'No estudia nada', '1994-12-15', '1994-12-30');

INSERT INTO Estudios (id_study, id_profesor, asignatura, comentarios, initial_date, finish_date)
VALUES (3000, 1000, 'Disenio Algoritmos', 'No estudia nada', '1994-12-15', '1994-12-30');
