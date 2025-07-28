<template>
    <div>
        <Number
            label="AuthId"
            v-model="value.authId"
            :editMode="editMode"
        />
        <Number
            label="UserId"
            v-model="value.userId"
            :editMode="editMode"
        />
        <String
            label="UpdateToken"
            v-model="value.updateToken"
            :editMode="editMode"
        />
        <String
            label="UpdateExpiredAt"
            v-model="value.updateExpiredAt"
            :editMode="editMode"
        />
        <v-row class="ma-0 pa-0">
            <v-spacer></v-spacer>
            <v-btn width="64px" color="primary" @click="save">
                저장
            </v-btn>
        </v-row>
    </div>
</template>


<script>
import BaseEntity from './base-ui/BaseEntity.vue'

export default {
    name: 'Auth',
    mixins:[BaseEntity],
    components:{
    },
    
    data: () => ({
        path: "auths",
        value: {
        }
    }),
    created(){
    },
    computed:{
    },
    methods: {
    },
}
</script>
