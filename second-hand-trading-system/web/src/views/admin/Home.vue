<template>
    <div>
        <h3>系统通知</h3>
      <br>
        <el-collapse>
            <el-collapse-item v-for="item in sysInformList" :key="item.id">
                <template slot="title">
                    <span style="font-weight: bold">{{ item.name }}</span><i class="header-icon el-icon-info"></i>
                </template>
                <div>发布时间：{{item.createTime}}</div>
                <div>发布内容：{{ item.content }}</div>
            </el-collapse-item>
        </el-collapse>
    </div>
</template>
<script>
export default {
    data(){
        return {
          sysInformList:[]
        }
    },

    created(){
        this.getList()
    },
    methods:{
        getList() {
            this.request.get("/sysInform/list").then(res => {
                this.sysInformList = res.data
            })
        },
    }
}
</script>
