<template>

    <v-card outlined>
        <v-card-title>
            PostCreate
        </v-card-title>

        <v-card-text>
            <Number label="BoardId" v-model="value.boardId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <Number label="AuthorId" v-model="value.authorId" :editMode="editMode"/>
            <String label="Type" v-model="value.type" :editMode="editMode"/>
            <File offline label="Attachments" v-model="value.attachments" :editMode="editMode" @change="change"/>
            <Date label="CreatedAt" v-model="value.createdAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="postCreate"
            >
                PostCreate
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
    name: 'PostCreateCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.boardId = 0;
        this.value.title = '';
        this.value.content = '';
        this.value.authorId = 0;
        this.value.type = '';
        this.value.attachments = {};
        this.value.createdAt = '2025-07-28';
    },
    watch: {
    },
    methods: {
        postCreate() {
            this.$emit('postCreate', this.value);
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

