package com.devfay.baseapp.raffleapp.controller;

import com.devfay.baseapp.raffleapp.model.Raffle;
import com.devfay.baseapp.raffleapp.service.RaffleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/raffles")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class RaffleController {

    private RaffleService raffleService;

    @GetMapping
    public String listRaffles(Model model) {
        List<Raffle> raffles = raffleService.listRaffles();
        model.addAttribute("raffles", raffles);
        return "raffle/raffles";
    }

    @GetMapping("/{id}")
    public String getRaffle(@PathVariable Long id, Model model) {
//        Raffle raffle = raffleService.getRaffle(id);
//        List<RaffleNumber> numbers = raffleService.getRaffleNumbers(id);
//        model.addAttribute("raffle", raffle);
//        model.addAttribute("numbers", numbers);
        return "buy-raffle";
    }

    @PostMapping
    public String createRaffle(@ModelAttribute Raffle raffle) {
        raffleService.createRaffle(raffle);
        return "redirect:/raffle/raffles";
    }

    @PostMapping("/{raffleId}/assign-trader/{traderId}")
    public String assignTraderToRaffle(@PathVariable Long raffleId, @PathVariable Long traderId) {
        raffleService.assignTraderToRaffle(raffleId, traderId);
        return "redirect:/raffle/raffles/" + raffleId;
    }
}

