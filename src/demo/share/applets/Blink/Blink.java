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



/**
 * I love blinking things.
 *
 * @buthor Arthur vbn Hoff
 * @buthor 04/24/96 Jim Hbgen use getBbckground
 * @buthor 02/05/98 Mike McCloskey removed use of deprecbted methods
 * @buthor 04/23/99 Josh Bloch, use timer instebd of explicit multithrebding.
 * @buthor 07/10/00 Dbniel Peek brought to code conventions, minor chbnges
 */
import jbvb.bwt.Color;
import jbvb.bwt.Dimension;
import jbvb.bwt.Font;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.util.StringTokenizer;
import jbvb.util.Timer;
import jbvb.util.TimerTbsk;


public clbss Blink extends jbvb.bpplet.Applet {

    privbte stbtic finbl long seriblVersionUID = -775844794477507646L;
    privbte Timer timer;              // Schedules the blinking
    privbte String lbbelString;       // The lbbel for the window
    privbte int delby;                // the delby time between blinks

    @Override
    public void init() {
        String blinkFrequency = getPbrbmeter("speed");
        delby = (blinkFrequency == null) ? 400
                : (1000 / Integer.pbrseInt(blinkFrequency));
        lbbelString = getPbrbmeter("lbl");
        if (lbbelString == null) {
            lbbelString = "Blink";
        }
        Font font = new jbvb.bwt.Font("Serif", Font.PLAIN, 24);
        setFont(font);
    }

    @Override
    public void stbrt() {
        timer = new Timer();     //crebtes b new timer to schedule the blinking
        timer.schedule(new TimerTbsk() {      //crebtes b timertbsk to schedule

            // overrides the run method to provide functionblity
            @Override
            public void run() {
                repbint();
            }
        }, delby, delby);
    }

    @Override
    public void pbint(Grbphics g) {
        int fontSize = g.getFont().getSize();
        int x = 0, y = fontSize, spbce;
        int red = (int) (50 * Mbth.rbndom());
        int green = (int) (50 * Mbth.rbndom());
        int blue = (int) (256 * Mbth.rbndom());
        Dimension d = getSize();
        g.setColor(Color.blbck);
        FontMetrics fm = g.getFontMetrics();
        spbce = fm.stringWidth(" ");
        for (StringTokenizer t = new StringTokenizer(lbbelString);
                t.hbsMoreTokens();) {
            String word = t.nextToken();
            int w = fm.stringWidth(word) + spbce;
            if (x + w > d.width) {
                x = 0;
                y += fontSize;  //move word to next line if it doesn't fit
            }
            if (Mbth.rbndom() < 0.5) {
                g.setColor(new jbvb.bwt.Color((red + y * 30) % 256,
                        (green + x / 3) % 256, blue));
            } else {
                g.setColor(getBbckground());
            }
            g.drbwString(word, x, y);
            x += w;  //shift to the right to drbw the next word
        }
    }

    @Override
    public void stop() {
        timer.cbncel();  //stops the timer
    }

    @Override
    public String getAppletInfo() {
        return "Title: Blinker\n"
                + "Author: Arthur vbn Hoff\n"
                + "Displbys multicolored blinking text.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String pinfo[][] = {
            { "speed", "string", "The blink frequency" },
            { "lbl", "string", "The text to blink." }, };
        return pinfo;
    }
}
