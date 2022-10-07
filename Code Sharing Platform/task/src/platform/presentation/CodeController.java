package platform.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.businesslayer.Code;
import platform.businesslayer.CodeService;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;


@Controller
public class CodeController {

    @Autowired
    CodeService codeService;

    @GetMapping(value = "/api/code/{id}", produces = "application/json")
    public @ResponseBody Code returnCodeJson(@PathVariable String id) {

        return updatedCode(id);
    }

    @GetMapping("/code/{id}")
    public String returnCodeHTML(@PathVariable String id, Model model) {
        Code code = updatedCode(id);
        model.addAttribute("code", code);

        return "code";
    }

    @PostMapping(value = "/api/code/new", produces = "application/json")
    public @ResponseBody Map<String, String> setCodeJson(@RequestBody Code inputCode) {
        UUID uuid = UUID.randomUUID();

        Code codeToSave = new Code(uuid.toString(), inputCode.getCode(),
                LocalDateTime.now(), inputCode.getTime(), inputCode.getViews());

        if (codeToSave.getTime() > 0) {
            codeToSave.setTimeRestrictions(true);
        }

        if (codeToSave.getViews() > 0) {
            codeToSave.setViewsRestrictions(true);
        }

        Code code = codeService.save(codeToSave);

        Map<String, String> map = new HashMap<>();
        map.put("id", code.getId());
        return map;
    }

    @GetMapping("/code/new")
    public String setCodeHTML() {
        return "form";
    }

    @GetMapping(value = "/api/code/latest", produces = "application/json")
    public @ResponseBody List<Code> returnLatestJson() {
        return getLatestCodes();
    }

    @GetMapping("/code/latest")
    public String returnLatestHTML(Model model) {
        model.addAttribute("codes", getLatestCodes());
        return "codeLatest";
    }

    private Code updatedCode(String id) {
        if (codeService.findCodeById(id) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Code code = codeService.findCodeById(id);

        if (code.isTimeRestrictions()) {
            long seconds = ChronoUnit.SECONDS.between(code.getDateAsLocalDateTime(), LocalDateTime.now());
            code.setTime(code.getTime() - seconds);
        }

        if (code.isViewsRestrictions()) {
            code.setViews(code.getViews() - 1);
        }

        if (code.isTimeRestrictions() && code.getTime() < 0 || code.isViewsRestrictions() && code.getViews() < 0) {
            codeService.deleteById(code.getId());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        codeService.save(code);

        return code;
    }

    private List<Code> getLatestCodes() {
        List<Code> allCodes = codeService.findAll();
        List<Code> lastCodes = new ArrayList<>();
        int i = allCodes.size() - 1;
        int counter = 0;
        while (i >= 0 && counter != 10) {
            if (allCodes.get(i) != null && allCodes.get(i).getViews() <= 0 && allCodes.get(i).getTime() <= 0) {

                lastCodes.add(allCodes.get(i));
                counter++;
            }

            i--;
        }


        return lastCodes;
    }
}
