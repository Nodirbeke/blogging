package nod.pro.blogging.service;

import nod.pro.blogging.entity.Theme;
import nod.pro.blogging.repository.ThemeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThemeService {

    private final ThemeRepository repository;

    public ThemeService(ThemeRepository repository) {
        this.repository = repository;
    }

    public Theme getByNameOrCreateNew(String theme) {
        Theme newTheme;
        Optional<Theme> byName = repository.findByName(theme);
        if (byName.isEmpty()) {
            newTheme = repository.save(new Theme(theme));
        } else {
            newTheme = byName.get();
        }
        return newTheme;
    }
}
