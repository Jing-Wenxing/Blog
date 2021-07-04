<template>
  <div class="flex bg-EBF0F8" v-loading="loading">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <div class="std-titlerange">
        <div class="std-pagetitle">时光轴 · 编辑</div>
      </div>
      <div class="margin-tb margin-lr-lg std-content user-select-none">
        <div class="std-content-top">
          <div></div>
          <div class="flex align-center">
            <el-button size="small" type="danger" :disabled="title == '' || text == '' || classification == ''"
              @click="Upload()">
              保存
            </el-button>
            <el-button size="small" @click="pageChange('/TimeLine')">
              取消
            </el-button>
          </div>
        </div>
        <div class="std-content-center">
          <div class="padding-tb padding-lr-sm">
            <div class="flex margin-bottom-xs width-100">
              <div class="width-3333 margin-right">
                <div class="std-blocksubtitle margin-bottom-xs">编辑标题</div>
                <el-input class="margin-bottom-xs" v-model="title" placeholder="请输入标题"></el-input>
              </div>
              <div class="width-3333 margin-right">
                <div class="std-blocksubtitle margin-bottom-xs">编辑分类</div>
                <el-input class="margin-bottom-xs" v-model="classification" placeholder="请输入分类"></el-input>
              </div>
              <div class="margin-right">
                <div class="std-blocksubtitle margin-bottom-xs">选择图标</div>
                <el-button style="padding: 6.5px 22px !important" @click="icon_select = !icon_select">
                  <div class="icon iconfont" style="font-size: 25px" :class="icon"></div>
                </el-button>
              </div>
              <div>
                <div class="std-blocksubtitle margin-bottom-xs">选择颜色</div>
                <div class="flex align-center">
                  <el-button class="color-range" @click="selectColor('#FFFFFF')"></el-button>
                  <el-button class="color-range" @click="selectColor('#409EFF')" type="primary"></el-button>
                  <el-button class="color-range" @click="selectColor('#67C23A')" type="success"></el-button>
                  <el-button class="color-range" @click="selectColor('#E6A23C')" type="warning"></el-button>
                  <el-button class="color-range" @click="selectColor('#F56C6C')" type="danger"></el-button>
                  <el-color-picker class="margin-left-xs" v-model="color">
                  </el-color-picker>
                </div>
              </div>
            </div>
            <div class="margin-bottom-xs">
              <div class="std-blocksubtitle margin-bottom-xs">编辑文案</div>
              <el-input type="textarea" :rows="12" placeholder="请输入内容" v-model="text" resize="none">
              </el-input>
            </div>
            <div class="margin-bottom-xs">
              <div class="std-blocksubtitle margin-bottom-xs">上传文件</div>
              <input type="file" id="timelineimage" multiple />
            </div>
          </div>
        </div>
        <div class="std-content-bottom"></div>
      </div>
    </div>
    <van-overlay class="flex justify-center align-center" :show="icon_select">
      <div class="timeline-iconselect-range">
        <div class="flex justify-between align-center margin-bottom-sm">
          <div class="std-blocktitle">图标库</div>
          <van-icon class="cursor-pointer" name="cross" size="20" color="#909399" @click="icon_select = !icon_select" />
        </div>
        <div class="iconlist flex">
          <div class="icon iconfont icon-single" :class="item" v-for="item in iconlist" :key="item"
            @click="selectIcon(item)"></div>
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
      title: "",
      classification: "",
      text: "",
      img: "",
      icon: "icon-emoji_fill",
      color: "#FFFFFF",
      icon_select: false,
      loading: false,
      iconlist: [
        "icon-jiantoushang",
        "icon-jiantoushang1",
        "icon-jiantouyou",
        "icon-jiantouxia",
        "icon-zhifeiji",
        "icon-activity",
        "icon-activity_fill",
        "icon-addition_fill",
        "icon-addition",
        "icon-barrage_fill",
        "icon-barrage",
        "icon-browse_fill",
        "icon-browse",
        "icon-brush",
        "icon-brush_fill",
        "icon-camera_fill",
        "icon-camera",
        "icon-collection_fill",
        "icon-collection",
        "icon-coordinates_fill",
        "icon-coordinates",
        "icon-createtask_fill",
        "icon-createtask",
        "icon-delete_fill",
        "icon-delete",
        "icon-document",
        "icon-document_fill",
        "icon-dynamic_fill",
        "icon-dynamic",
        "icon-emoji_fill",
        "icon-emoji",
        "icon-empty",
        "icon-empty_fill",
        "icon-flag_fill",
        "icon-flag",
        "icon-flashlight",
        "icon-flashlight_fill",
        "icon-flip",
        "icon-flip_fill",
        "icon-homepage_fill",
        "icon-homepage",
        "icon-interactive_fill",
        "icon-interactive",
        "icon-label",
        "icon-label_fill",
        "icon-like_fill",
        "icon-like",
        "icon-live_fill",
        "icon-live",
        "icon-mail",
        "icon-mail_fill",
        "icon-manage_fill",
        "icon-manage",
        "icon-order_fill",
        "icon-order",
        "icon-qrcode_fill",
        "icon-qrcode",
        "icon-remind_fill",
        "icon-remind",
        "icon-service_fill",
        "icon-service",
        "icon-setup_fill",
        "icon-setup",
        "icon-smallscreen_fill",
        "icon-smallscreen",
        "icon-tailor",
        "icon-unlock_fill",
        "icon-unlock",
        "icon-search",
        "icon-searchfill",
        "icon-3column",
        "icon-column-4",
        "icon-add",
        "icon-add-circle",
        "icon-adjust",
        "icon-arrow-up-circle",
        "icon-arrow-right-circle",
        "icon-arrow-down",
        "icon-ashbin",
        "icon-arrow-right",
        "icon-browse1",
        "icon-bottom",
        "icon-back",
        "icon-bad",
        "icon-arrow-double-left",
        "icon-arrow-left-circle",
        "icon-arrow-double-right",
        "icon-caps-lock",
        "icon-camera1",
        "icon-chart-bar",
        "icon-attachment",
        "icon-code",
        "icon-check-item",
        "icon-calendar",
        "icon-comment",
        "icon-column-vertical",
        "icon-column-horizontal",
        "icon-complete",
        "icon-chart-pie",
        "icon-cry",
        "icon-customer-service",
        "icon-delete1",
        "icon-direction-down",
        "icon-copy",
        "icon-cut",
        "icon-data-view",
        "icon-direction-down-circle",
        "icon-direction-right",
        "icon-direction-up",
        "icon-discount",
        "icon-direction-left",
        "icon-download",
        "icon-electronics",
        "icon-drag",
        "icon-elipsis",
        "icon-export",
        "icon-explain",
        "icon-edit",
        "icon-eye-close",
        "icon-email",
        "icon-error",
        "icon-favorite",
        "icon-file-common",
        "icon-file-delete",
        "icon-file-add",
        "icon-film",
        "icon-fabulous",
        "icon-file",
        "icon-folder-close",
        "icon-filter",
        "icon-good",
        "icon-hide",
        "icon-home",
        "icon-history",
        "icon-file-open",
        "icon-forward",
        "icon-import",
        "icon-image-text",
        "icon-keyboard-26",
        "icon-keyboard-9",
        "icon-link",
        "icon-layout",
        "icon-fullscreen-shrink",
        "icon-layers",
        "icon-lock",
        "icon-fullscreen-expand",
        "icon-map",
        "icon-meh",
        "icon-menu",
        "icon-loading",
        "icon-help",
        "icon-minus-circle",
        "icon-modular",
        "icon-notification",
        "icon-mic",
        "icon-more",
        "icon-pad",
        "icon-operation",
        "icon-play",
        "icon-print",
        "icon-mobile-phone",
        "icon-minus",
        "icon-navigation",
        "icon-pdf",
        "icon-prompt",
        "icon-move",
        "icon-refresh",
        "icon-run-up",
        "icon-picture",
        "icon-run-in",
        "icon-pin",
        "icon-save",
        "icon-search1",
        "icon-share",
        "icon-scanning",
        "icon-security",
        "icon-sign-out",
        "icon-select",
        "icon-stop",
        "icon-success",
        "icon-smile",
        "icon-switch",
        "icon-setting",
        "icon-survey",
        "icon-task",
        "icon-skip",
        "icon-text",
        "icon-time",
        "icon-telephone-out",
        "icon-toggle-left",
        "icon-toggle-right",
        "icon-telephone",
        "icon-top",
        "icon-unlock1",
        "icon-user",
        "icon-upload",
        "icon-work",
        "icon-training",
        "icon-warning",
        "icon-zoom-in",
        "icon-zoom-out",
        "icon-add-bold",
        "icon-arrow-left-bold",
        "icon-arrow-up-bold",
        "icon-close-bold",
        "icon-arrow-down-bold",
        "icon-minus-bold",
        "icon-arrow-right-bold",
        "icon-select-bold",
        "icon-arrow-up-filling",
        "icon-arrow-down-filling",
        "icon-arrow-left-filling",
        "icon-arrow-right-filling",
        "icon-caps-unlock-filling",
        "icon-comment-filling",
        "icon-check-item-filling",
        "icon-clock-filling",
        "icon-delete-filling",
        "icon-decline-filling",
        "icon-dynamic-filling",
        "icon-intermediate-filling",
        "icon-favorite-filling",
        "icon-layout-filling",
        "icon-help-filling",
        "icon-history-filling",
        "icon-filter-filling",
        "icon-file-common-filling",
        "icon-news-filling",
        "icon-edit-filling",
        "icon-fullscreen-expand-filling",
        "icon-smile-filling",
        "icon-rise-filling",
        "icon-picture-filling",
        "icon-notification-filling",
        "icon-user-filling",
        "icon-setting-filling",
        "icon-switch-filling",
        "icon-work-filling",
        "icon-task-filling",
        "icon-success-filling",
        "icon-warning-filling",
        "icon-folder-filling",
        "icon-map-filling",
        "icon-prompt-filling",
        "icon-meh-filling",
        "icon-cry-filling",
        "icon-top-filling",
        "icon-home-filling",
        "icon-sorting",
      ],
    };
  },
  mounted () {
    this.$store.commit("ChangeNavbarExistTrue");
  },
  methods: {
    getSideStatus () {
      return this.$store.state.side_exist;
    },
    pageChange (path) {
      this.$router.push(path);
    },
    selectIcon (icon) {
      Toast.success("选择成功");
      this.icon_select = !this.icon_select;
      this.icon = icon;
    },
    selectColor (color) {
      this.color = color;
    },
    openIconSelect () { },
    // ==== 上传 ====
    Upload () {
      if (document.querySelector("#timelineimage").files.length) {
        // 上传图片和文案
        this.loading = true;
        this.img = document.querySelector("#timelineimage").files[0].name;
        this.getUploadURL();
      } else {
        // 上传文案就好
        this.UploadAttributes();
      }
    },

    getUploadURL () {
      var files = document.querySelector("#timelineimage").files;

      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "get",
        url:
          "url/timeline/getUploadTempUrl?objectName=" +
          files[0].name,
      })
        .then((response) => {
          if (response.data.Status == 1) {
            this.UploadFile(response.data.Result);
          }
        })
        .catch((e) => {
          console.error(e);
        });
    },
    UploadFile (url) {
      var files = document.querySelector("#timelineimage").files;
      for (var i = 0; i < files.length; i++) {
        var file = files[i];
        fetch(url, {
          method: "PUT",
          body: file,
        })
          .then((response) => {
            this.UploadAttributes();
          })
          .catch((e) => {
            console.error(e);
          });
      }
    },
    UploadAttributes () {
      var changetext = this.text.replace(/\r/g, "&nbsp;");
      changetext = changetext.replace(/\n/g, "<br/><br/>");

      const axios = require("axios").default;
      axios.defaults.withCredentials = true;
      axios({
        method: "post",
        url: "url/timeline/uploadData",
        data: {
          classification: this.classification,
          title: this.title,
          text: changetext,
          img: this.img,
          icon: this.icon,
          color: this.color,
        },
      }).then((response) => {
        this.loading = false;
        if (response.data.Status == 1) {
          Toast.success("上传成功");
          this.pageChange("/TimeLine");
        } else Toast.fail("上传失败");
      });
    },
  },
};
</script>

<style scoped>
.color-range {
  padding: 18.5px;
}

.timeline-iconselect-range {
  padding: 25px;
  width: 800px;
  height: 450px;
  background: #fff;
  border-radius: 10px;
}

.iconlist {
  display: flex;
  /* justify-content: center; */
  flex-flow: row wrap;
  height: 400px;
  overflow: auto;
}

.icon-single {
  margin: 5px;
  width: 70px;
  height: 70px;
  line-height: 70px;
  text-align: center;
  cursor: pointer;
  user-select: none;
  font-size: 25px;
}

.icon-single:hover {
  background: rgba(0, 0, 0, 0.1);
  border-radius: 6px;
}
</style>