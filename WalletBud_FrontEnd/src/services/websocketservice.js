import { userStore } from "@/store/userStore";

class WebSocketService {
    constructor(url, token) {
        this.url = url;
        this.token = token;
        this.ws = null;
        this.close = false;
        this.store = userStore();
    }

    connect() {
        this.ws = new WebSocket(this.url);

        this.ws.onopen = () => {
            alert('WebSocket connected' + this.store.username);
            this.ws.send(this.token);
        };

        this.ws.onmessage = (event) => {
            const data = JSON.parse(event.data);
            alert(JSON.stringify(data));

            this.store.addNotif(data);
            alert(this.store.notifs[this.store.notifs.length - 1].descricao)
            // this.onMessageCallback(data);
        };

        this.ws.onclose = () => {
            if (!this.close) {
                alert('WebSocket disconnected');
                setTimeout(() => this.connect(), 500); // Reconnect after 1 second
            } else {
                alert('WebSocket disconnected2');
            }
        };

        this.ws.onerror = (error) => {
            console.error('WebSocket error', error);
        };
    }

    disconnect() {
        this.close = true;
        this.ws.close();
    }

    // onMessage(callback) {
    //     this.onMessageCallback = callback;
    // }

    sendMessage(message) {
        this.ws.send(JSON.stringify(message));
    }
}

// const websocketService = new WebSocketService('ws://localhost:8000/WalletBud-1.0-SNAPSHOT/api/notifs');
// export default websocketService;

export default WebSocketService;