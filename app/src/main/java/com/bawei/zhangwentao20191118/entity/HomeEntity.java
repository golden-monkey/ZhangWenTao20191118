package com.bawei.zhangwentao20191118.entity;

import java.util.List;
/**
 * 作者：张文涛
 * 功能：封装类
 * 时间：2019年11月18日11:31:30
 */
public class HomeEntity {

    /**
     * result : [{"id":50,"releaseTime":1539582903000,"source":"全天候科技","title":"行业薪酬\u201c大跳水\u201d，区块链真的凉了？"},{"id":49,"releaseTime":1539582707000,"source":"蓝狐笔记","title":"为什么说区块链没那么简单？"},{"id":48,"releaseTime":1539582496000,"source":"网事风云","title":"区块链落地实体经济，这个领域可能是先锋"},{"id":47,"releaseTime":1539582250000,"source":"蓝狐笔记","title":"为什么说区块链\u201c无需信任\u201d？"},{"id":46,"releaseTime":1539582121000,"source":"懂懂笔记","title":"\u201c大会\u201d要开、\u201c大屏\u201d要占：区块链\u201c药\u201d不能停"}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 50
         * releaseTime : 1539582903000
         * source : 全天候科技
         * title : 行业薪酬“大跳水”，区块链真的凉了？
         */

        private int id;
        private long releaseTime;
        private String source;
        private String title;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
