<template>
  <div class="bloglist-range" :style="
      'padding-top: ' +
      paddingtop +
      '; height: calc(' +
      height +
      ' - ' +
      paddingtop +
      ');'
    ">
    <div class="bloglist-base">
      <transition-group name="itemlist" tag="div">
        <div class="bloglist-single" v-for="item in bloglist" :key="item.id">
          <div class="title">
            {{ item.title }}
          </div>
          <div class="operat">
            <div class="icon iconfont icon-time cursor-pointer" style="font-size: 14px; margin-right: 2px"></div>
            <span class="margintext">发表于：{{ item.timeline | formatDate }}</span>
            <div class="icon iconfont icon-order cursor-pointer" style="font-size: 14px; margin-right: 2px"></div>
            <span class="margintext">分类：{{ item.classification }}</span>
            <!-- <div
              class="icon iconfont icon-browse cursor-pointer"
              style="font-size: 14px; margin-right: 2px"
            ></div>
            <span class="margintext">阅读：{{ item.read }}</span>
            <div
              class="icon iconfont icon-comment cursor-pointer"
              style="font-size: 14px; margin-right: 2px"
            ></div>
            <span class="margintext">评论：{{ item.comment }}</span> -->
          </div>
          <div class="summary">摘要：{{ item.summary }}</div>
          <MenuButtones size="16px" color="000" icon="icon-search" height="50px" width="100px" bold nopadding
            @click="OpenArtcle(item.id)">阅读全文 >>
          </MenuButtones>
        </div>
      </transition-group>
      <div class="flex justify-center">
        <MenuButtones v-if="isloading" size="16px" color="000" icon="icon-search" height="50px" width="100px" bold
          nopadding @click="DataUpdata()">加载更多
        </MenuButtones>
        <div v-else class="margin-top-sm margin-bottom-lg text-bold">
          —— 没有更多数据了 ——
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import MenuButtones from "~/components/Publics/MenuButtones.vue";

export default {
  components: {
    MenuButtones,
  },
  props: {
    paddingtop: {
      type: String,
      default: "100px",
    },
    height: {
      type: String,
      default: "100vh",
    },
  },
  data () {
    return {
      isloading: true,
      page: 1,
      bloglist: [],
      updatalist: [],
    };
  },
  mounted () {
    this.getData(this.page);
    this.page++;
  },
  methods: {
    getData (page) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "post",
        url: "url/artcle/getALlFrontArtcleDataofPage",
        data: {
          page: page,
        },
      }).then((response) => {
        this.bloglist = response.data.Result;
        if (this.bloglist[this.bloglist.length - 1].id == 1)
          this.isloading = false;
      });
    },
    DataUpdata () {
      // 获取数据
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "post",
        url: "url/artcle/getALlFrontArtcleDataofPage",
        data: {
          page: this.page,
        },
      }).then((response) => {
        this.updatalist = response.data.Result;
        if (this.updatalist.length < 1) this.isloading = false;
        else this.isloading = true;

        for (let i = 0; i < this.updatalist.length; i++) {
          this.bloglist.push(this.updatalist[i]);
        }

        if (this.bloglist[this.bloglist.length - 1].id == 1)
          this.isloading = false;
      });
    },
    OpenArtcle (singleid) {
      this.$router.push({
        name: "Artcle",
        query: {
          id: singleid,
        },
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
.bloglist-range {
  /* padding-top: 100px; */
  width: 100%;
  /* height: calc(100vh - 100px); */
  overflow-y: auto;
  display: flex;
  justify-content: center;
  transition: all 0.3s linear;
}

.bloglist-base {
  margin: 0 25px;
  transition: all 0.2s linear;
}

.bloglist-single {
  height: auto;
  width: 900px;
  margin: 15px 25px;
  background: #fff;
  box-shadow: 0 0 20px 10px rgb(220 220 220 / 30%);
  padding: 25px;
  border-radius: 20px;
  transition: all 0.2s linear;
}

@media screen and (max-width: 1000px) {
  .bloglist-single {
    width: calc(100% - 50px);
  }
}

.bloglist-single .title {
  font-size: 30px;
  font-weight: bold;
  color: #4a4a4a;
}

.bloglist-single .operat {
  margin: 12px 0;
  display: flex;
  align-items: center;
  color: #888888;
}

.bloglist-single .margintext {
  margin-left: 5px;
  margin-right: 15px;
}

.bloglist-single .summary {
  font-size: 17.5px;
  font-weight: bold;
  color: #4a4a4a;
}

.itemlist-enter-active,
.itemlist-leave-active {
  transition: all 1s;
}

.itemlist-enter,
.itemlist-leave-active {
  opacity: 0;
}
</style>