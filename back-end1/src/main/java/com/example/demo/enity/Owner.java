package com.example.demo.enity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Owner {
    private Integer id;
    private String accountId;
    private String reputation;
    private String userId;
    private String userType;
    private String profileImage;
    private String displayName;
    private String link;

    public Owner(){

    }

    public Owner(String accountId, String reputation, String userId, String userType, String profileImage,
                 String displayName, String link) {
        this.accountId = accountId;
        this.reputation = reputation;
        this.userId = userId;
        this.userType = userType;
        this.profileImage = profileImage;
        this.displayName = displayName;
        this.link = link;
    }

    public Integer getId() {
        return id;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getReputation() {
        return reputation;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserType() {
        return userType;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getLink() {
        return link;
    }
}
