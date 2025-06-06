<template>
  <div>
    <div class="pb10px">
      <el-form ref="searchForm" :model="searchForm" inline class="common-search-form">
        <el-form-item label="发布人" prop="userId">
          <el-input v-model="searchForm.userId"></el-input>
        </el-form-item>
        <el-form-item label="卖家" prop="sellerId">
          <el-input v-model="searchForm.sellerId"></el-input>
        </el-form-item>
        <el-form-item label="商品" prop="productId">
          <el-input v-model="searchForm.productId"></el-input>
        </el-form-item>
        <el-form-item label="创建时间" prop="createTime">
          <el-input v-model="searchForm.createTime"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜 索</el-button>
          <el-button icon="el-icon-refresh-left" @click="resetForm">重 置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="common-table-button">
      <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
      <el-button type="danger" icon="el-icon-remove-outline" @click="batchDelete(null)" v-show="selectionRows.length">批量删除</el-button>
    </div>
    <el-table ref="multipleTable"
              :data="tableData"

              tooltip-effect="dark"
              header-cell-class-name="table-header"
              @selection-change="selectionChange"
              style="width: 100%;">
      <el-table-column type="selection" width="55"></el-table-column>
      <el-table-column prop="id" label="ID" width="50"></el-table-column>
      <el-table-column prop="userId" label="发布人"></el-table-column>
      <el-table-column prop="sellerId" label="卖家"></el-table-column>
      <el-table-column prop="productId" label="商品"></el-table-column>
      <el-table-column prop="createTime" label="创建时间"></el-table-column>

      <el-table-column label="操作" width="200" fixed="right">
        <template slot-scope="scope">
          <el-button icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button type="danger" icon="el-icon-remove-outline" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
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
    <!--    <el-drawer-->
    <!--        :title="formData.id===undefined?'新增':'编辑'"-->
    <!--        :visible.sync="isDrawerDialog"-->
    <!--        size="50%"-->
    <!--        direction="rtl"-->
    <!--        :wrapper-closable="false"-->
    <!--        v-if="isDrawerDialog"-->
    <!--    >-->
    <!--    -->
    <!--    </el-drawer>-->

    <ChatRecordDetail v-if="isDrawerDialog" :chatRecordId="formData.id" @close="isDrawerDialog=false"></ChatRecordDetail>

  </div>
</template>

<script>
import ChatRecordDetail from "@/views/front/ChatRecordDetail.vue";

export default {
  components: {
    ChatRecordDetail
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
      searchForm: {},
      selectionRows: [],

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
      this.request.get("/chatRecord/page", {
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
        this.request.post("/chatRecord", this.formData).then(res => {
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
        this.request.put("/chatRecord", this.formData).then(res => {
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
        this.request.delete("/chatRecord", {data: ids}).then(res => {
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
