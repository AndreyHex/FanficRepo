<template>
  <template v-if="isLoading">
    <span>Loading page</span>
  </template>
  <template v-if="!isLoading">
    <a-row>
      <template v-for="index in page.content.length" :key="index">
        <a-col span="4">
          <FanficCard :index="index-1"/>
        </a-col>
      </template>
    </a-row>
  </template>

</template>

<script>
import FanficCard from "@/components/FanficCard";
import { mapGetters, mapActions } from "vuex"

export default {
  name: 'FanficGrid',
  components: {
    FanficCard
  },
  computed: mapGetters('page', { page: 'getPage',  isLoading: 'isLoading' }),
  methods: {
    ...mapActions('page', ['loadPage']),
  },
  created() {
    this.loadPage()
  }
}
</script>

<style scoped>
  h2 {
    font-size: xx-large;
    margin: 5px;
  }
</style>