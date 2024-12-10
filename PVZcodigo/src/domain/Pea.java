public class Pea extends Missile{
    private Zombie targetZombie;

    public Pea(int xInitial,int yInitial, Zombie zombie){
        this.xPosition = xInitial;
        this.yPosition = yInitial;
        this.damage = 20;
        this.targetZombie = zombie;
        this.name = "pea";
        this.width = 20;
        this.height = 20;
        this.extension = "pea.png";
    }

    @Override
    public void move(){
        xPosition += 5;

        if(targetZombie != null && targetZombie.getXPosition() <= xPosition + width){
            targetZombie.takeDamage(damage);
            disappear();
        }
    }

    @Override
    public int getRow() {
        return yPosition;
    }

    @Override
    public int getCol(){
        return xPosition;
    }

    /**
     * pea dissapear when hits zombie
     */
    private void disappear(){

    }
}
