<template>
  <div v-if="info.id">

    <el-alert
        v-if="info.status === 2"
        title="商品已下架"
        type="warning"
        effect="dark"
        :closable="false">
    </el-alert>
    <el-alert
        v-if="info.status === 3"
        title="商品已经卖出了"
        type="warning"
        effect="dark"
        :closable="false">
    </el-alert>
    <el-row :gutter="20" style="margin-top: 20px">
      <el-col :span="6">
        <el-image
            :src="Utils.getFileInfo(info.img)[0].url "
            :preview-src-list="[Utils.getFileInfo(info.img)[0].url]">
        </el-image>
      </el-col>
      <el-col :span="10">

        <el-row style="margin: 10px 0">
          <H4>{{ info.name }}</H4>
        </el-row>
        <el-row style="margin: 7.5px 0">
          <span class="price">{{ info.price }}</span>
          <span class="original-price">¥{{ info.originalPrice }}</span>
        </el-row>
        <el-row>
          <span style="color: #909399">{{ info.want }}人想要</span>
          <i class="el-icon-view" style="color: #909399; margin: 0 10px"> {{ info.pageView }}</i>
        </el-row>
        <el-row style="margin: 7.5px 0">
          <span style="color: #909399">成色：     {{ productFinenessIdList.find(x => x.id === info.productFinenessId)?.name }}</span>
        </el-row>
        <el-row :gutter="20" style="margin: 7.5px 0">
          <!--          <el-button type="primary" @click="play(info.id)">播放</el-button>-->
          <!--          <el-button type="info" @click="downloadProduct">下载</el-button>-->
          <el-button type="warning" @click="saveProductFavorite" v-if="!info.productFavorite">收藏</el-button>
          <el-button type="warning" @click="cancelProductFavorite" v-else>取消收藏</el-button>
          <el-button type="info" @click="consult">咨询</el-button>
          <el-button type="primary" @click="openBuy">购买</el-button>
        </el-row>
      </el-col>
    </el-row>
    <el-row :gutter="20" style="margin: 20px">
      <h3>详情</h3>
      <div style="margin: 10px 0"> {{ info.content }}</div>

      <div class="gallery">
        <div class="gallery-item" v-for="(item, index) in  Utils.getFileInfo(info.img)" :key="index">
          <el-image :src="item.url" fit="cover" :preview-src-list="[item.url]" style="height: 100%;width: 100%"></el-image>
        </div>
        <div class="gallery-item" v-for="(item, index) in  Utils.getFileInfo(info.video)" :key="index">

          <video width="320" height="240" controls loop muted>
            <source :src="item.url">
          </video>
        </div>
      </div>
    </el-row>
    <el-row :gutter="20" style="margin: 20px">
      <h3>留言</h3>
      <el-input
          style="margin: 10px 0"
          type="textarea"
          :rows="3"
          placeholder="请输入内容"
          v-model="productLeaveMessage.content">
      </el-input>
      <el-button type="primary" @click="saveProductLeaveMessage">发表</el-button>
    </el-row>
    <div v-for="item in productLeaveMessage.tableData" style="margin: 20px">


      <el-card :body-style="{ padding: '0px', marginBottom: '1px'}">
        <div style="float: left;margin: 10px;height: 100%">
          <el-avatar :src=" Utils.getFileInfo(item.userAvatarUrl)[0].url "></el-avatar>
        </div>
        <div style="padding: 14px;">
          <span>{{ item.userName }}</span>
          <br>
          <div style="margin:10px;word-wrap: break-word;clear: left">{{ item.content }}</div>
          <div style="float: right;margin-bottom: 5px">
            <span style="font-size: 12px">{{ item.createTime }}</span>
            <el-popconfirm v-if="user.id === item.userId"
                           class="ml-5"
                           confirm-button-text='确定'
                           cancel-button-text='我再想想'
                           icon="el-icon-info"
                           icon-color="red"
                           title="您确定删除吗？"
                           @confirm="delBatch"
            >
              <el-button type="text" slot="reference" @click="deleteProductFavorite(item.id)">删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>
          </div>
        </div>
      </el-card>
    </div>

    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="productLeaveMessage.pageNum"
          :page-size="productLeaveMessage.pageSize"
          layout="prev, pager, next"
          :total="productLeaveMessage.total">
      </el-pagination>
    </div>
    <el-dialog title="购买" :visible.sync="buy.dialogFormVisible" v-if="buy.dialogFormVisible" width="50%" :close-on-click-modal="false">
      <el-form :model="formData" label-width="100px" class="demo-ruleForm">
        <slot name="content">
          <!--          <el-form-item label="价格">-->
          <!--            <el-input v-model="buy.formData.price"></el-input>-->
          <!--          </el-form-item>-->
          <el-form-item label="发货方式">
            <el-radio v-model="buy.formData.mode" :label=item.id :key=item.id v-for="item in buy.modeList">{{ item.name }}</el-radio>
          </el-form-item>
          <el-form-item label="收货地址" v-if="buy.formData.mode===1">
            <el-radio v-model="buy.formData.shippingAddressId" :label=item.id :key=item.id v-for="item in buy.shippingAddressList">{{ convertShippingAddress(item) }}</el-radio>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" rows="5" v-model="buy.formData.remark"></el-input>
          </el-form-item>
        </slot>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitOrder" style="margin: 10px 0">提交</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>


