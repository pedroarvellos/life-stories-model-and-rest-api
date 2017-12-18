/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.lifestories.api.websocket;

import br.com.lifestories.api.mock.Professor;
import br.com.lifestories.api.mock.ProfessorService;
import br.com.lifestories.model.entity.Idoso;
import br.com.lifestories.model.service.IdosoService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;
import javax.websocket.Session;

/**
 *
 * @author Joao Pedro
 */
@ServerEndpoint(value = "/ws-idoso")
public class IdosoWebSocket {

    private static final Set<Session> SESSION = Collections.synchronizedSet(new HashSet<Session>());

    @OnOpen
    public Session onOpen(Session session) {
        SESSION.add(session);
        Professor professor = new Professor();
        professor.setIdConexaoSocket(session.getId());
        ProfessorService professorService = new ProfessorService();
        professorService.add(professor);
        sendId(session.getId());
        return session;
    }

    private void sendId(String id) {
        Map<String,String> map = new HashMap<>();
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
    public String onClose(Session session) {
        ProfessorService professorService = new ProfessorService();
        Professor professor = professorService.readById(session.getId());
        professorService.remove(professor);
        SESSION.remove(session);
        professor.setFlag("remove");
        notifyStudent(professor);
        return "morri";
    }

    @OnMessage
    public void onMessage(String json) {
        Map jsonObj = new Gson().fromJson(json, Map.class);
        String flag = (String) jsonObj.get("flag");
        
        if(flag.equals("update")){
            
            String idConnection = (String) jsonObj.get("idConnection");
            String idRTC = (String) jsonObj.get("idRTC");
            String idString = (String) jsonObj.get("id");
            Long id = Long.parseLong(idString);
            ProfessorService professorService = new ProfessorService();
            Professor professor = professorService.readById(idConnection);
            professor.setIdRTC(idRTC);
            IdosoService idosoService = new IdosoService();
            Idoso idoso = null;
            try {
                idoso = idosoService.readById(id);
                professor.setIdoso(idoso);
                professorService.update(professor);
                professor.setFlag("new");
                notifyStudent(professor);
            } catch (Exception ex) {
                Logger.getLogger(IdosoWebSocket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(flag.equals("response")){
            EstudanteRTCSocket rtcSocket = new EstudanteRTCSocket();
            rtcSocket.sendResponse(json);
        }
    }

    private void notifyStudent(Professor professor) {
        EstudanteWebSocket estudentSocket = new EstudanteWebSocket();
        try {
            String json = new Gson().toJson(professor);
            estudentSocket.onMessage(json);
        } catch (Exception ex) {
            Logger.getLogger(IdosoWebSocket.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void askElderly(String json){
        Map jsonObj = new Gson().fromJson(json, Map.class);
        String idConnection = (String) jsonObj.get("idConnectionElderly");
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
