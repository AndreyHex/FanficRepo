<template>

  <a-row>
    <a-col>
      <a-form>

        <a-form-item>
          <a-textarea
              placeholder="Title"
              auto-size />
        </a-form-item>
        <a-form-item>
          <a-textarea
              placeholder="Description"
              :auto-size="{ minRows: 2 }"
          />
        </a-form-item>
        <a-form-item>
          <a-textarea
              placeholder="Fandom"
              auto-size />
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
        <div>
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


      </a-form>
    </a-col>
  </a-row>

</template>

<script>
export default {
  data() {
    return {
      tags: ['Action', 'Comedy'],
      inputVisible: false,
      inputValue: '',
      headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
      },
      fileList: [],
    };
  },
  methods: {
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
};
</script>