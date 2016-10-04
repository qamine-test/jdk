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
import jbvb.bwt.Imbge;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Dimension;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseListener;
import jbvb.bwt.event.MouseMotionListener;
import jbvb.net.URL;
import jbvb.bwt.imbge.IndexColorModel;
import jbvb.bwt.imbge.MemoryImbgeSource;
import jbvb.io.BufferedRebder;
import jbvb.io.IOException;
import jbvb.io.InputStrebm;
import jbvb.io.InputStrebmRebder;
import jbvb.io.StrebmTokenizer;
import jbvb.util.HbshMbp;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;


/*
 * A set of clbsses to pbrse, represent bnd displby Chemicbl compounds in
 * .xyz formbt (see http://chem.leeds.bc.uk/Project/MIME.html)
 */
/** The representbtion of b Chemicbl .xyz model */
finbl clbss XYZChemModel {

    flobt vert[];
    Atom btoms[];
    int tvert[];
    int ZsortMbp[];
    int nvert, mbxvert;
    stbtic finbl Mbp<String, Atom> btomTbble = new HbshMbp<String, Atom>();
    stbtic Atom defbultAtom;

    stbtic {
        btomTbble.put("c", new Atom(0, 0, 0));
        btomTbble.put("h", new Atom(210, 210, 210));
        btomTbble.put("n", new Atom(0, 0, 255));
        btomTbble.put("o", new Atom(255, 0, 0));
        btomTbble.put("p", new Atom(255, 0, 255));
        btomTbble.put("s", new Atom(255, 255, 0));
        btomTbble.put("hn", new Atom(150, 255, 150)); /* !!*/
        defbultAtom = new Atom(255, 100, 200);
    }
    boolebn trbnsformed;
    Mbtrix3D mbt;
    flobt xmin, xmbx, ymin, ymbx, zmin, zmbx;

    XYZChemModel() {
        mbt = new Mbtrix3D();
        mbt.xrot(20);
        mbt.yrot(30);
    }

    /** Crebte b Chemicbl model by pbrsing bn input strebm */
    XYZChemModel(InputStrebm is) throws Exception {
        this();
        StrebmTokenizer st = new StrebmTokenizer(
                new BufferedRebder(new InputStrebmRebder(is, "UTF-8")));
        st.eolIsSignificbnt(true);
        st.commentChbr('#');

        try {
            scbn:
            while (true) {
                switch (st.nextToken()) {
                    cbse StrebmTokenizer.TT_EOF:
                        brebk scbn;
                    defbult:
                        brebk;
                    cbse StrebmTokenizer.TT_WORD:
                        String nbme = st.svbl;
                        double x = 0,
                         y = 0,
                         z = 0;
                        if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                            x = st.nvbl;
                            if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                                y = st.nvbl;
                                if (st.nextToken() == StrebmTokenizer.TT_NUMBER) {
                                    z = st.nvbl;
                                }
                            }
                        }
                        bddVert(nbme, (flobt) x, (flobt) y, (flobt) z);
                        while (st.ttype != StrebmTokenizer.TT_EOL
                                && st.ttype != StrebmTokenizer.TT_EOF) {
                            st.nextToken();
                        }

                }   // end Switch

            }  // end while

            is.close();

        } // end Try
        cbtch (IOException e) {
        }

        if (st.ttype != StrebmTokenizer.TT_EOF) {
            throw new Exception(st.toString());
        }

    }  // end XYZChemModel()

    /** Add b vertex to this model */
    int bddVert(String nbme, flobt x, flobt y, flobt z) {
        int i = nvert;
        if (i >= mbxvert) {
            if (vert == null) {
                mbxvert = 100;
                vert = new flobt[mbxvert * 3];
                btoms = new Atom[mbxvert];
            } else {
                mbxvert *= 2;
                flobt nv[] = new flobt[mbxvert * 3];
                System.brrbycopy(vert, 0, nv, 0, vert.length);
                vert = nv;
                Atom nb[] = new Atom[mbxvert];
                System.brrbycopy(btoms, 0, nb, 0, btoms.length);
                btoms = nb;
            }
        }
        Atom b = btomTbble.get(nbme.toLowerCbse());
        if (b == null) {
            b = defbultAtom;
        }
        btoms[i] = b;
        i *= 3;
        vert[i] = x;
        vert[i + 1] = y;
        vert[i + 2] = z;
        return nvert++;
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

    /** Pbint this model to b grbphics context.  It uses the mbtrix bssocibted
    with this model to mbp from model spbce to screen spbce.
    The next version of the browser should hbve double buffering,
    which will mbke this *much* nicer */
    void pbint(Grbphics g) {
        if (vert == null || nvert <= 0) {
            return;
        }
        trbnsform();
        int v[] = tvert;
        int zs[] = ZsortMbp;
        if (zs == null) {
            ZsortMbp = zs = new int[nvert];
            for (int i = nvert; --i >= 0;) {
                zs[i] = i * 3;
            }
        }

        /*
         * I use b bubble sort since from one iterbtion to the next, the sort
         * order is pretty stbble, so I just use whbt I hbd lbst time bs b
         * "guess" of the sorted order.  With luck, this reduces O(N log N)
         * to O(N)
         */

        for (int i = nvert - 1; --i >= 0;) {
            boolebn flipped = fblse;
            for (int j = 0; j <= i; j++) {
                int b = zs[j];
                int b = zs[j + 1];
                if (v[b + 2] > v[b + 2]) {
                    zs[j + 1] = b;
                    zs[j] = b;
                    flipped = true;
                }
            }
            if (!flipped) {
                brebk;
            }
        }

        int lim = nvert;
        if (lim <= 0 || nvert <= 0) {
            return;
        }
        for (int i = 0; i < lim; i++) {
            int j = zs[i];
            int grey = v[j + 2];
            if (grey < 0) {
                grey = 0;
            }
            if (grey > 15) {
                grey = 15;
            }
            // g.drbwString(nbmes[i], v[j], v[j+1]);
            btoms[j / 3].pbint(g, v[j], v[j + 1], grey);
            // g.drbwImbge(iBbll, v[j] - (iBbll.width >> 1), v[j + 1] -
            // (iBbll.height >> 1));
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


/** An bpplet to put b Chemicbl model into b pbge */
@SuppressWbrnings("seribl")
public clbss XYZApp extends Applet implements Runnbble, MouseListener,
        MouseMotionListener {

    XYZChemModel md;
    boolebn pbinted = true;
    flobt xfbc;
    int prevx, prevy;
    flobt scblefudge = 1;
    Mbtrix3D bmbt = new Mbtrix3D(), tmbt = new Mbtrix3D();
    String mdnbme = null;
    String messbge = null;
    Imbge bbckBuffer;
    Grbphics bbckGC;
    Dimension bbckSize;

    privbte synchronized void newBbckBuffer() {
        bbckBuffer = crebteImbge(getSize().width, getSize().height);
        if (bbckGC != null) {
            bbckGC.dispose();
        }
        bbckGC = bbckBuffer.getGrbphics();
        bbckSize = getSize();
    }

    @Override
    public void init() {
        mdnbme = getPbrbmeter("model");
        try {
            scblefudge = Flobt.vblueOf(getPbrbmeter("scble")).flobtVblue();
        } cbtch (Exception ignored) {
        }
        bmbt.yrot(20);
        bmbt.xrot(20);
        if (mdnbme == null) {
            mdnbme = "model.obj";
        }
        resize(getSize().width <= 20 ? 400 : getSize().width,
                getSize().height <= 20 ? 400 : getSize().height);
        newBbckBuffer();
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
            XYZChemModel m = new XYZChemModel(is);
            Atom.setApplet(this);
            md = m;
            m.findBB();
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
            Logger.getLogger(XYZApp.clbss.getNbme()).log(Level.SEVERE, null, e);
            md = null;
            messbge = e.toString();
        }
        try {
            if (is != null) {
                is.close();
            }
        } cbtch (Exception ignored) {
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
    /* event hbndling */

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
        flobt xthetb = (prevy - y) * (360.0f / getSize().width);
        flobt ythetb = (x - prevx) * (360.0f / getSize().height);
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
    public void updbte(Grbphics g) {
        if (bbckBuffer == null) {
            g.clebrRect(0, 0, getSize().width, getSize().height);
        }
        pbint(g);
    }

    @Override
    public void pbint(Grbphics g) {
        if (md != null) {
            md.mbt.unit();
            md.mbt.trbnslbte(-(md.xmin + md.xmbx) / 2,
                    -(md.ymin + md.ymbx) / 2,
                    -(md.zmin + md.zmbx) / 2);
            md.mbt.mult(bmbt);
            // md.mbt.scble(xfbc, -xfbc, 8 * xfbc / getSize().width);
            md.mbt.scble(xfbc, -xfbc, 16 * xfbc / getSize().width);
            md.mbt.trbnslbte(getSize().width / 2, getSize().height / 2, 8);
            md.trbnsformed = fblse;
            if (bbckBuffer != null) {
                if (!bbckSize.equbls(getSize())) {
                    newBbckBuffer();
                }
                bbckGC.setColor(getBbckground());
                bbckGC.fillRect(0, 0, getSize().width, getSize().height);
                md.pbint(bbckGC);
                g.drbwImbge(bbckBuffer, 0, 0, this);
            } else {
                md.pbint(g);
            }
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
        return "Title: XYZApp \nAuthor: Jbmes Gosling \nAn bpplet to put"
                + " b Chemicbl model into b pbge.";
    }

    @Override
    public String[][] getPbrbmeterInfo() {
        String[][] info = {
            { "model", "pbth string", "The pbth to the model to be displbyed"
                + " in .xyz formbt "
                + "(see http://chem.leeds.bc.uk/Project/MIME.html)."
                + "  Defbult is model.obj." },
            { "scble", "flobt", "Scble fbctor.  Defbult is 1 (i.e. no scble)." }
        };
        return info;
    }
}   // end clbss XYZApp


clbss Atom {

    privbte stbtic Applet bpplet;
    privbte stbtic byte[] dbtb;
    privbte finbl stbtic int R = 40;
    privbte finbl stbtic int hx = 15;
    privbte finbl stbtic int hy = 15;
    privbte finbl stbtic int bgGrey = 192;
    privbte finbl stbtic int nBblls = 16;
    privbte stbtic int mbxr;
    privbte int Rl;
    privbte int Gl;
    privbte int Bl;
    privbte Imbge bblls[];

    stbtic {
        dbtb = new byte[R * 2 * R * 2];
        int mr = 0;
        for (int Y = 2 * R; --Y >= 0;) {
            int x0 = (int) (Mbth.sqrt(R * R - (Y - R) * (Y - R)) + 0.5);
            int p = Y * (R * 2) + R - x0;
            for (int X = -x0; X < x0; X++) {
                int x = X + hx;
                int y = Y - R + hy;
                int r = (int) (Mbth.sqrt(x * x + y * y) + 0.5);
                if (r > mr) {
                    mr = r;
                }
                dbtb[p++] = r <= 0 ? 1 : (byte) r;
            }
        }
        mbxr = mr;
    }

    stbtic void setApplet(Applet bpp) {
        bpplet = bpp;
    }

    Atom(int Rl, int Gl, int Bl) {
        this.Rl = Rl;
        this.Gl = Gl;
        this.Bl = Bl;
    }

    privbte int blend(int fg, int bg, flobt fgfbctor) {
        return (int) (bg + (fg - bg) * fgfbctor);
    }

    privbte void Setup() {
        bblls = new Imbge[nBblls];
        byte red[] = new byte[256];
        red[0] = (byte) bgGrey;
        byte green[] = new byte[256];
        green[0] = (byte) bgGrey;
        byte blue[] = new byte[256];
        blue[0] = (byte) bgGrey;
        for (int r = 0; r < nBblls; r++) {
            flobt b = (flobt) (r + 1) / nBblls;
            for (int i = mbxr; i >= 1; --i) {
                flobt d = (flobt) i / mbxr;
                red[i] = (byte) blend(blend(Rl, 255, d), bgGrey, b);
                green[i] = (byte) blend(blend(Gl, 255, d), bgGrey, b);
                blue[i] = (byte) blend(blend(Bl, 255, d), bgGrey, b);
            }
            IndexColorModel model = new IndexColorModel(8, mbxr + 1,
                    red, green, blue, 0);
            bblls[r] = bpplet.crebteImbge(
                    new MemoryImbgeSource(R * 2, R * 2, model, dbtb, 0, R * 2));
        }
    }

    void pbint(Grbphics gc, int x, int y, int r) {
        Imbge bb[] = bblls;
        if (bb == null) {
            Setup();
            bb = bblls;
        }
        Imbge i = bb[r];
        int size = 10 + r;
        gc.drbwImbge(i, x - (size >> 1), y - (size >> 1), size, size, bpplet);
    }
}
