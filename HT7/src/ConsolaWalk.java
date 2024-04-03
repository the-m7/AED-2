public class ConsolaWalk<V> implements IWalk<V>{

    @Override
    public void doWalk(V actualValue) {
        String[] pals = (String[])actualValue;
        for(String pal : pals){
            System.out.print(pal+", ");
        }
        System.out.println();
    }
    
}
