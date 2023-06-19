package com.example.demoproject.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.demoproject.entitymodel.BankAccountType;
import com.example.demoproject.repository.BankAccountTypeRepository;

@Service
public class BankAccountTypeDataServiceImpl implements BankAccountTypeDataService {

	@Autowired
	@Qualifier("bankAccountTypeRepository")
	private BankAccountTypeRepository bankAccountTypeRepository;
	
	BankAccountTypeDataServiceImpl(BankAccountTypeRepository bankAccountTypeRepository) {
		this.bankAccountTypeRepository = bankAccountTypeRepository;
	}
 
 @Override
	public boolean existsByAccountType(String accountType) {
		
		return bankAccountTypeRepository.existsByAccountType(accountType);
	}

	@Override
	public boolean existsById(Long id) {
	
		return bankAccountTypeRepository.existsById(id);
	}

	@Override
	public BankAccountType createBankAccountTypeService(BankAccountType reqBankAccountType) {
			
		if (Objects.nonNull(reqBankAccountType)) {
			BankAccountType data = bankAccountTypeRepository.save(reqBankAccountType);
			
			return data;
		}
		return null;
	}

	@Override
	public List<BankAccountType> fetchAllBankAccountTypeService() {
		
		if (bankAccountTypeRepository.findByStatus("Active")!=null) {
			return bankAccountTypeRepository.findByStatus("Active");
		}
		
		return null;
	}

	@Override
	public List<BankAccountType> retrieveAllBankAccountTypeService() {
		
		if (bankAccountTypeRepository.findAll().isEmpty()) {
			return null;
		}
		return bankAccountTypeRepository.findAll();
		
	}

	@Override
	public BankAccountType updateBankAccountTypeByIdService(BankAccountType reqBankAccountType, Long id) {
		
		if (Objects.isNull(reqBankAccountType) || id==0) {
			
			return null;
		}
		
		if (bankAccountTypeRepository.existsByAccountType(
				reqBankAccountType.getAccountType())==true &&
						bankAccountTypeRepository.existsById(id)==false) {
			return null;
		} else {
			BankAccountType bankAccountTypeData = bankAccountTypeRepository.findById(id).get();
			
			bankAccountTypeData.setAccountType(reqBankAccountType.getAccountType());
			bankAccountTypeData.setStatus(reqBankAccountType.getStatus());
			
			bankAccountTypeRepository.save(bankAccountTypeData);
			
			return bankAccountTypeData;
		}
		
	}

	@Override
	public boolean deleteBankAccountTypeById(Long id) {
		
		if (bankAccountTypeRepository.existsById(id)==false) {
			return false;
		} 
		else {
			BankAccountType bankAccountTypeData = bankAccountTypeRepository.findById(id).get();
			
			//Soft delete
			bankAccountTypeData.setStatus("Inactive");
			bankAccountTypeRepository.save(bankAccountTypeData);
			
			//delete
			//repository.delete(bankAccountTypeData);
			
			return true;
		}
		
	}


}

