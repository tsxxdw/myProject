// component/slide/slide_2.js
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
    windowHeight: 200,
    indexId: 0,
    currentCar_index: 0,
    carList: [{
      index: 0,
      name: '小面包车'
    }, {
      index: 1,
      name: '中面包车'
    }, {
      index: 2,
      name: '小型厢货'
    }, {
      index: 3,
      name: '中货车'
    }],


    dataList: [{
      "proUrl": "/images/car_1.png",
      "title": "title",
      "carPrice": 10000,
      "carBase": 1,
      "carBaseStr": '1(小面包)',
      'carIntroduce': '小面包车适合1人',
      "carType": "smallVan" //小面包车
    }, {
      "proUrl": "/images/car_2.png",
      "title": "title",
      "carPrice": 20000,
        "carBase": 1.1,
        "carBaseStr": '1.1(中面包车)',
        'carIntroduce': '中面包车适合2人',
      "carType": "mediumVan" //中面包车
    }, {
      "proUrl": "/images/car_3.png",
      "title": "title",
      "carPrice": 30000,
        "carBase": 1.2,
        "carBaseStr": '1.2(小型厢货)',
        'carIntroduce': '小型厢货车辆适合2-3人',
      "carType": "smallCargo" //小型厢货
    }, {
      "proUrl": "/images/car_4.png",
      "title": "title",
      "carPrice": 40000,
        "carBase": 1.3,
        "carBaseStr": '1.3(中货车)',
        'carIntroduce': '中货车适合3-5人',
      "carType": "mediumTruck" //中货车
    }]
  },

    attached() {
      this.triggerEvent('getCarInfo', this.data.dataList[0])

    },
  /**
   * 组件的方法列表
   */
  methods: {
    swiperchange: function(e) {
      var current = e.detail.current;
      this.setData({
        currentCar_index: current,
      });
      this.triggerEvent('getCarInfo', this.data.dataList[current])

    },

  }
})