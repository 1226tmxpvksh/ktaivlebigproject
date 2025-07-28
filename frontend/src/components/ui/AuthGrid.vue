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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="loginDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>로그인
                </v-btn>
                <v-dialog v-model="loginDialog" width="500">
                    <Login
                        @closeDialog="loginDialog = false"
                        @login="login"
                    ></Login>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="logoutDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>로그아웃
                </v-btn>
                <v-dialog v-model="logoutDialog" width="500">
                    <Logout
                        @closeDialog="logoutDialog = false"
                        @logout="logout"
                    ></Logout>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="tokenReissueDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>토큰 재발급
                </v-btn>
                <v-dialog v-model="tokenReissueDialog" width="500">
                    <TokenReissue
                        @closeDialog="tokenReissueDialog = false"
                        @tokenReissue="tokenReissue"
                    ></TokenReissue>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="tokenVerificationDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>토큰 검증
                </v-btn>
                <v-dialog v-model="tokenVerificationDialog" width="500">
                    <TokenVerification
                        @closeDialog="tokenVerificationDialog = false"
                        @tokenVerification="tokenVerification"
                    ></TokenVerification>
                </v-dialog>
            </div>
            <AuthReadModel @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></AuthReadModel>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>UserId</th>
                        <th>UpdateToken</th>
                        <th>UpdateExpiredAt</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="UserId">{{ val.userId }}</td>
                            <td class="whitespace-nowrap" label="UpdateToken">{{ val.updateToken }}</td>
                            <td class="whitespace-nowrap" label="UpdateExpiredAt">{{ val.updateExpiredAt }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Auth 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Auth :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Auth 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="AuthId" v-model="selectedRow.authId" :editMode="true"/>
                            <Number label="UserId" v-model="selectedRow.userId" :editMode="true"/>
                            <String label="UpdateToken" v-model="selectedRow.updateToken" :editMode="true"/>
                            <String label="UpdateExpiredAt" v-model="selectedRow.updateExpiredAt" :editMode="true"/>
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
    name: 'authGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'auths',
        loginDialog: false,
        logoutDialog: false,
        tokenReissueDialog: false,
        tokenVerificationDialog: false,
    }),
    watch: {
    },
    methods:{
        async login(params){
            try{
                var path = "login".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','Login 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.loginDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async logout(params){
            try{
                var path = "logout".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','Logout 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.logoutDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async tokenReissue(params){
            try{
                var path = "tokenReissue".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success',' TokenReissue 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.tokenReissueDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async tokenVerification(params){
            try{
                var path = "tokenVerification".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','TokenVerification 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.tokenVerificationDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>