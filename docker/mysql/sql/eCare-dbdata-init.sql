use ecare;

INSERT INTO plans (id, name, price)
VALUES
    (1, "Dictator", 1000.0),
    (2, "Peasant" , 2.0),
    (3, "Exile"   , 50.0),
    (4, "Call me baby", 1.0),
    (5, "In the web", 1.0),
    (6, "Happy letter", 1.0),
    (7, "Happy dispatch", 0.5);

INSERT INTO options(id, name, price, turn_on_price, description)
VALUES
       (1, "Block everyone", 10, 1, "Whenever you want you can block the Internet in the whole country. Make them suffer"),
       (2, "Internet", 0, 1, "Use the magic to communicate with other people vie chats and send the catpics"),
       (3, "Calls", 0, 1, "Just take your phone and click call!"),
       (4, "Spam-suffer", 0, 1, "Use this opportunity to make your peasants suffer!"),
       (5, "Suffer", 0, 1, "Sorry, you will suffer for eternity. That's your dictator wish"),
       (6, "News", 0, 1, "Receive news everyday"),
       (7, "Bieber", 0, 1, "Let them suffer listening Justin Bieber"),
       (8, "You don't pay", 0, 1, "Ley your friend pay for your call"),
       (9, "You're on wire", 0, 1, "All your actions would be reported to the ruler"),
       (10, "Last call", 0, 1, "Call someone and make a harakiri");

INSERT INTO plan_options(plan_id, option_id, undisablable)
VALUES
       (1,1, 1),
       (1,2, 0),
       (1,3, 0),
       (1,4, 1),
       (1,6, 0),
       (1,7, 0),
       (1,8, 0),

       (2,2, 1),
       (2,3, 0),
       (2,6, 0),
       (2,7, 0),
       (2,8, 0),
       (2,9, 1),

       (3,6, 1),
       (3,5, 1),
       (3,9, 1),

       (4,3, 1),
       (4,6, 1),
       (4,9, 1),

       (5,2, 1),
       (5,6, 1),
       (5,9, 1),

       (6,2, 1),
       (6,3, 1),
       (6,6, 1),
       (6,9, 1),

       (7,10, 1),
       (7,6, 1),
       (7,9, 1);

INSERT INTO users(id, name, last_name, birth_date, passport, address, email, password, blocked, authority)
VALUES
       (1, "Denis", "Verkhovskii", "1991-09-11", "1232 123122", "spb street 11", "verkh@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 0, "ROLE_DICTATOR"),
       (2, "Oleg", "Oooo", "1992-09-11", "42332 123122", "lo street 11", "oleg@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 0, "ROLE_USER"),
       (3, "Maria", "Mmmmm", "1941-09-11", "342 123122", "moscow street 11", "maria@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 0, "ROLE_USER"),
       (4, "Anna", "Aaaaa", "1985-09-11", "65443 123122", "dom 12", "anna@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 1, "ROLE_USER"),
       (5, "Peasant", "Peasantov", "1999-09-11", "23553 123122", "some box", "peasant@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 0, "ROLE_USER"),
       (6, "Exile", "Exilov", "1999-09-11", "23553 123122", "swamp", "exile@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 0, "ROLE_USER"),
       (7, "Admin", "Adminov", "1999-09-11", "23553 123122", "swamp", "admin@gmail.com", "$2a$10$8.UnVuG9HHgffUDAlk8qfOuVGkqRzgVymGe07xd00DMxs.AQubh4a", 0, "ROLE_ADMIN");

INSERT INTO contracts(id, phone_number, user_id, plan_id)
VALUES
       (11, "79650358599", 1, 1),
       (12, "79650358598", 2, 2),
       (13, "79650358597", 3, 2),
       (14, "79650358596", 4, 2),
       (15, "79650358595", 5, 2),
       (16, "79650358594", 6, 3),
       (17, "79650358593", 7, 1);

INSERT INTO contract_options(contract_id, option_id, undisablable)
VALUES
        (11,1, 1),(17,1, 1),
        (11,4, 1),(17,4, 1),
        (11,8, 0),(17,8, 0),

        (12,2, 1),(13,2, 1),(14,2, 1),(15,2, 1),
        (12,3, 0),(13,3, 0),(14,3, 0),(15,3, 0),
        (12,8, 0),(13,8, 0),(14,8, 0),(15,8, 0),
        (12,9, 1),(13,9, 1),(14,9, 1),(15,9, 1),

        (16,5, 1),
        (16,9, 1);

INSERT INTO option_rules(id, option_id1, rule, option_id2)
VALUES
       (1, 1, "INCOMPATIBLE", 5),
       (2, 2, "INCOMPATIBLE", 5),
       (3, 3, "INCOMPATIBLE", 5),
       (4, 4, "INCOMPATIBLE", 5);