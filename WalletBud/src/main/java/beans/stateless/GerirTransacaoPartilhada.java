package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.json.*;

import org.orm.PersistentException;
import org.orm.PersistentSession;
import wb.walletbud.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import java.time.ZoneId;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;


import java.time.temporal.ChronoUnit;

@Stateless
public class GerirTransacaoPartilhada {

    @EJB
    private GerirUnica gerirUnica;

    @EJB
    private GerirFixa gerirFixa;

    @EJB
    private GerirUtilizador gerirUtilizador;

    public int shareTransaction(PersistentSession session, String transacao, String email, JsonArray usersArray, Unica unica, Fixa fixa) {

        try {
            int cond;
            if (transacao.equals("fixa")) {
                cond = gerirFixa.shareFixa(session, usersArray, email, fixa);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.shareUnica(session, usersArray, email, unica);
            } else {
                return -1;
            }
            return cond;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -7;
        }

    }

    public JsonObject getMovimentos(PersistentSession session, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserId(session, user.getId_user());

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : transacoes) {
                if(transacao.get("Discriminator").equals("Unica")){
                    Unica unica = UnicaDAO.getUnicaByORMID(session, (int) transacao.get("Id"));
                    String descricao = unica.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", descricao)
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);
                } else{
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID(session, (int) transacao.get("Id"));
                    Fixa fixa = tf.getTransacaofixa_ID();
                    String descricao = fixa.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("id_tf",tf.getID())
                            .add("name", fixa.getName())
                            .add("value", tf.getPayvalue())
                            .add("date", tf.getDataAtual().toString())
                            .add("description", descricao)
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            return Json.createObjectBuilder()
                    .add("movimentos", arrayBuilder)
                    .build();

        } catch (Exception e) {
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public JsonObject getMovimentosDays(PersistentSession session, String email, int days) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserIdandDays(session, user.getId_user(), days);

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : transacoes) {
                if(transacao.get("Discriminator").equals("Unica")){
                    Unica unica = UnicaDAO.getUnicaByORMID(session, (int) transacao.get("Id"));
                    String descricao = unica.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", descricao)
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);
                } else{
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID(session, (int) transacao.get("Id"));
                    Fixa fixa = tf.getTransacaofixa_ID();
                    String descricao = fixa.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("id_tf",tf.getID())
                            .add("name", fixa.getName())
                            .add("value", tf.getPayvalue())
                            .add("date", tf.getDataAtual().toString())
                            .add("description", descricao)
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            return Json.createObjectBuilder()
                    .add("movimentos", arrayBuilder)
                    .build();

        } catch (Exception e) {
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public JsonObject getTimeline(PersistentSession session, String email,int ano, int mes) throws PersistentException {//Em processo de construcao
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            String condition = "UserId_user = " + user.getId_user();

            Fixa[] fixas = FixaDAO.listFixaByQuery(session, condition, null);

            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(session, transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (f != null) {
                        transacoes_id.add(f);
                    }
                } catch (Exception e) {
                    //nao era fixa
                }
            }

            //movimentos do mes
            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserIdAndTime(session, user.getId_user(), ano, mes);
            String mes_str;
            if(mes < 10){
                mes_str = "0" + mes;
            } else {
                mes_str = ""+mes;
            }
            String dateStr = ano + "-" + mes_str + "-" + "01 00:00:00";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);

            List<Map<String, Object>> arrayFixas = new ArrayList<>();

            //criar transacoes fixas do mes e adicionar ao

