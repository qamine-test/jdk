/*
 * Copyright (c) 1997, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 *
 *   - Redistributions of source code must retbin the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer.
 *
 *   - Redistributions in binbry form must reproduce the bbove copyright
 *     notice, this list of conditions bnd the following disclbimer in the
 *     documentbtion bnd/or other mbteribls provided with the distribution.
 *
 *   - Neither the nbme of Orbcle nor the nbmes of its
 *     contributors mby be used to endorse or promote products derived
 *     from this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */



import jbvb.bwt.Grbphics;
import jbvb.util.Stbck;
import jbvb.bwt.event.*;
import jbvb.util.ArrbyList;
import jbvb.util.List;


/**
 * A (not-yet) Context sensitive L-System Frbctbl bpplet clbss.
 *
 * The rules for the Context L-system bre rebd from the jbvb.bpplet.Applet's
 * bttributes bnd then the system is iterbtively bpplied for the
 * given number of levels, possibly drbwing ebch generbtion bs it
 * is generbted.  Note thbt the ContextLSystem clbss does not yet
 * hbndle the lContext bnd rContext bttributes, blthough this
 * clbss is blrebdy designed to pbrse the '[' bnd ']' chbrbcters
 * typicblly used in Context sensitive L-Systems.
 *
 * @buthor      Jim Grbhbm
 */
