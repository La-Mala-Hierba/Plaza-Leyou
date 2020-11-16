
  // 分类
 const treeData = [{
    "id": 74,
    "name": "Electrónica",
    "parentId": 0,
    "isParent": true,
    "sort": 2,
    "path": ["Electrónica"],
    "children": [{
      "id": 75,
      "name": "Telefonía",
      "parentId": 74,
      "isParent": true,
      "sort": 1,
      "path": ["Electrónica", "Telefonía"],
      "children": [{
        "id": 76,
        "name": "Móviles",
        "parentId": 75,
        "isParent": false,
        "sort": 1,
        "path": ["Electrónica", "Telefonía", "Móviles"]
      }, {"id": 77, "name": "Teléfono Fijo", "parentId": 75, "isParent": false, "sort": 2, "path": ["手机", "Telefonía", "Teléfono Fijo"]}]
    }, {
      "id": 78,
      "name": "Proveedor Telefomunicativo",
      "parentId": 74,
      "isParent": true,
      "sort": 2,
      "path": ["手机", "Proveedor Telefomunicativo"],
      "children": [{
        "id": 79,
        "name": "合约机",
        "parentId": 78,
        "isParent": false,
        "sort": 1,
        "path": ["手机", "Proveedor Telefomunicativo", "合约机"]
      }, {
        "id": 80,
        "name": "选号中心",
        "parentId": 78,
        "isParent": false,
        "sort": 2,
        "path": ["手机", "Proveedor Telefomunicativo", "选号中心"]
      }, {"id": 81, "name": "装宽带", "parentId": 78, "isParent": false, "sort": 3, "path": ["手机", "Proveedor Telefomunicativo", "装宽带"]}, {
        "id": 82,
        "name": "办套餐",
        "parentId": 78,
        "isParent": false,
        "sort": 4,
        "path": ["手机", "Proveedor Telefomunicativo", "办套餐"]
      }]
    }, {
      "id": 83,
      "name": "Accesorios de Smartphones",
      "parentId": 74,
      "isParent": true,
      "sort": 3,
      "path": ["手机", "Accesorios de Smartphones"],
      "children": [{
        "id": 84,
        "name": "移动电源",
        "parentId": 83,
        "isParent": false,
        "sort": 1,
        "path": ["手机", "Accesorios de Smartphones", "移动电源"]
      }, {
        "id": 85,
        "name": "电池/移动电源",
        "parentId": 83,
        "isParent": false,
        "sort": 2,
        "path": ["手机", "Accesorios de Smartphones", "电池/移动电源"]
      }, {
        "id": 86,
        "name": "蓝牙耳机",
        "parentId": 83,
        "isParent": false,
        "sort": 3,
        "path": ["手机", "Accesorios de Smartphones", "蓝牙耳机"]
      }, {
        "id": 87,
        "name": "充电器/数据线",
        "parentId": 83,
        "isParent": false,
        "sort": 4,
        "path": ["手机", "Accesorios de Smartphones", "充电器/数据线"]
      }, {
        "id": 88,
        "name": "苹果周边",
        "parentId": 83,
        "isParent": false,
        "sort": 5,
        "path": ["手机", "Accesorios de Smartphones", "苹果周边"]
      }, {
        "id": 89,
        "name": "手机耳机",
        "parentId": 83,
        "isParent": false,
        "sort": 6,
        "path": ["手机", "Accesorios de Smartphones", "手机耳机"]
      }, {
        "id": 90,
        "name": "手机贴膜",
        "parentId": 83,
        "isParent": false,
        "sort": 7,
        "path": ["手机", "Accesorios de Smartphones", "手机贴膜"]
      }, {
        "id": 91,
        "name": "手机存储卡",
        "parentId": 83,
        "isParent": false,
        "sort": 8,
        "path": ["手机", "Accesorios de Smartphones", "手机存储卡"]
      }, {
        "id": 92,
        "name": "充电器",
        "parentId": 83,
        "isParent": false,
        "sort": 9,
        "path": ["手机", "Accesorios de Smartphones", "充电器"]
      }, {
        "id": 93,
        "name": "数据线",
        "parentId": 83,
        "isParent": false,
        "sort": 10,
        "path": ["手机", "Accesorios de Smartphones", "数据线"]
      }, {
        "id": 94,
        "name": "手机保护套",
        "parentId": 83,
        "isParent": false,
        "sort": 11,
        "path": ["手机", "Accesorios de Smartphones", "手机保护套"]
      }, {
        "id": 95,
        "name": "车载配件",
        "parentId": 83,
        "isParent": false,
        "sort": 12,
        "path": ["手机", "Accesorios de Smartphones", "车载配件"]
      }, {
        "id": 96,
        "name": "iPhone 配件",
        "parentId": 83,
        "isParent": false,
        "sort": 13,
        "path": ["手机", "Accesorios de Smartphones", "iPhone 配件"]
      }, {
        "id": 97,
        "name": "手机电池",
        "parentId": 83,
        "isParent": false,
        "sort": 14,
        "path": ["手机", "Accesorios de Smartphones", "手机电池"]
      }, {
        "id": 98,
        "name": "创意配件",
        "parentId": 83,
        "isParent": false,
        "sort": 15,
        "path": ["手机", "Accesorios de Smartphones", "创意配件"]
      }, {
        "id": 99,
        "name": "便携/无线音响",
        "parentId": 83,
        "isParent": false,
        "sort": 16,
        "path": ["手机", "Accesorios de Smartphones", "便携/无线音响"]
      }, {
        "id": 100,
        "name": "手机饰品",
        "parentId": 83,
        "isParent": false,
        "sort": 17,
        "path": ["手机", "Accesorios de Smartphones", "手机饰品"]
      }, {
        "id": 101,
        "name": "拍照配件",
        "parentId": 83,
        "isParent": false,
        "sort": 18,
        "path": ["手机", "Accesorios de Smartphones", "拍照配件"]
      }, {"id": 102, "name": "手机支架", "parentId": 83, "isParent": false, "sort": 19, "path": ["手机", "Accesorios de Smartphones", "手机支架"]}]
    }]
  }, {
    "id": 103,
    "name": "Electrodomésticos",
    "parentId": 0,
    "isParent": true,
    "sort": 3,
    "path": ["Electrodomésticos"],
    "children": [{
      "id": 104,
      "name": "Gran Electrodoméstico",
      "parentId": 103,
      "isParent": true,
      "sort": 1,
      "path": ["Electrodomésticos", "Gran Electrodoméstico"],
      "children": [{
        "id": 105,
        "name": "TV",
        "parentId": 104,
        "isParent": false,
        "sort": 1,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "TV"]
      }, {
        "id": 106,
        "name": "Aire acondicionad",
        "parentId": 104,
        "isParent": false,
        "sort": 2,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Aire acondicionado"]
      }, {
        "id": 107,
        "name": "Frigoríficos",
        "parentId": 104,
        "isParent": false,
        "sort": 3,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Frigoríficos"]
      }, {
        "id": 108,
        "name": "Lavadora",
        "parentId": 104,
        "isParent": false,
        "sort": 4,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Lavadora"]
      }, {
        "id": 109,
        "name": "Lavavajillas",
        "parentId": 104,
        "isParent": false,
        "sort": 5,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Lavavajillas"]
      }, {
        "id": 110,
        "name": "DVD",
        "parentId": 104,
        "isParent": false,
        "sort": 6,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "DVD"]
      }, {
        "id": 111,
        "name": "Secadoras",
        "parentId": 104,
        "isParent": false,
        "sort": 7,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Secadoras"]
      }, {
        "id": 112,
        "name": "Congeladores",
        "parentId": 104,
        "isParent": false,
        "sort": 8,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Congeladores"]
      }, {
        "id": 113,
        "name": "Accessorios",
        "parentId": 104,
        "isParent": false,
        "sort": 9,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Accessorios"]
      }, {
        "id": 114,
        "name": "Hornos",
        "parentId": 104,
        "isParent": false,
        "sort": 10,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Hornos"]
      }, {
        "id": 115,
        "name": "Soundbar",
        "parentId": 104,
        "isParent": false,
        "sort": 11,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Soundbar"]
      }, {
        "id": 116,
        "name": "Accessorios",
        "parentId": 104,
        "isParent": false,
        "sort": 12,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Accessorios"]
      }, {
        "id": 117,
        "name": "Companas",
        "parentId": 104,
        "isParent": false,
        "sort": 13,
        "path": ["Electrodomésticos", "Gran Electrodoméstico", "Companas"]
      }, {"id": 118, "name": "酒柜", "parentId": 104, "isParent": false, "sort": 14, "path": ["Electrodomésticos", "Gran Electrodoméstico", "酒柜"]}]
    }, {
      "id": 119,
      "name": "Pequeño Electrodoméstico",
      "parentId": 103,
      "isParent": true,
      "sort": 2,
      "path": ["Electrodomésticos", "Pequeño Electrodoméstico"],
      "children": [{
        "id": 120,
        "name": "Robots de cocina",
        "parentId": 119,
        "isParent": false,
        "sort": 1,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "Robots de cocina"]
      }, {
        "id": 121,
        "name": "油烟机",
        "parentId": 119,
        "isParent": false,
        "sort": 2,
        "path": ["Electrodomésticos", "厨卫大电", "油烟机"]
      }, {
        "id": 122,
        "name": "热水器",
        "parentId": 119,
        "isParent": false,
        "sort": 3,
        "path": ["Electrodomésticos", "厨卫大电", "热水器"]
      }, {
        "id": 123,
        "name": "消毒柜",
        "parentId": 119,
        "isParent": false,
        "sort": 4,
        "path": ["Electrodomésticos", "厨卫大电", "消毒柜"]
      }, {"id": 124, "name": "洗碗机", "parentId": 119, "isParent": false, "sort": 5, "path": ["Electrodomésticos", "厨卫大电", "洗碗机"]}]
    }, {
      "id": 125,
      "name": "Electrodoméstico Cocina",
      "parentId": 103,
      "isParent": true,
      "sort": 3,
      "path": ["Electrodomésticos", "Electrodoméstico Cocina"],
      "children": [{
        "id": 126,
        "name": "料理机",
        "parentId": 125,
        "isParent": false,
        "sort": 1,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "料理机"]
      }, {
        "id": 127,
        "name": "榨汁机",
        "parentId": 125,
        "isParent": false,
        "sort": 2,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "榨汁机"]
      }, {
        "id": 128,
        "name": "电饭煲",
        "parentId": 125,
        "isParent": false,
        "sort": 3,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电饭煲"]
      }, {
        "id": 129,
        "name": "电压力锅",
        "parentId": 125,
        "isParent": false,
        "sort": 4,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电压力锅"]
      }, {
        "id": 130,
        "name": "豆浆机",
        "parentId": 125,
        "isParent": false,
        "sort": 5,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "豆浆机"]
      }, {
        "id": 131,
        "name": "咖啡机",
        "parentId": 125,
        "isParent": false,
        "sort": 6,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "咖啡机"]
      }, {
        "id": 132,
        "name": "微波炉",
        "parentId": 125,
        "isParent": false,
        "sort": 7,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "微波炉"]
      }, {
        "id": 133,
        "name": "电烤箱",
        "parentId": 125,
        "isParent": false,
        "sort": 8,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电烤箱"]
      }, {
        "id": 134,
        "name": "电磁炉",
        "parentId": 125,
        "isParent": false,
        "sort": 9,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电磁炉"]
      }, {
        "id": 135,
        "name": "面包机",
        "parentId": 125,
        "isParent": false,
        "sort": 10,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "面包机"]
      }, {
        "id": 136,
        "name": "煮蛋器",
        "parentId": 125,
        "isParent": false,
        "sort": 11,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "煮蛋器"]
      }, {
        "id": 137,
        "name": "酸奶机",
        "parentId": 125,
        "isParent": false,
        "sort": 12,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "酸奶机"]
      }, {
        "id": 138,
        "name": "电炖锅",
        "parentId": 125,
        "isParent": false,
        "sort": 13,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电炖锅"]
      }, {
        "id": 139,
        "name": "电水壶/热水瓶",
        "parentId": 125,
        "isParent": false,
        "sort": 14,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电水壶/热水瓶"]
      }, {
        "id": 140,
        "name": "电饼铛",
        "parentId": 125,
        "isParent": false,
        "sort": 15,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电饼铛"]
      }, {
        "id": 141,
        "name": "多用途锅",
        "parentId": 125,
        "isParent": false,
        "sort": 16,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "多用途锅"]
      }, {
        "id": 142,
        "name": "电烧烤炉",
        "parentId": 125,
        "isParent": false,
        "sort": 17,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电烧烤炉"]
      }, {
        "id": 143,
        "name": "果蔬解毒机",
        "parentId": 125,
        "isParent": false,
        "sort": 18,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "果蔬解毒机"]
      }, {
        "id": 144,
        "name": "其它厨房电器",
        "parentId": 125,
        "isParent": false,
        "sort": 19,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "其它厨房电器"]
      }, {
        "id": 145,
        "name": "养生壶/煎药壶",
        "parentId": 125,
        "isParent": false,
        "sort": 20,
        "path": ["Electrodomésticos", "Electrodoméstico Cocina", "养生壶/煎药壶"]
      }, {"id": 146, "name": "电热饭盒", "parentId": 125, "isParent": false, "sort": 21, "path": ["Electrodomésticos", "Electrodoméstico Cocina", "电热饭盒"]}]
    }, {
      "id": 147,
      "name": "Salud",
      "parentId": 103,
      "isParent": true,
      "sort": 4,
      "path": ["Electrodomésticos", "Salud"],
      "children": [{
        "id": 148,
        "name": "取暖电器",
        "parentId": 147,
        "isParent": false,
        "sort": 1,
        "path": ["Electrodomésticos", "Salud", "取暖电器"]
      }, {
        "id": 149,
        "name": "净化器",
        "parentId": 147,
        "isParent": false,
        "sort": 2,
        "path": ["Electrodomésticos", "Salud", "净化器"]
      }, {
        "id": 150,
        "name": "加湿器",
        "parentId": 147,
        "isParent": false,
        "sort": 3,
        "path": ["Electrodomésticos", "Salud", "加湿器"]
      }, {
        "id": 151,
        "name": "扫地机器人",
        "parentId": 147,
        "isParent": false,
        "sort": 4,
        "path": ["Electrodomésticos", "Salud", "扫地机器人"]
      }, {
        "id": 152,
        "name": "吸尘器",
        "parentId": 147,
        "isParent": false,
        "sort": 5,
        "path": ["Electrodomésticos", "Salud", "吸尘器"]
      }, {
        "id": 153,
        "name": "挂烫机/熨斗",
        "parentId": 147,
        "isParent": false,
        "sort": 6,
        "path": ["Electrodomésticos", "Salud", "挂烫机/熨斗"]
      }, {
        "id": 154,
        "name": "插座",
        "parentId": 147,
        "isParent": false,
        "sort": 7,
        "path": ["Electrodomésticos", "Salud", "插座"]
      }, {
        "id": 155,
        "name": "电话机",
        "parentId": 147,
        "isParent": false,
        "sort": 8,
        "path": ["Electrodomésticos", "Salud", "电话机"]
      }, {
        "id": 156,
        "name": "清洁机",
        "parentId": 147,
        "isParent": false,
        "sort": 9,
        "path": ["Electrodomésticos", "Salud", "清洁机"]
      }, {
        "id": 157,
        "name": "除湿机",
        "parentId": 147,
        "isParent": false,
        "sort": 10,
        "path": ["Electrodomésticos", "Salud", "除湿机"]
      }, {
        "id": 158,
        "name": "干衣机",
        "parentId": 147,
        "isParent": false,
        "sort": 11,
        "path": ["Electrodomésticos", "Salud", "干衣机"]
      }, {
        "id": 159,
        "name": "收录/音机",
        "parentId": 147,
        "isParent": false,
        "sort": 12,
        "path": ["Electrodomésticos", "Salud", "收录/音机"]
      }, {
        "id": 160,
        "name": "电风扇",
        "parentId": 147,
        "isParent": false,
        "sort": 13,
        "path": ["Electrodomésticos", "Salud", "电风扇"]
      }, {
        "id": 161,
        "name": "冷风扇",
        "parentId": 147,
        "isParent": false,
        "sort": 14,
        "path": ["Electrodomésticos", "Salud", "冷风扇"]
      }, {
        "id": 162,
        "name": "其它Salud",
        "parentId": 147,
        "isParent": false,
        "sort": 15,
        "path": ["Electrodomésticos", "Salud", "其它Salud"]
      }, {
        "id": 163,
        "name": "Salud配件",
        "parentId": 147,
        "isParent": false,
        "sort": 16,
        "path": ["Electrodomésticos", "Salud", "Salud配件"]
      }, {
        "id": 164,
        "name": "净水器",
        "parentId": 147,
        "isParent": false,
        "sort": 17,
        "path": ["Electrodomésticos", "Salud", "净水器"]
      }, {"id": 165, "name": "饮水机", "parentId": 147, "isParent": false, "sort": 18, "path": ["Electrodomésticos", "Salud", "饮水机"]}]
    }, {
      "id": 166,
      "name": "Cuidado facial y corporal",
      "parentId": 103,
      "isParent": true,
      "sort": 5,
      "path": ["Electrodomésticos", "Cuidado facial y corporal"],
      "children": [{
        "id": 167,
        "name": "剃须刀",
        "parentId": 166,
        "isParent": false,
        "sort": 1,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "剃须刀"]
      }, {
        "id": 168,
        "name": "剃/脱毛器",
        "parentId": 166,
        "isParent": false,
        "sort": 2,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "剃/脱毛器"]
      }, {
        "id": 169,
        "name": "口腔护理",
        "parentId": 166,
        "isParent": false,
        "sort": 3,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "口腔护理"]
      }, {
        "id": 170,
        "name": "电吹风",
        "parentId": 166,
        "isParent": false,
        "sort": 4,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "电吹风"]
      }, {
        "id": 171,
        "name": "美容器",
        "parentId": 166,
        "isParent": false,
        "sort": 5,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "美容器"]
      }, {
        "id": 172,
        "name": "理发器",
        "parentId": 166,
        "isParent": false,
        "sort": 6,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "理发器"]
      }, {
        "id": 173,
        "name": "卷/直发器",
        "parentId": 166,
        "isParent": false,
        "sort": 7,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "卷/直发器"]
      }, {
        "id": 174,
        "name": "按摩椅",
        "parentId": 166,
        "isParent": false,
        "sort": 8,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "按摩椅"]
      }, {
        "id": 175,
        "name": "按摩器",
        "parentId": 166,
        "isParent": false,
        "sort": 9,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "按摩器"]
      }, {
        "id": 176,
        "name": "足浴盆",
        "parentId": 166,
        "isParent": false,
        "sort": 10,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "足浴盆"]
      }, {
        "id": 177,
        "name": "血压计",
        "parentId": 166,
        "isParent": false,
        "sort": 11,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "血压计"]
      }, {
        "id": 178,
        "name": "电子秤/厨房秤",
        "parentId": 166,
        "isParent": false,
        "sort": 12,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "电子秤/厨房秤"]
      }, {
        "id": 179,
        "name": "血糖仪",
        "parentId": 166,
        "isParent": false,
        "sort": 13,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "血糖仪"]
      }, {
        "id": 180,
        "name": "体温计",
        "parentId": 166,
        "isParent": false,
        "sort": 14,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "体温计"]
      }, {
        "id": 181,
        "name": "其它健康电器",
        "parentId": 166,
        "isParent": false,
        "sort": 15,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "其它健康电器"]
      }, {
        "id": 182,
        "name": "计步器/脂肪检测仪",
        "parentId": 166,
        "isParent": false,
        "sort": 16,
        "path": ["Electrodomésticos", "Cuidado facial y corporal", "计步器/脂肪检测仪"]
      }]
    }, {
      "id": 183,
      "name": "Climatización",
      "parentId": 103,
      "isParent": true,
      "sort": 6,
      "path": ["Electrodomésticos", "Climatización"],
      "children": [{
        "id": 184,
        "name": "电动工具",
        "parentId": 183,
        "isParent": false,
        "sort": 1,
        "path": ["Electrodomésticos", "Climatización", "电动工具"]
      }, {
        "id": 185,
        "name": "手动工具",
        "parentId": 183,
        "isParent": false,
        "sort": 2,
        "path": ["Electrodomésticos", "Climatización", "手动工具"]
      }, {
        "id": 186,
        "name": "仪器仪表",
        "parentId": 183,
        "isParent": false,
        "sort": 3,
        "path": ["Electrodomésticos", "Climatización", "仪器仪表"]
      }, {
        "id": 187,
        "name": "浴霸/排气扇",
        "parentId": 183,
        "isParent": false,
        "sort": 4,
        "path": ["Electrodomésticos", "Climatización", "浴霸/排气扇"]
      }, {
        "id": 188,
        "name": "灯具",
        "parentId": 183,
        "isParent": false,
        "sort": 5,
        "path": ["Electrodomésticos", "Climatización", "灯具"]
      }, {
        "id": 189,
        "name": "LED灯",
        "parentId": 183,
        "isParent": false,
        "sort": 6,
        "path": ["Electrodomésticos", "Climatización", "LED灯"]
      }, {
        "id": 190,
        "name": "洁身器",
        "parentId": 183,
        "isParent": false,
        "sort": 7,
        "path": ["Electrodomésticos", "Climatización", "洁身器"]
      }, {
        "id": 191,
        "name": "水槽",
        "parentId": 183,
        "isParent": false,
        "sort": 8,
        "path": ["Electrodomésticos", "Climatización", "水槽"]
      }, {
        "id": 192,
        "name": "龙头",
        "parentId": 183,
        "isParent": false,
        "sort": 9,
        "path": ["Electrodomésticos", "Climatización", "龙头"]
      }, {
        "id": 193,
        "name": "淋浴花洒",
        "parentId": 183,
        "isParent": false,
        "sort": 10,
        "path": ["Electrodomésticos", "Climatización", "淋浴花洒"]
      }, {
        "id": 194,
        "name": "厨卫五金",
        "parentId": 183,
        "isParent": false,
        "sort": 11,
        "path": ["Electrodomésticos", "Climatización", "厨卫五金"]
      }, {
        "id": 195,
        "name": "家具五金",
        "parentId": 183,
        "isParent": false,
        "sort": 12,
        "path": ["Electrodomésticos", "Climatización", "家具五金"]
      }, {
        "id": 196,
        "name": "门铃",
        "parentId": 183,
        "isParent": false,
        "sort": 13,
        "path": ["Electrodomésticos", "Climatización", "门铃"]
      }, {
        "id": 197,
        "name": "电气开关",
        "parentId": 183,
        "isParent": false,
        "sort": 14,
        "path": ["Electrodomésticos", "Climatización", "电气开关"]
      }, {
        "id": 198,
        "name": "插座",
        "parentId": 183,
        "isParent": false,
        "sort": 15,
        "path": ["Electrodomésticos", "Climatización", "插座"]
      }, {
        "id": 199,
        "name": "电工电料",
        "parentId": 183,
        "isParent": false,
        "sort": 16,
        "path": ["Electrodomésticos", "Climatización", "电工电料"]
      }, {
        "id": 200,
        "name": "监控安防",
        "parentId": 183,
        "isParent": false,
        "sort": 17,
        "path": ["Electrodomésticos", "Climatización", "监控安防"]
      }, {
        "id": 201,
        "name": "电线/线缆",
        "parentId": 183,
        "isParent": false,
        "sort": 18,
        "path": ["Electrodomésticos", "Climatización", "电线/线缆"]
      }]
    }]
  }];
