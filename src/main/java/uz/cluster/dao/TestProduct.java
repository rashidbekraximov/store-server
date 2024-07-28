package uz.cluster.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TestProduct {

    private String name;

    private String catalogcode;

    private String barcode;

    private String catalogname;

    private double count;

    private byte measureid;

    private String packagename;

    private String packagecode;

    private double summa;

    private double deliverysum;

    private double vatrate;

    private double vatsum;

    private double excisesum;

    private double deliverysumwithvat;

}
