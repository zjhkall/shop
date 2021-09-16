// pages/cart/cart.js
const util = require('../../utils/util.js')
const app = getApp() //获取app.js
Page({
  data: {
    api:app.globalData.api,
    product: [], //购物车商品列表
    total:0,  //计算一下总价格
    name: '',
    mobile: '',                                                                               
    address: '',
    hidden: false
  },
  history:{
    product:[]
  },
  onLoad: function (options) {
    // this.data.name = wx.getStorageSync('name')//缓存中取姓名
    // this.data.name = wx.getStorageSync('mobile')//缓存中取手机
    // this.data.name = wx.getStorageSync('address')//缓存中取地址
    this.data.product = util.getStorageCart('cart', 'id', 'count')
    this.data.total = 0
    for(let i in this.data.product){
      this.data.product[i].fee = this.data.product[i].price * this.data.product[i].count
      this.data.total += this.data.product[i].fee
    }
    this.setData(this.data)
  },
  onTabItemTap: function(){ //点击Tab按钮的时候，这个函数会被调用
    this.onLoad() // 再一次调用主函数
  },
  addCart: function(e){
    var item = e.currentTarget.dataset.item
    var len = util.enableAddStorageObj('cart', 'id', 'num', item, function(){
      util.alert(item.product + '售罄了！')
    })
    util.setTabBarBadgeNumber(1, len)  //设置TabBar红色数字角标
    this.onLoad()
  },
  reduceCart: function(e){
    var item = e.currentTarget.dataset.item
    var len = util.delStorageObj('cart', 'id', item.id)
    util.setTabBarBadgeNumber(1, len)  //设置TabBar红色数字角标
    this.onLoad()
  },
  onInput:function(e){  //把输入框输入的内容赋值给对应的变量
    this.data[e.currentTarget.dataset.name] = e.detail.value
  },
  submit: function(){
    // util.alert(this.data.name + '.' + this.data.mobile + '.' + this.data.address)
    //在js中，有一个JSON对象，专门用于json字符串的生成和解析，stringify就是把对象转换成json字符串
    util.httpPost('/wx/order', {
      name: this.data.name,
      mobile:this.data.mobile,
      address: this.data.address,
      total:this.data.total,
      //tname:'tbl_zhangsan',
      json:JSON.stringify(this.data.product)
    }, resp=>{
      if(resp.code == 1){
        //保存上一次购物地址
        wx.setStorageSync('name', this.data.name)//本地缓存:缓存到小程序内部，也就是微信内部
        wx.setStorageSync('mobile', this.data.mobile)//当你卸载小程序或者卸载微信，这些本地缓存全部丢失
        wx.setStorageSync('address', this.data.address)//关键数据要存入mysql中，非关键数据存入本地缓存即可
        wx.setStorageSync('cart', '')//清空购物车
        util.setTabBarBadgeNumber(2, 0)///清除小红点
        this.data.product=[]
        this.data.total = 0 //总价清零
        this.setData(this.data) //更新页面
      }else{
        util.alert(resp.msg)
      }
    })
  },
  shoppingButton: function(e){
    this.data.hidden = false
    this.setData(this.data)
  },
  // history:function(e){
  //   util.httpPost('/wx/history', {
  //     name: this.data.name,
  //   }, resp=>{
  //     this.history.product = resp.product
  //     util.getStorageCart('cart', 'id', 'count', this.history.product)
  //   })
  //   this.data.hidden = true
  //   this.setData(this.history)
    
  //   this.setData(this.data)
  // }
})