package uz.cluster.dao;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

public class FacturaData {
    @JsonProperty("Version")
    private int version;

    @JsonProperty("FacturaType")
    private int facturaType;

    @JsonProperty("SingleSidedType")
    private int singleSidedType;

    @JsonProperty("IsPrimaryDocument")
    private boolean isPrimaryDocument;

    @JsonProperty("IncomeType")
    private int incomeType;

    @JsonProperty("RealizationPurpose")
    private int realizationPurpose;

    @JsonProperty("FacturaId")
    private String facturaId;

    @JsonProperty("FacturaProductId")
    private String facturaProductId;

    @JsonProperty("HasEmpowerment")
    private boolean hasEmpowerment;

    @JsonProperty("HasInvestmentObject")
    private boolean hasInvestmentObject;

    @JsonProperty("HasProducts")
    private boolean hasProducts;

    @JsonProperty("LotType")
    private String lotType;

    @JsonProperty("LotNo")
    private String lotNo;

    @JsonProperty("InvestmentObjectId")
    private String investmentObjectId;

    @JsonProperty("InvestmentObjectName")
    private String investmentObjectName;

    @JsonProperty("CurrentStateId")
    private String currentStateId;

    @JsonProperty("CurrentStateName")
    private String currentStateName;

    @JsonProperty("CurrentStateClass")
    private String currentStateClass;

    @JsonProperty("Notes")
    private String notes;

    @JsonProperty("ContractId")
    private String contractId;

    @JsonProperty("WaybillId")
    private String waybillId;

    @JsonProperty("WaybillIds")
    private List<String> waybillIds;

    @JsonProperty("WaybillLocalIds")
    private List<String> waybillLocalIds;

    @JsonProperty("FacturaNo")
    private String facturaNo;

    @JsonProperty("FacturaDate")
    private String facturaDate;

    @JsonProperty("ContractNo")
    private String contractNo;

    @JsonProperty("ContractDate")
    private String contractDate;

    @JsonProperty("AgentFacturaId")
    private String agentFacturaId;

    @JsonProperty("EmpowermentNo")
    private String empowermentNo;

    @JsonProperty("EmpowermentDateOfIssue")
    private String empowermentDateOfIssue;

    @JsonProperty("AgentTin")
    private String agentTin;

    @JsonProperty("AgentPinfl")
    private String agentPinfl;

    @JsonProperty("AgentFio")
    private String agentFio;

    @JsonProperty("OldFacturaId")
    private String oldFacturaId;

    @JsonProperty("OldFacturaNo")
    private String oldFacturaNo;

    @JsonProperty("OldFacturaDate")
    private String oldFacturaDate;

    @JsonProperty("ItemReleasedTin")
    private String itemReleasedTin;

    @JsonProperty("ItemReleasedPinfl")
    private String itemReleasedPinfl;

    @JsonProperty("ItemReleasedFio")
    private String itemReleasedFio;

    @JsonProperty("SellerTin")
    private String sellerTin;

    @JsonProperty("SellerName")
    private String sellerName;

    @JsonProperty("SellerAccount")
    private String sellerAccount;

    @JsonProperty("SellerBankId")
    private String sellerBankId;

    @JsonProperty("SellerBankName")
    private String sellerBankName;

    @JsonProperty("SellerAddress")
    private String sellerAddress;

    @JsonProperty("SellerMobile")
    private String sellerMobile;

    @JsonProperty("SellerPhone")
    private String sellerPhone;

    @JsonProperty("SellerOked")
    private String sellerOked;

    @JsonProperty("SellerRegionId")
    private String sellerRegionId;

    @JsonProperty("SellerRegionName")
    private String sellerRegionName;

    @JsonProperty("SellerDistrictId")
    private String sellerDistrictId;

    @JsonProperty("SellerDistrictName")
    private String sellerDistrictName;

    @JsonProperty("SellerDirector")
    private String sellerDirector;

    @JsonProperty("SellerAccountant")
    private String sellerAccountant;

    @JsonProperty("SellerVatRegCode")
    private String sellerVatRegCode;

    @JsonProperty("SellerBranchCode")
    private String sellerBranchCode;

    @JsonProperty("SellerBranchName")
    private String sellerBranchName;

    @JsonProperty("SellerCategory")
    private String sellerCategory;

    @JsonProperty("SellerTaxPayerTypeCode")
    private String sellerTaxPayerTypeCode;

    @JsonProperty("SellerTaxPayerTypeName")
    private String sellerTaxPayerTypeName;

    @JsonProperty("BuyerTin")
    private String buyerTin;

    @JsonProperty("BuyerName")
    private String buyerName;

    @JsonProperty("BuyerAccount")
    private String buyerAccount;

    @JsonProperty("BuyerBankId")
    private String buyerBankId;

    @JsonProperty("BuyerBankName")
    private String buyerBankName;

    @JsonProperty("BuyerAddress")
    private String buyerAddress;

    @JsonProperty("BuyerMobile")
    private String buyerMobile;

    @JsonProperty("BuyerPhone")
    private String buyerPhone;

    @JsonProperty("BuyerOked")
    private String buyerOked;

    @JsonProperty("BuyerRegionId")
    private String buyerRegionId;

    @JsonProperty("BuyerRegionName")
    private String buyerRegionName;

    @JsonProperty("BuyerDistrictId")
    private String buyerDistrictId;

    @JsonProperty("BuyerDistrictName")
    private String buyerDistrictName;

    @JsonProperty("BuyerDirector")
    private String buyerDirector;

    @JsonProperty("BuyerAccountant")
    private String buyerAccountant;

    @JsonProperty("BuyerVatRegCode")
    private String buyerVatRegCode;

    @JsonProperty("BuyerBranchCode")
    private String buyerBranchCode;

    @JsonProperty("BuyerBranchName")
    private String buyerBranchName;

    @JsonProperty("BuyerTaxPayerTypeCode")
    private String buyerTaxPayerTypeCode;

    @JsonProperty("BuyerTaxPayerTypeName")
    private String buyerTaxPayerTypeName;

    @JsonProperty("ForeignCompanyName")
    private String foreignCompanyName;

    @JsonProperty("ForeignCompanyCountryId")
    private String foreignCompanyCountryId;

    @JsonProperty("ForeignCompanyAddress")
    private String foreignCompanyAddress;

    @JsonProperty("ForeignCompanyBank")
    private String foreignCompanyBank;

    @JsonProperty("ForeignCompanyAccount")
    private String foreignCompanyAccount;

    @JsonProperty("PayableTotal")
    private String payableTotal;

    @JsonProperty("Created")
    private String created;

    @JsonProperty("Updated")
    private String updated;

    @JsonProperty("ProductInfo")
    private String productInfo;

    @JsonProperty("Buttons")
    private Buttons buttons;

    @JsonProperty("States")
    private List<State> states;

    private List<Invoice> invoices;
    // Constructor, getters, and setters
}

