<html lang="en">
<head>
<#--    <script type="text/javascript" src="../js/toJson.js"></script>-->
    <title>Create</title>
</head>
<body>
  <form>
    <label for="code_snippet">Enter code here:</label><textarea id="code_snippet"></textarea>
    <label for="time_restriction"></label><input id="time_restriction" type="text"/>
    <label for="views_restriction"></label><input id="views_restriction" type="text"/>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
  </form>
    <script>
      function send() {
          let object = {
              "code": document.getElementById("code_snippet").value
              "time": document.getElementById("time_restriction").value
              "views": document.getElementById("views_restriction").value
          };

          let json = JSON.stringify(object);

          let xhr = new XMLHttpRequest();
          xhr.open("POST", '/api/code/new', false)
          xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
          xhr.send(json);

          if (xhr.status === 200) {
              alert("Success!");
          }
      }
  </script>
</body>
</html>
