package spittr.service;

import spittr.domain.Spitter;

public interface ISpitterService {

	Spitter save(Spitter spitter);
	
	Spitter findByUsername(String username);
}