            for (Fixa fixa: transacoes_id) {
                Timestamp time = Timestamp.valueOf(dateTime);

                if (time.before(fixa.getDate())) {
                    time = new Timestamp(fixa.getDate().getTime());
                }

                if(fixa.getRepeticao() == 1 || (fixa.getRepeticao() == 2 && time.equals(fixa.getDate())) || fixa.getRepeticao() == 3 || fixa.getRepeticao() == 4){
                    while (time.getMonth() <= fixa.getDate().getMonth()) {
                        if (fixa.getRepeticao() == 3) { //Mensalmente
                            time = new Timestamp(fixa.getDate().getTime());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(time);

                            calendar.set(Calendar.YEAR, ano);
                            calendar.set(Calendar.MONTH, mes - 1); // Subtrair 1 do mês porque é 0-based

                            // Obter o novo timestamp ajustado
                            time = new Timestamp(calendar.getTimeInMillis());

                            if (fixa.getDate().getYear() > time.getYear() || (fixa.getDate().getMonth() > time.getMonth() && fixa.getDate().getYear() >= time.getYear()) ) {
                                break;
                            }
                        } else if (fixa.getRepeticao() == 4) { //Anualmente
                            time = new Timestamp(fixa.getDate().getTime());

                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(time);

                            calendar.set(Calendar.YEAR, ano);
                            time = new Timestamp(calendar.getTimeInMillis());
                            if (fixa.getDate().getYear() > time.getYear() || (time.getMonth() != fixa.getDate().getMonth())) {
                                break;
                            }
                        }

                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(time);

                        // Ajustar a hora, minutos e segundos
                        Calendar fixaCalendar = Calendar.getInstance();
                        fixaCalendar.setTime(fixa.getDate());

                        calendar.set(Calendar.HOUR_OF_DAY, fixaCalendar.get(Calendar.HOUR_OF_DAY));
                        calendar.set(Calendar.MINUTE, fixaCalendar.get(Calendar.MINUTE));
                        calendar.set(Calendar.SECOND, fixaCalendar.get(Calendar.SECOND));

                        // Obter o novo timestamp ajustado
                        time = new Timestamp(calendar.getTimeInMillis());
                        String descricao = fixa.getDescrição();
                        if(descricao == null) {
                            descricao = "";
                        }
                        Map<String, Object> fixasMap = new HashMap<>();
                        fixasMap.put("id", fixa.getId_transacao());
                        fixasMap.put("name", fixa.getName());
                        fixasMap.put("date", time.toString());
                        fixasMap.put("value", fixa.getShareValue());
                        fixasMap.put("descricao", descricao);
                        fixasMap.put("categoria", fixa.getCategoriaId_categoria().getName());
                        fixasMap.put("tipo", fixa.getTipo());
                        fixasMap.put("local", fixa.getLocal());
                        fixasMap.put("repeticao", fixa.getRepeticao());
                        fixasMap.put("status", false);

                        arrayFixas.add(fixasMap);

                        // Ajustar a hora, minutos e segundos
                        LocalDateTime adjustedDateTime = time.toLocalDateTime();

                        // Adicionar a repetição apropriada
                        switch (fixa.getRepeticao()) {
                            case 1: // Diariamente
                                adjustedDateTime = adjustedDateTime.plusDays(1);
                                break;
                            case 2: // Semanalmente
                                adjustedDateTime = adjustedDateTime.plusWeeks(1);
                                break;
                            case 3: // Mensalmente
                                adjustedDateTime = adjustedDateTime.plusMonths(1);
                                break;
                            case 4: // Anualmente
                                adjustedDateTime = adjustedDateTime.plusYears(1);
                                break;
                        }

                        // Converter de volta para Timestamp
                        time = Timestamp.valueOf(adjustedDateTime);
                    }
                } else {
                    Timestamp timestamp = new Timestamp(fixa.getDate().getTime());

                    LocalDateTime localDateTime = timestamp.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                    DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
                    List<LocalDate> matchingDays = getDaysOfMonthWithSameDayOfWeek( ano, mes, dayOfWeek);

                    for (LocalDate date : matchingDays) {
                        LocalDateTime dt = date.atTime(localDateTime.toLocalTime());
                        Timestamp sqlTimestamp = Timestamp.valueOf(dt);
                        String descricao = fixa.getDescrição();
                        if(descricao == null) {
                            descricao = "";
                        }
                        Map<String, Object> fixasMap = new HashMap<>();
                        fixasMap.put("id", fixa.getId_transacao());
                        fixasMap.put("name", fixa.getName());
                        fixasMap.put("date", sqlTimestamp.toString());
                        fixasMap.put("value", fixa.getShareValue());
                        fixasMap.put("descricao", descricao);
                        fixasMap.put("categoria", fixa.getCategoriaId_categoria().getName());
                        fixasMap.put("tipo", fixa.getTipo());
                        fixasMap.put("local", fixa.getLocal());
                        fixasMap.put("repeticao", fixa.getRepeticao());
                        fixasMap.put("status", false);

                        arrayFixas.add(fixasMap);
                    }
                }
            }

