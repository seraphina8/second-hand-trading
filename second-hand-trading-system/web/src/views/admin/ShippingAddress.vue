<template>
  <div>
    <admin-page>
      <template v-slot:searchForm>
        <el-form ref="searchForm" :model="searchForm" inline>
          <el-form-item label="收货人" prop="name">
            <el-input v-model="searchForm.name"></el-input>
          </el-form-item>
          <el-form-item label="电话" prop="tel">
            <el-input v-model="searchForm.tel"></el-input>
          </el-form-item>
          <el-form-item label="地址" prop="address">
            <el-input v-model="searchForm.address"></el-input>
          </el-form-item>
          <el-form-item label="默认地址" prop="isDefault">
            <el-select v-model="searchForm.isDefault" placeholder="请选择" clearable>
              <el-option :label=item.name :value=item.id :key=item.id v-for="item in isDefaultList"></el-option>
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
                  tooltip-effect="dark"
                  header-cell-class-name="table-header"
                  @selection-change="selectionChange"
                  style="width: 100%;">
          <el-table-column type="selection" width="55"></el-table-column>
          <el-table-column prop="id" label="ID" width="50"></el-table-column>
          <el-table-column prop="name" label="收货人"></el-table-column>
          <el-table-column prop="tel" label="电话"></el-table-column>
          <el-table-column prop="address" label="地址" show-overflow-tooltip></el-table-column>
          <el-table-column prop="isDefault" label="默认地址">
            <template slot-scope="scope">
              {{ isDefaultList.find(x => x.id === scope.row.isDefault)?.name }}
            </template>
          </el-table-column>
          <el-table-column prop="userName" label="用户"></el-table-column>
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
            layout="sizes, prev, pager, next"
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
        <!-- 确保 ref 属性名和代码里引用的一致 -->
        <el-form ref="formData" :model="formData" :rules="rules" label-width="100px" class="demo-ruleForm">
          <slot name="content">
            <el-form-item label="收货人" prop="name">
              <el-input v-model="formData.name"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="tel">
              <el-input v-model="formData.tel"></el-input>
            </el-form-item>
            <el-form-item label="地址" prop="address">
              <el-input type="textarea" rows="10" v-model="formData.address"></el-input>
            </el-form-item>
            <el-form-item label="默认地址">
              <el-radio v-model="formData.isDefault" :label=item.id :key=item.id v-for="item in isDefaultList">{{ item.name }}</el-radio>
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
import AdminPage from "@/views/layout/AdminPage.vue";

// 定义电话验证规则
const validateTel = (rule, value, callback) => {
  const reg = /^1[3-9]\d{9}$/;
  if (!value) {
    callback(new Error('电话不能为空'));
  } else if (!reg.test(value)) {
    callback(new Error('请输入有效的手机号'));
  } else {
    callback();
  }
};

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
      searchForm: {},
      selectionRows: [],
      isDefaultList: [{name: '是', id: 1}, {name: '否', id: 0}],

      formData: {},
      rules: {
        name: [
          { required: true, message: '收货人不能为空', trigger: 'blur' },
          { max: 20, message: '收货人长度不能超过 20 个字符', trigger: 'blur' }
        ],
        tel: [
          { validator: validateTel, trigger: 'blur' }
        ],
        address: [
          { required: true, message: '地址不能为空', trigger: 'blur' },
          { max: 200, message: '地址长度不能超过 200 个字符', trigger: 'blur' }
        ]
      }
    }
  },

  created() {
    this.getList()
  },
  methods: {
    //获取分页数据
    getList() {
      let data = Object.assign(this.searchForm, this.pageInfo)
      this.request.get("/shippingAddress/page", {
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
      if (this.$refs['formData']) {
        this.$refs['formData'].validate((valid) => {
          if (valid) {
            // 新增
            if (this.formData.id === undefined) {
              this.request.post("/shippingAddress/add", this.formData).then(res => {
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
              // 更新
              this.request.put("/shippingAddress/update", this.formData).then(res => {
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
          }
        });
      } else {
        console.error('表单引用未正确绑定，请检查 ref 属性');
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
        this.request.delete("/shippingAddress", {data: ids}).then(res => {
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
