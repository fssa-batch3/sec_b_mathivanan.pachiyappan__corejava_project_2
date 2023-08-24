use mathivanan_pachiyappan__corejava_project;

-- Create the Taluk table

CREATE TABLE taluks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    taluk_name VARCHAR(255) not null,
    is_active BOOLEAN default true
);

-- Create the User table

CREATE TABLE users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(255) not null,
    password VARCHAR(255) not null,
    address VARCHAR(255) not null,
    voter_id INT not null,
    taluk_id INT not null,
    is_active BOOLEAN default true,
    FOREIGN KEY (taluk_id) REFERENCES taluks(id)
);

-- Create the Election table

CREATE TABLE elections (
    id INT PRIMARY KEY AUTO_INCREMENT,
    election_name VARCHAR(255) not null,
    election_date DATE not null,
    booth_address VARCHAR(255) not null,
    taluk_id INT not null,
    is_active BOOLEAN default true,
    FOREIGN KEY (taluk_id) REFERENCES taluks(id)
);

-- Create the Candidate table

CREATE TABLE candidates (
    id INT PRIMARY KEY AUTO_INCREMENT,
    candidate_eno INT not null,
    election_id INT not null,
    name VARCHAR(255) not null,
    created_at DATE not null,
    is_active BOOLEAN default true,
    FOREIGN KEY (election_id) REFERENCES elections(id)
);