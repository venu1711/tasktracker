-- ======================
-- Users Table
--  =====================
CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(150) UNIQUE NOT NULL
);

-- ======================
-- Tasks Table
-- ======================
CREATE TABLE tasks (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(200) NOT NULL,
                       description TEXT
);

-- ======================
-- Many-to-Many: Task ↔ User
-- ======================
CREATE TABLE task_user (
                           task_id BIGINT NOT NULL,
                           user_id BIGINT NOT NULL,
                           PRIMARY KEY (task_id, user_id),
                           CONSTRAINT fk_task FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,
                           CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- ======================
-- SubTasks Table
-- ======================
CREATE TABLE sub_tasks (
                           id BIGINT AUTO_INCREMENT PRIMARY KEY,
                           title VARCHAR(200) NOT NULL,
                           status VARCHAR(50) DEFAULT 'PENDING',

    -- Foreign key to parent task
                           task_id BIGINT,
                           CONSTRAINT fk_subtask_task FOREIGN KEY (task_id) REFERENCES tasks(id) ON DELETE CASCADE,

    -- Foreign key to assigned user
                           user_id BIGINT,
                           CONSTRAINT fk_subtask_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE SET NULL
);