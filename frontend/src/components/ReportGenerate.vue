<template>

    <v-card outlined>
        <v-card-title>
            ReportGenerate
        </v-card-title>

        <v-card-text>
            <Number label="ReportId" v-model="value.reportId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
            <Date label="CreatedAt" v-model="value.createdAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="reportGenerate"
            >
                ReportGenerate
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
    name: 'ReportGenerateCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.reportId = 0;
        this.value.title = '';
        this.value.content = '';
        this.value.authorId = 0;
        this.value.createdAt = '2025-07-28';
    },
    watch: {
    },
    methods: {
        reportGenerate() {
            this.$emit('reportGenerate', this.value);
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

