<template>
  <div>
    <admin-page>
      <template v-slot:searchForm>
        <el-form ref="searchForm" :model="searchForm" inline>
          <el-form-item label="名称" prop="name">
            <el-input v-model="searchForm.name" clearable placeholder="请输入角色名称"></el-input>
          </el-form-item>
          <el-form-item label="描述" prop="content">
            <el-input v-model="searchForm.content" clearable placeholder="请输入角色描述"></el-input>
          </el-form-item>
          <el-form-item label="代码" prop="code">
            <el-input v-model="searchForm.code" clearable placeholder="请输入角色字段"></el-input>
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
                  style="width: 100%">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" width="50"></el-table-column>
          <el-table-column prop="name" label="名称"></el-table-column>
          <el-table-column prop="content" label="描述"></el-table-column>
          <el-table-column prop="code" label="代码"></el-table-column>
          <el-table-column label="操作" width="200" fixed="right">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button type="danger" icon="el-icon-remove-outline"
                         @click="handleDelete(scope.$index, scope.row)">删除
              </el-button>
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
      <div class="demo-drawer_content" style="padding-right: 40px;box-sizing: border-box">
        <el-form :model="formData" label-width="100px" class="demo-ruleForm">
          <slot name="content">
            <el-form-item label="名称">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item label="描述">
              <el-input v-model="formData.content"></el-input>
            </el-form-item>
            <el-form-item label="代码">
              <el-input v-model="formData.code"></el-input>
            </el-form-item>
            <el-form-item label="菜单权限">
              <el-tree
                  :data="menuTree"
                  :props="{children: 'children', label: 'name'}"
                  show-checkbox
                  check-strictly
                  node-key="id"
                  ref="menuTree"
                  highlight-current
              ></el-tree>
            </el-form-item>
          </slot>
        </el-form>
      </div>
      <div class="demo-drawer_footer footer">
        <el-button @click="isDrawerDialog=false">取消</el-button>
        <el-button type="primary" @click="submit">提交</el-button>
      </div>
    </el-drawer>
  </div>
</template>

<script>

import AdminPage from "@/views/layout/AdminPage.vue";

export default {
  components: {
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
        name: ""
      },
      selectionRows: [],
      formData: {},
      menuTree: []
    }
  },

  created() {
    this.getList()
    this.getMenuTree()
  },
  methods: {
    //获取分页数据
    getList() {
      let data = Object.assign(this.searchForm, this.pageInfo)
      this.request.get("/sysRole/page", {
        params: data
      }).then(res => {
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    getMenuTree() {
      this.request.get("/sysMenu/tree").then(res => {
        this.menuTree = res.data
      })
    },
    getMenuTreeCheckedKeys() {
      this.formData.menuIds = this.$refs.menuTree.getCheckedKeys()
    },
    setMenuTreeCheckedKeys() {
      this.$refs.menuTree.setCheckedKeys(this.formData.menuIds);
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
      this.getMenuTreeCheckedKeys()
      if (this.formData.id === undefined) {
        this.request.post("/sysRole", this.formData).then(res => {
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
        this.request.put("/sysRole", this.formData).then(res => {
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
      this.$nextTick(() => {
        this.setMenuTreeCheckedKeys()
      });
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
        this.request.delete("/sysRole", {data: ids}).then(res => {
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
