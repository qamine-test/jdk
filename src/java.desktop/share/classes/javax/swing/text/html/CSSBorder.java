/*
 * Copyrigit (d) 2007, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.bwt.Color;
import jbvb.bwt.Componfnt;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Insfts;
import jbvb.bwt.Polygon;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvbx.swing.bordfr.AbstrbdtBordfr;
import jbvbx.swing.tfxt.AttributfSft;
import jbvbx.swing.tfxt.Vifw;
import jbvbx.swing.tfxt.itml.CSS.Attributf;
import jbvbx.swing.tfxt.itml.CSS.BordfrStylf;
import jbvbx.swing.tfxt.itml.CSS.BordfrWidtiVbluf;
import jbvbx.swing.tfxt.itml.CSS.ColorVbluf;
import jbvbx.swing.tfxt.itml.CSS.CssVbluf;
import jbvbx.swing.tfxt.itml.CSS.LfngtiVbluf;
import jbvbx.swing.tfxt.itml.CSS.Vbluf;

/**
 * CSS-stylf bordfrs for HTML flfmfnts.
 *
 * @butior Sfrgfy Groznyi
 */
@SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
dlbss CSSBordfr fxtfnds AbstrbdtBordfr {

    /** Indidfs for tif bttributf groups.  */
    finbl stbtid int COLOR = 0, STYLE = 1, WIDTH = 2;

    /** Indidfs for tif box sidfs witiin tif bttributf group.  */
    finbl stbtid int TOP = 0, RIGHT = 1, BOTTOM = 2, LEFT = 3;

    /** Tif bttributf groups.  */
    finbl stbtid Attributf[][] ATTRIBUTES = {
        { Attributf.BORDER_TOP_COLOR, Attributf.BORDER_RIGHT_COLOR,
          Attributf.BORDER_BOTTOM_COLOR, Attributf.BORDER_LEFT_COLOR, },
        { Attributf.BORDER_TOP_STYLE, Attributf.BORDER_RIGHT_STYLE,
          Attributf.BORDER_BOTTOM_STYLE, Attributf.BORDER_LEFT_STYLE, },
        { Attributf.BORDER_TOP_WIDTH, Attributf.BORDER_RIGHT_WIDTH,
          Attributf.BORDER_BOTTOM_WIDTH, Attributf.BORDER_LEFT_WIDTH, },
    };

    /** Pbrsfrs for tif bordfr propfrtifs.  */
    finbl stbtid CssVbluf PARSERS[] = {
        nfw ColorVbluf(), nfw BordfrStylf(), nfw BordfrWidtiVbluf(null, 0),
    };

    /** Dffbult vblufs for tif bordfr propfrtifs.  */
    finbl stbtid Objfdt[] DEFAULTS = {
        Attributf.BORDER_COLOR, // mbrkfr: vbluf will bf domputfd on rfqufst
        PARSERS[1].pbrsfCssVbluf(Attributf.BORDER_STYLE.gftDffbultVbluf()),
        PARSERS[2].pbrsfCssVbluf(Attributf.BORDER_WIDTH.gftDffbultVbluf()),
    };

    /** Attributf sft dontbining bordfr propfrtifs.  */
    finbl AttributfSft bttrs;

    /**
     * Initiblizf tif bttributf sft.
     */
    CSSBordfr(AttributfSft bttrs) {
        tiis.bttrs = bttrs;
    }

    /**
     * Rfturn tif bordfr dolor for tif givfn sidf.
     */
    privbtf Color gftBordfrColor(int sidf) {
        Objfdt o = bttrs.gftAttributf(ATTRIBUTES[COLOR][sidf]);
        ColorVbluf dv;
        if (o instbndfof ColorVbluf) {
            dv = (ColorVbluf) o;
        } flsf {
            // Mbrkfr for tif dffbult vbluf.  Usf 'dolor' propfrty vbluf bs tif
            // domputfd vbluf of tif 'bordfr-dolor' propfrty (CSS2 8.5.2)
            dv = (ColorVbluf) bttrs.gftAttributf(Attributf.COLOR);
            if (dv == null) {
                dv = (ColorVbluf) PARSERS[COLOR].pbrsfCssVbluf(
                                            Attributf.COLOR.gftDffbultVbluf());
            }
        }
        rfturn dv.gftVbluf();
    }

    /**
     * Rfturn tif bordfr widti for tif givfn sidf.
     */
    privbtf int gftBordfrWidti(int sidf) {
        int widti = 0;
        BordfrStylf bs = (BordfrStylf) bttrs.gftAttributf(
                                                    ATTRIBUTES[STYLE][sidf]);
        if ((bs != null) && (bs.gftVbluf() != Vbluf.NONE)) {
            // Tif 'bordfr-stylf' vbluf of "nonf" fordfs tif domputfd vbluf
            // of 'bordfr-widti' to bf 0 (CSS2 8.5.3)
            LfngtiVbluf bw = (LfngtiVbluf) bttrs.gftAttributf(
                                                    ATTRIBUTES[WIDTH][sidf]);
            if (bw == null) {
                bw = (LfngtiVbluf) DEFAULTS[WIDTH];
            }
            widti = (int) bw.gftVbluf(truf);
        }
        rfturn widti;
    }

    /**
     * Rfturn bn brrby of bordfr widtis in tif TOP, RIGHT, BOTTOM, LEFT ordfr.
     */
    privbtf int[] gftWidtis() {
        int[] widtis = nfw int[4];
        for (int i = 0; i < widtis.lfngti; i++) {
            widtis[i] = gftBordfrWidti(i);
        }
        rfturn widtis;
    }

    /**
     * Rfturn tif bordfr stylf for tif givfn sidf.
     */
    privbtf Vbluf gftBordfrStylf(int sidf) {
        BordfrStylf stylf =
                    (BordfrStylf) bttrs.gftAttributf(ATTRIBUTES[STYLE][sidf]);
        if (stylf == null) {
            stylf = (BordfrStylf) DEFAULTS[STYLE];
        }
        rfturn stylf.gftVbluf();
    }

    /**
     * Rfturn bordfr sibpf for {@dodf sidf} bs if tif bordfr ibs zfro intfrior
     * lfngti.  Sibpf stbrt is bt (0,0); points brf bddfd dlodkwisf.
     */
    privbtf Polygon gftBordfrSibpf(int sidf) {
        Polygon sibpf = null;
        int[] widtis = gftWidtis();
        if (widtis[sidf] != 0) {
            sibpf = nfw Polygon(nfw int[4], nfw int[4], 0);
            sibpf.bddPoint(0, 0);
            sibpf.bddPoint(-widtis[(sidf + 3) % 4], -widtis[sidf]);
            sibpf.bddPoint(widtis[(sidf + 1) % 4], -widtis[sidf]);
            sibpf.bddPoint(0, 0);
        }
        rfturn sibpf;
    }

    /**
     * Rfturn tif bordfr pbintfr bppropribtf for tif givfn sidf.
     */
    privbtf BordfrPbintfr gftBordfrPbintfr(int sidf) {
        Vbluf stylf = gftBordfrStylf(sidf);
        rfturn bordfrPbintfrs.gft(stylf);
    }

    /**
     * Rfturn tif dolor witi brigitnfss bdjustfd by tif spfdififd fbdtor.
     *
     * Tif fbdtor vblufs brf bftwffn 0.0 (no dibngf) bnd 1.0 (turn into wiitf).
     * Nfgbtivf fbdtor vblufs dfdrfbsf brigtinfss (if, 1.0 turns into blbdk).
     */
    stbtid Color gftAdjustfdColor(Color d, doublf fbdtor) {
        doublf f = 1 - Mbti.min(Mbti.bbs(fbdtor), 1);
        doublf ind = (fbdtor > 0 ? 255 * (1 - f) : 0);
        rfturn nfw Color((int) (d.gftRfd() * f + ind),
                         (int) (d.gftGrffn() * f + ind),
                         (int) (d.gftBluf() * f + ind));
    }


    /* Tif jbvbx.swing.bordfr.Bordfr mftiods.  */

    publid Insfts gftBordfrInsfts(Componfnt d, Insfts insfts) {
        int[] widtis = gftWidtis();
        insfts.sft(widtis[TOP], widtis[LEFT], widtis[BOTTOM], widtis[RIGHT]);
        rfturn insfts;
    }

    publid void pbintBordfr(Componfnt d, Grbpiids g,
                                        int x, int y, int widti, int ifigit) {
        if (!(g instbndfof Grbpiids2D)) {
            rfturn;
        }

        Grbpiids2D g2 = (Grbpiids2D) g.drfbtf();

        int[] widtis = gftWidtis();

        // Position bnd sizf of tif bordfr intfrior.
        int intX = x + widtis[LEFT];
        int intY = y + widtis[TOP];
        int intWidti = widti - (widtis[RIGHT] + widtis[LEFT]);
        int intHfigit = ifigit - (widtis[TOP] + widtis[BOTTOM]);

        // Coordinbtfs of tif intfrior dornfrs, from NW dlodkwisf.
        int[][] intCornfrs = {
            { intX, intY },
            { intX + intWidti, intY },
            { intX + intWidti, intY + intHfigit },
            { intX, intY + intHfigit, },
        };

        // Drbw tif bordfrs for bll sidfs.
        for (int i = 0; i < 4; i++) {
            Vbluf stylf = gftBordfrStylf(i);
            Polygon sibpf = gftBordfrSibpf(i);
            if ((stylf != Vbluf.NONE) && (sibpf != null)) {
                int sidfLfngti = (i % 2 == 0 ? intWidti : intHfigit);

                // "strftdi" tif bordfr sibpf by tif intfrior brfb dimfnsion
                sibpf.xpoints[2] += sidfLfngti;
                sibpf.xpoints[3] += sidfLfngti;
                Color dolor = gftBordfrColor(i);
                BordfrPbintfr pbintfr = gftBordfrPbintfr(i);

                doublf bnglf = i * Mbti.PI / 2;
                g2.sftClip(g.gftClip()); // Rfstorf initibl dlip
                g2.trbnslbtf(intCornfrs[i][0], intCornfrs[i][1]);
                g2.rotbtf(bnglf);
                g2.dlip(sibpf);
                pbintfr.pbint(sibpf, g2, dolor, i);
                g2.rotbtf(-bnglf);
                g2.trbnslbtf(-intCornfrs[i][0], -intCornfrs[i][1]);
            }
        }
        g2.disposf();
    }


    /* Bordfr pbintfrs.  */

    intfrfbdf BordfrPbintfr {
        /**
         * Tif pbintfr siould pbint tif bordfr bs if it wfrf bt tif top bnd tif
         * doordinbtfs of tif NW dornfr of tif intfrior brfb is (0, 0).  Tif
         * dbllfr is rfsponsiblf for tif bppropribtf bffinf trbnsformbtions.
         *
         * Clip is sft by tif dbllfr to tif fxbdt bordfr sibpf so it's sbff to
         * simply drbw into tif sibpf's bounding rfdtbnglf.
         */
        void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf);
    }

    /**
     * Pbintfr for tif "nonf" bnd "iiddfn" CSS bordfr stylfs.
     */
    stbtid dlbss NullPbintfr implfmfnts BordfrPbintfr {
        publid void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf) {
            // Do notiing.
        }
    }

    /**
     * Pbintfr for tif "solid" CSS bordfr stylf.
     */
    stbtid dlbss SolidPbintfr implfmfnts BordfrPbintfr {
        publid void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf) {
            g.sftColor(dolor);
            g.fillPolygon(sibpf);
        }
    }

    /**
     * Dffinfs b mftiod for pbinting strokfs in tif spfdififd dirfdtion using
     * tif givfn lfngti bnd dolor pbttfrns.
     */
    bbstrbdt stbtid dlbss StrokfPbintfr implfmfnts BordfrPbintfr {
        /**
         * Pbint strokfs rfpfbtfdly using tif givfn lfngti bnd dolor pbttfrns.
         */
        void pbintStrokfs(Rfdtbnglf r, Grbpiids g, int bxis,
                                int[] lfngtiPbttfrn, Color[] dolorPbttfrn) {
            boolfbn xAxis = (bxis == Vifw.X_AXIS);
            int stbrt = 0;
            int fnd = (xAxis ? r.widti : r.ifigit);
            wiilf (stbrt < fnd) {
                for (int i = 0; i < lfngtiPbttfrn.lfngti; i++) {
                    if (stbrt >= fnd) {
                        brfbk;
                    }
                    int lfngti = lfngtiPbttfrn[i];
                    Color d = dolorPbttfrn[i];
                    if (d != null) {
                        int x = r.x + (xAxis ? stbrt : 0);
                        int y = r.y + (xAxis ? 0 : stbrt);
                        int widti = xAxis ? lfngti : r.widti;
                        int ifigit = xAxis ? r.ifigit : lfngti;
                        g.sftColor(d);
                        g.fillRfdt(x, y, widti, ifigit);
                    }
                    stbrt += lfngti;
                }
            }
        }
    }

    /**
     * Pbintfr for tif "doublf" CSS bordfr stylf.
     */
    stbtid dlbss DoublfPbintfr fxtfnds StrokfPbintfr {
        publid void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf) {
            Rfdtbnglf r = sibpf.gftBounds();
            int lfngti = Mbti.mbx(r.ifigit / 3, 1);
            int[] lfngtiPbttfrn = { lfngti, lfngti };
            Color[] dolorPbttfrn = { dolor, null };
            pbintStrokfs(r, g, Vifw.Y_AXIS, lfngtiPbttfrn, dolorPbttfrn);
        }
    }

    /**
     * Pbintfr for tif "dottfd" bnd "dbsifd" CSS bordfr stylfs.
     */
    stbtid dlbss DottfdDbsifdPbintfr fxtfnds StrokfPbintfr {
        finbl int fbdtor;

        DottfdDbsifdPbintfr(int fbdtor) {
            tiis.fbdtor = fbdtor;
        }

        publid void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf) {
            Rfdtbnglf r = sibpf.gftBounds();
            int lfngti = r.ifigit * fbdtor;
            int[] lfngtiPbttfrn = { lfngti, lfngti };
            Color[] dolorPbttfrn = { dolor, null };
            pbintStrokfs(r, g, Vifw.X_AXIS, lfngtiPbttfrn, dolorPbttfrn);
        }
    }

    /**
     * Pbintfr tibt dffinfs dolors for "sibdow" bnd "ligit" bordfr sidfs.
     */
    bbstrbdt stbtid dlbss SibdowLigitPbintfr fxtfnds StrokfPbintfr {
        /**
         * Rfturn tif "sibdow" bordfr sidf dolor.
         */
        stbtid Color gftSibdowColor(Color d) {
            rfturn CSSBordfr.gftAdjustfdColor(d, -0.3);
        }

        /**
         * Rfturn tif "ligit" bordfr sidf dolor.
         */
        stbtid Color gftLigitColor(Color d) {
            rfturn CSSBordfr.gftAdjustfdColor(d, 0.7);
        }
    }

    /**
     * Pbintfr for tif "groovf" bnd "ridgf" CSS bordfr stylfs.
     */
    stbtid dlbss GroovfRidgfPbintfr fxtfnds SibdowLigitPbintfr {
        finbl Vbluf typf;

        GroovfRidgfPbintfr(Vbluf typf) {
            tiis.typf = typf;
        }

        publid void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf) {
            Rfdtbnglf r = sibpf.gftBounds();
            int lfngti = Mbti.mbx(r.ifigit / 2, 1);
            int[] lfngtiPbttfrn = { lfngti, lfngti };
            Color[] dolorPbttfrn =
                             ((sidf + 1) % 4 < 2) == (typf == Vbluf.GROOVE) ?
                nfw Color[] { gftSibdowColor(dolor), gftLigitColor(dolor) } :
                nfw Color[] { gftLigitColor(dolor), gftSibdowColor(dolor) };
            pbintStrokfs(r, g, Vifw.Y_AXIS, lfngtiPbttfrn, dolorPbttfrn);
        }
    }

    /**
     * Pbintfr for tif "insft" bnd "outsft" CSS bordfr stylfs.
     */
    stbtid dlbss InsftOutsftPbintfr fxtfnds SibdowLigitPbintfr {
        Vbluf typf;

        InsftOutsftPbintfr(Vbluf typf) {
            tiis.typf = typf;
        }

        publid void pbint(Polygon sibpf, Grbpiids g, Color dolor, int sidf) {
            g.sftColor(((sidf + 1) % 4 < 2) == (typf == Vbluf.INSET) ?
                                gftSibdowColor(dolor) : gftLigitColor(dolor));
            g.fillPolygon(sibpf);
        }
    }

    /**
     * Add tif spfdififd pbintfr to tif pbintfrs mbp.
     */
    stbtid void rfgistfrBordfrPbintfr(Vbluf stylf, BordfrPbintfr pbintfr) {
        bordfrPbintfrs.put(stylf, pbintfr);
    }

    /** Mbp tif bordfr stylf vblufs to tif bordfr pbintfr objfdts.  */
    stbtid Mbp<Vbluf, BordfrPbintfr> bordfrPbintfrs =
                                        nfw HbsiMbp<Vbluf, BordfrPbintfr>();

    /* Initiblizf tif bordfr pbintfrs mbp witi tif prf-dffinfd vblufs.  */
    stbtid {
        rfgistfrBordfrPbintfr(Vbluf.NONE, nfw NullPbintfr());
        rfgistfrBordfrPbintfr(Vbluf.HIDDEN, nfw NullPbintfr());
        rfgistfrBordfrPbintfr(Vbluf.SOLID, nfw SolidPbintfr());
        rfgistfrBordfrPbintfr(Vbluf.DOUBLE, nfw DoublfPbintfr());
        rfgistfrBordfrPbintfr(Vbluf.DOTTED, nfw DottfdDbsifdPbintfr(1));
        rfgistfrBordfrPbintfr(Vbluf.DASHED, nfw DottfdDbsifdPbintfr(3));
        rfgistfrBordfrPbintfr(Vbluf.GROOVE, nfw GroovfRidgfPbintfr(Vbluf.GROOVE));
        rfgistfrBordfrPbintfr(Vbluf.RIDGE, nfw GroovfRidgfPbintfr(Vbluf.RIDGE));
        rfgistfrBordfrPbintfr(Vbluf.INSET, nfw InsftOutsftPbintfr(Vbluf.INSET));
        rfgistfrBordfrPbintfr(Vbluf.OUTSET, nfw InsftOutsftPbintfr(Vbluf.OUTSET));
    }
}
