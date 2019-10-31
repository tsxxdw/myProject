// component/selector/scroll /scroll_1.js
Component({
  /**
   * 组件的属性列表
   */
  properties: {
      //图片地址
      textInfo: {
        type: String
      },
  },
  attached() {
    // let temp = 'dataList[' + 0 + '].name'  // 获取goodsList[index].num
    // this.setData({
    //   [temp]: this.data.textInfo
    // })
  },
  /**
   * 组件的初始数据
   */
  data: {
    dataList: [
      { id: 0, name: '有电梯，免费', price: 0, ladder:0},
      { id: 1, name: '无电梯1层，免费', price: 0, ladder: 1 },
      { id: 2, name: '无电梯2层，20元', price: 2000, ladder: 2 },
      { id: 3, name: '无电梯3层，30元', price: 3000, ladder: 3 },
      { id: 4, name: '无电梯4层，40元', price: 4000, ladder: 4 },
      { id: 5, name: '无电梯5层，50元', price: 5000, ladder: 5},
      { id: 6, name: '无电梯6层，60元', price: 6000, ladder: 6},
      { id: 7, name: '无电梯7层，70元', price: 7000, ladder: 7},
      { id: 8, name: '无电梯8层，80元', price: 8000, ladder: 8},
      { id: 9, name: '无电梯9层，90元', price: 9000, ladder: 9},
      { id: 10, name: '无电梯10层，100元', price: 10000, ladder: 10},
    ],
    index: 0
  },

  /**
   * 组件的方法列表
   */
  methods: {
    bindPickerChange_hx: function (e) {
      var that=this;
      var index=e.detail.value;
      var carryLadder = this.data.dataList[index].name;
      var ladder = this.data.dataList[index].ladder;
      that.setData({   //给变量赋值
        index: index,  //每次选择了下拉列表的内容同时修改下标然后修改显示的内容，显示的内容和选择的内容一致
        carryLadder: carryLadder,
        ladder: ladder
      })
      if (that.data.textInfo=="搬出楼层"){
        this.triggerEvent('getStartInfo', this.data.dataList[index])
      }else{
        this.triggerEvent('getEndInfo', this.data.dataList[index])
      }
    },
  }
})
