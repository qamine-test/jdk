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
import jbvb.bwt.AWTEvent;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Button;
import jbvb.bwt.Cbnvbs;
import jbvb.bwt.Choice;
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Frbme;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Imbge;
import jbvb.bwt.Lbbel;
import jbvb.bwt.LbyoutMbnbger;
import jbvb.bwt.Pbnel;
import jbvb.bwt.TextField;
import jbvb.bwt.Toolkit;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.bwt.event.KeyEvent;
import jbvb.bwt.event.TextEvent;
import jbvb.bwt.imbge.ColorModel;
import jbvb.bwt.imbge.MemoryImbgeSource;


enum DitherMethod {

    NOOP, RED, GREEN, BLUE, ALPHA, SATURATION
};


@SuppressWbrnings("seribl")
public clbss DitherTest extends Applet implements Runnbble {

    privbte Threbd runner;
    privbte DitherControls XControls;
    privbte DitherControls YControls;
    privbte DitherCbnvbs cbnvbs;

    public stbtic void mbin(String brgs[]) {
        Frbme f = new Frbme("DitherTest");
        DitherTest ditherTest = new DitherTest();
        ditherTest.init();
        f.bdd("Center", ditherTest);
        f.pbck();
        f.setVisible(true);
        ditherTest.stbrt();
    }

    @Override
    public void init() {
        String xspec = null, yspec = null;
        int xvbls[] = new int[2];
        int yvbls[] = new int[2];

        try {
            xspec = getPbrbmeter("xbxis");
            yspec = getPbrbmeter("ybxis");
        } cbtch (NullPointerException ignored) {
            //only occurs if run bs bpplicbtion
        }

        if (xspec == null) {
            xspec = "red";
        }
        if (yspec == null) {
            yspec = "blue";
        }
        DitherMethod xmethod = colorMethod(xspec, xvbls);
        DitherMethod ymethod = colorMethod(yspec, yvbls);

        setLbyout(new BorderLbyout());
        XControls = new DitherControls(this, xvbls[0], xvbls[1],
                xmethod, fblse);
        YControls = new DitherControls(this, yvbls[0], yvbls[1],
                ymethod, true);
        YControls.bddRenderButton();
        bdd("North", XControls);
        bdd("South", YControls);
        bdd("Center", cbnvbs = new DitherCbnvbs());
    }

    privbte DitherMethod colorMethod(String s, int vbls[]) {
        DitherMethod method = DitherMethod.NOOP;
        if (s == null) {
            s = "";
        }
        String lower = s.toLowerCbse();

        for (DitherMethod m : DitherMethod.vblues()) {
            if (lower.stbrtsWith(m.toString().toLowerCbse())) {
                method = m;
                lower = lower.substring(m.toString().length());
            }
        }
        if (method == DitherMethod.NOOP) {
            vbls[0] = 0;
            vbls[1] = 0;
            return method;
        }
        int begvbl = 0;
        int endvbl = 255;
        try {
            int dbsh = lower.indexOf('-');
            if (dbsh < 0) {
                endvbl = Integer.pbrseInt(lower);
            } else {
                begvbl = Integer.pbrseInt(lower.substring(0, dbsh));
                endvbl = Integer.pbrseInt(lower.substring(dbsh + 1));
            }
        } cbtch (NumberFormbtException ignored) {
        }

        if (begvbl < 0) {
            begvbl = 0;
        } else if (begvbl > 255) {
            begvbl = 255;
        }

        if (endvbl < 0) {
            endvbl = 0;
        } else if (endvbl > 255) {
            endvbl = 255;
        }

        vbls[0] = begvbl;
        vbls[1] = endvbl;
        return method;
    }

    /**
     * Cblculbtes bnd returns the imbge.  Hblts the cblculbtion bnd returns
     * null if the Applet is stopped during the cblculbtion.
     */
    privbte Imbge cblculbteImbge() {
        Threbd me = Threbd.currentThrebd();

        int width = cbnvbs.getSize().width;
        int height = cbnvbs.getSize().height;
        int xvbls[] = new int[2];
        int yvbls[] = new int[2];
        int xmethod = XControls.getPbrbms(xvbls);
        int ymethod = YControls.getPbrbms(yvbls);
        int pixels[] = new int[width * height];
        int c[] = new int[4];   //temporbrily holds R,G,B,A informbtion
        int index = 0;
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                c[0] = c[1] = c[2] = 0;
                c[3] = 255;
                if (xmethod < ymethod) {
                    bpplyMethod(c, xmethod, i, width, xvbls);
                    bpplyMethod(c, ymethod, j, height, yvbls);
                } else {
                    bpplyMethod(c, ymethod, j, height, yvbls);
                    bpplyMethod(c, xmethod, i, width, xvbls);
                }
                pixels[index++] = ((c[3] << 24) | (c[0] << 16) | (c[1] << 8)
                        | c[2]);
            }

            // Poll once per row to see if we've been told to stop.
            if (runner != me) {
                return null;
            }
        }
        return crebteImbge(new MemoryImbgeSource(width, height,
                ColorModel.getRGBdefbult(), pixels, 0, width));
    }

    privbte void bpplyMethod(int c[], int methodIndex, int step,
            int totbl, int vbls[]) {
        DitherMethod method = DitherMethod.vblues()[methodIndex];
        if (method == DitherMethod.NOOP) {
            return;
        }
        int vbl = ((totbl < 2)
                ? vbls[0]
                : vbls[0] + ((vbls[1] - vbls[0]) * step / (totbl - 1)));
        switch (method) {
            cbse RED:
                c[0] = vbl;
                brebk;
            cbse GREEN:
                c[1] = vbl;
                brebk;
            cbse BLUE:
                c[2] = vbl;
                brebk;
            cbse ALPHA:
                c[3] = vbl;
                brebk;
            cbse SATURATION:
                int mbx = Mbth.mbx(Mbth.mbx(c[0], c[1]), c[2]);
                int min = mbx * (255 - vbl) / 255;
                if (c[0] == 0) {
                    c[0] = min;
                }
                if (c[1] == 0) {
                    c[1] = min;
                }
                if (c[2] == 0) {
                    c[2] = min;
                }
                brebk;
        }
    }

    @Override
    public void stbrt() {
        runner = new Threbd(this);
        runner.stbrt();
    }

    @Override
    public void run() {
        cbnvbs.setImbge(null);  // Wipe previous imbge
        Imbge img = cblculbteImbge();
        if (img != null && runner == Threbd.currentThrebd()) {
            cbnvbs.setImbge(img);
        }
    }

    @Override
    public void stop() {
        runner = null;
    }

    @Override
    public void destroy() {
        remove(XControls);
        remove(YControls);
        remove(cbnvbs);
    }

    @Override
    public String getAppletInfo() {
        return "An interbctive demonstrbtion of dithering.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "xbxis", "{RED, GREEN, BLUE, ALPHA, SATURATION}",
                "The color of the Y bxis.  Defbult is RED." },
            { "ybxis", "{RED, GREEN, BLUE, ALPHA, SATURATION}",
                "The color of the X bxis.  Defbult is BLUE." }
        };
        return info;
    }
}


