package com.api.negocio.utils;


import lombok.Builder;
import lombok.Data;


import java.io.Serializable;

@Data
@Builder
public class MensajeResponse  implements Serializable {

    private String mnesaje;
    private Object object;

}
