package stoktakipapp;

/**
 *
 * @author mehme
 */
public class Product {
    
    private int product_id;
    private String stk_kod;
    private String urun_ad;
    private int urun_kdv;
    private float urun_fiyat;
    private int stok_durum;

    public Product(int product_id, String stk_kod, String urun_ad, int urun_kdv, float urun_fiyat, int stok_durum) {
        this.product_id = product_id;
        this.stk_kod = stk_kod;
        this.urun_ad = urun_ad;
        this.urun_kdv = urun_kdv;
        this.urun_fiyat = urun_fiyat;
        this.stok_durum = stok_durum;
    }

    public int getProductId() {
        return product_id;
    }

    public void setId(int product_id) {
        this.product_id = product_id;
    }

    public String getStk_kod() {
        return stk_kod;
    }

    public void setStk_kod(String stk_kod) {
        this.stk_kod = stk_kod;
    }

    public String getUrun_ad() {
        return urun_ad;
    }

    public void setUrun_ad(String urun_ad) {
        this.urun_ad = urun_ad;
    }

    public int getUrun_kdv() {
        return urun_kdv;
    }

    public void setUrun_kdv(int urun_kdv) {
        this.urun_kdv = urun_kdv;
    }

    public float getUrun_fiyat() {
        return urun_fiyat;
    }

    public void setUrun_fiyat(float urun_fiyat) {
        this.urun_fiyat = urun_fiyat;
    }

    public int getStok_durum() {
        return stok_durum;
    }

    public void setStok_durum(int stok_durum) {
        this.stok_durum = stok_durum;
    }
    
    
    
}
