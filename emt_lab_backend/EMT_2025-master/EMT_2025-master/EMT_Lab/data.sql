INSERT INTO country (id, name, continent) VALUES
                                              (2, 'Macedonia', 'Europe'),
                                              (3, 'USA', 'NorthAmerica'),
                                              (4, 'Japan', 'Asia');

INSERT INTO author (id, name, surname, country_id) VALUES
                                              (3, 'Name2', 'Surname2', 4),
                                              (4, 'Name3', 'Surname3', 2),
                                              (5, 'string', 'string', 2);

INSERT INTO book (id, name, category, author_id, availableCopies) VALUES
                                              (4, 'Book3', 'BIOGRAPHY', 4, 199);