package org.educadventista.Sabor.Digital.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

@Service
public class ImageDownloadService {

    // Use uma propriedade para configurar o diretório de imagens, tornando-o mais flexível
    @Value("${image.upload.dir}")
    private String imageDirectory;

    public String downloadImage(String imageUrl) throws IOException {
        // Verifique se o URL não está vazio ou nulo
        /*if (imageUrl == null || imageUrl.isEmpty()) {
            throw new IllegalArgumentException("A URL da imagem não pode ser vazia.");
        }*/

        // Crie um nome de arquivo único baseado no timestamp
        String fileName = System.currentTimeMillis() + "-" + imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        File file = new File(imageDirectory + fileName);

        // Crie o diretório se não existir
        File directory = new File(imageDirectory);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Baixe e salve o arquivo
        URL url = new URL(imageUrl);
        FileUtils.copyURLToFile(url, file);

        // Retorne o caminho acessível pela aplicação
        return "/images/" + file.getName();
    }
}
