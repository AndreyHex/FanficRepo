<template>
  <a-card  hoverable shadow="hover" class="card">
    <template v-slot:cover>
      <template v-if="!fanfic(index).imgUrl">
        <a-empty />
      </template>
      <img :src="fanfic(index).imgUrl"/>
    </template>
    <template v-slot:actions>
      <a-button type="primary" size="small" @click="$router.push('/fanfic/'+fanfic(index).id)">Read</a-button>
      <template v-if="fanfic(index).username === username">
        <a-button type="default" size="small" @click="$router.push('/myfanfics/'+fanfic(index).id+'/details')">Edit</a-button>
      </template>
      <template v-else>
        <div></div>
      </template>
    </template>
    <a-card-meta>
      <template v-slot:title>{{fanfic(index).title}}</template>
      <template v-slot:description>{{fanfic(index).description}}</template>
    </a-card-meta>
    <div>
      <template v-for="(tag, i) in fanfic(index).tags" :key="i">
        <a-tag>{{ tag }}</a-tag>
      </template>
    </div>
  </a-card>
</template>

<script>
import { mapGetters } from "vuex"

export default {
  name: 'FanficCard',
  props: ['index'],
  computed: {
    ...mapGetters('page', { fanfic: 'getFanficByIndex' }),
    ...mapGetters('user', { username: 'getUsername'})
  }
}
</script>

<style scoped>
  .card {
    margin: 6px;
  }
</style>