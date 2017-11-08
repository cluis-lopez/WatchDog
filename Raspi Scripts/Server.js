//import Pusher from 'pusher.js';
const Pusher = require('/usr/local/lib/node_modules/pusher-js');
var spawn = require("child_process").spawn;

const socket = new Pusher('2ffdf405c4858a8972c4', {
	cluster: 'eu',
	authEndpoint: 'https://watchdog-182012.appspot.com/Auth'
});
console.log("Abriendo el canal");

const channel = socket.subscribe('private-request');
channel.bind('client-feedme', function(data) {
	if (data.message == 'SENDME'){
		var process = spawn('python', ['Main.py']);
		console.log(data.message);
	};
	if (data.message == 'SEND_Datos'){
		var process = spawn('python', ['Get_Temp.py']);
		console.log(data.message);
	};
	if (data.message == 'SEND_Foto'){
		var process = spawn('python', ['Get_Foto.py']);
		console.log(data.message);
	}
});
