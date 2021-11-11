public class Body {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    public static final double G=6.67e-11;

    public Body(double xP, double yP, double xV, double yV, double m, String img){
        xxPos=xP;
        yyPos=yP;
        xxVel=xV;
        yyVel=yV;
        mass=m;
        imgFileName=img;
    }

    public Body(Body b){
        xxPos=b.xxPos;
        yyPos=b.yyPos;
        xxVel=b.xxVel;
        yyVel=b.yyVel;
        mass=b.mass;
        imgFileName=b.imgFileName;
    }

    public double calcDistance(Body b){
        return Math.hypot(xxPos-b.xxPos,yyPos-b.yyPos);
    }

    public double calcForceExertedBy(Body b){
        return G*mass*b.mass/Math.pow(calcDistance(b),2);
    }

    public double calcForceExertedByX(Body b){
        return calcForceExertedBy(b)*(b.xxPos-xxPos)/calcDistance(b);
    }

    public double calcForceExertedByY(Body b){
        return calcForceExertedBy(b)*(b.yyPos-yyPos)/calcDistance(b);
    }

    public double calcNetForceExertedByX(Body[] all){
        double netForceExertedByX=0.0;
        for(Body b : all){
            if(this.equals(b)){
                continue;
            }
            netForceExertedByX+=calcForceExertedByX(b);
        }
        return netForceExertedByX;
    }

    public double calcNetForceExertedByY(Body[] all){
        double netForceExertedByY=0.0;
        for(Body b : all){
            if(this.equals(b)){
                continue;
            }
            netForceExertedByY+=calcForceExertedByY(b);
        }
        return netForceExertedByY;
    }

    public void update(double dt, double fX, double fY){
        double aX=fX/mass;
        double aY=fY/mass;
        xxVel+=aX*dt;
        yyVel+=aY*dt;
        xxPos+=xxVel*dt;
        yyPos+=yyVel*dt;
    }

    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}