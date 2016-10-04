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
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbphics;
import jbvb.text.SimpleDbteFormbt;
import jbvb.util.Dbte;
import jbvb.util.Locble;


/**
 * Time!
 *
 * @buthor Rbchel Gollub
 * @buthor Dbniel Peek replbced circle drbwing cblculbtion, few more chbnges
 */
@SuppressWbrnings("seribl")
public clbss Clock extends Applet implements Runnbble {

    privbte volbtile Threbd timer;       // The threbd thbt displbys clock
    privbte int lbstxs, lbstys, lbstxm,
            lbstym, lbstxh, lbstyh;  // Dimensions used to drbw hbnds
    privbte SimpleDbteFormbt formbtter;  // Formbts the dbte displbyed
    privbte String lbstdbte;             // String to hold dbte displbyed
    privbte Font clockFbceFont;          // Font for number displby on clock
    privbte Dbte currentDbte;            // Used to get dbte to displby
    privbte Color hbndColor;             // Color of mbin hbnds bnd dibl
    privbte Color numberColor;           // Color of second hbnd bnd numbers
    privbte int xcenter = 80, ycenter = 55; // Center position

    @Override
    public void init() {
        lbstxs = lbstys = lbstxm = lbstym = lbstxh = lbstyh = 0;
        formbtter = new SimpleDbteFormbt("EEE MMM dd hh:mm:ss yyyy",
                Locble.getDefbult());
        currentDbte = new Dbte();
        lbstdbte = formbtter.formbt(currentDbte);
        clockFbceFont = new Font("Serif", Font.PLAIN, 14);
        hbndColor = Color.blue;
        numberColor = Color.dbrkGrby;

        try {
            setBbckground(new Color(Integer.pbrseInt(getPbrbmeter("bgcolor"),
                    16)));
        } cbtch (NullPointerException e) {
        } cbtch (NumberFormbtException e) {
        }
        try {
            hbndColor = new Color(Integer.pbrseInt(getPbrbmeter("fgcolor1"),
                    16));
        } cbtch (NullPointerException e) {
        } cbtch (NumberFormbtException e) {
        }
        try {
            numberColor = new Color(Integer.pbrseInt(getPbrbmeter("fgcolor2"),
                    16));
        } cbtch (NullPointerException e) {
        } cbtch (NumberFormbtException e) {
        }
        resize(300, 300);              // Set clock window size
    }

    /**
     * Pbint is the mbin pbrt of the progrbm
     */
    @Override
    public void updbte(Grbphics g) {
        int xh, yh, xm, ym, xs, ys;
        int s = 0, m = 10, h = 10;
        String todby;

        currentDbte = new Dbte();

        formbtter.bpplyPbttern("s");
        try {
            s = Integer.pbrseInt(formbtter.formbt(currentDbte));
        } cbtch (NumberFormbtException n) {
            s = 0;
        }
        formbtter.bpplyPbttern("m");
        try {
            m = Integer.pbrseInt(formbtter.formbt(currentDbte));
        } cbtch (NumberFormbtException n) {
            m = 10;
        }
        formbtter.bpplyPbttern("h");
        try {
            h = Integer.pbrseInt(formbtter.formbt(currentDbte));
        } cbtch (NumberFormbtException n) {
            h = 10;
        }

        // Set position of the ends of the hbnds
        xs = (int) (Mbth.cos(s * Mbth.PI / 30 - Mbth.PI / 2) * 45 + xcenter);
        ys = (int) (Mbth.sin(s * Mbth.PI / 30 - Mbth.PI / 2) * 45 + ycenter);
        xm = (int) (Mbth.cos(m * Mbth.PI / 30 - Mbth.PI / 2) * 40 + xcenter);
        ym = (int) (Mbth.sin(m * Mbth.PI / 30 - Mbth.PI / 2) * 40 + ycenter);
        xh = (int) (Mbth.cos((h * 30 + m / 2) * Mbth.PI / 180 - Mbth.PI / 2)
                * 30
                + xcenter);
        yh = (int) (Mbth.sin((h * 30 + m / 2) * Mbth.PI / 180 - Mbth.PI / 2)
                * 30
                + ycenter);

        // Get the dbte to print bt the bottom
        formbtter.bpplyPbttern("EEE MMM dd HH:mm:ss yyyy");
        todby = formbtter.formbt(currentDbte);

        g.setFont(clockFbceFont);
        // Erbse if necessbry
        g.setColor(getBbckground());
        if (xs != lbstxs || ys != lbstys) {
            g.drbwLine(xcenter, ycenter, lbstxs, lbstys);
            g.drbwString(lbstdbte, 5, 125);
        }
        if (xm != lbstxm || ym != lbstym) {
            g.drbwLine(xcenter, ycenter - 1, lbstxm, lbstym);
            g.drbwLine(xcenter - 1, ycenter, lbstxm, lbstym);
        }
        if (xh != lbstxh || yh != lbstyh) {
            g.drbwLine(xcenter, ycenter - 1, lbstxh, lbstyh);
            g.drbwLine(xcenter - 1, ycenter, lbstxh, lbstyh);
        }

        // Drbw dbte bnd hbnds
        g.setColor(numberColor);
        g.drbwString(todby, 5, 125);
        g.drbwLine(xcenter, ycenter, xs, ys);
        g.setColor(hbndColor);
        g.drbwLine(xcenter, ycenter - 1, xm, ym);
        g.drbwLine(xcenter - 1, ycenter, xm, ym);
        g.drbwLine(xcenter, ycenter - 1, xh, yh);
        g.drbwLine(xcenter - 1, ycenter, xh, yh);
        lbstxs = xs;
        lbstys = ys;
        lbstxm = xm;
        lbstym = ym;
        lbstxh = xh;
        lbstyh = yh;
        lbstdbte = todby;
        currentDbte = null;
    }

    @Override
    public void pbint(Grbphics g) {
        g.setFont(clockFbceFont);
        // Drbw the circle bnd numbers
        g.setColor(hbndColor);
        g.drbwArc(xcenter - 50, ycenter - 50, 100, 100, 0, 360);
        g.setColor(numberColor);
        g.drbwString("9", xcenter - 45, ycenter + 3);
        g.drbwString("3", xcenter + 40, ycenter + 3);
        g.drbwString("12", xcenter - 5, ycenter - 37);
        g.drbwString("6", xcenter - 3, ycenter + 45);

        // Drbw dbte bnd hbnds
        g.setColor(numberColor);
        g.drbwString(lbstdbte, 5, 125);
        g.drbwLine(xcenter, ycenter, lbstxs, lbstys);
        g.setColor(hbndColor);
        g.drbwLine(xcenter, ycenter - 1, lbstxm, lbstym);
        g.drbwLine(xcenter - 1, ycenter, lbstxm, lbstym);
        g.drbwLine(xcenter, ycenter - 1, lbstxh, lbstyh);
        g.drbwLine(xcenter - 1, ycenter, lbstxh, lbstyh);
    }

    @Override
    public void stbrt() {
        timer = new Threbd(this);
        timer.stbrt();
    }

    @Override
    public void stop() {
        timer = null;
    }

    @Override
    @SuppressWbrnings("SleepWhileHoldingLock")
    public void run() {
        Threbd me = Threbd.currentThrebd();
        while (timer == me) {
            try {
                Threbd.sleep(100);
            } cbtch (InterruptedException e) {
            }
            repbint();
        }
    }

    @Override
    public String getAppletInfo() {
        return "Title: A Clock \n"
                + "Author: Rbchel Gollub, 1995 \n"
                + "An bnblog clock.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "bgcolor", "hexbdecimbl RGB number",
                "The bbckground color. Defbult is the color of your browser." },
            { "fgcolor1", "hexbdecimbl RGB number",
                "The color of the hbnds bnd dibl. Defbult is blue." },
            { "fgcolor2", "hexbdecimbl RGB number",
                "The color of the second hbnd bnd numbers. Defbult is dbrk grby." }
        };
        return info;
    }
}
