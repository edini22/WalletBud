package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.json.*;

import org.orm.PersistentException;
import org.orm.PersistentTransaction;
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

    public int shareTransaction(String transacao, String email, JsonArray usersArray, Unica unica, Fixa fixa) {

        try {

            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                System.out.println("User Email: " + userEmail);
            }
            int cond;
            if (transacao.equals("fixa")) {
                cond = gerirFixa.shareFixa(usersArray, email, fixa);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.shareUnica(usersArray, email, unica);
            } else {
                return -1;
            }
            return cond;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -7;
        }

    }

    public JsonObject getMovimentos(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserId(user.getId_user());

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : transacoes) {
                if(transacao.get("Discriminator").equals("Unica")){
                    Unica unica = UnicaDAO.getUnicaByORMID((int) transacao.get("Id"));
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", unica.getDescrição())
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);
                } else{
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID((int) transacao.get("Id"));
                    Fixa fixa = tf.getTransacaofixa_ID();
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", tf.getPayvalue())
                            .add("date", tf.getDataAtual().toString())
                            .add("datePagamento",tf.getDataPagamento().toString())
                            .add("descricao", fixa.getDescrição())
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            t.commit();
            return Json.createObjectBuilder()
                    .add("movimentos", arrayBuilder)
                    .build();

        } catch (Exception e) {
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public JsonObject getMovimentosDays(String email, int days) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserIdandDays(user.getId_user(), days);

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : transacoes) {
                if(transacao.get("Discriminator").equals("Unica")){
                    Unica unica = UnicaDAO.getUnicaByORMID((int) transacao.get("Id"));
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", unica.getDescrição())
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);
                } else{
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID((int) transacao.get("Id"));
                    Fixa fixa = tf.getTransacaofixa_ID();
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", tf.getPayvalue())
                            .add("date", tf.getDataAtual().toString())
                            .add("datePagamento",tf.getDataPagamento().toString())
                            .add("descricao", fixa.getDescrição())
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            t.commit();
            return Json.createObjectBuilder()
                    .add("movimentos", arrayBuilder)
                    .build();

        } catch (Exception e) {
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public JsonObject getTimeline(String email,int ano, int mes) throws PersistentException {//Em processo de construcao
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            // transaçoes unicas (confirmadas) + fixas (pago ou nao pago)
            //TODO: ainda nao fiz nada, mas o objetivo e apresentar as fixas e unicas ao qual o user pagou/tem a pagar e as unicas que pagou (status = true)

            String condition = "UserId_user = " + user.getId_user();

            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null);

            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (f != null) {
                        transacoes_id.add(f);
                    }
                } catch (Exception e) {
                    //nao era fixa
                }
            }

            //movimentos do mes
            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserIdAndTime(user.getId_user(), ano, mes);
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

                if(fixa.getRepeticao() == 1 || (fixa.getRepeticao() == 2 && time.before(fixa.getDate())) || fixa.getRepeticao() == 3 || fixa.getRepeticao() == 4){
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

                        Map<String, Object> fixasMap = new HashMap<>();
                        fixasMap.put("id", fixa.getId_transacao());
                        fixasMap.put("name", fixa.getName());
                        fixasMap.put("date", time.toString());
                        fixasMap.put("value", fixa.getShareValue());
                        fixasMap.put("descricao", fixa.getDescrição());
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
                    List<LocalDate> matchingDays = getDaysOfMonthWithSameDayOfWeek(ano, mes, dayOfWeek);

                    for (LocalDate date : matchingDays) {
                        LocalDateTime dt = date.atTime(localDateTime.toLocalTime());
                        Timestamp sqlTimestamp = Timestamp.valueOf(dt);

                        Map<String, Object> fixasMap = new HashMap<>();
                        fixasMap.put("id", fixa.getId_transacao());
                        fixasMap.put("name", fixa.getName());
                        fixasMap.put("date", sqlTimestamp.toString());
                        fixasMap.put("value", fixa.getShareValue());
                        fixasMap.put("descricao", fixa.getDescrição());
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
                    Unica unica = UnicaDAO.getUnicaByORMID((int) transacao.get("Id"));
                    Map<String, Object> fixasMap = new HashMap<>();
                    fixasMap.put("id", unica.getId_transacao());
                    fixasMap.put("name", unica.getName());
                    fixasMap.put("date", unica.getDate().toString());
                    fixasMap.put("value", unica.getShareValue());
                    fixasMap.put("descricao", unica.getDescrição());
                    fixasMap.put("categoria", unica.getCategoriaId_categoria().getName());
                    fixasMap.put("tipo", unica.getTipo());
                    fixasMap.put("local", unica.getLocal());
                    fixasMap.put("status", true);

                    arrayFixas.add(fixasMap);

                } else  {
                    for (Map<String, Object> pagamento: arrayFixas) {
                        if (pagamento.get("date").toString().equals(transacao.get("date").toString())) {
                            pagamento.replace("status", true);
                            break;
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

            t.commit();
            return Json.createObjectBuilder()
                    .add("timeline", jsonArray)
                    .build();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public List<LocalDate> getDaysOfMonthWithSameDayOfWeek(int year, int month, DayOfWeek dayOfWeek) {
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

}
