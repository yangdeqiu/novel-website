package cn.book.comm.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BookUIResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    //查询数据总数量
    private Integer total;
    //分页查询的list结果
    private List<?> rows;

    public BookUIResult() {
    }

    public BookUIResult(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public BookUIResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static BookUIResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");
            List<?> list = null;
            if (data.isArray() && data.size() > 0) {
                list = MAPPER.readValue(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new BookUIResult(jsonNode.get("total").intValue(), list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
