create database extra_database;

grant all privileges on extra_database.* to 'todo'@'%' identified by 'todosecret';