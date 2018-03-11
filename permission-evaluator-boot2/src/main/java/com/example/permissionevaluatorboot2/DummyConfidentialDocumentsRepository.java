package com.example.permissionevaluatorboot2;

import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class DummyConfidentialDocumentsRepository implements ConfidentialDocumentsRepository {
    private static final Map<Integer, ConfidentialDocument> DATA = new HashMap<>();

    static {
        DATA.put(1, new ConfidentialDocument(1, "file1.txt", "user1"));
        DATA.put(2, new ConfidentialDocument(2, "file2.txt", "user1"));
        DATA.put(3, new ConfidentialDocument(3, "file3.txt", "user2"));
    }

    @Override
    public ConfidentialDocument save(ConfidentialDocument document, String userLogin) {
        Integer maxId = DATA
                .entrySet()
                .stream()
                .map(x -> x.getKey())
                .max(Integer::compareTo)
                .orElse(1);
        document.setId(maxId);
        DATA.put(maxId, document);
        return document;
    }

    @Override
    public Collection<ConfidentialDocument> findAll() {
        return DATA.values();
    }

    @Override
    public ConfidentialDocument findOne(Integer documentId) {
        ConfidentialDocument confidentialDocument =
                DATA.get(documentId);
        return confidentialDocument;

    }
}
