package com.kafka.streams.dto;

public class Payload {
    private Integer id;
    private String name;
    private String xml_content;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXml_content() {
        return xml_content;
    }

    public void setXml_content(String xml_content) {
        this.xml_content = xml_content;
    }

    @Override
    public String toString() {
        return "Payload{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", xml_content='" + xml_content + '\'' +
                '}';
    }
}
