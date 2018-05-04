package com.lcm.app.data.entity


/**
 * ****************************************************************
 * Author: LCM
 * Date: 2018/5/4 下午4:47
 * Desc:
 * *****************************************************************
 */
class WelfareBean {
    var _id: String? = null
    var createdAt: String? = null
    var desc: String? = null
    var publishedAt: String? = null
    var source: String? = null
    var type: String? = null
    var url: String? = null
    private var used: Boolean = false
    var who: String? = null

    constructor(_id: String, createdAt: String, desc: String,
                publishedAt: String, source: String, type: String, url: String,
                used: Boolean, who: String) {
        this._id = _id
        this.createdAt = createdAt
        this.desc = desc
        this.publishedAt = publishedAt
        this.source = source
        this.type = type
        this.url = url
        this.used = used
        this.who = who
    }

    constructor() {}

    fun isUsed(): Boolean {
        return used
    }

    fun setUsed(used: Boolean) {
        this.used = used
    }


    fun getUsed(): Boolean {
        return this.used
    }

    override fun toString(): String {
        return "WelfareBean(_id=$_id, createdAt=$createdAt, desc=$desc, publishedAt=$publishedAt, source=$source, type=$type, url=$url, used=$used, who=$who)"
    }
}
