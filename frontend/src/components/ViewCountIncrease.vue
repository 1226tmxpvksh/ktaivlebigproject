<template>

    <v-card outlined>
        <v-card-title>
            ViewCountIncrease
        </v-card-title>

        <v-card-text>
            <Number label="BoardId" v-model="value.boardId" :editMode="editMode"/>
            <Date label="UpdatedAt" v-model="value.updatedAt" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="viewCountIncrease"
            >
                ViewCountIncrease
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
    name: 'ViewCountIncreaseCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.boardId = 0;
        this.value.updatedAt = '2025-07-28';
    },
    watch: {
    },
    methods: {
        viewCountIncrease() {
            this.$emit('viewCountIncrease', this.value);
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