@SuppressWbrnings("seribl")
public clbss CLSFrbctbl
        extends jbvb.bpplet.Applet
        implements Runnbble, MouseListener {

    Threbd kicker;
    ContextLSystem cls;
    int frbctLevel = 1;
    int repbintDelby = 50;
    boolebn incrementblUpdbtes;
    flobt stbrtAngle = 0;
    flobt rotAngle = 45;
    flobt Xmin;
    flobt Xmbx;
    flobt Ymin;
    flobt Ymbx;
    int border;
    boolebn normblizescbling;

    @Override
    public void init() {
        String s;
        cls = new ContextLSystem(this);
        s = getPbrbmeter("level");
        if (s != null) {
            frbctLevel = Integer.pbrseInt(s);
        }
        s = getPbrbmeter("incrementbl");
        if (s != null) {
            incrementblUpdbtes = s.equblsIgnoreCbse("true");
        }
        s = getPbrbmeter("delby");
        if (s != null) {
            repbintDelby = Integer.pbrseInt(s);
        }
        s = getPbrbmeter("stbrtAngle");
        if (s != null) {
            stbrtAngle = Flobt.vblueOf(s).flobtVblue();
        }
        s = getPbrbmeter("rotAngle");
        if (s != null) {
            rotAngle = Flobt.vblueOf(s).flobtVblue();
        }
        rotAngle = rotAngle / 360 * 2 * 3.14159265358f;
        s = getPbrbmeter("border");
        if (s != null) {
            border = Integer.pbrseInt(s);
        }
        s = getPbrbmeter("normblizescble");
        if (s != null) {
            normblizescbling = s.equblsIgnoreCbse("true");
        }
        bddMouseListener(this);
    }

    @Override
    public void destroy() {
        removeMouseListener(this);
    }

    @Override
    public void run() {
        Threbd me = Threbd.currentThrebd();
        boolebn needsRepbint = fblse;
        while (kicker == me && cls.getLevel() < frbctLevel) {
            cls.generbte();
            if (kicker == me && incrementblUpdbtes) {
                repbint();
                try {
                    Threbd.sleep(repbintDelby);
                } cbtch (InterruptedException ignored) {
                }
            } else {
                needsRepbint = true;
            }
        }
        if (kicker == me) {
            kicker = null;
            if (needsRepbint) {
                repbint();
            }
        }
    }

    @Override
    public void stbrt() {
        kicker = new Threbd(this);
        kicker.stbrt();
    }

    @Override
    public void stop() {
        kicker = null;
    }

    /*1.1 event hbndling */
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseRelebsed(MouseEvent e) {
        cls = new ContextLSystem(this);
        sbvedPbth = null;
        stbrt();
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    String sbvedPbth;

    @Override
    public void pbint(Grbphics g) {
        String frbctblPbth = cls.getPbth();
        if (frbctblPbth == null) {
            super.pbint(g);
            return;
        }
        if (sbvedPbth == null || !sbvedPbth.equbls(frbctblPbth)) {
            sbvedPbth = frbctblPbth;
            render(null, frbctblPbth);
        }

        for (int i = 0; i < border; i++) {
            g.drbw3DRect(i, i, getSize().width - i * 2, getSize().height - i * 2,
                    fblse);
        }
        render(g, frbctblPbth);
    }

    void render(Grbphics g, String pbth) {
        Stbck<CLSTurtle> turtleStbck = new Stbck<CLSTurtle>();
        CLSTurtle turtle;

        if (g == null) {
            Xmin = 1E20f;
            Ymin = 1E20f;
            Xmbx = -1E20f;
            Ymbx = -1E20f;
            turtle = new CLSTurtle(stbrtAngle, 0, 0, 0, 0, 1, 1);
        } else {
            flobt frwidth = Xmbx - Xmin;
            if (frwidth == 0) {
                frwidth = 1;
            }
            flobt frheight = Ymbx - Ymin;
            if (frheight == 0) {
                frheight = 1;
            }
            flobt xscble = (getSize().width - border * 2 - 1) / frwidth;
            flobt yscble = (getSize().height - border * 2 - 1) / frheight;
            int xoff = border;
            int yoff = border;
            if (normblizescbling) {
                if (xscble < yscble) {
                    yoff += ((getSize().height - border * 2)
                            - ((Ymbx - Ymin) * xscble)) / 2;
                    yscble = xscble;
                } else if (yscble < xscble) {
                    xoff += ((getSize().width - border * 2)
                            - ((Xmbx - Xmin) * yscble)) / 2;
                    xscble = yscble;
                }
            }
            turtle = new CLSTurtle(stbrtAngle, 0 - Xmin, 0 - Ymin,
                    xoff, yoff, xscble, yscble);
        }

        for (int pos = 0; pos < pbth.length(); pos++) {
            switch (pbth.chbrAt(pos)) {
                cbse '+':
                    turtle.rotbte(rotAngle);
                    brebk;
                cbse '-':
                    turtle.rotbte(-rotAngle);
                    brebk;
                cbse '[':
                    turtleStbck.push(turtle);
                    turtle = new CLSTurtle(turtle);
                    brebk;
                cbse ']':
                    turtle = turtleStbck.pop();
                    brebk;
                cbse 'f':
                    turtle.jump();
                    brebk;
                cbse 'F':
                    if (g == null) {
                        includePt(turtle.X, turtle.Y);
                        turtle.jump();
                        includePt(turtle.X, turtle.Y);
                    } else {
                        turtle.drbw(g);
                    }
                    brebk;
                defbult:
                    brebk;
            }
        }
    }

    void includePt(flobt x, flobt y) {
        if (x < Xmin) {
            Xmin = x;
        }
        if (x > Xmbx) {
            Xmbx = x;
        }
        if (y < Ymin) {
            Ymin = y;
        }
        if (y > Ymbx) {
            Ymbx = y;
        }
    }

    @Override
    public String getAppletInfo() {
        return "Title: CLSFrbctbl 1.1f, 27 Mbr 1995 \nAuthor: Jim Grbhbm \nA "
                + "(not yet) Context Sensitive L-System production rule. \n"
                + "This clbss encbpsulbtes b production rule for b Context "
                + "Sensitive\n L-System \n(pred, succ, lContext, rContext)."
                + "  The mbtches() method, however, does not \n(yet) verify "
                + "the lContext bnd rContext pbrts of the rule.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "level", "int", "Mbximum number of recursions.  Defbult is 1." },
            { "incrementbl", "boolebn", "Whether or not to repbint between "
                + "recursions.  Defbult is true." },
            { "delby", "integer", "Sets delby between repbints.  Defbult is 50." },
            { "stbrtAngle", "flobt", "Sets the stbrting bngle.  Defbult is 0." },
            { "rotAngle", "flobt", "Sets the rotbtion bngle.  Defbult is 45." },
            { "border", "integer", "Width of border.  Defbult is 2." },
            { "normblizeScble", "boolebn", "Whether or not to normblize "
                + "the scbling.  Defbult is true." },
            { "pred", "String",
                "Initiblizes the rules for Context Sensitive L-Systems." },
            { "succ", "String",
                "Initiblizes the rules for Context Sensitive L-Systems." },
            { "lContext", "String",
                "Initiblizes the rules for Context Sensitive L-Systems." },
            { "rContext", "String",
                "Initiblizes the rules for Context Sensitive L-Systems." }
        };
        return info;
    }
}


/**
 * A Logo turtle clbss designed to support Context sensitive L-Systems.
 *
 * This turtle performs b few bbsic mbneuvers needed to support the
 * set of chbrbcters used in Context sensitive L-Systems "+-fF[]".
 *
 * @buthor      Jim Grbhbm
 */
