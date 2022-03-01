package hello.core.order;

public class Order {

    private Long memberId;
    private String itemName;
    private int itemPnrice;
    private int discountPrice;


    public Order(Long memberId, String itemName, int itemPnrice, int discountPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPnrice = itemPnrice;
        this.discountPrice = discountPrice;
    }

    // 비즈니스(계산) 로직
    public int calculatePrice() {
        return itemPnrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemPnrice() {
        return itemPnrice;
    }

    public void setItemPnrice(int itemPnrice) {
        this.itemPnrice = itemPnrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\'' +
                ", itemPnrice=" + itemPnrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
