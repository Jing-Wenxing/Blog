<template>
  <div
    :class="[
      getNavbarTop() && getNavbarStatu()
        ? 'navbar-base-dark'
        : 'navbar-base-light',
      getNavbarExist() ? 'navbar-base' : 'navbar-base-none',
    ]"
    @mouseenter="MouseEnter()"
    @mouseleave="MouseLeave()"
  >
    <div class="navbar-range">
      <div class="navbar-logo">
        <MenuButtones
          class="navbar-menu-logo"
          size="40px"
          icon="icon-zhifeiji"
          width="40px"
          :color="getNavbarColor()"
          @click="PageChange('/')"
        ></MenuButtones>
      </div>
      <div class="navbar-contents">
        <MenuButtones
          class="margin-right-sm"
          size="18px"
          bold
          :color="getNavbarColor()"
          @click="PageChange('/')"
          >首页
        </MenuButtones>
        <MenuButtones
          class="margin-lr-sm"
          size="18px"
          bold
          :color="getNavbarColor()"
          @click="PageChange('/Blog')"
          >博客
        </MenuButtones>
        <!-- <MenuButtones
          class="margin-lr-sm"
          size="18px"
          bold
          :color="getNavbarColor()"
          @click="PageChange('/Classification')"
          >分类
        </MenuButtones> -->
        <MenuButtones
          class="margin-left-sm"
          size="18px"
          bold
          :color="getNavbarColor()"
          @click="PageChange('/Timeline')"
        >
          时光
        </MenuButtones>
      </div>
      <div class="navbar-operats">
        <!-- <MenuButtones
          class="navbar-menu-modular"
          size="25px"
          icon="icon-modular"
          width="30px"
          bold
          :color="getNavbarColor()"
          @click="test()"
        >
        </MenuButtones> -->
        <MenuButtones
          class="navbar-menu-user"
          size="25px"
          icon="icon-user"
          width="30px"
          bold
          :color="getNavbarColor()"
          @click="toBackPage()"
        >
        </MenuButtones>
        <!-- <MenuButtones
          class="navbar-menu-search"
          size="25px"
          icon="icon-search1"
          width="30px"
          bold
          :color="getNavbarColor()"
          @click="PageChange('/Search')"
        ></MenuButtones> -->
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
  methods: {
    PageChange(path) {
      this.$router.push(path);
    },
    test() {
      console.log("测试");
    },
    // 顶部判断
    getNavbarTop() {
      return this.$store.state.navbar_istop;
    },
    // 字体颜色
    getNavbarColor() {
      if (
        this.$store.state.navbar_istop &&
        this.$store.state.navbar_statu_islight &&
        this.$store.state.navbar_font
      )
        return "fff";
      else return "000";
    },
    // 背景颜色
    getNavbarStatu() {
      return this.$store.state.navbar_statu_islight;
    },
    // 是否存在
    getNavbarExist() {
      return this.$store.state.navbar_exist;
    },
    MouseEnter() {
      if (this.$store.state.navbar_statu_islight)
        this.$store.commit("ChangeTopFalse");
    },
    MouseLeave() {
      if (this.$store.state.navbar_statu_islight)
        this.$store.commit("ChangeTopTrue");
    },
    toBackPage() {
      window.open("http://blog.jingwenxing.top/");
    },
  },
};
</script>

<style>
.navbar-base-none {
  display: none;
}

.navbar-base {
  height: 100px;
  width: 100%;

  position: fixed;

  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 99;
  transition: all 0.2s linear;
}

.navbar-base-dark {
  background: transparent;
  transition: all 0.2s linear;
}

.navbar-base-light {
  transition: all 0.2s linear;
  background: rgba(255, 255, 255, 0.85);
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);
  transition: all 0.2s linear;
}

.navbar-logo .logo-range {
  font-size: 32px;
  transition: all 0.4s linear;
}

.navbar-range {
  width: 1700px;
  height: 100px;
  margin: 0 50px;

  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.2s linear;
}

.navbar-logo {
  width: 20%;
}

.navbar-contents {
  width: 60%;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.2s linear;
}

.navbar-operats {
  width: 20%;
  display: flex;
  flex-direction: row-reverse;
  transition: all 0.2s linear;
}

.navbar-menu-search {
  margin-right: 10px;
  transition: all 0.2s linear;
}

.navbar-menu-user {
  margin-left: 10px;
  transition: all 0.2s linear;
}

.navbar-menu-logo {
  transition: all 0.2s linear;
}

.navbar-menu-modular {
  display: none !important;
  transition: all 0.2s linear;
}

@media screen and (max-width: 1700px) {
  .navbar-range {
    width: 100%;
    margin: 0 50px;
  }
}

@media screen and (max-width: 1024px) {
  .navbar-logo {
    width: 50%;
  }

  .navbar-contents {
    display: none;
  }

  .navbar-range {
    width: 100%;
    margin: 0 25px;
  }

  .navbar-operats {
    width: 50%;
  }

  .navbar-menu-search {
    display: none !important;
  }

  /* .navbar-menu-user {
    display: none !important;
  } */

  .navbar-menu-modular {
    display: flex !important;
    transition: all 0.2s linear;
  }
}
</style>