<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!!!!!" %>
</h1>

<form class="number-form">
    <label>
        <input class="number-input" type="number" onclick="formHandler(e)"/>
    </label>
    <button class="number-submit-btn" onclick="btnNumberHandler(e)">x2</button>
    <div class="answer"></div>
</form>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<script type="module">
    import {API} from "./js/constant";

document.querySelector('.number-form').addEventListener('submit',(e)  => {
        e.preventDefault();
    })
document.querySelector('.number-submit-btn').addEventListener('click', async (e) => {
        e.preventDefault();
        const num = ditocument.querySelector('.number-input').value
        const res = await getTwiceNum(num);
        document.querySelector('.answer').innerHTML = res;
    })

    const getTwiceNum = async () => {
        const res = await  axios.post(API + '/math/twice');
        return res.data
    }
</script>
</body>
</html>