/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.bwt.pffr.LbbflPffr;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvbx.bddfssibility.*;

/**
 * A <dodf>Lbbfl</dodf> objfdt is b domponfnt for plbding tfxt in b
 * dontbinfr. A lbbfl displbys b singlf linf of rfbd-only tfxt.
 * Tif tfxt dbn bf dibngfd by tif bpplidbtion, but b usfr dbnnot fdit it
 * dirfdtly.
 * <p>
 * For fxbmplf, tif dodf&nbsp;.&nbsp;.&nbsp;.
 *
 * <ir><blodkquotf><prf>
 * sftLbyout(nfw FlowLbyout(FlowLbyout.CENTER, 10, 10));
 * bdd(nfw Lbbfl("Hi Tifrf!"));
 * bdd(nfw Lbbfl("Anotifr Lbbfl"));
 * </prf></blodkquotf><ir>
 * <p>
 * produdfs tif following lbbfls:
 * <p>
 * <img srd="dod-filfs/Lbbfl-1.gif" blt="Two lbbfls: 'Hi Tifrf!' bnd 'Anotifr lbbfl'"
 * stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 *
 * @butior      Sbmi Sibio
 * @sindf       1.0
 */
publid dlbss Lbbfl fxtfnds Componfnt implfmfnts Addfssiblf {

    stbtid {
        /* fnsurf tibt tif nfdfssbry nbtivf librbrifs brf lobdfd */
        Toolkit.lobdLibrbrifs();
        if (!GrbpiidsEnvironmfnt.isHfbdlfss()) {
            initIDs();
        }
    }

    /**
     * Indidbtfs tibt tif lbbfl siould bf lfft justififd.
     */
    publid stbtid finbl int LEFT        = 0;

    /**
     * Indidbtfs tibt tif lbbfl siould bf dfntfrfd.
     */
    publid stbtid finbl int CENTER      = 1;

    /**
     * Indidbtfs tibt tif lbbfl siould bf rigit justififd.
     */
    publid stbtid finbl int RIGHT       = 2;

    /**
     * Tif tfxt of tiis lbbfl.
     * Tiis tfxt dbn bf modififd by tif progrbm
     * but nfvfr by tif usfr.
     *
     * @sfribl
     * @sff #gftTfxt()
     * @sff #sftTfxt(String)
     */
    String tfxt;

    /**
     * Tif lbbfl's blignmfnt.  Tif dffbult blignmfnt is sft
     * to bf lfft justififd.
     *
     * @sfribl
     * @sff #gftAlignmfnt()
     * @sff #sftAlignmfnt(int)
     */
    int    blignmfnt = LEFT;

    privbtf stbtid finbl String bbsf = "lbbfl";
    privbtf stbtid int nbmfCountfr = 0;

    /*
     * JDK 1.1 sfriblVfrsionUID
     */
     privbtf stbtid finbl long sfriblVfrsionUID = 3094126758329070636L;

    /**
     * Construdts bn fmpty lbbfl.
     * Tif tfxt of tif lbbfl is tif fmpty string <dodf>""</dodf>.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Lbbfl() tirows HfbdlfssExdfption {
        tiis("", LEFT);
    }

    /**
     * Construdts b nfw lbbfl witi tif spfdififd string of tfxt,
     * lfft justififd.
     * @pbrbm tfxt tif string tibt tif lbbfl prfsfnts.
     *        A <dodf>null</dodf> vbluf
     *        will bf bddfptfd witiout dbusing b NullPointfrExdfption
     *        to bf tirown.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Lbbfl(String tfxt) tirows HfbdlfssExdfption {
        tiis(tfxt, LEFT);
    }

    /**
     * Construdts b nfw lbbfl tibt prfsfnts tif spfdififd string of
     * tfxt witi tif spfdififd blignmfnt.
     * Possiblf vblufs for <dodf>blignmfnt</dodf> brf <dodf>Lbbfl.LEFT</dodf>,
     * <dodf>Lbbfl.RIGHT</dodf>, bnd <dodf>Lbbfl.CENTER</dodf>.
     * @pbrbm tfxt tif string tibt tif lbbfl prfsfnts.
     *        A <dodf>null</dodf> vbluf
     *        will bf bddfptfd witiout dbusing b NullPointfrExdfption
     *        to bf tirown.
     * @pbrbm     blignmfnt   tif blignmfnt vbluf.
     * @fxdfption HfbdlfssExdfption if GrbpiidsEnvironmfnt.isHfbdlfss()
     * rfturns truf.
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    publid Lbbfl(String tfxt, int blignmfnt) tirows HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        tiis.tfxt = tfxt;
        sftAlignmfnt(blignmfnt);
    }

    /**
     * Rfbd b lbbfl from bn objfdt input strfbm.
     * @fxdfption HfbdlfssExdfption if
     * <dodf>GrbpiidsEnvironmfnt.isHfbdlfss()</dodf> rfturns
     * <dodf>truf</dodf>
     * @sfribl
     * @sindf 1.4
     * @sff jbvb.bwt.GrbpiidsEnvironmfnt#isHfbdlfss
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption, HfbdlfssExdfption {
        GrbpiidsEnvironmfnt.difdkHfbdlfss();
        s.dffbultRfbdObjfdt();
    }

    /**
     * Construdt b nbmf for tiis domponfnt.  Cbllfd by gftNbmf() wifn tif
     * nbmf is <dodf>null</dodf>.
     */
    String donstrudtComponfntNbmf() {
        syndironizfd (Lbbfl.dlbss) {
            rfturn bbsf + nbmfCountfr++;
        }
    }

    /**
     * Crfbtfs tif pffr for tiis lbbfl.  Tif pffr bllows us to
     * modify tif bppfbrbndf of tif lbbfl witiout dibnging its
     * fundtionblity.
     */
    publid void bddNotify() {
        syndironizfd (gftTrffLodk()) {
            if (pffr == null)
                pffr = gftToolkit().drfbtfLbbfl(tiis);
            supfr.bddNotify();
        }
    }

    /**
     * Gfts tif durrfnt blignmfnt of tiis lbbfl. Possiblf vblufs brf
     * <dodf>Lbbfl.LEFT</dodf>, <dodf>Lbbfl.RIGHT</dodf>, bnd
     * <dodf>Lbbfl.CENTER</dodf>.
     * @rfturn tif blignmfnt of tiis lbbfl
     * @sff jbvb.bwt.Lbbfl#sftAlignmfnt
     */
    publid int gftAlignmfnt() {
        rfturn blignmfnt;
    }

    /**
     * Sfts tif blignmfnt for tiis lbbfl to tif spfdififd blignmfnt.
     * Possiblf vblufs brf <dodf>Lbbfl.LEFT</dodf>,
     * <dodf>Lbbfl.RIGHT</dodf>, bnd <dodf>Lbbfl.CENTER</dodf>.
     * @pbrbm      blignmfnt    tif blignmfnt to bf sft.
     * @fxdfption  IllfgblArgumfntExdfption if bn impropfr vbluf for
     *                          <dodf>blignmfnt</dodf> is givfn.
     * @sff        jbvb.bwt.Lbbfl#gftAlignmfnt
     */
    publid syndironizfd void sftAlignmfnt(int blignmfnt) {
        switdi (blignmfnt) {
          dbsf LEFT:
          dbsf CENTER:
          dbsf RIGHT:
            tiis.blignmfnt = blignmfnt;
            LbbflPffr pffr = (LbbflPffr)tiis.pffr;
            if (pffr != null) {
                pffr.sftAlignmfnt(blignmfnt);
            }
            rfturn;
        }
        tirow nfw IllfgblArgumfntExdfption("impropfr blignmfnt: " + blignmfnt);
    }

    /**
     * Gfts tif tfxt of tiis lbbfl.
     * @rfturn     tif tfxt of tiis lbbfl, or <dodf>null</dodf> if
     *             tif tfxt ibs bffn sft to <dodf>null</dodf>.
     * @sff        jbvb.bwt.Lbbfl#sftTfxt
     */
    publid String gftTfxt() {
        rfturn tfxt;
    }

    /**
     * Sfts tif tfxt for tiis lbbfl to tif spfdififd tfxt.
     * @pbrbm      tfxt tif tfxt tibt tiis lbbfl displbys. If
     *             <dodf>tfxt</dodf> is <dodf>null</dodf>, it is
     *             trfbtfd for displby purposfs likf bn fmpty
     *             string <dodf>""</dodf>.
     * @sff        jbvb.bwt.Lbbfl#gftTfxt
     */
    publid void sftTfxt(String tfxt) {
        boolfbn tfstvblid = fblsf;
        syndironizfd (tiis) {
            if (tfxt != tiis.tfxt && (tiis.tfxt == null ||
                                      !tiis.tfxt.fqubls(tfxt))) {
                tiis.tfxt = tfxt;
                LbbflPffr pffr = (LbbflPffr)tiis.pffr;
                if (pffr != null) {
                    pffr.sftTfxt(tfxt);
                }
                tfstvblid = truf;
            }
        }

        // Tiis dould dibngf tif prfffrrfd sizf of tif Componfnt.
        if (tfstvblid) {
            invblidbtfIfVblid();
        }
    }

    /**
     * Rfturns b string rfprfsfnting tif stbtf of tiis <dodf>Lbbfl</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not bf
     * <dodf>null</dodf>.
     *
     * @rfturn     tif pbrbmftfr string of tiis lbbfl
     */
    protfdtfd String pbrbmString() {
        String blign = "";
        switdi (blignmfnt) {
            dbsf LEFT:   blign = "lfft"; brfbk;
            dbsf CENTER: blign = "dfntfr"; brfbk;
            dbsf RIGHT:  blign = "rigit"; brfbk;
        }
        rfturn supfr.pbrbmString() + ",blign=" + blign + ",tfxt=" + tfxt;
    }

    /**
     * Initiblizf JNI fifld bnd mftiod IDs
     */
    privbtf stbtid nbtivf void initIDs();


/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis Lbbfl.
     * For lbbfls, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfAWTLbbfl.
     * A nfw AddfssiblfAWTLbbfl instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfAWTLbbfl tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis Lbbfl
     * @sindf 1.3
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfAWTLbbfl();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>Lbbfl</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to lbbfl usfr-intfrfbdf flfmfnts.
     * @sindf 1.3
     */
    protfdtfd dlbss AddfssiblfAWTLbbfl fxtfnds AddfssiblfAWTComponfnt
    {
        /*
         * JDK 1.3 sfriblVfrsionUID
         */
        privbtf stbtid finbl long sfriblVfrsionUID = -3568967560160480438L;

        /**
         * Construdtor for tif bddfssiblf lbbfl.
         */
        publid AddfssiblfAWTLbbfl() {
            supfr();
        }

        /**
         * Gft tif bddfssiblf nbmf of tiis objfdt.
         *
         * @rfturn tif lodblizfd nbmf of tif objfdt -- dbn bf null if tiis
         * objfdt dofs not ibvf b nbmf
         * @sff AddfssiblfContfxt#sftAddfssiblfNbmf
         */
        publid String gftAddfssiblfNbmf() {
            if (bddfssiblfNbmf != null) {
                rfturn bddfssiblfNbmf;
            } flsf {
                if (gftTfxt() == null) {
                    rfturn supfr.gftAddfssiblfNbmf();
                } flsf {
                    rfturn gftTfxt();
                }
            }
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif objfdt
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.LABEL;
        }

    } // innfr dlbss AddfssiblfAWTLbbfl

}