            for(Map<String, Object> transacao : transacoes) {
                if (transacao.get("Discriminator").equals("Unica")) {
                    Unica unica = UnicaDAO.getUnicaByORMID(session, (int) transacao.get("Id"));
                    String descricao = unica.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    Map<String, Object> fixasMap = new HashMap<>();
                    fixasMap.put("id", unica.getId_transacao());
                    fixasMap.put("name", unica.getName());
                    fixasMap.put("date", unica.getDate().toString());
                    fixasMap.put("value", unica.getShareValue());
                    fixasMap.put("descricao", descricao);
                    fixasMap.put("categoria", unica.getCategoriaId_categoria().getName());
                    fixasMap.put("tipo", unica.getTipo());
                    fixasMap.put("local", unica.getLocal());
                    fixasMap.put("status", true);

                    arrayFixas.add(fixasMap);

                } else  {
//                    Fixa f = FixaDAO.getFixaByORMID((int) transacao.get("Id"));
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID(session, (int) transacao.get("Id"));
                    Fixa f = tf.getTransacaofixa_ID();
                    if(f.getOwner_id() == null){
                        String descricao = f.getDescrição();
                        if(descricao == null) {
                            descricao = "";
                        }
                        Map<String, Object> fixasMap = new HashMap<>();
                        fixasMap.put("id", f.getId_transacao());
                        fixasMap.put("name", f.getName());
                        fixasMap.put("date", tf.getDataPagamento().toString());
                        fixasMap.put("value", f.getShareValue());
                        fixasMap.put("descricao", descricao);
                        fixasMap.put("categoria", f.getCategoriaId_categoria().getName());
                        fixasMap.put("tipo", f.getTipo());
                        fixasMap.put("local", f.getLocal());
                        fixasMap.put("repeticao", f.getRepeticao());
                        fixasMap.put("status", true);

                        arrayFixas.add(fixasMap);
                    } else{
                        for (Map<String, Object> pagamento: arrayFixas) {
                            if (pagamento.get("date").toString().equals(transacao.get("date").toString())) {
                                pagamento.replace("status", true);
                                break;
                            }
                        }
                    }

                }
            }

            arrayFixas.sort(new Comparator<Map<String, Object>>() {
                private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S");

                @Override
                public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                    try {
                        Timestamp date1 = new Timestamp(dateFormat.parse(o1.get("date").toString()).getTime());
                        Timestamp date2 = new Timestamp(dateFormat.parse(o2.get("date").toString()).getTime());
                        return date1.compareTo(date2);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                }
            });

            JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();

            for (Map<String, Object> map : arrayFixas) {
                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        jsonObjectBuilder.add(entry.getKey(), (String) value);
                    } else if (value instanceof Integer) {
                        jsonObjectBuilder.add(entry.getKey(), (Integer) value);
                    } else if (value instanceof Boolean) {
                        jsonObjectBuilder.add(entry.getKey(), (Boolean) value);
                    } else if (value instanceof Double) {
                        jsonObjectBuilder.add(entry.getKey(), (Double) value);
                    } else {
                        jsonObjectBuilder.add(entry.getKey(), value.toString());
                    }
                }
                JsonObject jsonObject = jsonObjectBuilder.build();
                jsonArrayBuilder.add(jsonObject);
            }

            JsonArray jsonArray = jsonArrayBuilder.build();

            return Json.createObjectBuilder()
                    .add("timeline", jsonArray)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public List<LocalDate> getDaysOfMonthWithSameDayOfWeek( int year, int month, DayOfWeek dayOfWeek) {
        List<LocalDate> matchingDays = new ArrayList<>();
        YearMonth yearMonth = YearMonth.of(year, month);

        // Itera por todos os dias do mês especificado
        for (int day = 1; day <= yearMonth.lengthOfMonth(); day++) {
            LocalDate date = LocalDate.of(year, month, day);
            if (date.getDayOfWeek() == dayOfWeek) {
                matchingDays.add(date);
            }
        }

        return matchingDays;
    }


    public JsonObject getPendentes(PersistentSession session, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }


            List<Map<String, Object>> pendentes = TransacaoDAO.queryPendentesByUserId(session, user.getId_user());
