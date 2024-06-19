package com.github.MateusHCandido.card_generating_service.infra.controller;

import com.github.MateusHCandido.card_generating_service.application.gateway.CardUseCase;
import com.github.MateusHCandido.card_generating_service.application.gateway.exception.CardAlreadyActiveException;
import com.github.MateusHCandido.card_generating_service.application.gateway.exception.CardAlreadyBlockedException;
import com.github.MateusHCandido.card_generating_service.application.gateway.exception.CardAlreadyCanceledException;
import com.github.MateusHCandido.card_generating_service.domain.entities.Card;
import com.github.MateusHCandido.card_generating_service.domain.entities.CardFactory;
import com.github.MateusHCandido.card_generating_service.infra.controller.dto.GetCardDto;
import com.github.MateusHCandido.card_generating_service.infra.controller.dto.PostCardDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardUseCase userCase;



    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CardFactory factory;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GetCardDto saveCard(@RequestBody PostCardDto dto){
        Card cardSaved = userCase.saveCard(dto.getCardName(), dto.getCardBrand(), dto.getCardReqIncome(), dto.getCardBasicLimit());
        return modelMapper.map(cardSaved, GetCardDto.class);
    }

    @GetMapping("/list-cards-with-required-income-less-or-equal/{reqIncome}")
    public List<GetCardDto> findByCardReqIncomeLessThanEqual(@PathVariable Long reqIncome){
        return userCase.findByCardReqIncomeLessThanEqual(reqIncome).stream()
                .map(card -> modelMapper.map(card, GetCardDto.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/list-cards-with-required-income-greater-or-equal/{reqIncome}")
    public List<GetCardDto> findByCardReqIncomeGreaterThanEqual(@PathVariable Long reqIncome){
        return userCase.findByCardReqIncomeGreaterThanEqual(reqIncome).stream()
            .map(card -> modelMapper.map(card, GetCardDto.class))
            .collect(Collectors.toList());
    }

    @GetMapping("/list-blocked-cards")
    public List<GetCardDto> listBlockedCards(){
        return userCase.listBlockedCard().stream()
                .map(card -> modelMapper.map(card, GetCardDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/list-active-cards")
    public List<GetCardDto> listActiveCards(){
        return userCase.listActivateCard().stream()
                .map(card -> modelMapper.map(card, GetCardDto.class))
                .collect(Collectors.toList());
    }
    @GetMapping("/list-canceled-cards")
    public List<GetCardDto> listCanceledCards(){
        return userCase.listCanceledCards().stream()
                .map(card -> modelMapper.map(card, GetCardDto.class))
                .collect(Collectors.toList());
    }

    @PutMapping("/block-card/{cardName}")
    public void blockCard(@PathVariable String cardName) throws CardAlreadyBlockedException {
        userCase.blockCard(cardName);
    }

    @PutMapping("/active-card/{cardName}")
    public void activateCard(@PathVariable String cardName) throws CardAlreadyActiveException {
        userCase.activateCard(cardName);
    }

    @PutMapping("/cancel-card/{cardName}")
    public void cancelCard(@PathVariable String cardName) throws CardAlreadyCanceledException {
        userCase.cancelCard(cardName);
    }
}
