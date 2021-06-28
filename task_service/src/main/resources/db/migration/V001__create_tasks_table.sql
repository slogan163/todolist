CREATE EXTENSION pgcrypto;

CREATE TABLE TASKS
(
    id uuid default public.gen_random_uuid() not null primary key,
    text varchar not null,
    creationTime timestamp not null,
    estimationTime timestamp not null,
    color integer not null,
    userId uuid
);

create index tasksEstimationTimeIndex on TASKS(estimationTime);
create index tasksUserIdIndex on TASKS(userId);