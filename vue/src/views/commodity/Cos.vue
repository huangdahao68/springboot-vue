<template>
  <div style="padding: 10px">

    <!--    功能区域-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="add">新增</el-button>
    </div>

    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable></el-input>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
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
          width="60"
      >
      </el-table-column>
      <el-table-column
          prop="cname"
          label="名字"
          width="80"
          :show-overflow-tooltip="true">
      </el-table-column>
      <el-table-column
          label="Cos1" >
        <template #default="scope">
          <el-image
              style="width: 50px; height: 50px"
              :src="scope.row.cimg1"
              :preview-src-list="[scope.row.cimg1]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column
          label="Cos2" >
        <template #default="scope">
          <el-image
              style="width: 50px; height: 50px"
              :src="scope.row.cimg2"
              :preview-src-list="[scope.row.cimg2]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column
          label="Cos3" >
        <template #default="scope">
          <el-image
              style="width: 50px; height: 50px"
              :src="scope.row.cimg3"
              :preview-src-list="[scope.row.cimg3]">
          </el-image>
        </template>
      </el-table-column>
      <el-table-column
          prop="cprice1"
          width="60"
          label="价格1">
      </el-table-column>
      <el-table-column
          prop="cprice2"
          width="60"
          label="价格2">
      </el-table-column>
      <el-table-column
          prop="cbrand"
          width="60"
          label="品牌">
      </el-table-column>
      <el-table-column
          prop="ccolor"
          width="60"
          label="颜色">
      </el-table-column>
      <el-table-column
          prop="cfabric"
          width="60"
          label="面料">
      </el-table-column>
      <el-table-column
          prop="cage"
          width="60"
          label="年龄">
      </el-table-column>
      <el-table-column
          prop="csize"
          width="60"
          label="size1">
      </el-table-column>
      <el-table-column
          prop="csize1"
          width="60"
          label="size2">
      </el-table-column>
      <el-table-column
          prop="csize2"
          width="60"
          label="size3">
      </el-table-column>
      <el-table-column
          prop="csize3"
          width="60"
          label="size4">
      </el-table-column>
      <el-table-column label="操作">
        <template #default="scope">
          <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
          <el-popconfirm title="确定删除吗？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button size="mini" type="danger">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <div style="margin: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>

      <el-dialog title="提示" v-model="dialogVisible" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="姓名">
            <el-input v-model="form.cname" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="图片1">
            <el-upload ref="upload1" action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess1">
              <el-button type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="图片2">
            <el-upload ref="upload2" action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess2">
              <el-button type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="图片3">
            <el-upload ref="upload3" action="http://localhost:9090/files/upload" :on-success="filesUploadSuccess3">
              <el-button type="primary">点击上传</el-button>
            </el-upload>
          </el-form-item>
          <el-form-item label="价格1">
            <el-input v-model="form.cprice1" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="价格2">
            <el-input v-model="form.cprice2" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="品牌">
            <el-input v-model="form.cbrand" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="颜色">
            <el-input v-model="form.ccolor" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="面料">
            <el-input v-model="form.cfabric" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.cage" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="尺寸1">
            <el-input v-model="form.csize" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="尺寸2">
            <el-input v-model="form.csize1" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="尺寸3">
            <el-input v-model="form.csize2" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="尺寸4">
            <el-input v-model="form.csize3" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
          <span class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="save">确 定</el-button>
          </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>


import request from "@/utils/request";

export default {
  name: 'Cos',
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
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    filesUploadSuccess1(res){
      this.form.cimg1 = res.data;
    },
    filesUploadSuccess2(res){
      this.form.cimg2 = res.data;
    },
    filesUploadSuccess3(res){
      this.form.cimg3 = res.data;
    },
    load() {
      this.loading = true
      request.get("/cos", {
        params: {
          pageNum: this.currentPage,
          pageSize: this.pageSize,
          search: this.search
        }
      }).then(res => {
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
      this.$refs['upload3'].clearFiles()  // 清除历史文件列表
    },
    save() {
      if (this.form.id) {  // 更新
        request.put("/cos", this.form).then(res => {
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
        request.post("/cos", this.form).then(res => {
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
        this.$refs['upload3'].clearFiles()  // 清除历史文件列表
      })
    },
    handleDelete(id) {
      console.log(id)
      request.delete("/cos/" + id).then(res => {
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