const brandData = [{"id": 1115, "name": "HTC", "image": "", "letter": "H", "categories": null}, {
    "id": 1528,
    "name": "LG",
    "image": "",
    "letter": "L",
    "categories": null
  }, {"id": 1912, "name": "NEC", "image": "", "letter": "N", "categories": null}, {
    "id": 2032,
    "name": "OPPO",
    "image": "http://img10.360buyimg.com/popshop/jfs/t2119/133/2264148064/4303/b8ab3755/56b2f385N8e4eb051.jpg",
    "letter": "O",
    "categories": null
  }, {"id": 2505, "name": "TCL", "image": "", "letter": "T", "categories": null}, {
    "id": 3177,
    "name": "爱贝多",
    "image": "",
    "letter": "A",
    "categories": null
  }, {"id": 3539, "name": "安桥（ONKYO）", "image": "", "letter": "A", "categories": null}, {
    "id": 3941,
    "name": "白金（PLATINUM）",
    "image": "",
    "letter": "B",
    "categories": null
  }, {"id": 4986, "name": "波导（BiRD）", "image": "", "letter": "B", "categories": null}, {
    "id": 6522,
    "name": "朵唯（DOOV）",
    "image": "",
    "letter": "D",
    "categories": null
  }];
// 商品
 const goodsData = [{
    "id": 145,
    "brandId": 91515,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "锤子（smartisan） 坚果32 手机 ",
    "subTitle": "【新品开售享壕礼】下单即送智能手环、蓝牙耳机、自拍杆~ <a href=\"https://item.jd.com/19566307882.html\" target=\"_blank\">点击购买坚果 Pro2 ！</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:00:09.000+0000",
    "lastUpdateTime": "2018-04-29T03:26:43.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "锤子（smartisan）"
  }, {
    "id": 207,
    "brandId": 2032,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "oopp2",
    "subTitle": "ppoo",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-29T01:46:15.000+0000",
    "lastUpdateTime": "2018-04-29T02:04:15.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "OPPO"
  }, {
    "id": 206,
    "brandId": 7817,
    "cid1": 103,
    "cid2": 104,
    "cid3": 105,
    "title": "海尔模卡（MOOKA）U55H3 ",
    "subTitle": "【预约，4月23日2099限量抢】海尔匠心打造，互联网定制，55英寸客厅尊贵机皇，高颜值外观！55吋曲面性价比甄选戳这里",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-22T01:57:44.000+0000",
    "lastUpdateTime": "2018-04-29T01:38:48.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrodomésticos/Gran Electrodoméstico/平板电视",
    "brandName": "海尔（Haier）"
  }, {
    "id": 204,
    "brandId": 18374,
    "cid1": 103,
    "cid2": 104,
    "cid3": 105,
    "title": "XiaoMi（MI）电视",
    "subTitle": "电视新品上新 !",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-22T01:36:08.000+0000",
    "lastUpdateTime": "2018-04-22T01:36:08.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrodomésticos/Gran Electrodoméstico/平板电视",
    "brandName": "XiaoMi（MI）"
  }, {
    "id": 203,
    "brandId": 325399,
    "cid1": 74,
    "cid2": 83,
    "cid3": 90,
    "title": "亿色(ESR) 苹果8/7/6s/6钢化膜",
    "subTitle": "配件大焕新~亿色自营官方旗舰店优质好货,低至5.9元!手机保护充电线材任君挑选，凑单还能省运费点我进店抢购(此商品不参加上述活动)",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T16:30:24.000+0000",
    "lastUpdateTime": "2018-04-21T16:30:24.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "手机/Accesorios de Smartphones/手机贴膜",
    "brandName": "亿色(ESR)"
  }, {
    "id": 202,
    "brandId": 325398,
    "cid1": 74,
    "cid2": 83,
    "cid3": 90,
    "title": "ESK  Plus抗蓝光钢化膜",
    "subTitle": "苹果8plus钢化膜 苹果7plus钢化膜 苹果6plus钢化膜 苹果6s plus钢化膜 iPhone8 plus钢化膜 iPhone7 plus钢化膜 iphone6 plus钢化膜",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T16:27:26.000+0000",
    "lastUpdateTime": "2018-04-21T16:27:26.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "手机/Accesorios de Smartphones/手机贴膜",
    "brandName": "比亚兹（ESK）"
  }, {
    "id": 182,
    "brandId": 18374,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "XiaoMi（MI） XiaoMi5X 全网通4G智能手机 ",
    "subTitle": "骁龙 八核CPU处理器 5.5英寸屏幕<a href=\"https://item.jd.com/26438399057.html\" target=\"_blank\">【XiaoMi新品】~红米note5 AI智能双摄</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:27.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:27.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "XiaoMi（MI）"
  }, {
    "id": 181,
    "brandId": 25591,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "vivo Y75 全面屏手机 4GB+32GB 移动联通电信4G手机 双卡双待 ",
    "subTitle": "<a href=\"https://sale.jd.com/act/vGWKogOy2nbPpQ.html\" target=\"_blank\">vivo X21新一代全面屏·6+128G大内存·AI智慧拍照·照亮你的美·现货抢购中</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:24.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:24.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "vivo"
  }, {
    "id": 180,
    "brandId": 12669,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "魅族（MEIZU） pro 6 plus手机 移动联通版 ",
    "subTitle": "【现货 劲爆价促销】<a href=\"https://item.jd.com/25109782095.html\" target=\"_blank\">魅蓝6 低至649元 评价更有好礼送</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:21.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:21.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "魅族（MEIZU）"
  }, {
    "id": 179,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） 荣耀7C 畅玩7C手机 全网通4G 全面屏 ",
    "subTitle": "【官方正品 全国联保】人脸识别 双摄美拍！高配拍送荣耀耳机<a href=\"https://item.jd.com/26131514041.html\" target=\"_blank\">@64G优惠购</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:19.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:19.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 178,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） 荣耀 畅玩6 手机 ",
    "subTitle": "【京仓直发】柔光自拍，舒适握感，长续航！<a href=\"https://item.jd.com/25882037762.html\" target=\"_blank\">【荣耀</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:17.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:17.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 177,
    "brandId": 18374,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "XiaoMi（MI） XiaoMiMax2 手机 双卡双待 ",
    "subTitle": "【XiaoMi大屏手机】6.44英寸屏，5300毫安电池。大屏玩王者荣耀更爽！<a href=\"https://item.jd.com/13038819059.html\" target=\"_blank\">变焦双摄XiaoMi5X</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:16.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:16.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "XiaoMi（MI）"
  }, {
    "id": 176,
    "brandId": 25591,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "vivo X21 屏幕指纹 双摄拍照手机 6GB+128GB 移动联通电信4G 双卡双待 ",
    "subTitle": "【稀缺货品】屏幕指纹识别·3D曲面背部玻璃·AI智慧拍照·照亮你的美·<a href=\"https://sale.jd.com/act/vGWKogOy2nbPpQ.html\" target=\"_blank\">更多精彩点击</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:14.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:14.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "vivo"
  }, {
    "id": 175,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） nova 3e 手机 ",
    "subTitle": "【现货，送五重好礼】前置2400万自然美妆，送Huawei原装耳机等！<a href=\"https://item.jd.com/21698630782.html\" target=\"_blank\">Huaweinova2s热销中~</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:13.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:13.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 174,
    "brandId": 18374,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "XiaoMi（MI） 红米5 Plus 4+64G 全面屏手机 双卡双待 ",
    "subTitle": "下单送好礼 自营配送 18:9千元全面屏 前置柔光自拍 4000毫安大电池 骁龙八核处理器",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:10.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:10.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "XiaoMi（MI）"
  }, {
    "id": 173,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） 荣耀 畅玩6 全网通4G智能手机 双卡双待 2G+16G ",
    "subTitle": "【京东仓发货 正品保证】柔光自拍，舒适握感，长续航！<a href=\"https://item.jd.com/26134956416.html\" target=\"_blank\">新品荣耀畅玩7C！</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:08.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:08.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 172,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） Huawei 畅享6S 手机 ",
    "subTitle": "京东配送！  骁龙芯片！金属机身！  <a href=\"https://item.jd.com/21669543700.html\" target=\"_blank\">【新品畅享7s】</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:07.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:07.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 171,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） HuaweiP20 Pro 全面屏 手机 ",
    "subTitle": "【全国多仓发货】送价值158元原装礼盒（自拍杆+数据线+指环扣）+原装耳机！<a href=\"https://item.jd.com/13039618422.html\" target=\"_blank\">抢HuaweiP20</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:05.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:05.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 170,
    "brandId": 12669,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "魅族（MEIZU） 魅族 魅蓝A5 4G手机 双卡双待 ",
    "subTitle": "魅族新品千元机",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:03.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:03.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "魅族（MEIZU）"
  }, {
    "id": 169,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） 荣耀V10手机 ",
    "subTitle": "【下单立减】送荣耀自拍杆+荣耀2合1数据线+荣耀手机支架+壳膜套装!<a href=\"https://item.jd.com/25749144099.html\" target=\"_blank\">荣耀畅玩7X</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:01:02.000+0000",
    "lastUpdateTime": "2018-04-21T08:01:02.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 168,
    "brandId": 18374,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "XiaoMi（MI） XiaoMi5X 手机 ",
    "subTitle": "【爆款低价 移动/公开全网通不做混发，请放心购买！】5.5”屏幕，变焦双摄！<a href=\"https://item.jd.com/12068579160.html\" target=\"_blank\">戳戳XiaoMi6~</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:00:59.000+0000",
    "lastUpdateTime": "2018-04-21T08:00:59.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "XiaoMi（MI）"
  }, {
    "id": 167,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） 畅享6S 移动联通电信4G手机 ",
    "subTitle": "【正品国行,全新原封】骁龙芯片！金属机身！享看又享玩！<a href=\"https://item.jd.com/12207790357.html\" target=\"_blank\">荣耀8青春热卖中！</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:00:57.000+0000",
    "lastUpdateTime": "2018-04-21T08:00:57.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 166,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） Mate10 手机 ",
    "subTitle": "送Huawei原装视窗保护套+Huawei原装蓝牙耳机+乐心运动手环+钢化膜等！<a href=\"https://item.jd.com/18180585426.html\" target=\"_blank\">mate10pro</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:00:56.000+0000",
    "lastUpdateTime": "2018-04-21T08:00:56.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }, {
    "id": 165,
    "brandId": 18374,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "XiaoMi（MI） XiaoMinote3 手机 ",
    "subTitle": "赠壳膜全国联保京东配送/屏四曲面陶瓷机身骁龙835<a href=\"https://item.jd.com/16716408877.html\" target=\"_blank\">XiaoMiMIX2点击购买</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:00:54.000+0000",
    "lastUpdateTime": "2018-04-21T08:00:54.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "XiaoMi（MI）"
  }, {
    "id": 164,
    "brandId": 8557,
    "cid1": 74,
    "cid2": 75,
    "cid3": 76,
    "title": "Huawei（HUAWEI） 荣耀8 手机 ",
    "subTitle": "【店长推荐】美得与众不同！<a href=\"https://item.jd.com/25882037762.html\" target=\"_blank\">【荣耀新款全面屏手机→荣耀畅玩7C】</a>",
    "saleable": true,
    "valid": true,
    "createTime": "2018-04-21T08:00:51.000+0000",
    "lastUpdateTime": "2018-04-21T08:00:51.000+0000",
    "spuDetail": null,
    "skus": null,
    "categoryName": "Electrónica/Telefonía/Móviles",
    "brandName": "Huawei（HUAWEI）"
  }];
