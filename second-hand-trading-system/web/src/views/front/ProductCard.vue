<template>
  <div style="margin-bottom: 10px">
    <el-card :body-style="{ padding: '0px' }" round>
      <div @click="showDetail(info.id)">
        <el-row>
          <el-col class="image-container">
            <div class="image-aspect-ratio">
              <el-image
                  :src="Utils.getFileInfo(info.img)[0].url"
                  :preview-src-list="[Utils.getFileInfo(info.img)[0].url]"
              ></el-image>
            </div>
          </el-col>
          <el-col style="padding:10px">
            <!-- 其他内容保持不变 -->
            <el-row>
              <h4 class="ellipsis-text">{{ info.name }}</h4>
            </el-row>
            <el-row style="margin: 7.5px 0">
              <span class="price">{{ info.price }}</span>
              <span class="original-price">¥{{ info.originalPrice }}</span>
            </el-row>
            <el-row>
              <span style="color: #909399">{{ info.want }}人想要</span>
              <i class="el-icon-view" style="color: #909399; margin: 0 10px"> {{ info.pageView }}</i>
            </el-row>
          </el-col>
        </el-row>
      </div>
    </el-card>
  </div>
</template>

<script>

import Utils from "@/utils/utils";

export default {
  name: "ProductCard",
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

.ellipsis-text {
  white-space: nowrap; /* 防止文本换行 */
  overflow: hidden; /* 隐藏超出容器的部分 */
  text-overflow: ellipsis; /* 用省略号表示被截断的文本 */
  /* 可选样式 */
  margin: 0; /* 重置margin，如果需要的话 */
}

.image-container {
  position: relative; /* 确保内部元素可以绝对定位 */
  overflow: hidden; /* 防止图片溢出 */
}

.image-aspect-ratio {
  position: relative;
  width: 100%; /* 填满父容器宽度 */
  padding-top: 75%; /* 4:3的宽高比 */
}

.image-aspect-ratio > .el-image {

  position: absolute;
  top: 0;
  left: 0;
  width: 100%; /* 填满容器宽度 */
  height: 100%; /* 填满容器高度，但由于padding-top的存在，这实际上会导致图片保持4:3的比例 */
  object-fit: cover; /* 根据需要裁剪或填充图片以覆盖整个容器 */
}

.price {
  font-size: 17px;
  color: #f00; /* 红色字体表示价格 */
  font-weight: bold;
  margin-right: 10px;
}

.original-price {
  font-size: 12px;
  color: #999; /* 灰色字体表示原价 */
  text-decoration: line-through; /* 添加删除线表示原价 */
}

</style>
