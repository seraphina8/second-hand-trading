<template>
  <!-- 此组件不需要渲染任何内容，因为它主要在后台工作 -->
</template>

<script>

import {EventBus} from "@/main";

export default {
  name: 'MessagePoller',
  data() {
    return {};
  },
  mounted() {
    this.fetchMessages();
  },
  methods: {
    async fetchMessages() {
      try {
        const res = await this.request.get("chatRoom/newMessage");
        if (res.code === 200 && res.data.length > 0) {
          // 触发一个全局事件，将消息传递给其他组件
          EventBus.$emit('newMessages', res.data);
        }
        await this.fetchMessages();
      } catch (error) {
        console.error('Failed to fetch messages:', error);
        await this.fetchMessages();
        // 可以在这里处理错误，比如通知用户或重试
      }
    }
  },
};
</script>
