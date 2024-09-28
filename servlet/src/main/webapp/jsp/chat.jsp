<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WebSocket Chat Example</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
        #chat-box, #users-box {
            border: 1px solid #ccc;
            height: 200px;
            overflow-y: scroll;
            padding: 10px;
            margin-bottom: 10px;
        }
        #message {
            width: 300px;
        }
        button {
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        button:hover {
            background-color: #45a049;
        }
        #users-box {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
<h2>Welcome, <%= request.getParameter("username") %>!</h2>

<div id="chat-box">
    <p>Recent Chat History</p>
</div>

<div id="users-box">
    <p>Online Users</p>
</div>

<input type="text" id="message" placeholder="Enter Message">
<button id="send">Send</button>

<script>
    let worker = new Worker('chatWorker.js');
    let username = "<%= request.getParameter("username") %>";

    worker.postMessage({ type: 'connect', url: 'http://localhost:8080/servlet/chat' }); //Asyncnchrounus is default true

    worker.onmessage = function(event) {
        const data = event.data;

        if (data.type === 'message') {
            if (data.message.startsWith('USERLIST:')) {
                // Display online users
                let users = data.message.replace('USERLIST:', '').split(',');
                $('#users-box').html('<p>Online Users:</p><ul>' + users.map(u => '<li>' + u + '</li>').join('') + '</ul>');
            } else {
                $('#chat-box').append('<p>' + data.message + '</p>');
                $('#chat-box').scrollTop($('#chat-box')[0].scrollHeight);
            }
        }

        if (data.type === 'status') {
            console.log(data.message);
        }
    };

    $('#send').click(function() {
        let message = $('#message').val();
        if (message.trim() !== '') {
            worker.postMessage({ type: 'send', message: username + ': ' + message });
            $('#message').val('');  // Clear the input field
        }
    });
</script>
</body>
</html>
