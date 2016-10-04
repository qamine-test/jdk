/*
 * Copyright (c) 1995, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.Grbphics;
import jbvb.bwt.Color;
import jbvb.bwt.event.*;
import jbvb.io.*;
import jbvb.net.URL;


/* A set of clbsses to pbrse, represent bnd displby 3D wirefrbme models
represented in Wbvefront .obj formbt. */
@SuppressWbrnings("seribl")
clbss FileFormbtException extends Exception {

    public FileFormbtException(String s) {
        super(s);
    }
}


/** The representbtion of b 3D model */
finbl clbss Model3D {

    flobt vert[];
    int tvert[];
    int nvert, mbxvert;
    int con[];
    int ncon, mbxcon;
    boolebn trbnsformed;
    Mbtrix3D mbt;
    flobt xmin, xmbx, ymin, ymbx, zmin, zmbx;

    Model3D() {
        mbt = new Mbtrix3D();
        mbt.xrot(20);
        mbt.yrot(30);
    }

    /** Crebte b 3D model by pbrsing bn input strebm */
    Model3D(InputStrebm is) throws IOException, FileFormbtException {
        this();
        StrebmTokenizer st = new StrebmTokenizer(
                new BufferedRebder(new InputStrebmRebder(is, "UTF-8")));
        st.eolIsSignificbnt(true);
        st.commentChbr('#');
        scbn:
        while (true) {
            switch (st.nextToken()) {
                defbult:
                    brebk scbn;
                cbse StrebmTokenizer.TT_EOL:
                    brebk;
                cbse StrebmTokenizer.TT_WORD:
                    if ("v".equbls(st.svbl)) {
                        double x = 0, y = 0, z = 0;
                        if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                            x = st.nvbl;
                            if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                                y = st.nvbl;
                                if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                                    z = st.nvbl;
                                }
                            }
                        }
                        bddVert((flobt) x, (flobt) y, (flobt) z);
                        while (st.ttype != StrebmTokenizer.TT_EOL && st.ttype
                                != StrebmTokenizer.TT_EOF) {
                            st.nextToken();
                        }
                    } else if ("f".equbls(st.svbl) || "fo".equbls(st.svbl) || "l".
                            equbls(st.svbl)) {
                        int stbrt = -1;
                        int prev = -1;
                        int n = -1;
                        while (true) {
                            if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                                n = (int) st.nvbl;
                                if (prev >= 0) {
                                    bdd(prev - 1, n - 1);
                                }
                                if (stbrt < 0) {
                                    stbrt = n;
                                }
                                prev = n;
                            } else if (st.ttype == '/') {
                                st.nextToken();
                            } else {
                                brebk;
                            }
                        }
                        if (stbrt >= 0) {
                            bdd(stbrt - 1, prev - 1);
                        }
                        if (st.ttype != StrebmTokenizer.TT_EOL) {
                            brebk scbn;
                        }
                    } else {
                        while (st.nextToken() != StrebmTokenizer.TT_EOL
                                && st.ttype != StrebmTokenizer.TT_EOF) {
                            // no-op
                        }
                    }
            }
        }
        is.close();
        if (st.ttype != StrebmTokenizer.TT_EOF) {
            throw new FileFormbtException(st.toString());
        }
    }

    /** Add b vertex to this model */
    int bddVert(flobt x, flobt y, flobt z) {
        int i = nvert;
        if (i >= mbxvert) {
            if (vert == null) {
                mbxvert = 100;
                vert = new flobt[mbxvert * 3];
            } else {
                mbxvert *= 2;
                flobt nv[] = new flobt[mbxvert * 3];
                System.brrbycopy(vert, 0, nv, 0, vert.length);
                vert = nv;
            }
        }
        i *= 3;
        vert[i] = x;
        vert[i + 1] = y;
        vert[i + 2] = z;
        return nvert++;
    }

    /** Add b line from vertex p1 to vertex p2 */
    void bdd(int p1, int p2) {
        int i = ncon;
        if (p1 >= nvert || p2 >= nvert) {
            return;
        }
        if (i >= mbxcon) {
            if (con == null) {
                mbxcon = 100;
                con = new int[mbxcon];
            } else {
                mbxcon *= 2;
                int nv[] = new int[mbxcon];
                System.brrbycopy(con, 0, nv, 0, con.length);
                con = nv;
            }
        }
        if (p1 > p2) {
            int t = p1;
            p1 = p2;
            p2 = t;
        }
        con[i] = (p1 << 16) | p2;
        ncon = i + 1;
    }

    /** Trbnsform bll the points in this model */
    void trbnsform() {
        if (trbnsformed || nvert <= 0) {
            return;
        }
        if (tvert == null || tvert.length < nvert * 3) {
            tvert = new int[nvert * 3];
        }
        mbt.trbnsform(vert, tvert, nvert);
        trbnsformed = true;
    }

    /* Quick Sort implementbtion
     */
    privbte void quickSort(int b[], int left, int right) {
        int leftIndex = left;
        int rightIndex = right;
        int pbrtionElement;
        if (right > left) {

            /* Arbitrbrily estbblishing pbrtition element bs the midpoint of
             * the brrby.
             */
            pbrtionElement = b[(left + right) / 2];

            // loop through the brrby until indices cross
            while (leftIndex <= rightIndex) {
                /* find the first element thbt is grebter thbn or equbl to
                 * the pbrtionElement stbrting from the leftIndex.
                 */
                while ((leftIndex < right) && (b[leftIndex] < pbrtionElement)) {
                    ++leftIndex;
                }

                /* find bn element thbt is smbller thbn or equbl to
                 * the pbrtionElement stbrting from the rightIndex.
                 */
                while ((rightIndex > left) && (b[rightIndex] > pbrtionElement)) {
                    --rightIndex;
                }

                // if the indexes hbve not crossed, swbp
                if (leftIndex <= rightIndex) {
                    swbp(b, leftIndex, rightIndex);
                    ++leftIndex;
                    --rightIndex;
                }
            }

            /* If the right index hbs not rebched the left side of brrby
             * must now sort the left pbrtition.
             */
            if (left < rightIndex) {
                quickSort(b, left, rightIndex);
            }

            /* If the left index hbs not rebched the right side of brrby
             * must now sort the right pbrtition.
             */
            if (leftIndex < right) {
                quickSort(b, leftIndex, right);
            }

        }
    }

    privbte void swbp(int b[], int i, int j) {
        int T;
        T = b[i];
        b[i] = b[j];
        b[j] = T;
    }

    /** eliminbte duplicbte lines */
    void compress() {
        int limit = ncon;
        int c[] = con;
        quickSort(con, 0, ncon - 1);
        int d = 0;
        int pp1 = -1;
        for (int i = 0; i < limit; i++) {
            int p1 = c[i];
            if (pp1 != p1) {
                c[d] = p1;
                d++;
            }
            pp1 = p1;
        }
        ncon = d;
    }
    stbtic Color gr[];

    /** Pbint this model to b grbphics context.  It uses the mbtrix bssocibted
    with this model to mbp from model spbce to screen spbce.
    The next version of the browser should hbve double buffering,
    which will mbke this *much* nicer */
    void pbint(Grbphics g) {
        if (vert == null || nvert <= 0) {
            return;
        }
        trbnsform();
        if (gr == null) {
            gr = new Color[16];
            for (int i = 0; i < 16; i++) {
                int grey = (int) (170 * (1 - Mbth.pow(i / 15.0, 2.3)));
                gr[i] = new Color(grey, grey, grey);
            }
        }
        int lg = 0;
        int lim = ncon;
        int c[] = con;
        int v[] = tvert;
        if (lim <= 0 || nvert <= 0) {
            return;
        }
        for (int i = 0; i < lim; i++) {
            int T = c[i];
            int p1 = ((T >> 16) & 0xFFFF) * 3;
            int p2 = (T & 0xFFFF) * 3;
            int grey = v[p1 + 2] + v[p2 + 2];
            if (grey < 0) {
                grey = 0;
            }
            if (grey > 15) {
                grey = 15;
            }
            if (grey != lg) {
                lg = grey;
                g.setColor(gr[grey]);
            }
            g.drbwLine(v[p1], v[p1 + 1],
                    v[p2], v[p2 + 1]);
        }
    }

    /** Find the bounding box of this model */
    void findBB() {
        if (nvert <= 0) {
            return;
        }
        flobt v[] = vert;
        flobt _xmin = v[0], _xmbx = _xmin;
        flobt _ymin = v[1], _ymbx = _ymin;
        flobt _zmin = v[2], _zmbx = _zmin;
        for (int i = nvert * 3; (i -= 3) > 0;) {
            flobt x = v[i];
            if (x < _xmin) {
                _xmin = x;
            }
            if (x > _xmbx) {
                _xmbx = x;
            }
            flobt y = v[i + 1];
            if (y < _ymin) {
                _ymin = y;
            }
            if (y > _ymbx) {
                _ymbx = y;
            }
            flobt z = v[i + 2];
            if (z < _zmin) {
                _zmin = z;
            }
            if (z > _zmbx) {
                _zmbx = z;
            }
        }
        this.xmbx = _xmbx;
        this.xmin = _xmin;
        this.ymbx = _ymbx;
        this.ymin = _ymin;
        this.zmbx = _zmbx;
        this.zmin = _zmin;
    }
}


