<template>
  <div class="11" style="padding: 10px">

    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
    </div>

    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <div class="table">
      <el-table
          v-loading="loading"
          :data="tableData"
          border
          stripe
          style="width: 100%">
        <el-table-column
            prop="id"
            label="ID"
            sortable
            width="50px"
        >
        </el-table-column>
        <el-table-column
            label="图片1"
            width="150px">
          <template #default="scope">
            <el-image
                style="width: 70px; height: 70px"
                :src="scope.row.aimg"
                :preview-src-list="[scope.row.aimg]">
            </el-image>
          </template>
        </el-table-column>
        <el-table-column
            prop="aimg"
            label="本地地址"
            :show-overflow-tooltip="true">
        </el-table-column>
        <el-table-column
          label="转换地址">
          <template #default="scope">
            <button @click="getBase64">获取</button>
          </template>
      </el-table-column>
      </el-table>
    </div>
  </div>
</template>


<script>


import request from "@/utils/request";

export default {
  name: 'Webabout',
  components: {

  },
  data() {
    return {
      loading: true,
      form: {},
      dialogVisible: false,
      search: '',
      currentPage: 1,
      pageSize: 10,
      total: 0,
      tableData: [],
    }
  },
  created() {
    this.load()
    this.getBase64()
  },
  methods: {
    ///
    //将本地图片转化为Base64
    getBase64 () { // 转一张图片编码
      var img = 'https://t7.baidu.com/it/u=2621658848,3952322712&fm=193&f=GIF'
      var image = new Image()
      image.crossOrigin = '' // 解决跨域问题
      image.src = img
      image.onload = function () {
        let canvas = document.createElement('canvas')
        canvas.width = image.width
        canvas.height = image.height
        let context = canvas.getContext('2d')
        context.drawImage(image, 0, 0, image.width, image.height)
        let dataURL = canvas.toDataURL() // 得到图片的base64编码数据
        console.log(dataURL)
      }
    },
    ///
    filesUploadSuccess1(res){
      this.form.aimg = res.data;
    },
    filesUploadSuccess2(res){
      this.form.aimg1 = res.data;
    },
    filesUploadSuccess3(res){
      this.form.aimg2 = res.data;
    },
    load() {
      this.loading = true
      request.get("/webabout", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
        console.log('res ==>aboutttttt', res)
        this.loading = false
        this.tableData = res.data.records
        this.total = res.data.total
      })
    },
    add() {
      this.dialogVisible = true
      this.form = {}
      this.$refs['upload1'].clearFiles()  // 清除历史文件列表
      this.$refs['upload2'].clearFiles()  // 清除历史文件列表
    },
    save() {
      if (this.form.id) {  // 更新
        request.put("/webabout", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "更新成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }
          this.load() // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      }  else {  // 新增
        request.post("/webabout", this.form).then(res => {
          console.log(res)
          if (res.code === '0') {
            this.$message({
              type: "success",
              message: "新增成功"
            })
          } else {
            this.$message({
              type: "error",
              message: res.msg
            })
          }

          this.load() // 刷新表格的数据
          this.dialogVisible = false  // 关闭弹窗
        })
      }

    },
    handleEdit(row) {
      this.form = JSON.parse(JSON.stringify(row))
      this.dialogVisible = true
      this.$nextTick(() => {
        this.$refs['upload1'].clearFiles()  // 清除历史文件列表
        this.$refs['upload2'].clearFiles()  // 清除历史文件列表
      })
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/webabout/" + id).then(res => {
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "删除成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
        this.load()  // 删除之后重新加载表格的数据
      })
    },
    handleSizeChange(pageSize) {   // 改变当前每页的个数触发
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum) {  // 改变当前页码触发
      this.currentPage = pageNum
      this.load()
    }
  }
}
</script>


<style>
.table .el-tooltip__popper {
  width: 400px;
}
</style>

