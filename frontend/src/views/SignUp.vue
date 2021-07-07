<template>
  <a-form ref="signup"
          :model="signup"
          :rules="rules">
    <a-form-item has-feedback name="username">
      <a-input v-model:value="signup.username" placeholder="Username"/>
    </a-form-item>
    <a-form-item has-feedback name="password">
      <a-input v-model:value="signup.password" placeholder="Password"/>
    </a-form-item>
    <a-form-item has-feedback name="confpass">
      <a-input v-model:value="signup.confpass" placeholder="Confirm password"/>
    </a-form-item>
    <a-form-model-item>
      <a-button type="primary" @click="submitForm()">
        Submit
      </a-button>
    </a-form-model-item>
  </a-form>
</template>

<script>
export default {
  name: 'SignUp',
  data() {
    let validateName = (rule, value, callback) => {
      if (value === '')
        callback(new Error('Please input the name'));
      else callback()
    };
    let validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password'));
      } else {
        callback();
      }
    };
    let validateConfPass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('Please input the password again'));
      } else if (value !== this.signup.password) {
        callback(new Error("Two inputs don't match!"));
      } else {
        callback();
      }
    };
    return {
      signup: {
        username: '',
        password: '',
        confpass: ''
      },
      rules: {
        username: [{ validator: validateName, trigger: 'change' }],
        password: [{ validator: validatePass, trigger: 'change' }],
        confpass: [{ validator: validateConfPass, trigger: 'change' }],
      }
    }
  },
  methods: {
    submitForm() {
      if(this.signup.username !== '' && this.password === this.confpass) console.log('submit')
    },
  }
}
</script>