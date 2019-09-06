CREATE TABLE master.dbo.event_queue (
    id bigint IDENTITY(1,1) NOT NULL,
    data varchar(50) NOT NULL,
    CONSTRAINT PK__event_queue PRIMARY KEY (id)
)

INSERT INTO master.dbo.event_queue
(data)
VALUES('123456');
INSERT INTO master.dbo.event_queue
(data)
VALUES('654321');
INSERT INTO master.dbo.event_queue
(data)
VALUES('789065');
INSERT INTO master.dbo.event_queue
(data)
VALUES('098761');
