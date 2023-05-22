create table if not exists Message
(
    message_id bigint generated always as identity primary key,
    chat_id    bigint not null,
    text       text    not null,
    constraint fk_chat_id foreign key (chat_id) references chat (chat_id)
)