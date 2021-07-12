<template>
  <a-row justify="center">
    <a-col span="18">
      <a-descriptions :title="fanfic?.title">
        <a-descriptions-item label="UserName">
          {{ fanfic?.username }}
        </a-descriptions-item>
        <a-descriptions-item label="Fandom">
          {{ fanfic?.fandom }}
        </a-descriptions-item>
        <a-descriptions-item label="Description">
          {{ fanfic?.description }}
        </a-descriptions-item>
      </a-descriptions>

      <template v-if="fanfic?.username === username">
        <a-button type="default" @click="$router.push('/myfanfics/'+fanfic(index).id+'/details')">Edit</a-button>
      </template>
    </a-col>
  </a-row>
  <hr>
  <a-row>

    <a-collapse v-model="activeKey" style="width: 100%">
      <template v-for="(chapter, index) in this.fanfic?.chapters" :key="index">
        <a-collapse-panel :header="'['+chapter.number+'] '+chapter.title">
          <p>{{ chapter.text }}</p>
        </a-collapse-panel>
      </template>
    </a-collapse>

  </a-row>

</template>

<script>
import {mapActions, mapGetters} from "vuex";

export default {
  name: 'Fanfic',
  data() {
    return {
      activeKey: ['1']
    }
  },
  computed: {
    ...mapGetters('fanfics', {fanfic: 'getFanfic'}),
    ...mapGetters('user', { username: 'getUsername'})
  },
  methods: {
    ...mapActions('fanfics', {load: 'loadFanfic'})
  },
  beforeMount() {
    this.load(this.$route.params.id)
  }
}
</script>