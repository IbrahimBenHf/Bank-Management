package tn.esprit.gestionbancaire.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.gestionbancaire.model.Client;
import tn.esprit.gestionbancaire.repository.ClientRepository;
import tn.esprit.gestionbancaire.services.IClientService;

@Service
public class ClientServiceImpl implements IClientService{

	@Autowired
	ClientRepository clientRepository;
	@Override
	public Client save(Client client) {
		// TODO Auto-generated method stub
		return clientRepository.save(client);
	}

}
