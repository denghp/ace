/*** custom function getChildList **/
DROP FUNCTION IF EXISTS getChildList;
DELIMITER ||
CREATE FUNCTION `getChildList`(rootId INT)
     RETURNS VARCHAR(1000)
BEGIN
       DECLARE pTemp VARCHAR(1000);
       DECLARE cTemp VARCHAR(1000);
       SET pTemp = '$';
       SET cTemp =cast(rootId as CHAR);
       WHILE cTemp is not null DO
         SET pTemp = concat(pTemp,',',cTemp);
         SELECT group_concat(id) INTO cTemp FROM sys_resource
         WHERE FIND_IN_SET(parent_id,cTemp)>0;
       END WHILE;
       RETURN pTemp;
END ||
DELIMITER ;
/*** custom function getChildList **/
DROP FUNCTION IF EXISTS getChildCount;
DELIMITER ||
CREATE FUNCTION `getChildCount`(rootId INT)
     RETURNS INT
BEGIN
       DECLARE pTemp INT DEFAULT 0 ;
       set pTemp = (select count(*) from sys_resource f_t where f_t.parent_id = rootId);
       RETURN pTemp;
END ||
DELIMITER ;