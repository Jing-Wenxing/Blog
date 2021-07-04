<template>
  <div class="index-base">
    <div class="relative-down">
      <van-swipe ref="swipe" class="index-swiper" :autoplay="0" :duration="750" @change="getContent">
        <van-swipe-item v-for="item in swiper_data" :key="item.id">
          <img class="image" :src="'http://jingwenxing.top:9000/swipers/' + item.img" />
        </van-swipe-item>
        <template #indicator>
          <div class="index-swiper-indicator">
            <div class="swipesingle-range">
              <van-swipe ref="swipesingle" :autoplay="0" :show-indicators="false" :duration="800">
                <van-swipe-item class="single" v-for="index in swiper_data.length" :key="index">
                  <div class="single-text">
                    {{ index | pageCompletion }}
                  </div>
                </van-swipe-item>
              </van-swipe>
            </div>
          </div>
        </template>
      </van-swipe>
    </div>

    <div class="relative-up">
      <div class="index-contentss">
        <!-- <van-swipe ref="swipecontent" :autoplay="0" :show-indicators="false" :duration="800">
          <van-swipe-item v-for="item in swiper_data" :key="item.id"> -->
        <div class="title">
          {{ title }}
        </div>
        <div class="text">
          {{ text }}
        </div>
        <!-- </van-swipe-item>
        </van-swipe> -->
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data () {
    return {
      // 轮播图需要：
      swiper_data: [],
      // ==========
      current: 0,
      title: "",
      text: "",
    };
  },
  mounted () {
    this.$store.commit("ChangeTopTrue");
    this.$store.commit("ChangeStatuTrue");
    this.$store.commit("ChangeFontTrue");
    this.$store.commit("ChangeExistTrue");

    // 鼠标滚轮监听函数
    window.addEventListener("mousewheel", this.rollScroll);

    const axios = require("axios").default;
    axios.defaults.withCredentials = true;

    axios({
      method: "get",
      url: "url/swiper/getALlShowSwiperData",
    }).then((response) => {
      this.swiper_data = response.data.Result;
      this.title = this.swiper_data[0].title;
      this.text = this.swiper_data[0].text;
    });
  },
  destroyed () {
    window.removeEventListener("mousewheel", this.rollScroll);
  },
  methods: {
    rollScroll (e) {
      if (e.deltaY > 0) {
        this.$refs.swipe.next();
        this.$refs.swipesingle.next();
        this.current++;
      }
      if (e.deltaY < 0) {
        this.$refs.swipe.prev();
        this.$refs.swipesingle.prev();
        this.current--;
      }
    },
    getContent (index) {
      this.title = this.swiper_data[index].title;
      this.text = this.swiper_data[index].text;
    },
  },
  filters: {
    pageCompletion (data) {
      if (data < 10) return "0" + data;
      else return data;
    },
  },
};
</script>

<style scope>
.index-base {
  width: 100%;
  height: 100vh;
  position: relative;
}

.index-base .relative-up {
  width: 100%;
  height: 100vh;
  position: absolute;
  display: flex;
  justify-content: center;
  align-items: center;
  z-index: 1;
  background: rgba(0, 0, 0, 0.2);
}

.index-base .relative-down {
  width: 100%;
  height: 100vh;
  position: absolute;
  z-index: 0;
}

/* ===== 层叠下部轮播图组件 ===== */

.index-swiper {
  width: 100%;
  height: 100vh;
  background: rgb(150, 150, 150);
}

.index-swiper .image {
  width: 100%;
  height: 100vh;
  object-fit: cover;
  object-position: center;
}

/* 轮播图指示器部分 */
.index-swiper-indicator {
  position: absolute;
  top: 0px;
  left: 0px;
  width: 100%;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}

.index-swiper-indicator .range {
  width: 100px;
}

.index-swiper-indicator .swipesingle-range {
  width: 50px;
  height: 100px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.index-swiper-indicator .single {
  width: 50px;
  display: flex;
  justify-content: center;
  align-items: center;
  background: transparent;
}

.index-swiper-indicator .single-text {
  font-size: 28px;
  color: #fff;
  border-radius: 10px;
}

/* ===== 层叠上部轮播图数据 ===== */
.index-contentss {
  width: 800px;
}

.index-contentss .title {
  width: 100%;
  padding: 30px 0;
  color: #fff;
  text-align: center;
  font-size: 50px;
  font-weight: bold;
  text-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);
}

.index-contentss .text {
  width: calc(100% - 50px);
  padding: 30px 25px;
  border-radius: 20px;
  color: #f0f0f0;
  text-align: center;
  font-size: 20px;
  white-space: normal;
  word-break: break-all;
  background: rgba(0, 0, 0, 0.5);
  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.25);
}
</style>