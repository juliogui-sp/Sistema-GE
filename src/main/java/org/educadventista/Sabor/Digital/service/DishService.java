package org.educadventista.Sabor.Digital.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.educadventista.Sabor.Digital.model.Dish;
import org.educadventista.Sabor.Digital.repository.DishRepository;

import java.io.IOException;
import java.util.List;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private ImageDownloadService imageDownloadService;

    public List<Dish> findAll() {
        return dishRepository.findAll();
    }

    public Dish findById(Long id) {
        return dishRepository.findById(id).orElseThrow(() -> new RuntimeException("Dish not found"));
    }

    public Dish create(Dish dish) {
        try {
            String storedImagePath = imageDownloadService.downloadImage(dish.getUrlImage());
            dish.setUrlImage(storedImagePath);
        } catch (IOException e) {
            e.printStackTrace();
            // Lidar com exceções conforme necessário
        }
        return dishRepository.save(dish);
    }

    public Dish update(Long id, Dish dishDetails) {
        Dish dish = findById(id);
        dish.setName(dishDetails.getName());
        dish.setDescription(dishDetails.getDescription());
        dish.setPrice(dishDetails.getPrice());

        // Atualize a imagem somente se a URL da imagem tiver mudado
        if (!dish.getUrlImage().equals(dishDetails.getUrlImage())) {
            try {
                String storedImagePath = imageDownloadService.downloadImage(dishDetails.getUrlImage());
                dish.setUrlImage(storedImagePath);
            } catch (IOException e) {
                e.printStackTrace();
                // Lidar com exceções conforme necessário
            }
        }

        return dishRepository.save(dish);
    }

    public void delete(Long id) {
        Dish dish = findById(id);
        dishRepository.delete(dish);
    }
}