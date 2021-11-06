package Game.Entities.Medallion;


import Game.RefLinks;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class MedallionManager {
    private final RefLinks refLinks;
    private final ArrayList<Medallion> medallions;

    public MedallionManager(RefLinks refLinks){
        this.refLinks = refLinks;
        medallions = new ArrayList<>();
    }

    public void Update(){
        Iterator<Medallion> it = medallions.iterator();
        while (it.hasNext()) {
            Medallion med = it.next();
            med.Update();
            if (med.isPickedUp())
                it.remove();
            }
    }

    public void Draw(Graphics g){
        for (Medallion med : medallions)
            med.Draw(g);
    }

    public void addMedallion(Medallion med)
    {
        med.setRefLinks(refLinks);
        medallions.add(med);
    }

    public RefLinks getRefLinks(){
        return refLinks;
    }
}