package spittr.service;

import java.util.List;

import spittr.domain.Spittle;

public interface ISpittleService {

	List<Spittle> getSpittles(long max, int count);
	
	Spittle getSpittle(long spittleId);
}
