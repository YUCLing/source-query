package me.luotianyi.sourcequery.test;

import me.luotianyi.sourcequery.Player;
import me.luotianyi.sourcequery.SourceClient;
import me.luotianyi.sourcequery.SourceInfo;

public class SourceQueryTest {

    static final String ip = "h.luotianyi.me";
    static final int port = 26015;

    public static void main(String[] args) {
        System.out.println("Connecting...");
        SourceClient client;
        try {
            client = new SourceClient(ip, port);
        } catch (Exception ex) {
            System.out.println("Connect failed");
            ex.printStackTrace();
            return;
        }
        System.out.println("Connect successfully, fetching server info information");
        SourceInfo info;
        try {
            info = client.GetServerInfo();
            System.out.println("Fetch successful.");
            System.out.println("====== Server Information ======");
            System.out.println("Name: " + info.Name);
            System.out.println("Game: " + info.Game);
            System.out.println("Map: " + info.Map);
            System.out.println("Players: " + info.Players + "/" + info.MaxPlayers);
            System.out.println("Game ID: " + info.GameID);
            System.out.println("Version: " + info.Version);
            System.out.println("================================");
        } catch(Exception ex) {
            ex.printStackTrace();
            System.out.println("Fetch failed, but will still try to fetch player list.");
        }
        System.out.println("Fetching player list");
        Player[] players;
        try {
            players = client.GetPlayers();
            System.out.println("====== Players ======");
            for (int i = 0;i<players.length;i++) {
                System.out.println("#" + players[i].Index + " [" + players[i].Name + "] Score: " + players[i].Score);
            }
            System.out.println("=====================");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Fetch failed.");
        }
    }

}