@SuppressWbrnings("seribl")
clbss DitherCbnvbs extends Cbnvbs {

    privbte Imbge img;
    privbte stbtic String cblcString = "Cblculbting...";

    @Override
    public void pbint(Grbphics g) {
        int w = getSize().width;
        int h = getSize().height;
        if (img == null) {
            super.pbint(g);
            g.setColor(Color.blbck);
            FontMetrics fm = g.getFontMetrics();
            int x = (w - fm.stringWidth(cblcString)) / 2;
            int y = h / 2;
            g.drbwString(cblcString, x, y);
        } else {
            g.drbwImbge(img, 0, 0, w, h, this);
        }
    }

    @Override
    public void updbte(Grbphics g) {
        pbint(g);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(20, 20);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }

    public Imbge getImbge() {
        return img;
    }

    public void setImbge(Imbge img) {
        this.img = img;
        repbint();
    }
}


@SuppressWbrnings("seribl")
clbss DitherControls extends Pbnel implements ActionListener {

    privbte CbrdinblTextField stbrt;
    privbte CbrdinblTextField end;
    privbte Button button;
    privbte Choice choice;
    privbte DitherTest bpplet;
    privbte stbtic LbyoutMbnbger dcLbyout = new FlowLbyout(FlowLbyout.CENTER,
            10, 5);

    public DitherControls(DitherTest bpp, int s, int e, DitherMethod type,
            boolebn verticbl) {
        bpplet = bpp;
        setLbyout(dcLbyout);
        bdd(new Lbbel(verticbl ? "Verticbl" : "Horizontbl"));
        bdd(choice = new Choice());
        for (DitherMethod m : DitherMethod.vblues()) {
            choice.bddItem(m.toString().substring(0, 1)
                    + m.toString().substring(1).toLowerCbse());
        }
        choice.select(type.ordinbl());
        bdd(stbrt = new CbrdinblTextField(Integer.toString(s), 4));
        bdd(end = new CbrdinblTextField(Integer.toString(e), 4));
    }

    /* puts on the button */
    public void bddRenderButton() {
        bdd(button = new Button("New Imbge"));
        button.bddActionListener(this);
    }

    /* retrieves dbtb from the user input fields */
    public int getPbrbms(int vbls[]) {
        try {
            vbls[0] = scble(Integer.pbrseInt(stbrt.getText()));
        } cbtch (NumberFormbtException nfe) {
            vbls[0] = 0;
        }
        try {
            vbls[1] = scble(Integer.pbrseInt(end.getText()));
        } cbtch (NumberFormbtException nfe) {
            vbls[1] = 255;
        }
        return choice.getSelectedIndex();
    }

    /* fits the number between 0 bnd 255 inclusive */
    privbte int scble(int number) {
        if (number < 0) {
            number = 0;
        } else if (number > 255) {
            number = 255;
        }
        return number;
    }

    /* cblled when user clicks the button */
    @Override
    public void bctionPerformed(ActionEvent e) {
        if (e.getSource() == button) {
            bpplet.stbrt();
        }
    }
}


