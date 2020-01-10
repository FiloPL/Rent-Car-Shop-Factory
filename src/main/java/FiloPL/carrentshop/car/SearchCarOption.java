package FiloPL.carrentshop.car;

public enum SearchCarOption {
    BY_CAR_MODEL("po modelu"),BY_PLATE_NR("po nr rej."), BY_VIN("po nr VIN"), BY_COLOR("po kolorze"), BY_TYPE("po type"), BY_SEGMENT("po segmencie");

    private String description;

    SearchCarOption(String description){
        this.description=description;
    }

    @Override
    public String toString() {
        return description;
    }

    public String getDescription() {
        return description;
    }
}
