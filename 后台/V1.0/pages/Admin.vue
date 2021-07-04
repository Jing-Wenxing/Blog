<template>
  <div class="flex bg-EBF0F8">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <div class="std-titlerange">
        <div class="std-pagetitle">仪表盘</div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  mounted() {
    this.$store.commit("ChangeNavbarExistTrue");
  },
  methods: {
    getSideStatus() {
      return this.$store.state.side_exist;
    },
  },
};
</script>

<style>
</style>