<template>
  <a-row>
    <a-button @click="$router.push('/myfanfics/create')">Create new Fanfic</a-button>
  </a-row>

    <router-view :username="this.username"/>



</template>

<script>
import {mapGetters} from "vuex";

export default {
  name: 'MyFanfics',
  computed: mapGetters('user', { username: 'getUsername'})
}
</script>