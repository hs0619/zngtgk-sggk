/**
 * Generate time : 2023-03-14 15:08:56
 * Version : 1.0
 */
package com.baosight.sggk.du.hc.domain;

import com.baosight.iplat4j.core.data.DaoEPBase;
import com.baosight.iplat4j.core.ei.EiColumn;
import com.baosight.iplat4j.core.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * THcLink
 *
 */
public class DUHC06 extends DaoEPBase {

    private String id = " ";        /* 主键id*/
    private String urlname = " ";        /* 网站名称*/
    private String url = " ";        /* 网址*/
    private String userid = " ";        /* 用户id*/
    private String username = " ";        /* 用户名称*/

    /**
     * initialize the metadata
     */
    public void initMetaData() {
        EiColumn eiColumn;

        eiColumn = new EiColumn("id");
        eiColumn.setPrimaryKey(true);
        eiColumn.setDescName("主键id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("urlname");
        eiColumn.setDescName("网站名称");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("url");
        eiColumn.setDescName("网址");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("userid");
        eiColumn.setDescName("用户id");
        eiMetadata.addMeta(eiColumn);

        eiColumn = new EiColumn("username");
        eiColumn.setDescName("用户名称");
        eiMetadata.addMeta(eiColumn);


    }

    /**
     * the constructor
     */
    public DUHC06() {
        initMetaData();
    }

    /**
     * get the id - 主键id
     * @return the id
     */
    public String getId() {
        return this.id;
    }

    /**
     * set the id - 主键id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * get the urlname - 网站名称
     * @return the urlname
     */
    public String getUrlname() {
        return this.urlname;
    }

    /**
     * set the urlname - 网站名称
     */
    public void setUrlname(String urlname) {
        this.urlname = urlname;
    }

    /**
     * get the url - 网址
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * set the url - 网址
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * get the userid - 用户id
     * @return the userid
     */
    public String getUserid() {
        return this.userid;
    }

    /**
     * set the userid - 用户id
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }

    /**
     * get the username - 用户名称
     * @return the username
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * set the username - 用户名称
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * get the value from Map
     */
    public void fromMap(Map map) {

        setId(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("id")), id));
        setUrlname(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("urlname")), urlname));
        setUrl(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("url")), url));
        setUserid(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("userid")), userid));
        setUsername(StringUtils.defaultIfEmpty(StringUtils.toString(map.get("username")), username));
    }

    /**
     * set the value to Map
     */
    public Map toMap() {

        Map map = new HashMap();
        map.put("id", StringUtils.toString(id, eiMetadata.getMeta("id")));
        map.put("urlname", StringUtils.toString(urlname, eiMetadata.getMeta("urlname")));
        map.put("url", StringUtils.toString(url, eiMetadata.getMeta("url")));
        map.put("userid", StringUtils.toString(userid, eiMetadata.getMeta("userid")));
        map.put("username", StringUtils.toString(username, eiMetadata.getMeta("username")));

        return map;

    }
}