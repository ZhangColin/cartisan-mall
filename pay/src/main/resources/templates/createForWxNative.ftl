<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>支付</title>
</head>
<body>
<div id="qrcode"></div>
<script src="https://cdn.bootcdn.net/ajax/libs/qrcodejs/1.0.0/qrcode.min.js"></script>
<script type="text/javascript">
  new QRCode(document.getElementById('qrcode'), "${codeUrl}");

	// $(function() {
  //   setInterval(function (){
	// 		$.ajax({
  //       url: '/pay/queryByOrderId',
  //       data: {
	// 				'orderId': '123456'
  //       },
  //       success: function (result) {
  //
	// 			},
	// 			error: function (error) {
	//
	// 			}
  //     })
  //   }, 2000)
	// });
</script>
</body>
</html>
