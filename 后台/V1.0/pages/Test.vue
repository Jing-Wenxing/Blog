<template>
  <div class="flex bg-EBF0F8">
    <div :class="getSideStatus() ? 'side' : 'side-none'"></div>
    <div :class="getSideStatus() ? 'page-content' : 'page-content-noside'">
      <!-- <input type="file" id="testimage" multiple />
      <el-button @click="upload_video(urls)">上传</el-button> -->
      <v-md-preview :text="value"></v-md-preview>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      urls: "http://jingwenxing.top:9000/swipers/%E8%A6%81%E6%B1%82.jpg?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=minioadmin%2F20210619%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Date=20210619T024040Z&X-Amz-Expires=86400&X-Amz-SignedHeaders=host&X-Amz-Signature=533bd4799755e2494b99ddde096cc5266eb13894270e83900a035827f817499e",
      value: "# 重置服务器\n```c\nint main(){ int a = 1;}\njava -jar .jar\n```",
    };
  },
  methods: {
    getSideStatus() {
      return this.$store.state.side_exist;
    },
    upload_video(url) {
      var files = document.querySelector("#testimage").files;
      for (var i = 0; i < files.length; i++) {
        var file = files[i];
        fetch(url, {
          method: "PUT",
          body: file,
        })
          .then((response) => {
            console.log(response);
          })
          .catch((e) => {
            console.error(e);
          });
      }
    },
  },
};
</script>

<style>
</style>