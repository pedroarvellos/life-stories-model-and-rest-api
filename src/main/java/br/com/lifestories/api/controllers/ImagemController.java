package br.com.lifestories.api.controllers;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/imagens")
public class ImagemController {

    @GetMapping()
    public void getImages(@RequestParam(value = "url", required = true) String url,
            HttpServletResponse response) throws IOException {

        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();

        try {
            InputStream is = new FileInputStream(url);
            BufferedImage image = ImageIO.read(is); //CALL_OR_CREATE_YOUR_IMAGE_OBJECT;
            ImageIO.write(image, "png", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }

        byte[] imgByte = jpegOutputStream.toByteArray();

        response.setHeader("Cache-Control", "no-store");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/png");
        ServletOutputStream responseOutputStream = response.getOutputStream();
        responseOutputStream.write(imgByte);
        responseOutputStream.flush();
        responseOutputStream.close();
    }

    /*@GetMapping
    public ResponseEntity readByUrl(@RequestParam(value = "url", required = true) String url) throws IOException {
        try {
            String nomeImagem = null;

            File arquivoImagem = new File(url);
            if (!arquivoImagem.exists()) {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("A imagem com a url: " + url + ", nao foi encontrada! ");
            } else {
                nomeImagem = arquivoImagem.getName();
                int pos = nomeImagem.indexOf(".");
                //nomeImagem = nomeImagem.substring(0, pos);
            }
            HttpHeaders respHeaders = new HttpHeaders();
            respHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
            respHeaders.setContentLength(arquivoImagem.length());
            respHeaders.setContentDispositionFormData("attachment", nomeImagem);

            InputStreamResource inputStreamImagem = new InputStreamResource(new FileInputStream(arquivoImagem));
            return new ResponseEntity(inputStreamImagem, respHeaders, HttpStatus.OK);
        } catch (Exception ex) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Ocorreu um erro ao carregar a imagem com a url " + url + " : " + ex.getMessage());
        }
    }*/
}
