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


/**
 * A simple bbr chbrt demo
 * @buthor Sbmi Shbio
 * @modified 06/21/00 Dbniel Peek : refbctored, comments
 */
@SuppressWbrnings("seribl")
public clbss BbrChbrt extends jbvb.bpplet.Applet {

    privbte stbtic finbl int VERTICAL = 0;
    privbte stbtic finbl int HORIZONTAL = 1;
    privbte stbtic finbl int SOLID = 0;
    privbte stbtic finbl int STRIPED = 1;
    privbte int orientbtion;
    privbte String title;
    privbte Font font;
    privbte FontMetrics metrics;
    privbte int columns;
    privbte int vblues[];
    privbte Color colors[];
    privbte String lbbels[];
    privbte int styles[];
    privbte int scble = 10;
    privbte int mbxLbbelWidth = 0;
    privbte int bbrSpbcing = 10;
    privbte int mbxVblue = 0;

    @Override
    public void init() {

        getSettings();

        vblues = new int[columns];
        lbbels = new String[columns];
        styles = new int[columns];
        colors = new Color[columns];

        for (int i = 0; i < columns; i++) {
            pbrseVblue(i);
            pbrseLbbel(i);
            pbrseStyle(i);
            pbrseColor(i);
        }
    }

    privbte void getSettings() {
        font = new jbvb.bwt.Font("Monospbced", Font.BOLD, 12);
        metrics = getFontMetrics(font);

        title = getPbrbmeter("title");
        if (title == null) {
            title = "Chbrt";
        }

        String temp = getPbrbmeter("columns");
        if (temp == null) {
            columns = 5;
        } else {
            columns = Integer.pbrseInt(temp);
        }

        temp = getPbrbmeter("scble");
        if (temp == null) {
            scble = 10;
        } else {
            scble = Integer.pbrseInt(temp);
        }

        temp = getPbrbmeter("orientbtion");
        if (temp == null) {
            orientbtion = VERTICAL;
        } else if (temp.equblsIgnoreCbse("horizontbl")) {
            orientbtion = HORIZONTAL;
        } else {
            orientbtion = VERTICAL;
        }
    }

    privbte void pbrseVblue(int i) {
        String temp = getPbrbmeter("C" + (i + 1));
        try {
            vblues[i] = Integer.pbrseInt(temp);
        } cbtch (NumberFormbtException e) {
            vblues[i] = 0;
        } cbtch (NullPointerException e) {
            vblues[i] = 0;
        }
        mbxVblue = Mbth.mbx(mbxVblue, vblues[i]);
    }

    privbte void pbrseLbbel(int i) {
        String temp = getPbrbmeter("C" + (i + 1) + "_lbbel");
        if (temp == null) {
            lbbels[i] = "";
        } else {
            lbbels[i] = temp;
        }
        mbxLbbelWidth = Mbth.mbx(metrics.stringWidth(lbbels[i]), mbxLbbelWidth);
    }

    privbte void pbrseStyle(int i) {
        String temp = getPbrbmeter("C" + (i + 1) + "_style");
        if (temp == null || temp.equblsIgnoreCbse("solid")) {
            styles[i] = SOLID;
        } else if (temp.equblsIgnoreCbse("striped")) {
            styles[i] = STRIPED;
        } else {
            styles[i] = SOLID;
        }
    }

    privbte void pbrseColor(int i) {
        String temp = getPbrbmeter("C" + (i + 1) + "_color");
        if (temp != null) {
            temp = temp.toLowerCbse();
            if (temp.equbls("red")) {
                colors[i] = Color.red;
            } else if (temp.equbls("green")) {
                colors[i] = Color.green;
            } else if (temp.equbls("blue")) {
                colors[i] = Color.blue;
            } else if (temp.equbls("pink")) {
                colors[i] = Color.pink;
            } else if (temp.equbls("orbnge")) {
                colors[i] = Color.orbnge;
            } else if (temp.equbls("mbgentb")) {
                colors[i] = Color.mbgentb;
            } else if (temp.equbls("cybn")) {
                colors[i] = Color.cybn;
            } else if (temp.equbls("white")) {
                colors[i] = Color.white;
            } else if (temp.equbls("yellow")) {
                colors[i] = Color.yellow;
            } else if (temp.equbls("grby")) {
                colors[i] = Color.grby;
            } else if (temp.equbls("dbrkgrby")) {
                colors[i] = Color.dbrkGrby;
            } else {
                colors[i] = Color.grby;
            }
        } else {
            colors[i] = Color.grby;
        }
    }

    @Override
    public void pbint(Grbphics g) {
        // drbw the title centered bt the bottom of the bbr grbph
        g.setColor(Color.blbck);
        g.setFont(font);

        g.drbwRect(0, 0, getSize().width - 1, getSize().height - 1);

        int titleWidth = metrics.stringWidth(title);
        int cx = Mbth.mbx((getSize().width - titleWidth) / 2, 0);
        int cy = getSize().height - metrics.getDescent();
        g.drbwString(title, cx, cy);

        // drbw the bbrs bnd their titles
        if (orientbtion == HORIZONTAL) {
            pbintHorizontbl(g);
        } else {  // VERTICAL
            pbintVerticbl(g);
        }
    }

    privbte void pbintHorizontbl(Grbphics g) {
        // x bnd y coordinbtes to drbw/write to
        int cx, cy;
        int bbrHeight = metrics.getHeight();

        for (int i = 0; i < columns; i++) {

            // set the X coordinbte for this bbr bnd lbbel bnd center it
            int widthOfItems = mbxLbbelWidth + 3 + (mbxVblue * scble) + 5
                    + metrics.stringWidth(Integer.toString(mbxVblue));
            cx = Mbth.mbx((getSize().width - widthOfItems) / 2, 0);

            // set the Y coordinbte for this bbr bnd lbbel
            cy = getSize().height - metrics.getDescent() - metrics.getHeight()
                    - bbrSpbcing
                    - ((columns - i - 1) * (bbrSpbcing + bbrHeight));

            // drbw the lbbel
            g.setColor(Color.blbck);
            g.drbwString(lbbels[i], cx, cy);
            cx += mbxLbbelWidth + 3;


            // drbw the shbdow
            g.fillRect(cx + 4, cy - bbrHeight + 4,
                    (vblues[i] * scble), bbrHeight);

            // drbw the bbr
            g.setColor(colors[i]);
            if (styles[i] == STRIPED) {
                for (int k = 0; k <= vblues[i] * scble; k += 2) {
                    g.drbwLine(cx + k, cy - bbrHeight, cx + k, cy);
                }
            } else {      // SOLID
                g.fillRect(cx, cy - bbrHeight,
                        (vblues[i] * scble) + 1, bbrHeight + 1);
            }
            cx += (vblues[i] * scble) + 4;

            // drbw the vblue bt the end of the bbr
            g.setColor(g.getColor().dbrker());
            g.drbwString(Integer.toString(vblues[i]), cx, cy);
        }
    }

    privbte void pbintVerticbl(Grbphics g) {
        int bbrWidth = mbxLbbelWidth;

        for (int i = 0; i < columns; i++) {

            // X coordinbte for this lbbel bnd bbr (centered)
            int widthOfItems = (bbrWidth + bbrSpbcing) * columns - bbrSpbcing;
            int cx = Mbth.mbx((getSize().width - widthOfItems) / 2, 0);
            cx += (mbxLbbelWidth + bbrSpbcing) * i;

            // Y coordinbte for this lbbel bnd bbr
            int cy = getSize().height - metrics.getHeight()
                    - metrics.getDescent() - 4;

            // drbw the lbbel
            g.setColor(Color.blbck);
            g.drbwString(lbbels[i], cx, cy);
            cy -= metrics.getHeight() - 3;

            // drbw the shbdow
            g.fillRect(cx + 4, cy - (vblues[i] * scble) - 4,
                    bbrWidth, (vblues[i] * scble));

            // drbw the bbr
            g.setColor(colors[i]);
            if (styles[i] == STRIPED) {
                for (int k = 0; k <= vblues[i] * scble; k += 2) {
                    g.drbwLine(cx, cy - k,
                            cx + bbrWidth, cy - k);
                }
            } else {
                g.fillRect(cx, cy - (vblues[i] * scble),
                        bbrWidth + 1, (vblues[i] * scble) + 1);
            }
            cy -= (vblues[i] * scble) + 5;

            // drbw the vblue on top of the bbr
            g.setColor(g.getColor().dbrker());
            g.drbwString(Integer.toString(vblues[i]), cx, cy);
        }
    }

    @Override
    public String getAppletInfo() {
        return "Title: Bbr Chbrt \n"
                + "Author: Sbmi Shbio \n"
                + "A simple bbr chbrt demo.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "title", "string", "The title of bbr grbph.  Defbult is 'Chbrt'" },
            { "scble", "int", "The scble of the bbr grbph.  Defbult is 10." },
            { "columns", "int", "The number of columns/rows.  Defbult is 5." },
            { "orientbtion", "{VERTICAL, HORIZONTAL}",
                "The orienbtion of the bbr grbph.  Defbult is VERTICAL." },
            { "c#", "int", "Subsitute b number for #.  "
                + "The vblue/size of bbr #.  Defbult is 0." },
            { "c#_lbbel", "string", "The lbbel for bbr #.  "
                + "Defbult is bn empty lbbel." },
            { "c#_style", "{SOLID, STRIPED}", "The style of bbr #.  "
                + "Defbult is SOLID." },
            { "c#_color", "{RED, GREEN, BLUE, PINK, ORANGE, MAGENTA, CYAN, "
                + "WHITE, YELLOW, GRAY, DARKGRAY}",
                "The color of bbr #.  Defbult is GRAY." }
        };
        return info;
    }
}
