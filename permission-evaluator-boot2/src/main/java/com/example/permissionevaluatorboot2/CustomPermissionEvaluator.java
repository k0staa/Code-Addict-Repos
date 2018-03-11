package com.example.permissionevaluatorboot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

@Component
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Autowired
    private ConfidentialDocumentsRepository confidentialDocumentsRepository;

    @Override
    public boolean hasPermission(Authentication auth, Object targetDomainObject, Object permission) {
        // I will not implement this method just because I don't needed in this demo.
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean hasPermission(Authentication auth, Serializable targetId, String targetType, Object permission) {
        if ((auth == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        ConfidentialDocument confidentialDocument =
                confidentialDocumentsRepository.findOne((Integer) targetId);
        String documentOwner = confidentialDocument.getOwner();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String principalLogin = userDetails.getUsername();
        // if current user is owner of document permission is granted
        if (Objects.equals(documentOwner, principalLogin)) {
            return true;
        }
        return false;
    }

}
