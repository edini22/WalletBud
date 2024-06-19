
class WebSocketService {
    constructor(url, token) {
        this.url = url;
        this.token = token;
        this.ws = null;
    }

    connect() {
        this.ws = new WebSocket(this.url);

        this.ws.onopen = () => {
            alert('WebSocket connected');
            this.ws.send(this.token);
        };

        this.ws.onmessage = (event) => {
            const data = JSON.parse(event.data);
            alert(data);
            this.onMessageCallback(data);
        };

        this.ws.onclose = () => {
            alert('WebSocket disconnected');
            setTimeout(() => this.connect(), 1000); // Reconnect after 1 second
        };

        this.ws.onerror = (error) => {
            console.error('WebSocket error', error);
        };
    }

    onMessage(callback) {
        this.onMessageCallback = callback;
    }

    sendMessage(message) {
        this.ws.send(JSON.stringify(message));
    }
}

// const websocketService = new WebSocketService('ws://localhost:8000/WalletBud-1.0-SNAPSHOT/api/notifs');
// export default websocketService;

export default WebSocketService;