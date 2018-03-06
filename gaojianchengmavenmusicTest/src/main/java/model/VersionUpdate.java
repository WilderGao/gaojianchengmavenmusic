package model;

/**
 * @author:Wilder Gao
 * @time:2018/3/6
 * @Discription：
 */
public class VersionUpdate {
    private int versionId;
    private String versionUrl;
    private String statement;

    public int getVersionId() {
        return versionId;
    }

    public void setVersionId(int versionId) {
        this.versionId = versionId;
    }

    public String getVersionUrl() {
        return versionUrl;
    }

    public void setVersionUrl(String versionUrl) {
        this.versionUrl = versionUrl;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    @Override
    public String toString() {
        return "VersionUpdate{" +
                "versionId=" + versionId +
                ", versionUrl='" + versionUrl + '\'' +
                ", statement='" + statement + '\'' +
                '}';
    }
}
