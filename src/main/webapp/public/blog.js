import {BASE_URL} from "./BASE_URL";

const updateDialog = document.getElementById("submitDialog")
const updateBtn = document.getElementById("subBTN")
updateBtn.addEventListener("click", () => {
    if (typeof updateDialog.showModal === "function") {
        updateDialog.showModal();
    }
});

$.ajax(
    {
        type:"GET",
        url:`${BASE_URL}//blog.do`,
        data:{
            method:"getUsers",
        },
        dataType :"json",
        success:function(data){
            console.log(data)
            // location.reload();
        }
    }
)


document.getElementById("submitForm").addEventListener(
    "submit" ,function (event){
        event.preventDefault();
        var formData = new FormData(this)
        console.log(formData.get("title"))
        $.ajax({
            type: "GET",
            url:`${BASE_URL}/blog.do`,
            data:{
                method: "submitBlog",
                title:formData.get("title"),
                content:formData.get("content")
            },
            dataType: "json",
            success:function(data){
                if(data.result==1){
                    alert("Submit Successful!")
                    updateDialog.close()
                    window.location.reload()

                }else{
                    alert("Submit Error :(")
                }
            }
        })
    }
)


document.getElementById("cancel_submit").addEventListener(
    "click",function (){
        updateDialog.close();
    }
)
const selectUser = document.getElementById("selUser");
const searchBtn = document.getElementById("search");
searchBtn.addEventListener(
    'click',function(){
        const selectedValue = selectUser.value;
        if(selectedValue==0){
            $.ajax(
                {
                    type:"GET",
                    url:`${BASE_URL}//blog.do`,
                    data:{
                        method:"getALLBlog",
                    },
                    dataType :"json",
                    success:function(data){
                        console.log(data)
                        location.reload();
                    }
                }
            )
        }else{
            $.ajax(
                {
                    type:"GET",
                    url:`${BASE_URL}//blog.do`,
                    data:{
                        method:"getUserBlog",
                        userId:selectedValue
                    },
                    dataType :"json",
                    success:function(data){
                        console.log(data)
                        location.reload();
                    }
                }
            )
        }
    }
)