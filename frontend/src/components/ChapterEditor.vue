<template>
  <template v-if="isLoading">
    <span>Loading....</span>
  </template>
  <template v-if="!isLoading">

    <a-row>
      <a-col>
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
      </a-col>
    </a-row>
    <a-row justify="center">
      <a-col span="20">

        <a-form layout="vertical">

          <a-form-item>
            <a-button type="primary" @click="submitChapters" :loading="isUploading">Save</a-button>
          </a-form-item>

          <template v-for="(chapter, index) in this.fanfic?.chapters" :key="index">
            <a-form-item>
              <a-input type="text" @change="titleHandler($event, index)"
                       :value="chapter.title">
              </a-input>
            </a-form-item>
            <a-form-item>
              <a-textarea
                  :value="chapter.text"
                  @change="textHandler($event, index)"
                  auto-size>
              </a-textarea>
            </a-form-item>
            <hr>

          </template>
          <a-button @click="addChapter"><a-icon type="plus" /> Add chapter</a-button>
        </a-form>

      </a-col>
    </a-row>

  </template>
</template>

<script>
import {mapActions, mapGetters, mapState} from "vuex";

export default {
  name: 'ChapterEditor',
  data() {
    return {
      test: 'test'
    }
  },
  computed: {
    ...mapGetters('fanfics', { isLoading: 'isLoading', isUploading: 'isUploading'}),
    ...mapState('fanfics', {
      fanfic: state => state.fanfic
    })
  },
  methods: {
    ...mapActions('fanfics', {load: 'loadFanfic', addChapter: 'addChapter', saveChapters: 'saveChapters'}),
    titleHandler(e, i) {
      this.$store.commit('fanfics/setTitle', {index: i, title: e.target.value})
    },
    textHandler(e, i) {
      this.$store.commit('fanfics/setText', {index: i, text: e.target.value})
    },
    submitChapters() {
      this.saveChapters()
    }
  },
  beforeMount() {
    this.load(this.$route.params.id)
  }
}

</script>