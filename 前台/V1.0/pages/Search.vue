<template>
  <div class="search-base">
    <div class="flex justify-center">
      <div class="resive flex justify-between">
        <div></div>
        <MenuButtones size="25px" icon="icon-close-bold" width="30px" bold color="000" @click="SearchShowClose()">
        </MenuButtones>
      </div>
    </div>
    <div class="search-range">
      <transition name="co-fade-in-linear" mode="out-in">
        <div v-if="!first_search" key="none">
          <div class="search-border" style="margin-top: 200px;">
            <div class="icon iconfont icon-search text-bold cursor-pointer" style="font-size: 23px; color: #757575;">
            </div>
            <input class="search-input" type="text" placeholder="搜索的内容" v-model="search_message"
              @keyup.enter="Search()">
            <div v-show="search_message.length!=0" class="icon iconfont icon-error text-bold cursor-pointer"
              style="font-size: 16px;" @click="SearchClean()">
            </div>
          </div>
        </div>
        <!-- </transition>
      <transition name="co-fade-in-linear"> -->
        <div v-else key="ok">
          <div class="search-border">
            <div class="icon iconfont icon-search text-bold cursor-pointer" style="font-size: 23px; color: #757575;">
            </div>
            <input class="search-input" type="text" placeholder="搜索的内容" v-model="search_message"
              @keyup.enter="Search()">
            <div v-show="search_message.length!=0" class="icon iconfont icon-error text-bold cursor-pointer"
              style="font-size: 16px;" @click="SearchClean()">
            </div>
          </div>

          <div class="search-result">
            <div class="search-search-searchresult">
              {{search_resultsingle | formatDate}} の 搜索结果：
            </div>
            <div class="search-searchlist">
              <div class="single" v-if="search_result_artcledata.length == 0">
                无搜索结果...
              </div>
              <div class="single" v-else v-for="item in search_result_artcledata" :key="item.id">
                <div class="flex align-center">
                  <div class="icon iconfont icon-file text-bold cursor-pointer" style="font-size: 20px; color: #000;">
                  </div>
                  <div class="title">{{item.title}}</div>
                  <div class="icon iconfont icon-comment text-bold cursor-pointer"
                    style="font-size: 14px; margin-right: 2px;"></div>
                  <div class="comment-value">
                    {{item.comment_value}}
                  </div>
                </div>
                <div class="summary">
                  {{item.summary}}
                </div>
              </div>
            </div>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script>
import MenuButtones from '~/components/Publics/MenuButtones.vue'

export default {
  components: {
    MenuButtones,
  },
  data () {
    return {
      search_message: '',
      search_resultsingle: '',
      first_search: false,
      search_result_artcledata: [
        {
          title: 'Vue',
          comment_value: '23',
          summary: 'VueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVueVue',
        },
      ],
    }
  },
  mounted () {
    this.$store.commit('ChangeExistFalse')
    // this.first_search
  },
  updated () {

  },
  methods: {
    PageChange (path) {
      this.$router.push(path)
    },
    SearchShowClose () {
      this.$router.push('/')
    },
    Search () {
      console.log(this.search_message)
      this.search_resultsingle = this.search_message
      this.search_message = ''
      this.first_search = true
    },
    SearchClean () {
      this.search_message = ''
    }
  },
  filters: {
    formatDate: function (value) {
      if (value.length > 15) {
        value = value.substring(0, 15)
        value += "..."
      }
      return value
    }
  },
}
</script>

<style>
.search-base {
  position: fixed;
  width: 100%;
  height: 100vh;
  background-color: #fff;
}

.search-range {
  width: 100%;
  height: calc(100vh - 100px);

  display: flex;
  justify-content: center;
}

/* ========== */

.search-border {
  width: 600px;
  height: 50px;
  padding: 0 20px;

  display: flex;
  justify-content: space-between;
  align-items: center;

  border: 1px solid #ccc;
  border-radius: 30px;
}

.search-input {
  margin: 0 15px;
  width: 100%;
  height: 40px;
  font-size: 20px;
  font-weight: bold;
}

/* ========== */

.search-result {
  width: 642px;
  margin-top: 20px;
}

.search-search-searchresult {
  color: #000;
  font-size: 18px;
  font-weight: bold;
}

.search-searchlist {
  margin-top: 20px;
  width: 100%;
  height: 600px;
  overflow-y: auto;
}

.search-searchlist .single {
  padding: 5px 10px;
  color: #a59a9a;
  transition: all 0.1s linear;
  cursor: pointer;
}

.search-searchlist .title {
  margin: 0 10px;
  font-size: 15px;
}

.search-searchlist .comment-value {
  font-size: 15px;
}

.search-searchlist .summary {
  margin-top: 5px;
  margin-left: 30px;
  font-size: 15px;

  word-break: keep-all;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
}

.search-searchlist .single:hover {
  color: #000;
  background: #eeeeee;
}
</style>