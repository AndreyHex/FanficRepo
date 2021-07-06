<template>
  <el-container>
    <el-header>
      <Header/>
    </el-header>
    <el-container>
      <el-aside>
        <Sidemenu/>
      </el-aside>
      <el-main>
          <router-view/>
      </el-main>
    </el-container>

  </el-container>
</template>

<script>
import Header from '@/components/Header.vue'
import Sidemenu from "@/components/Sidemenu.vue";

export default {
  name: 'App',
  components: {
    Header,
    Sidemenu,
  }
}

</script>

<style scoped>
  .el-header {
    align-content: center;
    align-items: center;
    text-align: center;
    border-bottom: 1px solid #dcdfe6;
  }

</style>
<style>
#app {
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
</style>
