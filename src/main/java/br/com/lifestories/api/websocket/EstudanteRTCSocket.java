package br.com.lifestories.api.websocket;

import br.com.lifestories.api.mock.Aluno;
import br.com.lifestories.model.entity.Estudante;
import br.com.lifestories.model.service.EstudanteService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/ws-rtc-estudante")
public class EstudanteRTCSocket {

    private static final Set<Session> SESSION = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public Session onOpen(Session session) {
        SESSION.add(session);
        sendId(session.getId());

        return session;
    }

    private void sendId(String id) {
        Map<String, String> map = new HashMap<>();
        map.put("id", id);
        map.put("flag", "update");
        String json = new Gson().toJson(map);
        try {
            for (Session session : SESSION) {
                if (session.getId().equals(id)) {
                    session.getBasicRemote().sendText(json);
                }
            }
        } catch (IOException e) {
        }
    }

    @OnClose
    public void onClose(Session session) {
        SESSION.remove(session);
    }

    @OnMessage
    public void onMessage(String json) throws Exception {
        Map jsonObj = new Gson().fromJson(json, Map.class);
        String flag = (String) jsonObj.get("flag");
        if (flag.equals("ask")) {
            String idConnection = (String) jsonObj.get("idConnection");
            String idConnectionElderly = (String) jsonObj.get("idConnectionElderly");
            String idRTC = (String) jsonObj.get("idRTC");
            String idString = (String) jsonObj.get("id");
            Long id = Long.parseLong(idString);
            EstudanteService service = new EstudanteService();
            Estudante estudante = service.readById(id);

            Aluno aluno = new Aluno();
            aluno.setFlag(flag);
            aluno.setIdConexaoSocket(idConnection);
            aluno.setIdRTC(idRTC);
            aluno.setEstudante(estudante);
            aluno.setIdConnectionElderly(idConnectionElderly);
            String jsonAluno = new Gson().toJson(aluno);

            IdosoWebSocket webSocket = new IdosoWebSocket();
            webSocket.askElderly(jsonAluno);
        }
    }

    public void sendResponse(String json) {
        Map jsonObj = new Gson().fromJson(json, Map.class);
        String idConnection = (String) jsonObj.get("idConexaoSocketStudent");
        try {
            for (Session session : SESSION) {
                if (session.getId().equals(idConnection)) {
                    session.getBasicRemote().sendText(json);
                }
            }
        } catch (IOException e) {
        }
    }
}
