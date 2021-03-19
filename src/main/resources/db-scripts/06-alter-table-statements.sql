ALTER TABLE unbound.sermon
ADD archive_resource VARCHAR(255);

ALTER TABLE unbound.sermon
DROP COLUMN archive_resource;

ALTER TABLE unbound.sermon
MODIFY archive_resource VARCHAR(500);