// pages/datail/detail.js
const util = require('../../utils/util.js')
const app = getApp() //获取app.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    api: app.globalData.api,
    item:{},
    green:[
      {name:'PLA', value:'PLA单孔细吸管(推荐)', checked:'true'},
      //{name:'ok', value:'PLA可降解吸管'},
      {name:'no', value:'不使用吸管'}
    ],
    status:[
      {name:'ice', value:'冰(推荐)', checked:'true'},
      {name:'water', value:'水'}
    ],
    Imany:[
      {name:'usual', value:'正常(推荐)', checked:'true'},
      {name:'no', value:'去冰球'}
    ],
    sweet:[
      {name:'standard', value:'标准甜(推荐)', checked:'true'},
      {name:'less', value:'少甜'},
      {name:'nos', value:'不另外加糖'}
    ],
    lemon:[
      {name:'usual', value:'标准(含柠檬片)', checked:'true'},
      {name:'no', value:'去柠檬片(不满杯)'}
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //util.alert(options.product) //有没有把item传过来
    //this.data.item = options.item
    this.data.item.logo = options.logo
    this.data.item.price = options.price
    this.data.item.product = options.product
    this.data.item.descript = options.descript
    this.data.item.count = options.count
    // this.data.item = wx.getStorageSync('item')
    this.setData(this.data)
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  addCart: function(e){
    var item = this.data.item
    var len = util.enableAddStorageObj('cart', 'id', 'num', item, function(){
      util.alert(item.product + '售罄了！')
    })
    // util.setTabBarBadgeNumber(1, len)  //设置TabBar红色数字角标
     util.getStorageCart('cart', 'id', 'count', this.data.product)// 把同类商品折合计数
    this.setData(this.data)
  },
  reduceCart: function(e){
    var item = this.data.item
    var len = util.delStorageObj('cart', 'id', item.id)
    // util.setTabBarBadgeNumber(1, len)  //设置TabBar红色数字角标
     util.getStorageCart('cart', 'id', 'count', this.data.product)// 把同类商品折合计数
    this.setData(this.data)
  },
})