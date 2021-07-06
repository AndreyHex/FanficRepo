<template>
    <el-row>
      <el-menu router="true">
        <el-menu-item index="/">
          <span>Home</span>
        </el-menu-item>
        <el-menu-item index="/">
          <span>Search</span>
        </el-menu-item>
        <el-menu-item index="/about">
          <span>About</span>
        </el-menu-item>
      </el-menu>
    </el-row>
</template>

<script>
export default {
  name: 'Sidemenu'
}
</script>

<style scoped>
  .el-menu {
    width: 250px;
    text-align: end;
  }
</style>