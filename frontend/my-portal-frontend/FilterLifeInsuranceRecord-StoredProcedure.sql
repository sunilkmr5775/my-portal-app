DROP procedure IF EXISTS `FilterLifeInsuranceRecord`;
DELIMITER //
CREATE PROCEDURE `FilterLifeInsuranceRecord`(IN policyNo varchar(255),
								IN policyStatus varchar(255),
                                IN bankName varchar(255))
BEGIN  
    SELECT * FROM life_insurance
        WHERE 1=1 
        AND
            (life_insurance.POLICY_NO=policyNo OR policyNo IS NULL)
		AND
            (life_insurance.policy_status=policyStatus OR policyStatus IS NULL)
        AND
            (life_insurance.bank=bankName OR bankName IS NULL);
            
END //
DELIMITER ;