package org.opencompare.api.java.impl.io;

import org.opencompare.api.java.PCM;
import org.opencompare.api.java.PCMContainer;
import org.opencompare.api.java.PCMMetadata;
import org.opencompare.api.java.impl.PCMImpl;
import org.opencompare.api.java.io.PCMExporter;
import org.kevoree.modeling.api.json.JSONModelSerializer;
import pcm.factory.DefaultPcmFactory;

import java.util.Base64;

/**
 * Created by gbecan on 13/10/14.
 */
public class KMFJSONExporter implements PCMExporter {

    private boolean base64Encoding;

    public KMFJSONExporter() {
        this(true);
    }

    public KMFJSONExporter(boolean base64Encoding) {
        this.base64Encoding = base64Encoding;
    }

    private DefaultPcmFactory factory = new DefaultPcmFactory();
    private JSONModelSerializer serializer = factory.createJSONSerializer();
    private PCMBase64Encoder encoder = new PCMBase64Encoder();

    @Override
    public String export(PCMContainer container) {
        return toJson(container.getPcm());
    }

    public String toJson(PCM pcm) {
        String json = "";

        if (pcm instanceof PCMImpl) {

            // Convert all strings to base64 to avoid encoding problems
            if (base64Encoding) {
                encoder.encode(pcm);
            }

            // Serialize PCM
            pcm.PCM kPcm = ((PCMImpl) pcm).getKpcm();
            json = serializer.serialize(kPcm);

            // Decode PCM
            if (base64Encoding) {
                encoder.decode(pcm);
            }
        }

        return json;
    }

}
