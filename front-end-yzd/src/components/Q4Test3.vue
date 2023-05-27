<template>
  <el-card v-for="user in userList" :key="user.id" class="user-card">
    <template #header>
      <div class="card-header">
        <img :src="user.profileImage" alt="Avatar" class="avatar" />
        <div class="header-text">{{ user.displayName }}</div>
      </div>
    </template>
    <div class="card-content">
      <p class="user-info">
        <span class="info-label">UserID:</span> {{ user.userId }}
      </p>
      <p class="user-info">
        <span class="info-label">活跃程度:</span> {{ user.key }}
      </p>
      <!-- Add more user information fields here -->
      <a :href="user.link" class="details-link">查看详情</a>
    </div>
  </el-card>
</template>

<script>
import axios from 'axios';

export default {
  name: "Q4Test3",
  data() {
    return {
      userList: []
    };
  },
  mounted() {
    this.getUserList();
  },
  methods: {
    getUserList() {
      axios
          .get('http://localhost:8081/Qsi3')
          .then(response => {
            const responseData = response.data;
            const userList = [];
            // Convert Map<String, User> to an array of user objects
            for (const [key, value] of Object.entries(responseData)) {
              const newUser = {
                key: key.slice(0, 2),
                ...value
              };

              // Remove quotes from profileImage property
              newUser.profileImage = newUser.profileImage.replace(/^"(.*)"$/, '$1');
              newUser.link = newUser.link.replace(/^"(.*)"$/, '$1');
              userList.push(newUser);
            }

            this.userList = userList;
          })
          .catch(error => {
            console.error(error);
          });
    }
  }
}
</script>

<style scoped>
.card-header {
  display: flex;
  align-items: center;
}

.avatar {
  width: 48px;
  height: 48px;
  border-radius: 50%;
  margin-right: 12px;
}

.header-text {
  font-weight: bold;
  font-size: 18px;
}

.card-content {
  padding: 16px;
}

.user-info {
  margin-bottom: 8px;
}

.info-label {
  font-weight: bold;
}

.details-link {
  display: inline-block;
  margin-top: 12px;
  color: blue;
  text-decoration: underline;
}
</style>
