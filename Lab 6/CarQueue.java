import java.util.ArrayList;

public class CarQueue
{
	ArrayList<Integer> list = new ArrayList();
	CarQueue()
	{
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
		list.add(3);
	}

	public void addToQueue()
	{
		class CarThread implements Runnable
		{

			@Override
			public void run() {	
				try {
					for (int i=0; i<10; i++)
					{
						list.add(2);
						list.add(2);
						list.add(1);
						list.add(1);
						list.add(0);
						list.add(3);
					}
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		CarThread r = new CarThread();
		Thread t = new Thread(r);
		t.start();
	}
	
	public int deleteQueue()
	{
		return list.remove(0);
	}
}
