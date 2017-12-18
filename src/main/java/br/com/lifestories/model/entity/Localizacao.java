package br.com.lifestories.model.entity;

import br.com.lifestories.model.base.BaseEntity;
import java.math.BigDecimal;

/**
 *
 * @author Marcelo
 */
public class Localizacao extends BaseEntity{
    private BigDecimal latitude;
    private BigDecimal longitude;
    
    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
