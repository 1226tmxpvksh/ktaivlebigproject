<script setup>
import {
  VerticalNavLink,
  VerticalNavSectionTitle,
} from '@layouts'
</script>

<template>
    <ul>
        <VerticalNavSectionTitle :item="{ heading: '유저' }" />
        <VerticalNavLink
            :item="{
                title: '유저',
                to: '/users',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'UserReadModel',
                to: '/userReadModels',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '인증' }" />
        <VerticalNavLink
            :item="{
                title: '인증',
                to: '/auths',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'AuthReadModel',
                to: '/authReadModels',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '게시판' }" />
        <VerticalNavLink
            :item="{
                title: '게시판',
                to: '/boards',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'BoardReadModel',
                to: '/boardReadModels',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: '보고서' }" />
        <VerticalNavLink
            :item="{
                title: '보고서',
                to: '/reports',
            }"
        />
        <VerticalNavLink
            :item="{
                title: 'ReportReadModel',
                to: '/reportReadModels',
            }"
        />
        <VerticalNavSectionTitle :item="{ heading: 'ApiGateway' }" />
    </ul>
</template>
