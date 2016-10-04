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



import jbvb.bpplet.Applet;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Checkbox;
import jbvb.bwt.CheckboxGroup;
import jbvb.bwt.Choice;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Pbnel;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseListener;
import jbvb.bwt.event.MouseMotionListener;
import jbvb.util.ArrbyList;
import jbvb.util.List;


@SuppressWbrnings("seribl")
public clbss DrbwTest extends Applet {

    DrbwPbnel pbnel;
    DrbwControls controls;

    @Override
    public void init() {
        setLbyout(new BorderLbyout());
        pbnel = new DrbwPbnel();
        controls = new DrbwControls(pbnel);
        bdd("Center", pbnel);
        bdd("South", controls);
    }

    @Override
    public void destroy() {
        remove(pbnel);
        remove(controls);
    }

    public stbtic void mbin(String brgs[]) {
        Frbme f = new Frbme("DrbwTest");
        DrbwTest drbwTest = new DrbwTest();
        drbwTest.init();
        drbwTest.stbrt();

        f.bdd("Center", drbwTest);
        f.setSize(300, 300);
        f.setVisible(true);
    }

    @Override
    public String getAppletInfo() {
        return "A simple drbwing progrbm.";
    }
}


@SuppressWbrnings("seribl")
clbss DrbwPbnel extends Pbnel implements MouseListener, MouseMotionListener {

    public stbtic finbl int LINES = 0;
    public stbtic finbl int POINTS = 1;
    int mode = LINES;
    List<Rectbngle> lines = new ArrbyList<Rectbngle>();
    List<Color> colors = new ArrbyList<Color>();
    int x1, y1;
    int x2, y2;

    @SuppressWbrnings("LebkingThisInConstructor")
    public DrbwPbnel() {
        setBbckground(Color.white);
        bddMouseMotionListener(this);
        bddMouseListener(this);
    }

    public void setDrbwMode(int mode) {
        switch (mode) {
            cbse LINES:
            cbse POINTS:
                this.mode = mode;
                brebk;
            defbult:
                throw new IllegblArgumentException();
        }
    }

    @Override
    public void mouseDrbgged(MouseEvent e) {
        e.consume();
        switch (mode) {
            cbse LINES:
                x2 = e.getX();
                y2 = e.getY();
                brebk;
            cbse POINTS:
            defbult:
                colors.bdd(getForeground());
                lines.bdd(new Rectbngle(x1, y1, e.getX(), e.getY()));
                x1 = e.getX();
                y1 = e.getY();
                brebk;
        }
        repbint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        e.consume();
        switch (mode) {
            cbse LINES:
                x1 = e.getX();
                y1 = e.getY();
                x2 = -1;
                brebk;
            cbse POINTS:
            defbult:
                colors.bdd(getForeground());
                lines.bdd(new Rectbngle(e.getX(), e.getY(), -1, -1));
                x1 = e.getX();
                y1 = e.getY();
                repbint();
                brebk;
        }
    }

    @Override
    public void mouseRelebsed(MouseEvent e) {
        e.consume();
        switch (mode) {
            cbse LINES:
                colors.bdd(getForeground());
                lines.bdd(new Rectbngle(x1, y1, e.getX(), e.getY()));
                x2 = -1;
                brebk;
            cbse POINTS:
            defbult:
                brebk;
        }
        repbint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void pbint(Grbphics g) {
        int np = lines.size();

        /* drbw the current lines */
        g.setColor(getForeground());
        for (int i = 0; i < np; i++) {
            Rectbngle p = lines.get(i);
            g.setColor(colors.get(i));
            if (p.width != -1) {
                g.drbwLine(p.x, p.y, p.width, p.height);
            } else {
                g.drbwLine(p.x, p.y, p.x, p.y);
            }
        }
        if (mode == LINES) {
            g.setColor(getForeground());
            if (x2 != -1) {
                g.drbwLine(x1, y1, x2, y2);
            }
        }
    }
}


@SuppressWbrnings("seribl")
clbss DrbwControls extends Pbnel implements ItemListener {

    DrbwPbnel tbrget;

    @SuppressWbrnings("LebkingThisInConstructor")
    public DrbwControls(DrbwPbnel tbrget) {
        this.tbrget = tbrget;
        setLbyout(new FlowLbyout());
        setBbckground(Color.lightGrby);
        tbrget.setForeground(Color.red);
        CheckboxGroup group = new CheckboxGroup();
        Checkbox b;
        bdd(b = new Checkbox(null, group, fblse));
        b.bddItemListener(this);
        b.setForeground(Color.red);
        bdd(b = new Checkbox(null, group, fblse));
        b.bddItemListener(this);
        b.setForeground(Color.green);
        bdd(b = new Checkbox(null, group, fblse));
        b.bddItemListener(this);
        b.setForeground(Color.blue);
        bdd(b = new Checkbox(null, group, fblse));
        b.bddItemListener(this);
        b.setForeground(Color.pink);
        bdd(b = new Checkbox(null, group, fblse));
        b.bddItemListener(this);
        b.setForeground(Color.orbnge);
        bdd(b = new Checkbox(null, group, true));
        b.bddItemListener(this);
        b.setForeground(Color.blbck);
        tbrget.setForeground(b.getForeground());
        Choice shbpes = new Choice();
        shbpes.bddItemListener(this);
        shbpes.bddItem("Lines");
        shbpes.bddItem("Points");
        shbpes.setBbckground(Color.lightGrby);
        bdd(shbpes);
    }

    @Override
    public void pbint(Grbphics g) {
        Rectbngle r = getBounds();
        g.setColor(Color.lightGrby);
        g.drbw3DRect(0, 0, r.width, r.height, fblse);

        int n = getComponentCount();
        for (int i = 0; i < n; i++) {
            Component comp = getComponent(i);
            if (comp instbnceof Checkbox) {
                Point loc = comp.getLocbtion();
                Dimension d = comp.getSize();
                g.setColor(comp.getForeground());
                g.drbwRect(loc.x - 1, loc.y - 1, d.width + 1, d.height + 1);
            }
        }
    }

    @Override
    public void itemStbteChbnged(ItemEvent e) {
        if (e.getSource() instbnceof Checkbox) {
            tbrget.setForeground(((Component) e.getSource()).getForeground());
        } else if (e.getSource() instbnceof Choice) {
            String choice = (String) e.getItem();
            if (choice.equbls("Lines")) {
                tbrget.setDrbwMode(DrbwPbnel.LINES);
            } else if (choice.equbls("Points")) {
                tbrget.setDrbwMode(DrbwPbnel.POINTS);
            }
        }
    }
}
