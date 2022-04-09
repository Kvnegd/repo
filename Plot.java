
public class Plot {
	
	private int x;
	private int y;
	private int width;
	private int depth;
	
	public Plot() {
		x=0;
		y=0;
		width=1;
		depth=1;
	}
	
	public Plot(Plot p) {
		this.x = p.x;
		this.y = p.y;
		this.width = p.width;
		this.depth = p.depth;
	}
	
	public Plot(int x, int y, int width, int depth) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.depth = depth;
	}
	
	public boolean overlaps(Plot plot)
    {
        if ((this.x >= plot.x+plot.width) || (plot.x >= this.x+this.width))
            return false;
        else if ((this.y >= plot.y+plot.depth) || (plot.y >= this.y+this.depth))
            return false;
        return true;
    }

	
	public boolean encompasses(Plot plot)
    {
		
        return this.x <= plot.x && this.x + this.width >= plot.x + plot.width && 
        	   this.y <= plot.y && this.y + this.depth >= plot.y + plot.depth;
    }
	

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public String toString() {
		return "Upper left: (" + this.x + "," + this.y + "); Width: " + this.width + " Depth: " + this.depth;
	}

}
