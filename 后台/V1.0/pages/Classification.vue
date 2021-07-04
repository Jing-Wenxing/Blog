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
            <el-button size="mini" @click="overlayrangeShow = !overlayrangeShow">
              添加
            </el-button>
          </div>
        </div>
        <div class="std-content-center">
          <el-table :data="classlist" style="width: 100%; transition: all 0.3s ease-in-out"
            :header-cell-style="thStyleFun" :cell-style="cellStyleFun">
            <el-table-column prop="id" label="ID值" width="75">
            </el-table-column>
            <el-table-column prop="title" label="分类名" width="150"></el-table-column>
            <el-table-column prop="description" label="描述"></el-table-column>
            <el-table-column label="操作" width="150">
              <template slot-scope="scope">
                <el-button type="danger" size="small" :disabled="scope.row.id == 1"
                  @click="deleteData(scope.row.title)">删除</el-button>
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
    <!-- 新建 -->
    <van-overlay class="flex justify-center align-center" :show="overlayrangeShow">
      <div class="std-overlayrange">
        <div class="flex justify-between align-center margin-bottom-sm">
          <div class="std-blocktitle">新建分类</div>
          <van-icon class="cursor-pointer" name="cross" size="20" color="#909399"
            @click="overlayrangeShow = !overlayrangeShow" />
        </div>
        <div class="width-100">
          <div class="std-blocksubtitle margin-bottom-xs">分类标题</div>
          <el-input class="margin-bottom-xs" v-model="newtitle" placeholder="请输入标题"></el-input>
        </div>
        <div class="width-100">
          <div class="std-blocksubtitle margin-bottom-xs">分类描述</div>
          <el-input class="margin-bottom width-50" v-model="newdescription" placeholder="请输入标题"></el-input>
        </div>
        <el-button icon="el-icon-upload" type="primary" @click="uploadClassdata()">确认</el-button>
      </div>
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
      loading: false,
      overlayrangeShow: false,
      // ===
      newtitle: "",
      newdescription: "",
      classlist: [],
      // ====
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
    uploadClassdata () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/classes/uploadData",
        data: {
          title: this.newtitle,
          description: this.newdescription,
        },
      })
        .then((response) => {
          if (response.data.Status == 1) {
            this.loading = false;
            this.overlayrangeShow = false;
            this.newtitle = "";
            this.newdescription = "";
            this.getData();
            this.getPagetotal();
          }
        })
        .catch((e) => {
          console.error(e);
        });
    },
    getData () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/classes/getALlClassDataofPage",
        data: {
          page: this.page,
        },
      }).then((response) => {
        this.classlist = response.data.Result;
        this.loading = false;
      });
    },
    deleteData (classification) {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/classes/deleteClassData",
        data: {
          classification: classification,
        },
      })
        .then((response) => {
          this.page = 1;
          this.getPagetotal();
          this.getData();
          if (response.data.Status == 1) {
            Toast.success("删除成功");
          } else Toast.fail("删除失败");
          this.loading = false;
        })
        .catch((e) => {
          console.error(e);
        });
    },
    getPagetotal () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url: "url/classes/tablesValue",
      }).then((response) => {
        this.total = response.data.Result;
        this.page = 1;
      });
    },
  },
};
</script>

<style>
</style>