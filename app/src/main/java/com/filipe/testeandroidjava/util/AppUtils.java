package com.filipe.testeandroidjava.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {


    private static char[] HEXCHARS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String hexEncode(byte[] bytes) {
        char[] result = new char[bytes.length * 2];
        int b;
        for (int i = 0, j = 0; i < bytes.length; i++) {
            b = bytes[i] & 0xff;
            result[j++] = HEXCHARS[b >> 4];
            result[j++] = HEXCHARS[b & 0xf];
        }
        return new String(result);
    }


    /**
     * Cria um hash MD5 par enviarmos a API da marvel
     * */
    public static String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());

            return hexEncode(digest.digest());

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    public boolean validaCPF(String CPF){
        CPF = removeCaracteresEspeciais(CPF);
        if (CPF.equals("00000000000") || CPF.equals("11111111111")
                || CPF.equals("22222222222") || CPF.equals("33333333333")
                || CPF.equals("44444444444") || CPF.equals("55555555555")
                || CPF.equals("66666666666") || CPF.equals("77777777777")
                || CPF.equals("88888888888") || CPF.equals("99999999999")){
            return false;
        }

        char dig10 = 0, dig11 = 0;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++){
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)){
                dig10 = '0';
            }else {
                dig11 = (char)(r+48);
            }

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))){
                return true;
            }else {
                return false;
            }
        }catch (InputMismatchException erro){
            return (false);
        }

    }

    private String removeCaracteresEspeciais(String cpf) {

        if (cpf.contains(".")){
            cpf = cpf.replace(".", "");
        }
        if (cpf.contains("-")){
            cpf = cpf.replace("-", "");
        }
        if (cpf.contains("/")){
            cpf = cpf.replace("/", "");
        }
        return cpf;

    }

    public boolean validarSenha(String senha){

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[@#$%^&+=])";
        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(senha);
        return matcher.matches();

    }

}