// 规格参数
 const phoneSpec = [{
    "group": "General",
    "params": [{"k": "Marca", "searchable": false, "global": true, "options": []}, {
      "k": "Modelo",
      "searchable": false,
      "global": true,
      "options": []
    }, {"k": "'Fecha de fabricación", "searchable": false, "global": true, "options": [], "numerical": true, "unit": "Año"}]
  }, {
    "group": "Información de producto",
    "params": [{"k": "Color", "searchable": false, "global": false, "options": []}, {
      "k": "Peso（g）",
      "searchable": false,
      "global": true,
      "options": [],
      "numerical": true,
      "unit": "g"
    }, {"k": "Material", "searchable": false, "global": true, "options": []}]
  }, {
    "group": "Sistema operativo",
    "params": [{"k": "Sistema operativo", "searchable": true, "global": true, "options": ["安卓", "IOS", "Windows", "功能机"]}]
  }, {
    "group": "Processor chip",
    "params": [{"k": "CPUMarca", "searchable": true, "global": true, "options": ["骁龙（Snapdragon)", "麒麟"]}, {
      "k": "CPUModelo",
      "searchable": false,
      "global": true,
      "options": []
    }, {"k": "Procesadores Intel Core", "searchable": true, "global": true, "options": ["一核", "二核", "四核", "六核", "八核", "十核"]}, {
      "k": "GHz de procesador",
      "searchable": true,
      "global": true,
      "options": [],
      "numerical": true,
      "unit": "GHz"
    }]
  }, {
    "group": "C",
    "params": [{
      "k": "Memoria RAM",
      "searchable": true,
      "global": false,
      "options": ["1GB", "2GB", "3GB", "4GB", "6GB", "8GB"],
      "numerical": false,
      "unit": "GB"
    }, {
      "k": "Memoria interna",
      "searchable": true,
      "global": false,
      "options": ["8GB", "16GB", "32GB", "64GB", "128GB", "256GB"],
      "numerical": false,
      "unit": "GB"
    }]
  }, {
    "group": "Pantalla",
    "params": [{
      "k": "Dimensiones（mm）",
      "searchable": true,
      "global": true,
      "options": [],
      "numerical": true,
      "unit": "mm"
    }, {"k": "Resolución", "searchable": false, "global": true, "options": []}]
  }, {
    "group": "Cámara",
    "params": [{
      "k": "Características cámara frontal",
      "searchable": true,
      "global": true,
      "options": [],
      "numerical": true,
      "unit": "万"
    }, {"k": "Características cámara trasera", "searchable": true, "global": true, "options": [], "numerical": true, "unit": "万"}]
  }, {
    "group": "Batería",
    "params": [{"k": "Capacidad de batería（mAh）", "searchable": true, "global": true, "options": [], "numerical": true, "unit": "mAh"}]
  }];

export {treeData, phoneSpec, brandData, goodsData}
