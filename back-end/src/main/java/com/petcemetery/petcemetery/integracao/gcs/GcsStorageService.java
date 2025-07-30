package com.petcemetery.petcemetery.integracao.gcs;

import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * Serviço para gerenciar o upload e acesso de arquivos no Google Cloud Storage (GCS).
 */
@Service
public class GcsStorageService {

    // Nome do bucket do GCS, injetado do application.properties
    @Value("${gcs.bucket.name}")
    private String bucketName;

    // Caminho para a chave da conta de serviço, injetado do application.properties
    @Value("${gcs.service.account.key.path}")
    private String serviceAccountKeyPath;

    private Storage storage;

    /**
     * Inicializa o cliente do Google Cloud Storage após a construção do bean.
     * Usa a chave da conta de serviço para autenticação.
     */
    @PostConstruct
    public void init() {
        try {
            // Cria o cliente Storage usando as credenciais da conta de serviço
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(
                            new FileInputStream(serviceAccountKeyPath)))
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao inicializar o GCS Storage Service", e);
        }
    }

    /**
     * Realiza o upload de um arquivo MultipartFile para o Google Cloud Storage.
     *
     * @param file O arquivo a ser enviado.
     * @return A URL pública do arquivo armazenado no GCS.
     * @throws IOException Se ocorrer um erro durante a leitura do arquivo ou upload.
     * @throws IllegalArgumentException Se o arquivo estiver vazio.
     */
    public String uploadFile(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo não pode estar vazio.");
        }

        // Gera um nome de arquivo único para evitar colisões
        // Combina um UUID com o nome original do arquivo para manter a extensão
        String originalFilename = file.getOriginalFilename();
        String fileExtension = "";
        if (originalFilename != null && originalFilename.contains(".")) {
            fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        String fileName = UUID.randomUUID() + fileExtension;

        // Define o caminho do objeto dentro do bucket (ex: fotos-jazigos/nome_do_arquivo.jpg)
        String objectName = "fotos-jazigos/" + fileName;

        // Cria o BlobId e BlobInfo para o upload
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        // Realiza o upload do arquivo
        storage.createFrom(blobInfo, file.getInputStream());

        // Retorna a URL pública do arquivo.
        // Certifique-se de que o bucket ou o objeto tenha permissões de leitura pública.
        return String.format("https://storage.googleapis.com/%s/%s", bucketName, objectName);
    }

    /**
     * Exemplo de como deletar um arquivo do GCS (opcional, mas útil).
     *
     * @param fileUrl A URL pública do arquivo a ser deletado.
     * @return true se o arquivo foi deletado com sucesso, false caso contrário.
     */
    public boolean deleteFile(String fileUrl) {
        // Extrai o nome do objeto da URL
        // Ex: "https://storage.googleapis.com/seu-bucket/fotos-jazigos/arquivo.jpg"
        // -> "fotos-jazigos/arquivo.jpg"
        String objectName = fileUrl.substring(fileUrl.indexOf(bucketName) + bucketName.length() + 1);
        BlobId blobId = BlobId.of(bucketName, objectName);
        return storage.delete(blobId);
    }
}