<template>
  <div class="flex bg-EBF0F8" v-loading="loading">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <div class="std-titlerange">
        <div class="std-pagetitle">时光轴</div>
      </div>
      <div class="margin-tb margin-lr-lg std-content">
        <!-- 顶部操作 -->
        <div class="std-content-top">
          <div></div>
          <div class="flex align-center">
            <el-button size="mini" @click="pageChange('/TimeLine/Edit')">
              添加
            </el-button>
          </div>
        </div>
        <div class="std-content-center">
          <el-table :data="tableData" style="width: 100%; transition: all 0.3s ease-in-out"
            :header-cell-style="thStyleFun" :cell-style="cellStyleFun">
            <el-table-column prop="id" label="ID值" width="75">
            </el-table-column>
            <el-table-column label="时间" width="150">
              <template slot-scope="scope">
                {{ scope.row.timeline | formatDate }}
              </template>
            </el-table-column>
            <el-table-column prop="classification" label="分类" width="75"></el-table-column>
            <el-table-column class="flex justify-center align-center" label="图标" width="75">
              <template slot-scope="scope">
                <div class="icon iconfont" style="font-size: 20px" :class="scope.row.icon"></div>
              </template>
            </el-table-column>
            <el-table-column prop="color" label="颜色" width="100"></el-table-column>
            <el-table-column prop="title" label="标题"> </el-table-column>
            <el-table-column prop="text" label="文案"></el-table-column>
            <el-table-column label="图片" width="100">
              <template slot-scope="scope">
                <el-button v-if="scope.row.img != ''" size="small" @click="showImg(scope.row.img)">
                  查看
                </el-button>
                <div v-else>无</div>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="100">
              <template slot-scope="scope">
                <el-button type="danger" size="small" :disabled="scope.row.id == 1" @click="deleteData(scope.row.id)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="std-content-bottom">
          <el-pagination style="background: transparent" layout="prev, pager, next" hide-on-single-page :total="total"
            :current-page.sync="page" @current-change="getData()">
          </el-pagination>
        </div>
      </div>
    </div>
    <van-overlay class="flex justify-center align-center" :show="imgShow">
      <img class="table-imgshow" :src="imgcontent" />
      <div class="
          icon
          iconfont
          icon-close
          imgclose-icon
          cursor-pointer
          user-select-none
        " style="color: #fff; font-size: 40px" @click="closeoverlay()"></div>
    </van-overlay>
  </div>
</template>

<script>
import Vue from "vue";
import { Toast } from "vant";

Vue.use(Toast);

export default {
  data () {
    return {
      tableData: [],
      loading: false,
      imgShow: false,
      imgcontent: "",
      total: null,
      page: 1,
    };
  },
  mounted () {
    this.$store.commit("ChangeNavbarExistTrue");
    this.getData();
    this.getPagetotal();
  },
  methods: {
    // 基础方法
    thStyleFun () {
      return "text-align:center";
    },
    cellStyleFun () {
      return "text-align:center";
    },
    getSideStatus () {
      return this.$store.state.side_exist;
    },
    pageChange (path) {
      this.$router.push(path);
    },
    // ===
    getData () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/timeline/getALlTimelineDataofPage",
        data: {
          page: this.page,
        },
      }).then((response) => {
        this.tableData = response.data.Result;
        this.loading = false;
      });
    },
    deleteData (id) {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/timeline/deleteTimelineData",
        data: {
          id: id,
        },
      }).then((response) => {
        this.page = 1;
        this.getPagetotal();
        this.getData();
        if (response.data.Status == 1) {
          Toast.success("删除成功");
        } else Toast.fail("删除失败");
        this.loading = false;
      });
    },
    // 图片处理
    closeoverlay () {
      this.imgcontent = "";
      this.imgShow = !this.imgShow;
    },
    showImg (img) {
      this.imgShow = !this.imgShow;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/timeline/getImage",
        data: {
          img: img,
        },
      }).then((response) => {
        this.imgcontent = response.data.Result.url;
      });
    },
    // ===
    // 页面切换
    getPagetotal () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url: "url/timeline/tablesValue",
      }).then((response) => {
        this.total = response.data.Result;
        this.page = 1;
      });
    },
    // ===
  },
  filters: {
    formatDate: function (value) {
      var date = new Date(value * 1); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + "年";
      var M =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "月";
      var D =
        (date.getDate() + 1 < 10 ? "0" + date.getDate() : date.getDate()) +
        "日";
      return Y + M + D;
    },
  },
};
</script>

<style scoped>
.table-single-color {
  width: 20px;
  height: 20px;
  border: 1px solid #000;
  border-radius: 3px;
}

.table-imgshow {
  height: 100vh;
  width: 1000px;
  object-fit: contain;
}

.imgclose-icon {
  position: absolute;
  top: 50px;
  right: 50px;
}

.el-dialog,
.el-pager li {
  background: transparent !important;
}
</style>