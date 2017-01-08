<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form id="save" method="post">
 					<tr>
						<td style="text-align:right;">时间段:</td>
						<td>
							<input type="text" name="" id="" placeholder="" class="" value="" data-options="">
						</td>
					
						<td style="text-align:right;">结算金额:</td>
						<td>
							<input type="text" name="" id="" placeholder="" class="" value="" data-options="">
						</td>
						<td style="width:20%; text-align:right;">支付方式:</td>
						<td>
						<select id="" name="" class="span4">
							<option value="0">现金</option>
							<option value="1">刷卡</option>
							<option value="2">微信支付</option>
							<option value="3">支付宝</option>
							<option value="4">VIP卡</option>
							<option value="5">赠券</option>
						</select>
						</td>
					</tr>
					
					 <tr>
						<td style="text-align:right;">定金:</td>
						<td>
							<input type="text" name="" id="" placeholder="" class="" value="${result.age }" data-options="">
						</td>
						<td style="text-align:right;">结算单号:</td>
						<td>
							<input type="text" name="" id="" placeholder="" class="" value="" data-options="">
						</td>
						<td style="text-align:right;">核对人:</td>
						<td>
							<input type="text" name="" id="" placeholder="" class="" value="" data-options="">
						</td>
					</tr>
					<tr>
						<td style="text-align:right;">备注:</td>
						<td>
							<input type="textarea" name="" id="" placeholder="" class="" value="${result.age }" data-options="">
						</td>
					</tr>
	</form>
</body>

<script type="text/javascript">
	
</script>
</html>