<template>

    <v-card outlined>
        <v-card-title>
            UserProfileUpdate
        </v-card-title>

        <v-card-text>
            <Number label="UserId" v-model="value.userId" :editMode="editMode"/>
            <String label="Email" v-model="value.email" :editMode="editMode"/>
            <String label="Password" v-model="value.password" :editMode="editMode"/>
            <String label="Nickname" v-model="value.nickname" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="userProfileUpdate"
            >
                UserProfileUpdate
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
    name: 'UserProfileUpdateCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.userId = 0;
        this.value.email = '';
        this.value.password = '';
        this.value.nickname = '';
    },
    watch: {
    },
    methods: {
        userProfileUpdate() {
            this.$emit('userProfileUpdate', this.value);
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

