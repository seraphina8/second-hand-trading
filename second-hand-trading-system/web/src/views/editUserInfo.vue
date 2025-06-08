<template>
    <el-card style="width: 350px">
        <el-form :model="formData" :rules="rulesList"
                 status-icon
                 ref="formData"
                 label-position="left"
                 label-width="100px">
            <h3 class="title" style="color: #000">用户信息修改</h3>
          <el-form-item label="头像">
            <MyUpLoad type="imageCar" :limit="1" :files="formData.img" @setFiles="formData.img =$event"></MyUpLoad>
          </el-form-item>
            <el-form-item prop="username" label="用户名">
                <el-input type="text"
                          v-model="formData.username"
                          auto-complete="off"
                          placeholder="用户名"
                          :disabled="true"
                ></el-input>
            </el-form-item>

            <el-form-item prop="email" label="邮箱">
                <el-input type="text"
                          v-model="formData.email"
                          auto-complete="off"
                          placeholder="邮箱"
                ></el-input>
            </el-form-item>

            <el-form-item prop="mobile" label="手机号">
                <el-input type="text"
                          v-model="formData.mobile"
                          auto-complete="off"
                          placeholder="手机号"
                ></el-input>
            </el-form-item>
            <el-form-item prop="address" label="地址">
                <el-input type="text"
                          v-model="formData.address"
                          auto-complete="off"
                          placeholder="地址"
                ></el-input>
            </el-form-item>
            <el-form-item label="性别">
                <el-radio-group v-model="formData.gender">
                    <el-radio label="男"></el-radio>
                    <el-radio label="女"></el-radio>
                </el-radio-group>
            </el-form-item>
            <el-form-item prop="content" label="个人简介">
                <el-input
                    type="textarea"
                    :rows="3"
                    v-model="formData.content"
                    auto-complete="off"
                    placeholder="个人简介"
                ></el-input>
            </el-form-item>
            <el-form-item style="width:100%;">
                <el-button type="primary" style="width:100px;" @click="handleSubmit">修改</el-button>
            </el-form-item>
        </el-form>
    </el-card>
</template>

<script>
import utils from "@/utils/utils";
import MyUpLoad from "@/components/MyUpload.vue";

// 定义邮箱验证规则
const validateEmail = (rule, value, callback) => {
  const reg = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
  if (value && !reg.test(value)) {
    callback(new Error('请输入有效的邮箱地址'));
  } else {
    callback();
  }
};

// 定义手机号验证规则
const validateMobile = (rule, value, callback) => {
  const reg = /^1[3-9]\d{9}$/;
  if (value && !reg.test(value)) {
    callback(new Error('请输入有效的手机号'));
  } else {
    callback();
  }
};

export default {
  components: {MyUpLoad},
    data() {
        return {
            formData: {
                id: "",
                username: '',
                email: '',
                mobile: '',
                address: '',
                content: '',
            },
            rulesList: {
                email: [
                  { required: true, message: '请输入邮箱', trigger: 'blur' },
                  { validator: validateEmail, trigger: 'blur' }
                ],
                mobile: [
                  { required: true, message: '请输入手机号', trigger: 'blur' },
                  { validator: validateMobile, trigger: 'blur' }
                ],
                address: [
                  { required: true, message: '请输入地址', trigger: 'blur' }
                ],
                content: [
                  { required: true, message: '请输入个人简介', trigger: 'blur' }
                ]
            },
            headers: {
                "aaa": "12343"
            }
        }
    },
    created() {
        console.log(utils.getUserInfo())
        this.formData = utils.getUserInfo()
    },
    methods: {
        handleSubmit() {
            //编辑
            this.request.put('/sysUser/update', this.formData).then(res => {
                console.log(res)
                localStorage.setItem("server-userInfo", JSON.stringify(this.formData))
                this.$message({
                    message: '修改成功',
                    type: 'success'
                });
            })
        },
        success(res) {
            console.log(res)
            this.formData.img = res.data.url
        }
    }
};
</script>

<style scoped>
.avatar-uploader {
    text-align: center;
    padding-bottom: 10px;
}

.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 150px;
    height: 150px;
    line-height: 150px;
    text-align: center;
}

.avatar {
    width: 150px;
    height: 150px;
    display: block;
}

</style>

