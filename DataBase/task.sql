CREATE TABLE task (
    task_id SERIAL PRIMARY KEY,
    list_id BIGINT NOT NULL,
    task_title VARCHAR(255) NOT NULL,
    completed BOOLEAN NOT NULL,
    user_id BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (list_id) REFERENCES task_list (list_id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users (user_id) ON DELETE CASCADE
);