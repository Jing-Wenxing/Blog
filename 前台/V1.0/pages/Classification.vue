<template>
  <div class="class-base">
    <div class="relative-up">
      <div class="class-content">
        <div class="range-left">

        </div>
        <div class="range-right">
          <div class="class-swiper-left">
            <div class="icon iconfont icon-arrow-left-bold" style="font-size: 30px;"
              :style="movedistance!=0?'':'display: none;'" @click="SwiperPrev()"></div>
          </div>
          <van-swipe class="class-swiper" ref="classswipe" :show-indicators="false" :loop="false" :width="230">
            <van-swipe-item class="swiper-single" v-for="(item, i) in classlist" :key="i">
              <div :class="selectswiper!=i?'single-range':'single-rangeselect'" @click="OpenSwiperSingle(i)">
                <div class="swiper-content-padding color-fff text-20 text-cut">
                  <div class="padding-tb-sm padding-lr-xs color-fff text-20 text-cut">
                    {{item.class}}
                  </div>
                </div>
                <img class="single-img" :src="item.img">
              </div>
            </van-swipe-item>
          </van-swipe>
          <div class="class-swiper-right">
            <div class="icon iconfont icon-arrow-right-bold" style="font-size: 30px;"
              :style="movedistance+5!=classlist.length&&classlist.length>5?'':'display: none;'" @click="SwiperNext()">
            </div>
          </div>
        </div>
      </div>
      <div class="class-bottom">
        <div class="range-left">
          <div class="class-description">
            <div class="flex align-center margin-bottom-sm">
              <div class="title margin-right">
                博客项目·技术说明
              </div>
              <div class="tag">123</div>
            </div>

            <div class="summary">
              Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
              dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex
              ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu
              fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt
              mollit anim id est laborum.
            </div>
          </div>
        </div>
        <div class="range-center">
          <div class="range-up">
            <div class="iconrange">
              <div class="icon iconfont icon-link" style="font-size: 28px; color: #59FDEF;"></div>
            </div>
          </div>
          <div class="range-down">
            <div class="bg-left"></div>
            <div class="bg-right"></div>
          </div>
        </div>
        <div class="range-right flex justify-center align-center">
          <div style="color: #fff; font-size: 22px;">Let't go</div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import BlogList from '~/components/Publics/BlogList.vue'

export default {
  components: {
    BlogList,
  },
  mounted () {
    this.$store.commit('ChangeTopTrue')
    this.$store.commit('ChangeStatuTrue')
    this.$store.commit('ChangeFontTrue')
    this.$store.commit('ChangeExistTrue')
  },
  data () {
    return {
      movedistance: 0,
      selectswiper: 0,
      classlist: [
        {
          class: '博客项目·技术说明',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'
        },
        {
          class: '2cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '3cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '4cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '5cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '6cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '7cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '8cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '9cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
        {
          class: '10cs',
          img: 'https://pic.gksec.com/2021/05/25/74ce7ab6ea961/pexels-brett-sayles-6850336.jpg'

        },
      ]
    }
  },
  methods: {
    SwiperPrev () {
      this.$refs.classswipe.prev()
      this.movedistance--
      if (this.selectswiper >= this.movedistance + 4)
        this.selectswiper = this.movedistance + 4
    },
    SwiperNext () {
      this.$refs.classswipe.next()
      this.movedistance++
      if (this.selectswiper < this.movedistance)
        this.selectswiper = this.movedistance
    },
    OpenSwiperSingle (i) {
      this.selectswiper = i
    }
  }
}
</script>

<style scope>
.class-base {
  width: 100%;
  height: 100vh;
  position: relative;
  background: #377d8a;
}

/* ===== 层叠上部 ===== */

/* 分类板块 */

.class-content {
  padding-top: 100px;
  width: 100%;
  height: calc(80vh - 100px);
  display: flex;
}

.class-content .range-left {
  width: 35%;
}

.class-content .range-right {
  width: 65%;
  display: flex;
  align-items: center;
  justify-content: center;
}

/* 分类轮播 */

.class-swiper {
  width: 1150px;
  height: 550px;
  display: flex;
  align-items: center;
  transition: all 1s linear;
}

.class-swiper .swiper-single {
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 1s linear;
}

.class-swiper .single-range {
  position: relative;
  width: 200px;
  height: 500px;
  background: #ddd;
  transition: all 0.2s linear;
  border-radius: 20px;
  cursor: pointer;
}

.class-swiper .single-range:hover {
  box-shadow: 0px 4px 7px rgba(0, 0, 0, 0.5);
  transition: all 0.2s linear;
}

.class-swiper .single-rangeselect {
  position: relative;
  width: 210px;
  height: 530px;
  background: #ddd;
  transition: all 0.2s linear;
  box-shadow: 0px 4px 7px rgba(0, 0, 0, 0.5);
  border-radius: 20px;
  cursor: pointer;
}

.class-swiper .single-range .single-img {
  position: absolute;
  width: 200px;
  height: 500px;
  transition: all 0.2s linear;
  border-radius: 20px;
  cursor: pointer;
  z-index: 0;
}

.class-swiper .single-rangeselect .single-img {
  position: absolute;
  width: 210px;
  height: 530px;
  transition: all 0.2s linear;
  border-radius: 20px;
  cursor: pointer;
  z-index: 0;
}

.class-swiper .single-range .swiper-content-padding {
  width: 200px;
  height: 500px;
  position: absolute;
  z-index: 1;
  transition: all 0.2s linear;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}

.class-swiper .single-rangeselect .swiper-content-padding {
  width: 210px;
  height: 530px;
  position: absolute;
  z-index: 1;
  transition: all 0.2s linear;
  display: flex;
  justify-content: center;
  align-items: flex-end;
}

.class-swiper-left,
.class-swiper-right {
  width: 35px;
  height: 550px;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: all 0.2s linear;
  cursor: pointer;
}

/* 分类底部 */

.class-bottom {
  width: 100%;
  height: 20vh;
  display: flex;
}

.class-bottom .range-left {
  width: calc(80% - 25px);
  background: rgba(0, 0, 0, 0.3);
}

.class-description {
  padding: 25px 50px;
  height: calc(20vh - 50px);
}

.class-description .title {
  color: #fff;
  font-size: 20px;
  font-weight: bold;
}

.class-description .tag {
  padding: 5px 15px;
  color: #fff;
  font-size: 15px;
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid #fff;
  border-radius: 20px;
}

.class-description .summary {
  color: #fff;
  font-size: 18px;
}

.class-bottom .range-right {
  width: calc(20% - 25px);
  background: rgba(0, 0, 0, 0.55);
}

.class-bottom .range-center {
  width: 50px;
}

.class-bottom .range-center .range-up {
  position: absolute;
  z-index: 1;
  width: 50px;
  height: 20vh;
  display: flex;
  justify-content: center;
  align-items: center;
}

.class-bottom .range-center .range-up .iconrange {
  width: 50px;
  height: 50px;
  border-radius: 25px;
  background: rgba(255, 255, 255, 0.35);
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.2s linear;
  cursor: pointer;
}

.class-bottom .range-center .range-up .iconrange:hover {
  box-shadow: 0px 0px 7px rgba(255, 255, 255, 0.5);
}

.class-bottom .range-center .range-down {
  position: absolute;
  z-index: 0;
  width: 50px;
  display: flex;
  height: 20vh;
}

.class-bottom .range-center .range-down .bg-left {
  width: 50%;
  background: rgba(0, 0, 0, 0.3);
}

.class-bottom .range-center .range-down .bg-right {
  width: 50%;
  background: rgba(0, 0, 0, 0.55);
}
</style>