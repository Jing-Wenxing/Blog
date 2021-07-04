<template>
  <div class="timeline-other">
    <div class="timeline-base">
      <div class="timeline-top">
        <div class="timeline-time">
          <div class="text-right">{{ timelinedata.timeline | formatDate }}</div>
        </div>
        <div class="timeline-iconcontent">
          <div
            class="icon iconfont timeline-icon"
            :class="timelinedata.icon"
            :style="'border: 3px solid ' + timelinedata.color + ';'"
          ></div>
        </div>
        <div
          class="timeline-up-tag"
          :style="'background: ' + timelinedata.color + ';'"
        >
          {{ timelinedata.classification }}
        </div>
        <div class="timeline-up-title">
          {{ timelinedata.title }}
        </div>
      </div>
      <div class="timeline-down">
        <div class="timeline-donw-content">
          <img
            v-if="timelinedata.img != ''"
            class="timeline-image"
            :src="'http://jingwenxing.top:9000/timelines/' + timelinedata.img"
          />
          <div class="timeline-text" v-html="timelinedata.text"></div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    timelinedata: {
      types: Object,
    },
  },
  filters: {
    formatDate: function (value) {
      var date = new Date(value * 1); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      // var Y = date.getFullYear() + '年';
      var M =
        (date.getMonth() + 1 < 10
          ? "0" + (date.getMonth() + 1)
          : date.getMonth() + 1) + "月";
      var D =
        (date.getDate() + 1 < 10 ? "0" + date.getDate() : date.getDate() + 1) +
        "日";
      return M + D;
    },
  },
};
</script>

<style>
.timeline-other {
  position: relative;
}

.timeline-base {
  width: 100%;
  color: #fff;
}

.timeline-top {
  display: flex;
  align-items: center;
}

.timeline-time {
  width: 100px;
}

.timeline-iconcontent {
  width: 101px;
  display: flex;
  justify-content: center;
}

.timeline-icon {
  width: 28px;
  height: 28px;
  line-height: 28px;
  text-align: center;

  font-size: 16px;
  font-weight: bold;
  color: #fff;

  border-radius: 28px;
  border: 3px solid #fff;
  z-index: 1;
}

.timeline-up-tag {
  margin-right: 10px;
  padding: 5px 12px;
  height: auto;
  border-radius: 30px;
  text-transform: uppercase;
  color: #fff;
  font-size: 12px;
}

.timeline-up-title {
  color: #fff;
  font-size: 18px;
  font-weight: bold;
}

/*==========*/

.timeline-down {
  display: flex;
  margin-left: 150px;
  border-left: 1px solid #313d4f;
  width: calc(100% - 151px);
}

.timeline-donw-content {
  width: 100%;
  padding: 20px 0 20px 50px;
}

.timeline-image {
  width: calc(100% - 4px);
  border: 2px solid #fff;
  border-radius: 6px;
}

.timeline-text {
  margin-top: 20px;
  color: #a9c7df;
  font-size: 18px;
  line-height: 28px;
  font-family: "Heebo", "Helvetica Neue", "Source Sans Pro", Helvetica, Arial,
    sans-serif;
}

.timeline-text a {
  text-decoration: underline;
  cursor: pointer;
  color: #fff;
}
</style>