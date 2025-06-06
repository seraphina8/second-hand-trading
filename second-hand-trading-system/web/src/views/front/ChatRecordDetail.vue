<template>
  <div class="modal-container">
    <el-card class="message-card " :body-style="{ padding: '0px' }">
      <div slot="header" class="clearfix">
        <span>消息记录</span>
        <el-button style="float: right; padding: 3px 0" type="text" @click="$emit('close')">关闭</el-button>
      </div>
      <JwChat-index
          ref="jwChat"
          v-model="formData.content"
          :config="config"
          :taleList="messageList"
          :scrollType="scrollType"
          :toolConfig="tool"
          :winBarConfig="winBarConfig"
          :showRightBox="false"
          @enter="send"
      >

        <template slot="header">
          <div>{{ currentChatRecord.productName }}</div>
        </template>
      </JwChat-index>
    </el-card>
  </div>
</template>
<script>
import Utils from "@/utils/utils";
import {EventBus} from "@/main";

export default {
  props: {
    chatRecordId: {
      type: Number,
      default: 0
    },
  },
  data() {
    return {
      chatRecordList: [],
      currentChatRecord: {},

      user: Utils.getUserInfo(),
      messageList: [],
      formData: {
        content: '',
      },

      scrollType: 'scroll',
      tool: {},
      config: {},
      winBarConfig: {
        active: "win01",
        width: "180px",
        listHeight: "60px",
        list: [],
        callback: this.bindWinBar,
      },
    }
  },
  created() {
    // 监听全局事件以接收新消息
    EventBus.$on('newMessages', (newMessages) => {
      for (let message of newMessages) {
        let chatRecord = this.winBarConfig.list.find(item => item.id === message.id);
        if (chatRecord) {

          for (let detail of message.chatRecordDetails) {
            if (message.id === this.currentChatRecord.id) {
              detail.status = 1;
            }
            chatRecord.info.chatRecordDetails.push(detail);
          }
          chatRecord.dept = message.chatRecordDetails.length > 0 ? message.chatRecordDetails[0].content : ''
          chatRecord.readNum = chatRecord.info.chatRecordDetails.length > 0 ? chatRecord.info.chatRecordDetails.filter(x => x.status === 0 && x.userId !== this.user.id).length : 0
          console.log(chatRecord)
          if (this.currentChatRecord.id === chatRecord.id) {
            for (let detail of message.chatRecordDetails) {
              this.messageList.push(this.convertMessage(detail, message));
            }
          }
          if (message.id === this.currentChatRecord.id) {
            this.readMessage()
          }
        } else {

        }
      }
    });
    this.load()
  },
  methods: {
    async load() {
      await this.request.get("/chatRecord/list").then(res => {
        this.chatRecordList = res.data
        this.winBarConfig.list = []
        this.chatRecordList.forEach(item => {
          this.winBarConfig.list.push(this.convertChatRecord(item))
        });
        this.winBarConfig.active = this.chatRecordList[0].id
        this.currentChatRecord = this.chatRecordList[0]
        this.loadMessage(this.currentChatRecord)
        console.log(this.winBarConfig)
      })
    },
    loadMessage() {
      this.messageList = []
      console.log(this.currentChatRecord)
      this.currentChatRecord.chatRecordDetails.forEach(item => {
        this.messageList.push(this.convertMessage(item, this.currentChatRecord))
      })
      this.readMessage()
    },
    readMessage() {
      this.request.put("/chatRecordDetail/read/" + this.currentChatRecord.id).then(res => {
      })
    },

    send(e) {
      console.log(e)
      this.formData.chatRecordId = this.currentChatRecord.id
      this.request.post("/chatRecordDetail", this.formData).then(res => {
        if (res.code === 200) {
        }
      })
    },
    convertMessage(message, chatRecord) {
      return {
        date: message.createTime,
        text: {text: message.content},
        mine: message.userId === this.user.id,
        name: message.userId === this.user.id ? chatRecord.sellerName : chatRecord.userName,
        img: Utils.getFileInfo(message.userId === this.user.id ? chatRecord.sellerAvatarUrl : chatRecord.userAvatarUrl)[0].url,
      }
    },
    convertChatRecord(chatRecord) {
      console.log(chatRecord)
      console.log(111)
      return {
        id: chatRecord.id,
        dept: chatRecord.chatRecordDetails.length > 0 ? chatRecord.chatRecordDetails[0].content : '',
        name: chatRecord.userId === this.user.id ? chatRecord.sellerName : chatRecord.userName,
        img: Utils.getFileInfo(chatRecord.userId === this.user.id ? chatRecord.sellerAvatarUrl : chatRecord.userAvatarUrl)[0].url,
        readNum: chatRecord.chatRecordDetails.length > 0 ? chatRecord.chatRecordDetails.filter(x => x.status === 0 && x.userId !== this.user.id).length : 0,
        productName: chatRecord.product.name,
        info: chatRecord
      }
    },
    bindWinBar(play = {}) {
      const {type, data = {}} = play
      console.log(data);
      if (type === 'winBar') {
        const {id, dept, name, img} = data
        this.winBarConfig.active = id
        this.currentChatRecord = data.info
        data.readNum = 0
        console.log(data.info.chatRecordDetails);
        data.info.chatRecordDetails.forEach(item => {
          item.status = 1;
        })
        this.loadMessage()
      }
    }
  }
}
</script>


<style scoped>
.message-card {
  width: 1550px
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
