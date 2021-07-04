<template>
  <div class="artcle-base">
    <div class="artcle-range">
      <div class="
          artcle-top
          padding-top padding-bottom-sm
          flex flex-direction
          align-center
        ">
        <div class="title">{{ artcledata.title }}</div>
        <div class="timeline flex align-center">
          <span class="icon iconfont icon-time margin-right-xs"></span>
          <span class="margin-right-xs">发表于：{{ artcledata.timeline | formatDate }}</span>
          <span class="icon iconfont icon-order margin-right-xs"></span>
          <span>分类：{{ artcledata.classification }}</span>
        </div>
      </div>
      <v-md-preview :text="artcledata.text"></v-md-preview>
      <div style="height: 50px; background: #fff"></div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      artcledata: {
        classification: "",
        id: "",
        isshow: "",
        summary: "",
        text: "",
        timeline: "",
        title: "",
      },
    };
  },
  mounted () {
    this.$store.commit("ChangeTopTrue");
    this.$store.commit("ChangeStatuFalse");
    this.$store.commit("ChangeFontTrue");
    this.$store.commit("ChangeExistTrue");
    this.getData(this.$route.query.id);
  },
  methods: {
    getData (id) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "post",
        url: "url/artcle/getDataofID",
        data: {
          id: id,
        },
      }).then((response) => {
        this.artcledata = response.data.Result;
      });
    },
  },
  filters: {
    formatDate: function (value) {
      var date = new Date(value * 1); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + "-";
      var M =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "-";
      var D = date.getDate() + 1 < 10 ? "0" + date.getDate() : date.getDate();
      return Y + M + D;
    },
  },
};
</script>

<style>
.artcle-base {
  padding-top: 100px;
  width: 100%;
  height: calc(100vh - 100px);
  display: flex;
  justify-content: center;
  overflow-y: auto;
  background: #f6f6f6;
}

.artcle-range {
  width: 925px;
  background: #fff;
}

.artcle-top,
.artcle-bottom {
  background: #fff;
}

.artcle-top .title {
  margin-bottom: 5px;
  font-size: 28px;
  font-weight: bold;
  color: #303133;
}

.artcle-top .timeline {
  font-size: 15px;
  color: #909399;
}
</style>