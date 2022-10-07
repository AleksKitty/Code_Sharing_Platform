package platform.businesslayer;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import platform.persistance.CodeRepository;

import java.util.List;

@Service
public class CodeService {

    private final CodeRepository codeRepository;

    @Autowired
    public CodeService(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    public Code findCodeById(String id) {
        return codeRepository.findCodeById(id);
    }

    public Code save(Code toSave) {
        return codeRepository.save(toSave);
    }

    public List<Code> findAll() {
        return codeRepository.findAll();
    }

    public void deleteById(String id) {
        codeRepository.deleteById(id);
    }
}
