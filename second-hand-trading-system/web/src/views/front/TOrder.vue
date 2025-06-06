<template>
  <div>
    <el-tabs v-model="searchForm.status" @tab-click="handleStatusClick" style="width: 100%">
      <el-tab-pane :label="item.name" v-for="item in statusList" :name="''+item.id"></el-tab-pane>
    </el-tabs>

    <el-table ref="multipleTable"
              :data="tableData"

              tooltip-effect="dark"
              header-cell-class-name="table-header"
              style="width: 100%;">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="备注">
              <span>{{ props.row.remark }}</span>
            </el-form-item>
            <el-form-item label="付款交易号">
              <span>{{ props.row.payTransactionNumber }}</span>
            </el-form-item>
            <el-form-item label="物流单号">
              <span>{{ props.row.trackingNumber }}</span>
            </el-form-item>
            <el-form-item label="收货地址">
              <span>{{ props.row.shippingAddress }}</span>
            </el-form-item>
            <el-form-item label="付款时间">
              <span>{{ props.row.payTime }}</span>
            </el-form-item>
            <el-form-item label="发货时间">
              <span>{{ props.row.deliverTime }}</span>
            </el-form-item>
            <el-form-item label="成交时间">
              <span>{{ props.row.successTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>

      <el-table-column prop="number" label="订单编号"></el-table-column>
      <el-table-column prop="productId" label="商品" width="220">
        <template slot-scope="scope">
          <ProductItem :info="scope.row.product"></ProductItem>
        </template>
      </el-table-column>
      <el-table-column prop="price" label="价格"></el-table-column>
      <el-table-column prop="status" label="状态">
        <template slot-scope="scope">
          {{ statusList.find(x => x.id === scope.row.status)?.name }}
        </template>
      </el-table-column>
      <el-table-column prop="mode" label="发货方式">
        <template slot-scope="scope">
          {{ modeList.find(x => x.id === scope.row.mode)?.name }}
        </template>
      </el-table-column>
      <el-table-column prop="createTime" label="下单时间"></el-table-column>
      <el-table-column label="操作" width="200" fixed="right">
        <template slot-scope="scope">
          <el-popconfirm
              v-if="scope.row.status===3"
              class="ml-5"
              confirm-button-text='确定'
              cancel-button-text='我再想想'
              icon="el-icon-info"
              icon-color="red"
              title="您确定要收货吗？"
              @confirm="handleReceive(scope.row.id)"
          >
            <el-button type="success" slot="reference">确认收货</el-button>
          </el-popconfirm>
          <el-button type="primary"   v-if="scope.row.status===1"  @click="handlePay(scope.row.id)">支付</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="common-table-pagination" style="margin-top: 20px">
      <el-pagination
          @current-change="currentChange"
          @size-change="sizeChange"
          :page-size="pageInfo.pageSize"
          :current-page="pageInfo.currentPage"
          background
          layout="sizes, prev, pager, next"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import ProductItem from "@/views/front/ProductItem.vue";
import Utils from "@/utils/utils";

export default {
  components: {
    ProductItem
  },
  data() {
    return {
      //列表数据
      tableData: [],
      //总条数
      total: 0,
      //分页详情
      pageInfo: {
        //当前页
        pageNum: 1,
        //分页大小
        pageSize: 10
      },
      //搜索
      searchForm: {
        type: "in"
      },
      statusList: [{name: '全部', id: 0}, {name: '待付款', id: 1}, {name: '待发货', id: 2}, {name: '已发货', id: 3}, {name: '交易成功', id: 4}],
      modeList: [{name: '邮寄', id: 1}, {name: '自提', id: 2}],
    }
  },

  created() {
    this.getList()
  },
  methods: {
    //获取分页数据
    getList() {
      let data = Utils.cloneDeep(Object.assign(this.searchForm, this.pageInfo))
      if (this.searchForm.status === "0") {
        delete data.status;
      }
      console.log(this.searchForm)
      this.request.get("/tOrder/page", {
        params: data
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    //选择分页
    currentChange(e) {
      this.pageInfo.pageNum = e
      this.getList()
    },
    //改变分页数量
    sizeChange(e) {
      this.pageInfo.pageSize = e
      this.getList()
    },

    handleStatusClick(tab, event) {
      this.getList()
    },


    handleReceive(id) {
      this.request.put("/tOrder/receive/" + id).then(res => {
        if (res.code === 200) {
          this.$message({
            message: res.msg,
            type: 'success'
          });
          this.getList()
        }
      })
    },


    handlePay(id) {
      this.request.put("/tOrder/pay/" + id).then(res => {
        if (res.code === 200) {
          this.$message({
            message: res.msg,
            type: 'success'
          });
          this.getList()
        }
      })

    },
  }
};
</script>

<style scoped>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}
</style>
