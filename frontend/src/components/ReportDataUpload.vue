<template>

    <v-card outlined>
        <v-card-title>
            ReportDataUpload
        </v-card-title>

        <v-card-text>
            <Number label="ReportId" v-model="value.reportId" :editMode="editMode"/>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
            <File offline label="UploadedData" v-model="value.uploadedData" :editMode="editMode" @change="change"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="reportDataUpload"
            >
                ReportDataUpload
            </v-btn>
            
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="close"
            >
                Close
            </v-btn>
        </v-card-actions>
    </v-card>

</template>

<script>

export default {
    name: 'ReportDataUploadCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.reportId = 0;
        this.value.authorId = 0;
        this.value.uploadedData = {};
    },
    watch: {
    },
    methods: {
        reportDataUpload() {
            this.$emit('reportDataUpload', this.value);
        },
        close() {
            this.$emit('closeDialog');
        },
        change() {
            this.$emit("update:modelValue", this.value);
        },
    }
}
</script>

