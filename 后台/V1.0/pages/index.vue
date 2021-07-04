<template>
  <!-- <div class="index-base" v-loading="loading"> -->
  <div class="index-base">
    <div class="index-login-block">
      <div class="index-left">
        <img style="width: 275px; margin-bottom: 20px" src="~/assets/img/think.png" alt="" />
      </div>
      <div class="index-right">
        <div class="text-20 text-bold color-a margin-bottom-xs">
          博客后台系统
        </div>
        <div class="index-input">
          <el-input class="margin-bottom-sm" placeholder="请输入账户" v-model="username" clearable
            prefix-icon="el-icon-user">
          </el-input>
          <el-input class="margin-bottom-sm" placeholder="请输入密码" v-model="password" clearable show-password
            prefix-icon="el-icon-lock">
          </el-input>
          <div class="index-verify">
            <img :src="imgurl" />
            <el-button icon="el-icon-refresh-left" circle @click="refimg_get"></el-button>
          </div>
          <el-input placeholder="请输入验证码" v-model="verify" clearable prefix-icon="el-icon-magic-stick">
          </el-input>
        </div>
        <el-button type="primary" plain round @click="login()">
          <span class="margin-lr-xl">登录</span>
        </el-button>
        <!-- <el-button type="primary" plain round @click="profile()">
          <span class="margin-lr-xl">状态</span>
        </el-button>
        <el-button type="primary" plain round @click="logout()">
          <span class="margin-lr-xl">退出</span>
        </el-button> -->
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
      username: "",
      password: "",
      verify: "",
      imgurl: this.refimg_get(),
    };
  },
  mounted () {
    this.$store.commit("ChangeNavbarExistFalse");
    this.profile();
  },
  methods: {
    refimg_get () {
      var num = Math.ceil(Math.random() * 10000);
      this.imgurl = "url/user/captcha?" + num;
      return this.imgurl;
    },
    pageChange (path) {
      this.$router.push(path);
    },
    login () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "post",
        url: "url/user/login",
        data: {
          username: this.username,
          password: this.password,
          code: this.verify,
        },
      })
        .then((response) => {
          if (response.data.Status == 1) {
            Toast.success("登录成功");
            this.pageChange("Swiper");
          } else Toast.fail(response.data.Result.msg);
          this.loading = false;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    profile () {
      this.loading = true;
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "get",
        url: "url/user/profile",
      })
        .then((response) => {
          if (response.data.Result.login == true) this.pageChange("Swiper");
          else this.pageChange("/");
          this.loading = false;
        })
        .catch(function (error) {
          console.log(error);
        });
    },
    logout () {
      const axios = require("axios").default;
      axios.defaults.withCredentials = true;

      axios({
        method: "get",
        url: "url/user/logout",
      })
        .then((response) => {
          console.log(response.data.Result);
        })
        .catch(function (error) {
          console.log(error);
        });
    },
  },
};
</script>

<style>
.index-base {
  width: 100%;
  height: 100vh;
  background: linear-gradient(75deg, #c6cfe8, #e9edf6);
  display: flex;
  justify-content: center;
  align-items: center;
}

.index-login-block {
  width: 700px;
  height: 425px;
  background: #fff;
  border-radius: 20px;
  display: flex;
}

.index-left,
.index-right {
  width: 50%;
  padding: 40px 0;
}

.index-left {
  padding-left: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
}

.index-right {
  padding-right: 20px;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: space-between;
}

.index-input {
  width: 80%;
}

.index-verify {
  margin-bottom: 20px;
  height: 40px;
  display: flex;
  justify-content: space-between;
}
</style>
