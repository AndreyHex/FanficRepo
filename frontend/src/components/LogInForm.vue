<template>
  <div style="display: flex">
    <template v-if="!isLogined">
      <a-form layout="inline" :model="logForm" @submit.prevent="handleSubmit">
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
    </template>
    <template v-if="isLogined">
      <a-button
          type="danger"
          @click="handleLogout"
      >Log Out</a-button>
    </template>
  </div>


</template>

<script>
import {mapActions, mapGetters} from "vuex";

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
  computed: {
    ...mapGetters('user', { isLogined: 'isLogined' })
  },
  methods: {
    ...mapActions('user', { login: 'login', logout: 'logout' }),
    handleSubmit() {
      this.login(this.logForm)
      console.log(this.logForm);
    },
    handleLogout() {
      this.logout()
      this.$router.push('/')
    }
  },
};
</script>