<template>

    <v-card outlined>
        <v-card-title>
            PostDetail
        </v-card-title>

        <v-card-text>
            <Number label="BoardId" v-model="value.boardId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="postDetail"
            >
                PostDetail
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
    name: 'PostDetailCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.boardId = 0;
    },
    watch: {
    },
    methods: {
        postDetail() {
            this.$emit('postDetail', this.value);
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

