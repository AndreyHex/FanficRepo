<template>
  <div style="display: flex">
    <a-form layout="inline" :model="logForm" @submit="handleSubmit">
      <a-form-item>
        <a-input v-model:value="logForm.username" placeholder="Username">
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input v-model:value="logForm.password" type="password" placeholder="Password"/>
      </a-form-item>
      <a-form-item>
        <a-button
            type="primary"
            html-type="submit"
            :disabled="logForm.username === '' || logForm.password === ''"
        >Log In</a-button>
      </a-form-item>
    </a-form>

    <a-button
        type="primary"
        @click="$router.push('/signup')"
    >Sign Up</a-button>
  </div>


</template>

<script>
export default {
  name: "LogInForm",
  data() {
    return {
      test: '',
      logForm: {
        username: '',
        password: ''
      }
    };
  },
  methods: {
    handleSubmit(e) {
      console.log(this.logForm.toString()+'\n'+e);
    },
  },
};
</script>