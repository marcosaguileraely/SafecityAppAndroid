package com.cool4code.safecity.safecity;

import com.parse.ParseClassName;
import com.parse.ParseObject;
/**
 * Created by marcosantonioaguilerely on 4/22/14.
 */
@ParseClassName("socialPost")
public class socialPost extends ParseObject{
    public socialPost(){

    }

    public String getDescription(){
        return getString("post_comment");
    }

    public void setDescription(String comment){
        put("post_comment", comment);
    }
}