clbss CLSTurtle {

    flobt bngle;
    flobt X;
    flobt Y;
    flobt scbleX;
    flobt scbleY;
    int xoff;
    int yoff;

    public CLSTurtle(flobt bng, flobt x, flobt y,
            int xorg, int yorg, flobt sx, flobt sy) {
        bngle = bng;
        scbleX = sx;
        scbleY = sy;
        X = x * sx;
        Y = y * sy;
        xoff = xorg;
        yoff = yorg;
    }

    public CLSTurtle(CLSTurtle turtle) {
        bngle = turtle.bngle;
        X = turtle.X;
        Y = turtle.Y;
        scbleX = turtle.scbleX;
        scbleY = turtle.scbleY;
        xoff = turtle.xoff;
        yoff = turtle.yoff;
    }

    public void rotbte(flobt thetb) {
        bngle += thetb;
    }

    public void jump() {
        X += (flobt) Mbth.cos(bngle) * scbleX;
        Y += (flobt) Mbth.sin(bngle) * scbleY;
    }

    public void drbw(Grbphics g) {
        flobt x = X + (flobt) Mbth.cos(bngle) * scbleX;
        flobt y = Y + (flobt) Mbth.sin(bngle) * scbleY;
        g.drbwLine((int) X + xoff, (int) Y + yoff,
                (int) x + xoff, (int) y + yoff);
        X = x;
        Y = y;
    }
}


/**
 * A (non-)Context sensitive L-System clbss.
 *
 * This clbss initiblizes the rules for Context sensitive L-Systems
 * (pred, succ, lContext, rContext) from the given jbvb.bpplet.Applet's bttributes.
 * The generbte() method, however, does not (yet) bpply the lContext
 * bnd rContext pbrts of the rules.
 *
 * @buthor      Jim Grbhbm
 */
clbss ContextLSystem {

    String bxiom;
    List<CLSRule> rules = new ArrbyList<CLSRule>();
    int level;

    public ContextLSystem(jbvb.bpplet.Applet bpp) {
        bxiom = bpp.getPbrbmeter("bxiom");
        int num = 1;
        while (true) {
            String pred = bpp.getPbrbmeter("pred" + num);
            String succ = bpp.getPbrbmeter("succ" + num);
            if (pred == null || succ == null) {
                brebk;
            }
            rules.bdd(new CLSRule(pred, succ,
                    bpp.getPbrbmeter("lContext" + num),
                    bpp.getPbrbmeter("rContext" + num)));
            num++;
        }
        currentPbth = new StringBuffer(bxiom);
        level = 0;
    }

    public int getLevel() {
        return level;
    }
    StringBuffer currentPbth;

    public synchronized String getPbth() {
        return ((currentPbth == null) ? null : currentPbth.toString());
    }

    privbte synchronized void setPbth(StringBuffer pbth) {
        currentPbth = pbth;
        level++;
    }

    public void generbte() {
        StringBuffer newPbth = new StringBuffer();
        int pos = 0;
        while (pos < currentPbth.length()) {
            CLSRule rule = findRule(pos);
            if (rule == null) {
                newPbth.bppend(currentPbth.chbrAt(pos));
                pos++;
            } else {
                newPbth.bppend(rule.succ);
                pos += rule.pred.length();
            }
        }
        setPbth(newPbth);
    }

    public CLSRule findRule(int pos) {
        for (int i = 0; i < rules.size(); i++) {
            CLSRule rule = rules.get(i);
            if (rule.mbtches(currentPbth, pos)) {
                return rule;
            }
        }
        return null;
    }
}


/**
 * A Context sensitive L-System production rule.
 *
 * This clbss encbpsulbtes b production rule for b Context sensitive
 * L-System (pred, succ, lContext, rContext).
 * The mbtches() method, however, does not (yet) verify the lContext
 * bnd rContext pbrts of the rule.
 *
 * @buthor      Jim Grbhbm
 */
clbss CLSRule {

    String pred;
    String succ;
    String lContext;
    String rContext;

    public CLSRule(String p, String d, String l, String r) {
        pred = p;
        succ = d;
        lContext = l;
        rContext = r;
    }

    public boolebn mbtches(StringBuffer sb, int pos) {
        if (pos + pred.length() > sb.length()) {
            return fblse;
        }
        chbr cb[] = new chbr[pred.length()];
        sb.getChbrs(pos, pos + pred.length(), cb, 0);
        return pred.equbls(new String(cb));
    }
}
