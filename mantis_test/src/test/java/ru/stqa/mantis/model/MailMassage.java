package ru.stqa.mantis.model;

public record MailMassage(String from, String content) {
    public MailMassage() {
        this("","");
    }

    public MailMassage withFrom(String from) {
        return new MailMassage(from, this.content);
    }
    public MailMassage withContent(String content) {
        return new MailMassage(this.from, content);
    }
}
