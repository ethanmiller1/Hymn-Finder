select *
from mysql.user
order by user;

set password for root@`%`=password('');
set password for root@localhost=password('');

drop user if exists 'improving'@'localhost';
create user 'improving'@'localhost' identified by 'excellence';
grant all privileges on * . * to 'improving'@'localhost';
alter user 'improving'@'localhost' identified with mysql_native_password by 'excellence';

