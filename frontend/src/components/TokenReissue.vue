<template>

    <v-card outlined>
        <v-card-title>
            TokenReissue
        </v-card-title>

        <v-card-text>
            <String label="UpdateToken" v-model="value.updateToken" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="tokenReissue"
            >
                TokenReissue
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
    name: 'TokenReissueCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.updateToken = '';
    },
    watch: {
    },
    methods: {
        tokenReissue() {
            this.$emit('tokenReissue', this.value);
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

