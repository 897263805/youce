new Vue({
  el: '#caseMan',
  data() {
    return {
      tableData: []
    }
  },
  methods: {
    getListData () {
      let that = this
      axios.get('/myCase', {
        params: {}
      })
      .then(function (response) {
        console.log(response);
        // 将返回的数据放到data中
        that.tableData = response.data
      })
      .catch(function (error) {
        // that.tableData = [{}, {}, {}, {}, {}, {}, {}, {}]
        that.$message.error('获取列表失败')
      })
    },
    // 点击删除按钮时
    deleteData (id) {
      let that = this
      this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        that.confirmDelete(id)
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })      
    },
    confirmDelete (id) {
      let that = this
      axios.get('/deleteCase', {
        params: {
          id
        }
      })
      .then(function (response) {
        console.log(response);
        // 将返回的数据放到data中
        if (response.data) {
          // 删除成功
          that.$message.success(response.data)
          // 重新获取列表
          that.getListData()
        } else {
          // 删除失败
          that.$message.error('失败')
        }
      })
      .catch(function (error) {
      
        that.$message.error('删除失败')
      })
    },
    // 点击下载按钮时
    uploadDate (url) {
      console.log(url)
      let a =  document.createElement('a')
      a.href = url
      a.download = ''
      document.body.appendChild(a)
      a.click()
      document.body.removeChild(a)
    },
    goBack () {
      window.location.href = 'https://baidu.com'
    }
  },
  mounted () {
    this.getListData()
  }
})
