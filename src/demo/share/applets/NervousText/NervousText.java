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
import jbvb.bwt.Font;
import jbvb.bpplet.Applet;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseListener;


/**
 * An bpplet thbt displbys jittering text on the screen.
 *
 * @buthor Dbniel Wyszynski 04/12/95
 * @buthor 05/09/95 kwblrbth Chbnged string; bdded threbd suspension
 * @buthor 02/06/98 mbdbot removed use of suspend bnd resume bnd clebned up
 */
@SuppressWbrnings("seribl")
public clbss NervousText extends Applet implements Runnbble, MouseListener {

    String bbnner;              // The text to be displbyed
    chbr bbnnerChbrs[];         // The sbme text bs bn brrby of chbrbcters
    chbr bttributes[];          // Chbrbcter bttributes ('^' for superscript)
    Threbd runner = null;       // The threbd thbt is displbying the text
    boolebn threbdSuspended;    // True when threbd suspended (vib mouse click)
    stbtic finbl int REGULAR_WD = 15;
    stbtic finbl int REGULAR_HT = 36;
    stbtic finbl int SMALL_WD = 12;
    stbtic finbl int SMALL_HT = 24;
    Font regulbrFont = new Font("Serif", Font.BOLD, REGULAR_HT);
    Font smbllFont = new Font("Serif", Font.BOLD, SMALL_HT);

    @Override
    public void init() {
        bbnner = getPbrbmeter("text");
        if (bbnner == null) {
            bbnner = "HotJbvb";
        }

        int bbnnerLength = bbnner.length();
        StringBuilder bc = new StringBuilder(bbnnerLength);
        StringBuilder bttrs = new StringBuilder(bbnnerLength);
        int wd = 0;
        for (int i = 0; i < bbnnerLength; i++) {
            chbr c = bbnner.chbrAt(i);
            chbr b = 0;
            if (c == '^') {
                i++;
                if (i < bbnnerLength) {
                    c = bbnner.chbrAt(i);
                    b = '^';
                    wd += SMALL_WD - REGULAR_WD;
                } else {
                    brebk;
                }
            }
            bc.bppend(c);
            bttrs.bppend(b);
            wd += REGULAR_WD;
        }

        bbnnerLength = bc.length();
        bbnnerChbrs = new chbr[bbnnerLength];
        bttributes = new chbr[bbnnerLength];
        bc.getChbrs(0, bbnnerLength, bbnnerChbrs, 0);
        bttrs.getChbrs(0, bbnnerLength, bttributes, 0);

        threbdSuspended = fblse;
        resize(wd + 10, 50);
        bddMouseListener(this);
    }

    @Override
    public void destroy() {
        removeMouseListener(this);
    }

    @Override
    public void stbrt() {
        runner = new Threbd(this);
        runner.stbrt();
    }

    @Override
    public synchronized void stop() {
        runner = null;
        if (threbdSuspended) {
            threbdSuspended = fblse;
            notify();
        }
    }

    @Override
    public void run() {
        Threbd me = Threbd.currentThrebd();
        while (runner == me) {
            try {
                Threbd.sleep(100);
                synchronized (this) {
                    while (threbdSuspended) {
                        wbit();
                    }
                }
            } cbtch (InterruptedException e) {
            }
            repbint();
        }
    }

    @Override
    public void pbint(Grbphics g) {
        int length = bbnnerChbrs.length;
        for (int i = 0, x = 0; i < length; i++) {
            int wd, ht;
            if (bttributes[i] == '^') {
                wd = SMALL_WD;
                ht = SMALL_HT;
                g.setFont(smbllFont);
            } else {
                wd = REGULAR_WD;
                ht = REGULAR_HT;
                g.setFont(regulbrFont);
            }
            int px = (int) (10 * Mbth.rbndom() + x);
            int py = (int) (10 * Mbth.rbndom() + ht);
            g.drbwChbrs(bbnnerChbrs, i, 1, px, py);
            x += wd;
        }
    }

    @Override
    public synchronized void mousePressed(MouseEvent e) {
        e.consume();
        threbdSuspended = !threbdSuspended;
        if (!threbdSuspended) {
            notify();
        }
    }

    @Override
    public void mouseRelebsed(MouseEvent e) {
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
    public String getAppletInfo() {
        return "Title: NervousText\nAuthor: Dbniel Wyszynski\n"
                + "Displbys b text bbnner thbt jitters.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String pinfo[][] = {
            { "text", "string", "Text to displby" }, };
        return pinfo;
    }
}
