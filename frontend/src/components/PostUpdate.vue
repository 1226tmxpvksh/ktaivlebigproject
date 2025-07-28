<template>

    <v-card outlined>
        <v-card-title>
            PostUpdate
        </v-card-title>

        <v-card-text>
            <Number label="BoardId" v-model="value.boardId" :editMode="editMode"/>
            <String label="Title" v-model="value.title" :editMode="editMode"/>
            <String label="Content" v-model="value.content" :editMode="editMode"/>
            <String label="Type" v-model="value.type" :editMode="editMode"/>
            <File offline label="Attachments" v-model="value.attachments" :editMode="editMode" @change="change"/>
            <Date label="UpdatedAt" v-model="value.updatedAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="postUpdate"
            >
                PostUpdate
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
    name: 'PostUpdateCommand',
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
        this.value.type = '';
        this.value.attachments = {};
        this.value.updatedAt = '2025-07-28';
    },
    watch: {
    },
    methods: {
        postUpdate() {
            this.$emit('postUpdate', this.value);
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

