<template>

  <el-row style="padding: 10px 0px">
    <el-col :offset="11" :span="4"><span style="font-weight: 900;font-size: 18px">客户管理</span></el-col>
  </el-row>

  <div>
    <el-tabs v-model="activeName" @tab-click="handleClick" style="margin-left: 20px">
      <el-tab-pane label="全部客户"  name="全部">
        <el-card>
          <el-row>
            <el-col>
              <i class="el-icon-guide" style="font-size: 20px;"></i>
              <el-select v-model="values" placeholder="请选择" @change="selectClient">
                <el-option label="全部数据" value=""></el-option>
                <el-option-group
                    v-for="group in options"
                    :key="group.label"
                    :label="group.label"
                >
                  <el-option
                      v-for="item in group.options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  >
                  </el-option>
                </el-option-group>
              </el-select>
              <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
              <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
              <el-button style="margin-left: 10px" @click="findClientName(inputs)">搜索</el-button>
            </el-col>
            <el-col>
              <el-button @click="open()" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>添加新客户</el-button>
            </el-col>
          </el-row>

          <el-table
              ref="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"> </el-table-column>

            <el-table-column prop="clientId" label="ID" sortable/>

            <el-table-column prop="clientName" label="名称" sortable/>

<!--            <el-table-column prop="clientSite" label="地址" show-overflow-tooltip sortable/>-->
            <el-table-column prop="clientSource" label="客户来源" sortable/>
            <el-table-column prop="clientRank" label="定级" sortable/>

            <el-table-column width="250" label="操作">
              <template  #default="scope">
                <template v-if="scope.row.clientSeas == 0">
                  <el-button size="mini" @click="updateState(scope.row.clientId)">转公海</el-button>
                </template>
                <template v-if="scope.row.clientSeas == 1">
                  <el-button size="mini" @click="updateState1(scope.row.clientId)">转普通</el-button>
                </template>
                  <el-button size="mini" @click="editTherapy(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!--分页-->
          <div class="fy_div">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
            </el-pagination>
          </div>
        </el-card>

      </el-tab-pane>
      <el-tab-pane label="潜在客户" name="潜在">
        <el-card>
          <el-row>
            <el-col>
              <i class="el-icon-guide" style="font-size: 20px;"></i>
              <el-select v-model="values" placeholder="请选择" @change="selectClient">
                <el-option label="全部数据" value=""></el-option>
                <el-option-group
                    v-for="group in options"
                    :key="group.label"
                    :label="group.label"
                >
                  <el-option
                      v-for="item in group.options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  >
                  </el-option>
                </el-option-group>
              </el-select>
              <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
              <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
              <el-button style="margin-left: 10px" @click="findClientName(inputs)">搜索</el-button>
            </el-col>
            <el-col>
              <el-button @click="open" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>添加新客户</el-button>
            </el-col>
          </el-row>

          <el-table
              ref="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"> </el-table-column>

            <el-table-column prop="clientId" label="ID" sortable/>

            <el-table-column prop="clientName" label="名称" sortable/>

<!--            <el-table-column prop="clientSite" label="地址" show-overflow-tooltip sortable/>-->
            <el-table-column prop="clientSource" label="客户来源" sortable/>
            <el-table-column prop="clientRank" label="定级" sortable/>

            <el-table-column width="250" label="操作">
              <template  #default="scope">
                <template v-if="scope.row.clientSeas == 0">
                  <el-button size="mini" @click="updateState(scope.row.clientId)">转公海</el-button>
                </template>
                <template v-if="scope.row.clientSeas == 1">
                  <el-button size="mini" @click="updateState1(scope.row.clientId)">转普通</el-button>
                </template>
                <el-button size="mini" @click="editTherapy(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!--分页-->
          <div class="fy_div">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
            </el-pagination>
          </div>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="签约客户" name="签约">
        <el-card>
          <el-row>
            <el-col>
              <i class="el-icon-guide" style="font-size: 20px;"></i>
              <el-select v-model="values" placeholder="请选择" @change="selectClient">
                <el-option label="全部数据" value=""></el-option>
                <el-option-group
                    v-for="group in options"
                    :key="group.label"
                    :label="group.label"
                >
                  <el-option
                      v-for="item in group.options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  >
                  </el-option>
                </el-option-group>
              </el-select>
              <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
              <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
              <el-button style="margin-left: 10px" @click="findClientName(inputs)">搜索</el-button>
            </el-col>
            <el-col>
              <el-button @click="open" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>添加新客户</el-button>
            </el-col>
          </el-row>

          <el-table
              ref="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"> </el-table-column>

            <el-table-column prop="clientId" label="ID" sortable/>

            <el-table-column prop="clientName" label="名称" sortable/>

