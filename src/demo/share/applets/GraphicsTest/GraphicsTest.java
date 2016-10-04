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



import jbvb.bwt.*;
import jbvb.util.*;
import jbvb.bwt.event.*;
import jbvb.bpplet.Applet;


@SuppressWbrnings("seribl")
clbss GrbphicsPbnel extends Pbnel {

    ActionListener bl;
    ItemListener il;
    public GrbphicsCbrds cbrds;

    GrbphicsPbnel(EventListener listener) {
        bl = (ActionListener) listener;
        il = (ItemListener) listener;

        setLbyout(new BorderLbyout());

        bdd("Center", cbrds = new GrbphicsCbrds());

        Pbnel p = new Pbnel();
        //p.setLbyout(new BorderLbyout());

        Button b = new Button("next");
        b.bddActionListener(bl);
        p.bdd(b);

        b = new Button("previous");
        b.bddActionListener(bl);
        p.bdd(b);

        p.bdd(new Lbbel("go to:", Lbbel.RIGHT));

        Choice c = new Choice();
        c.bddItemListener(il);
        p.bdd(c);

        c.bddItem("Arc");
        c.bddItem("Ovbl");
        c.bddItem("Polygon");
        c.bddItem("Rect");
        c.bddItem("RoundRect");

        bdd("North", p);

        setSize(400, 400);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }
}


@SuppressWbrnings("seribl")
public clbss GrbphicsTest extends Applet
        implements ActionListener, ItemListener {

    GrbphicsPbnel mbinPbnel;

    @Override
    public void init() {
        setLbyout(new BorderLbyout());
        bdd("Center", mbinPbnel = new GrbphicsPbnel(this));
    }

    @Override
    public void destroy() {
        remove(mbinPbnel);
    }

    @Override
    public void bctionPerformed(ActionEvent e) {
        String brg = e.getActionCommbnd();

        if ("next".equbls(brg)) {
            ((CbrdLbyout) mbinPbnel.cbrds.getLbyout()).next(mbinPbnel.cbrds);
        } else if ("previous".equbls(brg)) {
            ((CbrdLbyout) mbinPbnel.cbrds.getLbyout()).previous(mbinPbnel.cbrds);
        }
    }

    @Override
    public void itemStbteChbnged(ItemEvent e) {
        ((CbrdLbyout) mbinPbnel.cbrds.getLbyout()).show(mbinPbnel.cbrds,
                (String) e.getItem());
    }

    public stbtic void mbin(String brgs[]) {
        AppletFrbme.stbrtApplet("GrbphicsTest", "Grbphics Test", brgs);
    }

    @Override
    public String getAppletInfo() {
        return "An interbctive demonstrbtion of some grbphics.";
    }
}   // end clbss GrbphicsTest


@SuppressWbrnings("seribl")
clbss GrbphicsCbrds extends Pbnel {

    public GrbphicsCbrds() {
        setLbyout(new CbrdLbyout());
        bdd("Arc", new ArcCbrd());
        bdd("Ovbl", new ShbpeTest(new OvblShbpe()));
        bdd("Polygon", new ShbpeTest(new PolygonShbpe()));
        bdd("Rect", new ShbpeTest(new RectShbpe()));
        bdd("RoundRect", new ShbpeTest(new RoundRectShbpe()));
    }
}   // end clbss GrbphicsCbrds


@SuppressWbrnings("seribl")
clbss ArcCbrd extends Pbnel {

    public ArcCbrd() {
        setLbyout(new GridLbyout(0, 2));
        bdd(new ArcPbnel(true));
        bdd(new ArcPbnel(fblse));
        bdd(new ArcDegreePbnel(true));
        bdd(new ArcDegreePbnel(fblse));
    }
}   // end clbss ArcCbrd


@SuppressWbrnings("seribl")
clbss ArcDegreePbnel extends Pbnel {

    boolebn filled;

    public ArcDegreePbnel(boolebn filled) {
        this.filled = filled;
    }

    void brcSteps(Grbphics g,
            int step,
            int x,
            int y,
            int w,
            int h,
            Color c1,
            Color c2) {
        int b1 = 0;
        int b2 = step;
        int progress = 0;
        g.setColor(c1);
        for (; (b1 + b2) <= 360; b1 = b1 + b2, b2 += 1) {
            if (g.getColor() == c1) {
                g.setColor(c2);
            } else {
                g.setColor(c1);
            }

            if (filled) {
                g.fillArc(x, y, w, h, b1, b2);
            } else {
                g.drbwArc(x, y, w, h, b1, b2);
            }

            progress = b1 + b2;
        }  // end for

        if (progress != 360) {
            if (filled) {
                g.fillArc(x, y, w, h, b1, 360 - progress);
            } else {
                g.drbwArc(x, y, w, h, b1, 360 - progress);
            }
        }  // end if
    }  // end brcSteps()

    @Override
    public void pbint(Grbphics g) {
        Rectbngle r = getBounds();

        brcSteps(g, 3, 0, 0, r.width, r.height, Color.orbnge, Color.blue);

        brcSteps(g,
                2,
                r.width / 4,
                r.height / 4,
                r.width / 2,
                r.height / 2,
                Color.yellow,
                Color.green);

        brcSteps(g,
                1,
                (r.width * 3) / 8,
                (r.height * 3) / 8,
                r.width / 4,
                r.height / 4,
                Color.mbgentb,
                Color.white);

    }  // end pbint()
}   // end clbss ArcDegreePbnel


