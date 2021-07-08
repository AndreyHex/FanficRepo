<template>
  <a-menu
      :default-selected-keys="[$route.path]"
      :style="{ height: '100%'}">
    <a-menu-item key="/" @click="$router.push('/')">Home</a-menu-item>
    <a-menu-item key="/search">Search</a-menu-item>
    <template v-if="isLogined">
      <a-menu-item key="/bookmarks">Bookmarks</a-menu-item>
      <a-menu-item key="/about" @click="$router.push('/about')">Settings</a-menu-item>
      <template v-if="roles.filter(i => i.name === 'ROLE_ADMIN').length > 0">
        <a-menu-item key="/about" @click="$router.push('/about')">Admin Panel</a-menu-item>
      </template>
    </template>
  </a-menu>
</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'Sidemenu',
  computed: mapGetters('user', { roles: 'getRoles', isLogined: 'isLogined' })
}
</script>

<style scoped>


</style>