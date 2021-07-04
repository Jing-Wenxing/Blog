<template>
  <div class="stdtable-base">
    <div class="stdtable-opreate">
      <div></div>
      <div class="flex">
        <div class="button">新建</div>
        <div class="button margin-lr-sm">修改</div>
        <div class="button">删除</div>
      </div>
    </div>
    <!-- 表格区域 -->
    <div class="stdtable-tablerange">
      <!-- 表头区域 -->
      <div class="head">
        <div class="select">123</div>
      </div>
      <!-- 表格内容 -->
      <div></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    // tabledata: {
    //   type: String,
    // },
  },
  data() {
    return {
      table_range: ["", "", "", "", "", ""],
    };
  },
};
</script>

<style>
.stdtable-base {
  border-radius: 10px;
  box-shadow: 0px 2px 5px rgba(168, 180, 227, 0.75);
}

.stdtable-opreate {
  padding: 20px;
  border-radius: 10px 10px 0 0;
  width: calc(100% - 40px);
  background: #f7f9ff;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.stdtable-opreate .button {
  width: 75px;
  height: 25px;
  line-height: 25px;
  text-align: center;
  background: #879fe5;
  font-size: 12px;
  color: #fff;
  border-radius: 7.5px;
  cursor: pointer;
}

.stdtable-tablerange {
  width: calc(100% - 40px);
}

.stdtable-tablerange .head {
  width: calc(100% - 40px);
  background: #f0f5ff;
}

.stdtable-tablerange .head .select {
  width: 75px;
  height: 50px;
}

/* .stdtable-tablerange .head .head-content {
  height: 50px;
} */
</style>