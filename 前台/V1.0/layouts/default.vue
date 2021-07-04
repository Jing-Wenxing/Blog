<template>
  <div class="flex">
    <Navbar />
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
  methods: {},
};
</script>

<style>
</style>