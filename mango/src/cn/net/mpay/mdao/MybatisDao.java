package cn.net.mpay.mdao;

import org.apache.ibatis.annotations.Select;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MybatisDao {
	@Select("select count(*) from user;")
	public int countALL();

	/*
	 * @Select("select * from goods where goods_id=#{goods_id};") public Goods
	 * queryGoods(int goods_id);
	 * 
	 * @Select(
	 * "SELECT * FROM admin_user WHERE user_id IN (SELECT user_id FROM user_role WHERE role_id =#{role_id})"
	 * ) public List<AdminUser> findAllUser(int role_id);
	 * 
	 * @Select ("SELECT goods_kc_name FROM pack WHERE goods_id=#{goods_id};")
	 * public List<String> findComponChildName(int goods_id);
	 * 
	 * @Select ("SELECT * from color where kc_name=#{kc_name};") public
	 * List<Color> findComponColors(String kc_name);
	 * 
	 * @Select("SELECT * FROM size WHERE inventory >0 and color_id=#{color_id}")
	 * public List<Size> findSizeByColorId(int color_id);
	 * 
	 * 
	 * @Select("SELECT * FROM color WHERE inventory >0 AND goods_id=#{goods_id}")
	 * public List<Color> getGoodColor(int goods_id);
	 * 
	 * @Select("SELECT * FROM order_goods WHERE order_id=#{order_id}") public
	 * List<Order_goods> getOrderGoodById(int order_id);
	 * 
	 * @Select("SELECT color FROM color WHERE color_id=#{color_id}") public
	 * String getColorName(int color_id);
	 * 
	 * @Select("SELECT size FROM size WHERE size_id=#{size_id}") public String
	 * getSizeName(int size_id);
	 * 
	 * @Select("SELECT * FROM order_info WHERE order_id=#{order_id}") public
	 * Order_info getOrderById(int order_id);
	 * 
	 * //@Select(
	 * "SELECT * FROM product_info WHERE pro_sku IN (SELECT goodsn FROM order_goods WHERE order_id =#{order_id} )"
	 * ) //@Select(
	 * "SELECT * FROM product_info WHERE pro_sku IN (SELECT goods_code FROM goods AS a,order_goods AS b,goods_kc AS c WHERE a.goods_id=b.goodid AND b.order_id =#{order_id} AND a.goods_kc_name=c.goodName AND c.goods_type=2)"
	 * )
	 * 
	 * @Select("SELECT * FROM product_info WHERE pro_sku IN (${_parameter});")
	 * public List<Product_info> getProinfoById(String _parameter);
	 * 
	 * @Select(
	 * "SELECT c.goods_code FROM goods AS a,order_goods AS b,goods_kc AS c WHERE a.goods_id=b.goodid AND b.order_id =#{order_id} AND a.goods_kc_name=c.goodName AND c.goods_type=2 AND (CASE WHEN b.color_id>0 THEN c.color= (SELECT color FROM color WHERE color_id=b.color_id) ELSE 0=0 END) AND (CASE WHEN b.size_id>0 THEN c.size =(SELECT size FROM size WHERE size_id=b.size_id) ELSE 0=0 END);"
	 * ) public List<String> getProSku(int order_id);
	 * 
	 * 
	 * @Select(
	 * "select * from admin_user where user_name=#{username},email=#{email},password=#{password}"
	 * ) public Set<AdminUser> getAdminUser(AdminUser user);
	 * 
	 * public List<AdminUser> getAdminUsers(AdminUser user);
	 * 
	 * 
	 * @Select(
	 * "SELECT COUNT(*) FROM goods AS gg,order_goods AS og WHERE og.goodid=gg.goods_id AND og.order_id=#{order_id} AND gg.ispac IN (${supplier});"
	 * ) public int findSuppilerByOrderId(Map<String, Object> map);
	 * 
	 * @Select(
	 * "SELECT og.*,gg.ASIN as tb_asin FROM goods AS gg,order_goods AS og WHERE og.goodid=gg.goods_id AND og.order_id=#{order_id} AND gg.ispac =#{supplier};"
	 * ) public List<Order_goods> getOrderGoodsExt(Map<String, Object> map);
	 * 
	 * 
	 * @Select(
	 * "SELECT og.*,gg.ASIN as tb_asin FROM goods AS gg,order_goods AS og WHERE og.goodid=gg.goods_id AND og.order_id=#{order_id} AND gg.ispac =#{supplier};"
	 * ) public List<Map<String, Object>> getOrderGoodsExt2(Map<String, Object>
	 * map);
	 * 
	 * @Update(
	 * "update order_info set extends_sn=#{extends_sn} where order_id=#{order_id}"
	 * ) public int updateOrderInfo(Map<String, Object> map);
	 * 
	 * @Select(
	 * "SELECT og.*,ss.supplier AS tb_supplier,gg.goodnumber AS tb_goodskcnum,CONCAT(gg.goods_id,'/',i.src) AS tb_logopath,cc.color AS tb_color_name,si.size AS tb_size_name "
	 * + "FROM order_goods AS og " +
	 * "LEFT JOIN goods AS gg ON og.goodid=gg.goods_id " +
	 * "LEFT JOIN supplier AS ss ON gg.supplier_id=ss.supplier_id " +
	 * "LEFT JOIN color AS cc ON cc.color_id =og.color_id " +
	 * "LEFT JOIN size AS si ON si.size_id=og.size_id " +
	 * "LEFT JOIN image AS i ON i.goods_id=gg.goods_id AND i.type=0 " +
	 * "WHERE og.order_id=#{order_id};") public List<Order_goods>
	 * findGoodsByOrderId(int order_id);
	 * 
	 * 
	 * @Select("SELECT * FROM webapp WHERE facid=#{facid};") public List<WebApp>
	 * queryByFid(int facid);
	 * 
	 * 
	 * @Select("SELECT * FROM webapp WHERE facid=#{facid} AND app_name=#{app_name};"
	 * ) public WebApp queryOne(WebApp app);
	 */

}
