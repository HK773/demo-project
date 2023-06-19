package com.example.demoproject.service;

import java.util.List;

import com.example.demoproject.entitymodel.BankAccountType;

public interface BankAccountTypeDataService {
	
boolean existsByAccountType(String accountType);
	
	boolean existsById(Long id);
	
	BankAccountType createBankAccountTypeService(BankAccountType reqBankAccountType);
	
	List<BankAccountType> fetchAllBankAccountTypeService();
	
	List<BankAccountType> retrieveAllBankAccountTypeService();
		
	BankAccountType updateBankAccountTypeByIdService(
			BankAccountType reqBankAccountType,
				Long id);
		
	boolean deleteBankAccountTypeById(Long id);
	

}

