package com.stackfusion.onbon.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 *  "fastag_data":"[{
 *                         "epc_id":        "34161FA820328972163F19C0",
 *                         "rfid":        "34161FA820328972163F19C0",
 *                         "t_id":        "E200680A0000400A97921CF2",
 *                         "user_memory":        "5858585858585858585858580400F176617BAB2F3FD87AE0900E60F343ACFEC60EBF6DF6B17EB89063B1D914A383000000000000000000000000000000000000",
 *                         "tag_signature_data_hex":        "F176617BAB2F3FD87AE0900E60F343ACFEC60EBF6DF6B17EB89063B1D914A383",
 *                         "tag_v_class":        "0400",
 *                         "rfu":        "000000000000000000000000000000000000",
 *                         "tag_vrn":        "585858585858585858585858",
 *                         "issuer_bank_id":        607417,
 *                         "vehicle_number":        "UP16AZ0001",
 *                         "chassis_number":        "",
 *                         "status_code":        205,
 *                         "anpr":        null,
 *                         "anpr_confidence":        null,
 *                         "success_flag":        true,
 *                         "TIMESTAMP":        1624084222
 *                 }
 * */
/**
 *  "fastag_data":"[{
 *                         "epc_id":        "34161FA820328972163F19C0",
 *                         "rfid":        "34161FA820328972163F19C0",
 *                         "t_id":        "E200680A0000400A97921CF2",
 *                         "user_memory":        "5858585858585858585858580400F176617BAB2F3FD87AE0900E60F343ACFEC60EBF6DF6B17EB89063B1D914A383000000000000000000000000000000000000",
 *                         "tag_signature_data_hex":        "F176617BAB2F3FD87AE0900E60F343ACFEC60EBF6DF6B17EB89063B1D914A383",
 *                         "tag_v_class":        "0400",
 *                         "rfu":        "000000000000000000000000000000000000",
 *                         "tag_vrn":        "585858585858585858585858",
 *                         "issuer_bank_id":        607417,
 *                         "vehicle_number":        "UP16AZ0001",
 *                         "chassis_number":        "",
 *                         "status_code":        205,
 *                         "anpr":        null,
 *                         "anpr_confidence":        null,
 *                         "success_flag":        true,
 *                         "TIMESTAMP":        1624084222
 *                 }
 * */
public class TAGDetails {
    @SerializedName("epc_id")
    @Expose
    private String epcId;
    @SerializedName("rfid")
    @Expose
    private String rfid;
    @SerializedName("t_id")
    @Expose
    private String tId;
    @SerializedName("user_memory")
    @Expose
    private String userMemory;
    @SerializedName("tag_signature_data_hex")
    @Expose
    private String tagSignatureDataHex;
    @SerializedName("tag_v_class")
    @Expose
    private String tagVClass;
    @SerializedName("rfu")
    @Expose
    private String rfu;
    @SerializedName("tag_vrn")
    @Expose
    private String tagVrn;
    @SerializedName("issuer_bank_id")
    @Expose
    private Integer issuerBankId;
    @SerializedName("vehicle_number")
    @Expose
    private String vehicleNumber;
    @SerializedName("chassis_number")
    @Expose
    private String chassisNumber;
    @SerializedName("status_code")
    @Expose
    private Integer statusCode;
    @SerializedName("anpr")
    @Expose
    private Object anpr;
    @SerializedName("anpr_confidence")
    @Expose
    private Object anprConfidence;
    @SerializedName("success_flag")
    @Expose
    private Boolean successFlag;
    @SerializedName("TIMESTAMP")
    @Expose
    private Long timestamp;
    @SerializedName("L1_high")
    @Expose
    private String L1_high;
    @SerializedName("L2_high")
    @Expose
    private String L2_high;

    public String getL1_high() {
        return L1_high;
    }

    public void setL1_high(String l1_high) {
        L1_high = l1_high;
    }

    public String getL2_high() {
        return L2_high;
    }

    public void setL2_high(String l2_high) {
        L2_high = l2_high;
    }

    public String getEpcId() {
        return epcId;
    }

    public void setEpcId(String epcId) {
        this.epcId = epcId;
    }

    public String getRfid() {
        return rfid;
    }

    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    public String gettId() {
        return tId;
    }

    public void settId(String tId) {
        this.tId = tId;
    }

    public String getUserMemory() {
        return userMemory;
    }

    public void setUserMemory(String userMemory) {
        this.userMemory = userMemory;
    }

    public String getTagSignatureDataHex() {
        return tagSignatureDataHex;
    }

    public void setTagSignatureDataHex(String tagSignatureDataHex) {
        this.tagSignatureDataHex = tagSignatureDataHex;
    }

    public String getTagVClass() {
        return tagVClass;
    }

    public void setTagVClass(String tagVClass) {
        this.tagVClass = tagVClass;
    }

