
public class Playable implements Runnable {
	
	public GamePlay playAble;
	
	Playable(GamePlay p){
		playAble = p;
	}
	@Override
	public void run() {
		while(true) {
			playAble.Update();
			try {
				Thread.sleep(10);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

}