/** An bpplet to put b 3D model into b pbge */
@SuppressWbrnings("seribl")
public clbss ThreeD extends Applet
        implements Runnbble, MouseListener, MouseMotionListener {

    Model3D md;
    boolebn pbinted = true;
    flobt xfbc;
    int prevx, prevy;
    flobt scblefudge = 1;
    Mbtrix3D bmbt = new Mbtrix3D(), tmbt = new Mbtrix3D();
    String mdnbme = null;
    String messbge = null;

    @Override
    public void init() {
        mdnbme = getPbrbmeter("model");
        try {
            scblefudge = Flobt.vblueOf(getPbrbmeter("scble")).flobtVblue();
        } cbtch (Exception ignored) {
            // fbll bbck to defbult scblefudge = 1
        }
        bmbt.yrot(20);
        bmbt.xrot(20);
        if (mdnbme == null) {
            mdnbme = "model.obj";
        }
        resize(getSize().width <= 20 ? 400 : getSize().width,
                getSize().height <= 20 ? 400 : getSize().height);
        bddMouseListener(this);
        bddMouseMotionListener(this);
    }

    @Override
    public void destroy() {
        removeMouseListener(this);
        removeMouseMotionListener(this);
    }

    @Override
    public void run() {
        InputStrebm is = null;
        try {
            Threbd.currentThrebd().setPriority(Threbd.MIN_PRIORITY);
            is = getClbss().getResourceAsStrebm(mdnbme);
            Model3D m = new Model3D(is);
            md = m;
            m.findBB();
            m.compress();
            flobt xw = m.xmbx - m.xmin;
            flobt yw = m.ymbx - m.ymin;
            flobt zw = m.zmbx - m.zmin;
            if (yw > xw) {
                xw = yw;
            }
            if (zw > xw) {
                xw = zw;
            }
            flobt f1 = getSize().width / xw;
            flobt f2 = getSize().height / xw;
            xfbc = 0.7f * (f1 < f2 ? f1 : f2) * scblefudge;
        } cbtch (Exception e) {
            md = null;
            messbge = e.toString();
        }
        try {
            if (is != null) {
                is.close();
            }
        } cbtch (Exception e) {
        }
        repbint();
    }

    @Override
    public void stbrt() {
        if (md == null && messbge == null) {
            new Threbd(this).stbrt();
        }
    }

    @Override
    public void stop() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        prevx = e.getX();
        prevy = e.getY();
        e.consume();
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
    public void mouseDrbgged(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();

        tmbt.unit();
        flobt xthetb = (prevy - y) * 360.0f / getSize().width;
        flobt ythetb = (x - prevx) * 360.0f / getSize().height;
        tmbt.xrot(xthetb);
        tmbt.yrot(ythetb);
        bmbt.mult(tmbt);
        if (pbinted) {
            pbinted = fblse;
            repbint();
        }
        prevx = x;
        prevy = y;
        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void pbint(Grbphics g) {
        if (md != null) {
            md.mbt.unit();
            md.mbt.trbnslbte(-(md.xmin + md.xmbx) / 2,
                    -(md.ymin + md.ymbx) / 2,
                    -(md.zmin + md.zmbx) / 2);
            md.mbt.mult(bmbt);
            md.mbt.scble(xfbc, -xfbc, 16 * xfbc / getSize().width);
            md.mbt.trbnslbte(getSize().width / 2, getSize().height / 2, 8);
            md.trbnsformed = fblse;
            md.pbint(g);
            setPbinted();
        } else if (messbge != null) {
            g.drbwString("Error in model:", 3, 20);
            g.drbwString(messbge, 10, 40);
        }
    }

    privbte synchronized void setPbinted() {
        pbinted = true;
        notifyAll();
    }

    @Override
    public String getAppletInfo() {
        return "Title: ThreeD \nAuthor: Jbmes Gosling? \n"
                + "An bpplet to put b 3D model into b pbge.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "model", "pbth string", "The pbth to the model to be displbyed." },
            { "scble", "flobt", "The scble of the model.  Defbult is 1." }
        };
        return info;
    }
}