<!--            <el-table-column prop="clientSite" label="地址" show-overflow-tooltip sortable/>-->
            <el-table-column prop="clientSource" label="客户来源" sortable/>
            <el-table-column prop="clientRank" label="定级" sortable/>

            <el-table-column width="250" label="操作">
              <template  #default="scope">
                <template v-if="scope.row.clientSeas == 0">
                  <el-button size="mini" @click="updateState(scope.row.clientId)">转公海</el-button>
                </template>
                <template v-if="scope.row.clientSeas == 1">
                  <el-button size="mini" @click="updateState1(scope.row.clientId)">转普通</el-button>
                </template>
                <el-button size="mini" @click="editTherapy(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!--分页-->
          <div class="fy_div">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
            </el-pagination>
          </div>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="重复购买" name="重复购买">
        <el-card>
          <el-row>
            <el-col>
              <i class="el-icon-guide" style="font-size: 20px;"></i>
              <el-select v-model="values" placeholder="请选择" @change="selectClient">
                <el-option label="全部数据" value=""></el-option>
                <el-option-group
                    v-for="group in options"
                    :key="group.label"
                    :label="group.label"
                >
                  <el-option
                      v-for="item in group.options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  >
                  </el-option>
                </el-option-group>
              </el-select>
              <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
              <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
              <el-button style="margin-left: 10px" @click="findClientName(inputs)">搜索</el-button>
            </el-col>
            <el-col>
              <el-button @click="open" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>添加新客户</el-button>
            </el-col>
          </el-row>

          <el-table
              ref="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"> </el-table-column>

            <el-table-column prop="clientId" label="ID" sortable/>

            <el-table-column prop="clientName" label="名称" sortable/>

<!--            <el-table-column prop="clientSite" label="地址" show-overflow-tooltip sortable/>-->
            <el-table-column prop="clientSource" label="客户来源" sortable/>
            <el-table-column prop="clientRank" label="定级" sortable/>

            <el-table-column width="250" label="操作">
              <template  #default="scope">
                <template v-if="scope.row.clientSeas == 0">
                  <el-button size="mini" @click="updateState(scope.row.clientId)">转公海</el-button>
                </template>
                <template v-if="scope.row.clientSeas == 1">
                  <el-button size="mini" @click="updateState1(scope.row.clientId)">转普通</el-button>
                </template>
                <el-button size="mini" @click="editTherapy(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!--分页-->
          <div class="fy_div">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
            </el-pagination>
          </div>
        </el-card>
      </el-tab-pane>
      <el-tab-pane label="失效客户" name="失效">
        <el-card>
          <el-row>
            <el-col>
              <i class="el-icon-guide" style="font-size: 20px;"></i>
              <el-select v-model="values" placeholder="请选择" @change="selectClient">
                <el-option label="全部数据" value=""></el-option>
                <el-option-group
                    v-for="group in options"
                    :key="group.label"
                    :label="group.label"
                >
                  <el-option
                      v-for="item in group.options"
                      :key="item.value"
                      :label="item.label"
                      :value="item.value"
                  >
                  </el-option>
                </el-option-group>
              </el-select>
              <i class="el-icon-search" style="margin-left: 10px;font-size: 20px"></i>
              <el-input v-model="inputs" placeholder="请根据客户名称查询" style="width: 200px"></el-input>
              <el-button style="margin-left: 10px" @click="findClientName(inputs)">搜索</el-button>
            </el-col>
            <el-col>
              <el-button @click="open" type="info" plain style="width:160px;color: #2c3e50;float: right"><i class="el-icon-circle-plus-outline"></i>添加新客户</el-button>
            </el-col>
          </el-row>

          <el-table
              ref="multipleTable"
              :data="tableData"
              tooltip-effect="dark"
              style="width: 100%"
              @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55"> </el-table-column>

            <el-table-column prop="clientId" label="ID" sortable/>

            <el-table-column prop="clientName" label="名称" sortable/>

