<template>
  <div class="flex bg-EBF0F8" v-loading="loading">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <div class="std-titlerange">
        <div class="std-pagetitle">文章</div>
      </div>
      <div class="margin-tb margin-lr-lg std-content">
        <!-- 顶部操作 -->
        <div class="std-content-top">
          <div></div>
          <div class="flex align-center">
            <el-button size="mini" @click="pageChange('/Artcle/Edit')">
              添加
            </el-button>
          </div>
        </div>
        <div class="std-content-center">
          <div class="padding-tb padding-lr-sm"></div>
        </div>
        <div class="std-content-bottom"></div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false,
    };
  },
  methods: {
    // 基础方法
    thStyleFun() {
      return "text-align:center";
    },
    cellStyleFun() {
      return "text-align:center";
    },
    getSideStatus() {
      return this.$store.state.side_exist;
    },
    pageChange(path) {
      this.$router.push(path);
    },
    // ===
  },
};
</script>

<style>
</style>