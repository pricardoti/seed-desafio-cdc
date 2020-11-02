package br.com.pricardoti.casacodigo.core.error;

public class ItemErrorDetail {
    private final String key;
    private final String value;

    public ItemErrorDetail(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
