package uz.cluster.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Json {

    private ProductList productlist;

    private String sellertin;

    private String buyertin;

    private Seller seller;

    private Buyer buyer;

    private Contract contractdoc;

    private Factur facturadoc;

    private String version;

    private FaacturPowerment facturaempowermentdoc;

}
