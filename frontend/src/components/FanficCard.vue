<template>
  <el-card shadow="hover">
    <el-image
        src="https://via.placeholder.com/300.png"
        fit="fill"/>
    <div>
      <span class="card-title">Title</span>
      <div>
        <p>Long dfsj shdjkfh lakjhf kjasd dsd asd asd asd asda sd asd asd asd asdhfkj hasdjkf hklashdfkj hasdfkljh asldkjfhlkajsdhf kjsadhfjkhasdf jkhsdkfj hasdkfh askjdhfkjlshad flkjhasd kjfhsadkjfh jsadhfjheuwirahfiuhrgiu iuerg.</p>
        <div class="tags">
          <el-tag size="small">tag1</el-tag>
          <el-tag size="small">tag3</el-tag>
          <el-tag size="small">tag4</el-tag>
          <el-tag size="small">tag5</el-tag>
        </div>
        <el-button type="primary" size="small">Read</el-button>
        <el-button type="warning" size="small" icon="el-icon-star-off" circle></el-button>
      </div>
    </div>
  </el-card>
</template>

<script>
export default {
  name: 'FanficCard'
}
</script>

<style scoped>
  .el-card {
    max-width: 300px;
    max-height: 550px;
    margin: 5px;
  }
  .card-title {
    font-size: 24px;
  }
  p {
    max-height: 150px;
    text-overflow: clip;
    text-align: justify;
  }
  .tags {
    padding: 4px;
    font-size: 14px;
  }
  .el-tag {
    margin: 2px;
  }
</style>