<template>
  <a-layout>

    <a-layout-header>
      <Header/>
    </a-layout-header>

    <a-layout>

      <a-layout-sider>
        <Sidemenu/>
      </a-layout-sider>

      <a-layout style="padding: 20px">
        <a-layout-content
            :style="{ background: '#fff', padding: '24px', margin: 0, minHeight: '800px' }">
          <router-view></router-view>
        </a-layout-content>
      </a-layout>
    </a-layout>
  </a-layout>
</template>

<script>
import Header from '@/components/Header'
import Sidemenu from '@/components/Sidemenu'

export default {
  name: 'App',
  components: {
    Header,
    Sidemenu
  }
}
</script>

<style scoped>
 .a-layout-content {
   margin: 24px 16px;
   padding: 24px;
   background: #fff;
   minHeight: 280px;
 }
</style>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;

}
</style>
