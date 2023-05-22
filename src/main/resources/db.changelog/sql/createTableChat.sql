create table if not exists chat
(
    chat_id bigint primary key,
    status varchar not null default 'free'
)