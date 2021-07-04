<template>
  <div class="blog-base">
    <BlogList paddingtop="110px"></BlogList>
  </div>
</template>

<script>
import BlogList from "~/components/Publics/BlogList.vue";

export default {
  components: {
    BlogList,
  },
  mounted() {
    this.$store.commit("ChangeTopTrue");
    this.$store.commit("ChangeStatuFalse");
    this.$store.commit("ChangeFontTrue");
    this.$store.commit("ChangeExistTrue");
  },
};
</script>

<style>
.blog-base {
  width: 100%;
  height: 100vh;
  background: #f6f6f6;
}
</style>