<template>
  <el-row>
    <el-col>
      <h2>Recently added</h2>
    </el-col>
  </el-row>
  <el-row>
    <el-col span="3">
      <FanficCard/>
    </el-col>
    <el-col span="3">
      <FanficCard/>
    </el-col>
    <el-col span="3">
      <FanficCard/>
    </el-col>
    <el-col span="3">
      <FanficCard/>
    </el-col>
    <el-col span="3">
      <FanficCard/>
    </el-col>
    <el-col span="3">
      <FanficCard/>
    </el-col>
  </el-row>
</template>

<script>
import FanficCard from "@/components/FanficCard";

export default {
  name: 'FanficGrid',
  components: {
    FanficCard
  }
}
</script>

<style scoped>
  h2 {
    font-size: xx-large;
    margin: 5px;
  }
</style>