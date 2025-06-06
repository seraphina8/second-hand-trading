<template>
  <div style="margin-bottom: 10px">

      <div @click="showDetail(info.id)" >
        <el-row :gutter="10">
          <el-col :span="12">
            <el-image
                :fit="fit"
                style="height: 75px;width:  100px; overflow: inherit"
                :src=" Utils.getFileInfo(info.img)[0].url"
                :preview-src-list="[Utils.getFileInfo(info.img)[0].url]">
            </el-image>
          </el-col>
          <el-col :span="12">
            <H5 style="margin: 5px;  height: 75px;">{{ info.name }}</H5>
          </el-col>
        </el-row>
      </div>
  </div>
</template>

<script>

import Utils from "@/utils/utils";

export default {
  name: "ProductItem",
  computed: {
    Utils() {
      return Utils
    }
  },
  props: {
    info: {
      type: Object,
      default: {}
    },
  },
  data() {
    return {}
  },

  created() {
    this.load()
  },
  methods: {
    load() {

    },
    showDetail(id) {
      this.$router.push("/front/productDetail/" + id)
    }
  }
};
</script>

<style scoped>
.category {
  position: fixed;
  left: 150px;
  top: 20%;
  cursor: pointer;
  z-index: 999;
}

.category li {
  line-height: 30px;
  background: skyblue;
  color: #fff;
  padding: 10px;
}

.headerBg {
  background: #eee !important;
}

</style>
