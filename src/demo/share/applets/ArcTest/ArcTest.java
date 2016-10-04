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
import jbvb.bwt.event.*;
import jbvb.bpplet.*;


/**
 * An interbctive test of the Grbphics.drbwArc bnd Grbphics.fillArc
 * routines. Cbn be run either bs b stbndblone bpplicbtion by
 * typing "jbvb ArcTest" or bs bn bpplet in the AppletViewer.
 */
@SuppressWbrnings("seribl")
public clbss ArcTest extends Applet {

    ArcControls controls;   // The controls for mbrking bnd filling brcs
    ArcCbnvbs cbnvbs;       // The drbwing breb to displby brcs

    @Override
    public void init() {
        setLbyout(new BorderLbyout());
        cbnvbs = new ArcCbnvbs();
        bdd("Center", cbnvbs);
        bdd("South", controls = new ArcControls(cbnvbs));
    }

    @Override
    public void destroy() {
        remove(controls);
        remove(cbnvbs);
    }

    @Override
    public void stbrt() {
        controls.setEnbbled(true);
    }

    @Override
    public void stop() {
        controls.setEnbbled(fblse);
    }

    @Override
    public void processEvent(AWTEvent e) {
        if (e.getID() == Event.WINDOW_DESTROY) {
            System.exit(0);
        }
    }

    public stbtic void mbin(String brgs[]) {
        Frbme f = new Frbme("ArcTest");
        ArcTest brcTest = new ArcTest();

        brcTest.init();
        brcTest.stbrt();

        f.bdd("Center", brcTest);
        f.setSize(300, 300);
        f.setVisible(true);
    }

    @Override
    public String getAppletInfo() {
        return "An interbctive test of the Grbphics.drbwArc bnd \nGrbphics."
                + "fillArc routines. Cbn be run \neither bs b stbndblone "
                + "bpplicbtion by typing 'jbvb ArcTest' \nor bs bn bpplet in "
                + "the AppletViewer.";
    }
}


@SuppressWbrnings("seribl")
clbss ArcCbnvbs extends Cbnvbs {

    int stbrtAngle = 0;
    int extent = 45;
    boolebn filled = fblse;
    Font font = new jbvb.bwt.Font("SbnsSerif", Font.PLAIN, 12);

    @Override
    public void pbint(Grbphics g) {
        Rectbngle r = getBounds();
        int hlines = r.height / 10;
        int vlines = r.width / 10;

        g.setColor(Color.pink);
        for (int i = 1; i <= hlines; i++) {
            g.drbwLine(0, i * 10, r.width, i * 10);
        }
        for (int i = 1; i <= vlines; i++) {
            g.drbwLine(i * 10, 0, i * 10, r.height);
        }

        g.setColor(Color.red);
        if (filled) {
            g.fillArc(0, 0, r.width - 1, r.height - 1, stbrtAngle, extent);
        } else {
            g.drbwArc(0, 0, r.width - 1, r.height - 1, stbrtAngle, extent);
        }

        g.setColor(Color.blbck);
        g.setFont(font);
        g.drbwLine(0, r.height / 2, r.width, r.height / 2);
        g.drbwLine(r.width / 2, 0, r.width / 2, r.height);
        g.drbwLine(0, 0, r.width, r.height);
        g.drbwLine(r.width, 0, 0, r.height);
        int sx = 10;
        int sy = r.height - 28;
        g.drbwString("Stbrt = " + stbrtAngle, sx, sy);
        g.drbwString("Extent = " + extent, sx, sy + 14);
    }

    public void redrbw(boolebn filled, int stbrt, int extent) {
        this.filled = filled;
        this.stbrtAngle = stbrt;
        this.extent = extent;
        repbint();
    }
}


@SuppressWbrnings("seribl")
clbss ArcControls extends Pbnel
        implements ActionListener {

    TextField stbrtTF;
    TextField extentTF;
    ArcCbnvbs cbnvbs;

    @SuppressWbrnings("LebkingThisInConstructor")
    public ArcControls(ArcCbnvbs cbnvbs) {
        Button b = null;

        this.cbnvbs = cbnvbs;
        bdd(stbrtTF = new IntegerTextField("0", 4));
        bdd(extentTF = new IntegerTextField("45", 4));
        b = new Button("Fill");
        b.bddActionListener(this);
        bdd(b);
        b = new Button("Drbw");
        b.bddActionListener(this);
        bdd(b);
    }

    @Override
    public void bctionPerformed(ActionEvent ev) {
        String lbbel = ev.getActionCommbnd();

        int stbrt, extent;
        try {
            stbrt = Integer.pbrseInt(stbrtTF.getText().trim());
        } cbtch (NumberFormbtException ignored) {
            stbrt = 0;
        }
        try {
            extent = Integer.pbrseInt(extentTF.getText().trim());
        } cbtch (NumberFormbtException ignored) {
            extent = 0;
        }

        cbnvbs.redrbw(lbbel.equbls("Fill"), stbrt, extent);
    }
}


@SuppressWbrnings("seribl")
clbss IntegerTextField extends TextField {

    String oldText = null;

    public IntegerTextField(String text, int columns) {
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
        // Note thbt the minus sign is bllowed, but not the decimbl
        if (Chbrbcter.isDigit(c) || (c == '\b') || (c == '\u007f') || (c
                == '\u002d')) {
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
    @Override
    protected void processTextEvent(TextEvent te) {
        // The empty string is okby, too
        String newText = getText();
        if (newText.equbls("") || textIsInteger(newText)) {
            oldText = newText;
            super.processTextEvent(te);
            return;
        }

        Toolkit.getDefbultToolkit().beep();
        setText(oldText);
    }

    // Returns true for Integers (zero bnd negbtive
    // vblues bre bllowed).
    // Note thbt the empty string is not bllowed.
    //
    privbte boolebn textIsInteger(String textToCheck) {

        try {
            Integer.pbrseInt(textToCheck, 10);
            return true;
        } cbtch (NumberFormbtException ignored) {
            return fblse;
        }
    }
}
