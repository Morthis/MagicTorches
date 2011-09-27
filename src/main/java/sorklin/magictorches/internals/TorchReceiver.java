package sorklin.magictorches.internals;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import sorklin.magictorches.MagicTorches;


public class TorchReceiver implements Cloneable {
    
    private Location torchLocation;
    private byte torchType;
    private long lastUsed = 0;

    public TorchReceiver(Location loc) {
        this.torchLocation = loc;
        this.torchType = 0x0; //NONE -- if left undefined == direct?
    }
    
    public TorchReceiver(Location loc, byte type) {
        this.torchLocation = loc;
        this.torchType = type;
    }
    
    public Location getLocation() {
        return torchLocation;
    }
    
    public byte getType() {
        return torchType;
    }
    
    public void setType(byte type) {
        this.torchType = type;
    }
    
    public boolean receive(boolean signal){ //torch On = true, off = false
        //Return true if I can process signal, else false to indicate
        //something wrong with this torch receiver.
        
        //Lets check for a location and a torch at that location.
        if(this.torchLocation == null)
            return false;
        Block torch = torchLocation.getBlock();
        if(!(torch.getType().equals(Material.TORCH) ||
                torch.getType().equals(Material.REDSTONE_TORCH_ON))) {
            return false;
        }
        
        switch(torchType) {
            case TorchArray.NONE:
                return false;
                
            case TorchArray.DIRECT:
                //MagicTorches.spamt("Direct receive");
                if(signal){
                    torch.setType(Material.TORCH);
                } else {
                    torch.setType(Material.REDSTONE_TORCH_ON);
                }
                lastUsed = System.currentTimeMillis();
                break;
                
            case TorchArray.INVERSE:
                //MagicTorches.spamt("Inverse receive");
                if(signal){
                    torch.setType(Material.REDSTONE_TORCH_ON);
                } else {
                    torch.setType(Material.TORCH);
                }
                lastUsed = System.currentTimeMillis();
                break;
                
            case TorchArray.DELAY:
                //MagicTorches.spamt("Delay receive");
                if(System.currentTimeMillis() > (MagicTorches.delayTime + lastUsed)){
                    //Okay to use.  Acts as toggle, so flip the torch data.
                    if(torch.getType().equals(Material.TORCH)) {
                        torch.setType(Material.REDSTONE_TORCH_ON);
                    } else
                    
                    if(torch.getType().equals(Material.REDSTONE_TORCH_ON)) {
                        torch.setType(Material.TORCH);
                    }
                    lastUsed = System.currentTimeMillis();
                }
                break;
        }
        return true;
    }
    
    
    @Override
    public String toString() {
        String result;
        result = this.torchLocation.toString();
        result = result + ":Type{" + String.valueOf(this.torchType) + "}";
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof TorchReceiver)) {
            return false;
        }
        TorchReceiver objTR = (TorchReceiver)obj;
        return (this.torchLocation.equals(objTR.torchLocation));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + (this.torchLocation != null ? this.torchLocation.hashCode() : 0);
        return hash;
    }

    @Override
    public TorchReceiver clone() {
        try {
            TorchReceiver l = (TorchReceiver) super.clone();
            l.torchLocation = torchLocation;
            l.torchType = torchType;
            l.lastUsed = lastUsed;
            return l;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
