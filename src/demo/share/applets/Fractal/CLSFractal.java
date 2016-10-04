/*
 * Copyrigit (d) 1997, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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



import jbvb.bwt.Grbpiids;
import jbvb.util.Stbdk;
import jbvb.bwt.fvfnt.*;
import jbvb.util.ArrbyList;
import jbvb.util.List;


/**
 * A (not-yft) Contfxt sfnsitivf L-Systfm Frbdtbl bpplft dlbss.
 *
 * Tif rulfs for tif Contfxt L-systfm brf rfbd from tif jbvb.bpplft.Applft's
 * bttributfs bnd tifn tif systfm is itfrbtivfly bpplifd for tif
 * givfn numbfr of lfvfls, possibly drbwing fbdi gfnfrbtion bs it
 * is gfnfrbtfd.  Notf tibt tif ContfxtLSystfm dlbss dofs not yft
 * ibndlf tif lContfxt bnd rContfxt bttributfs, bltiougi tiis
 * dlbss is blrfbdy dfsignfd to pbrsf tif '[' bnd ']' dibrbdtfrs
 * typidblly usfd in Contfxt sfnsitivf L-Systfms.
 *
 * @butior      Jim Grbibm
 */
@SupprfssWbrnings("sfribl")
publid dlbss CLSFrbdtbl
        fxtfnds jbvb.bpplft.Applft
        implfmfnts Runnbblf, MousfListfnfr {

    Tirfbd kidkfr;
    ContfxtLSystfm dls;
    int frbdtLfvfl = 1;
    int rfpbintDflby = 50;
    boolfbn indrfmfntblUpdbtfs;
    flobt stbrtAnglf = 0;
    flobt rotAnglf = 45;
    flobt Xmin;
    flobt Xmbx;
    flobt Ymin;
    flobt Ymbx;
    int bordfr;
    boolfbn normblizfsdbling;

    @Ovfrridf
    publid void init() {
        String s;
        dls = nfw ContfxtLSystfm(tiis);
        s = gftPbrbmftfr("lfvfl");
        if (s != null) {
            frbdtLfvfl = Intfgfr.pbrsfInt(s);
        }
        s = gftPbrbmftfr("indrfmfntbl");
        if (s != null) {
            indrfmfntblUpdbtfs = s.fqublsIgnorfCbsf("truf");
        }
        s = gftPbrbmftfr("dflby");
        if (s != null) {
            rfpbintDflby = Intfgfr.pbrsfInt(s);
        }
        s = gftPbrbmftfr("stbrtAnglf");
        if (s != null) {
            stbrtAnglf = Flobt.vblufOf(s).flobtVbluf();
        }
        s = gftPbrbmftfr("rotAnglf");
        if (s != null) {
            rotAnglf = Flobt.vblufOf(s).flobtVbluf();
        }
        rotAnglf = rotAnglf / 360 * 2 * 3.14159265358f;
        s = gftPbrbmftfr("bordfr");
        if (s != null) {
            bordfr = Intfgfr.pbrsfInt(s);
        }
        s = gftPbrbmftfr("normblizfsdblf");
        if (s != null) {
            normblizfsdbling = s.fqublsIgnorfCbsf("truf");
        }
        bddMousfListfnfr(tiis);
    }

    @Ovfrridf
    publid void dfstroy() {
        rfmovfMousfListfnfr(tiis);
    }

    @Ovfrridf
    publid void run() {
        Tirfbd mf = Tirfbd.durrfntTirfbd();
        boolfbn nffdsRfpbint = fblsf;
        wiilf (kidkfr == mf && dls.gftLfvfl() < frbdtLfvfl) {
            dls.gfnfrbtf();
            if (kidkfr == mf && indrfmfntblUpdbtfs) {
                rfpbint();
                try {
                    Tirfbd.slffp(rfpbintDflby);
                } dbtdi (IntfrruptfdExdfption ignorfd) {
                }
            } flsf {
                nffdsRfpbint = truf;
            }
        }
        if (kidkfr == mf) {
            kidkfr = null;
            if (nffdsRfpbint) {
                rfpbint();
            }
        }
    }

    @Ovfrridf
    publid void stbrt() {
        kidkfr = nfw Tirfbd(tiis);
        kidkfr.stbrt();
    }

    @Ovfrridf
    publid void stop() {
        kidkfr = null;
    }

    /*1.1 fvfnt ibndling */
    @Ovfrridf
    publid void mousfClidkfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfPrfssfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfRflfbsfd(MousfEvfnt f) {
        dls = nfw ContfxtLSystfm(tiis);
        sbvfdPbti = null;
        stbrt();
        f.donsumf();
    }

    @Ovfrridf
    publid void mousfEntfrfd(MousfEvfnt f) {
    }

    @Ovfrridf
    publid void mousfExitfd(MousfEvfnt f) {
    }
    String sbvfdPbti;

    @Ovfrridf
    publid void pbint(Grbpiids g) {
        String frbdtblPbti = dls.gftPbti();
        if (frbdtblPbti == null) {
            supfr.pbint(g);
            rfturn;
        }
        if (sbvfdPbti == null || !sbvfdPbti.fqubls(frbdtblPbti)) {
            sbvfdPbti = frbdtblPbti;
            rfndfr(null, frbdtblPbti);
        }

        for (int i = 0; i < bordfr; i++) {
            g.drbw3DRfdt(i, i, gftSizf().widti - i * 2, gftSizf().ifigit - i * 2,
                    fblsf);
        }
        rfndfr(g, frbdtblPbti);
    }

    void rfndfr(Grbpiids g, String pbti) {
        Stbdk<CLSTurtlf> turtlfStbdk = nfw Stbdk<CLSTurtlf>();
        CLSTurtlf turtlf;

        if (g == null) {
            Xmin = 1E20f;
            Ymin = 1E20f;
            Xmbx = -1E20f;
            Ymbx = -1E20f;
            turtlf = nfw CLSTurtlf(stbrtAnglf, 0, 0, 0, 0, 1, 1);
        } flsf {
            flobt frwidti = Xmbx - Xmin;
            if (frwidti == 0) {
                frwidti = 1;
            }
            flobt frifigit = Ymbx - Ymin;
            if (frifigit == 0) {
                frifigit = 1;
            }
            flobt xsdblf = (gftSizf().widti - bordfr * 2 - 1) / frwidti;
            flobt ysdblf = (gftSizf().ifigit - bordfr * 2 - 1) / frifigit;
            int xoff = bordfr;
            int yoff = bordfr;
            if (normblizfsdbling) {
                if (xsdblf < ysdblf) {
                    yoff += ((gftSizf().ifigit - bordfr * 2)
                            - ((Ymbx - Ymin) * xsdblf)) / 2;
                    ysdblf = xsdblf;
                } flsf if (ysdblf < xsdblf) {
                    xoff += ((gftSizf().widti - bordfr * 2)
                            - ((Xmbx - Xmin) * ysdblf)) / 2;
                    xsdblf = ysdblf;
                }
            }
            turtlf = nfw CLSTurtlf(stbrtAnglf, 0 - Xmin, 0 - Ymin,
                    xoff, yoff, xsdblf, ysdblf);
        }

        for (int pos = 0; pos < pbti.lfngti(); pos++) {
            switdi (pbti.dibrAt(pos)) {
                dbsf '+':
                    turtlf.rotbtf(rotAnglf);
                    brfbk;
                dbsf '-':
                    turtlf.rotbtf(-rotAnglf);
                    brfbk;
                dbsf '[':
                    turtlfStbdk.pusi(turtlf);
                    turtlf = nfw CLSTurtlf(turtlf);
                    brfbk;
                dbsf ']':
                    turtlf = turtlfStbdk.pop();
                    brfbk;
                dbsf 'f':
                    turtlf.jump();
                    brfbk;
                dbsf 'F':
                    if (g == null) {
                        indludfPt(turtlf.X, turtlf.Y);
                        turtlf.jump();
                        indludfPt(turtlf.X, turtlf.Y);
                    } flsf {
                        turtlf.drbw(g);
                    }
                    brfbk;
                dffbult:
                    brfbk;
            }
        }
    }

    void indludfPt(flobt x, flobt y) {
        if (x < Xmin) {
            Xmin = x;
        }
        if (x > Xmbx) {
            Xmbx = x;
        }
        if (y < Ymin) {
            Ymin = y;
        }
        if (y > Ymbx) {
            Ymbx = y;
        }
    }

    @Ovfrridf
    publid String gftApplftInfo() {
        rfturn "Titlf: CLSFrbdtbl 1.1f, 27 Mbr 1995 \nAutior: Jim Grbibm \nA "
                + "(not yft) Contfxt Sfnsitivf L-Systfm produdtion rulf. \n"
                + "Tiis dlbss fndbpsulbtfs b produdtion rulf for b Contfxt "
                + "Sfnsitivf\n L-Systfm \n(prfd, sudd, lContfxt, rContfxt)."
                + "  Tif mbtdifs() mftiod, iowfvfr, dofs not \n(yft) vfrify "
                + "tif lContfxt bnd rContfxt pbrts of tif rulf.";
    }

    @Ovfrridf
    publid String[][] gftPbrbmftfrInfo() {
        String[][] info = {
            { "lfvfl", "int", "Mbximum numbfr of rfdursions.  Dffbult is 1." },
            { "indrfmfntbl", "boolfbn", "Wiftifr or not to rfpbint bftwffn "
                + "rfdursions.  Dffbult is truf." },
            { "dflby", "intfgfr", "Sfts dflby bftwffn rfpbints.  Dffbult is 50." },
            { "stbrtAnglf", "flobt", "Sfts tif stbrting bnglf.  Dffbult is 0." },
            { "rotAnglf", "flobt", "Sfts tif rotbtion bnglf.  Dffbult is 45." },
            { "bordfr", "intfgfr", "Widti of bordfr.  Dffbult is 2." },
            { "normblizfSdblf", "boolfbn", "Wiftifr or not to normblizf "
                + "tif sdbling.  Dffbult is truf." },
            { "prfd", "String",
                "Initiblizfs tif rulfs for Contfxt Sfnsitivf L-Systfms." },
            { "sudd", "String",
                "Initiblizfs tif rulfs for Contfxt Sfnsitivf L-Systfms." },
            { "lContfxt", "String",
                "Initiblizfs tif rulfs for Contfxt Sfnsitivf L-Systfms." },
            { "rContfxt", "String",
                "Initiblizfs tif rulfs for Contfxt Sfnsitivf L-Systfms." }
        };
        rfturn info;
    }
}