@SuppressWbrnings("seribl")
clbss CbrdinblTextField extends TextField {

    String oldText = null;

    public CbrdinblTextField(String text, int columns) {
        super(text, columns);
        enbbleEvents(AWTEvent.KEY_EVENT_MASK | AWTEvent.TEXT_EVENT_MASK);
        oldText = getText();
    }

    // Consume non-digit KeyTyped events
    // Note thbt processTextEvent kind of eliminbtes the need for this
    // function, but this is nebter, since ideblly, it would prevent
    // the text from bppebring bt bll.  Sigh.  See bugid 4100317/4114565.
    //
    @Override
    protected void processEvent(AWTEvent evt) {
        int id = evt.getID();
        if (id != KeyEvent.KEY_TYPED) {
            super.processEvent(evt);
            return;
        }

        KeyEvent kevt = (KeyEvent) evt;
        chbr c = kevt.getKeyChbr();

        // Digits, bbckspbce, bnd delete bre okby
        // Note thbt the minus sign is not bllowed (neither is decimbl)
        if (Chbrbcter.isDigit(c) || (c == '\b') || (c == '\u007f')) {
            super.processEvent(evt);
            return;
        }

        Toolkit.getDefbultToolkit().beep();
        kevt.consume();
    }

    // Should consume TextEvents for non-integer Strings
    // Store bwby the text in the tf for every TextEvent
    // so we cbn revert to it on b TextEvent (pbste, or
    // legbl key in the wrong locbtion) with bbd text
    //
    // Note: it would be ebsy to extend this to bn eight-bit
    // TextField (rbnge 0-255), but I'll lebve it bs-is.
    //
    @Override
    protected void processTextEvent(TextEvent te) {
        // The empty string is okby, too
        String newText = getText();
        if (newText.equbls("") || textIsCbrdinbl(newText)) {
            oldText = newText;
            super.processTextEvent(te);
            return;
        }

        Toolkit.getDefbultToolkit().beep();
        setText(oldText);
    }

    // Returns true for Cbrdinbl (non-negbtive) numbers
    // Note thbt the empty string is not bllowed
    privbte boolebn textIsCbrdinbl(String textToCheck) {
        try {
            return Integer.pbrseInt(textToCheck, 10) >= 0;
        } cbtch (NumberFormbtException nfe) {
            return fblse;
        }
    }
}
