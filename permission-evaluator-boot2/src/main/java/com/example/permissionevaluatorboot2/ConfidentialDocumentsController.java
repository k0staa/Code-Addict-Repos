package com.example.permissionevaluatorboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;
import java.util.Collection;

@PreAuthorize("isAuthenticated()")
@Controller
public class ConfidentialDocumentsController {
    @Autowired
    private ConfidentialDocumentsRepository repository;

    @PostMapping("/document")
    String create(@RequestBody ConfidentialDocument documentsStream, Principal principal, final Model model) {
        final ConfidentialDocument document =
                this.repository.save(documentsStream, principal.getName());
        model.addAttribute("document", document);
        return "document";
    }

    @GetMapping("/")
    String list(final Model model) {
        final Collection<ConfidentialDocument> documentsStream =
                this.repository.findAll();
        model.addAttribute("documents", documentsStream);
        return "documents";
    }

    @PreAuthorize("hasPermission(#id, 'ConfidentialDocument', 'read')")
    @GetMapping("/document/{id}")
    String findById(@PathVariable Integer id, final Model model) {
        final ConfidentialDocument document =
                this.repository.findOne(id);
        model.addAttribute("document", document);
        return "document";
    }
}