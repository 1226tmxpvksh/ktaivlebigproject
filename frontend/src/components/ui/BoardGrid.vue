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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="postCreateDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시물 생성
                </v-btn>
                <v-dialog v-model="postCreateDialog" width="500">
                    <PostCreate
                        @closeDialog="postCreateDialog = false"
                        @postCreate="postCreate"
                    ></PostCreate>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="postDeleteDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시물 삭제
                </v-btn>
                <v-dialog v-model="postDeleteDialog" width="500">
                    <PostDelete
                        @closeDialog="postDeleteDialog = false"
                        @postDelete="postDelete"
                    ></PostDelete>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="downloadFileDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>파일 다운로드
                </v-btn>
                <v-dialog v-model="downloadFileDialog" width="500">
                    <DownloadFile
                        @closeDialog="downloadFileDialog = false"
                        @downloadFile="downloadFile"
                    ></DownloadFile>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="postUpdateDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시물 수정
                </v-btn>
                <v-dialog v-model="postUpdateDialog" width="500">
                    <PostUpdate
                        @closeDialog="postUpdateDialog = false"
                        @postUpdate="postUpdate"
                    ></PostUpdate>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="postDetailDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>게시글 상세 조회
                </v-btn>
                <v-dialog v-model="postDetailDialog" width="500">
                    <PostDetail
                        @closeDialog="postDetailDialog = false"
                        @postDetail="postDetail"
                    ></PostDetail>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="viewCountIncreaseDialog = true" class="contrast-primary-text" small color="primary" >
                    <v-icon small>mdi-minus-circle-outline</v-icon>조회수 증가
                </v-btn>
                <v-dialog v-model="viewCountIncreaseDialog" width="500">
                    <ViewCountIncrease
                        @closeDialog="viewCountIncreaseDialog = false"
                        @viewCountIncrease="viewCountIncrease"
                    ></ViewCountIncrease>
                </v-dialog>
            </div>
            <BoardReadModel @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></BoardReadModel>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>AuthorId</th>
                        <th>ViewCount</th>
                        <th>Type</th>
                        <th>Attachments</th>
                        <th>CreatedAt</th>
                        <th>UpdatedAt</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(val, idx) in value" 
                            @click="changeSelectedRow(val)"
                            :key="val"  
                            :style="val === selectedRow ? 'background-color: rgb(var(--v-theme-primary), 0.2) !important;':''"
                        >
                            <td class="font-semibold">{{ idx + 1 }}</td>
                            <td class="whitespace-nowrap" label="Title">{{ val.title }}</td>
                            <td class="whitespace-nowrap" label="Content">{{ val.content }}</td>
                            <td class="whitespace-nowrap" label="AuthorId">{{ val.authorId }}</td>
                            <td class="whitespace-nowrap" label="ViewCount">{{ val.viewCount }}</td>
                            <td class="whitespace-nowrap" label="Type">{{ val.type }}</td>
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="UpdatedAt">{{ val.updatedAt }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Board 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Board :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Board 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="BoardId" v-model="selectedRow.boardId" :editMode="true"/>
                            <String label="Title" v-model="selectedRow.title" :editMode="true"/>
                            <String label="Content" v-model="selectedRow.content" :editMode="true"/>
                            <Number label="AuthorId" v-model="selectedRow.authorId" :editMode="true"/>
                            <Number label="ViewCount" v-model="selectedRow.viewCount" :editMode="true"/>
                            <String label="Type" v-model="selectedRow.type" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="UpdatedAt" v-model="selectedRow.updatedAt" :editMode="true"/>
                            <File offline label="Attachments" v-model="selectedRow.attachments" :editMode="true"/>
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
    name: 'boardGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'boards',
        postCreateDialog: false,
        postDeleteDialog: false,
        downloadFileDialog: false,
        postUpdateDialog: false,
        postDetailDialog: false,
        viewCountIncreaseDialog: false,
    }),
    watch: {
    },
    methods:{
        async postCreate(params){
            try{
                var path = "postCreate".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PostCreate 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.postCreateDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async postDelete(params){
            try{
                var path = "postDelete".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PostDelete 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.postDeleteDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async downloadFile(params){
            try{
                var path = "downloadFile".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','DownloadFile 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.downloadFileDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async postUpdate(params){
            try{
                var path = "postUpdate".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PostUpdate 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.postUpdateDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async postDetail(params){
            try{
                var path = "postDetail".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','PostDetail 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.postDetailDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async viewCountIncrease(params){
            try{
                var path = "viewCountIncrease".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ViewCountIncrease 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.viewCountIncreaseDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>