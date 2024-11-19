public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    private static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
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
        return Math.sqrt((this.xxPos - p.xxPos) * (this.xxPos - p.xxPos) + (this.yyPos - p.yyPos) * (this.yyPos - p.yyPos));
    }

    public double calcForceExertedBy(Planet p) {
        double r = calcDistance(p);
        return G * this.mass * p.mass / (r * r);
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return calcForceExertedBy(p) * dx / calcDistance(p);
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return calcForceExertedBy(p) * dy / calcDistance(p);
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netForceX = 0.0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                netForceX += calcForceExertedByX(p);
            }
        }
        return netForceX;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netForceY = 0.0;
        for (Planet p: allPlanets) {
            if (!this.equals(p)) {
                netForceY += calcForceExertedByY(p);
            }
        }
        return netForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX / this.mass;
        double aY = fY / this.mass;
        this.xxVel += aX * dt;
        this.yyVel += aY * dt;
        this.xxPos += this.xxVel * dt;
        this.yyPos += this.yyVel * dt;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
