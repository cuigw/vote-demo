package com.vote.domain;

import java.util.Date;

/**
 * 功能说明: BaseVote 投票基本信息<br>
 * 系统版本: v1.0<br>
 * 开发人员: @author cgw<br>
 * 开发时间: 2017年02月07日<br>
 */
public class BaseVote {
    private Long    vote_id;
    private String  business_id;
    private String  vote_name;
    private String  vote_content;
    private String  vote_sum;
    private String  status;
    private String  img_url;
    private String  vote_type;
    private String  over_data;
    private String  create_by;
    private Date    create_time;
    private String  update_by;
    private String  update_time;

    public Long getVote_id() {
        return vote_id;
    }

    public void setVote_id(Long vote_id) {
        this.vote_id = vote_id;
    }

    public String getBusiness_id() {
        return business_id;
    }

    public void setBusiness_id(String business_id) {
        this.business_id = business_id;
    }

    public String getVote_name() {
        return vote_name;
    }

    public void setVote_name(String vote_name) {
        this.vote_name = vote_name;
    }

    public String getVote_content() {
        return vote_content;
    }

    public void setVote_content(String vote_content) {
        this.vote_content = vote_content;
    }

    public String getVote_sum() {
        return vote_sum;
    }

    public void setVote_sum(String vote_sum) {
        this.vote_sum = vote_sum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public String getVote_type() {
        return vote_type;
    }

    public void setVote_type(String vote_type) {
        this.vote_type = vote_type;
    }

    public String getOver_data() {
        return over_data;
    }

    public void setOver_data(String over_data) {
        this.over_data = over_data;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getUpdate_by() {
        return update_by;
    }

    public void setUpdate_by(String update_by) {
        this.update_by = update_by;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}
