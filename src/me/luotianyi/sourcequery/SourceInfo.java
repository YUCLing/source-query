package me.luotianyi.sourcequery;

public class SourceInfo
{
    public byte Protocol;

    public String Name;

    public String Map;

    public String Folder;

    public String Game;

    public short GameID;

    public int Players;

    public int MaxPlayers;

    public int Bots;

    public String ServerType;

    public String Environment;

    public boolean Visibility;

    public boolean VAC;

    public String Version;

    public SourceInfo(byte protocol, String name, String map, String folder, String game, short gameid, int players, int maxplayers, int bots, String type, String env, boolean visible, boolean vac, String version)
    {
        Protocol = protocol;
        Name = name;
        Map = map;
        Folder = folder;
        Game = game;
        GameID = gameid;
        Players = players;
        MaxPlayers = maxplayers;
        Bots = bots;
        ServerType = type;
        Environment = env;
        Visibility = visible;
        VAC = vac;
        Version = version;
    }
}