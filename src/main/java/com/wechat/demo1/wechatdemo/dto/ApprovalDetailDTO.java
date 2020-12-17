package com.wechat.demo1.wechatdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Description TODO
 * @Auther Administrator
 * @Date 2020/12/17 8:55
 */
@Data
public class ApprovalDetailDTO {

    private Integer errcode;
    private String errmsg;
    private InfoBean info;

    @Getter
    @Setter
    public static class InfoBean {

        @JsonProperty(value = "sp_no")
        private String spNo;

        @JsonProperty(value = "sp_name")
        private String spName;

        @JsonProperty(value = "sp_status")
        private Integer spStatus;

        @JsonProperty(value = "template_id")
        private String tempLateId;

        private ApplyerBean applyer;

        @JsonProperty(value = "apply_data")
        private ApplyDataBean applyData;

        @JsonProperty(value = "sp_record")
//        private List<SpRecordBean> spRecord;

        private List<NotifyerBean> notifyer;

        private List<CommentsBean> comments;

        @Getter
        @Setter
        public static class ApplyerBean {

            private String userId;

            private String partyId;
        }

        @Getter
        @Setter
        public static class ApplyDataBean {

            private String control;

            private String id;

            private ValueBean value;

            private List<TitleBean> title;


            public static class ValueBean {

                private String text;

                private List<?> tipe;
                private List<?> members;
                private List<?> departments;
                private List<?> files;
                private List<?> children;

                private SelectorBean selector;

                @JsonProperty(value = "stat_field")
                private List<?> statField;

                @JsonProperty(value = "new_money")
                private Double newMoney;

                @Getter
                @Setter
                public static class SelectorBean {
                    private String type;

                    private List<OptionsBean> options;

                    @Getter
                    @Setter
                    public static class OptionsBean {

                        /**
                         * key : option-16000000
                         * value:[{"text":"差旅费","lang":"zh_CN"}]
                         */
                        private String key;
                        private List<TextBean> value;

                        @Getter
                        @Setter
                        public static class TextBean {

                            /**
                             * text: 差旅费
                             * lang: zh_cn
                             */
                            private String text;

                            private String lang;
                        }
                    }
                }
            }

            @Getter
            @Setter
            public static class TitleBean {

                /**
                 * text: 文本控制
                 * lang: zh_CN
                 */
                private String text;
                private String lang;
            }
        }
    }

    @Getter
    @Setter
    public static class spRecordBean {

        @JsonProperty(value = "sp_status")
        private Integer spStatus;

        private Integer approverattr;

        private List<DetailsBean> details;

        @Getter
        @Setter
        public static class DetailsBean {

            private ApproverBean approver;

            private String speech;

            @JsonProperty(value = "sp_status")
            private Integer spStatus;

            private Long sptime;

            @JsonProperty(value = "medis_id")
            private List<?> mediaId;

            @Getter
            @Setter
            public static class ApproverBean {

                private String userId;
            }
        }
    }

    @Getter
    @Setter
    public static class NotifyerBean {

        private String userId;
    }

    @Getter
    @Setter
    public static class CommentsBean {

        private CommentUserInfoBean commentUserInfo;

        @JsonProperty(value = "commenttime")
        private Long commentTime;

        @JsonProperty(value = "commentcontent")
        private String commentContent;

        @JsonProperty(value = "commentId")
        private String commentId;

        @JsonProperty(value = "media_id")
        private List<String> medisId;

        @Getter
        @Setter
        public static class CommentUserInfoBean {
            private String userid;
        }
    }
}
