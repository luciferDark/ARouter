package com.ll.arouter_process.element_utils.beans;

/**
 * 代码语句bean
 */
public class StatementBean {
    private String statementFormat;
    private Object[] args;

    public String getStatementFormat() {
        return statementFormat;
    }

    public void setStatementFormat(String statementFormat) {
        this.statementFormat = statementFormat;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    public StatementBean() {
    }

    public StatementBean(String statementFormat) {
        this.statementFormat = statementFormat;
        this.args = null;
    }

    public StatementBean(String statementFormat, Object[] args) {
        this.statementFormat = statementFormat;
        this.args = args;
    }
}
