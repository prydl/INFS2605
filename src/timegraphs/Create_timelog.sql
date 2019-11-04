create table timelog (
    task_title varchar(200) not null,
    start_time int not null,
    end_time int not null,
    color varchar(20) not null,
    total_duration int not null,
    entry_description varchar(200) not null,
    task_description varchar(200) not null,
    doDate varchar(30) not null,
    dueDate varchar(30) not null,
    task_priority int not null
);