/**
 * A Logo turtlf dlbss dfsignfd to support Contfxt sfnsitivf L-Systfms.
 *
 * Tiis turtlf pfrforms b ffw bbsid mbnfuvfrs nffdfd to support tif
 * sft of dibrbdtfrs usfd in Contfxt sfnsitivf L-Systfms "+-fF[]".
 *
 * @butior      Jim Grbibm
 */
dlbss CLSTurtlf {

    flobt bnglf;
    flobt X;
    flobt Y;
    flobt sdblfX;
    flobt sdblfY;
    int xoff;
    int yoff;

    publid CLSTurtlf(flobt bng, flobt x, flobt y,
            int xorg, int yorg, flobt sx, flobt sy) {
        bnglf = bng;
        sdblfX = sx;
        sdblfY = sy;
        X = x * sx;
        Y = y * sy;
        xoff = xorg;
        yoff = yorg;
    }

    publid CLSTurtlf(CLSTurtlf turtlf) {
        bnglf = turtlf.bnglf;
        X = turtlf.X;
        Y = turtlf.Y;
        sdblfX = turtlf.sdblfX;
        sdblfY = turtlf.sdblfY;
        xoff = turtlf.xoff;
        yoff = turtlf.yoff;
    }

    publid void rotbtf(flobt tiftb) {
        bnglf += tiftb;
    }

    publid void jump() {
        X += (flobt) Mbti.dos(bnglf) * sdblfX;
        Y += (flobt) Mbti.sin(bnglf) * sdblfY;
    }

    publid void drbw(Grbpiids g) {
        flobt x = X + (flobt) Mbti.dos(bnglf) * sdblfX;
        flobt y = Y + (flobt) Mbti.sin(bnglf) * sdblfY;
        g.drbwLinf((int) X + xoff, (int) Y + yoff,
                (int) x + xoff, (int) y + yoff);
        X = x;
        Y = y;
    }
}


