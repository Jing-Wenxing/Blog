<template>
  <div class="flex bg-EBF0F8" v-loading="loading">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <div class="std-titlerange">
        <div class="std-pagetitle">轮播图</div>
      </div>
      <div class="margin-tb margin-lr-lg std-content">
        <!-- 顶部操作 -->
        <div class="std-content-top">
          <div></div>
          <div class="flex align-center">
            <el-button size="mini" @click="opencloseAddRange()">
              添加
            </el-button>
          </div>
        </div>
        <div class="std-content-center">
          <el-table :data="classlist" style="width: 100%; transition: all 0.3s ease-in-out"
            :header-cell-style="thStyleFun" :cell-style="cellStyleFun">
            <el-table-column prop="id" label="ID值" width="75">
            </el-table-column>
            <el-table-column prop="title" label="标题" width="200">
            </el-table-column>
            <el-table-column prop="text" label="文案"></el-table-column>
            <el-table-column label="展示" width="75">
              <template slot-scope="scope">
                <el-switch v-model="scope.row.isshow" @change="changeShowStatus(scope.row.id)"></el-switch>
              </template>
            </el-table-column>
            <el-table-column label="顺序" width="150">
              <template slot-scope="scope">
                <el-button icon="el-icon-arrow-up" circle :disabled="scope.row.swquence == 1"
                  @click="changeSwquence(scope.row.id, 'up')"></el-button>
                <el-button icon="el-icon-arrow-down" circle :disabled="scope.row.swquence == classlist.length"
                  @click="changeSwquence(scope.row.id, 'down')"></el-button>
              </template>
            </el-table-column>
            <el-table-column label="操作" width="200">
              <template slot-scope="scope">
                <el-button size="small" @click="openEditRange(scope.row)">编辑
                </el-button>
                <el-button size="small" type="danger" :disabled="scope.row.id == 1" @click="deleteSwiper(scope.row.id)">
                  删除
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="std-content-bottom">
          <el-pagination style="background: transparent" layout="prev, pager, next" hide-on-single-page :total="total"
            :current-page.sync="page" @current-change="getSwiperData()">
          </el-pagination>
        </div>
      </div>
    </div>
    <!-- 新建 -->
    <!-- 添加 -->
    <van-overlay class="flex justify-center align-center" :show="addRangeShow">
      <div class="swiper-addrange">
        <div class="flex justify-between align-center margin-bottom-sm">
          <div class="std-blocktitle">添加数据</div>
          <van-icon class="cursor-pointer" name="cross" size="20" color="#909399" @click="opencloseAddRange()" />
        </div>
        <div class="flex margin-bottom-xs">
          <div class="width-50 margin-right">
            <div class="std-blocksubtitle margin-bottom-xs">上传标题</div>
            <el-input class="margin-bottom-xs" v-model="updatatitle" placeholder="请输入标题"></el-input>
          </div>
          <div class="width-50">
            <div class="std-blocksubtitle margin-bottom-xs">上传文案</div>
            <el-input class="margin-bottom-xs" v-model="updatatext" placeholder="请输入内容"></el-input>
          </div>
        </div>
        <div>
          <input type="file" id="swiperimage" multiple />
          <el-button icon="el-icon-upload" type="primary" @click="addSwiper()">上传</el-button>
        </div>
      </div>
    </van-overlay>
    <!-- 修改 -->
    <van-overlay class="flex justify-center align-center" :show="editRangeShow">
      <div class="swiper-editrange">
        <div class="flex justify-between align-center margin-bottom-sm">
          <div class="std-blocktitle">修改数据</div>
          <van-icon class="cursor-pointer" name="cross" size="20" color="#909399" @click="closeEditRange()" />
        </div>
        <div class="flex margin-bottom-xs">
          <div class="width-50 margin-right">
            <div class="std-blocksubtitle margin-bottom-xs">上传标题</div>
            <el-input class="margin-bottom-xs" v-model="edittitle" placeholder="请输入标题"></el-input>
          </div>
          <div class="width-50">
            <div class="std-blocksubtitle margin-bottom-xs">上传文案</div>
            <el-input class="margin-bottom-xs" v-model="edittext" placeholder="请输入文案"></el-input>
          </div>
        </div>
        <div>
          <el-button icon="el-icon-upload" type="primary" @click="editSwiper()">保存</el-button>
        </div>
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
      addRangeShow: false,
      editRangeShow: false,
      updatatitle: "",
      updatatext: "",
      editID: "",
      edittitle: "",
      edittext: "",
    };
  },
  mounted () {
    this.$store.commit("ChangeNavbarExistTrue");
    this.getSwiperData();
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
    opencloseAddRange () {
      this.addRangeShow = !this.addRangeShow;
    },
    openEditRange (data) {
      this.editRangeShow = true;
      this.editID = data.id;
      this.edittitle = data.title;
      this.edittext = data.text;
    },
    closeEditRange () {
      this.editRangeShow = false;
      this.editID = "";
      this.edittitle = "";
      this.edittext = "";
    },
    // 删除项目
    deleteSwiper (id) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/swiper/deleteSwiperData",
        data: {
          id: id,
        },
      }).then((response) => {
        this.getSwiperData();
      });
    },
    // 上传轮播
    addSwiper () {
      if (this.classlist.length < 10) {
        var imgfile = document.querySelector("#swiperimage").files;
        if (
          this.updatatitle != "" &&
          this.updatatext != "" &&
          imgfile.length != 0
        ) {
          // 上传文件
          this.getUploadURL(imgfile[0]);
        } else {
          this.$message.error("参数错误，请补全需要上传的数据");
        }
      } else {
        this.$message.error("轮播图数量达到上限");
        this.opencloseAddRange();
      }
    },
    getUploadURL (file) {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url:
          "url/swiper/getUploadTempUrl?objectName=" +
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
          this.UploadText(file);
        })
        .catch((e) => {
          console.error(e);
        });
    },
    UploadText (file) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/swiper/addSwiperData",
        data: {
          title: this.updatatitle,
          text: this.updatatext,
          img: file.name,
        },
      }).then((response) => {
        this.getSwiperData();
        this.addRangeShow = false;
        this.updatatitle = "";
        this.updatatext = "";
        this.loading = false;
      });
    },
    // 修改轮播
    editSwiper () {
      if (this.edittitle != "" && this.edittext != "") {
        const axios = require("axios").default;
        axios.defaults.withCredentials = true;
        axios({
          method: "post",
          url: "url/swiper/updataSwiperAttributes",
          data: {
            id: this.editID,
            title: this.edittitle,
            text: this.edittext,
          },
        }).then((response) => {
          this.getSwiperData();
          this.closeEditRange();
        });
      } else {
        this.$message.error("参数错误，请补全需要上传的数据");
      }
    },
    // 移动顺序
    changeSwquence (id, status) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/swiper/changeSwiperSwquence",
        data: {
          id: id,
          status: status,
        },
      }).then((response) => {
        this.getSwiperData();
      });
    },
    // 展示状态
    changeShowStatus (id) {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/swiper/updataSwiperIsShow",
        data: {
          id: id,
        },
      }).then((response) => {
        this.getSwiperData();
      });
    },
    // ===
    getSwiperData () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/swiper/getALlClassDataofPage",
        data: {
          page: this.page,
        },
      }).then((response) => {
        this.classlist = response.data.Result;
        this.loading = false;
      });
    },
    getPagetotal () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url: "url/swiper/tablesValue",
      }).then((response) => {
        this.total = response.data.Result;
        this.page = 1;
      });
    },
  },
};
</script>

<style>
.swiper-base {
  width: calc(100vw - 100px);
  transition: all 0.3s ease-in-out;
}

.swiper-base-noside {
  width: 100vw;
  transition: all 0.3s ease-in-out;
}

.swiper-base,
.swiper-base-noside {
  /* height: 100vh; */
  position: relative;
}

.std-opreate {
  padding: 20px;
  border-radius: 10px 10px 0 0;
  width: calc(100% - 40px);
  background: #f7f9ff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.std-opreate .button {
  width: 75px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  background: #879fe5;
  font-size: 12px;
  color: #fff;
  border-radius: 7.5px;
  cursor: pointer;
}

.swiper-addrange {
  padding: 25px;
  width: 600px;
  height: 350px;
  background: #fff;
  border-radius: 10px;
}

.swiper-editrange {
  padding: 25px;
  width: 600px;
  height: 350px;
  background: #fff;
  border-radius: 10px;
}
</style>