<template>
  <div>
    <el-button @click="fetchSomething()">Axios检测</el-button>
  </div>
</template>

<script>
export default {
  mounted () {
    this.$store.commit("ChangeExistFalse");
  },
  methods: {
    fetchSomething () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      // axios({
      //   method: 'post',
      //   url: 'url/artcle/getClassOfArtcleList',
      //   data: {
      //     classification: '全部',
      //   }
      // })
      //   .then((response) => {
      //     console.log(response)
      //   })

      axios({
        method: "get",
        url: "url/swiper/getAllSwiperList",
      }).then((response) => {
        console.log(response);
      });
    },
  },
};
</script>

<style>
</style>