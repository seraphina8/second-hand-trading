<template>
  <div>
    <admin-page>
      <template v-slot:searchForm>
        <el-form ref="searchForm" :model="searchForm" inline>
          <el-form-item label="买家" prop="userName">
            <el-input v-model="searchForm.userName"></el-input>
          </el-form-item>
          <el-form-item label="卖家" prop="sellerName">
            <el-input v-model="searchForm.sellerName"></el-input>
          </el-form-item>
          <el-form-item label="商品" prop="productName">
            <el-input v-model="searchForm.productName"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="searchForm.status" placeholder="请选择" clearable>
              <el-option :label=item.name :value=item.id :key=item.id v-for="item in statusList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="发货方式" prop="mode">
            <el-select v-model="searchForm.mode" placeholder="请选择" clearable>
              <el-option :label=item.name :value=item.id :key=item.id v-for="item in modeList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="收货地址" prop="shippingAddress">
            <el-input v-model="searchForm.shippingAddress"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="searchForm.remark"></el-input>
          </el-form-item>
          <el-form-item label="付款交易号" prop="payTransactionNumber">
            <el-input v-model="searchForm.payTransactionNumber"></el-input>
          </el-form-item>
          <el-form-item label="订单编号" prop="number">
            <el-input v-model="searchForm.number"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜 索</el-button>
            <el-button icon="el-icon-refresh-left" @click="resetForm">重 置</el-button>
          </el-form-item>
        </el-form>
      </template>
      <template v-slot:table-button>
        <el-button type="danger" icon="el-icon-remove-outline" @click="batchDelete(null)" v-show="selectionRows.length">批量删除</el-button>
      </template>
      <template v-slot:table>
        <el-table ref="multipleTable"
                  :data="tableData"
                  stripe
                  tooltip-effect="dark"
                  header-cell-class-name="table-header"
                  @selection-change="selectionChange"
                  style="width: 100%;">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" width="50"></el-table-column>
          <el-table-column prop="userName" label="买家"></el-table-column>
          <el-table-column prop="sellerName" label="卖家"></el-table-column>
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
          <el-table-column prop="shippingAddress" label="收货地址"></el-table-column>
          <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
          <el-table-column prop="payTransactionNumber" label="付款交易号"></el-table-column>
          <el-table-column prop="number" label="订单编号"></el-table-column>
          <el-table-column prop="trackingNumber" label="物流单号"></el-table-column>
          <el-table-column prop="createTime" label="下单时间"></el-table-column>
          <el-table-column prop="payTime" label="付款时间"></el-table-column>
          <el-table-column prop="deliverTime" label="发货时间"></el-table-column>
          <el-table-column prop="successTime" label="成交时间"></el-table-column>

          <el-table-column label="操作" width="300" fixed="right">
            <template slot-scope="scope">
              <el-button type="primary" @click="handleDeliver(scope.row.id)" v-if="scope.row.status===2">发货</el-button>
              <el-button icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button type="danger" icon="el-icon-remove-outline" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

      </template>
      <template v-slot:table-page>
        <el-pagination
            @current-change="currentChange"
            @size-change="sizeChange"
            :page-size="pageInfo.pageSize"
            :current-page="pageInfo.currentPage"
            background
            layout=" prev, pager, next,total ,sizes"
            :total="total">
        </el-pagination>
      </template>
    </admin-page>
    <el-drawer
        :title="formData.id===undefined?'新增':'编辑'"
        :visible.sync="isDrawerDialog"
        size="30%"
        direction="rtl"
        :wrapper-closable="false"
    >
      <div class="demo-drawer_content common-drawer" style="padding-right: 40px;box-sizing: border-box">
        <el-form :model="formData" label-width="100px" class="demo-ruleForm">
          <slot name="content">
            <el-form-item label="收货地址">
              <el-input v-model="formData.shippingAddress"></el-input>
            </el-form-item>
            <el-form-item label="备注">
              <el-input type="textarea" rows="5" v-model="formData.remark"></el-input>
            </el-form-item>
          </slot>
        </el-form>
      </div>
      <div class="demo-drawer_footer footer common-drawer-footer">
        <el-button @click="isDrawerDialog=false">取消</el-button>
        <el-button type="primary" @click="submit">提交</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>
import ProductItem from "@/views/front/ProductItem.vue";
import AdminPage from "@/views/layout/AdminPage.vue";

export default {
  components: {
    ProductItem,
    AdminPage
  },
  data() {
    return {
      //抽屉控制
      isDrawerDialog: false,
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
      searchForm: {type: "out"},
      selectionRows: [],
      statusList: [{name: '待付款', id: 1}, {name: '待发货', id: 2}, {name: '已发货', id: 3}, {name: '交易成功', id: 4}],
      modeList: [{name: '邮寄', id: 1}, {name: '自提', id: 2}],

      formData: {}
    }
  },

  created() {
    this.getList()
  },
  methods: {
    //获取分页数据
    getList() {
      let data = Object.assign(this.searchForm, this.pageInfo)
      this.request.get("/tOrder/page", {
        params: data
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    resetForm() {
      this.$refs['searchForm'].resetFields();
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
    //搜索
    handleSearch() {
      this.pageInfo.pageNum = 1
      this.getList()
    },
    //提交数据
    submit() {
      //新增
      if (this.formData.id === undefined) {
        this.request.post("/tOrder", this.formData).then(res => {
          if (res.code === 200) {
            this.isDrawerDialog = false
            this.$message({
              message: res.msg,
              type: 'success'
            });
            this.getList()
          }
        })
      } else {
        //更新
        this.request.put("/tOrder", this.formData).then(res => {
          if (res.code === 200) {
            this.isDrawerDialog = false
            this.$message({
              message: res.msg,
              type: 'success'
            });
            this.getList()
          }
        })
      }
    },
    //添加数据
    handleAdd() {
      this.formData = {}
      this.isDrawerDialog = true
    },
    //编辑
    handleEdit(index, row) {
      this.formData = Object.assign({}, row)
      this.isDrawerDialog = true
    },
    selectionChange(rows) {
      this.selectionRows = rows
    },
    //单个删除
    handleDelete(index, row) {
      this.batchDelete([row])
    },
    //批量删除
    batchDelete(rows) {
      if (!rows) {
        rows = this.selectionRows;
      }
      let ids = rows.map(item => item.id);
      this.$confirm(`此操作将永久删除ID为[${ids}]的数据, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        //批量删除数据
        this.request.delete("/tOrder", {data: ids}).then(res => {
          if (res.code === 200) {
            this.$message({
              message: res.msg,
              type: 'success'
            });
            this.getList()
          }
        })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
        this.$refs.multipleTable.clearSelection();
      });
    },

    handleDeliver(id) {
      this.$prompt('请填写物流单号(自提不需要填写)', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
      }).then(({value}) => {
        this.request.put("/tOrder/deliver", {
          id: id,
          trackingNumber: value
        }).then(res => {
          if (res.code === 200) {
            this.$message({
              message: res.msg,
              type: 'success'
            });
            this.getList()
          }
        })
      })
    },

  }
};
</script>

<style scoped>

</style>
