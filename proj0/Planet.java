public class Planet {

    private static final double G = 6.67 * 1e-11; // Gravitational constant

    public double xxPos; // current x position
    public double yyPos; // current y position
    public double xxVel; // current velocity in the x direction
    public double yyVel; // current velocity in the y direction
    public double mass; // mass
    public String imgFileName; // name of the file that corresponds to the image that depicts the planet, e.g. jupiter.gif

    /*One way to instantiate the class is to provide all the characteristics an object owns.*/
    public Planet(double xP, double yP, double xV,
                double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /*instantiate the class by invoking an instance already exits.*/
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /*calculate the distance between two planets*/
    public double calcDistance(Planet p) {
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        double dis = Math.sqrt(dx*dx + dy*dy);
        return dis;
    }

    /*calcForceExertedBy*/
    public double calcForceExertedBy(Planet p) {
        double F = G * this.mass * p.mass / (this.calcDistance(p) * this.calcDistance(p));
        return F;
    }

    /*calcForceExertedByX and calcForceExertedByY*/
    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dx = p.xxPos - this.xxPos;
        double fx = F * dx / r;
        return fx;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double r = this.calcDistance(p);
        double dy = p.yyPos - this.yyPos;
        double fy = F * dy / r;
        return fy;
    }

    /*calcNetForceExertedByX and calcNetForceExertedByY*/
    public double calcNetForceExertedByX(Planet[] planets) {
        double NFx = 0d;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i])) {
                continue;
            }
            NFx += this.calcForceExertedByX(planets[i]);
        }
        return NFx;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double NFy = 0d;
        for (int i = 0; i < planets.length; i++) {
            if (this.equals(planets[i])) {
                continue;
            }
            NFy += this.calcForceExertedByY(planets[i]);
        }
        return NFy;
    }
    public void update(double dt, double fX, double fY) {
        double ax = fX / mass;
        double ay = fY / mass;
        xxVel = xxVel + dt * ax;
        yyVel = yyVel + dt * ay;
        xxPos = xxPos + dt * xxVel;
        yyPos = yyPos + dt * yyVel;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }

}