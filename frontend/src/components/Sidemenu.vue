<template>
  <a-menu
      :default-selected-keys="[$route.path]"
      :style="{ height: '100%'}">
    <a-menu-item key="/" @click="$router.push('/')">Home</a-menu-item>
    <a-menu-item key="/search">Search</a-menu-item>
    <a-menu-item key="/bookmarks">Bookmarks</a-menu-item>
    <a-menu-item key="/about" @click="$router.push('/about')">Admin Panel</a-menu-item>
  </a-menu>
</template>

<script>
export default {
  name: 'Sidemenu'
}
</script>

<style scoped>


</style>