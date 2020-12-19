CREATE TABLE IF NOT EXISTS `plans` (
    id INT unsigned NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS `options` (
    id INT unsigned NOT NULL,
    name VARCHAR(255) NOT NULL,
    price DOUBLE NOT NULL,
    turn_on_price DOUBLE NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS `plan_options` (
    plan_id INT unsigned NOT NULL,
    option_id INT unsigned NOT NULL,
    PRIMARY KEY(plan_id, option_id),
    FOREIGN KEY (plan_id) REFERENCES plans (id),
    FOREIGN KEY (option_id) REFERENCES options (id)
);

CREATE TABLE IF NOT EXISTS `subcribers` (
    id INT unsigned NOT NULL,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_date DATETIME NOT NULL,
    passport VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS `contracts` (
    phone_nubmer INT unsigned NOT NULL,
    plan_id INT unsigned NOT NULL,
    PRIMARY KEY(phone_nubmer),
    FOREIGN KEY(plan_id) REFERENCES plans(id)
);

CREATE TABLE IF NOT EXISTS `subcribers_contracts` (
    subcriber_id INT unsigned NOT NULL,
    contract_phone_nubmer INT unsigned NOT NULL,
    PRIMARY KEY(subcriber_id, contract_phone_nubmer),
    FOREIGN KEY(subcriber_id) REFERENCES subcribers(id),
    FOREIGN KEY(contract_phone_nubmer) REFERENCES contracts(phone_nubmer)
);

CREATE TABLE IF NOT EXISTS `subcribers_options` (
    contract_id INT unsigned NOT NULL,
    option_id INT unsigned NOT NULL,
    PRIMARY KEY(plan_id, option_id),
    FOREIGN KEY (subcriber_id) REFERENCES plans (id),
    FOREIGN KEY (option_id) REFERENCES options (id)
);


