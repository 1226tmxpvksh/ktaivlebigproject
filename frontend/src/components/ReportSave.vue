<template>

    <v-card outlined>
        <v-card-title>
            ReportSave
        </v-card-title>

        <v-card-text>
            <String label="ReportId" v-model="value.reportId" :editMode="editMode"/>
            <Date label="SavedAt" v-model="value.savedAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="reportSave"
            >
                ReportSave
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
    name: 'ReportSaveCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.reportId = '';
        this.value.savedAt = '2025-07-28';
    },
    watch: {
    },
    methods: {
        reportSave() {
            this.$emit('reportSave', this.value);
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

