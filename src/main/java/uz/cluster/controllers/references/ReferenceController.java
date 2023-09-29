package uz.cluster.controllers.references;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.cluster.dao.reference.DefaultReference;
import uz.cluster.dao.reference.SimilarDao;
import uz.cluster.services.reference.DefaultReferenceService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/references/")
@Tag(name = "Oxshash malumotlar ucun", description = "Spravichniy tablitsalar ustida amallar")
@RequiredArgsConstructor
public class ReferenceController {

    private final DefaultReferenceService defaultReferenceService;

    public static Map<String, String> globalReferences = new HashMap<>();

    static {
        globalReferences.put("specialist", "specialist");
        globalReferences.put("work_place", "work_place");
    }

    @Operation(summary = "oddish spravichniylar ro'yxatini olish")
    @GetMapping(value = "/def/{referenceKey}")
    public List<SimilarDao> getAllReferences(@PathVariable(name = "referenceKey") String referenceKey) {
        if (globalReferences.containsKey(referenceKey)) {
            String viewName = globalReferences.get(referenceKey);
            System.out.println(referenceKey + " == " + viewName);
            return defaultReferenceService.getReferenceItems(viewName);
        }
        return null;
    }

    @Operation(summary = "Default Spravichniyni saqlash")
    @PostMapping("/def_references")
    public ResponseEntity<?> save(@RequestBody DefaultReference reference) {
        try {
            defaultReferenceService.save(reference);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }
}
