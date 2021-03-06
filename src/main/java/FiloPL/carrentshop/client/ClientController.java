package FiloPL.carrentshop.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService) {this.clientService = clientService;}

    @GetMapping
    public ModelAndView getClientList(@RequestParam(required = false) String searchWhat, @RequestParam(required = false) SearchClientOption searchBy){
        ModelAndView model = new ModelAndView("clientList");
        if (searchWhat == null) {
            model.addObject("clients", clientService.getAllClients());
        } else {
            model.addObject("clients", clientService.searchClients(searchWhat, searchBy));
        }
        model.addObject("options", SearchClientOption.values());
        return model;
    }

    @GetMapping("/{id}")
    public ModelAndView getClientDetails(@PathVariable int id){
        ModelAndView model = new ModelAndView("clientDetail");
        model.addObject("client", clientService.getClient(id));
        return model;
    }

    @GetMapping("/add")
    public ModelAndView getAddClientForm(){
        ModelAndView model = new ModelAndView("clientDetail");
        model.addObject("client", new Client());
        return model;
    }

    @PostMapping(value = {"/add", "/{id}"})
    public String saveClient(@ModelAttribute Client client, @PathVariable(required = false) Integer id){
        if (id != null) {
            client.setId(id);
        }
        clientService.saveClient(client);
        return "redirect:/client";
    }
}
