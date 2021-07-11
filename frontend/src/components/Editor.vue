<template>

  <a-row style="padding: 10px">
    <a-col span="12">
      <a-form layout="vertical">

        <a-form-item label="Title:">
          <a-input
              v-model:value="title"
              placeholder="title"
              type="textarea"> {{ fanfic?.title }} </a-input>
        </a-form-item>
        <a-form-item label="Description:">
          <a-textarea
              v-model:value="description"
              placeholder="description"
              type="textarea"
              :auto-size="{ minRows: 2, maxRows: 6 }"
          >  {{ fanfic?.description }} </a-textarea>
        </a-form-item>
        <a-form-item label="Fandom:">
          <a-input
              v-model:value="fandom"
              placeholder="Fandom"
              type="textarea"
              auto-size> {{ fanfic?.fandom }}  </a-input>
        </a-form-item>

        <!-- tags -->
        <div>
          <template v-for="tag in tags" :key="tag">
            <a-tag v-if="tag.length > 20" :closable="true" @close="() => handleTagClose(tag)">
              {{ `${tag.slice(0, 20)}...` }}
            </a-tag>
            <a-tag v-else :closable="true" @close="() => handleTagClose(tag)">
              {{ tag }}
            </a-tag>
          </template>
          <a-input
              v-if="inputVisible"
              ref="input"
              type="text"
              size="small"
              :style="{ width: '78px' }"
              :value="inputValue"
              @change="handleInputChange"
              @blur="handleInputConfirm"
              @keyup.enter="handleInputConfirm"
          />
          <a-tag v-else style="background: #fff; borderStyle: dashed;" @click="showInput">
            <a-icon type="plus" /> New Tag
          </a-tag>
        </div>

        <!-- image upload -->
        <div style="padding: 10px;">
          <a-upload
              list-type="picture"
              action="/api/upload"
              :file-list="fileList"
              :headers="headers"
              @change="handleImageChange"
          >
            <template v-if="fileList.length === 0">
              <a-button> <a-icon type="upload" /> Upload </a-button>
            </template>

          </a-upload>
        </div>

        <a-form-item style="padding: 10px">
          <a-button type="primary" :loading="isUploading" @click="submitFanfic">Save Fanfic</a-button>
        </a-form-item>
        <template v-if="error.length > 0">
          <a-alert type="error" :message="error" banner />
        </template>


      </a-form>
    </a-col>
  </a-row>

</template>

<script>
import {mapGetters, mapActions} from "vuex";

export default {
  name: 'Editor',
  props: ['fanficId'],
  data() {
    return {
      title: '',
      fandom: '',
      description: '',
      img: {},
      tags: ['Action', 'Comedy'],
      inputVisible: false,
      inputValue: '',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      },
      fileList: [],
      error: ''
    };
  },
  computed: mapGetters('fanfics', {
    isUploading: 'isUploading',
    status: 'getStatus',
    fanfic: 'getFanfic'
  }),
  methods: {
    ...mapActions('fanfics', {save: 'saveFanfic', load: 'loadFanfic'}),
    submitFanfic() {
      if(this.title.length > 0 && this.description.length > 0) {
        this.save({
          title: this.title,
          description: this.description,
          fandom: this.fandom,
          tags: this.tags,
          imgId: this.fileList[0].response.id
        }).then(() => this.$router.push('/myfanfics'))
      }
    },
    handleTagClose(removedTag) {
      const tags = this.tags.filter(tag => tag !== removedTag);
      this.tags = tags;
    },
    showInput() {
      this.inputVisible = true;
      this.$nextTick(function() {
        this.$refs.input.focus();
      });
    },

    handleInputChange(e) {
      this.inputValue = e.target.value;
    },

    handleInputConfirm() {
      const inputValue = this.inputValue;
      let tags = this.tags;
      if (inputValue && tags.indexOf(inputValue) === -1) {
        tags = [...tags, inputValue];
      }
      Object.assign(this, {
        tags,
        inputVisible: false,
        inputValue: '',
      });
    },
    handleImageChange(info) {
      this.fileList = info.fileList;
    },
  },
  beforeMount() {
    if(this.fanficId) this.load(this.fanficId)
  }
};
</script>
