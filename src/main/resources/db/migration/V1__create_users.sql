Use e_voting;

-- Create the e_voting database if it doesn't exist
CREATE DATABASE IF NOT EXISTS e_voting;

-- Use the e_voting database
USE e_voting;

-- Create the Taluk table
CREATE TABLE Taluk (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255)
);

-- Create the Election table
CREATE TABLE Election (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255),
    date DATE,
    booth_address VARCHAR(255),
    taluk_id INT,
    FOREIGN KEY (taluk_id) REFERENCES Taluk(id)
);

-- Create the User table
CREATE TABLE User (
    id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255),
    password VARCHAR(255),
    address VARCHAR(255),
    voter_id INT,
    taluk_id INT,
    FOREIGN KEY (taluk_id) REFERENCES Taluk(id)
);

-- Create the Candidate table
CREATE TABLE Candidatcandidatee (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT,
    election_id INT,
    name VARCHAR(255),
    created_at DATE,
    FOREIGN KEY (user_id) REFERENCES User(id),
    FOREIGN KEY (election_id) REFERENCES Election(id)
);