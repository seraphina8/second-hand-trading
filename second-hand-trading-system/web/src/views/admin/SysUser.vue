<template>
    <div>
        <admin-page>
            <template v-slot:searchForm>
                <el-form ref="searchForm" :model="searchForm" inline>
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="searchForm.username" clearable placeholder="请输入用户名"></el-input>
                    </el-form-item>
                    <el-form-item label="邮箱" prop="email">
                        <el-input v-model="searchForm.email" clearable placeholder="请输入邮箱"></el-input>
                    </el-form-item>
                    <el-form-item label="手机号" prop="mobile">
                        <el-input v-model="searchForm.mobile" clearable placeholder="请输入手机号"></el-input>
                    </el-form-item>
                    <el-form-item label="性别" prop="sex">
                        <el-select v-model="searchForm.sex" placeholder="请选择" clearable>
                            <el-option
                                v-for="item in sexList"
                                :key="item.value"
                                :label="item.name"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="状态" prop="status">
                        <el-select v-model="searchForm.status" placeholder="请选择" clearable>
                            <el-option
                                v-for="item in statusList"
                                :key="item.value"
                                :label="item.name"
                                :value="item.value">
                            </el-option>
                        </el-select>
                    </el-form-item>
                    <el-form-item label="角色" prop="roleId">
                        <el-select v-model="searchForm.roleId" placeholder="请选择" clearable>
                            <el-option
                                v-for="item in roleList"
                                :key="item.id"
                                :label="item.name"
                                :value="item.id">
                            </el-option>
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
                <el-button type="danger" icon="el-icon-remove-outline" @click="batchDelete(null)"
                           v-show="selectionRows.length">批量删除
                </el-button>
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
                    <el-table-column prop="username" label="用户名"></el-table-column>
                    <el-table-column prop="img" label="头像">
                        <template slot-scope="scope">
                            <el-image v-if="scope.row.img" style="width: 50px; height: 50px"
                                      :src="JSON.parse(scope.row.img)[0].url"
                                      :preview-src-list="[JSON.parse(scope.row.img)[0].url]"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column prop="email" label="邮箱"></el-table-column>
                    <el-table-column prop="mobile" label="手机号"></el-table-column>
                    <el-table-column prop="address" label="地址"></el-table-column>
                    <el-table-column prop="sex" label="性别">
                        <template slot-scope="scope">
                            {{ sexList.find(x => x.value === scope.row.sex)?.name }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="status" label="状态">
                        <template slot-scope="scope">
                            {{ statusList.find(x => x.value === scope.row.status)?.name }}
                        </template>
                    </el-table-column>
                    <el-table-column prop="role.name" label="角色"></el-table-column>
                    <el-table-column prop="createTime" label="创建时间"></el-table-column>
                    <el-table-column label="操作" width="300" fixed="right">
                        <template slot-scope="scope">
                            <el-button icon="el-icon-edit-outline" @click="handleEdit(scope.$index, scope.row)">编辑
                            </el-button>
                            <el-button icon="el-icon-edit-outline" @click="resetPassword(scope.row)">密码重置
                            </el-button>
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
            <div class="demo-drawer_content common-drawer" style="padding-right: 40px;box-sizing: border-box">
                <el-form :model="formData" label-width="100px" class="demo-ruleForm">
                    <slot name="content">
                        <el-form-item label="用户名">
                            <el-input v-model="formData.username"></el-input>
                        </el-form-item>
                        <el-form-item label="头像">
                            <MyUpLoad type="imageCar" :limit="1" :files="formData.img" @setFiles="formData.img =$event"
                                      v-if="isDrawerDialog"></MyUpLoad>
                        </el-form-item>
                        <el-form-item label="用户密码" v-if="!formData.id">
                            <el-input v-model="formData.password"></el-input>
                        </el-form-item>
                        <el-form-item label="个人简介">
                            <el-input v-model="formData.content"></el-input>
                        </el-form-item>
                        <el-form-item label="邮箱">
                            <el-input v-model="formData.email"></el-input>
                        </el-form-item>
                        <el-form-item label="手机号">
                            <el-input v-model="formData.mobile"></el-input>
                        </el-form-item>
                        <el-form-item label="地址">
                            <el-input v-model="formData.address"></el-input>
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-radio v-model="formData.sex" :label=item.value v-for="item in sexList">{{
                                    item.name
                                }}
                            </el-radio>
                        </el-form-item>
                        <el-form-item label="状态">
                            <el-radio v-model="formData.status" :label=item.value v-for="item in statusList">
                                {{ item.name }}
                            </el-radio>
                        </el-form-item>
                        <el-form-item label="角色">
                            <el-select v-model="formData.roleId" placeholder="请选择">
                                <el-option
                                    v-for="item in roleList"
                                    :key="item.id"
                                    :label="item.name"
                                    :value="item.id">
                                </el-option>
                            </el-select>
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
                sex: null,
                status: null,
                roleId: null
            },
            formData: {},
            selectionRows: [],
            sexList: [
                {name: '男', value: 0},
                {name: '女', value: 1}
            ],
            statusList: [
                {name: '启用', value: 1},
                {name: '禁用', value: 0}
            ],
            roleList: []
        }
    },

    created() {
        this.getList()
        this.getRoleList()
    },
    methods: {
        //获取分页数据
        getList() {
            let data = Object.assign(this.searchForm, this.pageInfo)
            this.request.get("/sysUser/page", {
                params: data
            }).then(res => {
                this.tableData = res.data.list
                this.total = res.data.total
            })
        },
        getRoleList() {
            this.request.get("/sysRole/list").then(res => {
                this.roleList = res.data
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
                this.request.post("/sysUser", this.formData).then(res => {
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
                this.request.put("/sysUser", this.formData).then(res => {
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
            console.log(rows)
            let ids = rows.map(item => item.id);
            this.$confirm(`此操作将永久删除ID为[${ids}]的数据, 是否继续?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
                center: true
            }).then(() => {
                //批量删除数据
                this.request.delete("/sysUser", {data: ids}).then(res => {
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
        resetPassword(row) {
            this.request.put("/sysUser/resetPassword/" + row.id).then(res => {
                console.log(res)
                if (res.code === 200) {
                    this.isDrawerDialog = false
                    this.$message({
                        message: res.msg,
                        type: 'success'
                    });
                }
            })
        },
    }
};
</script>

<style scoped>

</style>
