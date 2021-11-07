<template >
  <div  class="div-login">
    <div  class="div-w">
      <el-form  ref="form" :model="form" :rules="rules" class="login-box">
        <h3 class="login-title">柿子医疗后台登录</h3>
        <el-form-item prop="uName" >
          <el-input type="text" style="margin-top: 20px;" placeholder="请输入账号" v-model="form.uName"/>
        </el-form-item>
        <el-form-item  prop="uPswd" :error="errors.user">
          <el-input type="password" placeholder="请输入密码" style="width: 100%;" v-model="form.uPswd"/>
        </el-form-item>
        <el-form-item>
          <el-row>
            <el-col style="margin-top: 15px"  :span="24">
              <el-button  type="primary"   @click="onSubmit('form')">登录</el-button>
            </el-col>
          </el-row>
        </el-form-item>
      </el-form>

<!--      					<el-dialog-->
<!--      					  title="温馨提示"-->
<!--      					 v-model="dialogVisible"-->
<!--                  modelVa="dialogVisible"-->
<!--      					  width="30%"  :before-close="handleClose">-->
<!--      					  <span>请输入账号和密码</span>-->
<!--      					  <span slot="footer" class="dialog-footer">-->
<!--      					    <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
<!--      					  </span>-->
<!--      			</el-dialog>-->
    </div>
  </div>
</template>

<script>
import qs from 'qs'
export default{
  data(){
    return{
      user:{
        userName:'',
        userPassword:''
      },
      form: {
        uName:'',
        uPswd:'',
      },
      errors:{
        users:'',
      },
      // 表单验证，需要在 el-form-item 元素中增加 prop 属性
      rules: {
        uName: [
          {required: true, message: '账号不可为空', trigger: 'blur'}
        ],
        uPswd: [
          {required: true, message: '密码不可为空', trigger: 'blur'}
        ]
      },
      // 对话框显示和隐藏
      dialogVisible: false
    }
  },
  methods: {
    onSubmit(formName){
      this.$refs[formName].validate(valid =>{
        if(valid){
          this.axios.post("/user/login",this.form).then((v)=>{
            console.log(v)
            if(v.data.code==200){
              this.$store.state.token =v.data.obj[0];
              this.$store.commit("login",v.data.obj[0])
              sessionStorage.setItem("token",JSON.stringify(v.data.obj[0]))
              console.log(this.$store.state.token)
              this.$router.push('/home')
            }else{
              this.form={
                uName:'',
                uPswd:''
              }
              alert("请重新登录")
            }
          }).catch()
        }
      })
    }

  },
  created() {
    //清空sessionStorage
    this.$store.state.token = ''
    sessionStorage.removeItem("token")
    this.axios.interceptors.request.use((config)=>{
      if(this.$store.state.token.empId===null||this.$store.state.token.empId===undefined){
        this.$router.push('/')
      }else{
        this.$router.push('/home')
      }
      return config;
    });
  }
}
</script>

<style scoped>
.div-login{
  width: 100%;
  height: 100%;
  overflow: hidden;
  position: absolute;
  background-color: #2d3a4b;
}
.div-w{
  padding: 25px 30px 20px 30px;
  background-color: #2d3a4b;
  transform: translate(-50%,-50%);
  position: absolute;
  width: 300px;
  background: rgba(163,186,208,0.3);
  left: 50%;
  top: 50%;
  text-align: center;
  z-index: 100;
}
.el-button{
  width: 100%;
}
.el-input{
  text-align: center;
}
</style>
