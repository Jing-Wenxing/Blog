<template>
  <div id="screentop" class="time-base">
    <div class="time-content">
      <TimeLine v-for="item in timelinedatas" :key="item.id" :timelinedata="item" />
    </div>
  </div>
</template>

<script>
import TimeLine from "~/components/Publics/TimeLine.vue";

export default {
  components: {
    TimeLine,
  },
  data () {
    return {
      timelinedatas: [],
    };
  },
  mounted () {
    this.$store.commit("ChangeTopTrue");
    this.$store.commit("ChangeStatuTrue");
    this.$store.commit("ChangeFontTrue");
    this.$store.commit("ChangeExistTrue");
    this.getData();
    document
      .querySelector("#screentop")
      .addEventListener("scroll", this.handleScroll);
  },
  methods: {
    handleScroll () {
      let scrollTop = document.querySelector("#screentop").scrollTop;
      if (scrollTop > 0) {
        if (this.$store.state.navbar_istop == true) {
          this.$store.commit("ChangeTopFalse");
        }
      } else {
        if (this.$store.state.navbar_istop == false) {
          this.$store.commit("ChangeTopTrue");
        }
      }
    },
    move () {
      document.getElementById("screentop").scrollTo({
        top: 50,
        behavior: "smooth",
      });
    },
    getData () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "get",
        url: "url/timeline/getALlTimelineData",
      }).then((response) => {
        this.timelinedatas = response.data.Result;
      });
    },
  },
};
</script>

<style>
.time-base {
  padding-top: 100px;
  width: 100%;
  height: calc(100vh - 100px);
  background: #1b2431;
  display: flex;
  justify-content: center;
  overflow-y: scroll;
}

.time-content {
  padding: 20px 0;
  width: 925px;
}
</style>