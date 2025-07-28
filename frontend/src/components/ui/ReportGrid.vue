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
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="reportDataUploadDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>보고서 데이터 업로드
                </v-btn>
                <v-dialog v-model="reportDataUploadDialog" width="500">
                    <ReportDataUpload
                        @closeDialog="reportDataUploadDialog = false"
                        @reportDataUpload="reportDataUpload"
                    ></ReportDataUpload>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="reportGenerateDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>보고서 생성
                </v-btn>
                <v-dialog v-model="reportGenerateDialog" width="500">
                    <ReportGenerate
                        @closeDialog="reportGenerateDialog = false"
                        @reportGenerate="reportGenerate"
                    ></ReportGenerate>
                </v-dialog>
                <v-btn :disabled="!selectedRow" style="margin-left: 5px;" @click="reportSaveDialog = true" class="contrast-primary-text" small color="primary" :disabled="!hasRole('user')">
                    <v-icon small>mdi-minus-circle-outline</v-icon>보고서 저장
                </v-btn>
                <v-dialog v-model="reportSaveDialog" width="500">
                    <ReportSave
                        @closeDialog="reportSaveDialog = false"
                        @reportSave="reportSave"
                    ></ReportSave>
                </v-dialog>
            </div>
            <ReportReadModel @search="search" style="margin-bottom: 10px; background-color: #ffffff;"></ReportReadModel>
            <div class="mb-5 text-lg font-bold"></div>
            <div class="table-responsive">
                <v-table>
                    <thead>
                        <tr>
                        <th>Id</th>
                        <th>Title</th>
                        <th>Content</th>
                        <th>AuthorId</th>
                        <th>UploadedData</th>
                        <th>CreatedAt</th>
                        <th>SavedAt</th>
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
                            <td class="whitespace-nowrap" label="CreatedAt">{{ val.createdAt }}</td>
                            <td class="whitespace-nowrap" label="SavedAt">{{ val.savedAt }}</td>
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
                        <div style="color:white; font-size:17px; font-weight:700;">Report 등록</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <Report :offline="offline"
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
                        <div style="color:white; font-size:17px; font-weight:700;">Report 수정</div>
                        <v-spacer></v-spacer>
                        <v-icon
                            color="white"
                            small
                            @click="closeDialog()"
                        >mdi-close</v-icon>
                    </v-toolbar>
                    <v-card-text>
                        <div>
                            <Number label="ReportId" v-model="selectedRow.reportId" :editMode="true"/>
                            <String label="Title" v-model="selectedRow.title" :editMode="true"/>
                            <String label="Content" v-model="selectedRow.content" :editMode="true"/>
                            <Number label="AuthorId" v-model="selectedRow.authorId" :editMode="true"/>
                            <Date label="CreatedAt" v-model="selectedRow.createdAt" :editMode="true"/>
                            <Date label="SavedAt" v-model="selectedRow.savedAt" :editMode="true"/>
                            <File offline label="UploadedData" v-model="selectedRow.uploadedData" :editMode="true"/>
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
    name: 'reportGrid',
    mixins:[BaseGrid],
    components:{
    },
    data: () => ({
        path: 'reports',
        reportDataUploadDialog: false,
        reportGenerateDialog: false,
        reportSaveDialog: false,
    }),
    watch: {
    },
    methods:{
        async reportDataUpload(params){
            try{
                var path = "reportDataUpload".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ReportDataUpload 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.reportDataUploadDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async reportGenerate(params){
            try{
                var path = "reportGenerate".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ReportGenerate 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.reportGenerateDialog = false
            }catch(e){
                console.log(e)
            }
        },
        async reportSave(params){
            try{
                var path = "reportSave".toLowerCase();
                var temp = await this.repository.invoke(this.selectedRow, path, params)
                // 스넥바 관련 수정 필요
                // this.$EventBus.$emit('show-success','ReportSave 성공적으로 처리되었습니다.')
                for(var i = 0; i< this.value.length; i++){
                    if(this.value[i] == this.selectedRow){
                        this.value[i] = temp.data
                    }
                }
                this.reportSaveDialog = false
            }catch(e){
                console.log(e)
            }
        },
    }
}

</script>