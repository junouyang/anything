
INSERT INTO `federated_entity_metadata` (`id`, `version`, `federated_account_key`, `federated_entity_id`, `entity_type`, `resolved_name`, `federated_config_id`, `last_seen_timestamp`)
VALUES(1,0,'KAKAKA','kakakaka','FEDERATED_ACCOUNT','delete',NULL,0);

set @account_key1 = (select account_key from account where name='customer1');
set @account_key2 = (select account_key from account where name='customer2');
set @helloId0 = (select id from application where name='hello0');
set @helloId1 = (select id from application where name='hello1');
set @invalidId = 100;

INSERT INTO `federated_entity_metadata` (`id`, `version`, `federated_account_key`, `federated_entity_id`, `entity_type`, `resolved_name`, `federated_config_id`, `last_seen_timestamp`)
VALUES
    (0,0,@account_key1,@account_key1,'FEDERATED_ACCOUNT','customer1',NULL,0),
	(2,0,@account_key2,@account_key2,'FEDERATED_ACCOUNT','rename',NULL,0),
	(3,0,@account_key1,'10','FEDERATED_APPLICATION','hello0',NULL,0),
	(4,0,@account_key1,'11','FEDERATED_APPLICATION','rename',NULL,0),
	(5,0,@account_key1,'30','FEDERATED_APPLICATION','delete',NULL,0),
	(6,0,@account_key1,'15','FEDERATED_COMPONENT','web',NULL,0),
	(7,0,@account_key1,'30','FEDERATED_COMPONENT','delete',NULL,0),
	(8,0,@account_key1,'17','FEDERATED_COMPONENT','rename',NULL,0),
	(9,0,@account_key1,'1','FEDERATED_BT','/backend',NULL,0),
	(10,0,@account_key1,'2','FEDERATED_BT','rename',NULL,0),
	(11,0,@account_key1,'30','FEDERATED_BT','delete',NULL,0);



INSERT INTO `federated_entity_metadata` (`id`, `version`, `federated_account_key`, `federated_entity_id`, `entity_type`, `resolved_name`, `federated_config_id`, `last_seen_timestamp`)
VALUES (9,0,@account_key1,'1','FEDERATED_BT','/backend',NULL,0);
