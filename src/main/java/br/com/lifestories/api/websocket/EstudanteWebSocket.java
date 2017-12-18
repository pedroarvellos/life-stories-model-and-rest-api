/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.api.websocket;

import br.com.lifestories.api.mock.ProfessorService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 *
 * @author Joao Pedro
 */
@ServerEndpoint(value = "/ws-estudante")
public class EstudanteWebSocket {

    private static final Set<Session> SESSION = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public Session onOpen(Session session) {
        SESSION.add(session);
        getIdososOnline(session.getId());
        return session;
    }

    private void getIdososOnline(String id) {
        ProfessorService professorService = new ProfessorService();
        List professorList = professorService.readAll();
        String json = new Gson().toJson(professorList);
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
        try {
            for (Session session : SESSION) {
                session.getBasicRemote().sendText(json);
            }
        } catch (IOException e) {
        }
    }

}
