new Vue({
  el: '#selectFile',
  data() {
    return {
      showDialog: false,
      uploadData: [],
      selectData: [],
      allDataList: []
    }
  },
  methods: {
    openDialog () {
      this.getList()
      this.showDialog = true
    },
    // 点击选择文件弹出框确认按钮
    uploadConfirm () {
      console.log(this.selectData)
      let thiz = this
      // 将选择的数据进行筛选
      let confirmArr = []
      thiz.allDataList.forEach(item => {
        if (thiz.selectData.indexOf(item.fileName) !== -1) {
          confirmArr.push(item)
        }
      })
      
      const loading = this.$loading({
		lock: true,
		text: 'Loading',
		spinner: 'el-icon-loading',
		background: 'rgba(0, 0, 0, 0.7)'
		});
		      
      // 调用确认已选择的列表
      axios.post('/run', 
        
         confirmArr
       
      )
      .then(function (response) {
        // 成功后返回的数据
    	  thiz.selectData = []
    	  loading.close()
    	  thiz.$message.success(response.data)
    	 
        thiz.showDialog = false
      })
      .catch(function (error) {
    	  loading.close()
        thiz.$message.error('确认列表失败')
      });
    },
    // 查看用例
    viewUseCase () {
      window.location.pathname = '/viewUseCases'
    },
    getList () {
      let thiz = this
      // 获取上传后文件的接口，然后进行渲染数据到data中
      axios.get('/myCase', {
        params: {}
      })
      .then(function (response) {
    	  thiz.selectData = []
        console.log(response);
        // 将返回的数据放到data中
        let dataArr = []
        response.data.forEach(res => {
          dataArr.push({
            fileName: res.fileName
          })
        })
        thiz.allDataList = response.data
        thiz.uploadData = dataArr
      })
      .catch(function (error) {
        thiz.uploadData = []
        thiz.$message.error('获取列表失败')
      })
    }
  },
  mounted () {
  }
})
