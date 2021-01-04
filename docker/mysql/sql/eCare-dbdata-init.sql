use ecare;

INSERT INTO plans (id, name, price)
VALUES
    (1, "Dictator", 1000.0),
    (2, "Peasant" , 2.0),
    (3, "Exile"   , 50.0);

INSERT INTO options(id, name, price, turn_on_price, description)
VALUES
       (1, "Block everyone", 10, 1, "Whenever you want you can block the Internet in the whole country. Make them suffer"),
       (2, "Internet", 0, 1, "Use the magic to communicate with other people vie chats and send the catpics"),
       (3, "Calls", 0, 1, "Just take your phone and click call!"),
       (4, "Spam-suffer", 0, 1, "Use this opportunity to make your peasants suffer!"),
       (5, "Suffer", 0, 1, "Sorry, you will suffer for eternity. That's your dictator wish");

INSERT INTO plan_options(plan_id, option_id)
VALUES
       (1,1),
       (1,2),
       (1,3),
       (1,4),
       (2,2),
       (2,3),
       (3,5);

INSERT INTO users(id, name, last_name, birth_date, passport, address, email, password, enabled, authority)
VALUES
       (1, "Denis", "Verkhovskii", "1991-09-11", "1232 123122", "spb street 11", "verkh@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_DICTATOR"),
       (2, "Oleg", "Oooo", "1992-09-11", "42332 123122", "lo street 11", "oleg@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_USER"),
       (3, "Maria", "Mmmmm", "1941-09-11", "342 123122", "moscow street 11", "maria@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_USER"),
       (4, "Anna", "Aaaaa", "1985-09-11", "65443 123122", "dom 12", "anna@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_USER"),
       (5, "Peasant", "Peasantov", "1999-09-11", "23553 123122", "some box", "peasant@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_USER"),
       (6, "Exile", "Exilov", "1999-09-11", "23553 123122", "swamp", "exile@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_EXILE"),
       (7, "Admin", "Adminov", "1999-09-11", "23553 123122", "swamp", "admin@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_ADMIN");