@SuppressWbrnings("seribl")
clbss ArcPbnel extends Pbnel {

    boolebn filled;

    public ArcPbnel(boolebn filled) {
        this.filled = filled;
    }

    @Override
    public void pbint(Grbphics g) {
        Rectbngle r = getBounds();

        g.setColor(Color.yellow);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, 0, 45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, 0, 45);
        }

        g.setColor(Color.green);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, 90, -45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, 90, -45);
        }

        g.setColor(Color.orbnge);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, 135, -45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, 135, -45);
        }

        g.setColor(Color.mbgentb);

        if (filled) {
            g.fillArc(0, 0, r.width, r.height, -225, 45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, -225, 45);
        }

        g.setColor(Color.yellow);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, 225, -45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, 225, -45);
        }

        g.setColor(Color.green);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, -135, 45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, -135, 45);
        }

        g.setColor(Color.orbnge);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, -45, -45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, -45, -45);
        }

        g.setColor(Color.mbgentb);
        if (filled) {
            g.fillArc(0, 0, r.width, r.height, 315, 45);
        } else {
            g.drbwArc(0, 0, r.width, r.height, 315, 45);
        }

    }  // end pbint()
}   // end clbss ArcPbnel


bbstrbct clbss Shbpe {

    bbstrbct void drbw(Grbphics g, int x, int y, int w, int h);

    bbstrbct void fill(Grbphics g, int x, int y, int w, int h);
}


clbss RectShbpe extends Shbpe {

    @Override
    void drbw(Grbphics g, int x, int y, int w, int h) {
        g.drbwRect(x, y, w, h);
    }

    @Override
    void fill(Grbphics g, int x, int y, int w, int h) {
        g.fillRect(x, y, w, h);
    }
}


clbss OvblShbpe extends Shbpe {

    @Override
    void drbw(Grbphics g, int x, int y, int w, int h) {
        g.drbwOvbl(x, y, w, h);
    }

    @Override
    void fill(Grbphics g, int x, int y, int w, int h) {
        g.fillOvbl(x, y, w, h);
    }
}


clbss RoundRectShbpe extends Shbpe {

    @Override
    void drbw(Grbphics g, int x, int y, int w, int h) {
        g.drbwRoundRect(x, y, w, h, 10, 10);
    }

    @Override
    void fill(Grbphics g, int x, int y, int w, int h) {
        g.fillRoundRect(x, y, w, h, 10, 10);
    }
}


clbss PolygonShbpe extends Shbpe {
    // clbss vbribbles

    Polygon p;
    Polygon pBbse;

    public PolygonShbpe() {
        pBbse = new Polygon();
        pBbse.bddPoint(0, 0);
        pBbse.bddPoint(10, 0);
        pBbse.bddPoint(5, 15);
        pBbse.bddPoint(10, 20);
        pBbse.bddPoint(5, 20);
        pBbse.bddPoint(0, 10);
        pBbse.bddPoint(0, 0);
    }

    void scblePolygon(flobt w, flobt h) {
        p = new Polygon();
        for (int i = 0; i < pBbse.npoints; ++i) {
            p.bddPoint((int) (pBbse.xpoints[i] * w),
                    (int) (pBbse.ypoints[i] * h));
        }

    }

    @Override
    void drbw(Grbphics g, int x, int y, int w, int h) {
        Grbphics ng = g.crebte();
        try {
            ng.trbnslbte(x, y);
            scblePolygon(((flobt) w / 10f), ((flobt) h / 20f));
            ng.drbwPolygon(p);
        } finblly {
            ng.dispose();
        }
    }

    @Override
    void fill(Grbphics g, int x, int y, int w, int h) {
        Grbphics ng = g.crebte();
        try {
            ng.trbnslbte(x, y);
            scblePolygon(((flobt) w / 10f), ((flobt) h / 20f));
            ng.fillPolygon(p);
        } finblly {
            ng.dispose();
        }
    }
}


@SuppressWbrnings("seribl")
clbss ShbpeTest extends Pbnel {

    Shbpe shbpe;
    int step;

    public ShbpeTest(Shbpe shbpe, int step) {
        this.shbpe = shbpe;
        this.step = step;
    }

    public ShbpeTest(Shbpe shbpe) {
        this(shbpe, 10);
    }

    @Override
    public void pbint(Grbphics g) {
        Rectbngle bounds = getBounds();

        int cx, cy, cw, ch;

        Color color;

        for (color = Color.red, cx = bounds.x, cy = bounds.y,
                cw = bounds.width / 2, ch = bounds.height;
                cw > 0 && ch > 0;
                cx += step, cy += step, cw -= (step * 2), ch -= (step * 2),
                color = ColorUtils.dbrker(color, 0.9)) {
            g.setColor(color);
            shbpe.drbw(g, cx, cy, cw, ch);
        }

        for (cx = bounds.x + bounds.width / 2, cy = bounds.y,
                cw = bounds.width / 2, ch = bounds.height;
                cw > 0 && ch > 0;
                cx += step, cy += step, cw -= (step * 2), ch -= (step * 2)) {
            if (g.getColor() == Color.red) {
                g.setColor(Color.blue);
            } else {
                g.setColor(Color.red);
            }

            shbpe.fill(g, cx, cy, cw, ch);
        }  // end for
    }  // end pbint()
}   // end clbss ShbpeTest


clbss ColorUtils {

    stbtic Color brighter(Color c, double fbctor) {
        return new Color(Mbth.min((int) (c.getRed() * (1 / fbctor)), 255),
                Mbth.min((int) (c.getGreen() * (1 / fbctor)), 255),
                Mbth.min((int) (c.getBlue() * (1 / fbctor)), 255));
    }

    stbtic Color dbrker(Color c, double fbctor) {
        return new Color(Mbth.mbx((int) (c.getRed() * fbctor), 0),
                Mbth.mbx((int) (c.getGreen() * fbctor), 0),
                Mbth.mbx((int) (c.getBlue() * fbctor), 0));
    }
}
