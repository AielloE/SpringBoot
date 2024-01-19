package com.example.ProgettoLibreria;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UtenteController {
    @Autowired
    private IUtenteRep iUtenteRep;
    @Autowired
    private ILibroRep iLibroRep;
    @GetMapping("/")
    public String dashboard(){
        return "dashboard";
    }

    @GetMapping("/registrazione")
    public String showRegister(Persona persona){
        return "registrazioneuser";
    }

    @PostMapping("/postRegistrazione")
    public String  postRegistrazione(@Valid Persona persona, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "registrazioneuser";
        }
        iUtenteRep.save(new Utente(persona.nome,persona.cognome,persona.username,persona.password));
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLogin(Login login) {
        return "loginuser";
    }

    @PostMapping("/postLogin")
    public String postLogin(Login login, HttpSession session) {
        Utente utente = iUtenteRep.login(login.username, login.password);

        if(utente != null){
            session.setAttribute("utente", utente);
            System.out.println(session.getAttribute("utente"));
            return "redirect:/home";
        }else{
            return "loginuser";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.setAttribute("utente", null);
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String showHome(Model m, HttpSession session) {

        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Utente utente = (Utente) session.getAttribute("utente");

        m.addAttribute("libri",iLibroRep.findAll());

        m.addAttribute("preferiti",iLibroRep.findLibriByUtenteLibri(utente.getId()));

        return "home";
    }

    @GetMapping("/profilo")
    public String showProfilo(HttpSession session, Model model){
        if(session.getAttribute("user")==null){
            return "sessionerror";
        }
        Utente utente = (Utente) session.getAttribute("utente");
        model.addAttribute("utente", utente);

        return "profilo";
    }

    @GetMapping("/modificaUtente")
    public String mostraModificaForm(Model model, HttpSession session) {
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
        Utente utente = (Utente) session.getAttribute("utente");

        model.addAttribute("utente", utente);
        return "modificaProfilo";
    }

    @PostMapping("/modificaPostUtente")
    public String postModificaForm(Utente utente, HttpSession session) {
        if(session.getAttribute("user")==null){
            return "sessionerror";
        }
        Utente utente1 = (Utente) session.getAttribute("utente");
        utente1.setNome(utente.getNome());
        utente1.setCognome(utente.getCognome());
        utente1.setUsername(utente.getUsername());
        utente1.setPassword(utente.getPassword());

        iUtenteRep.save(utente1);

        return "redirect:/profilo";
    }

    @GetMapping("/delete")
    public String removeUser(HttpSession session){
        if(session.getAttribute("user")==null){
            return "sessionerror";
        }
        Utente utente = (Utente) session.getAttribute("utente");
        iUtenteRep.delete(utente);

        session.setAttribute("user",null);

        return "redirect:/registrazione";
    }
}
