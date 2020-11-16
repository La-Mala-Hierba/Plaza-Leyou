<template>
  <v-form v-model="valid" ref="myBrandForm">
    <v-text-field v-model="brand.name" label="Marca" required :rules="nameRules"/>
    <v-text-field v-model="brand.letter" label="Primera letra de marca" required :rules="letterRules"/>
    <v-cascader
      url="/item/category/list"
      multiple
      required
      v-model="brand.categories"
        label="Elegir la categoría de productos"/>
    <v-layout row>
      <v-flex xs3>
        <span style="font-size: 16px; color: #444">Marca LOGO：</span>
      </v-flex>
      <v-flex>
        <v-upload
          v-model="brand.image" url="/upload/image" :multiple="false" :pic-width="250" :pic-height="90"
        />
      </v-flex>
    </v-layout>
    <v-layout class="my-4" row>
      <v-spacer/>
      <v-btn @click="submit" color="primary">Submit</v-btn>
      <v-btn @click="clear">Reset</v-btn>
    </v-layout>
  </v-form>
</template>

<script>
  export default {
    name: "brand-form",
    props: { //brand-form是brand的局部组件，通过props接收brand传递来的属性值
      oldBrand: {//当brand中的oldBrand发生变化时，brandForm中对应的数据也发生变化
        type: Object
      },
      isEdit: {//当brand中的isEdit发生变化时，brandForm中对应的数据也发生变化
        type: Boolean,
        default: false
      }
    },
    data() {
      return {
        valid: false, // 表单校验结果标记
        brand: {
          name: '', // Marca名称
          letter: '', // Marca首字母
          image: '',// Marcalogo
          categories: [], // Marca所属的商品分类数组
        },
        nameRules: [
          v => !!v || "Obligatorio para rellenar",
          v => v.length > 1 || "Mínimo 2 caracteres"
        ],
        letterRules: [
          v => !!v || "Obligatorio para rellenar",
          v => /^[a-zA-Z]{1}$/.test(v) || "Obligatorio de ser una única letra"
        ]
      }
    },
    methods: {
      submit() {
        // 表单校验 this.$refs.获取所有表单信息，this.$refs.myBrandForm = 获取myBrandForm的表单信息
        if (this.$refs.myBrandForm.validate()) {
          //
          // 定义一个请求参数对象，通过解构表达式来获取brand中的属性
          const {categories, letter, ...params} = this.brand; // params: {name, image, cids, letter}
          // 数据库中只要保存分类的id即可，因此我们对categories的值进行处理,只保留id，并转为字符串
          params.cids = categories.map(c => c.id).join(","); //"74, 75, 76" http://localhost/user?cids=74, 75, 76
          // 将字母都处理为大写
          params.letter = letter.toUpperCase();
          // 将数据提交到后台
          this.$http({
            method: this.isEdit ? 'put' : 'post',
            url: '/item/brand',
            data: this.$qs.stringify(params)
          }).then(() => {
            // 关闭窗口
            this.$emit("close");
            this.$message.success("Guardado en éxito！");
            this.clear();
          })
            .catch(() => {
              this.$message.error("Fallido！");
            });
        }
      },
      clear() {
        // 重置表单
        this.$refs.myBrandForm.reset();
        // 需要手动清空商品分类
        this.categories = null;
      }
    },
    watch: {
        oldBrand: {// 监控oldBrand的变化
          handler(val) {
            if (val) {
              // 注意不要直接复制，否则这边的修改会影响到父组件的数据，copy属性即可
              this.brand = Object.deepCopy(val)
            } else {
              // 为空，初始化brand
              this.brand = {
                name: '',
                letter: '',
                image: '',
                categories: [],
              }
            }
        },
        deep: true
      }
    }
  }
</script>

<style scoped>

</style>
