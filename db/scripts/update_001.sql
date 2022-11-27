CREATE TABLE tasks (
   id SERIAL PRIMARY KEY,
   description TEXT,
   created TIMESTAMP,
   isDone BOOLEAN
);