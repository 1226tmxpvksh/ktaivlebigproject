<template>
    <v-container>
        <v-snackbar
            v-model="snackbar.status"
            :timeout="snackbar.timeout"
            :color="snackbar.color"
        >
            
            <v-btn style="margin-left: 80px;" text @click="snackbar.status = false">
                Close
            </v-btn>
        </v-snackbar>
        <div class="panel">
            <div class="gs-bundle-of-buttons" style="max-height:10vh;">
                <v-btn @click="addNewRow" @class="contrast-primary-text" small color="primary">
                    <v-icon small style="margin-left: -5px;">mdi-plus</v-icon>등록
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="openEditDialog()" class="contrast-primary-text" small color="primary">
                    <v-icon small>mdi-pencil</v-icon>수정
                </v-btn>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="userRegisterDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon> 사용자 등록
                </v-btn>
                <v-dialog v-model="userRegisterDialog" width="500">
                    <UserRegister
                        @closeDialog="userRegisterDialog = false"
                        @userRegister="userRegister"
                    ></UserRegister>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="userDeleteDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 삭제
                </v-btn>
                <v-dialog v-model="userDeleteDialog" width="500">
                    <UserDelete
                        @closeDialog="userDeleteDialog = false"
                        @userDelete="userDelete"
                    ></UserDelete>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="userProfileUpdateDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 프로필 업데이트
                </v-btn>
                <v-dialog v-model="userProfileUpdateDialog" width="500">
                    <UserProfileUpdate
                        @closeDialog="userProfileUpdateDialog = false"
                        @userProfileUpdate="userProfileUpdate"
                    ></UserProfileUpdate>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="getUserInfoDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>사용자 정보 가져오기
                </v-btn>
                <v-dialog v-model="getUserInfoDialog" width="500">
                    <GetUserInfo
                        @closeDialog="getUserInfoDialog = false"
                        @getUserInfo="getUserInfo"
                    ></GetUserInfo>
                </v-dialog>
            </div>
            <UserReadModel @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></UserReadModel>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Email</th>
                        <th>Password</th>
                        <th>Nickname</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="Email">{{ val.email }}</td>
                            <td class="whitespace-nowrap" label="Password">{{ val.password }}</td>
                            <td class="whitespace-nowrap" label="Nickname">{{ val.nickname }}</td>
                            <v-row class="ma-0 pa-4 align-center">
                                <v-spacer></v-spacer>
                                <Icon style="cursor: pointer;" icon="mi:delete" @click="deleteRow(val)" />
                            </v-row>
                        </tr>
                    </tbody>
                </v-table>
            </div>
        </div>
        <v-col>
            <v-dialog
                v-model="openDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">User 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <User :offline="offline"
                            :isNew="!value.idx"
                            :editMode="true"
                            :inList="false"
                            v-model="newValue"
                            @add="append"
                        />
                    </v-card-text>
                </v-card>
            </v-dialog>
            <v-dialog
                v-model="editDialog"
                transition="dialog-bottom-transition"
                width="35%"
            >
                <v-card>
                    <v-toolbar
                        color="primary"
                        class="elevation-0 pa-4"
                        height="50px"
                    >
                        <div style="color:white; font-size:17px; font-weight:700;">User 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <String label="Email" v-model="selectedRow.email" :editMode="true"/>
                            <String label="Password" v-model="selectedRow.password" :editMode="true"/>
                            <String label="Nickname" v-model="selectedRow.nickname" :editMode="true"/>
                            <v-divider class="border-opacity-100 my-divider"></v-divider>
                            <v-layout row justify-end>
                                <v-btn
                                    width="64px"
                                    color="primary"
                                    @click="save"
                                >
                                    수정
                                </v-btn>
                            </v-layout>
                        </div>
                    </v-card-text>
                </v-card>
            </v-dialog>
        </v-col>
    </v-container>
</template>

<script>
import { ref } from 'vue';
import { useTheme } from 'vuetify';
import BaseGrid from '../base-ui/BaseGrid.vue'


export default {
    name: 'userGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'users',
        userRegisterDialog: false,
        userDeleteDialog: false,
        userProfileUpdateDialog: false,
        getUserInfoDialog: false,
    }),
    watch: {
    },
    methods:{
        async userRegister(params){
            try{
                var path = "userRegister".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UserRegister 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.userRegisterDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async userDelete(params){
            try{
                var path = "userDelete".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UserDelete 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.userDeleteDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async userProfileUpdate(params){
            try{
                var path = "userProfileUpdate".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','UserProfileUpdate 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.userProfileUpdateDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async getUserInfo(params){
            try{
                var path = "getUserInfo".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','GetUserInfo 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.getUserInfoDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>