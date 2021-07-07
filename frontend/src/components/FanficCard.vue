<template>
  <a-card  hoverable shadow="hover" class="card">
    <template v-slot:cover>
      <img src="https://via.placeholder.com/500"/>
    </template>
    <template v-slot:actions>
      <a-button type="primary" size="small">Read</a-button>
      <a-button type="warning" size="small">Circle</a-button>
    </template>
    <a-card-meta
        title="Long dfsj shdjkfh lakjhf kjasd dsd asd asd asd asda sd asd asd asd asdhfkj hasdjkf hklashdfkj hasdfkljh asldkjfhlkajsdhf kjsadhfjkhasdf jk"
        description="Long dfsj shdjkfh lakjhf kjasd dsd asd asd asd asda sd asd asd asd asdhfkj hasdjkf hklashdfkj hasdfkljh asldk">
    </a-card-meta>
    <div>
      <a-tag>tagtag</a-tag>
      <a-tag>tagtag</a-tag>
      <a-tag>tagtag</a-tag>
      <a-tag>tagtag</a-tag>
    </div>
  </a-card>
</template>

<script>
export default {
  name: 'FanficCard'
}
</script>

<style scoped>
  .card {
    margin: 10px;
  }
</style>