new Vue({
  el: '#upload',
  data() {
    return {
      fileList: [],
      formInline: {
        filename: ''
      }
    }
  },
  methods: {
    // 文件上传提交
    submitUpload (value) {
      let thiz = this
      thiz.$refs.upload.submit();
    },
  }
})