<template>
    <el-submenu v-if="menu.children && menu.children.length > 0" :key="menu.id" :index="menu.address">
        <template slot="title">
            <i :class="menu.icon"></i><span>{{ menu.name }}</span>
        </template>
        <RecursiveMenu v-for="child in menu.children" :menu="child" :key="child.id"/>
    </el-submenu>
    <!-- 如果是外部链接 -->
    <el-menu-item v-else-if="menu.outsideChain === 1" :key="menu.id" @click.native="openUrl">
        <i :class="menu.icon"></i><span slot="title">{{ menu.name }}</span>
    </el-menu-item>
    <!-- 如果是内部链接 -->
    <el-menu-item v-else :index="'/admin/'+menu.address" :key="menu.id">
      <i :class="menu.icon"></i><span slot="title">{{ menu.name }}</span>
    </el-menu-item>

</template>

<script>
export default {
    name: 'RecursiveMenu',
    props: {
        menu: {
            type: Object,
            required: true
        }
    },
    created() {
        console.log("渲染了")
    },
    methods: {
        openUrl() {
            window.open(this.menu.address, '_blank')
        }
    }
}
</script>
