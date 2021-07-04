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
            <el-button size="mini" @click="pageChange('/Artcle/Add')">
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
            <el-table-column prop="classification" label="分类" width="150"></el-table-column>
            <el-table-column prop="title" label="标题"></el-table-column>
            <el-table-column label="草稿 / 发布" width="150">
              <template slot-scope="scope">
                <el-switch v-model="scope.row.isshow" @change="changeShowStatus(scope.row.id)"></el-switch>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
              <template slot-scope="scope">
                <el-button size="small" @click="pageEdit('/Artcle/Edit', scope.row.id)">编辑</el-button>
                <el-button type="danger" size="small" :disabled="scope.row.id == 1" @click="deleteData(scope.row.id)">删除
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
  </div>
</template>

<script>
import Vue from "vue";
import { Toast } from "vant";

Vue.use(Toast);

export default {
  data () {
    return {
      loading: false,
      // ===
      tableData: [],
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
    pageEdit (path, id) {
      this.$router.push({ path: path, query: { id: id } });
    },
    getData () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/artcle/getALlArtcleDataofPage",
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
        url: "url/artcle/deleteArtcleData",
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
    changeShowStatus (id) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/artcle/updataArtcleIsShow",
        data: {
          id: id,
        },
      }).then((response) => {
        this.getData();
      });
    },
    // ===
    // 页面切换
    getPagetotal () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url: "url/artcle/tablesValue",
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

<style>
</style>