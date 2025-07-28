<template>
    <div>
        <Number
            label="ReportId"
            v-model="value.reportId"
            :editMode="editMode"
        />
        <String
            label="Title"
            v-model="value.title"
            :editMode="editMode"
        />
        <String
            label="Content"
            v-model="value.content"
            :editMode="editMode"
        />
        <Number
            label="AuthorId"
            v-model="value.authorId"
            :editMode="editMode"
        />
        <Date
            label="CreatedAt"
            v-model="value.createdAt"
            :editMode="editMode"
        />
        <Date
            label="SavedAt"
            v-model="value.savedAt"
            :editMode="editMode"
        />
        <File
            offline
            label="UploadedData"
            v-model="value.uploadedData"
            :editMode="editMode"
            @change="change"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Report',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "reports",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
