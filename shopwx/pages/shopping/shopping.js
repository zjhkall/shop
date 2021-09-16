const util = require("../../utils/util.js")// 导包，引入外部文件
const app = getApp()
Page({
  data: { //这个页面的全局变量
    api: app.globalData.api,
    //indicatorDots: true, //指示器true
    //autoplay: true, //自动播放true
    //interval: 4000, //间隔时间4s
    //duration: 2000, //动画持续时长2s
    imgUrls: [],
    category: [],
    product: [],
    cid: 0
  },
  onLoad: function () { //这个页面的主函数
    // 数据库数据->java->小程序
    // 数据库和java交互用的是mybits sql语句
    // 小程序和Java交互用的时http请求
    util.setTabBarBadgeNumber(2,util.getStorageObj('cart').length)
    util.httpGet('/wx/index',resp=>{
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
  //小程序js后面可以加';',也可以不加，行业习惯是不加
  //网页的js后面可以加';',也可以不加，行业习惯是加
  clickCategory:function(e){
    //util.alert(e.currentTarget.dataset.item.id)
    this.data.cid = e.currentTarget.dataset.item.id
    util.httpGet('/wx/index?cid=' + this.data.cid,resp=>{
      this.data.product = resp.product
      util.getStorageCart('cart','id','count',this.data.product)
      this.setData(this.data)
    })
  },
  addCart:function(e){
    var item = e.currentTarget.dataset.item
    var len = util.enableAddStorageObj('cart','id','num',item,function(){
      util.alert(item.product + "售罄")
    })
    util.setTabBarBadgeNumber(2,len)//设置TabBar红色数字角标
    util.getStorageCart('cart','id','count',this.data.product)
    this.setData(this.data)
  },
  reduceCart:function(e){
    var item = e.currentTarget.dataset.item
    var len = util.delStorageObj('cart','id',item.id)
    util.setTabBarBadgeNumber(2,len)
    util.getStorageCart('cart','id','count',this.data.product)
    this.setData(this.data)
  },
  onTabItemTap:function(){
    util.getStorageCart('cart','id','count',this.data.product)
    this.setData(this.data)
  },
  startPage:function(e){
    //wx.setStorageSync('item', e.currentTarget.dataset.)
    //打开详情页面
    //util.redirect('detail')//不带参数打开详情页
    //wx.setStorageSync('item', e.currentTarget.dataset.item)
    util.redirect({ url:'detail',//打开详情页  //打开页面传参,
    logo:e.currentTarget.dataset.item.logo,
      price:e.currentTarget.dataset.item.price,
      product:e.currentTarget.dataset.item.product,
      descript:e.currentTarget.dataset.item.descript,
      count:e.currentTarget.dataset.item.count
  })
  }
})