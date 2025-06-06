<template>
  <div class="component-wrapper">
    <div class="searchForm">
      <slot name="searchForm"></slot>
    </div>
    <div class="table-button">
      <slot class="table-button" name="table-button"></slot>
    </div>
    <div class="table">
      <slot class="table" name="table"></slot>
    </div>
    <div class="table-page">
      <slot class="table-page" name="table-page"></slot>
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
.table {
  margin-top: 10px;
}

.table-page {
  margin-top: 10px;
}
</style>