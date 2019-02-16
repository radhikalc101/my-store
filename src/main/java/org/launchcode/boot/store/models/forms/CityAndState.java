package org.launchcode.boot.store.models.forms;

public class CityAndState {
    private String city;
    private String state;

    public CityAndState(){ }

    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("city:").append(city).append("\t").append("state:").append(state);
        return sb.toString();
    }
}
