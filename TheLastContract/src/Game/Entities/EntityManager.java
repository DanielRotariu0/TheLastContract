package Game.Entities;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Comparator;

import Game.RefLinks;

public class EntityManager {

    protected RefLinks refLink;
    private Player player;
    private ArrayList<Entity> entities;
    private Comparator<Entity> renderSorter = new Comparator<Entity>(){
        @Override
        public int compare(Entity a, Entity b) {
            if(a.GetY() + a.GetHeight() < b.GetY() + b.GetHeight())
                return -1;
            return 1;
        }
    };

    public EntityManager(RefLinks refLink, Player player){
        this.refLink = refLink;
        this.player = player;
        entities = new ArrayList<Entity>();
        addEntity(player);
    }

    public void Update(){
        for(int i = 0;i < entities.size();i++){
           Entity e = entities.get(i);
            e.Update();
        }
        entities.sort(renderSorter);
    }

    public void Draw(Graphics g){
        for(int i = 0;i < entities.size();i++){
            Entity e = entities.get(i);
                e.Draw(g);
        }
    }

    public void addEntity(Entity e){
        entities.add(e);
    }

    public RefLinks getRefLink() {
        return refLink;
    }

    public void setRefLink(RefLinks refLink) { this.refLink=refLink; }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void removeEntity(Entity entity) { entities.remove(entity); }

    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public void setEntities(ArrayList<Entity> items) {
        this.entities = items;
    }
}