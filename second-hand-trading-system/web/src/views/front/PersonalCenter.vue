<template>
  <div style="margin: 10px 0">
    <el-tabs v-model="activeName" :tab-position="tabPosition" type="card" @tab-click="handleClick">
      <el-tab-pane label="收货地址" name="shippingAddress">
        <el-card>
          <ShippingAddress v-if="activeName==='shippingAddress'"></ShippingAddress>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="收藏" name="productFavorite">
        <el-card>
          <ProductFavorite v-if="activeName==='productFavorite'"></ProductFavorite>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="历史浏览" name="productHistory">
        <el-card>
          <ProductHistory v-if="activeName==='productHistory'"></ProductHistory>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="我的留言" name="productLeaveMessage">
        <el-card>
          <ProductLeaveMessage v-if="activeName==='productLeaveMessage'"></ProductLeaveMessage>
        </el-card>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script lang="ts">
import Vue from 'vue';
import ShippingAddress from "./personalCenter/ShippingAddress.vue";
import ProductFavorite from "./personalCenter/ProductFavorite.vue";
import ProductHistory from "./personalCenter/ProductHistory.vue";
import ProductLeaveMessage from "./personalCenter/ProductLeaveMessage.vue";


export default Vue.extend({
  name: "PersonalCenter",
  components: {ShippingAddress, ProductFavorite, ProductHistory, ProductLeaveMessage},
  data() {
    return {
      activeName: 'shippingAddress',
      tabPosition: 'left'
    };
  },
  created() {

  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    }
  }
});
</script>
