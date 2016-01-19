package com.keepjob.common.mybatis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyBatisCriteria {
	protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;
    
    protected Integer skip;
    
    protected Integer rows;
    
    protected String limit;

    public MyBatisCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }
    
    public void setLimit(String limit){
    	this.limit=limit;
    }
    public void setLimit(Integer pageNo, Integer pageSize){
    	this.limit=((pageNo-1)*pageSize)+","+pageSize;
    }
    
    public String getLimit(){
    	return this.limit;
    }
    
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public Integer getSkip() {
		return skip;
	}

	public void setSkip(Integer skip) {
		this.skip = skip;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria createCriteria() {
        Criteria criteria = new Criteria();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
        skip = null;
        rows = null;
        limit=null;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String column, String condition) {
            if (column == null) {
            	throw new RuntimeException("column name for condition cannot be null");
            }
            criteria.add(new Criterion(column + " " +condition));
        }

        protected void addCriterion(String column, String condition, Object value) {
        	if (column == null) {
            	throw new RuntimeException("column name for condition cannot be null");
            }
            if (value == null) {
                throw new RuntimeException("value for condition cannot be null");
            }
            criteria.add(new Criterion(column+" "+condition, value));
        }

        protected void addCriterion(String column, String condition, Object oneValue, Object twoValue) {
        	if (column == null) {
            	throw new RuntimeException("column name for condition cannot be null");
            }
            if (oneValue == null || twoValue == null) {
                throw new RuntimeException("between values for balue cannot be null");
            }
            criteria.add(new Criterion(column+" "+condition, oneValue, twoValue));
        }

        public Criteria andIsNull(String column) {
            addCriterion(column, "is null");
            return (Criteria) this;
        }

        public Criteria andIsNotNull(String column) {
            addCriterion(column, "is not null");
            return (Criteria) this;
        }

        public Criteria andNotLike(String column, String value) {
            addCriterion(column, "not like", value);
            return (Criteria) this;
        }
        
        public Criteria andLike(String column, String value) {
            addCriterion(column, "like", value);
            return (Criteria) this;
        }
        
        public Criteria andEqualTo(String column, String value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }
        
        public Criteria andEqualTo(String column, Byte value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }
        
        public Criteria andEqualTo(String column, Character value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }
        
        public Criteria andEqualTo(String column, Short value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }

        public Criteria andEqualTo(String column, Integer value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }

        public Criteria andEqualTo(String column, Long value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }

        public Criteria andEqualTo(String column, Float value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }

        public Criteria andEqualTo(String column, Double value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }

        public Criteria andEqualTo(String column, Boolean value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }
        
        public Criteria andEqualTo(String column, Object value) {
            addCriterion(column, "=", value);
            return (Criteria) this;
        }
        
        public Criteria andEqualTo(String column, Date value) {
            addCriterion(column, "=", new java.sql.Date(value.getTime()));
            return (Criteria) this;
        }
        
        public Criteria andNotEqualTo(String column, String value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }
        
        public Criteria andNotEqualTo(String column, Byte value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }
        
        public Criteria andNotEqualTo(String column, Character value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }
        
        public Criteria andNotEqualTo(String column, Short value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String column, Integer value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String column, Long value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String column, Float value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String column, Double value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }

        public Criteria andNotEqualTo(String column, Boolean value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }
        
        public Criteria andNotEqualTo(String column, Object value) {
            addCriterion(column, "<>", value);
            return (Criteria) this;
        }
        
        public Criteria andNotEqualTo(String column, Date value) {
            addCriterion(column, "<>", new java.sql.Date(value.getTime()));
            return (Criteria) this;
        }
       
        public Criteria andGreaterThan(String column, String value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThan(String column, Byte value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThan(String column, Character value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThan(String column, Short value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String column, Integer value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String column, Long value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String column, Float value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String column, Double value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThan(String column, Boolean value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThan(String column, Object value) {
            addCriterion(column, ">", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThan(String column, Date value) {
            addCriterion(column, ">", new java.sql.Date(value.getTime()));
            return (Criteria) this;
        }
        
        public Criteria andGreaterThanOrEqualTo(String column, String value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThanOrEqualTo(String column, Byte value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThanOrEqualTo(String column, Character value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThanOrEqualTo(String column, Short value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String column, Integer value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String column, Long value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String column, Float value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String column, Double value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }

        public Criteria andGreaterThanOrEqualTo(String column, Boolean value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThanOrEqualTo(String column, Object value) {
            addCriterion(column, ">=", value);
            return (Criteria) this;
        }
        
        public Criteria andGreaterThanOrEqualTo(String column, Date value) {
            addCriterion(column, ">=", new java.sql.Date(value.getTime()));
            return (Criteria) this;
        }
        
        public Criteria andLessThan(String column, String value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThan(String column, Byte value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThan(String column, Character value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThan(String column, Short value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }

        public Criteria andLessThan(String column, Integer value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }

        public Criteria andLessThan(String column, Long value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }

        public Criteria andLessThan(String column, Float value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }

        public Criteria andLessThan(String column, Double value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }

        public Criteria andLessThan(String column, Boolean value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThan(String column, Object value) {
            addCriterion(column, "<", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThan(String column, Date value) {
            addCriterion(column, "<", new java.sql.Date(value.getTime()));
            return (Criteria) this;
        }
        
        public Criteria andLessThanOrEqualTo(String column, String value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThanOrEqualTo(String column, Byte value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThanOrEqualTo(String column, Character value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThanOrEqualTo(String column, Short value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String column, Integer value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String column, Long value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String column, Float value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String column, Double value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }

        public Criteria andLessThanOrEqualTo(String column, Boolean value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThanOrEqualTo(String column, Object value) {
            addCriterion(column, "<=", value);
            return (Criteria) this;
        }
        
        public Criteria andLessThanOrEqualTo(String column, Date value) {
            addCriterion(column, "<=", new java.sql.Date(value.getTime()));
            return (Criteria) this;
        }
        
        public Criteria andInString(String column, List<String> values) {
            addCriterion(column, "in", values);
            return (Criteria) this;
        }
        
        public Criteria andInByte(String column, List<Byte> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }
        
        public Criteria andInCharacter(String column, List<Character> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }
        
        public Criteria andInShort(String column, List<Short> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }

        public Criteria andInInteger(String column, List<Integer> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }

        public Criteria andInLong(String column, List<Long> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }

        public Criteria andInFloat(String column, List<Float> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }

        public Criteria andInDouble(String column, List<Double> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }

        public Criteria andInBoolean(String column, List<Boolean> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }
        
        public Criteria andInObject(String column, List<Object> values) {
        	addCriterion(column, "in", values);
            return (Criteria) this;
        }
        
        public Criteria andInDate(String column, List<Date> values) {
            addCriterion(column, "in", values);
            return (Criteria) this;
        }

        public Criteria andNotInString(String column, List<String> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }
        
        public Criteria andNotInByte(String column, List<Byte> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }
        
        public Criteria andNotInCharacter(String column, List<Character> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }
        
        public Criteria andNotInShort(String column, List<Short> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }

        public Criteria andNotInInteger(String column, List<Integer> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }

        public Criteria andNotInLong(String column, List<Long> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }

        public Criteria andNotInFloat(String column, List<Float> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }

        public Criteria andNotInDouble(String column, List<Double> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }

        public Criteria andNotInBoolean(String column, List<Boolean> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }
        
        public Criteria andNotInObject(String column, List<Object> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }
        
        public Criteria andNotInDate(String column, List<Date> values) {
            addCriterion(column, "not in", values);
            return (Criteria) this;
        }
        
        public Criteria andBetween(String column, String startValue, String endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andBetween(String column, Byte startValue, Byte endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andBetween(String column, Character startValue, Character endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andBetween(String column, Short startValue, Short endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andBetween(String column, Integer startValue, Integer endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andBerween(String column, Long startValue, Long endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andBetween(String column, Float startValue, Float endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andBetween(String column, Double startValue, Double endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andBetween(String column, Boolean startValue, Boolean endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andBetween(String column, Object startValue, Object endValue) {
            addCriterion(column, "between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andBetween(String column, Date startValue, Date endValue) {
            addCriterion(column, "between", new java.sql.Date(startValue.getTime()), new java.sql.Date(endValue.getTime()));
            return (Criteria) this;
        }
        
        public Criteria andNotBetween(String column, String startValue, String endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andNotBetween(String column, Byte startValue, Byte endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andNotBetween(String column, Character startValue, Character endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andNotBetween(String column, Short startValue, Short endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String column, Integer startValue, Integer endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andNotBerween(String column, Long startValue, Long endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String column, Float startValue, Float endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String column, Double startValue, Double endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }

        public Criteria andNotBetween(String column, Boolean startValue, Boolean endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andNotBetween(String column, Object startValue, Object endValue) {
            addCriterion(column, "not between", startValue, endValue);
            return (Criteria) this;
        }
        
        public Criteria andNotBetween(String column, Date startValue, Date endValue) {
            addCriterion(column, "not between", new java.sql.Date(startValue.getTime()), new java.sql.Date(endValue.getTime()));
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
