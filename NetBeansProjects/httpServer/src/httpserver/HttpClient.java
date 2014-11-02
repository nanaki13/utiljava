/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package httpserver;

import com.jonathan.IOstream.StreamTool;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jonathan
 */
public class HttpClient {

    public static void main(String[] args) throws IOException {


        HttpClient client = new HttpClient();
        HttpRequest httpRequest = new HttpRequest();
        httpRequest.setVersion("HTTP/1.1");
        httpRequest.setMethod("GET");
        httpRequest.setHost("www.allocine.fr");
        httpRequest.setRessource("/");
        HttpResponse doMethode = client.send(httpRequest);
        System.out.println(new String(doMethode.getResponse(),doMethode.getEncoding()));
    }

    public static int decodeHexa(String toDecode) {
        int somme = 0, del;
        for (int i = 0, j = toDecode.length(); i < toDecode.length(); i++, j--) {
            char c = toDecode.charAt(i);
            if (c >= '0' && c <= '9') {
                del = c - '0';
            } else if (c >= 'a' && c <= 'f') {
                del = c - 'a' + 10;
            } else if (c >= 'A' && c <= 'F') {
                del = c - 'A' + 10;
            } else {
                return -1;
            }
            somme += del << ((j - 1) * 4);
        }
        return somme;
    }

    private Socket s;
    private HttpResponse response;
    private ByteArrayOutputStream baos;
    private byte[] end;

    public HttpResponse send(HttpRequest request) throws IOException {
//        HttpRequest request= new HttpRequest();

        s = new Socket(request.getHost(), 80);
        String reqestString = request.getMethod() + " " + request.getRessource() + " " + request.getVersion() + "\nHost: " + request.getHost() + "\n\n";
        s.getOutputStream().write(reqestString.getBytes("utf-8"));
        s.getOutputStream().flush();
        response = new HttpResponse();
        readHeader();
        readContent();
        return response;

    }


    public static void read(InputStreamReader in, StringBuilder out, int size) throws IOException {
        int c;
        int count = 0;
        while ((c = in.read()) != -1 && count != size) {
            out.append((char) c);
            count++;
        }
    }

    public static void printMap(Map<?, ?> map) {
        map.entrySet().stream().forEach((enrty) -> {
            System.out.println(enrty.getKey() + ":" + enrty.getValue());
        });
    }

    private void readHeader() throws IOException {
        InputStream inputStream = s.getInputStream();
        baos = new ByteArrayOutputStream();
        StringBuilder out = new StringBuilder();
        byte[] space = new byte[]{' '};
        byte[] point = new byte[]{':'};
        end = new byte[]{'\r', '\n'};
        StreamTool.readUntil(baos, inputStream, space);
        System.out.print(new String(baos.toByteArray()) + " ");

        response.setVersion(new String(baos.toByteArray()));
        baos.reset();
        StreamTool.readUntil(baos, inputStream, space);
        System.out.print(new String(baos.toByteArray()) + " ");
        response.setStatusCode(new String(baos.toByteArray()));
        baos.reset();
        StreamTool.readUntil(baos, inputStream, end);
        System.out.print(new String(baos.toByteArray()) + "\r\n");
        response.setStatut(new String(baos.toByteArray()));
        boolean continu = true;

        while (continu) {
            baos.reset();
            String readLine;
            StreamTool.readUntil(baos, inputStream, end);
            System.out.print(new String(baos.toByteArray()) + "\r\n");
            readLine = new String(baos.toByteArray());
            if (readLine.isEmpty()) {
                continu = false;
            } else {
                response.addParams(readLine);
            }
        }

//       
    }

    private void readContent() throws IOException {
        String contentType = response.getParam("Content-Type");
        if (contentType != null) {
//            out.setLength(0);
            int indexOf = contentType.indexOf(';');
            if (indexOf != -1) {
                String mimeType = contentType.substring(0, indexOf);
                String encoding = null;
                indexOf++;

            }
        }
        String transferEncoding = response.getParam("Transfer-Encoding");
        baos.reset();
        ByteArrayOutputStream baosTotal = new ByteArrayOutputStream();
        if (transferEncoding != null) {
            boolean continu = true;

            byte[] buff = new byte[512];
            while (continu) {
                InputStream inputStream = s.getInputStream();
                StreamTool.readUntil(baos, inputStream, end);
                String sizeStr = new String(baos.toByteArray());
                baos.reset();
                int size = decodeHexa(sizeStr);
                int count = size;
                while (count != 0) {
                    int readen = inputStream.read(buff, 0, Math.min(512, count));
                    baosTotal.write(buff, 0, readen);
                    count -= readen;
                }

                if (size == 0) {
                    continu = false;
                } else {
                    StreamTool.readUntil(baos, inputStream, end);
                }

            }
            response.setResponse(baosTotal.toByteArray());

        }
        s.close();
    }
}
