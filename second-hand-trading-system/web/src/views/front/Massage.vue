<template>
  <div class="modal-container">
    <el-card class="message-card " :body-style="{ padding: '0px' }">
      <div slot="header" class="clearfix">
        <span>消息记录</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="$emit('close')">关闭</el-button>
      </div>
      <vue-advanced-chat
          v-if="load"
          ref="chat"
          :text-messages="JSON.stringify({
        ROOMS_EMPTY: '没有联系人',
        ROOM_EMPTY: '没有选择联系人',
        NEW_MESSAGES: '新消息',
        MESSAGE_DELETED: '该消息已删除',
        MESSAGES_EMPTY: '没有消息',
        CONVERSATION_STARTED: '对话开始于：',
        TYPE_MESSAGE: '输入信息',
        SEARCH: '搜索',
        IS_ONLINE: '在线',
        LAST_SEEN: '上次在线',
        IS_TYPING: '正在输入...',
        CANCEL_SELECT_MESSAGE: '取消选择消息'
      })"
          :current-user-id="currentUserId"
          :rooms="JSON.stringify(rooms.data)"
          :messages="JSON.stringify(currentRoomMessages)"
          :menu-actions="JSON.stringify(menuActions)"
          :message-selection-actions="JSON.stringify(messageSelectionActions)"
          :message-actions="JSON.stringify([])"
          show-add-room="false"
          show-audio="false"
          show-files="false"
          show-new-messages-divider="false"
          :room-id="roomId"
          :loading-rooms="loadingRooms"
          :rooms-loaded="true"
          :room-message="roomMessage"
          :messages-loaded="messagesLoaded"
          @fetch-messages="fetchMessages($event.detail[0])"
          @send-message="sendMessage($event.detail[0])"
          @menu-action-handler="menuActionHandler($event.detail[0])"
      />
    </el-card>
  </div>
</template>

<script>
import {register} from 'vue-advanced-chat'
import Utils from "@/utils/utils";
import {EventBus} from "@/main";

register()

