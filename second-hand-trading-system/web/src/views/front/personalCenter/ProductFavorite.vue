<template>
  <div>
    <admin-page>
      <template v-slot:searchForm>
        <el-form ref="searchForm" :model="searchForm" inline>
          <el-form-item label="商品" prop="productName">
            <el-input v-model="searchForm.productName"></el-input>
          </el-form-item>
          <el-form-item label="">
            <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜 索</el-button>
            <el-button icon="el-icon-refresh-left" @click="resetForm">重 置</el-button>
          </el-form-item>
        </el-form>
      </template>
      <template v-slot:table>
        <el-table ref="multipleTable"
                  :data="tableData"

                  tooltip-effect="dark"
                  header-cell-class-name="table-header" style="width: 100%">
          <el-table-column prop="productId" label="商品" width="220">
            <template slot-scope="scope">
              <ProductItem :info="scope.row.product"></ProductItem>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>

          <el-table-column label="操作" width="200" fixed="right">
            <template slot-scope="scope">
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
            layout="sizes, prev, pager, next"
            :total="total">
        </el-pagination>
      </template>
    </admin-page>
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
      searchForm: {},
      selectionRows: [],
    }
  },

  created() {
    this.getList()
  },
  methods: {
    //获取分页数据
    getList() {
      let data = Object.assign(this.searchForm, this.pageInfo)
      this.request.get("/productFavorite/page", {
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
        this.request.delete("/productFavorite", {data: ids}).then(res => {
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

  }
};
</script>

<style scoped>

</style>
