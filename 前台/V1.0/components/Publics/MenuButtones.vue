<template>
  <div class="menubuttones-base-nomarl menubuttones-effect" :class="['menubuttones-color-'+color,
  nopadding?'menubuttones-base-nopadding':'']"
    :style="'width: ' +width+';'+'height: ' +height+';'+'line-height: ' +height+';'+'color: #' +color+';'"
    @click="handleClick">
    <div v-if="content_is" class="menubuttones-bottondown" :class="bold?'text-bold':''"
      :style="'font-size: ' +size+';'">{{content_text}}</div>
    <div v-else class="icon iconfont menubuttones-bottondown" :class="[icon, bold?'text-bold':'']"
      :style="'font-size: ' +size+';'"></div>
  </div>
</template>

<script>
export default {
  props: {
    width: {
      type: String,
      default: 'auto'
    },
    height: {
      type: String,
      default: '100px'
    },
    size: {
      type: String,
      default: '16px'
    },
    icon: {
      type: String,
      default: ''
    },
    color: {
      type: String,
      default: ''
    },
    bold: {
      type: Boolean,
      default: false
    },
    nopadding: {
      type: Boolean,
      default: false
    }
  },
  data () {
    return {
      content_is: true,
      content_text: ''
    }
  },
  mounted () {
    this.ContentChange()
  },
  updated () {
    this.ContentChange()
  },
  methods: {
    ContentChange () {
      if (this.$slots.default == null) {
        this.content_is = false
      }
      else {
        this.content_is = true
        this.content_text = this.$slots.default[0].text
      }
    },
    handleClick (evt) {
      this.$emit('click', evt);
    }
  }
}
</script>

<style scoped>
.menubuttones-base-nomarl {
  padding: 0 15px;
  height: 100px;
  position: relative;
  transition: all 0.2s linear;
  cursor: pointer;
  display: flex;
  justify-content: center;
  align-items: center;
}

.menubuttones-base-nopadding {
  padding: 0 !important;
}

.menubuttones-base-nomarl .menubuttones-bottondown {
  transition: all 0.2s linear;
  display: flex;
}

.menubuttones-base-nomarl:hover .menubuttones-bottondown {
  margin-top: 5px;
}

.menubuttones-effect:before,
.menubuttones-effect:after,
.menubuttones-effect > .ui-border-element:before,
.menubuttones-effect > .ui-border-element:after {
  content: "";
  position: absolute;
}

.menubuttones-effect:after {
  left: 50%;
  right: 50%;
  bottom: 0px;
  -webkit-transition-property: all;
  -moz-transition-property: all;
  -o-transition-property: all;
  transition-property: all;
  -webkit-transition-timing-function: cubic-bezier(0, 0.98, 0.98, 1);
  -moz-transition-timing-function: cubic-bezier(0, 0.98, 0.98, 1);
  -o-transition-timing-function: cubic-bezier(0, 0.98, 0.98, 1);
  transition-timing-function: cubic-bezier(0, 0.98, 0.98, 1);
  -webkit-transition-duration: 250ms;
  -moz-transition-duration: 250ms;
  -o-transition-duration: 250ms;
  transition-duration: 250ms;
  -webkit-transition-delay: 0s;
  -moz-transition-delay: 0s;
  -o-transition-delay: 0s;
  transition-delay: 0s;
}
.menubuttones-effect:hover:after {
  left: 0%;
  right: 0%;
}
.menubuttones-effect:not(:hover):after {
  -webkit-transition-delay: 0s;
  -moz-transition-delay: 0s;
  -o-transition-delay: 0s;
  transition-delay: 0s;
}

.menubuttones-color-000:after {
  border-bottom: 2px solid #000; /* 提供修改颜色 */
}

.menubuttones-color-fff:after {
  border-bottom: 2px solid #fff; /* 提供修改颜色 */
}
</style>