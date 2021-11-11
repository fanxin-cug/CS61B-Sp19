public class NBody {
    public static double readRadius(String s){
        In in=new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        return R;
    }

    public static Body[] readBodies(String s){
        In in=new In(s);
        int N = in.readInt();
        double R = in.readDouble();
        Body[] res=new Body[N];
        for(int i=0;i<N;i++){
            double xPos=in.readDouble();
            double yPos=in.readDouble();
            double xVel=in.readDouble();
            double yVel=in.readDouble();
            double m=in.readDouble();
            String name=in.readString();
            Body b=new Body(xPos,yPos,xVel,yVel,m,name);
            res[i]=b;
        }
        return res;
    }

    public static void main(String[] args){
        //Collecting All Needed Input
        double T=Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double R=readRadius(filename);
        Body[] bodies=readBodies(filename);

        //Creating an Animation
        StdDraw.enableDoubleBuffering();
        StdDraw.setScale(-R, R);
        //Drawing the Background
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        //Drawing bodies
        for(Body b:bodies){
            b.draw();
        }
        StdDraw.show();
        StdDraw.pause(10);

        double t=0.0;
        int num= bodies.length;
        while(t<T || Math.abs(t-T)<1e-6){
            //Drawing the Background
            StdDraw.clear();
            StdDraw.picture(0, 0, "images/starfield.jpg");

            double[] xForce=new double[num];
            double[] yForce=new double[num];
            for(int i=0;i<num;i++){
                xForce[i]=bodies[i].calcNetForceExertedByX(bodies);
                yForce[i]=bodies[i].calcNetForceExertedByY(bodies);
            }
            for(int i=0;i<num;i++){
                bodies[i].update(dt,xForce[i],yForce[i]);
            }

            //Drawing bodies
            for(Body b:bodies){
                b.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);

            t+=dt;
        }

        //Printing the Universe
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", R);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                    bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }
    }
}