    public String getRfu() {
        return rfu;
    }

    public void setRfu(String rfu) {
        this.rfu = rfu;
    }

    public String getTagVrn() {
        return tagVrn;
    }

    public void setTagVrn(String tagVrn) {
        this.tagVrn = tagVrn;
    }

    public Integer getIssuerBankId() {
        return issuerBankId;
    }

    public void setIssuerBankId(Integer issuerBankId) {
        this.issuerBankId = issuerBankId;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public String getChassisNumber() {
        return chassisNumber;
    }

    public void setChassisNumber(String chassisNumber) {
        this.chassisNumber = chassisNumber;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public Object getAnpr() {
        return anpr;
    }

    public void setAnpr(Object anpr) {
        this.anpr = anpr;
    }

    public Object getAnprConfidence() {
        return anprConfidence;
    }

    public void setAnprConfidence(Object anprConfidence) {
        this.anprConfidence = anprConfidence;
    }

    public Boolean getSuccessFlag() {
        return successFlag;
    }

    public void setSuccessFlag(Boolean successFlag) {
        this.successFlag = successFlag;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public enum TAG_STATUS {
        ACTIVE("Active"),
        BLACKLIST("Blacklist"),
        LOW_BALANCE("Low Balance"),
        UNREGISTERED("UnRegistered"),
        INVALID("Invalid"),
        VALID("valid"),
        EXEMPTED("Exempted"),
        API_FAILURE("Failed to get Response");
        String tagType;

        TAG_STATUS(String tagType) {
            this.tagType = tagType;
        }

        public String getString() {
            return tagType;
        }
    }



    /***this is for the STAMP **/
    public static String getStatusFromCode(int code) {
        TagErrorCode errorCode = TagErrorCode.getTagErrorCode(code);
        String status = "";
        switch (errorCode) {
            //RFID reader fastag api
            case EPC_OK:
            case SIGNATURE_INVALID_KEY_NOTFOUND:
            case SIGNATURE_INVALID_FIELDS_VALID:
                status = TAG_STATUS.VALID.getString();

                break;
            case EPC_ERR_HEADER:
            case EPC_ERR_FILTER:
            case EPC_ERR_PARTITION:
            case EPC_ERR_COUNTRY:
            case EPC_ERR_IMHCL:
            case EPC_ERR_CCHID:
            case EPC_ERR_ISSUER:
            case SIGNATURE_VALID_FIELDS_INVALID:
//            case SIGNATURE_INVALID_FIELDS_VALID: /** This is commented as some tags dont have correct keys **/
                //case SIGNATURE_INVALID_KEY_NOTFOUND:
                status = TAG_STATUS.INVALID.getString();
                break;

            //Backend
            case ACTIVE:
            case ACTIVE_SIGNATURE_VALID:
            case ACTIVE_SIGNATURE_INVALID:
                status = TAG_STATUS.ACTIVE.getString();
                break;
            case BLACKLIST:
                status = TAG_STATUS.BLACKLIST.getString();
                break;
            case EXEMPTED:
                status = TAG_STATUS.EXEMPTED.getString();
                break;
            case LOW_BALANCE:
                status = TAG_STATUS.LOW_BALANCE.getString();
                break;
            case UNREGISTERED:
                status = TAG_STATUS.UNREGISTERED.getString();
                break;
            case API_FAILURE:
                status = TAG_STATUS.API_FAILURE.getString();
                break;


            //SitePass websocket client
//            case EXCPT_NONE:
//                status = TAG_STATUS.VALID.getString();
//                break;
//            case EXCPT_BLACKLISTED:
//                status = TAG_STATUS.BLACKLIST.getString();
//                break;
//            case EXCPT_NOT_VERIFIED:
//                status = TAG_STATUS.INVALID.getString();
//                break;
//            case EXCPT_NOT_ACTIVE:
//                status = TAG_STATUS.INVALID.getString();
//                break;
//            case EXCPT_MASTER_INSIDE:
//                status = TAG_STATUS.INVALID.getString();
//                break;
//            case EXCPT_CLONE_INSIDE:
//                status = TAG_STATUS.INVALID.getString();
//                break;
//            case EXCPT_ALREADY_INSIDE:
//                status = TAG_STATUS.INVALID.getString();
//                break;
//            case EXCPT_ENTRY_NOT_REGISTERED:
//                status = TAG_STATUS.INVALID.getString();
//                break;
//            case EXCPT_NOT_ALLOWED:
//                status = TAG_STATUS.INVALID.getString();
//                break;

            default:
                status = TAG_STATUS.INVALID.getString();
                break;

        }
        return status;
    }

    private static String getMessageFromErrorCode(int code) {
        TagErrorCode errorCode = TagErrorCode.getTagErrorCode(code);
        String errorMessage = "";
        switch (errorCode) {
            //RFID reader fastag api
            case EPC_OK:
            case SIGNATURE_INVALID_KEY_NOTFOUND:
            case SIGNATURE_INVALID_FIELDS_VALID:
                errorMessage = "";
                break;
            case EPC_ERR_HEADER:
                errorMessage = "Incorrect header";
                break;
            case EPC_ERR_FILTER:
                errorMessage = "Incorrect filter";
                break;
            case EPC_ERR_PARTITION:
                errorMessage = "Incorrect partition";
                break;
            case EPC_ERR_COUNTRY:
                errorMessage = "Incorrect country code";
                break;
            case EPC_ERR_IMHCL:
                errorMessage = "Incorrect IMHCL Prefix";
                break;
            case EPC_ERR_CCHID:
                errorMessage = "Incorrect CCH ID prefix";
                break;
            case EPC_ERR_ISSUER:
                errorMessage = "Incorrect issuer ID";
                break;
            case SIGNATURE_VALID_FIELDS_INVALID:
                errorMessage = "Incorrect Fields";
                break;
//            case SIGNATURE_INVALID_FIELDS_VALID:
//                errorMessage = "Signature validation failed";
//                break;
//            case SIGNATURE_INVALID_KEY_NOTFOUND:
//                errorMessage = "No-Key/Signature validation failed";
//                break;

            //Backend
            case ACTIVE:
                errorMessage = "";
                break;
            case BLACKLIST:
                errorMessage = "Insufficient Tag Balance / Contact Your Bank";
                break;
            case EXEMPTED:
                errorMessage = "Insufficient Tag Balance / Contact Your Bank";
                break;
            case LOW_BALANCE:
                errorMessage = "Insufficient Tag Balance / Contact Your Bank";
                break;
            case UNREGISTERED:
                errorMessage = "Tag Invalid / Contact your Bank";
                break;
            case API_FAILURE:
                errorMessage = "Failed to get Status";
                break;


            //SitePass websocket client

//            case EXCPT_NONE:
//                errorMessage = "";
//                break;
//            case EXCPT_BLACKLISTED:
//                errorMessage = "Blacklist vehicle";
//                break;
//            case EXCPT_NOT_VERIFIED:
//                errorMessage = "Not verified";
//                break;
//            case EXCPT_NOT_ACTIVE:
//                errorMessage = "Iinactive";
//                break;
//            case EXCPT_MASTER_INSIDE:
//                errorMessage = "Clone Master is inside";
//                break;
//            case EXCPT_CLONE_INSIDE:
//                errorMessage = "Clone is inside";
//                break;
//            case EXCPT_ALREADY_INSIDE:
//                errorMessage = "Is inside";
//                break;
//            case EXCPT_ENTRY_NOT_REGISTERED:
//                errorMessage = "Entry not detected";
//                break;
//            case EXCPT_NOT_ALLOWED:
//                errorMessage = "Not allowed on gate";
//                break;
            case IGNORE:
                errorMessage = "Ignore";
                break;

            default:
                errorMessage = "Unknown Error : " + code;

        }
        return errorMessage;
    }

    public enum TagErrorCode {
        //BackEnd Fastag check
        ACTIVE(201),
        ACTIVE_SIGNATURE_VALID(202),
        ACTIVE_SIGNATURE_INVALID(203),
        BLACKLIST(204),
        EXEMPTED(2),
        LOW_BALANCE(3),
        UNREGISTERED(205),
        API_FAILURE(-30),

        //Hardware Fastag Check
        EPC_OK(-10),//EPC_OK means FASTAG
        EPC_ERR_HEADER(100),
        EPC_ERR_FILTER(101),
        EPC_ERR_PARTITION(102),
        EPC_ERR_COUNTRY(103),
        EPC_ERR_IMHCL(104),
        EPC_ERR_CCHID(105),
        EPC_ERR_ISSUER(106),
        SIGNATURE_VALID_FIELDS_INVALID(107),
        SIGNATURE_INVALID_FIELDS_VALID(108),
        SIGNATURE_INVALID_KEY_NOTFOUND(109),

        //SitePass Tag check
//        EXCPT_NONE(-20),//Sitepass ok
//        EXCPT_BLACKLISTED(200),
//        EXCPT_NOT_VERIFIED(201),
//        EXCPT_NOT_ACTIVE(202),
//        EXCPT_MASTER_INSIDE(203),
//        EXCPT_CLONE_INSIDE(204),
//        EXCPT_ALREADY_INSIDE(205),
//        EXCPT_ENTRY_NOT_REGISTERED(206),
//        EXCPT_NOT_ALLOWED(207),
        IGNORE(209);


        int code;

        TagErrorCode(int code) {
            this.code = code;
        }


        public int getCode() {
            return code;
        }

        public static String getErrorMessage(int code) {
            return getMessageFromErrorCode(code);
        }

        public static String getTagStatus(int code) {
            return getStatusFromCode(code);
        }

        public static TagErrorCode getTagErrorCode(int code){
            switch (code) {
                //RFID reader fastag api
                case -10:
                    return EPC_OK;

                case 100:
                    return EPC_ERR_HEADER;
                case 101:
                    return EPC_ERR_FILTER;
                case 102:
                    return EPC_ERR_PARTITION;
                case 103:
                    return EPC_ERR_COUNTRY;
                case 104:
                    return EPC_ERR_IMHCL;
                case 105:
                    return EPC_ERR_CCHID;
                case 106:
                    return EPC_ERR_ISSUER;
                case 207:
                    return SIGNATURE_VALID_FIELDS_INVALID;
                case 203:
                    return SIGNATURE_INVALID_FIELDS_VALID;
                case 208:
                    return SIGNATURE_INVALID_KEY_NOTFOUND;
                //Backendcase EPC_ERR_PARTITION:


                case 202:
                    return ACTIVE;
                case 204:
                    return BLACKLIST;
                case 2:
                    return EXEMPTED;
                case 3:
                    return LOW_BALANCE;
                case 99:
                    return UNREGISTERED;
                case -30:
                    return API_FAILURE;

//               case -20:
//                    return EXCPT_NONE;
//
//                case 200:
//                    return EXCPT_BLACKLISTED;
//                case 201:
//                    return EXCPT_NOT_VERIFIED;
//                case 202:
//                    return EXCPT_NOT_ACTIVE;
//                case 203:
//                    return EXCPT_MASTER_INSIDE;
//                case 204:
//                    return EXCPT_CLONE_INSIDE;
//                case 205:
//                    return EXCPT_ALREADY_INSIDE;
//                case 206:
//                    return EXCPT_ENTRY_NOT_REGISTERED;
//                case 207:
//                    return EXCPT_NOT_ALLOWED;
                case 209:
                    return IGNORE;
                default:

                    try {
                        throw new RuntimeException("Unknown code found = " + code);
                    } catch (RuntimeException e) {
//                        MiscUtilities.handleSilentException(e);
                    }
                    return IGNORE;
            }
        }

        //TODO: Deepak untested
        public static boolean isValidFastag(TagErrorCode errorCode) {
            if (errorCode == TagErrorCode.EPC_OK || errorCode == TagErrorCode.SIGNATURE_INVALID_KEY_NOTFOUND) {
                return true;
            }
            return false;
        }

        public static boolean isValidFastag(int errorCode) {
            TagErrorCode errorCode1 = TagErrorCode.getTagErrorCode(errorCode);
            if (errorCode1 == TagErrorCode.EPC_OK || errorCode1 == TagErrorCode.SIGNATURE_INVALID_KEY_NOTFOUND) {
                return true;
            }
            return false;
        }
    }

    //TODO: have the
    public enum ISSUER_BANK {
        Airtel_Payments_Bank(607569),
        Axis_Bank_Ltd(607529),
        Bank_Of_Baroda(652151),
        Canara_Bank(607422),
        City_Union_Bank_Limited(607949),
        Equitas_Small_Finance_Bank(652402),
        Federal_Bank(607363),
        Fino_Payment_Bank(608001),
        HDFC_Bank(607318),
        ICICI_Bank(607417),
        IDFC_Bank(608116),
        IndusInd_Bank(608266),
        IndusInd__Bank(607189),
        Karur_Vysya_Bank(607100),
        Kotak_Bank(607469),
        Kotak_Mahindra_Bank(607421),
        Nagpur_Nagarik_Sahakari_Bank_Ltd(607799),
        PAYTM_Bank(608032),
        Punjab_Maharashtra_Bank(607057),
        Punjab_National_Bank(607859),
        Saraswat_Bank(652150),
        South_Indian_Bank(652203),
        State_Bank_of_India(606986),
        Syndicate_Bank(607787),
        Union_Bank_of_India(606992),
        Yes_Bank(652210),
        NHAI_Bank_Neutral_FASTag(222222),
        NHAI_Bank_Neutral__FASTag(222223);


        int issuerBankCode;
        String name;

        ISSUER_BANK(int issuerBankCode) {
            this.issuerBankCode = issuerBankCode;
        }

        public int getIssuerBankCode() {
            return issuerBankCode;
        }

        public String toString() {
            return name;
        }

        public void setIssuerBankCode(int issuerBankCode) {
            this.issuerBankCode = issuerBankCode;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }

    public enum TAG_TYPE {
        SITE_PASS("Site Pass"),
        FASTAG("Fastag"),
        GENERIC("Generic");
        String tagType;

        TAG_TYPE(String tagType) {
            this.tagType = tagType;
        }

        public String getString() {
            return tagType;
        }
    }

}
