package ek.kea.wishlist_gruppe8.controller;


import ek.kea.wishlist_gruppe8.model.Wish;
import ek.kea.wishlist_gruppe8.model.WishList;
import ek.kea.wishlist_gruppe8.service.WishListService;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WishListController {
    private final WishListService wishListService;

    public WishListController(WishListService wishListService) { // constructor injection
        this.wishListService = wishListService;
    }

    @GetMapping("/")
    public String welcome() {
        return "index";
    }

    @GetMapping("/wishlist/{id}")
    public String getWishlistById(@PathVariable int id, Model model) {
        WishList wishList = wishListService.getWishListById(id);
        if (wishList == null) return "wishlist-not-found"; // tell the user instead of silently redirecting
        model.addAttribute("wishList", wishList);
        return "wishlist";
    }
    @GetMapping("/wishlist/{id}/wish/new")
    public String addNewWish(@PathVariable int id, Model model){
        WishList wishList = wishListService.getWishListById(id);
        if (wishList == null) return "redirect:/"; //if the wishlist id doesn’t exist, send the user to the front page instead of crashing
       model.addAttribute("wishList",wishList);
       model.addAttribute("wish", new Wish()); //gives the form an empty wish to fill in
       return "wish-form";
    }

    @PostMapping("/wishlist/{id}/wishes")
    public String saveWishes (@PathVariable int id, @ModelAttribute Wish wish, Model model){
        try {
            wishListService.saveNewWish(id,wish);
            return "redirect:/wishlist/" + id;
        } catch (DataIntegrityViolationException e) { // wishlist id doesn't exist (foreign key)
            return "redirect:/";
        } catch (IllegalArgumentException e) { // invalid wish
            WishList wishList = wishListService.getWishListById(id);
            if (wishList == null) return "redirect:/";
            model.addAttribute("wishList", wishList);
            model.addAttribute("error", e.getMessage());
            return "wish-form"; // show the form again with the error and what the user typed
        }
    }
    @GetMapping("/wishlist/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        WishList wishList = wishListService.getWishListById(id);
        if (wishList == null) return "redirect:/";
        model.addAttribute("wishList", wishList);
        return "edit-wishlist";
    }
    @PostMapping("/wishlist/edit/{id}")
    public String editWishList(@PathVariable int id, @ModelAttribute WishList wishList, Model model) {
        try {
            if (wishListService.editWishList(id, wishList) == null) return "redirect:/"; // wishlist doesn't exist
            return "redirect:/wishlist/" + id; // back to the wishlist you just edited
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // empty name
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "A wishlist with that name already exists"); // name is UNIQUE
        }
        wishList.setWishList_id(id); // the form needs the id to post back to
        return "edit-wishlist"; // show the form again with the error and what the user typed
    }

    @PostMapping("/wish/edit/{wish_id}")
    public String editWish(@PathVariable int wish_id, @ModelAttribute Wish wish, Model model) {
        try {
            Wish saved = wishListService.editWish(wish_id, wish);
            if (saved == null) return "redirect:/"; // wish doesn't exist
            return "redirect:/wishlist/" + saved.getWishlist_id(); // back to the wishlist the wish belongs to
        } catch (IllegalArgumentException e) {
            Wish existing = wishListService.getWishById(wish_id);
            if (existing == null) return "redirect:/";
            wish.setWish_id(wish_id);
            wish.setWishlist_id(existing.getWishlist_id());
            model.addAttribute("error", e.getMessage());
            return "edit-wish"; // show the form again with the error and what the user typed
        }
    }

    @PostMapping("/wish/delete/{wish_id}")
    public String deleteWish(@PathVariable int wish_id, @RequestParam int wishlistId) {
        wishListService.deleteWish(wish_id);
        return "redirect:/wishlist/" + wishlistId; // stay on the same wishlist page
    }
    //@PathVariable and @ModelAttribute determine how Spring receives data.
    @PostMapping("/wishlist/delete/{id}")
    public String deleteWishLists(@PathVariable int id) {
            wishListService.deleteWishList(id);
        return "redirect:/index";
    }


    @GetMapping("/create")
    public String showCreate() {
        return "createWishList"; // skal hedde det samme som HTML-filen (uden .html)
    }

    // Modtager formularen når man trykker Gem
    @PostMapping("/create/add")
    public String addWishlist(@RequestParam String title,
                              @RequestParam(required = false) String description,
                              Model model) {
        try {
            int id = wishListService.createWishList(title, description); // gem i databasen
            return "redirect:/wishlist/" + id; // vis den nye ønskeseddel
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage()); // tom titel
        } catch (DuplicateKeyException e) {
            model.addAttribute("error", "Der findes allerede en ønskeseddel med den titel"); // titel er UNIQUE
        }
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        return "createWishList"; // vis formularen igen med fejl
    }

    //Show all Wishlists
    @GetMapping("/index")
    public String showAllWishLists(Model model) {
        model.addAttribute("wishLists", wishListService.getAllWishLists());
        return "allWishlists";
    }

    //edit wish
    @GetMapping("/wish/edit/{wish_id}")
    public String showEditWishForm(@PathVariable int wish_id, Model model) {
        Wish wish = wishListService.getWishById(wish_id);
        if (wish == null) return "redirect:/";
        model.addAttribute("wish", wish);
        return "edit-wish";
    }




}