import Utils from "@/utils/utils";
import {EventBus} from "@/main";

export default {
  name: "Product",
  components: {},
  data() {
    return {
      user: Utils.getUserInfo(),
      info: {},
      productLeaveMessage: {
        pageNum: 1,
        pageSize: 10,
        tableData: [],
        total: 0,
        content: ""
      },
      buy: {
        dialogFormVisible: false,
        formData: {},
        modeList: [{name: '邮寄', id: 1}, {name: '自提', id: 2}],
        shippingAddressList: [],
        data: []
      },
      productFinenessIdList: [],
    }
  },
  computed: {
    Utils() {
      return Utils
    },
    id() {
      return this.$route.params.id;
    }
  },
  created() {
    this.load()
    this.loadProductLeaveMessage()
    this.getProductFinenessIdListList()
  },
  methods: {
    load() {
      this.request.get("/product/" + this.id).then(res => {
        this.info = res.data
        console.log(this.info)
      })
      this.request.post("/productHistory", {productId: this.id}).then(res => {
      })
    },
    loadProductLeaveMessage() {
      this.request.get("/productLeaveMessage/page", {
        params: {
          pageNum: this.productLeaveMessage.pageNum,
          pageSize: this.productLeaveMessage.pageSize,
          productId: this.id,
        }
      }).then(res => {
        this.productLeaveMessage.tableData = res.data.list
        this.productLeaveMessage.total = res.data.total
      })
    },
    loadShippingAddress() {
      this.request.get("/shippingAddress/list").then(res => {
        this.buy.shippingAddressList = res.data
      })
    },
    getProductFinenessIdListList() {
      this.request.get("/productFineness/list").then(res => {
        this.productFinenessIdList = res.data
      })
    },
    convertShippingAddress(shippingAddress) {
      return shippingAddress.name + "-" + shippingAddress.tel + "-" + shippingAddress.address
    },

    handleSizeChange(pageSize) {
      this.productLeaveMessage.pageSize = pageSize
      this.loadProductLeaveMessage()
    },
    handleCurrentChange(pageNum) {
      this.productLeaveMessage.pageNum = pageNum
      this.loadProductLeaveMessage()
    },
    saveProductLeaveMessage() {
      this.request.post("/productLeaveMessage", {content: this.productLeaveMessage.content, productId: this.id}).then(res => {
        if (res.code === 200) {
          this.$message.success("评论成功")
          this.loadProductLeaveMessage()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    deleteProductFavorite(productLeaveMessageId) {
      this.request.delete("/productLeaveMessage/" + productLeaveMessageId).then(res => {
        if (res.code === 200) {
          this.$message.success("删除成功")
          this.loadProductLeaveMessage()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    saveProductFavorite() {
      this.request.post("/productFavorite", {productId: this.id}).then(res => {
        if (res.code === 200) {
          this.$message.success("收藏成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    cancelProductFavorite() {
      this.request.delete("/productFavorite/", {data: [this.info.productFavorite.id]}).then(res => {
        if (res.code === 200) {
          this.$message.success("取消收藏成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    consult() {
      this.request.post("/chatRoom", {productId: this.id}).then(res => {
        if (res.code === 200) {
          EventBus.$emit('openMessage', {id: res.data.id});
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    openBuy() {
      this.buy.formData.productId = this.id
      this.loadShippingAddress()
      this.buy.dialogFormVisible = true;
    },
    submitOrder() {
      this.buy.formData.productId = this.id
      if (this.buy.formData.mode === 1) {
        this.buy.formData.shippingAddress = this.convertShippingAddress(this.buy.shippingAddressList.find(item => item.id === this.buy.formData.shippingAddressId))
      }
      this.request.post("/tOrder", this.buy.formData).then(res => {
        if (res.code === 200) {
          this.isDrawerDialog = false
          this.$message({
            message: "下单成功",
            type: 'success'
          });
          this.$router.push("/front/tOrder")
        }
      })
    },


    saveProductScore() {
      if (this.productScore.score === 0) {
        this.$message.error("最低分数不能为0")
        return;
      }
      this.request.post("/productScore", {productId: this.id, score: this.productScore.score}).then(res => {
        if (res.code === 200) {
          this.$message.success("评分成功")
          this.loadProductScore()
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    addProductToUserSongList() {
      this.request.get("/userSongList/page", {
        params: {
          pageNum: 1,
          pageSize: 1000
        }
      }).then(res => {
        this.buy.data = res.data.records
        this.buy.dialogFormVisible = true;
      })
    },
    addProductToUserSongListSubmit() {
      this.request.post("/userSongListDetail", {
        userSongListId: this.buy.buyId,
        productId: this.id
      }).then(res => {
        if (res.code === 200) {
          this.$message.success("添加成功")
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    }

  }
}
</script>


<style scoped>
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


.gallery {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(200px, 1fr)); /* 自动填充列，每列最小宽度 200px，最大为可用空间的一等份 */
  grid-gap: 10px; /* 网格间隔 */
}

.gallery-item {
  /* 不需要额外的 flex 属性，因为已经使用了 grid 布局 */
}

.gallery-item img {
  /* 保持与 Flexbox 示例中相同的 img 样式 */
  width: 100%;
  height: auto;
  object-fit: cover;
}

.gallery-item video {
  /* 保持与 Flexbox 示例中相同的 img 样式 */
  width: 100%;
  height: auto;
  object-fit: cover;
}

</style>
