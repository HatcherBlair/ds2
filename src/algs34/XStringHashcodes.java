package algs34;
public class XStringHashcodes {
	public static void main (String[] args) {
		// from http://stackoverflow.com/questions/2310498/why-doesnt-strings-hashcode-cache-0
		String[] strings = new String[] {
				"pollinating sandboxes",
				"amusement & hemophilias",
				"schoolworks = perversive",
				"electrolysissweeteners.net",
				"constitutionalunstableness.net",
				"grinnerslaphappier.org",
				"BLEACHINGFEMININELY.NET",
				"WWW.BUMRACEGOERS.ORG",
				"WWW.RACCOONPRUDENTIALS.NET",
				"Microcomputers: the unredeemed lollipop...",
				"Incentively, my dear, I don't tessellate a derangement.",
				"A person who never yodelled an apology, never preened vocalizing transsexuals.",
				"polygenelubricants",
				"And so my fellow mismanagements: ask not what your newsdealer can sugarcoat for you -- ask what you can sugarcoat for your newsdealer."
		};
		for (String s : strings) {
			System.out.format ("%x %s\n", s.hashCode (), s);
		}
	}
}
