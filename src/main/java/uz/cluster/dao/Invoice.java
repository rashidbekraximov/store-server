package uz.cluster.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {
    private String invoiceNumber;
    private String description;
    private String productCode;
    private String unit;
    private String quantity;
    private String price;
    private String deliveryPrice;
    private String taxRate;
    private String taxAmount;
    private String totalPrice;
    private String remarks;

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", description='" + description + '\'' +
                ", productCode='" + productCode + '\'' +
                ", unit='" + unit + '\'' +
                ", quantity='" + quantity + '\'' +
                ", price='" + price + '\'' +
                ", deliveryPrice='" + deliveryPrice + '\'' +
                ", taxRate='" + taxRate + '\'' +
                ", taxAmount='" + taxAmount + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
