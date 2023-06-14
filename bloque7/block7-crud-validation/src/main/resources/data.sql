INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (1, 'Usuario', '1234', 'Rodolfo', 'Surmano', 'bosonit@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1990-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (2, 'Usuario', '1234', 'Rodolfo', 'Surmano', 'bosonit@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1990-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (3, 'Usuario', '1234', 'Rodolfo', 'Surmano', 'bosonit@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1990-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Persona (id, user_persona, password, name, surname, company_email, personal_email, city, active,
                     created_date,
                     imagen_url, termination_date)
VALUES (4, 'Usuario', '1234', 'Rodolfo', 'Surmano', 'bosonit@gmail.es', 'personal@gmail.es', 'Ciudad', false,
        '1990-01-01', 'www.url.com', '2000-01-01');

INSERT INTO Estudiantes (id_student, id_persona, horas_por_semana, comentarios, rama)
VALUES (1, 1, 12, 'No estudia nada', 'Ingeniería Software');

INSERT INTO Estudiantes (id_student, id_persona, horas_por_semana, comentarios, rama)
VALUES (2, 2, 12, 'No estudia nada', 'Ingeniería Software');

INSERT INTO Estudiantes (id_student, id_persona, horas_por_semana, comentarios, rama)
VALUES (3, 3, 12, 'No estudia nada', 'Ingeniería Software');

INSERT INTO Estudios (id_study, asignatura, comentarios, initial_date, finish_date)
VALUES (1, 'Disenio Algoritmos', 'No estudia nada', '1994-12-15', '1994-12-30');

INSERT INTO Estudios (id_study, asignatura, comentarios, initial_date, finish_date)
VALUES (2, 'Disenio Algoritmos', 'No estudia nada', '1994-12-15', '1994-12-30');

INSERT INTO Estudios (id_study, asignatura, comentarios, initial_date, finish_date)
VALUES (3, 'Disenio Algoritmos', 'No estudia nada', '1994-12-15', '1994-12-30');