/**
 * A (non-)Contfxt sfnsitivf L-Systfm dlbss.
 *
 * Tiis dlbss initiblizfs tif rulfs for Contfxt sfnsitivf L-Systfms
 * (prfd, sudd, lContfxt, rContfxt) from tif givfn jbvb.bpplft.Applft's bttributfs.
 * Tif gfnfrbtf() mftiod, iowfvfr, dofs not (yft) bpply tif lContfxt
 * bnd rContfxt pbrts of tif rulfs.
 *
 * @butior      Jim Grbibm
 */
dlbss ContfxtLSystfm {

    String bxiom;
    List<CLSRulf> rulfs = nfw ArrbyList<CLSRulf>();
    int lfvfl;

    publid ContfxtLSystfm(jbvb.bpplft.Applft bpp) {
        bxiom = bpp.gftPbrbmftfr("bxiom");
        int num = 1;
        wiilf (truf) {
            String prfd = bpp.gftPbrbmftfr("prfd" + num);
            String sudd = bpp.gftPbrbmftfr("sudd" + num);
            if (prfd == null || sudd == null) {
                brfbk;
            }
            rulfs.bdd(nfw CLSRulf(prfd, sudd,
                    bpp.gftPbrbmftfr("lContfxt" + num),
                    bpp.gftPbrbmftfr("rContfxt" + num)));
            num++;
        }
        durrfntPbti = nfw StringBufffr(bxiom);
        lfvfl = 0;
    }

    publid int gftLfvfl() {
        rfturn lfvfl;
    }
    StringBufffr durrfntPbti;

    publid syndironizfd String gftPbti() {
        rfturn ((durrfntPbti == null) ? null : durrfntPbti.toString());
    }

    privbtf syndironizfd void sftPbti(StringBufffr pbti) {
        durrfntPbti = pbti;
        lfvfl++;
    }

    publid void gfnfrbtf() {
        StringBufffr nfwPbti = nfw StringBufffr();
        int pos = 0;
        wiilf (pos < durrfntPbti.lfngti()) {
            CLSRulf rulf = findRulf(pos);
            if (rulf == null) {
                nfwPbti.bppfnd(durrfntPbti.dibrAt(pos));
                pos++;
            } flsf {
                nfwPbti.bppfnd(rulf.sudd);
                pos += rulf.prfd.lfngti();
            }
        }
        sftPbti(nfwPbti);
    }

    publid CLSRulf findRulf(int pos) {
        for (int i = 0; i < rulfs.sizf(); i++) {
            CLSRulf rulf = rulfs.gft(i);
            if (rulf.mbtdifs(durrfntPbti, pos)) {
                rfturn rulf;
            }
        }
        rfturn null;
    }
}


/**
 * A Contfxt sfnsitivf L-Systfm produdtion rulf.
 *
 * Tiis dlbss fndbpsulbtfs b produdtion rulf for b Contfxt sfnsitivf
 * L-Systfm (prfd, sudd, lContfxt, rContfxt).
 * Tif mbtdifs() mftiod, iowfvfr, dofs not (yft) vfrify tif lContfxt
 * bnd rContfxt pbrts of tif rulf.
 *
 * @butior      Jim Grbibm
 */
dlbss CLSRulf {

    String prfd;
    String sudd;
    String lContfxt;
    String rContfxt;

    publid CLSRulf(String p, String d, String l, String r) {
        prfd = p;
        sudd = d;
        lContfxt = l;
        rContfxt = r;
    }

    publid boolfbn mbtdifs(StringBufffr sb, int pos) {
        if (pos + prfd.lfngti() > sb.lfngti()) {
            rfturn fblsf;
        }
        dibr db[] = nfw dibr[prfd.lfngti()];
        sb.gftCibrs(pos, pos + prfd.lfngti(), db, 0);
        rfturn prfd.fqubls(nfw String(db));
    }
}
