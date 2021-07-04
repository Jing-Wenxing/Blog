<template>
  <div class="flex bg-EBF0F8" v-loading="loading">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <div class="std-titlerange">
        <div class="std-pagetitle">文章 · 编辑</div>
      </div>
      <div class="margin-tb margin-lr-lg std-content">
        <!-- 顶部操作 -->
        <div class="std-content-top">
          <div></div>
          <div class="flex align-center">
            <el-switch class="margin-right" v-model="isshow" active-text="发布文章" inactive-text="存为草稿">
            </el-switch>
            <el-button size="mini" type="danger" @click="UploadAttributes()"
              :disabled="title == '' || text == '' || classification == ''">保存</el-button>
            <el-button size="mini" @click="pageChange('/Artcle')">
              取消
            </el-button>
          </div>
        </div>
        <div class="std-content-center">
          <div class="padding-tb padding-lr-sm">
            <div class="flex margin-bottom-xs width-100">
              <div class="width-60 margin-right">
                <div class="std-blocksubtitle margin-bottom-xs">编辑标题</div>
                <el-input class="margin-bottom-xs" v-model="title" placeholder="请输入标题"></el-input>
              </div>
              <div class="width-40">
                <div class="std-blocksubtitle margin-bottom-xs">选择分类</div>
                <div class="flex margin-bottom-xs align-center">
                  <el-button-group>
                    <el-button type="primary" @click="overlayrangeShow = !overlayrangeShow">新建分类</el-button>
                    <el-dropdown trigger="click" @command="selectEditClass">
                      <el-button type="primary" style="padding: 12px 20px; min-width: 98px">{{ classification }}
                      </el-button>
                      <el-dropdown-menu slot="dropdown">
                        <el-dropdown-item :command="item.title" v-for="item in classlist" :key="item.id">
                          {{ item.title }}</el-dropdown-item>
                      </el-dropdown-menu>
                    </el-dropdown>
                  </el-button-group>
                </div>
              </div>
            </div>
            <v-md-editor v-model="text"
              left-toolbar="undo redo clear | h bold italic strikethrough quote | ul ol table hr | link image code | tip emoji todo-list"
              height="500px" :disabled-menus="[]" @upload-image="UploadImage"></v-md-editor>
          </div>
        </div>
        <div class="std-content-bottom"></div>
      </div>
    </div>
    <!-- 修改 -->
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
      isshow: false,
      title: "",
      classification: "未分类",
      text: "",
    };
  },
  mounted () {
    this.getData(this.$route.query.id);
    this.getClassdata();
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
    // 选择分类
    selectEditClass (command) {
      this.classification = command;
    },
    test () {
      console.log(this.text);
    },
    // ===
    // 数据获取
    getData (id) {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/artcle/getDataofID",
        data: {
          id: id,
        },
      }).then((response) => {
        this.title = response.data.Result.title;
        this.text = response.data.Result.text;
        this.classification = response.data.Result.classification;
        this.isshow = response.data.Result.isshow;
        this.loading = false;
      });
    },
    // 数据库上传
    UploadAttributes () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/artcle/updateData",
        data: {
          id: this.$route.query.id,
          classification: this.classification,
          title: this.title,
          text: this.text,
          isshow: this.isshow,
        },
      }).then((response) => {
        this.loading = false;
        if (response.data.Status == 1) {
          Toast.success("更改成功");
          this.pageChange("/Artcle");
        } else Toast.fail("更改失败");
      });
    },
    // 图片上传
    UploadImage (event, insertImage, files) {
      this.getUploadURL(files[0]);
      this.loading = true;
      insertImage({
        url: "http://jingwenxing.top:9000/artcles/" + files[0].name,
        desc: files[0].name,
      });
    },
    getUploadURL (file) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url:
          "url/artcle/getUploadTempUrl?objectName=" +
          file.name,
      })
        .then((response) => {
          if (response.data.Status == 1) {
            this.UploadFile(response.data.Result, file);
          }
        })
        .catch((e) => {
          console.error(e);
        });
    },
    UploadFile (url, file) {
      fetch(url, {
        method: "PUT",
        body: file,
      })
        .then((response) => {
          this.loading = false;
        })
        .catch((e) => {
          console.error(e);
        });
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
            this.getClassdata();
          }
        })
        .catch((e) => {
          console.error(e);
        });
    },
    getClassdata () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url: "url/classes/getALlClassData",
      })
        .then((response) => {
          if (response.data.Status == 1) {
            this.classlist = response.data.Result;
          }
        })
        .catch((e) => {
          console.error(e);
        });
    },
  },
};
</script>

<style>
</style>