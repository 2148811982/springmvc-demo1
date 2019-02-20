package spittr.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spittr.domain.Spittle;
import spittr.repository.SpittleRepository;

@Service
public class SpittleService implements ISpittleService{
	
	@Autowired
	private SpittleRepository spittleRepo;

	public List<Spittle> getSpittles(long max, int count) {
		return spittleRepo.findSpittles(max, count);
	}

	public Spittle getSpittle(long spittleId) {
		return spittleRepo.findSpittle(spittleId);
	}

}
