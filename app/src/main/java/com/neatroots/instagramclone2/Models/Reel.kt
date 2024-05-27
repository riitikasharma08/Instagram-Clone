package com.neatroots.instagramclone2.Models

class Reel {
    var reelUrl:String=""
    var caption:String=""
       var profileLink:String?= null
    constructor()
    constructor(reelUrl: String, caption: String) {
        this.reelUrl = reelUrl
        this.caption= caption
    }

    constructor(reelUrl: String) {
        this.reelUrl = reelUrl
        this.caption= caption
        this.profileLink = profileLink
    }

    constructor(videoUrl: String, toString: String, image: String)

}


