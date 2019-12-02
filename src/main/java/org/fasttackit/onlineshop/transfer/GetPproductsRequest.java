package org.fasttackit.onlineshop.transfer;

public class GetPproductsRequest {

    private String partialName;

    //allowing null by using wrapper class

    private Integer minQuantity;

    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    @Override
    public String toString() {
        return "GetPproductsRequest{" +
                "partialName='" + partialName + '\'' +
                ", minQuantity=" + minQuantity +
                '}';
    }
}
