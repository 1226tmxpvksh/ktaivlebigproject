<template>

    <v-card outlined>
        <v-card-title>
            UserRegister
        </v-card-title>

        <v-card-text>
            <String label="Email" v-model="value.email" :editMode="editMode"/>
            <String label="Password" v-model="value.password" :editMode="editMode"/>
            <String label="Nickname" v-model="value.nickname" :editMode="editMode"/>
        </v-card-text>

        <v-card-actions>
            <v-spacer></v-spacer>
            <v-btn
                    color="deep-purple lighten-2"
                    text
                    @click="userRegister"
            >
                UserRegister
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
    name: 'UserRegisterCommand',
    components:{
    },
    props: {},
    data: () => ({
        editMode: true,
        value: {},
    }),
    created() {
        this.value.email = '';
        this.value.password = '';
        this.value.nickname = '';
    },
    watch: {
    },
    methods: {
        userRegister() {
            this.$emit('userRegister', this.value);
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

