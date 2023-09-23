insert into users(id, email, first_name,last_name, login, password,account_non_expired, account_non_locked, credentials_non_expired, enabled, system_role_name)
values (1000, 'noreply@info.com', 'SUPER_ADMIN', 'ADMIN', '001112233', '$2a$10$0sfd/CWTSyi5HdpIimDQje.9IYo3VZo8BDAOqGh3XngVuP71yl8ia', null, true, true, true, true, 'SYSTEM_ROLE_SUPER_ADMIN');
--Login: 001112233
--Password: 12345