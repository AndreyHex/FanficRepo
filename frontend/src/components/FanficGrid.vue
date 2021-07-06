<template>
  <el-row>
    <span>Recently added</span>
  </el-row>
  <el-row>
    <el-col span="6">
      <FanficCard/>
    </el-col>
    <el-col span="6">
      <FanficCard/>
    </el-col>
    <el-col span="6">
      <FanficCard/>
    </el-col>
    <el-col span="6">
      <FanficCard/>
    </el-col>
    <el-col span="6">
      <FanficCard/>
    </el-col>
    <el-col span="6">
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

</style>