<template>
  <div :class="getNavbarExist() ? 'navbar-base' : 'navbar-base-none'">
    <!-- 两个内容的切换部分 -->
    <transition name="co-zoom-in-left" mode="out-in">
      <div v-if="navbar_show" key="show" class="navbar-show">
        <div class="content-range">
          <!-- 用户状态 -->
          <div></div>
          <!-- 导航栏列表 -->
          <div>
            <div
              :class="
                getPageRoute() == item.path
                  ? 'navbar-menu-click'
                  : 'navbar-menu'
              "
              v-for="item in navbar_list"
              :key="item.id"
              @click="pageChange('/' + item.path)"
            >
              <div class="icon iconfont" :class="item.icon"></div>
              <div class="title">{{ item.text }}</div>
            </div>
          </div>
          <!-- 设置 -->
          <div
            class="
              margin
              icon
              iconfont
              icon-sign-out
              cursor-pointer
              user-select-none
            "
            style="font-size: 24px"
            @click="logout"
          ></div>
        </div>
        <div class="navbar-button" @click="ChangeExistStatus()">
          <span class="icon iconfont icon-arrow-left-bold"></span>
        </div>
      </div>
      <div v-else key="noshow" class="navbar-noshow">
        <div class="navbar-button" @click="ChangeExistStatus()">
          <span class="icon iconfont icon-arrow-right-bold"></span>
        </div>
      </div>
    </transition>
  </div>
</template>

<script>
import Vue from "vue";
import { Toast } from "vant";

Vue.use(Toast);

export default {
  data() {
    return {
      navbar_show: true,
      navbar_list: [
        // {
        //   icon: "icon-chart-bar",
        //   text: "仪表盘",
        //   path: "Admin",
        // },
        {
          icon: "icon-3column",
          text: "轮播图",
          path: "Swiper",
        },
        {
          icon: "icon-document",
          text: "文章",
          path: "Artcle",
        },
        {
          icon: "icon-manage",
          text: "分类",
          path: "Classification",
        },
        {
          icon: "icon-data-view",
          text: "时光轴",
          path: "TimeLine",
        },
        // {
        //   icon: "icon-customer-service",
        //   text: "测试",
        //   path: "Test",
        // },
      ],
    };
  },
  mounted() {
    this.profile();
  },
  updated() {
    this.profile();
  },
  methods: {
    getNavbarExist() {
      return this.$store.state.navbar_exist;
    },
    getPageRoute() {
      return this.$route.name;
    },
    ChangeExistStatus() {
      this.navbar_show = !this.navbar_show;
      this.$store.commit("ChangeSideExistStatus");
    },
    pageChange(path) {
      this.$router.push(path);
    },
    profile() {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "get",
        url: "http://jingwenxing.top:8083/user/profile",
      })
        .then((response) => {
          if (response.data.Result.login != true) {
            this.pageChange("/");
            Toast.success("未登录");
          }
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    logout() {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "get",
        url: "http://jingwenxing.top:8083/user/logout",
      })
        .then((response) => {
          this.pageChange("/");
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.navbar-base-none {
  display: none;
}

.navbar-base {
  display: flex;
  position: fixed;
  z-index: 99;
}

.navbar-show {
  display: flex;
  align-items: center;
  width: 115px;
  height: 100vh;
}

.navbar-show .content-range {
  width: 100px;
  height: 100vh;
  background: #fcfdfe;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
  box-shadow: 0px 0px 7px rgba(0, 0, 0, 0.25);
}

.navbar-button {
  width: 15px;
  height: 150px;
  background: #333333;
  border-radius: 0 7px 7px 0;
  display: flex;
  justify-content: center;
  align-items: center;
  color: #fff;
  font-size: 10px;
  cursor: pointer;
  user-select: none;
}

.navbar-noshow {
  width: 15px;
  height: 100vh;
  display: flex;
  align-items: center;
}

.navbar-menu {
  margin: 40px 0;
  padding: 5px 0;
  width: 90px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  cursor: pointer;
  user-select: none;
  transition: all 0.2s ease-in-out;
  color: #333333;
}

.navbar-menu-click {
  margin: 40px 0;
  padding: 5px 0;
  width: 90px;
  display: flex;
  flex-direction: column;
  align-items: center;
  border-left: 5px solid transparent;
  border-right: 5px solid transparent;
  cursor: pointer;
  user-select: none;
  transition: all 0.3s ease-in-out;
  color: #7277ef;
  border-left: 5px solid #7277ef;
  background: rgba(114, 118, 239, 0.075);
  border-radius: 0 50px 50px 0;
}

.navbar-menu .icon,
.navbar-menu-click .icon {
  font-size: 32px;
}

.navbar-menu .title,
.navbar-menu-click .title {
  font-size: 15px;
}

.navbar-menu:hover {
  color: #7277ef;
  border-left: 5px solid #7277ef;
  background: rgba(114, 118, 239, 0.075);
  border-radius: 0 50px 50px 0;
}
</style>