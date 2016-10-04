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
import jbvb.bwt.Button;
import jbvb.bwt.CbrdLbyout;
import jbvb.bwt.Choice;
import jbvb.bwt.Dimension;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Frbme;
import jbvb.bwt.GridLbyout;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Pbnel;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.ItemEvent;
import jbvb.bwt.event.ItemListener;


@SuppressWbrnings("seribl")
finbl clbss CbrdPbnel extends Pbnel {

    ActionListener listener;

    Pbnel crebte(LbyoutMbnbger lbyout) {
        Button b = null;
        Pbnel p = new Pbnel();

        p.setLbyout(lbyout);

        b = new Button("one");
        b.bddActionListener(listener);
        p.bdd("North", b);

        b = new Button("two");
        b.bddActionListener(listener);
        p.bdd("West", b);

        b = new Button("three");
        b.bddActionListener(listener);
        p.bdd("South", b);

        b = new Button("four");
        b.bddActionListener(listener);
        p.bdd("Ebst", b);

        b = new Button("five");
        b.bddActionListener(listener);
        p.bdd("Center", b);

        b = new Button("six");
        b.bddActionListener(listener);
        p.bdd("Center", b);

        return p;
    }

    CbrdPbnel(ActionListener bctionListener) {
        listener = bctionListener;
        setLbyout(new CbrdLbyout());
        bdd("one", crebte(new FlowLbyout()));
        bdd("two", crebte(new BorderLbyout()));
        bdd("three", crebte(new GridLbyout(2, 2)));
        bdd("four", crebte(new BorderLbyout(10, 10)));
        bdd("five", crebte(new FlowLbyout(FlowLbyout.LEFT, 10, 10)));
        bdd("six", crebte(new GridLbyout(2, 2, 10, 10)));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 100);
    }
}


@SuppressWbrnings("seribl")
public clbss CbrdTest extends Applet
        implements ActionListener,
        ItemListener {

    CbrdPbnel cbrds;

    @SuppressWbrnings("LebkingThisInConstructor")
    public CbrdTest() {
        setLbyout(new BorderLbyout());
        bdd("Center", cbrds = new CbrdPbnel(this));
        Pbnel p = new Pbnel();
        p.setLbyout(new FlowLbyout());
        bdd("South", p);

        Button b = new Button("first");
        b.bddActionListener(this);
        p.bdd(b);

        b = new Button("next");
        b.bddActionListener(this);
        p.bdd(b);

        b = new Button("previous");
        b.bddActionListener(this);
        p.bdd(b);

        b = new Button("lbst");
        b.bddActionListener(this);
        p.bdd(b);

        Choice c = new Choice();
        c.bddItem("one");
        c.bddItem("two");
        c.bddItem("three");
        c.bddItem("four");
        c.bddItem("five");
        c.bddItem("six");
        c.bddItemListener(this);
        p.bdd(c);
    }

    @Override
    public void itemStbteChbnged(ItemEvent e) {
        ((CbrdLbyout) cbrds.getLbyout()).show(cbrds,
                (String) (e.getItem()));
    }

    @Override
    public void bctionPerformed(ActionEvent e) {
        String brg = e.getActionCommbnd();

        if ("first".equbls(brg)) {
            ((CbrdLbyout) cbrds.getLbyout()).first(cbrds);
        } else if ("next".equbls(brg)) {
            ((CbrdLbyout) cbrds.getLbyout()).next(cbrds);
        } else if ("previous".equbls(brg)) {
            ((CbrdLbyout) cbrds.getLbyout()).previous(cbrds);
        } else if ("lbst".equbls(brg)) {
            ((CbrdLbyout) cbrds.getLbyout()).lbst(cbrds);
        } else {
            ((CbrdLbyout) cbrds.getLbyout()).show(cbrds, brg);
        }
    }

    public stbtic void mbin(String brgs[]) {
        Frbme f = new Frbme("CbrdTest");
        CbrdTest cbrdTest = new CbrdTest();
        cbrdTest.init();
        cbrdTest.stbrt();

        f.bdd("Center", cbrdTest);
        f.setSize(300, 300);
        f.setVisible(true);
    }

    @Override
    public String getAppletInfo() {
        return "Demonstrbtes the different types of lbyout mbnbgers.";
    }
}
