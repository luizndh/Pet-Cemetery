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
import java.util.concurrent.TimeUnit;

@Service
public class GcsStorageService {

    @Value("${gcs.bucket.name}")
    private String bucketName;

    @Value("${gcs.service.account.key.path}")
    private String serviceAccountKeyPath;

    private Storage storage;

    @PostConstruct
    public void init() {
        try {
            this.storage = StorageOptions.newBuilder()
                    .setCredentials(com.google.auth.oauth2.GoogleCredentials.fromStream(
                            new FileInputStream(serviceAccountKeyPath)))
                    .build()
                    .getService();
        } catch (IOException e) {
            throw new RuntimeException("Falha ao inicializar o GCS Storage Service", e);
        }
    }

    public String uploadFile(MultipartFile file, String enderecoJazigo) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("O arquivo não pode estar vazio.");
        }

        String objectName = "fotos-jazigo/" + enderecoJazigo;

        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId)
                .setContentType(file.getContentType())
                .build();

        storage.createFrom(blobInfo, file.getInputStream());

        return String.format("https://storage.googleapis.com/%s/%s", bucketName, objectName);
    }

    public String gerarUrlImagem(String enderecoJazigo) throws IOException {
        String objectName = "fotos-jazigo/" + enderecoJazigo;
        BlobId blobId = BlobId.of(bucketName, objectName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();

        return storage.signUrl(blobInfo, 5, TimeUnit.MINUTES, Storage.SignUrlOption.withV4Signature())
                .toString();
    }

    public boolean deleteFile(String fileUrl) {
        String objectName = fileUrl.substring(fileUrl.indexOf(bucketName) + bucketName.length() + 1);
        BlobId blobId = BlobId.of(bucketName, objectName);
        return storage.delete(blobId);
    }
}