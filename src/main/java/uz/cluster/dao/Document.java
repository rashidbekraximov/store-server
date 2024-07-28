package uz.cluster.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    private String doc_id;

    private String name;

    private String doc_date;

    private String contract_number;

    private String contract_date;

    private String partnerCompany;

    private String updated;

    private String created;

    private String type;

    private double total_delivery_sum;

    private double total_vat_sum;

    private double total_delivery_sum_with_vat;

    private byte doc_status;

    private boolean has_vat;

    private Json document_json;

    private List<TestProduct> products;

}
