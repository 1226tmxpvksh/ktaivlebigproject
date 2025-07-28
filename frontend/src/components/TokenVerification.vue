<template>

    <v-card outlined>
        <v-card-title>
            TokenVerification
        </v-card-title>

        <v-card-text>
            <String label="UpdateExpiredAt" v-model="value.updateExpiredAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="tokenVerification"
            >
                TokenVerification
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
    name: 'TokenVerificationCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.updateExpiredAt = '';
    },
    watch: {
    },
    methods: {
        tokenVerification() {
            this.$emit('tokenVerification', this.value);
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

