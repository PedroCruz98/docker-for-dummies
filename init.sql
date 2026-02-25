ALTER SESSION SET CONTAINER = FREEPDB1;
ALTER SESSION SET CURRENT_SCHEMA = myuser;

CREATE TABLE EVENTS (
                        uuid        VARCHAR2(36) PRIMARY KEY,
                        event_name  VARCHAR2(200) NOT NULL,
                        start_time  TIMESTAMP NOT NULL,
                        end_time    TIMESTAMP NOT NULL,
                        responsible VARCHAR2(100) NOT NULL,
                        local       VARCHAR2(200)
);

COMMIT;