package FiloPL.carrentshop.client;

public enum SearchClientOption {
    BY_NAME("po nazwie"), BY_ADDRESS("po adresie"), BY_TELEPHONE("po numerze telefonu");
    private String description;
    SearchClientOption(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return description;
    }

}