<!--            <el-table-column prop="clientSite" label="地址" show-overflow-tooltip sortable/>-->
            <el-table-column prop="clientSource" label="客户来源" sortable/>
            <el-table-column prop="clientRank" label="定级" sortable/>

            <el-table-column width="250" label="操作">
              <template  #default="scope">
                <template v-if="scope.row.clientSeas == 0">
                  <el-button size="mini" @click="updateState(scope.row.clientId)">转公海</el-button>
                </template>
                <template v-if="scope.row.clientSeas == 1">
                  <el-button size="mini" @click="updateState1(scope.row.clientId)">转普通</el-button>
                </template>
                <el-button size="mini" @click="editTherapy(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!--分页-->
          <div class="fy_div">
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page="currentPage"
                :page-sizes="[5, 10, 20, 40]"
                :page-size="pagesize"
                layout="total, sizes, prev, pager, next, jumper"
                :total="tableData.length">
            </el-pagination>
          </div>
        </el-card>
      </el-tab-pane>
    </el-tabs>
<!--弹窗-->
    <el-dialog
        title="客户"
        v-model="dialogVisible"
        width="60%">
      <el-form :model="client" status-icon ref="client" :rules="rules" label-width="100px" class="demo-ruleForm">
        <el-row>
          <el-col :span="10">
            <el-form-item label="客户名称" prop="clientName">
              <el-input v-model="client.clientName"></el-input>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="客户手机号" prop="clientPhone">
              <el-input v-model="client.clientPhone"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="客户种类" prop="clientKind">
              <el-select v-model="client.clientKind" placeholder="请选择">
                <el-option
                    v-for="item in kind"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="客户生命周期" prop="clientPeriod">
              <el-select v-model="client.clientPeriod" placeholder="请选择">
                <el-option
                    v-for="item in period"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="客户来源" prop="clientSource">
              <el-select v-model="client.clientSource" placeholder="请选择">
                <el-option
                    v-for="item in source"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>


          <el-col :span="10">
            <el-form-item label="客户定性" prop="clientQualitative">
              <el-select v-model="client.clientQualitative" placeholder="请选择">
                <el-option
                    v-for="item in qualitative"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="客户定级" prop="clientRank">
              <el-select v-model="client.clientRank" placeholder="请选择">
                <el-option
                    v-for="item in rank"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="10">
            <el-form-item label="预计签约时间" prop="clientSigningDate">
              <el-select v-model="client.clientSigningDate" placeholder="请选择">
                <el-option
                    v-for="item in signingDate"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-col>

          <el-col :span="10">
            <el-form-item label="预计签约金额" prop="clientContractAmount">
              <el-input v-model="client.clientContractAmount"></el-input>
            </el-form-item>
          </el-col>

<!--          <el-col :span="10">-->
<!--            <el-form-item label="客户地址" prop="clientSite">-->
<!--              <el-input v-model="client.clientSite"></el-input>-->
<!--            </el-form-item>-->
<!--          </el-col>-->

          <el-col :span="10">
            <el-form-item label="备注" prop="clientRemark">
              <el-input v-model="client.clientRemark"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>

      <template #footer>
    <span class="dialog-footer">
      <el-button @click="ClearFrom">取 消</el-button>
      <el-button @click="saveClient" type="primary">确 定</el-button>
    </span>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import locale from "element-plus/lib/locale/lang/zh-cn";

