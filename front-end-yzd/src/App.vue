<template>
  <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
  >
    <el-sub-menu index="1">
      <template #title>Number of Answers</template>
      <el-menu-item index="1-1" @click="goToPage('/answer1')">展示没有答案的 questions 的百分比</el-menu-item>
      <el-menu-item index="1-2" @click="goToPage('/answer2')">展示 answer 数量的平均值和最大值</el-menu-item>
      <el-menu-item index="1-3" @click="goToPage('/answer3')">展示 answer 数的分布</el-menu-item>
    </el-sub-menu>

    <el-sub-menu index="2">
      <template #title>Accepted Answers</template>
      <el-menu-item index="2-1" @click="goToPage('/q1')">展示有 accepted answer 的问题的百分比</el-menu-item>
      <el-menu-item index="2-2" @click="goToPage('/q2')">展示问题从提出到解决的时间间隔分布</el-menu-item>
      <el-menu-item index="2-3" @click="goToPage('/q3')">展示含有 non-accepted answer 的 upvote 数高于 accepted answer 的问题的百分比</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="3">
      <template #title>Tags</template>
      <el-menu-item index="3-1" @click="goToPage('/tag1')">展示哪些tags经常和Java tag一起出现</el-menu-item>
      <el-menu-item index="3-2" @click="goToPage('/tag2')">展示哪些 tags 或 tag 的组合得到最多的 upvotes</el-menu-item>
      <el-menu-item index="3-3" @click="goToPage('/tag3')">展示哪些 tags 或 tag 的组合得到最多的 views</el-menu-item>
    </el-sub-menu>
    <el-sub-menu index="4">
      <template #title>Users</template>
      <el-menu-item index="4-1" @click="goToPage('/user1')">展示参与 Thread 讨论的用户数量的分布</el-menu-item>
      <el-menu-item index="4-2" @click="goToPage('/user2')">从问题回答者和评论者)两个角度进行统计</el-menu-item>
      <el-menu-item index="4-3" @click="goToPage('/user3')">展示哪些用户参与 java thread 讨论最活跃</el-menu-item>
    </el-sub-menu>
  </el-menu>
  <router-view></router-view>
</template>


<script>

export default {
  name: 'App',
  data() {
    return {
      activeIndex : '/answer1',
    };
  },
  methods: {
    goToPage(path) {
      this.activeIndex = path; // 更新激活的菜单项
      this.$router.push(path); // 使用 Vue Router 跳转到指定页面
    },
    handleSelect(index) {
      this.activeIndex = index;
    },
  },
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
