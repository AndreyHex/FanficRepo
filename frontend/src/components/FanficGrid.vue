<template>
  <template v-if="isLoading">
    <span>Loading page</span>
  </template>
  <template v-if="!isLoading && !page.error">
    <a-row>
      <template v-for="index in page.content.length" :key="index">
        <a-col :xs="24" :sm="8" :md="6" :xl="4">
          <FanficCard :index="index-1"/>
        </a-col>
      </template>
    </a-row>
    <a-row justify="center">
      <a-col>
        <a-pagination
            :current="page.number+1"
            :page-size="page.size"
            :total="page.totalElements" @change="onChange" />
      </a-col>
    </a-row>


  </template>

</template>

<script>
import FanficCard from "@/components/FanficCard";
import { mapGetters, mapActions } from "vuex"

export default {
  name: 'FanficGrid',
  props: ['username'],
  components: {
    FanficCard
  },
  computed: mapGetters('page', { page: 'getPage',  isLoading: 'isLoading' }),
  methods: {
    ...mapActions('page', { loadPage: 'loadPage'} ),
    onChange(current) {
      this.loadPage({
        username: this.username,
        page: current-1
      })
    },
  },
  created() {
    if(isNaN(this.page)) this.loadPage({ username: this.username})
  }
}
</script>

<style scoped>
  h2 {
    font-size: xx-large;
    margin: 5px;
  }
</style>