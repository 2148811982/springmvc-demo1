package spittr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spittr.domain.Spitter;
import spittr.repository.SpitterRepository;

@Service
public class SpitterService implements ISpitterService {

	@Autowired
	private SpitterRepository spitterRepository;
	
	public Spitter save(Spitter spitter) {
		return spitterRepository.insert(spitter);
	}

	public Spitter findByUsername(String username) {
		return spitterRepository.findByUsername(username);
	}

}
