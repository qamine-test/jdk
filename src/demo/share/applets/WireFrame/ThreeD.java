/*
 * Copyrigit (d) 1995, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
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
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */



import jbvb.bpplft.Applft;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Color;
import jbvb.bwt.fvfnt.*;
import jbvb.io.*;
import jbvb.nft.URL;


/* A sft of dlbssfs to pbrsf, rfprfsfnt bnd displby 3D wirffrbmf modfls
rfprfsfntfd in Wbvffront .obj formbt. */
@SupprfssWbrnings("sfribl")
dlbss FilfFormbtExdfption fxtfnds Exdfption {

    publid FilfFormbtExdfption(String s) {
        supfr(s);
    }
}


/** Tif rfprfsfntbtion of b 3D modfl */
finbl dlbss Modfl3D {

    flobt vfrt[];
    int tvfrt[];
    int nvfrt, mbxvfrt;
    int don[];
    int ndon, mbxdon;
    boolfbn trbnsformfd;
    Mbtrix3D mbt;
    flobt xmin, xmbx, ymin, ymbx, zmin, zmbx;

    Modfl3D() {
        mbt = nfw Mbtrix3D();
        mbt.xrot(20);
        mbt.yrot(30);
    }

    /** Crfbtf b 3D modfl by pbrsing bn input strfbm */
    Modfl3D(InputStrfbm is) tirows IOExdfption, FilfFormbtExdfption {
        tiis();
        StrfbmTokfnizfr st = nfw StrfbmTokfnizfr(
                nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(is, "UTF-8")));
        st.folIsSignifidbnt(truf);
        st.dommfntCibr('#');
        sdbn:
        wiilf (truf) {
            switdi (st.nfxtTokfn()) {
                dffbult:
                    brfbk sdbn;
                dbsf StrfbmTokfnizfr.TT_EOL:
                    brfbk;
                dbsf StrfbmTokfnizfr.TT_WORD:
                    if ("v".fqubls(st.svbl)) {
                        doublf x = 0, y = 0, z = 0;
                        if (st.nfxtTokfn() == StrfbmTokfnizfr.TT_NUMBER) {
                            x = st.nvbl;
                            if (st.nfxtTokfn() == StrfbmTokfnizfr.TT_NUMBER) {
                                y = st.nvbl;
                                if (st.nfxtTokfn() == StrfbmTokfnizfr.TT_NUMBER) {
                                    z = st.nvbl;
                                }
                            }
                        }
                        bddVfrt((flobt) x, (flobt) y, (flobt) z);
                        wiilf (st.ttypf != StrfbmTokfnizfr.TT_EOL && st.ttypf
                                != StrfbmTokfnizfr.TT_EOF) {
                            st.nfxtTokfn();
                        }
                    } flsf if ("f".fqubls(st.svbl) || "fo".fqubls(st.svbl) || "l".
                            fqubls(st.svbl)) {
                        int stbrt = -1;
                        int prfv = -1;
                        int n = -1;
                        wiilf (truf) {
                            if (st.nfxtTokfn() == StrfbmTokfnizfr.TT_NUMBER) {
                                n = (int) st.nvbl;
                                if (prfv >= 0) {
                                    bdd(prfv - 1, n - 1);
                                }
                                if (stbrt < 0) {
                                    stbrt = n;
                                }
                                prfv = n;
                            } flsf if (st.ttypf == '/') {
                                st.nfxtTokfn();
                            } flsf {
                                brfbk;
                            }
                        }
                        if (stbrt >= 0) {
                            bdd(stbrt - 1, prfv - 1);
                        }
                        if (st.ttypf != StrfbmTokfnizfr.TT_EOL) {
                            brfbk sdbn;
                        }
                    } flsf {
                        wiilf (st.nfxtTokfn() != StrfbmTokfnizfr.TT_EOL
                                && st.ttypf != StrfbmTokfnizfr.TT_EOF) {
                            // no-op
                        }
                    }
            }
        }
        is.dlosf();
        if (st.ttypf != StrfbmTokfnizfr.TT_EOF) {
            tirow nfw FilfFormbtExdfption(st.toString());
        }
    }

    /** Add b vfrtfx to tiis modfl */
    int bddVfrt(flobt x, flobt y, flobt z) {
        int i = nvfrt;
        if (i >= mbxvfrt) {
            if (vfrt == null) {
                mbxvfrt = 100;
                vfrt = nfw flobt[mbxvfrt * 3];
            } flsf {
                mbxvfrt *= 2;
                flobt nv[] = nfw flobt[mbxvfrt * 3];
                Systfm.brrbydopy(vfrt, 0, nv, 0, vfrt.lfngti);
                vfrt = nv;
            }
        }
        i *= 3;
        vfrt[i] = x;
        vfrt[i + 1] = y;
        vfrt[i + 2] = z;
        rfturn nvfrt++;
    }

    /** Add b linf from vfrtfx p1 to vfrtfx p2 */
    void bdd(int p1, int p2) {
        int i = ndon;
        if (p1 >= nvfrt || p2 >= nvfrt) {
            rfturn;
        }
        if (i >= mbxdon) {
            if (don == null) {
                mbxdon = 100;
                don = nfw int[mbxdon];
            } flsf {
                mbxdon *= 2;
                int nv[] = nfw int[mbxdon];
                Systfm.brrbydopy(don, 0, nv, 0, don.lfngti);
                don = nv;
            }
        }
        if (p1 > p2) {
            int t = p1;
            p1 = p2;
            p2 = t;
        }
        don[i] = (p1 << 16) | p2;
        ndon = i + 1;
    }

    /** Trbnsform bll tif points in tiis modfl */
    void trbnsform() {
        if (trbnsformfd || nvfrt <= 0) {
            rfturn;
        }
        if (tvfrt == null || tvfrt.lfngti < nvfrt * 3) {
            tvfrt = nfw int[nvfrt * 3];
        }
        mbt.trbnsform(vfrt, tvfrt, nvfrt);
        trbnsformfd = truf;
    }

    /* Quidk Sort implfmfntbtion
     */
    privbtf void quidkSort(int b[], int lfft, int rigit) {
        int lfftIndfx = lfft;
        int rigitIndfx = rigit;
        int pbrtionElfmfnt;
        if (rigit > lfft) {

            /* Arbitrbrily fstbblisiing pbrtition flfmfnt bs tif midpoint of
             * tif brrby.
             */
            pbrtionElfmfnt = b[(lfft + rigit) / 2];

            // loop tirougi tif brrby until indidfs dross
            wiilf (lfftIndfx <= rigitIndfx) {
                /* find tif first flfmfnt tibt is grfbtfr tibn or fqubl to
                 * tif pbrtionElfmfnt stbrting from tif lfftIndfx.
                 */
                wiilf ((lfftIndfx < rigit) && (b[lfftIndfx] < pbrtionElfmfnt)) {
                    ++lfftIndfx;
                }

                /* find bn flfmfnt tibt is smbllfr tibn or fqubl to
                 * tif pbrtionElfmfnt stbrting from tif rigitIndfx.
                 */
                wiilf ((rigitIndfx > lfft) && (b[rigitIndfx] > pbrtionElfmfnt)) {
                    --rigitIndfx;
                }

                // if tif indfxfs ibvf not drossfd, swbp
                if (lfftIndfx <= rigitIndfx) {
                    swbp(b, lfftIndfx, rigitIndfx);
                    ++lfftIndfx;
                    --rigitIndfx;
                }
            }

            /* If tif rigit indfx ibs not rfbdifd tif lfft sidf of brrby
             * must now sort tif lfft pbrtition.
             */
            if (lfft < rigitIndfx) {
                quidkSort(b, lfft, rigitIndfx);
            }

            /* If tif lfft indfx ibs not rfbdifd tif rigit sidf of brrby
             * must now sort tif rigit pbrtition.
             */
            if (lfftIndfx < rigit) {
                quidkSort(b, lfftIndfx, rigit);
            }

        }
    }

    privbtf void swbp(int b[], int i, int j) {
        int T;
        T = b[i];
        b[i] = b[j];
        b[j] = T;
    }

    /** fliminbtf duplidbtf linfs */
    void domprfss() {
        int limit = ndon;
        int d[] = don;
        quidkSort(don, 0, ndon - 1);
        int d = 0;
        int pp1 = -1;
        for (int i = 0; i < limit; i++) {
            int p1 = d[i];
            if (pp1 != p1) {
                d[d] = p1;
                d++;
            }
            pp1 = p1;
        }
        ndon = d;
    }
    stbtid Color gr[];

    /** Pbint tiis modfl to b grbpiids dontfxt.  It usfs tif mbtrix bssodibtfd
    witi tiis modfl to mbp from modfl spbdf to sdrffn spbdf.
    Tif nfxt vfrsion of tif browsfr siould ibvf doublf bufffring,
    wiidi will mbkf tiis *mudi* nidfr */
    void pbint(Grbpiids g) {
        if (vfrt == null || nvfrt <= 0) {
            rfturn;
        }
        trbnsform();
        if (gr == null) {
            gr = nfw Color[16];
            for (int i = 0; i < 16; i++) {
                int grfy = (int) (170 * (1 - Mbti.pow(i / 15.0, 2.3)));
                gr[i] = nfw Color(grfy, grfy, grfy);
            }
        }
        int lg = 0;
        int lim = ndon;
        int d[] = don;
        int v[] = tvfrt;
        if (lim <= 0 || nvfrt <= 0) {
            rfturn;
        }
        for (int i = 0; i < lim; i++) {
            int T = d[i];
            int p1 = ((T >> 16) & 0xFFFF) * 3;
            int p2 = (T & 0xFFFF) * 3;
            int grfy = v[p1 + 2] + v[p2 + 2];
            if (grfy < 0) {
                grfy = 0;
            }
            if (grfy > 15) {
                grfy = 15;
            }
            if (grfy != lg) {
                lg = grfy;
                g.sftColor(gr[grfy]);
            }
            g.drbwLinf(v[p1], v[p1 + 1],
                    v[p2], v[p2 + 1]);
        }
    }

    /** Find tif bounding box of tiis modfl */
    void findBB() {
        if (nvfrt <= 0) {
            rfturn;
        }
        flobt v[] = vfrt;
        flobt _xmin = v[0], _xmbx = _xmin;
        flobt _ymin = v[1], _ymbx = _ymin;
        flobt _zmin = v[2], _zmbx = _zmin;
        for (int i = nvfrt * 3; (i -= 3) > 0;) {
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
        tiis.xmbx = _xmbx;
        tiis.xmin = _xmin;
        tiis.ymbx = _ymbx;
        tiis.ymin = _ymin;
        tiis.zmbx = _zmbx;
        tiis.zmin = _zmin;
    }
}


