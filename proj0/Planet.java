public class Planet {

	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static final double g = 6.67e-11;

	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double rsqr = (this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) +
					  (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos);
		double r = Math.sqrt(rsqr);
		return r;
	}

	public double calcForceExertedBy(Planet p) {
		double force = (g * this.mass * p.mass) / (calcDistance(p) * calcDistance(p));
		return force;
	}

	public double calcForceExertedByX(Planet p) {
		return (calcForceExertedBy(p) * (p.xxPos - this.xxPos)) / calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		return (calcForceExertedBy(p) * (p.yyPos - this.yyPos)) / calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] planets) {
		double sum = 0;
		for (Planet p : planets) {
			if (!this.equals(p)) { 
				sum += calcForceExertedByX(p);
			}
		}
		return sum;
	}

	public double calcNetForceExertedByY(Planet[] planets) {
		double sum = 0;
		for (Planet p : planets) {
			if (!this.equals(p)) {
				sum += calcForceExertedByY(p);
			}
		}
		return sum;
	}

	public void update(double dt, double fX, double fY) {
		double xxAccl = fX / this.mass;
		double yyAccl = fY / this.mass;
		xxVel += dt * xxAccl;
		yyVel += dt * yyAccl;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}

	public void draw() {
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + imgFileName);
	}
}