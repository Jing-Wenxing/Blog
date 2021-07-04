<template>
  <div class="flex">
    <Navbar v-if="isRouteExite()" />
    <transition name="co-fade-in-linear">
      <Nuxt />
    </transition>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },
  mounted() {},
  updated() {},
  methods: {
    isRouteExite() {
      if (this.$route.name == "index") return false;
      else return true;
    },
  },
};
</script>

<style>
</style>