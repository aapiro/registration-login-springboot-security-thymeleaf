package com.devfay.baseapp.raffleapp.controller;

import com.devfay.baseapp.raffleapp.model.Purchase;
import com.devfay.baseapp.raffleapp.model.Raffle;
import com.devfay.baseapp.raffleapp.model.RaffleNumber;
import com.devfay.baseapp.raffleapp.model.User;
import com.devfay.baseapp.raffleapp.service.PurchaseService;
import com.devfay.baseapp.raffleapp.service.RaffleService;
import com.devfay.baseapp.raffleapp.service.RaffleUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/purchases")
@CrossOrigin(origins = "*")
@AllArgsConstructor
public class PurchaseController {

    private PurchaseService purchaseService;
    private RaffleUserService userService;
    private RaffleService raffleService;

    @GetMapping("/buy/{raffleId}")
    public String showPurchaseForm(@PathVariable Long raffleId, Model model, Principal principal) {
        Raffle raffle = raffleService.getRaffle(raffleId);
        List<RaffleNumber> numbers = raffleService.getRaffleNumbers(raffleId);
        numbers.add(RaffleNumber.builder()
                .id(1L)
                .number(10)
                .build());
        numbers.add(RaffleNumber.builder().id(2L).number(0).state("sold").build());
        numbers.add(RaffleNumber.builder().id(3L).number(2).state("reserved").build());
        Set<User> traders = raffle.getTraders();
//        String userType = userService.findByName(principal.getName()).getType();

        model.addAttribute("raffle", raffle);
        model.addAttribute("numbers", numbers);
        model.addAttribute("traders", traders);
        model.addAttribute("userType", null);
        return "raffle/buy-raffle";
    }

    @PostMapping("/buy")
    public String processAction(@RequestParam Long raffleId, @RequestParam Long traderId,
                                @RequestParam String name, @RequestParam String phone,
                                @RequestParam List<Integer> numbers, @RequestParam String action,
                                Model model) {
        User purchaser = userService.findByName(name);
        if (purchaser == null) {
            purchaser = new User();
            purchaser.setName(name);
            purchaser.setPhone(phone);
            purchaser.setType("purchaser");
            purchaser = userService.save(purchaser);
        }

        User trader = userService.findById(traderId).orElse(null);

        for (int number : numbers) {
            RaffleNumber raffleNumber = purchaseService.getRaffleNumber(raffleId, number);

            switch(action) {
                case "buy":
                    if (!raffleNumber.getState().equals("free")) {
                        throw new RuntimeException("The raffle number is not available for purchase");
                    }
                    raffleNumber.setState("sold");
                    break;
                case "reserve":
                    if (!raffleNumber.getState().equals("free")) {
                        throw new RuntimeException("The raffle number is not available for reservation");
                    }
                    raffleNumber.setState("reserved");
                    break;
                case "release":
                    if (!purchaser.getType().equals("ADMIN")) {
                        throw new RuntimeException("Only ADMIN users can release numbers");
                    }
                    raffleNumber.setState("free");
                    break;
                default:
                    throw new RuntimeException("Invalid action");
            }

            raffleService.save(raffleNumber);

            if (action.equals("buy")) {
                Purchase purchase = new Purchase();
                purchase.setPurchaser(purchaser);
                purchase.setRaffleNumber(raffleNumber);
                purchase.setTrader(trader);
                purchase.setState("completed");
                purchase.setPurchaseDate(new Date().toString());
                purchaseService.buyRaffleNumber(purchase);
            }
        }

        model.addAttribute("message", "Action performed successfully!");
        return "redirect:/raffle/purchases";
    }

    @GetMapping
    public String listPurchases(Model model) {
        // Assuming the authenticated user is retrieved
        User authenticatedUser = purchaseService.getAuthenticatedUser();
        List<Purchase> purchases = purchaseService.listPurchasesByUser(authenticatedUser);
        model.addAttribute("purchases", purchases);
        return "raffle/purchases";
    }
}
