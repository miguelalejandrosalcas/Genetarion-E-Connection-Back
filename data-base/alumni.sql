



DROP DATABASE IF EXISTS alumni;

CREATE DATABASE alumni;

USE alumni;



CREATE TABLE administrators (

                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                name VARCHAR(150) NOT NULL,

                                email VARCHAR(150) NOT NULL UNIQUE,

                                password_hash VARCHAR(255) NOT NULL,

                                role VARCHAR(50) NOT NULL DEFAULT 'editor',

                                active BIT NOT NULL DEFAULT 1,

                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP

);



CREATE TABLE categories (

                            id BIGINT AUTO_INCREMENT PRIMARY KEY,

                            name VARCHAR(100) NOT NULL UNIQUE,

                            description TEXT,

                            active BIT NOT NULL DEFAULT 1

);



CREATE TABLE resource_types (

                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                name VARCHAR(50) NOT NULL UNIQUE

);



CREATE TABLE resources (

                           id BIGINT AUTO_INCREMENT PRIMARY KEY,

                           title VARCHAR(200) NOT NULL,

                           description TEXT,

                           category_id BIGINT,

                           type_id BIGINT,

                           section ENUM('library','recording') NOT NULL DEFAULT 'library',

                           url VARCHAR(1000) NOT NULL,

                           thumbnail_url VARCHAR(1000) NULL,

                           file_name VARCHAR(255) NULL,

                           file_size BIGINT NULL,

                           duration_minutes INT,

                           publication_date DATE NOT NULL DEFAULT (CURRENT_DATE),

                           featured BIT NOT NULL DEFAULT 0,

                           active BIT NOT NULL DEFAULT 1,

                           views INT NOT NULL DEFAULT 0,

                           downloads INT NOT NULL DEFAULT 0,

                           created_by BIGINT,

                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                           updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

                           FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE SET NULL,

                           FOREIGN KEY (type_id) REFERENCES resource_types(id) ON DELETE SET NULL,

                           FOREIGN KEY (created_by) REFERENCES administrators(id) ON DELETE SET NULL

);



CREATE TABLE programs (

                          id BIGINT AUTO_INCREMENT PRIMARY KEY,

                          name VARCHAR(150) NOT NULL,

                          description TEXT

);



CREATE TABLE skills (

                        id BIGINT AUTO_INCREMENT PRIMARY KEY,

                        skill_name VARCHAR(150) NOT NULL,

                        description TEXT

);



CREATE TABLE learning_paths (

                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                name VARCHAR(150) NOT NULL,

                                description TEXT,

                                id_programs BIGINT NOT NULL,

                                active BIT NOT NULL DEFAULT 1,

                                FOREIGN KEY (id_programs) REFERENCES programs (id)

                                    ON DELETE RESTRICT

                                    ON UPDATE CASCADE

);



-- Tabla intermedia: una ruta puede cubrir varias skills

-- y una skill puede aparecer en varias rutas (relación N:M)

CREATE TABLE learning_path_skills (

                                      id_learning_paths BIGINT NOT NULL,

                                      id_skills BIGINT NOT NULL,

                                      PRIMARY KEY (id_learning_paths, id_skills),

                                      FOREIGN KEY (id_learning_paths) REFERENCES learning_paths (id)

                                          ON DELETE CASCADE

                                          ON UPDATE CASCADE,

                                      FOREIGN KEY (id_skills) REFERENCES skills (id)

                                          ON DELETE CASCADE

                                          ON UPDATE CASCADE

);



CREATE TABLE job_boards (

                            id BIGINT AUTO_INCREMENT PRIMARY KEY,

                            name VARCHAR(100) NOT NULL,

                            url VARCHAR(1000) NOT NULL,

                            description TEXT,

                            logo_url VARCHAR(1000),

                            active BIT NOT NULL DEFAULT 1

);



CREATE TABLE stories (

                         id BIGINT AUTO_INCREMENT PRIMARY KEY,

                         alumni_name VARCHAR(150) NOT NULL,

                         program VARCHAR(150),

                         photo_url VARCHAR(1000),

                         testimonial TEXT NOT NULL,

                         trajectory TEXT,

                         video_url VARCHAR(1000),

                         featured BIT NOT NULL DEFAULT 0,

                         active BIT NOT NULL DEFAULT 1,

                         publication_date DATE NOT NULL DEFAULT (CURRENT_DATE),

                         published_by BIGINT,

                         FOREIGN KEY (published_by) REFERENCES administrators(id) ON DELETE SET NULL

);



CREATE TABLE interactions (

                              id BIGINT AUTO_INCREMENT PRIMARY KEY,

                              resource_id BIGINT,

                              event_type ENUM('view','download') NOT NULL,

                              session_id VARCHAR(100) NOT NULL,

                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                              FOREIGN KEY (resource_id) REFERENCES resources(id) ON DELETE CASCADE

);



CREATE TABLE uploaded_files (

                                id BIGINT AUTO_INCREMENT PRIMARY KEY,

                                administrator_id BIGINT,

                                filename VARCHAR(300),

                                storage_url VARCHAR(2000) NOT NULL,

                                mime_type VARCHAR(100),

                                size_bytes BIGINT,

                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

                                FOREIGN KEY (administrator_id) REFERENCES administrators(id) ON DELETE SET NULL

);

ALTER TABLE categories
    ADD COLUMN category_type ENUM('CV','LINKEDIN','INTERVIEW','NETWORKING','ENGLISH','PORTFOLIO','NEGOTIATION','PERSONAL_DEV') NOT NULL;
    
ALTER TABLE categories DROP COLUMN name;