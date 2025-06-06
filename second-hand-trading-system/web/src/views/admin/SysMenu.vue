<template>
  <div>
    <admin-page>
      <template v-slot:searchForm>
        <el-form ref="searchForm" :model="searchForm" inline>
          <el-form-item label="名称" prop="name">
            <el-input v-model="searchForm.name" clearable placeholder="请输入名称"></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="status">
            <el-select clearable v-model="searchForm.status">
              <el-option label="启用" :value=1></el-option>
              <el-option label="禁用" :value=0></el-option>
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
      </template>
      <template v-slot:table>
        <el-table ref="multipleTable"
                  :data="tableData"
                  stripe
                  header-cell-class-name="table-header"
                  tooltip-effect="dark"
                  row-key="id"
                  default-expand-all
                  :tree-props="{children: 'children', hasChildren: 'hasChildren'}"
                  style="width: 100%;margin-bottom: 20px;">
          <el-table-column prop="name" label="名称"></el-table-column>
          <el-table-column prop="type" label="菜单类型" width="80">
            <template slot-scope="scope">
              <el-tag type="" v-if="scope.row.type===1">目录</el-tag>
              <el-tag type="info" v-else>页面</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="icon" label="图标" width="80">
            <template slot-scope="scope">
              <i :class="scope.row.icon"></i>
            </template>
          </el-table-column>
          <el-table-column prop="sort" label="排序" width="80"></el-table-column>
          <el-table-column prop="outsideChain" label="外链" width="80">
            <template slot-scope="scope">
              <el-tag type="success" v-if="scope.row.outsideChain===1">是</el-tag>
              <el-tag type="danger" v-else>否</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="address" label="路由地址"></el-table-column>
          <el-table-column prop="component" label="组件地址"></el-table-column>
          <el-table-column prop="status" label="状态" width="80">
            <template slot-scope="scope">
              <el-tag :type="scope.row.status===1?'success':'danger'">{{ scope.row.status === 1 ? '启用' : '禁用' }}</el-tag>
            </template>
          </el-table-column>
          <el-table-column prop="createTime" label="创建时间"></el-table-column>
          <el-table-column label="操作" width="300" fixed="right">
            <template slot-scope="scope">
              <el-button icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button type="primary" icon="el-icon-plus" @click="handleAdd(scope.row.id)">新增</el-button>
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
        v-if="isDrawerDialog"
        size="30%"
        direction="rtl"
        :wrapper-closable="false"
    >
      <div class="demo-drawer_content" style="padding-right: 40px;box-sizing: border-box">
        <el-form :model="formData" label-width="100px" class="demo-ruleForm">
          <slot name="content">
            <el-form-item label="上级菜单">
              <el-tree
                  ref="menuTree"
                  :data="tableData"
                  :props="defaultProps"
                  node-key="id"
                  check-on-click-node
                  default-expand-all
                  :current-node-key="formData.parentId"
                  highlight-current
                  @node-click="handleNodeClick"></el-tree>
            </el-form-item>
            <el-form-item label="菜单类型">
              <el-radio v-model="formData.type" :label=1>目录</el-radio>
              <el-radio v-model="formData.type" :label=2>页面</el-radio>
            </el-form-item>
            <el-form-item label="图标">
              <icon-picker v-model="formData.icon"/>
            </el-form-item>
            <el-form-item label="名称">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item label="排序">
              <el-input v-model="formData.sort"></el-input>
            </el-form-item>
            <el-form-item label="外链">
              <el-radio v-model="formData.outsideChain" :label=1>是</el-radio>
              <el-radio v-model="formData.outsideChain" :label=0>否</el-radio>
            </el-form-item>
            <el-form-item label="路由地址">
              <el-input v-model="formData.address"></el-input>
            </el-form-item>
            <el-form-item label="组件地址">
              <el-input v-model="formData.component"></el-input>
            </el-form-item>
            <el-form-item label="状态">
              <el-radio v-model="formData.status" :label=1>启用</el-radio>
              <el-radio v-model="formData.status" :label=0>禁用</el-radio>
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
        name: "",
        status: ""
      },
      formData: {},
      defaultProps: {
        children: 'children',
        label: 'name'
      }
    }
  },

  created() {
    this.getList()
  },
  methods: {
    //获取分页数据
    getList() {

      this.request.get("/sysMenu/tree", {
        params: this.searchForm
      }).then(res => {
        this.tableData = res.data
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
        this.request.post("/sysMenu", this.formData).then(res => {
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
        this.request.put("/sysMenu", this.formData).then(res => {
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
    handleAdd(id) {
      this.formData = {}
      if (id) {
        this.formData.parentId = id;
      }
      this.isDrawerDialog = true
    },
    //编辑
    handleEdit(index, row) {
      this.formData = Object.assign({}, row)
      this.isDrawerDialog = true
      // this.$nextTick(() => {
      //   console.log([this.formData.parentId])
      //   this.$refs.menuTree.setCheckedKeys([this.formData.parentId]);
      // });
    },
    //单个删除
    handleDelete(index, row) {
      this.batchDelete([row])
    },
    //批量删除
    batchDelete(rows) {
      if (!rows) {
        rows = this.$refs.multipleTable.selection;
      }
      let ids = rows.map(item => item.id);
      this.$confirm(`此操作将永久删除ID为[${ids}]的数据, 是否继续?`, '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        //批量删除数据
        this.request.delete("/sysMenu", {data: ids}).then(res => {
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
    handleNodeClick(data) {
      this.formData.parentId = data.id
    }

  }
};
</script>

<style scoped>

</style>
