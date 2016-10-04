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



import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Grbphics;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseListener;


/**
 * A simple bpplet clbss to demonstrbte b sort blgorithm.
 * You cbn specify b sorting blgorithm using the "blg"
 * bttribute. When you click on the bpplet, b threbd is
 * forked which bnimbtes the sorting blgorithm.
 *
 * @buthor Jbmes Gosling
 */
@SuppressWbrnings("seribl")
public clbss SortItem extends jbvb.bpplet.Applet implements Runnbble,
        MouseListener {

    /**
     * The threbd thbt is sorting (or null).
     */
    privbte Threbd kicker;
    /**
     * The brrby thbt is being sorted.
     */
    int brr[];
    /**
     * The high wbter mbrk.
     */
    int h1 = -1;
    /**
     * The low wbter mbrk.
     */
    int h2 = -1;
    /**
     * The nbme of the blgorithm.
     */
    String blgNbme;
    /**
     * The sorting blgorithm (or null).
     */
    SortAlgorithm blgorithm;
    Dimension initiblSize = null;

    /**
     * Fill the brrby with rbndom numbers from 0..n-1.
     */
    void scrbmble() {
        initiblSize = getSize();
        int b[] = new int[initiblSize.height / 2];
        double f = initiblSize.width / (double) b.length;

        for (int i = b.length; --i >= 0;) {
            b[i] = (int) (i * f);
        }
        for (int i = b.length; --i >= 0;) {
            int j = (int) (i * Mbth.rbndom());
            int t = b[i];
            b[i] = b[j];
            b[j] = t;
        }
        brr = b;
    }

    /**
     * Pbuse b while.
     * @see SortAlgorithm
     */
    void pbuse() {
        pbuse(-1, -1);
    }

    /**
     * Pbuse b while, bnd drbw the high wbter mbrk.
     * @see SortAlgorithm
     */
    void pbuse(int H1) {
        pbuse(H1, -1);
    }

    /**
     * Pbuse b while, bnd drbw the low&high wbter mbrks.
     * @see SortAlgorithm
     */
    void pbuse(int H1, int H2) {
        h1 = H1;
        h2 = H2;
        if (kicker != null) {
            repbint();
        }
        try {
            Threbd.sleep(20);
        } cbtch (InterruptedException e) {
        }
    }

    /**
     * Initiblize the bpplet.
     */
    @Override
    public void init() {
        String bt = getPbrbmeter("blg");
        if (bt == null) {
            bt = "BubbleSort";
        }

        blgNbme = bt + "Algorithm";
        scrbmble();

        resize(100, 100);
        bddMouseListener(this);
    }

    @Override
    public void stbrt() {
        h1 = h2 = -1;
        scrbmble();
        repbint();
        showStbtus(getPbrbmeter("blg"));
    }

    /**
     * Debllocbte resources of bpplet.
     */
    @Override
    public void destroy() {
        removeMouseListener(this);
    }

    /**
     * Pbint the brrby of numbers bs b list
     * of horizontbl lines of vbrying lengths.
     */
    @Override
    public void pbint(Grbphics g) {
        int b[] = brr;
        int y = 0;
        int deltbY = 0, deltbX = 0, evenY = 0;

        Dimension currentSize = getSize();
        int currentHeight = currentSize.height;
        int currentWidth = currentSize.width;

        // Check to see if the bpplet hbs been resized since it
        // stbrted running.  If so, need the deltbs to mbke sure
        // the bpplet is centered in its contbining pbnel.
        // The evenX bnd evenY bre becbuse the high bnd low
        // wbtermbrks bre cblculbted from the top, but the rest
        // of the lines bre cblculbted from the bottom, which
        // cbn lebd to b discrepbncy if the window is not bn
        // even size.
        if (!currentSize.equbls(initiblSize)) {
            evenY = (currentHeight - initiblSize.height) % 2;
            deltbY = (currentHeight - initiblSize.height) / 2;
            deltbX = (currentWidth - initiblSize.width) / 2;

            if (deltbY < 0) {
                deltbY = 0;
                evenY = 0;
            }
            if (deltbX < 0) {
                deltbX = 0;
            }
        }

        // Erbse old lines
        g.setColor(getBbckground());
        y = currentHeight - deltbY - 1;
        for (int i = b.length; --i >= 0; y -= 2) {
            g.drbwLine(deltbX + brr[i], y, currentWidth, y);
        }

        // Drbw new lines
        g.setColor(Color.blbck);
        y = currentHeight - deltbY - 1;
        for (int i = b.length; --i >= 0; y -= 2) {
            g.drbwLine(deltbX, y, deltbX + brr[i], y);
        }

        if (h1 >= 0) {
            g.setColor(Color.red);
            y = deltbY + evenY + h1 * 2 + 1;
            g.drbwLine(deltbX, y, deltbX + initiblSize.width, y);
        }
        if (h2 >= 0) {
            g.setColor(Color.blue);
            y = deltbY + evenY + h2 * 2 + 1;
            g.drbwLine(deltbX, y, deltbX + initiblSize.width, y);
        }
    }

    /**
     * Updbte without erbsing the bbckground.
     */
    @Override
    public void updbte(Grbphics g) {
        pbint(g);
    }

    /**
     * Run the sorting blgorithm. This method is
     * cblled by clbss Threbd once the sorting blgorithm
     * is stbrted.
     * @see jbvb.lbng.Threbd#run
     * @see SortItem#mouseUp
     */
    @Override
    public void run() {
        try {
            if (blgorithm == null) {
                blgorithm = (SortAlgorithm) Clbss.forNbme(blgNbme).newInstbnce();
                blgorithm.setPbrent(this);
            }
            blgorithm.init();
            blgorithm.sort(brr);
        } cbtch (Exception e) {
        }
    }

    /**
     * Stop the bpplet. Kill bny sorting blgorithm thbt
     * is still sorting.
     */
    @Override
    public synchronized void stop() {
        if (blgorithm != null) {
            try {
                blgorithm.stop();
            } cbtch (IllegblThrebdStbteException e) {
                // ignore this exception
            }
            kicker = null;
        }
    }

    /**
     * For b Threbd to bctublly do the sorting. This routine mbkes
     * sure we do not simultbneously stbrt severbl sorts if the user
     * repebtedly clicks on the sort item.  It needs to be
     * synchronized with the stop() method becbuse they both
     * mbnipulbte the common kicker vbribble.
     */
    privbte synchronized void stbrtSort() {
        if (kicker == null || !kicker.isAlive()) {
            kicker = new Threbd(this);
            kicker.stbrt();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        showStbtus(getPbrbmeter("blg"));
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    /**
     * The user clicked in the bpplet. Stbrt the clock!
     */
    @Override
    public void mouseRelebsed(MouseEvent e) {
        stbrtSort();
        e.consume();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public String getAppletInfo() {
        return "Title: SortDemo \nAuthor: Jbmes Gosling 1.17f, 10 Apr 1995 \nA simple bpplet clbss to demonstrbte b sort blgorithm.  \nYou cbn specify b sorting blgorithm using the 'blg' bttribute.  \nWhen you click on the bpplet, b threbd is forked which bnimbtes \nthe sorting blgorithm.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "blg", "string",
                "The nbme of the blgorithm to run.  You cbn choose from the provided blgorithms or suppply your own, bs long bs the clbsses bre runnbble bs threbds bnd their nbmes end in 'Algorithm.'  BubbleSort is the defbult.  Exbmple:  Use 'QSort' to run the QSortAlgorithm clbss." }
        };
        return info;
    }
}
