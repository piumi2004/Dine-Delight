package org.example.dinedelightsystems.web;

import org.example.dinedelightsystems.model.User;
import org.example.dinedelightsystems.repository.OrderRepository;
import org.example.dinedelightsystems.repository.UserRepository;
import org.example.dinedelightsystems.service.EventService;
import org.example.dinedelightsystems.service.ReservationService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {

    private final ReservationService reservationService;
    private final EventService eventService;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;

    public DashboardController(ReservationService reservationService,
                               EventService eventService,
                               UserRepository userRepository,
                               OrderRepository orderRepository) {
        this.reservationService = reservationService;
        this.eventService = eventService;
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails principal, Model model) {
        User user = userRepository.findByEmail(principal.getUsername()).orElseThrow();

        var upcomingReservations = reservationService.getUpcomingReservations(user);
        var upcomingEventBookings = eventService.getUpcomingEventBookings(user);
        var allOrders = orderRepository.findAllByUserOrderByCreatedAtDesc(user);

        // âœ… Updated to use Double total
        double totalSpent = allOrders.stream()
                .filter(order -> order.getStatus().name().equals("APPROVED"))
                .mapToDouble(order -> order.getTotal())
                .sum();

        // âœ… Reward points (1 point per LKR)
        int rewardPoints = (int) totalSpent;

        model.addAttribute("upcomingReservations", upcomingReservations);
        model.addAttribute("upcomingEventBookings", upcomingEventBookings);
        model.addAttribute("totalSpent", totalSpent);
        model.addAttribute("rewardPoints", rewardPoints);
        model.addAttribute("user", user);
        return "dashboard";
    }
}

