/*** custom function getChildList **/
use ace;
DROP FUNCTION IF EXISTS `getChildList`;
DELIMITER ||
CREATE FUNCTION `getChildList`(rootId BIGINT )
     RETURNS VARCHAR(1000)
BEGIN
       DECLARE pTemp VARCHAR(1000);
       DECLARE cTemp VARCHAR(1000);
       SET pTemp = '$';
       SET cTemp =cast(rootId as CHAR);
       WHILE cTemp is not null DO
         SET pTemp = concat(pTemp,',',cTemp);
         SELECT group_concat(id) INTO cTemp FROM sys_resources
         WHERE FIND_IN_SET(parent_id,cTemp)>0;
       END WHILE;
       RETURN pTemp;
END ||
DELIMITER ;


/*** custom function getChildList **/
DROP FUNCTION IF EXISTS `getChildCount`;
DELIMITER ||
CREATE FUNCTION `getChildCount`(rootId BIGINT)
     RETURNS INT
BEGIN
       DECLARE pTemp BIGINT DEFAULT 0 ;
       set pTemp = (select count(*) from sys_resources f_t where f_t.parent_id = rootId);
       RETURN pTemp;
END ||
DELIMITER ;
