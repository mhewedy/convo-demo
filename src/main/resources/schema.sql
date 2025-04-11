create table if not exists conversation_holder
(
    id                 varchar(50) primary key,
    expires_at         timestamp,
    conversation_class varchar(500),
    conversation_value varchar(8000)
);