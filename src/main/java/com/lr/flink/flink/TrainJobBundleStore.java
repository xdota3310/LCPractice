package com.lr.flink.flink;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;

/**
 * @author chengtao
 * @date 24/11/2020 - 15:39
 */
public class TrainJobBundleStore {

    private static final Logger logger = LoggerFactory.getLogger(TrainJobBundleStore.class);
    private final String bundlePathDir;
    private final ObjectMapper objectMapper;

    public TrainJobBundleStore(String bundlePathDir) {
        this.bundlePathDir = bundlePathDir;
        this.objectMapper = new ObjectMapper();
    }

    /**
     * store the train job bundle
     * @param bundle
     * @return bundle config absolute path
     */
    public String store(TrainJobBundle bundle) {
        File filePath = new File(bundlePathDir);
        File fileLocation = filePath.getParentFile();
        String bundleFileName = filePath.getName();
        File tmpFile;
        try {
            tmpFile = File.createTempFile(bundleFileName, null, fileLocation);
        } catch (IOException iox) {
            logger.error("error create temp file {}", filePath.getAbsolutePath(), iox);
            throw new RuntimeException(iox);
        }

        try (
                FileOutputStream fos = new FileOutputStream(tmpFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter writer = new BufferedWriter(osw)) {
            writer.write(objectMapper.writeValueAsString(bundle));
        } catch (IOException e) {
            throw new RuntimeException("store bundle config file error ", e);
        }
        return tmpFile.getAbsolutePath();
    }

    public TrainJobBundle load(String absBundlePath) {
        return load(absBundlePath, true);
    }

    public TrainJobBundle load(String absBundlePath, boolean removeAfterLoad) {
        File bundleFile = new File(absBundlePath);
        if (!bundleFile.exists()) {
            throw new RuntimeException("bundle file not found " + absBundlePath);
        }
        try {
            TrainJobBundle trainJobBundle =
                    objectMapper.readValue(bundleFile, TrainJobBundle.class);
            if (removeAfterLoad) {
                remove(absBundlePath);
            }
            return trainJobBundle;
        } catch (IOException iox) {
            throw new RuntimeException("read bundle file error " + absBundlePath, iox);
        }
    }

    private void remove(String absBundlePath) {
        try {
            Files.deleteIfExists(new File(absBundlePath).toPath());
            logger.info("remove {}", absBundlePath);
        } catch (IOException iox) {
            throw new RuntimeException("remove bundle file error " + absBundlePath, iox);
        }
    }

}
