package app.model;

public enum NameRoles{
    ROLE_USER,ROLE_ADMIN,ROLE_SUPPORT;

    public String getPresentationName(){
        if(name().equals("ROLE_USER")){return "Пользователь";}
        else if(name().equals("ROLE_ADMIN")){return "Администратор";}
        else if(name().equals("ROLE_SUPPORT")){return "Поддержка";}
        return null;
    }
}
