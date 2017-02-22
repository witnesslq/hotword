<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv=”refresh” content=”2″>
  <title>search hot word</title>

  <script type="text/javascript">
    //传参
    function transportPara() {
      var form = document.forms[0];
      form.action = "${pageContext.request.contextPath}/hotword.do";
      form.method = "post";
      form.submit();
    }
  </script>
</head>
