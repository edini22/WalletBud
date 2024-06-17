package com.walletbud;

//Gasto por cada dia da semana passada - MAYBE


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.*;

import java.util.List;
import java.util.Map;

public class Teste {

    public static void main(String[] args) {

        try {
            PersistentSession session = AASICPersistentManager.instance().getSession();
            PersistentTransaction transaction = session.beginTransaction();

            User user = UserDAO.getUserByORMID(3);

            System.out.println(gastosPorMes(session, user, 2024).toString());

            transaction.commit();
        } catch (PersistentException e) {
            throw new RuntimeException(e);
        }

    }

    public static JsonObject gastosPorMes(PersistentSession session, User user, int ano) throws PersistentException {

        List<Map<String, Object>> gastos = TransacaoDAO.queryGastosByAnoById(session, user.getId_user(), ano);

        JsonObjectBuilder gastos_mes = Json.createObjectBuilder();
        int mes = 1;
        for(Map<String, Object> transacao : gastos) {
            for (int count = mes; count <= 12; count ++) {
                mes = count;
                if ((int) transacao.get("Month") == mes) {
                    gastos_mes.add(String.valueOf(mes), (int) transacao.get("TotalCost"));
                    break;
                } else {
                    gastos_mes.add(String.valueOf(mes), 0);
                }
            }
        }

        return gastos_mes.build();
    }

}
