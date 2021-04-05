
public class NBody {
    /*read the radius of a given planet*/
    public static double readRadius(String s) {
        In in = new In(s);
        in.readInt();
        double radiusOfPlanet = in.readDouble();
        return radiusOfPlanet;
    }

    /*list the bodies in a given file*/
    public static Planet[] readPlanets(String s) {
        In in = new In(s);
        int n = in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[n];
        for (int i = 0; i < planets.length; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double xV = in.readDouble();
            double yV = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            planets[i] = new Planet(xP, yP, xV, yV, m, img);
        }
        return planets;
    }

    /*Drawing the Initial Universe State (main)*/
    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        /*Drawing the Background*/
        String background = "images/starfield.jpg";
        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, background);

        /*Drawing All of the Planets*/
        for (Planet p : planets) {
            p.draw();
        }

        /*Enable double buffering to prevent flickering in the animation.*/
        StdDraw.enableDoubleBuffering();

        /*Creating an Animation*/
        for (double t = 0; t <= T; t += dt) {
            int n = planets.length;
            double[] xForces = new double[n];
            double[] yForces = new double[n];
            for (int i = 0; i < n; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }
            for (int i = 0; i < n; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }
            /*Draw the background image.*/
            StdDraw.clear();
            StdDraw.picture(0, 0, background);

            /*Drawing All of the Planets*/
            for (Planet p : planets) {
                p.draw();
            }

            /*Show the offscreen buffer (see the show method of StdDraw).*/
            StdDraw.show();

            /*Pause the animation for 10 milliseconds (see the pause method of StdDraw).
             You may need to tweak this on your computer.*/
            StdDraw.pause(10);
        }

        /** Printing the Universe */
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }

    }
}