export default {
  props: {
    selectRoomId: {
      type: Number,
      required: false
    }
  },
  data() {
    return {
      load: false,
      roomsLoaded: true,
      loadingRooms: false,
      messagesLoaded: true,
      currentMsgSeq: 1,
      roomMessage: "",
      roomId: 7,
      currentUserId: Utils.getUserInfo().id,
      rooms: {
        pageNum: 1,
        pageSize: 1000,
        total: 0,
        data: [],
      },
      currentRoomMessages: [],
      // 右上角菜单
      menuActions: [
        {name: 'showProduct', title: '查看商品详情'},
      ],
      messageSelectionActions: [],
    }
  }, // end data


  created() {
    // 监听全局事件以接收新消息
    EventBus.$on('newMessages', (newMessages) => {
      for (let message of newMessages) {
        switch (message.type) {
          case 1: {
            let chatRoom = [this.convertChatRoom(message.data)]
            this.rooms.data = chatRoom.concat(this.rooms.data);
            break
          }
          case 2: {
            let room = this.getRoom(message.data.chatRoomId)
            let chatMessage = this.convertChatMessage(message.data);
            room.lastMessage = chatMessage;
            room.messageList.push(chatMessage);
            if (message.data.userId !== this.currentUserId && this.roomId !== message.data.chatRoomId.toString()) {
              room.unreadCount++;
            }
            break
          }
        }
      }
    });
  },
  mounted() {
    this.getChatRoomList()
  },
  methods: {
    //获取分页数据
    getChatRoomList() {
      let data = {
        pageNum: this.rooms.pageNum,
        pageSize: this.rooms.pageSize,
      }
      this.request.get("/chatRoom/page", {
        params: data
      }).then(res => {
        let chatRoomList = []
        res.data.list.forEach(item => {
          chatRoomList.push(this.convertChatRoom(item))
        })
        this.rooms.data = chatRoomList
        this.rooms.total = res.data.total
        console.log("this.rooms", this.rooms)
        if (!this.selectRoomId) {
          if (chatRoomList.length > 0) {
          this.roomId = chatRoomList[0].roomId
          }
        } else {
          this.roomId = this.selectRoomId.toString()
        }
        this.load = true
      })
    },
    getLastMessage(chatRoom) {
      if (!chatRoom.lastMessage) {
        return {}
      }
      return this.convertChatMessage(chatRoom.lastMessage)
    },
    convertChatRoom(chatRoom) {
      console.log("chatRoom", chatRoom)
      return {
        roomId: chatRoom.id.toString(),
        roomName: chatRoom.product.name,
        productId: chatRoom.productId,
        avatar: this.getFileInfo(chatRoom.avatar)[0].url,
        unreadCount: chatRoom.unreadCount,
        lastUpdated: chatRoom.updateTime,
        index: 1,
        users: [
          this.convertChatUser(chatRoom.user1),
          this.convertChatUser(chatRoom.user2)
        ],
        lastMessage: this.getLastMessage(chatRoom)
      }
    },
    convertChatUser(chatUser) {
      return {
        _id: chatUser.id.toString(),
        username: chatUser.userName,
        avatar: this.getFileInfo(chatUser.avatar)[0].url
      }
    },
    getRoom(roomId) {
      console.log("roomId1", roomId)
      console.log("roomId1", this.rooms)
      let room = this.rooms.data.find(item =>
          item.roomId === roomId.toString()
      )
      console.log("room11", room)
      if (!room.messageList) {
        room.messageList = []
      }
      return room
    },

    async getMessageList(roomId) {
      let room = this.getRoom(roomId)
      console.log("room111", room)
      room.messageList = await this.getChatMessageList()
      if (room.messageList.length > 0) {
        room.lastMessage = room.messageList[room.messageList.length - 1]
      }
      return room.messageList
    },


    async getChatMessageList() {
      let room = this.getRoom(this.roomId)
      let messageList = room.messageList;
      console.log("messageList", messageList)
      let data = {
        chatRoomId: this.roomId,
        lastId: messageList.length > 0 ? messageList[0]._id : null,
      }
      await this.request.get("/chatMessage/page", {
        params: data,
      }).then(res => {
        if (res.data.list.length === 0) {
          this.messagesLoaded = true;
        }
        let chatMessageList = []
        res.data.list.forEach(item => {
          chatMessageList.push(this.convertChatMessage(item))
        })
        chatMessageList.reverse()
        messageList = chatMessageList.concat(messageList);
        console.log("messageList", messageList)
      })
      return messageList;
    },
    convertChatMessage(chatMessage) {
      return {
        _id: chatMessage.id.toString(),
        senderId: chatMessage.userId.toString(),
        username: chatMessage.userName,
        avatar: this.getFileInfo(chatMessage.avatar)[0].url,
        content: chatMessage.content,
        timestamp: chatMessage.createTime,
        date: chatMessage.createTime.substring(0, 10)
      }
    },

    readMessage(room) {
      room.unreadCount = 0;
      this.request.put("/chatMessage/read/" + room.roomId).then(res => {
      })
    },


    // 点击了聊天会话，会触发这个回调函数，在这里加载消息
    async fetchMessages({room, options = {}}) {
      this.messagesLoaded = false;
      this.$emit('show-demo-options', false)
      if (options.reset) {
        //this.resetMessages()
      }
      console.log("选项：", options);
      console.log("选中会话：" + room.roomId);
      this.roomId = room.roomId
      let currentRoom = this.getRoom(this.roomId)
      console.log(" currentRoom ", currentRoom)
      console.log(" currentRoom.messageList.data.length ", currentRoom.messageList.length)
      this.currentRoomMessages = await this.getMessageList(this.roomId)
      this.readMessage(currentRoom)
      console.log(" this.currentRoomMessages ", this.currentRoomMessages)
    },

    // 点击了发送按钮，则执行发送消息
    async sendMessage({content, roomId, files, replyMessage}) {
      console.log("当前发送消息到对话：" + roomId);
      console.log("content：" + content);
      let formData = {
        chatRoomId: roomId,
        content: content,
      };
      if (files) {
        this.$message.info(`暂未开放，敬请期待`);
        return;
      }
      this.request.post("/chatMessage", formData).then(res => {
        if (res.code === 200) {
        }
      })
    },
    async menuActionHandler({roomId, action}) {
      let room = this.getRoom(roomId)
      switch (action.name) {
        case 'showProduct':
          window.open("/front/productDetail/" + room.productId)
          break
      }
      console.log(roomId, action);
    },
    getFileInfo(info) {
      if (!info) {
        return [{}]
      }
      return JSON.parse(info);
    },
  }
  ,
  beforeDestroy() {
    EventBus.$off('newMessages');
  }
}// end default
</script>

<style scoped>
.message-card {
  width: 1200px
}

.modal-container {
  display: flex;
  justify-content: center;
  align-items: center;
  position: absolute;
  top: 0;
  left: 0;
  right: 0;
  bottom: 0;
  z-index: 999;
}

</style>
