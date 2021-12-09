package uk.gov.mydata.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("/")
public class DemoController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/user")
    public String selectUser(@RequestParam(name = "user") String userId, Model model) {
        model.addAttribute("userId", userId);
        String userName = "Unknown";

        switch (userId) {
            case "a8745051-904d-4a95-b2f7-31071e3af6c8":
                userName = "Stuart";
                break;
            case "a8745051-904d-4a95-b2f7-31071e3af6c2":
                userName = "Mike";
                break;
            
        }
        model.addAttribute("userName", userName);
        return "select-mode";
    }

    @PostMapping("/mode")
    public String selectMode(@RequestParam(name = "mode") String mode, @RequestParam(name="userId") String userId, Model model) {
        model.addAttribute("userId", userId);
        model.addAttribute("mode", mode);
        String userName = "Unknown";

        switch (userId) {
            case "a8745051-904d-4a95-b2f7-31071e3af6c8":
                userName = "Stuart";
                break;
            case "a8745051-904d-4a95-b2f7-31071e3af6c2":
                userName = "Mike";
                break;
        }
        model.addAttribute("userName", userName);

        return "mode-" + mode;
    }

}
