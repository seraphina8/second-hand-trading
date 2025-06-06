<template>
  <div>
    <admin-page>
      <template v-slot:searchForm>
        <el-form ref="searchForm" :model="searchForm" inline>
          <el-form-item label="标题" prop="name">
            <el-input v-model="searchForm.name"></el-input>
          </el-form-item>
          <el-form-item label="说明" prop="content">
            <el-input v-model="searchForm.content"></el-input>
          </el-form-item>
          <el-form-item label="分类" prop="productClassifyId">
            <el-select v-model="searchForm.productClassifyId" placeholder="请选择" clearable>
              <el-option :label=item.name :value=item.id v-for="item in productClassifyIdList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="成色" prop="productFinenessId">
            <el-select v-model="searchForm.productFinenessId" placeholder="请选择" clearable>
              <el-option :label=item.name :value=item.id v-for="item in productFinenessIdList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select v-model="searchForm.status" placeholder="请选择" clearable>
              <el-option :label=item.name :value=item.id v-for="item in statusList"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="">
            <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜 索</el-button>
            <el-button icon="el-icon-refresh-left" @click="resetForm">重 置</el-button>
          </el-form-item>
        </el-form>
      </template>
      <template v-slot:table-button>
        <el-button type="primary" icon="el-icon-plus" @click="handleAdd">新增</el-button>
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
          <el-table-column prop="name" label="标题"></el-table-column>
          <el-table-column prop="content" label="说明" show-overflow-tooltip></el-table-column>
          <el-table-column prop="productClassifyId" label="分类">
            <template slot-scope="scope">
              {{ productClassifyIdList.find(x => x.id === scope.row.productClassifyId)?.name }}
            </template>
          </el-table-column>
          <el-table-column prop="productFinenessId" label="成色">
            <template slot-scope="scope">
              {{ productFinenessIdList.find(x => x.id === scope.row.productFinenessId)?.name }}
            </template>
          </el-table-column>
          <el-table-column prop="originalPrice" label="原价"></el-table-column>
          <el-table-column prop="price" label="价格"></el-table-column>
          <el-table-column prop="status" label="状态">
            <template slot-scope="scope">
              {{ statusList.find(x => x.id === scope.row.status)?.name }}
            </template>
          </el-table-column>
          <el-table-column prop="userName" label="发布人"></el-table-column>
          <el-table-column prop="pageView" label="浏览量"></el-table-column>
          <el-table-column prop="want" label="想要"></el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>

          <el-table-column label="操作" width="200" fixed="right">
            <template slot-scope="scope">
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
        <el-form :model="formData" label-width="100px" class="demo-ruleForm" :disabled="formData.status===3">
          <slot name="content">
            <el-form-item label="标题">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item label="说明">
              <el-input type="textarea" rows="10" v-model="formData.content"></el-input>
            </el-form-item>
            <el-form-item label="图片">
              <MyUpLoad type="image" :files="formData.img" @setFiles="formData.img =$event" v-if="isDrawerDialog"></MyUpLoad>
            </el-form-item>
            <el-form-item label="视频">
              <MyUpLoad type="video" :files="formData.video" @setFiles="formData.video =$event" v-if="isDrawerDialog"></MyUpLoad>
            </el-form-item>
            <el-form-item label="分类">
              <el-select v-model="formData.productClassifyId" placeholder="请选择">
                <el-option :label=item.name :value=item.id v-for="item in productClassifyIdList"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="成色">
              <el-select v-model="formData.productFinenessId" placeholder="请选择">
                <el-option :label=item.name :value=item.id v-for="item in productFinenessIdList"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="原价">
              <el-input v-model="formData.originalPrice"></el-input>
            </el-form-item>
            <el-form-item label="价格">
              <el-input v-model="formData.price"></el-input>
            </el-form-item>
            <el-form-item label="状态" v-if="formData.id">
              <el-radio v-model="formData.status" :label=item.id v-for="item in statusList" :disabled="item.id===3">{{ item.name }}</el-radio>
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
import MyUpLoad from "@/components/MyUpload.vue";
import Utils from "@/utils/utils";
import AdminPage from "@/views/layout/AdminPage.vue";

export default {
  components: {
    MyUpLoad,
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
      searchForm: {
        type: 'my',
      },
      selectionRows: [],
      productClassifyIdList: [],
      productFinenessIdList: [],
      statusList: [{name: '上架', id: 1}, {name: '下架', id: 2}, {name: '已卖出', id: 3}],

      formData: {}
    }
  },

  created() {
    this.getList()
    this.getProductClassifyIdList()
    this.getProductFinenessIdListList()
  },
  methods: {
    //获取分页数据
    getList() {
      let data = Utils.cloneDeep(Object.assign(this.searchForm, this.pageInfo))
      this.request.get("/product/page", {
        params: data
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    getProductClassifyIdList() {
      this.request.get("/productClassify/list").then(res => {
        this.productClassifyIdList = res.data
      })
    },
    getProductFinenessIdListList() {
      this.request.get("/productFineness/list").then(res => {
        this.productFinenessIdList = res.data
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
        this.request.post("/product", this.formData).then(res => {
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
        this.request.put("/product", this.formData).then(res => {
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
      this.formData.status = 1
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
        this.request.delete("/product", {data: ids}).then(res => {
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
