<template>
  <div>

      <el-form ref="searchForm" :model="searchForm" :rules="searchRules" inline>
        <el-form-item label="标题" prop="name">
          <el-input v-model="searchForm.name"></el-input>
        </el-form-item>
        <el-form-item label="说明" prop="content">
          <el-input v-model="searchForm.content"></el-input>
        </el-form-item>
        <el-form-item label="成色" prop="productFinenessId">
          <el-select v-model="searchForm.productFinenessId" placeholder="请选择" clearable>
            <el-option :label=item.name :value=item.id v-for="item in productFinenessIdList"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="">
          <el-button type="primary" icon="el-icon-search" @click="handleSearch">搜 索</el-button>
          <el-button icon="el-icon-refresh-left" @click="resetForm">重 置</el-button>
        </el-form-item>
      </el-form>

    <div style="">
      <el-tabs v-model="searchForm.productClassifyId" @tab-click="handleTypeClick" style="width: 100%">
        <el-tab-pane label="全部" name=""></el-tab-pane>
        <el-tab-pane :label="item.name" v-for="item in productClassifyIdList" :name="''+item.id"></el-tab-pane>
      </el-tabs>
      <el-row :gutter="10" v-for="(item, index) in tableData" key="index">
        <el-col v-for="(itemDetail, indexDetail) in item" :span="24/itemSize">
          <ProductCard :info="itemDetail" @changeAudio='$emit("changeAudio", itemDetail.id)'/>
        </el-col>
      </el-row>
    </div>


    <div class="common-table-pagination" style="margin-top: 20px">
      <el-pagination
          @current-change="currentChange"
          @size-change="sizeChange"
          :page-size="pageInfo.pageSize"
          :current-page="pageInfo.currentPage"
          layout="prev, pager, next"
          :total="total">
      </el-pagination>
    </div>

  </div>
</template>

<script>
import MyUpLoad from "@/components/MyUpload.vue";
import Utils from "@/utils/utils";
import ProductCard from "@/views/front/ProductCard.vue";

export default {
  computed: {
  },
  components: {
    MyUpLoad,
    ProductCard
  },
  data() {
    return {
      itemSize: 6,
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
        status: 1,
      },
      selectionRows: [],
      productClassifyIdList: [],
      productFinenessIdList: [],
      statusList: [{name: '上架', id: 1}, {name: '下架', id: 2}, {name: '已卖出', id: 3}],

      formData: {},
      // 将 searchRules 插入到这里
      searchRules: {
        name: [
          { required: true, message: '标题不能为空', trigger: 'blur' }
        ],
        content: [
          { required: true, message: '说明不能为空', trigger: 'blur' }
        ],
        productFinenessId: [
          { required: true, message: '请选择成色', trigger: 'change' }
        ]
      }
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
      if (this.searchForm.productClassifyId === "0") {
        delete data.productClassifyId;
      }
      this.request.get("/product/page", {
        params: data
      }).then(res => {
        let data = res.data.list
        this.tableData = []
        for (let i = 0; i < data.length / this.itemSize; i++) {
          let itemData = [];
          for (let j = 0; j < this.itemSize; j++) {
            let index = i * this.itemSize + j;
            console.log("i", i, "j", j, "index", index)
            if (index < data.length) {
              itemData.push(data[index]);
            }
          }
          this.tableData.push(itemData);
        }
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
      // 排除 status 字段后检查是否所有字段都为空
      const { status, ...searchParams } = this.searchForm;
      const isAllEmpty = Object.values(searchParams).every(value => {
        if (typeof value === 'string') {
          return value.trim() === '';
        }
        if (Array.isArray(value)) {
          return value.length === 0;
        }
        return !value;
      });

      if (isAllEmpty) {
        this.$message.error('搜索条件不能全为空');
        return;
      }

      this.pageInfo.pageNum = 1;
      this.getList();
    },
    handleTypeClick(tab, event) {
      this.getList()
    },

  }
};
</script>

<style scoped>
.category {
  position: fixed;
  left: 150px;
  top: 20%;
  cursor: pointer;
  z-index: 999;
}

.category li {
  line-height: 30px;
  background: skyblue;
  color: #fff;
  padding: 10px;
}

.headerBg {
  background: #eee !important;
}

</style>
