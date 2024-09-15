import {BASE_URL} from "./BASE_URL";

const to_reg = document.getElementsByClassName("to_register")[0]
console.log(to_reg)

to_reg.addEventListener('click',function (){
    console.log("hello!");
    const right_login = document.getElementsByClassName("right_login")[0]
    right_login.style.display = "None"
    const right_register = document.getElementsByClassName("right_register")[0]
    right_register.style.display = "block"
})


const to_login = document.getElementsByClassName("to_login")[0]
 to_login.addEventListener('click',function  (){
     console.log("hello!");
     const right_login = document.getElementsByClassName("right_login")[0]
     right_login.style.display = "block"
     const right_register = document.getElementsByClassName("right_register")[0]
     right_register.style.display = "None"
 })

// 获取相关 HTML 元素
const username = document.querySelector("#regUsername")
const passwordInput = document.querySelector('#regFirstPwd');
const repeatPasswordInput = document.querySelector('#regSecondPwd');
const errorDiv = document.querySelector('.reg_error');
const registerButton = document.getElementById('register_button');

// 注册按钮的点击事件处理函数
registerButton.addEventListener('click', function() {
    // 获取输入的密码值
    const password = passwordInput.value;
    console.log(password)
    const repeatPassword = repeatPasswordInput.value;
    console.log(repeatPassword)
    // 检查密码是否一致
    if (password !== repeatPassword) {
        // 密码不一致，显示错误信息
        errorDiv.textContent = 'Passwords do not match';
        errorDiv.style.color = 'red';
    } else {
        // 密码一致，清空错误信息
        errorDiv.textContent = '';
        console.log("can post the request!")
        $.ajax(
            {
                type:"GET",
                url:`${BASE_URL}/login.do`,
                data:{
                    method:"register",
                    username:username.value,
                    password:password
                },
                dataType :"json",
                success:function(data){
                    if(data.regResult===1){
                        alert("Success!")
                        const right_login = document.getElementsByClassName("right_login")[0]
                        right_login.style.display = "block"
                        const right_register = document.getElementsByClassName("right_register")[0]
                        right_register.style.display = "None"
                    }else{
                        alert("error!")
                    }
                }
            }
        )
    }
});
