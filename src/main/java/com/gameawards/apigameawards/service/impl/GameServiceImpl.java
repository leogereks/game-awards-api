package com.gameawards.apigameawards.service.impl;

import com.gameawards.apigameawards.domain.model.Game;
import com.gameawards.apigameawards.domain.model.GameRepository;
import com.gameawards.apigameawards.service.GameService;
import com.gameawards.apigameawards.service.exception.BusinessException;
import com.gameawards.apigameawards.service.exception.NoContentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository repository;

    @Override
    public List<Game> findAll() {
        List<Game> games = repository.findAll(Sort.by(Sort.Direction.DESC,"votes"));
        return games;
    }

    @Override
    public Game findById(Long id) {
        Optional<Game> game = repository.findById(id);
        return game.orElseThrow(() -> new NoContentException());
    }

    @Override
    public void insert(Game game) {
        repository.save(game);
    }

    @Override
    public void update(Long id, Game game) {
        Game gameDb = findById(id);
        if (gameDb.getId().equals(game.getId())){
        }else{
            throw new BusinessException("Os IDs para alteraçao estao divergentes");
        }
    }

    @Override
    public void delete(Long id) {
        Game gameDb = findById(id);
        repository.delete(gameDb);
    }

    @Override
    public void vote(Long id) {
        Game gameDb = findById(id);
        gameDb.setVotes(gameDb.getVotes()+1);
        update(id,gameDb);
    }
}
