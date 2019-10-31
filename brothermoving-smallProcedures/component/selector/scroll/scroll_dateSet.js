var dateTimePicker = require('../../../js/dateTimePicker.js');
var date = new Date();
var currentYears = date.getFullYear();
var currentMonth = date.getMonth() + 1;
var currentDay = date.getDate();
Component({
  /**
   * 组件的属性列表
   */
  properties: {

  },

  /**
   * 组件的初始数据
   */
  data: {
    carryDate:'请选择时间',
    date: '2018-10-01',
    time: '12:00',
    mydateTimeArray: null,
    dateTimeArray: null,
    dateTime: null,
    dateTimeArray1: null,
    dateTime1: null,
    startYear: 2000,
    endYear: 2050
  },

  /**
   * 组件的方法列表
   */
  methods: {
    changeDateTime(e) {
     var dataArray= e.detail.value;
      var carryDate = "20" + dataArray[0] + "-" + (dataArray[1] + 1) +"-"+ (dataArray[2] + 1);
      this.setData({ carryDate: carryDate });
      this.triggerEvent('getCarryDate', carryDate)
    },
  },
  attached() {
    // 获取完整的年月日 时分秒，以及默认显示的数组
    var obj = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    var obj1 = dateTimePicker.dateTimePicker(this.data.startYear, this.data.endYear);
    var dateTimeArray = obj.dateTimeArray;
    var mydateTimeArray = new Array();
    mydateTimeArray.push(dateTimeArray[0]);
    mydateTimeArray.push(dateTimeArray[1]);
    mydateTimeArray.push(dateTimeArray[2]);
    // 精确到分的处理，将数组的秒去掉
    var lastArray = obj1.dateTimeArray.pop();
    var lastTime = obj1.dateTime.pop();
    var date = currentYears + "-" + currentMonth + "-" + currentDay;
    this.setData({
      date: date,
      dateTime: obj.dateTime,
      dateTimeArray: obj.dateTimeArray,
      mydateTimeArray: mydateTimeArray,
      dateTimeArray1: obj1.dateTimeArray,
      dateTime1: obj1.dateTime
    });
  }
})
