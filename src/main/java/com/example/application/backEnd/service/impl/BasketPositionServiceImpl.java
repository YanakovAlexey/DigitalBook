package com.example.application.backEnd.service.impl;

import com.example.application.backEnd.builder.BasketPositionBuilder;
import com.example.application.backEnd.domain.BasketPosition;
import com.example.application.backEnd.reporitory.BasketPositionRepository;
import com.example.application.backEnd.service.BasketPositionService;
import com.example.application.backEnd.viewModel.BasketPositionViewModel;
import com.example.application.backEnd.viewModel.BasketViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketPositionServiceImpl implements BasketPositionService {

    private final BasketPositionBuilder basketPositionBuilder;
    private final BasketPositionRepository basketPositionRepository;

    public BasketPositionServiceImpl(BasketPositionBuilder basketPositionBuilder,
                                     BasketPositionRepository basketPositionRepository) {
        this.basketPositionBuilder = basketPositionBuilder;
        this.basketPositionRepository = basketPositionRepository;
    }

    @Override
    public void create(BasketPosition request) {
        basketPositionBuilder.createBook(request);
        basketPositionRepository.save(request);
    }


    @Override
    public List<BasketPositionViewModel> getAll() {
        List<BasketPosition> bookList = basketPositionRepository.findAll();
        List<BasketPositionViewModel> bookViewModels = new ArrayList<>();
        for (BasketPosition item : bookList) {
            bookViewModels.add(basketPositionBuilder.build(item));
        }
        return bookViewModels;
    }

    @Override
    public BasketViewModel getById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        basketPositionRepository.deleteById(id);
    }

    @Override
    public List<BasketPosition> findAllByIdBasket(Long id) {
        var basketPosition = basketPositionRepository.findAllByIdBasket(id);

        return basketPosition;
    }

    @Override
    public List<BasketPosition> findAllByIsPaid() {
        var basketPosition = basketPositionRepository.findAllByIsPaidTrue();
        if (basketPosition == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return basketPosition;
    }


}
