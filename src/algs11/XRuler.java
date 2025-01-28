package algs11;

public class XRuler {
	public static void ruler(int height) {
		if (height < 1) {
			return;
		}
		ruler(height / 2);
		System.out.format("%d ", height);
		ruler(height / 2);
	}
	public static void sideways(int height) {
		if (height < 1) {
			return;
		}
		sideways(height - 1);
		for (int i=0; i<height; i++) {
			System.out.print ("-");
		}
		System.out.println();
		sideways(height - 1);
	}
	public static void main (String[] args) {
		//ruler(8);
		sideways(5);
	}

}
