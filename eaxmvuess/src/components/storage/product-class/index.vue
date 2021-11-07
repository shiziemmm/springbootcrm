<template>
    <div>
        <el-tree :data="classData" :props="{label:'pcName'}" node-key="pcId" default-expand-all @node-click="handleNodeClick"></el-tree>                  
        <el-dialog title="分类设置" width="600px" v-model="dialogFormVisible">
          <el-form ref="form" :model="form1" label-width="130px">
            <el-form-item label="添加下一级分类:">
            <el-input v-model="form1.pcName" style="width:300px"></el-input>
            <el-button @click="handAdd()">确定</el-button>
            </el-form-item>
            <el-form-item label="添加同级分类:">
            <el-input v-model="form2.pcName" style="width:300px"></el-input>
            <el-button @click="handAddx()">确定</el-button>
            </el-form-item>
            <el-form-item label="修改分类名称:">
            <el-input v-model="form3.pcName" style="width:300px"></el-input>
            <el-button @click="handupdate()">确定</el-button>
            </el-form-item>
            <el-form-item label="删除分类:">
            <el-button @click="handDelete()">删除</el-button>
            </el-form-item>
          </el-form>  
       </el-dialog>
    </div>
</template>

<script>
export default {
    data(){
        return{
            classData:[],
            dialogFormVisible:false,
            form1:{},
            form2:{},
            form3:{},
            id:'',
        }
    },
    methods:{
        init(){
            this.axios.get('http://localhost:8188/product-class/findAll').then(res=>{
                this.classData=this.transformTozTreeFormat(res.data.data)
            })
        },
        transformTozTreeFormat(sNodes) {
            var i, l;
            var r = [];
            var tmpMap = {};
            for (i = 0, l = sNodes.length; i < l; i++) {
            tmpMap[sNodes[i].pcId] = sNodes[i];
            }
            for (i = 0, l = sNodes.length; i < l; i++) {
            var p = tmpMap[sNodes[i].pcPid];
            if (p && sNodes[i].pcId != sNodes[i].pcPid) {
                var children = this.nodeChildren(p);
                if (!children) {
                children = this.nodeChildren(p, []);
                }
                children.push(sNodes[i]);
            } else {
                r.push(sNodes[i]);
            }
            }
            return r;
        },
        nodeChildren(node, newChildren) {
            if (typeof newChildren !== 'undefined') {
            node.children = newChildren;
            }
            return node.children;
        },
        handleNodeClick(data){
            this.dialogFormVisible=true;
            this.form1.pcPid = data.pcId;
            this.form2.pcPid = data.pcPid;
            this.form3.pcId = data.pcId;
            this.id = data.pcId;
        },
        handAdd(){
           this.axios.post('http://localhost:8188/product-class/add',this.form1).then(res=>{
              this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.dialogFormVisible=false;
               this.init();
            })
        },
        handAddx(){
            this.axios.post('http://localhost:8188/product-class/add',this.form2).then(res=>{
              this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.dialogFormVisible=false;
               this.init();
            })
        },
        handupdate(){
            this.axios.put('http://localhost:8188/product-class/update/'+this.form3.pcId,this.form3).then(res=>{
              this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.dialogFormVisible=false;
               this.init();
            })
        },
        handDelete(){
            this.axios.delete('http://localhost:8188/product-class/delete/'+this.id).then(res=>{
              this.$message({
                    message: res.data.obj?'成功':'失败',
                    type: res.data.obj?'success':'error'
               });
               this.dialogFormVisible=false;
               this.init();
            })
        }
    },
    created(){
        this.init();
    }    
}
</script>

<style>
</style>


