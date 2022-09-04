package jpabook.jpashop2.controller;

public class BuildMe {
    private String username;
    private int age;

    BuildMe(String username, int age) {
        this.username = username;
        this.age = age;
    }

    public static BuildMeBuilder builder() {
        return new BuildMeBuilder();
    }

    public static class BuildMeBuilder {
        private String username;
        private int age;

        BuildMeBuilder() {
        }

        public BuildMeBuilder username(String username) {
            this.username = username;
            return this;
        }

        public BuildMeBuilder age(int age) {
            this.age = age;
            return this;
        }

        public BuildMe build() {
            return new BuildMe(this.username, this.age);
        }

        public String toString() {
            return "BuildMe.BuildMeBuilder(username=" + this.username + ", age=" + this.age + ")";
        }
    }
}