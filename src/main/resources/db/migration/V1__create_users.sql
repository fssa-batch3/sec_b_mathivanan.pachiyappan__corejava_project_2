use mathivanan_pachiyappan__corejava_project;

-- Create the Taluk table

CREATE TABLE Taluk (
    id INT PRIMARY KEY AUTO_INCREMENT,
    taluk_name VARCHAR(255)
);

-- Create the User table

CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    phone_number VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    voter_id INT,
    taluk_id INT,
    is_active BOOLEAN,
    FOREIGN KEY (taluk_id) REFERENCES Taluk(id)
);

-- Create the Election table

CREATE TABLE Election (
    id INT PRIMARY KEY AUTO_INCREMENT,
    election_name VARCHAR(255),
    election_date DATE,
    booth_address VARCHAR(255),
    taluk_id INT,
    is_active BOOLEAN,
    FOREIGN KEY (taluk_id) REFERENCES Taluk(id)
);

-- Create the Candidate table

CREATE TABLE Candidate (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    election_id INT,
    name VARCHAR(255),
    created_at DATE,
    is_active BOOLEAN,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (election_id) REFERENCES Election(id)
);