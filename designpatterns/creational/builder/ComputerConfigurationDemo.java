package com.designpatterns.creational.builder;

import com.designpatterns.util.LoggerUtil;

class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private String gpu;

    private Computer(ComputerBuilder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.gpu = builder.gpu;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", gpu='" + gpu + '\'' +
                '}';
    }

    public static class ComputerBuilder {
        private String cpu;
        private String ram;
        private String storage;
        private String gpu;

        public ComputerBuilder(String cpu, String ram) {
            this.cpu = cpu;
            this.ram = ram;
        }

        public ComputerBuilder setStorage(String storage) {
            this.storage = storage;
            return this;
        }

        public ComputerBuilder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class ComputerConfigurationDemo {
    public static void main(String[] args) {
        Computer basicComputer = new Computer.ComputerBuilder("Intel i3", "8GB")
                .setStorage("256GB SSD")
                .build();
        LoggerUtil.log("Basic Computer: " + basicComputer);

        Computer gamingComputer = new Computer.ComputerBuilder("Intel i9", "32GB")
                .setStorage("1TB NVMe SSD")
                .setGpu("NVIDIA RTX 3080")
                .build();
        LoggerUtil.log("Gaming Computer: " + gamingComputer);
    }
}