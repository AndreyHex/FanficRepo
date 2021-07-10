<template>
  <div class="header-content">
    <div>
      <router-link to="/">
        <a-typography-title level=3 class="title">FanficRepo</a-typography-title>
      </router-link>
    </div>
    <div style="margin-left: auto">
      <LogInForm/>
    </div>
  </div>>


</template>

<script>
import LogInForm from "@/components/LogInForm";

export default {
  name: 'Header',
  components: {
    LogInForm
  }
}

</script>

<style scoped>
  .header-content {
    height: 100%;
    width: 100%;
    display: flex;
    align-items: center;
  }
  .title {
    max-width: 250px;
    color: #ffffff;
    align-self: center;
    margin: 0;
  }
</style>