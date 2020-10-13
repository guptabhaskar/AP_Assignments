package com.company;

import java.util.*;

class User<T> {
    private final T user;

    public User(T u) {
        this.user = u;
    }

    public T getUser() {
        return user;
    }
}

abstract class Player {
    protected int hp;
    protected final int id;
    protected boolean alive;
    protected String playerName;
    protected String name;

    public Player(int hp, int id) {
        this.hp = hp;
        this.id = id;
        this.alive = true;
        this.playerName = "Player"+id;
        this.name = this.playerName; // [User]
    }

    abstract public int getHp();
    abstract public void setHp(int hp);
    abstract public int getId();
    abstract public boolean isAlive();
    abstract public void setAlive(boolean alive);
    abstract public String getName();
    abstract public void setName(String name);
    abstract public String getPlayerName();
    abstract public void setPlayerName(String playerName);

    @Override
    public boolean equals(Object o) {
        if(o != null && getClass() == o.getClass()) {
            Player p = (Player)o;
            return (p.getId() == getId());
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return id+" "+hp+" "+alive+" "+getClass().getSimpleName();
    }
}

class HPComparator implements Comparator<Player> {
    @Override
    public int compare(Player p1, Player p2) {
        return p1.getHp() - p2.getHp();
    }
}

class Mafia extends Player {
    public Mafia(int id) {
        super(2500, id);
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

class Detective extends Player {
    public Detective(int id) {
        super(800, id);
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

class Healer extends Player {
    public Healer(int id) {
        super(800, id);
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

class Commoner extends Player {
    public Commoner(int id) {
        super(1000, id);
    }

    @Override
    public int getHp() {
        return this.hp;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    @Override
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPlayerName() {
        return playerName;
    }

    @Override
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

class MafiaGame {
    final private int n, m, d, h, c;
    private ArrayList<Player> ForEnd;
    private ArrayList<Player> players;
    private User u;
    private boolean winner;
    private int mafiaCount, nonMafiaCount,detectiveCount, healerCount;
    private boolean alive;

    public MafiaGame(int n) {
        this.n = n;
        this.m = n/5;
        this.d = n/5;
        this.h = Math.max(1, n/10);
        this.c = this.n-this.m-this.d-this.h;
        this.players = new ArrayList<>();
        this.ForEnd = new ArrayList<>();
        this.mafiaCount = m;
        this.nonMafiaCount = n-m;
        this.detectiveCount = d;
        this.healerCount = h;
        this.winner = true;
        this.alive = true;
    }

    public void start() {
        makeList();
        initiate();
        int roundNo = 1;
        while(true) {
            System.out.println("Round " + roundNo + ":");
            conductRound();
            System.out.println("--End of Round " + roundNo + "--");
            if(nonMafiaCount <= mafiaCount) {
                winner = false;
                break;
            } else if(mafiaCount == 0) {
                winner = true;
                break;
            } else {
                roundNo++;
            }
        }
        theEnd();
    }

    private void conductRound() {
        playersRemaining();
        mafiaChoose();
        if(!(u.getUser() instanceof Mafia)) {
            System.out.println("Mafias have chosen their target.");
        } else if(u.getUser() instanceof Mafia) {
            if(!alive) {
                System.out.println("Mafias have chosen their target.");
            }
        }
        Player result = detectiveTest(); // Done returns null or player to kick
        if(!(u.getUser() instanceof Detective)) {
            System.out.println("Detectives have chosen a player to test.");
        } else if(u.getUser() instanceof Detective) {
            if(!alive) {
                System.out.println("Detectives have chosen a player to test.");
            }
        }
        healerHeal(); // Done
        if(!(u.getUser() instanceof Healer)) {
            System.out.println("Healers have chosen someone to heal.");
        } else if(u.getUser() instanceof Healer) {
            if(!alive) {
                System.out.println("Healers have chosen someone to heal.");
            }
        }
        System.out.println("--End of actions--");
        voteOut(result);
    }

    private void voteOut(Player tested) {
        concludeDead();
        boolean tie = true;
        while(tie) {
            if (mafiaCount != nonMafiaCount) {
                if (tested == null) {
                    if (alive) {
                        System.out.print("Select a person to vote out: ");
                        int kl;
                        Scanner scan = new Scanner(System.in);
                        boolean q = true;
                        while (q) {
                            if (scan.hasNextInt()) {
                                kl = scan.nextInt();
                                if (kl > 0 && kl <= n) {
                                    for (Player p : players) {
                                        if (p.getId() == kl && !p.isAlive()) {
                                            System.out.println("Player" + kl + " is already dead. Enter again: ");
                                        } else if (p.getId() == kl && p.isAlive()) {
                                            q = false;
                                            break;
                                        }
                                    }
                                } else {
                                    System.out.println("Invalid Input. Enter again: ");
                                }
                            } else {
                                System.out.println("Invalid Input. Enter again: ");
                            }
                        }
                    }
                    Random rand = new Random();
                    while (true) {
                        int k = rand.nextInt(n);
                        if (players.get(k).isAlive()) {
                            System.out.println(players.get(k).getPlayerName() + " has been voted out.");
                            players.get(k).setAlive(false);
                            if (players.get(k).equals(u.getUser())) {
                                alive = false;
                            }
                            if (players.get(k) instanceof Mafia) {
                                mafiaCount--;
                            } else if (players.get(k) instanceof Healer) {
                                healerCount--;
                                nonMafiaCount--;
                            } else if (players.get(k) instanceof Detective) {
                                detectiveCount--;
                                nonMafiaCount--;
                            } else {
                                nonMafiaCount--;
                            }
                            break;
                        }
                    }
                } else {
                    System.out.println(tested.getPlayerName() + " has been voted out.");
                    for (Player p : players) {
                        if (!p.equals(tested)) {
                            continue;
                        }
                        p.setAlive(false);
                        if (p.equals(u.getUser())) {
                            alive = false;
                        }
                        if (p instanceof Mafia) {
                            mafiaCount--;
                        } else if (p instanceof Healer) {
                            healerCount--;
                            nonMafiaCount--;
                        } else if (p instanceof Detective) {
                            detectiveCount--;
                            nonMafiaCount--;
                        } else {
                            nonMafiaCount--;
                        }
                        break;
                    }
                }
            }
            tie = false;
        }
    }

    private void concludeDead() {
        boolean xyz = true;
        for(Player p: players) {
            if(p.isAlive() && !(p instanceof Mafia) && p.getHp()==0) {
                xyz = false;
                System.out.println(p.getPlayerName()+" has died.");
                p.setAlive(false);
                nonMafiaCount--;
                if(p.equals(u.getUser())) {
                    alive =false;
                }
            }
        }
        if(xyz) {
            System.out.println("No one died.");
        }
    }

    private void healerHeal() {
        if(healerCount>0 && !(u.getUser() instanceof Healer)) {
            Random rand = new Random();
            while(true) {
                int r = rand.nextInt(n);
                if(players.get(r).isAlive()) {
                    players.get(r).setHp(players.get(r).getHp()+500);
                    break;
                }
            }
        } else if(healerCount>0 && u.getUser() instanceof Healer && alive) {
            System.out.print("Choose a player to heal: ");
            Scanner scan = new Scanner(System.in);
            while(true) {
                int inp;
                if(scan.hasNextInt()) {
                    inp = scan.nextInt();
                    if(inp>n || inp<1){
                        System.out.println("Enter again.");
                    } else {
                        boolean xyz = true;
                        for(Player p: players) {
                            if(p.getId()==inp && p.isAlive()) {
                                p.setHp(p.getHp()+500);
                                xyz = false;
                                break;
                            }
                        }
                        if(xyz) {
                            System.out.println("Player"+inp+" is dead. Enter again.");
                        } else {
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid Input. Enter again.");
                }
            }
        } else if(healerCount>0 && u.getUser() instanceof Healer && !alive) {
            Random rand = new Random();
            while(true) {
                int r = rand.nextInt(n);
                if(players.get(r).isAlive()) {
                    players.get(r).setHp(players.get(r).getHp()+500);
                    break;
                }
            }
        }
    }

    private Player detectiveTest() {
        if(detectiveCount>0 && !(u.getUser() instanceof Detective)) {
            Random rand = new Random();
            while(true) {
                int r = rand.nextInt(n);
                if(players.get(r).isAlive() && !(players.get(r) instanceof Detective)) {
                    if(players.get(r) instanceof Mafia) {
                        return players.get(r);
                    } else {
                        return null;
                    }
                }
            }
        } else if(detectiveCount>0 && u.getUser() instanceof Detective && alive) {
            System.out.print("Choose a player to test: ");
            Scanner scan = new Scanner(System.in);
            while(true) {
                int inp;
                if(scan.hasNextInt()) {
                    inp = scan.nextInt();
                    if(inp>n || inp<1){
                        System.out.println("Enter again.");
                    } else {
                        int xyz = 1;
                        for(Player p: players) {
                            if(p.getId() == inp && !p.isAlive()) {
                                xyz = -1;
                                break;
                            } else if(p.getId() == inp && p.isAlive() && p instanceof Detective) {
                                break;
                            } else if(p.getId() == inp && p.isAlive()) {
                                if(p instanceof Mafia) {
                                    System.out.println("Player"+inp+" is a Mafia.");
                                    return p;
                                } else {
                                    System.out.println("Player"+inp+" is not a Mafia.");
                                    return null;
                                }
                            }
                        }
                        if(xyz == -1) {
                            System.out.println("Player"+inp+" is dead. Enter again.");
                        } else {
                            System.out.println("Player"+inp+" is a detective. Try again.");
                        }
                    }
                } else {
                    System.out.println("Invalid Input. Enter again.");
                }
            }
        } else if(detectiveCount>0 && u.getUser() instanceof Detective && !alive) {
            Random rand = new Random();
            while(true) {
                int r = rand.nextInt(n);
                if(players.get(r).isAlive() && !(players.get(r) instanceof Detective)) {
                    if(players.get(r) instanceof Mafia) {
                        return players.get(r);
                    } else {
                        return null;
                    }
                }
            }
        } else {
            return null;
        }
    }

    private void mafiaChoose() {
        if(mafiaCount>0 && !(u.getUser() instanceof Mafia)) {
            Random rand = new Random();
            while(true) {
                int r = rand.nextInt(n);
                if(players.get(r).isAlive() && !(players.get(r) instanceof Mafia)) {
                    kill(r);
                    break;
                }
            }
        } else if(mafiaCount>0 && u.getUser() instanceof Mafia && alive) {
            System.out.print("Choose a player to kill: ");
            Scanner scan = new Scanner(System.in);
            while(true) {
                int inp;
                if(scan.hasNextInt()) {
                    inp = scan.nextInt();
                    if(inp>n || inp<1){
                        System.out.println("Enter again.");
                    } else {
                        int xyz = 0;
                        for(Player p: players) {
                            if(p.getId()==inp && p.isAlive() && !(p instanceof Mafia)) {
                                for(int i = 0; i < n; i++){
                                    if(players.get(i).getId()==inp){
                                        inp = i;
                                        break;
                                    }
                                }
                                kill(inp);
                                xyz = -1;
                                break;
                            } else if(p.getId()==inp && p.isAlive() && (p instanceof Mafia)) {
                                xyz = 1;
                                break;
                            }
                        }
                        if(xyz == 0) {
                            System.out.println("Player"+inp+" is dead. Enter again.");
                        } else if(xyz == 1) {
                            System.out.println("Player"+inp+" is a mafia. Try again.");
                        }else {
                            break;
                        }
                    }
                } else if(mafiaCount>0 && u.getUser() instanceof Mafia && !alive) {
                    Random rand = new Random();
                    while(true) {
                        int r = rand.nextInt(n);
                        if(players.get(r).isAlive() && !(players.get(r) instanceof Mafia)) {
                            kill(r);
                            break;
                        }
                    }
                } else {
                    System.out.println("Invalid Input. Enter again.");
                }
            }
        }
    }

    private void kill(int r) {
        int killId = players.get(r).getId();
        Collections.sort(players, new HPComparator());
        int newIdx = 0;
        for(int i = 0; i< n; i++) {
            if(players.get(i).getId()==killId) {
                newIdx = i;
                break;
            }
        }
        int totalMafiaHp = 0;
        for(Player p: players) {
            if(p.isAlive() && p instanceof Mafia) {
                totalMafiaHp += p.getHp();
            }
        }
        if(totalMafiaHp<=players.get(newIdx).getHp()) {
            players.get(newIdx).setHp(players.get(newIdx).getHp()-totalMafiaHp);
            for(Player p: players) {
                if(p.isAlive() && p instanceof Mafia) {
                    p.setHp(0);
                }
            }
        } else {
            int avg = players.get(newIdx).getHp()/mafiaCount;
            int count = mafiaCount;
            players.get(newIdx).setHp(0);
            for(Player p: players) {
                if(p.isAlive() && p instanceof Mafia) {
                    if(p.getHp()>=avg) {
                        p.setHp(p.getHp()-avg);
                    } else {
                        avg = ((avg*count)-p.getHp());
                        count--;
                        avg=avg/count;
                        p.setHp(0);
                    }
                }
            }
        }
    }

    private void playersRemaining() {
        int c = 0;
        String s = "";
        for(Player p: players) {
            if(p.isAlive()) {
                c++;
                s += p.getPlayerName() + ", ";
            }
        }
        System.out.println(c + " players are remaining: " + s);
    }

    private void makeList() {
        int[] arr = new int[n];
        Random rand = new Random();
        int id;
        for(int i = 0; i < m; i++) {
            while(true) {
                id = rand.nextInt(n);
                if(arr[id] == 0) {
                    arr[id] = 1;
                    players.add(new Mafia(id+1));
                    break;
                }
            }
        }
        for(int i = 0; i < d; i++) {
            while(true) {
                id = rand.nextInt(n);
                if(arr[id] == 0) {
                    arr[id] = 1;
                    players.add(new Detective(id+1));
                    break;
                }
            }
        }
        for(int i = 0; i < h; i++) {
            while(true) {
                id = rand.nextInt(n);
                if(arr[id] == 0) {
                    arr[id] = 1;
                    players.add(new Healer(id+1));
                    break;
                }
            }
        }
        for(int i = 0; i < c; i++) {
            while(true) {
                id = rand.nextInt(n);
                if(arr[id] == 0) {
                    arr[id] = 1;
                    players.add(new Commoner(id+1));
                    break;
                }
            }
        }
    }

    private void initiate() {
        System.out.println("Choose a Character\n" +
                           "1) Mafia\n" +
                           "2) Detective\n" +
                           "3) Healer\n" +
                           "4) Commoner\n" +
                           "5) Assign Randomly");
        Scanner scan = new Scanner(System.in);
        if(scan.hasNextInt()) {
            int n = scan.nextInt();
            Random rand = new Random();
            if(n == 1) {
                while(true) {
                    Player p = players.get(rand.nextInt(players.size()));
                    if(p instanceof Mafia) {
                        p.setName(p.getName()+"[User]");
                        u = new User(p);
                        System.out.println("You are " + p.getPlayerName() + ".");
                        System.out.print("You are a Mafia. Other Mafias are [");
                        for(Player p1: players) {
                            if(p1 instanceof Mafia && !p1.equals(u.getUser())) {
                                System.out.print(p1.getPlayerName() + ", ");
                            }
                        }
                        System.out.println("]");
                        break;
                    }
                }
            } else if(n == 2) {
                while(true) {
                    Player p = players.get(rand.nextInt(players.size()));
                    if(p instanceof Detective) {
                        p.setName(p.getName()+"[User]");
                        u = new User(p);
                        System.out.println("You are " + p.getPlayerName() + ".");
                        System.out.print("You are a Detective. Other Detectives are [");
                        for(Player p1: players) {
                            if(p1 instanceof Detective && !p1.equals(u.getUser())) {
                                System.out.print(p1.getPlayerName() + ", ");
                            }
                        }
                        System.out.println("]");
                        break;
                    }
                }
            } else if(n == 3) {
                while(true) {
                    Player p = players.get(rand.nextInt(players.size()));
                    if(p instanceof Healer) {
                        p.setName(p.getName()+"[User]");
                        u = new User(p);
                        System.out.println("You are " + p.getPlayerName() + ".");
                        System.out.print("You are a Healer. Other Healers are [");
                        for(Player p1: players) {
                            if(p1 instanceof Healer && !p1.equals(u.getUser())) {
                                System.out.print(p1.getPlayerName() + ", ");
                            }
                        }
                        System.out.println("]");
                        break;
                    }
                }
            } else if(n == 4) {
                while(true) {
                    Player p = players.get(rand.nextInt(players.size()));
                    if(p instanceof Commoner) {
                        p.setName(p.getName()+"[User]");
                        u = new User(p);
                        System.out.println("You are " + p.getPlayerName() + ".");
                        System.out.println("You are a Commoner.");
                        break;
                    }
                }
            } else if(n == 5) {
                Player p = players.get(rand.nextInt(players.size()));
                p.setName(p.getName()+"[User]");
                u = new User(p);
                System.out.println("You are " + p.getPlayerName() + ".");
                if(p instanceof Mafia) {
                    System.out.print("You are a Mafia. Other Mafias are [");
                    for(Player p1: players) {
                        if(p1 instanceof Mafia && !p1.equals(u.getUser())) {
                            System.out.print(p1.getPlayerName() + ", ");
                        }
                    }
                    System.out.println("]");
                } else if(p instanceof Detective) {
                    System.out.print("You are a Detective. Other Detectives are [");
                    for(Player p1: players) {
                        if(p1 instanceof Detective && !p1.equals(u.getUser())) {
                            System.out.print(p1.getPlayerName() + ", ");
                        }
                    }
                    System.out.println("]");
                } else if(p instanceof Healer) {
                    System.out.print("You are a Healer. Other Healers are [");
                    for(Player p1: players) {
                        if(p1 instanceof Healer && !p1.equals(u.getUser())) {
                            System.out.print(p1.getPlayerName() + ", ");
                        }
                    }
                    System.out.println("]");
                } else {
                    System.out.println("You are a Commoner.");
                }
            } else {
                System.out.println("Invalid Input");
                System.exit(0);
            }
            ForEnd = new ArrayList<>(players);
        } else {
            System.out.println("Invalid Input");
            System.exit(0);
        }
    }

    private void theEnd() {
        System.out.println("Game Over.");
        if(winner) {
            System.out.println("The Mafias have lost.");
        } else {
            System.out.println("The Mafias have won.");
        }
        int i;
        String s = "";
        for(i = 0;i < m-1; i++) {
            s += ForEnd.get(i).getName() + ", ";
        }
        s += ForEnd.get(i).getName() + " - Mafias";
        System.out.println(s);
        s = "";
        for(i = m; i < m+d-1; i++) {
            s += ForEnd.get(i).getName() + ", ";
        }
        s += ForEnd.get(i).getName() + " - Detectives";
        System.out.println(s);
        s = "";
        for(i = m+d; i < m+d+h-1; i++) {
            s += ForEnd.get(i).getName() + ", ";
        }
        s += ForEnd.get(i).getName() + " - Healers";
        System.out.println(s);
        s = "";
        for(i = m+d+h; i < n-1; i++) {
            s += ForEnd.get(i).getName() + ", ";
        }
        s += ForEnd.get(i).getName() + " - Commoners";
        System.out.println(s);
    }
}

public class Main {
    public static void main(String[] args) {
	    // write your code here
        Scanner scan = new Scanner(System.in);
        System.out.print("Welcome to Mafia\n" +
                           "Enter Number of players: ");
        if(scan.hasNextInt()) {
            int n = -1;
            while(n < 6){
                n = scan.nextInt();
                if(n<6) {
                    System.out.println("Invalid Input.");
                    System.out.println("Enter Again:");
                }
            }
            MafiaGame MG = new MafiaGame(n);
            MG.start();
        } else {
            System.out.println("Invalid Input");
        }
    }
}
