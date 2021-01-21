package com.example.x_smartcity_1.bean;

/**
 * author : 关鑫
 * Github : XGKerwin
 * date   : 2021/1/21  14:59
 */
public class GetCommitById {
    /**
     *             "num": 9,
     *             "commit": "祖国万岁",
     *             "commitTime": "2020-10-01 08:18:18",
     *             "reviewer": "tom"
     */

    private String num,commit,commitTime,reviewer;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public String getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(String commitTime) {
        this.commitTime = commitTime;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }
}
