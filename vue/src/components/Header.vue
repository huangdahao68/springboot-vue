<template>
  <div style="height: 65px; line-height: 50px; border-bottom: 1px solid #ccc; display: flex">
    <div style="width: 200px; font-weight: bold; color: dodgerblue">
      <img src="../assets/logo.png" style="width: 200px;height: 75px"/>
    </div>
    <div style="flex: 1;padding-left: 30px">
      <div style="padding-top: 6px;color: #86879c" >
        <h2>后台管理</h2>
      </div>
    </div>
    <div style="width: 100px;padding-top: 6px">
      <el-dropdown>
        <span class="el-dropdown-link">
          {{ user.nickName }}<i class="el-icon-arrow-down el-icon--right"></i>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="$router.push('/person')">个人信息</el-dropdown-item>
            <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    return {
      user: {}
    }
  },
  created() {
    let str = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(str)
  }
}
</script>

<style scoped>

</style>
