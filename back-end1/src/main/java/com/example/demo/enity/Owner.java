package com.example.demo.enity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty("id")
    public Integer getId() {
        return id;
    }
    @JsonProperty("accountId")
    public String getAccountId() {
        return accountId;
    }
    @JsonProperty("reputation")
    public String getReputation() {
        return reputation;
    }
    @JsonProperty("userId")
    public String getUserId() {
        return userId;
    }
    @JsonProperty("userType")
    public String getUserType() {
        return userType;
    }
    @JsonProperty("profileImage")
    public String getProfileImage() {
        return profileImage;
    }
    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }
    @JsonProperty("link")
    public String getLink() {
        return link;
    }
}
