<template>
  <div>
    <div class="parent">
      <div class="header">
        <img src="@/assets/logo.jpg" alt=""   style="text-align: left;font-size:20px;padding-left: 200px; height:40px;margin-top: 5px">
      </div>
      <div class="content">
        <div class="content-layout">
          <router-view/>
        </div>
      </div>
<!--      <div class="footer">-->
<!--        <p></p>-->
<!--        <p></p>-->
<!--      </div>-->
    </div>
  </div>
</template>
<script>

export default {
  data() {
    return {}
  },
  methods: {

  }
}
</script>
<style scoped>

.parent {
  display: flex;
  flex-direction: column;
  height: 100vh; /* 假设父元素占据整个视口高度 */
}
.header {
  /* header的高度 */
  height: 50px;
}

.content {
  flex-grow: 1; /* 占据剩余空间 */
  background: url("../../assets/login.png") 100% 100%;
  background-repeat: no-repeat; /* 防止图片平铺 */
  background-position: center center; /* 图片居中，如果需要拉伸则此行可省略 */
  background-size: 100% 100%; /* 或者简写为 background-size: 100%; 使图片拉伸填满整个div */
  //position: absolute; /* 如果需要div覆盖整个可视窗口 */
  //top: 0;
  //left: 0;
  //right: 0;
  //bottom: 0;

}

.content-layout {
  width: 1200px;
  margin: 0 auto;
  position: relative;
  z-index: 2;
}

.footer {
  margin: 5px 7.5px;
  /* footer的高度 */
  height: 50px;
  text-align: center;
}
</style>

