// pages/index/index.js
const util = require('../../utils/util.js')
const app = getApp() // 获取app.js
Page({
   /**
   * 页面的初始数据
   */
  data: { //这个页面的全局变量
    api: app.globalData.api,
    indicatorDots: true, //指示器true
    autoplay: true, //自动播放true
    interval: 4000, //间隔时间4s
    duration: 2000, //动画持续时长2s
    imgUrls: [],
    category: [],
    product: [],
    cid: 0
  },
  onLoad: function () { //这个页面的主函数
    // 数据库数据->java->小程序
    // 数据库和java交互用的是mybits sql语句
    // 小程序和Java交互用的时http请求
    util.httpGet('/wx/index',resp=>{
      //util.alert(resp)
      this.data.category = resp.category
      if(this.data.category && this.data.category.length > 0){
        this.data.cid = this.data.category[0].id
      }
      
      this.data.product = resp.product
      util.getStorageCart('cart','id','count',this.data.product) // 把同类商品折合计
      this.data.imgUrls = resp.hot
      this.setData(this.data)// 把数据更新到页面上

    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  startPage: function(e){
    wx.switchTab({
      url: '../shopping/shopping'
    })
  },
})