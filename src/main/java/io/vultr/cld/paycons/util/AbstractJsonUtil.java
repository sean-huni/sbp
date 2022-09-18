package io.vultr.cld.paycons.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class AbstractJsonUtil {

    public String toString() {
        final Gson g = new GsonBuilder()
                .disableHtmlEscaping()
                .setPrettyPrinting().create();
        return g.toJson(this);
    }
}