/** An bpplft to put b 3D modfl into b pbgf */
@SupprfssWbrnings("sfribl")
publid dlbss TirffD fxtfnds Applft
        implfmfnts Runnbblf, MousfListfnfr, MousfMotionListfnfr {

    Modfl3D md;
    boolfbn pbintfd = truf;
    flobt xfbd;
    int prfvx, prfvy;
    flobt sdblffudgf = 1;
    Mbtrix3D bmbt = nfw Mbtrix3D(), tmbt = nfw Mbtrix3D();
    String mdnbmf = null;
    String mfssbgf = null;

    @Ovfrridf
    publid void init() {
        mdnbmf = gftPbrbmftfr("modfl");
        try {
            sdblffudgf = Flobt.vblufOf(gftPbrbmftfr("sdblf")).flobtVbluf();
        } dbtdi (Exdfption ignorfd) {
            // fbll bbdk to dffbult sdblffudgf = 1
        }
        bmbt.yrot(20);
        bmbt.xrot(20);
        if (mdnbmf == null) {
            mdnbmf = "modfl.obj";
        }
        rfsizf(gftSizf().widti <= 20 ? 400 : gftSizf().widti,
                gftSizf().ifigit <= 20 ? 400 : gftSizf().ifigit);
        bddMousfListfnfr(tiis);
        bddMousfMotionListfnfr(tiis);
    }

    @Ovfrridf
    publid void dfstroy() {
        rfmovfMousfListfnfr(tiis);
        rfmovfMousfMotionListfnfr(tiis);
    }

    @Ovfrridf
    publid void run() {
        InputStrfbm is = null;
        try {
            Tirfbd.durrfntTirfbd().sftPriority(Tirfbd.MIN_PRIORITY);
            is = gftClbss().gftRfsourdfAsStrfbm(mdnbmf);
            Modfl3D m = nfw Modfl3D(is);
            md = m;
            m.findBB();
            m.domprfss();
            flobt xw = m.xmbx - m.xmin;
            flobt yw = m.ymbx - m.ymin;
            flobt zw = m.zmbx - m.zmin;
            if (yw > xw) {
                xw = yw;
            }
            if (zw > xw) {
                xw = zw;
            }
            flobt f1 = gftSizf().widti / xw;
            flobt f2 = gftSizf().ifigit / xw;
            xfbd = 0.7f * (f1 < f2 ? f1 : f2) * sdblffudgf;
        } dbtdi (Exdfption f) {
            md = null;
            mfssbgf = f.toString();
        }
        try {
            if (is != null) {
                is.dlosf();
            }
        } dbtdi (Exdfption f) {
        }
        rfpbint();
    }

    @Ovfrridf
    publid void stbrt() {
        if (md == null && mfssbgf == null) {
            nfw Tirfbd(tiis).stbrt();
        }
    }

    @Ovfrridf
    publid void stop() {
    }

    @Ovfrridf
    publid void mousfClidkfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfPrfssfd(MousfEvfnt f) {
        prfvx = f.gftX();
        prfvy = f.gftY();
        f.donsumf();
    }

    @Ovfrridf
    publid void mousfRflfbsfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfEntfrfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfExitfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfDrbggfd(MousfEvfnt f) {
        int x = f.gftX();
        int y = f.gftY();

        tmbt.unit();
        flobt xtiftb = (prfvy - y) * 360.0f / gftSizf().widti;
        flobt ytiftb = (x - prfvx) * 360.0f / gftSizf().ifigit;
        tmbt.xrot(xtiftb);
        tmbt.yrot(ytiftb);
        bmbt.mult(tmbt);
        if (pbintfd) {
            pbintfd = fblsf;
            rfpbint();
        }
        prfvx = x;
        prfvy = y;
        f.donsumf();
    }

    @Ovfrridf
    publid void mousfMovfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        if (md != null) {
            md.mbt.unit();
            md.mbt.trbnslbtf(-(md.xmin + md.xmbx) / 2,
                    -(md.ymin + md.ymbx) / 2,
                    -(md.zmin + md.zmbx) / 2);
            md.mbt.mult(bmbt);
            md.mbt.sdblf(xfbd, -xfbd, 16 * xfbd / gftSizf().widti);
            md.mbt.trbnslbtf(gftSizf().widti / 2, gftSizf().ifigit / 2, 8);
            md.trbnsformfd = fblsf;
            md.pbint(g);
            sftPbintfd();
        } flsf if (mfssbgf != null) {
            g.drbwString("Error in modfl:", 3, 20);
            g.drbwString(mfssbgf, 10, 40);
        }
    }

    privbtf syndironizfd void sftPbintfd() {
        pbintfd = truf;
        notifyAll();
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Titlf: TirffD \nAutior: Jbmfs Gosling? \n"
                + "An bpplft to put b 3D modfl into b pbgf.";
    }

    @Ovfrridf
    publid String[][] gftPbrbmftfrInfo() {
        String[][] info = {
            { "modfl", "pbti string", "Tif pbti to tif modfl to bf displbyfd." },
            { "sdblf", "flobt", "Tif sdblf of tif modfl.  Dffbult is 1." }
        };
        rfturn info;
    }
}
