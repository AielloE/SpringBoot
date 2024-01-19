package com.example.ProgettoLibreria;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class LibroController {
    @Autowired
    ILibroRep iLibroRep;
    @Autowired
    IUtenteRep iUtenteRep;
    @Autowired
    IUtenteLibroRep iutenteLibroRep;



    @GetMapping("/createBook")
    public String createBook(Libro libro, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }
        return "createbook";
    }

    @PostMapping("/postStoreBook")
    public String storeBook(@Valid Libro libro, BindingResult bindingResult, Model model, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        if(bindingResult.hasErrors()){
            return "createbook";
        }
        iLibroRep.save(libro);
        return "redirect:/home";
    }

    @GetMapping("/dettaglio")
    public String dettaglioBook(@RequestParam("libroId") Integer bookId, Model m, HttpSession session){

        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Optional<Libro> dettaglioLibro = iLibroRep.findById(bookId);
        Libro libro = null;

        if (dettaglioLibro.isPresent()) {
            libro = dettaglioLibro.get();
        }


        m.addAttribute("libro",libro);
        return "dettaglio";
    }

    @GetMapping("/modifica")
    public String mostraModificaForm(@RequestParam("libroId") int bookId, Model model, HttpSession session) {
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Optional<Libro> bookOptional =iLibroRep.findById(bookId);

        if (bookOptional.isPresent()) {
            Libro libro = bookOptional.get();
            model.addAttribute("libro", libro);
        }

        return "modificaForm";
    }

    @PostMapping("modificaPost")
    public String mostraModificaPost(@Valid Libro libro,BindingResult bindingResult, HttpSession session) {

        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        if(bindingResult.hasErrors()){
            return "modificaForm";
        }
        Optional<Libro> Optional = iLibroRep.findById(libro.getId());

        if (Optional.isPresent()) {
            Libro libri = Optional.get();
            libri.setTitolo(libro.getTitolo());
            libri.setAutore(libro.getAutore());
            libri.setDescrizione(libro.getDescrizione());
            System.out.printf(libri.toString());
           iLibroRep.save(libro);
        }

        return "redirect:/home";
    }

    @RequestMapping("/preferiti")
    public String bookUser(@RequestParam("libroId") Integer libroId, HttpSession session){
        if(session.getAttribute("utente")==null){
            return "sessionerror";
        }

        Utente utente = (Utente) session.getAttribute("utente");
        Optional<Libro> dettaglioLibro = iLibroRep.findById(libroId);
        Libro libro = null;

        if (dettaglioLibro.isPresent()) {
            libro = dettaglioLibro.get();
        }
        UtenteLibro utenteLibro = new UtenteLibro(utente,libro);
        iutenteLibroRep.save(utenteLibro);
        return "redirect:/home";

    }

    @GetMapping("/remove")
    public String removeBook(@RequestParam("bookId") Integer bookId, HttpSession session){
        if(session.getAttribute("user")==null){
            return "sessionerror";
        }

        Optional<Libro> libroToRemove = iLibroRep.findById(bookId);

        if (libroToRemove.isPresent()) {
            Libro libro = libroToRemove.get();
            iLibroRep.delete(libro);
        }

        return "redirect:/home";
    }

}
