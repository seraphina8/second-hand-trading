<template>
  <div>
    <div style="margin: 10px 0">
      <H2>排行榜</H2>
      <br>
      <el-row :gutter="10" v-for="(item, index) in productTopList" key="index">
        <el-col v-for="(itemDetail, indexDetail) in item" :span="24/itemSize">
          <ProductCard :info="itemDetail" @changeAudio='$emit("changeAudio", itemDetail.id)'/>
        </el-col>
      </el-row>
      <H2>猜你喜欢</H2>
      <br>
      <el-row :gutter="10" v-for="(item, index) in recommendProductList" key="index">
        <el-col v-for="(itemDetail, indexDetail) in item" :span="24/itemSize">
          <ProductCard :info="itemDetail" @changeAudio='$emit("changeAudio", itemDetail.id)'/>
        </el-col>
      </el-row>

    </div>
  </div>
</template>

<script>
import ProductCard from "@/views/front/ProductCard.vue";

export default {
  name: "FrontHome",
  components: {ProductCard},
  data() {
    return {
      productTopList: [],
      itemSize: 6,

      recommendProductList: [],
      pageNum: 1,
    }
  },
  created() {
    this.loadTop()
    this.loadRecommend();
  },
  methods: {
    loadTop() {
      this.request.get("/product/top/12").then(res => {
        let data = res.data
        this.productTopList = [];
        for (let i = 0; i < data.length / this.itemSize; i++) {
          let itemData = [];
          for (let j = 0; j < this.itemSize; j++) {
            let index = i * this.itemSize + j;
            console.log("i", i, "j", j, "index", index)
            if (index < data.length) {
              itemData.push(data[index]);
            }
          }
          this.productTopList.push(itemData);
        }
      })
    },
    loadRecommend() {
      this.request.get("/product/recommend/12/"+ this.pageNum).then(res => {
        let data = res.data
        this.recommendProductList = [];
        for (let i = 0; i < data.length / this.itemSize; i++) {
          let itemData = [];
          for (let j = 0; j < this.itemSize; j++) {
            let index = i * this.itemSize + j;
            console.log("i", i, "j", j, "index", index)
            if (index < data.length) {
              itemData.push(data[index]);
            }
          }
          this.recommendProductList.push(itemData);
        }
      })
    }
  }
}
</script>

<style>
.swiper img {
  width: 100%;
  height: 100%;
}
</style>
