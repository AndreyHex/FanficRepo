<template>

  <a-row>
    <a-col span="4">
      <FanficCard/>
    </a-col>
    <a-col span="4">
      <FanficCard/>
    </a-col>
    <a-col span="4">
      <FanficCard/>
    </a-col>
    <a-col span="4">
      <FanficCard/>
    </a-col>
    <a-col span="4">
      <FanficCard/>
    </a-col>
    <a-col span="4">
      <FanficCard/>
    </a-col>
  </a-row>

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