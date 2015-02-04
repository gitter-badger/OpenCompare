import model.Database;
import org.diverse.pcm.api.java.PCM;
import org.diverse.pcm.api.java.PCMFactory;
import org.diverse.pcm.api.java.exception.MergeConflictException;
import org.diverse.pcm.api.java.impl.PCMFactoryImpl;
import org.diverse.pcm.api.java.impl.io.KMFJSONLoader;
import org.diverse.pcm.formalizer.extractor.CellContentInterpreter;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;

/**
 * Created by gbecan on 12/12/14.
 */
public class LoadPCMs {
    @Test
    public void testLoadWikipediaPCMs() throws FileNotFoundException {
        String path = "../org.diverse.PCM.io.Wikipedia/output/model";
        File dir = new File(path);

        KMFJSONLoader loader = new KMFJSONLoader();
        CellContentInterpreter interpreter = new CellContentInterpreter();

        for (File file : dir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".pcm");
            }
        })) {
            PCM pcm = loader.load(file);
            pcm.setName(pcm.getName().replaceAll("_", " "));

            if (pcm.isValid()) {
                interpreter.interpretCells(pcm);
                Database.INSTANCE.save(pcm);
            }
        }

    }
}
