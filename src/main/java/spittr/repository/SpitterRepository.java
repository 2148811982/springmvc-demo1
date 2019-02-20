package spittr.repository;

import spittr.domain.Spitter;

public interface SpitterRepository {

	Spitter insert(Spitter spitter);
	
	Spitter findByUsername(String username);
}
