<template>
  <div>
    <el-menu
        style="width: 200px; min-height: calc(100vh - 65px)"
        :default-active="path"
        router
        class="el-menu-vertical-demo">
      <el-menu-item index="/home">
        <i class="el-icon-s-home"></i>
        <template #title>首页</template>
      </el-menu-item>
      <el-menu-item index="/webuser">
        <i class="el-icon-user-solid"></i>
        <template #title>网站用户管理</template>
      </el-menu-item>
      <!--  主页  ?-->
      <el-submenu index="3">
        <template #title>
          <i class="el-icon-s-unfold"></i>
          <span>主页图片管理</span>
        </template>
        <el-menu-item index="/bigphoto" style="font-size: small">
          <template #title>轮播图管理</template>
        </el-menu-item>
        <el-menu-item index="/smallphoto" style="font-size: small">
          <template #title>其他图片管理</template>
        </el-menu-item>
      </el-submenu>
      <!--      ?-->
      <!--  商品页  ?-->
      <el-submenu index="2">
        <template #title>
          <i class="el-icon-picture"></i>
          <span>商品图片管理</span>
        </template>
        <el-menu-item index="/lifu" style="font-size: small">
          <template #title>礼服管理</template>
        </el-menu-item>
        <el-menu-item index="/marry" style="font-size: small">
          <template #title>中式婚纱管理</template>
        </el-menu-item>
        <el-menu-item index="/xiezhen" style="font-size: small">
          <template #title>西式婚纱管理</template>
        </el-menu-item>
        <el-menu-item index="/cos" style="font-size: small">
          <template #title>cos管理</template>
        </el-menu-item>
        <el-menu-item index="/doll" style="font-size: small">
          <template #title>手办管理</template>
        </el-menu-item>
      </el-submenu>
      <!--      ?-->
      <!--      关于页管理-->
      <el-menu-item index="/webabout">
        <i class="el-icon-s-promotion"></i>
        <template #title>关于页管理</template>
      </el-menu-item>
      <!--      -->
      <el-menu-item index="/translate">
        <i class="el-icon-s-promotion"></i>
        <template #title>转换base64</template>
      </el-menu-item>
      <!--      -->
      <el-menu-item index="/news">
        <i class="el-icon-notebook-2"></i>
        <template #title>新闻管理</template>
      </el-menu-item>
      <el-submenu index="1" v-if="user.role === 1">
        <template #title>
          <i class="el-icon-s-platform"></i>
          <span>管理员</span>
        </template>
        <el-menu-item index="/user">用户信息</el-menu-item>
      </el-submenu>
<!--      -->
    </el-menu>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "Aside",
  data() {
    return {
      user: {},
      path: this.$route.path   // 设置默认高亮的菜单
    }
  },
  created() {
    let userStr = sessionStorage.getItem("user") || "{}"
    this.user = JSON.parse(userStr)

    // 请求服务端，确认当前登录用户的 合法信息
    request.get("/user/" + this.user.id).then(res => {
      if (res.code === '0') {
        this.user = res.data
      }
    })
  }
}
</script>

<style scoped>

</style>
