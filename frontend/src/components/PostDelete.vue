<template>

    <v-card outlined>
        <v-card-title>
            PostDelete
        </v-card-title>

        <v-card-text>
            <Number label="BoardId" v-model="value.boardId" :editMode="editMode"/>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="postDelete"
            >
                PostDelete
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
    name: 'PostDeleteCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.boardId = 0;
        this.value.authorId = 0;
    },
    watch: {
    },
    methods: {
        postDelete() {
            this.$emit('postDelete', this.value);
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