export default {
  name: 'CrManagement',
  data() {
    return {
      dialogVisible: false,
      currentPage:1, //初始页
      pagesize:10,    //    每页的数据
      activeName:"全部",
      tableData: [],
      multipleSelection: [],
      options: [
        {
          label: '客户种类',
          options: [
            {
              value: '客户',
              label: '客户'
            },
            {
              value: '供应商',
              label: '供应商'
            },
            {
              value: '合作伙伴',
              label: '合作伙伴'
            },
            {
              value: '媒体',
              label: '媒体'
            }
          ]
        },
        {
          label: '生命周期',
          options: [
            {
              value: '潜在',
              label: '潜在'
            },
            {
              value: '签约',
              label: '签约'
            },
            {
              value: '重复购买',
              label: '重复购买'
            },
            {
              value: '失效',
              label: '失效'
            }
          ]
        },
        {
          label: '定性',
          options: [
            {
              value: '有价值',
              label: '有价值'
            },
            {
              value: '没价值',
              label: '没价值'
            },
            {
              value: '不确定',
              label: '不确定'
            }
          ]
        },
        {
          label: '定级',
          options: [
            {
              value: '大单',
              label: '大单'
            },
            {
              value: '小单',
              label: '小单'
            },
            {
              value: '正常',
              label: '正常'
            }
          ]
        },
        {
          label: '预定签约时间',
          options: [
            {
              value: '本周',
              label: '本周'
            },
            {
              value: '下周',
              label: '下周'
            },
            {
              value: '本月',
              label: '本月'
            },
            {
              value: '下月',
              label: '下月'
            }
          ]
        },
        {
          label: '客户来源',
          options: [
            {
              value: '客户介绍',
              label: '客户介绍'
            },
            {
              value: '电话来访',
              label: '电话来访'
            },
            {
              value: '媒体宣传',
              label: '媒体宣传'
            },
            {
              value: '网站填表',
              label: '网站填表'
            },
            {
              value: '其他',
              label: '其他'
            }
          ]
        },
      ],
      //客户种类
      kind:[
        {
          value: '客户',
          label: '客户'
        },
        {
          value: '供应商',
          label: '供应商'
        },
        {
          value: '合作伙伴',
          label: '合作伙伴'
        },
        {
          value: '媒体',
          label: '媒体'
        }
      ],
      //客户生命周期
      period:[
        {
          value: '潜在',
          label: '潜在'
        },
        {
          value: '签约',
          label: '签约'
        },
        {
          value: '重复购买',
          label: '重复购买'
        },
        {
          value: '失效',
          label: '失效'
        }
      ],
      //客户来源
      source: [
        {
          value: '客户介绍',
          label: '客户介绍'
        },
        {
          value: '电话来访',
          label: '电话来访'
        },
        {
          value: '媒体宣传',
          label: '媒体宣传'
        },
        {
          value: '网站填表',
          label: '网站填表'
        },
        {
          value: '其他',
          label: '其他'
        }
      ],
      //预定签约时间
      signingDate:[
        {
          value: '本周',
          label: '本周'
        },
        {
          value: '下周',
          label: '下周'
        },
        {
          value: '本月',
          label: '本月'
        },
        {
          value: '下月',
          label: '下月'
        }
      ],
      //定级
      rank:[
          {
        value: '大单',
        label: '大单'
      },
        {
          value: '小单',
          label: '小单'
        },
        {
          value: '正常',
          label: '正常'
        }],
      //定性
      qualitative:[
        {
          value: '有价值',
          label: '有价值'
        },
        {
          value: '没价值',
          label: '没价值'
        },
        {
          value: '不确定',
          label: '不确定'
        }
      ],
      value: '',
      values: '',
      client:{
        clientId:'',
        clientName:'',
        clientPhone:'',
        clientTime:'',
        clientSeas:'',
        clientKind:'',
        clientPeriod:'',
        clientQualitative:'',
        clientRank:'',
        clientSigningDate:'',
        clientContractAmount:'',
        clientSource:'',
        clientSite:'',
        clientRemark:'',
        clientTransfer:'',
        linkmen:[],
        emp:{}
      },
      inputs:'',
    //  表单校验
      rules: {
        clientName: [
          { required: true, message: '请输入客户名称', trigger: 'blur' },
        ],
        clientPhone: [
          { required: true, message: '请输入电话号码', trigger: 'blur' },
        ],
        clientKind: [
          { required: true, message: '请选择种类', trigger: 'change' },
        ],
        clientPeriod: [
          { required: true, message: '请选择生命周期', trigger: 'change' },
        ],
        clientSource: [
          { required: true, message: '请选择客户来源', trigger: 'change' },
        ],
        clientQualitative: [
          { required: true, message: '请选择客户定性', trigger: 'change' },
        ],
        clientRank: [
          { required: true, message: '请选择客户定级', trigger: 'change' },
        ],
        clientSigningDate: [
          { required: true, message: '请选择预定时间', trigger: 'change' },
        ],
        date1: [
          {
            type: 'date',
            required: true,
            message: '请选择日期',
            trigger: 'change',
          },
        ],
        type: [
          {
            type: 'array',
            required: true,
            message: '请至少选择一个活动性质',
            trigger: 'change',
          },
        ],
        resource: [
          { required: true, message: '请选择活动资源', trigger: 'change' },
        ],
        desc: [
          { required: true, message: '请填写活动形式', trigger: 'blur' },
        ],
      },
    }
  },
  methods: {
    initData(){
      this.axios.get("client/find_client").then((v)=>{
        this.tableData=v.data
      })
    },
    findClientName(inputs){
      this.axios.get("client/find_client_name",{params:{clientName:inputs}})
          .then((v)=>{
            this.tableData = v.data
          })
    },
    //转公海
    updateState(row){
      this.axios.get("client/update_client_seas",{params:{clientId:row}})
          .then((v)=>{
            this.initData()
            this.$message("客户转为公海客户")
          })
    },
    //转普通
    updateState1(row){
      this.axios.get("client/update_client_seas1",{params:{clientId:row}})
          .then((v)=>{
            this.initData()
            this.$message("公海客户转为普通客户")
          })
    },
    open(){
      this.token = JSON.parse(localStorage.getItem("loginuser"))
      this.client.clientTransfer = this.token.empName
      this.dialogVisible = true
    },
    //回显弹出框
    editTherapy(row){
      this.client = Object.assign({}, row)
      this.dialogVisible=true;
    },
    //清空弹框
    ClearFrom(){
      this.$refs['client'].resetFields()
      this.client = this.$options.data().client
      this.dialogVisible=false;
    },
    saveClient(){
      this.$refs["client"].validate((v)=>{
        if(v){
          this.axios.post("client/save_client",this.client)
              .then((v)=>{
                this.dialogVisible = false
                this.$message("操作成功")
                this.initData()
              })
        }else{
          return false
        }
      })
    },
    handleClick() {
      console.log(this.activeName)
      this.axios.get("client/find_client_period",{params:{clientPeriod:this.activeName}})
          .then((v)=>{
            this.tableData = v.data
          })
    },
    selectClient(values){
      if (values == null){
        this.axios.get("client/find_client").then((v)=>{
          this.tableData=v.data
        })
      }else{
        this.axios.get("client/select_client",{params:{client:values}})
            .then((v)=>{
              this.tableData = v.data
            })
      }
    },



    formatter(row, column) {
      return row.address
    },
    // 初始页currentPage、初始每页数据数pagesize和数据data
    handleSizeChange: function (size) {
      this.pagesize = size;
      console.log(this.pagesize)  //每页下拉显示数据
    },
    handleCurrentChange: function(currentPage){
      this.currentPage = currentPage;
      console.log(this.currentPage)  //点击第几页
    },

    pageChange(p) {
      this.initData(p, this.pageSize)
    }
  },
  created() {
    this.initData();
    // user:JSON.parse(localStorage.setItem("loginuser"))
  },
}
</script>

<style scoped>
.fy_div{
  margin-top:20px;
  margin-left: 450px;
}
</style>
