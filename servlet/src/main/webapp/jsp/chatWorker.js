// chatWorker.js
let websocket = null;

self.onmessage = function(event) {
    const data = event.data;

    if (data.type === 'connect') {
        websocket = new WebSocket(data.url);

        websocket.onopen = function() {
            self.postMessage({ type: 'status', message: 'Connected to chat!' });
        };

        websocket.onmessage = function(event) {
            self.postMessage({ type: 'message', message: event.data });
        };

        websocket.onclose = function() {
            self.postMessage({ type: 'status', message: 'Disconnected from chat.' });
        };

        websocket.onerror = function(event) {
            self.postMessage({ type: 'status', message: 'Error in connection: ' + event });
        };
    }

    if (data.type === 'send') {
        if (websocket && websocket.readyState === WebSocket.OPEN) {
            websocket.send(data.message);
        }
    }
};