//            System.out.println("pendentes -> " + pendentes.toString());
            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : pendentes) {

                if (transacao.get("Discriminator").equals("Unica")) {
                    System.out.println((int) transacao.get("Id"));
                    Unica unica = UnicaDAO.getUnicaByORMID(session, (int) transacao.get("Id"));
                    String condition = "TransacaoId_transacao = " + unica.getId_transacao();
                    TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                    User Owner = unica.getOwner_id();
                    JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
                    JsonObject userJs = Json.createObjectBuilder()
                            .add("id", Owner.getId_user())
                            .add("name", Owner.getName())
                            .add("email", Owner.getEmail())
                            .add("confirma", 1)
                            .build();
                    userArrayBuilder.add(userJs);

                    for (TransacaoPartilhada tpPartilhada : tp) {
                        User u = tpPartilhada.getUserId_user();
                        JsonObject userJson = Json.createObjectBuilder()
                                .add("id", u.getId_user())
                                .add("name", u.getName())
                                .add("email", u.getEmail())
                                .add("confirma", tpPartilhada.getConfirma())
                                .build();
                        userArrayBuilder.add(userJson);
                    }
                    JsonArray userArray = userArrayBuilder.build();
                    String descricao = unica.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getValue())
                            .add("shareValue", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", descricao)
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("status", unica.getStatus())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .add("transacao","unica")
                            .add("users", userArray)
                            .build();
                    arrayBuilder.add(unicaJson);
                } else {
                    Fixa fixa = FixaDAO.getFixaByORMID(session, (int) transacao.get("Id"));
                    User Owner = fixa.getOwner_id();
                    JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
                    JsonObject userJs = Json.createObjectBuilder()
                            .add("id", Owner.getId_user())
                            .add("name", Owner.getName())
                            .add("email", Owner.getEmail())
                            .add("confirma", 1)
                            .build();
                    userArrayBuilder.add(userJs);

                    String condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                    TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                    for (TransacaoPartilhada tpPartilhada : tp) {
                        User u = tpPartilhada.getUserId_user();
                        JsonObject userJson = Json.createObjectBuilder()
                                .add("id", u.getId_user())
                                .add("name", u.getName())
                                .add("email", u.getEmail())
                                .add("confirma", tpPartilhada.getConfirma())
                                .build();
                        userArrayBuilder.add(userJson);
                    }
                    JsonArray userArray = userArrayBuilder.build();
                    String descricao = fixa.getDescrição();
                    if(descricao == null) {
                        descricao = "";
                    }
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", fixa.getValue())
                            .add("shareValue", fixa.getShareValue())
                            .add("date", fixa.getDate().toString())
                            .add("descricao", descricao)
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("status", fixa.getStatus())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .add("transacao","fixa")
                            .add("users", userArray)
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            return Json.createObjectBuilder()
                    .add("pendentes", arrayBuilder)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }
    }

    public JsonObject getGastosPorMes(PersistentSession session, String email, int ano) throws PersistentException {

        User user = gerirUtilizador.getUserByEmail(session, email);

        List<Map<String, Object>> gastos = TransacaoDAO.queryGastosByAnoById(session, user.getId_user(), ano);

        JsonObjectBuilder gastos_mes = Json.createObjectBuilder();

        for (int mes = 1; mes <= 12; mes++) {
            gastos_mes.add(String.valueOf(mes), 0);
        }

        for (Map<String, Object> transacao : gastos) {
            int mes = (int) transacao.get("Month");
            Number totalCost = (Number) transacao.get("TotalCost");
            gastos_mes.add(String.valueOf(mes), totalCost.doubleValue());
        }

        return gastos_mes.build();
    }

    public JsonObject getGastosPorAno(PersistentSession session, String email, int ano) throws PersistentException {

        User user = gerirUtilizador.getUserByEmail(session, email);

        List<Map<String, Object>> gastos = TransacaoDAO.queryGastosTotalAnoById(session, user.getId_user(), ano);

        JsonObjectBuilder gastos_mes = Json.createObjectBuilder();

        for (Map<String, Object> transacao : gastos) {
            Number totalCost = (Number) transacao.get("TotalCost");
            gastos_mes.add(String.valueOf(ano), totalCost.doubleValue());
        }

        return gastos_mes.build();
    }
}
