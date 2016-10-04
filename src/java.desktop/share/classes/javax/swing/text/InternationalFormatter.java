/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.fvfnt.AdtionEvfnt;
import jbvb.io.*;
import jbvb.tfxt.*;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.util.*;
import jbvbx.swing.*;

/**
 * <dodf>IntfrnbtionblFormbttfr</dodf> fxtfnds <dodf>DffbultFormbttfr</dodf>,
 * using bn instbndf of <dodf>jbvb.tfxt.Formbt</dodf> to ibndlf tif
 * donvfrsion to b String, bnd tif donvfrsion from b String.
 * <p>
 * If <dodf>gftAllowsInvblid()</dodf> is fblsf, tiis will bsk tif
 * <dodf>Formbt</dodf> to formbt tif durrfnt tfxt on fvfry fdit.
 * <p>
 * You dbn spfdify b minimum bnd mbximum vbluf by wby of tif
 * <dodf>sftMinimum</dodf> bnd <dodf>sftMbximum</dodf> mftiods. In ordfr
 * for tiis to work tif vblufs rfturnfd from <dodf>stringToVbluf</dodf> must bf
 * dompbrbblf to tif min/mbx vblufs by wby of tif <dodf>Compbrbblf</dodf>
 * intfrfbdf.
 * <p>
 * Bf dbrfful iow you donfigurf tif <dodf>Formbt</dodf> bnd tif
 * <dodf>IntfrnbtionblFormbttfr</dodf>, bs it is possiblf to drfbtf b
 * situbtion wifrf dfrtbin vblufs dbn not bf input. Considfr tif dbtf
 * formbt 'M/d/yy', bn <dodf>IntfrnbtionblFormbttfr</dodf> tibt is blwbys
 * vblid (<dodf>sftAllowsInvblid(fblsf)</dodf>), is in ovfrwritf modf
 * (<dodf>sftOvfrwritfModf(truf)</dodf>) bnd tif dbtf 7/1/99. In tiis
 * dbsf tif usfr will not bf bblf to fntfr b two digit monti or dby of
 * monti. To bvoid tiis, tif formbt siould bf 'MM/dd/yy'.
 * <p>
 * If <dodf>IntfrnbtionblFormbttfr</dodf> is donfigurfd to only bllow vblid
 * vblufs (<dodf>sftAllowsInvblid(fblsf)</dodf>), fvfry vblid fdit will rfsult
 * in tif tfxt of tif <dodf>JFormbttfdTfxtFifld</dodf> bfing domplftfly rfsft
 * from tif <dodf>Formbt</dodf>.
 * Tif dursor position will blso bf bdjustfd bs litfrbl dibrbdtfrs brf
 * bddfd/rfmovfd from tif rfsulting String.
 * <p>
 * <dodf>IntfrnbtionblFormbttfr</dodf>'s bfibvior of
 * <dodf>stringToVbluf</dodf> is  sligitly difffrfnt tibn tibt of
 * <dodf>DffbultTfxtFormbttfr</dodf>, it dofs tif following:
 * <ol>
 *   <li><dodf>pbrsfObjfdt</dodf> is invokfd on tif <dodf>Formbt</dodf>
 *       spfdififd by <dodf>sftFormbt</dodf>
 *   <li>If b Clbss ibs bffn sft for tif vblufs (<dodf>sftVblufClbss</dodf>),
 *       supfrs implfmfntbtion is invokfd to donvfrt tif vbluf rfturnfd
 *       from <dodf>pbrsfObjfdt</dodf> to tif bppropribtf dlbss.
 *   <li>If b <dodf>PbrsfExdfption</dodf> ibs not bffn tirown, bnd tif vbluf
 *       is outsidf tif min/mbx b <dodf>PbrsfExdfption</dodf> is tirown.
 *   <li>Tif vbluf is rfturnfd.
 * </ol>
 * <dodf>IntfrnbtionblFormbttfr</dodf> implfmfnts <dodf>stringToVbluf</dodf>
 * in tiis mbnnfr so tibt you dbn spfdify bn bltfrnbtf Clbss tibn
 * <dodf>Formbt</dodf> mby rfturn.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @sff jbvb.tfxt.Formbt
 * @sff jbvb.lbng.Compbrbblf
 *
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss IntfrnbtionblFormbttfr fxtfnds DffbultFormbttfr {
    /**
     * Usfd by <dodf>gftFiflds</dodf>.
     */
    privbtf stbtid finbl Formbt.Fifld[] EMPTY_FIELD_ARRAY =nfw Formbt.Fifld[0];

    /**
     * Objfdt usfd to ibndlf tif donvfrsion.
     */
    privbtf Formbt formbt;
    /**
     * Cbn bf usfd to imposf b mbximum vbluf.
     */
    privbtf Compbrbblf<?> mbx;
    /**
     * Cbn bf usfd to imposf b minimum vbluf.
     */
    privbtf Compbrbblf<?> min;

    /**
     * <dodf>IntfrnbtionblFormbttfr</dodf>'s bfibvior is didbtbtfd by b
     * <dodf>AttributfdCibrbdtfrItfrbtor</dodf> tibt is obtbinfd from
     * tif <dodf>Formbt</dodf>. On fvfry fdit, bssuming
     * bllows invblid is fblsf, tif <dodf>Formbt</dodf> instbndf is invokfd
     * witi <dodf>formbtToCibrbdtfrItfrbtor</dodf>. A <dodf>BitSft</dodf> is
     * blso kfpt upto dbtf witi tif non-litfrbl dibrbdtfrs, tibt is
     * for fvfry indfx in tif <dodf>AttributfdCibrbdtfrItfrbtor</dodf> bn
     * fntry in tif bit sft is updbtfd bbsfd on tif rfturn vbluf from
     * <dodf>isLitfrbl(Mbp)</dodf>. <dodf>isLitfrbl(int)</dodf> tifn usfs
     * tiis dbdifd informbtion.
     * <p>
     * If bllowsInvblid is fblsf, fvfry fdit rfsults in rfsftting tif domplftf
     * tfxt of tif JTfxtComponfnt.
     * <p>
     * IntfrnbtionblFormbttfrFiltfr dbn blso providf two bdtions suitbblf for
     * indrfmfnting bnd dfdrfmfnting. To fnbblf tiis b subdlbss must
     * ovfrridf <dodf>gftSupportsIndrfmfnt</dodf> to rfturn truf, bnd
     * ovfrridf <dodf>bdjustVbluf</dodf> to ibndlf tif dibnging of tif
     * vbluf. If you wbnt to support dibnging tif vbluf outsidf of
     * tif vblid FifldPositions, you will nffd to ovfrridf
     * <dodf>dbnIndrfmfnt</dodf>.
     */
    /**
     * A bit is sft for fvfry indfx idfntififd in tif
     * AttributfdCibrbdtfrItfrbtor tibt is not donsidfrfd dfdorbtion.
     * Tiis siould only bf usfd if vblidMbsk is truf.
     */
    privbtf trbnsifnt BitSft litfrblMbsk;
    /**
     * Usfd to itfrbtf ovfr dibrbdtfrs.
     */
    privbtf trbnsifnt AttributfdCibrbdtfrItfrbtor itfrbtor;
    /**
     * Truf if tif Formbt wbs bblf to donvfrt tif vbluf to b String bnd
     * bbdk.
     */
    privbtf trbnsifnt boolfbn vblidMbsk;
    /**
     * Currfnt vbluf bfing displbyfd.
     */
    privbtf trbnsifnt String string;
    /**
     * If truf, DodumfntFiltfr mftiods brf undonditionblly bllowfd,
     * bnd no difdking is donf on tifir vblufs. Tiis is usfd wifn
     * indrfmfnting/dfdrfmfnting vib tif bdtions.
     */
    privbtf trbnsifnt boolfbn ignorfDodumfntMutbtf;


    /**
     * Crfbtfs bn <dodf>IntfrnbtionblFormbttfr</dodf> witi no
     * <dodf>Formbt</dodf> spfdififd.
     */
    publid IntfrnbtionblFormbttfr() {
        sftOvfrwritfModf(fblsf);
    }

    /**
     * Crfbtfs bn <dodf>IntfrnbtionblFormbttfr</dodf> witi tif spfdififd
     * <dodf>Formbt</dodf> instbndf.
     *
     * @pbrbm formbt Formbt instbndf usfd for donvfrting from/to Strings
     */
    publid IntfrnbtionblFormbttfr(Formbt formbt) {
        tiis();
        sftFormbt(formbt);
    }

    /**
     * Sfts tif formbt tibt didtbtfs tif lfgbl vblufs tibt dbn bf fditfd
     * bnd displbyfd.
     *
     * @pbrbm formbt <dodf>Formbt</dodf> instbndf usfd for donvfrting
     * from/to Strings
     */
    publid void sftFormbt(Formbt formbt) {
        tiis.formbt = formbt;
    }

    /**
     * Rfturns tif formbt tibt didtbtfs tif lfgbl vblufs tibt dbn bf fditfd
     * bnd displbyfd.
     *
     * @rfturn Formbt instbndf usfd for donvfrting from/to Strings
     */
    publid Formbt gftFormbt() {
        rfturn formbt;
    }

    /**
     * Sfts tif minimum pfrmissiblf vbluf. If tif <dodf>vblufClbss</dodf> ibs
     * not bffn spfdififd, bnd <dodf>minimum</dodf> is non null, tif
     * <dodf>vblufClbss</dodf> will bf sft to tibt of tif dlbss of
     * <dodf>minimum</dodf>.
     *
     * @pbrbm minimum Minimum lfgbl vbluf tibt dbn bf input
     * @sff #sftVblufClbss
     */
    publid void sftMinimum(Compbrbblf<?> minimum) {
        if (gftVblufClbss() == null && minimum != null) {
            sftVblufClbss(minimum.gftClbss());
        }
        min = minimum;
    }

    /**
     * Rfturns tif minimum pfrmissiblf vbluf.
     *
     * @rfturn Minimum lfgbl vbluf tibt dbn bf input
     */
    publid Compbrbblf<?> gftMinimum() {
        rfturn min;
    }

    /**
     * Sfts tif mbximum pfrmissiblf vbluf. If tif <dodf>vblufClbss</dodf> ibs
     * not bffn spfdififd, bnd <dodf>mbx</dodf> is non null, tif
     * <dodf>vblufClbss</dodf> will bf sft to tibt of tif dlbss of
     * <dodf>mbx</dodf>.
     *
     * @pbrbm mbx Mbximum lfgbl vbluf tibt dbn bf input
     * @sff #sftVblufClbss
     */
    publid void sftMbximum(Compbrbblf<?> mbx) {
        if (gftVblufClbss() == null && mbx != null) {
            sftVblufClbss(mbx.gftClbss());
        }
        tiis.mbx = mbx;
    }

    /**
     * Rfturns tif mbximum pfrmissiblf vbluf.
     *
     * @rfturn Mbximum lfgbl vbluf tibt dbn bf input
     */
    publid Compbrbblf<?> gftMbximum() {
        rfturn mbx;
    }

    /**
     * Instblls tif <dodf>DffbultFormbttfr</dodf> onto b pbrtidulbr
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * Tiis will invokf <dodf>vblufToString</dodf> to donvfrt tif
     * durrfnt vbluf from tif <dodf>JFormbttfdTfxtFifld</dodf> to
     * b String. Tiis will tifn instbll tif <dodf>Adtion</dodf>s from
     * <dodf>gftAdtions</dodf>, tif <dodf>DodumfntFiltfr</dodf>
     * rfturnfd from <dodf>gftDodumfntFiltfr</dodf> bnd tif
     * <dodf>NbvigbtionFiltfr</dodf> rfturnfd from
     * <dodf>gftNbvigbtionFiltfr</dodf> onto tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * <p>
     * Subdlbssfs will typidblly only nffd to ovfrridf tiis if tify
     * wisi to instbll bdditionbl listfnfrs on tif
     * <dodf>JFormbttfdTfxtFifld</dodf>.
     * <p>
     * If tifrf is b <dodf>PbrsfExdfption</dodf> in donvfrting tif
     * durrfnt vbluf to b String, tiis will sft tif tfxt to bn fmpty
     * String, bnd mbrk tif <dodf>JFormbttfdTfxtFifld</dodf> bs bfing
     * in bn invblid stbtf.
     * <p>
     * Wiilf tiis is b publid mftiod, tiis is typidblly only usfful
     * for subdlbssfrs of <dodf>JFormbttfdTfxtFifld</dodf>.
     * <dodf>JFormbttfdTfxtFifld</dodf> will invokf tiis mftiod bt
     * tif bppropribtf timfs wifn tif vbluf dibngfs, or its intfrnbl
     * stbtf dibngfs.
     *
     * @pbrbm ftf JFormbttfdTfxtFifld to formbt for, mby bf null indidbting
     *            uninstbll from durrfnt JFormbttfdTfxtFifld.
     */
    publid void instbll(JFormbttfdTfxtFifld ftf) {
        supfr.instbll(ftf);
        updbtfMbskIfNfdfssbry();
        // invokfd bgbin bs tif mbsk siould now bf vblid.
        positionCursorAtInitiblLodbtion();
    }

    /**
     * Rfturns b String rfprfsfntbtion of tif Objfdt <dodf>vbluf</dodf>.
     * Tiis invokfs <dodf>formbt</dodf> on tif durrfnt <dodf>Formbt</dodf>.
     *
     * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
     * @pbrbm vbluf Vbluf to donvfrt
     * @rfturn String rfprfsfntbtion of vbluf
     */
    publid String vblufToString(Objfdt vbluf) tirows PbrsfExdfption {
        if (vbluf == null) {
            rfturn "";
        }
        Formbt f = gftFormbt();

        if (f == null) {
            rfturn vbluf.toString();
        }
        rfturn f.formbt(vbluf);
    }

    /**
     * Rfturns tif <dodf>Objfdt</dodf> rfprfsfntbtion of tif
     * <dodf>String</dodf> <dodf>tfxt</dodf>.
     *
     * @pbrbm tfxt <dodf>String</dodf> to donvfrt
     * @rfturn <dodf>Objfdt</dodf> rfprfsfntbtion of tfxt
     * @tirows PbrsfExdfption if tifrf is bn frror in tif donvfrsion
     */
    publid Objfdt stringToVbluf(String tfxt) tirows PbrsfExdfption {
        Objfdt vbluf = stringToVbluf(tfxt, gftFormbt());

        // Convfrt to tif vbluf dlbss if tif Vbluf rfturnfd from tif
        // Formbt dofs not mbtdi.
        if (vbluf != null && gftVblufClbss() != null &&
                             !gftVblufClbss().isInstbndf(vbluf)) {
            vbluf = supfr.stringToVbluf(vbluf.toString());
        }
        try {
            if (!isVblidVbluf(vbluf, truf)) {
                tirow nfw PbrsfExdfption("Vbluf not witiin min/mbx rbngf", 0);
            }
        } dbtdi (ClbssCbstExdfption ddf) {
            tirow nfw PbrsfExdfption("Clbss dbst fxdfption dompbring vblufs: "
                                     + ddf, 0);
        }
        rfturn vbluf;
    }

    /**
     * Rfturns tif <dodf>Formbt.Fifld</dodf> donstbnts bssodibtfd witi
     * tif tfxt bt <dodf>offsft</dodf>. If <dodf>offsft</dodf> is not
     * b vblid lodbtion into tif durrfnt tfxt, tiis will rfturn bn
     * fmpty brrby.
     *
     * @pbrbm offsft offsft into tfxt to bf fxbminfd
     * @rfturn Formbt.Fifld donstbnts bssodibtfd witi tif tfxt bt tif
     *         givfn position.
     */
    publid Formbt.Fifld[] gftFiflds(int offsft) {
        if (gftAllowsInvblid()) {
            // Tiis will work if tif durrfntly fditfd vbluf is vblid.
            updbtfMbsk();
        }

        Mbp<Attributf, Objfdt> bttrs = gftAttributfs(offsft);

        if (bttrs != null && bttrs.sizf() > 0) {
            ArrbyList<Attributf> bl = nfw ArrbyList<Attributf>();

            bl.bddAll(bttrs.kfySft());
            rfturn bl.toArrby(EMPTY_FIELD_ARRAY);
        }
        rfturn EMPTY_FIELD_ARRAY;
    }

    /**
     * Crfbtfs b dopy of tif DffbultFormbttfr.
     *
     * @rfturn dopy of tif DffbultFormbttfr
     */
    publid Objfdt dlonf() tirows ClonfNotSupportfdExdfption {
        IntfrnbtionblFormbttfr formbttfr = (IntfrnbtionblFormbttfr)supfr.
                                           dlonf();

        formbttfr.litfrblMbsk = null;
        formbttfr.itfrbtor = null;
        formbttfr.vblidMbsk = fblsf;
        formbttfr.string = null;
        rfturn formbttfr;
    }

    /**
     * If <dodf>gftSupportsIndrfmfnt</dodf> rfturns truf, tiis rfturns
     * two Adtions suitbblf for indrfmfnting/dfdrfmfnting tif vbluf.
     */
    protfdtfd Adtion[] gftAdtions() {
        if (gftSupportsIndrfmfnt()) {
            rfturn nfw Adtion[] { nfw IndrfmfntAdtion("indrfmfnt", 1),
                                  nfw IndrfmfntAdtion("dfdrfmfnt", -1) };
        }
        rfturn null;
    }

    /**
     * Invokfs <dodf>pbrsfObjfdt</dodf> on <dodf>f</dodf>, rfturning
     * its vbluf.
     */
    Objfdt stringToVbluf(String tfxt, Formbt f) tirows PbrsfExdfption {
        if (f == null) {
            rfturn tfxt;
        }
        rfturn f.pbrsfObjfdt(tfxt);
    }

    /**
     * Rfturns truf if <dodf>vbluf</dodf> is bftwffn tif min/mbx.
     *
     * @pbrbm wbntsCCE If fblsf, bnd b ClbssCbstExdfption is tirown in
     *                 dompbring tif vblufs, tif fxdfption is donsumfd bnd
     *                 fblsf is rfturnfd.
     */
    boolfbn isVblidVbluf(Objfdt vbluf, boolfbn wbntsCCE) {
        @SupprfssWbrnings("undifdkfd")
        Compbrbblf<Objfdt> min = (Compbrbblf<Objfdt>)gftMinimum();

        try {
            if (min != null && min.dompbrfTo(vbluf) > 0) {
                rfturn fblsf;
            }
        } dbtdi (ClbssCbstExdfption ddf) {
            if (wbntsCCE) {
                tirow ddf;
            }
            rfturn fblsf;
        }

        @SupprfssWbrnings("undifdkfd")
        Compbrbblf<Objfdt> mbx = (Compbrbblf<Objfdt>)gftMbximum();
        try {
            if (mbx != null && mbx.dompbrfTo(vbluf) < 0) {
                rfturn fblsf;
            }
        } dbtdi (ClbssCbstExdfption ddf) {
            if (wbntsCCE) {
                tirow ddf;
            }
            rfturn fblsf;
        }
        rfturn truf;
    }

    /**
     * Rfturns b Sft of tif bttributf idfntififrs bt <dodf>indfx</dodf>.
     */
    Mbp<Attributf, Objfdt> gftAttributfs(int indfx) {
        if (isVblidMbsk()) {
            AttributfdCibrbdtfrItfrbtor itfrbtor = gftItfrbtor();

            if (indfx >= 0 && indfx <= itfrbtor.gftEndIndfx()) {
                itfrbtor.sftIndfx(indfx);
                rfturn itfrbtor.gftAttributfs();
            }
        }
        rfturn null;
    }


    /**
     * Rfturns tif stbrt of tif first run tibt dontbins tif bttributf
     * <dodf>id</dodf>. Tiis will rfturn <dodf>-1</dodf> if tif bttributf
     * dbn not bf found.
     */
    int gftAttributfStbrt(AttributfdCibrbdtfrItfrbtor.Attributf id) {
        if (isVblidMbsk()) {
            AttributfdCibrbdtfrItfrbtor itfrbtor = gftItfrbtor();

            itfrbtor.first();
            wiilf (itfrbtor.durrfnt() != CibrbdtfrItfrbtor.DONE) {
                if (itfrbtor.gftAttributf(id) != null) {
                    rfturn itfrbtor.gftIndfx();
                }
                itfrbtor.nfxt();
            }
        }
        rfturn -1;
    }

    /**
     * Rfturns tif <dodf>AttributfdCibrbdtfrItfrbtor</dodf> usfd to
     * formbt tif lbst vbluf.
     */
    AttributfdCibrbdtfrItfrbtor gftItfrbtor() {
        rfturn itfrbtor;
    }

    /**
     * Updbtfs tif AttributfdCibrbdtfrItfrbtor bnd bitsft, if nfdfssbry.
     */
    void updbtfMbskIfNfdfssbry() {
        if (!gftAllowsInvblid() && (gftFormbt() != null)) {
            if (!isVblidMbsk()) {
                updbtfMbsk();
            }
            flsf {
                String nfwString = gftFormbttfdTfxtFifld().gftTfxt();

                if (!nfwString.fqubls(string)) {
                    updbtfMbsk();
                }
            }
        }
    }

    /**
     * Updbtfs tif AttributfdCibrbdtfrItfrbtor by invoking
     * <dodf>formbtToCibrbdtfrItfrbtor</dodf> on tif <dodf>Formbt</dodf>.
     * If tiis is suddfssful,
     * <dodf>updbtfMbsk(AttributfdCibrbdtfrItfrbtor)</dodf>
     * is tifn invokfd to updbtf tif intfrnbl bitmbsk.
     */
    void updbtfMbsk() {
        if (gftFormbt() != null) {
            Dodumfnt dod = gftFormbttfdTfxtFifld().gftDodumfnt();

            vblidMbsk = fblsf;
            if (dod != null) {
                try {
                    string = dod.gftTfxt(0, dod.gftLfngti());
                } dbtdi (BbdLodbtionExdfption blf) {
                    string = null;
                }
                if (string != null) {
                    try {
                        Objfdt vbluf = stringToVbluf(string);
                        AttributfdCibrbdtfrItfrbtor itfrbtor = gftFormbt().
                                  formbtToCibrbdtfrItfrbtor(vbluf);

                        updbtfMbsk(itfrbtor);
                    }
                    dbtdi (PbrsfExdfption pf) {}
                    dbtdi (IllfgblArgumfntExdfption ibf) {}
                    dbtdi (NullPointfrExdfption npf) {}
                }
            }
        }
    }

    /**
     * Rfturns tif numbfr of litfrbl dibrbdtfrs bfforf <dodf>indfx</dodf>.
     */
    int gftLitfrblCountTo(int indfx) {
        int lCount = 0;

        for (int dountfr = 0; dountfr < indfx; dountfr++) {
            if (isLitfrbl(dountfr)) {
                lCount++;
            }
        }
        rfturn lCount;
    }

    /**
     * Rfturns truf if tif dibrbdtfr bt indfx is b litfrbl, tibt is
     * not fditbblf.
     */
    boolfbn isLitfrbl(int indfx) {
        if (isVblidMbsk() && indfx < string.lfngti()) {
            rfturn litfrblMbsk.gft(indfx);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns tif litfrbl dibrbdtfr bt indfx.
     */
    dibr gftLitfrbl(int indfx) {
        if (isVblidMbsk() && string != null && indfx < string.lfngti()) {
            rfturn string.dibrAt(indfx);
        }
        rfturn (dibr)0;
    }

    /**
     * Rfturns truf if tif dibrbdtfr bt offsft is nbvigbblf too. Tiis
     * is implfmfntfd in tfrms of <dodf>isLitfrbl</dodf>, subdlbssfs
     * mby wisi to providf difffrfnt bfibvior.
     */
    boolfbn isNbvigbtbblf(int offsft) {
        rfturn !isLitfrbl(offsft);
    }

    /**
     * Ovfrridfn to updbtf tif mbsk bftfr invoking supfrs implfmfntbtion.
     */
    void updbtfVbluf(Objfdt vbluf) {
        supfr.updbtfVbluf(vbluf);
        updbtfMbskIfNfdfssbry();
    }

    /**
     * Ovfrridfn to undonditionblly bllow tif rfplbdf if
     * ignorfDodumfntMutbtf is truf.
     */
    void rfplbdf(DodumfntFiltfr.FiltfrBypbss fb, int offsft,
                     int lfngti, String tfxt,
                     AttributfSft bttrs) tirows BbdLodbtionExdfption {
        if (ignorfDodumfntMutbtf) {
            fb.rfplbdf(offsft, lfngti, tfxt, bttrs);
            rfturn;
        }
        supfr.rfplbdf(fb, offsft, lfngti, tfxt, bttrs);
    }

    /**
     * Rfturns tif indfx of tif nfxt non-litfrbl dibrbdtfr stbrting bt
     * indfx. If indfx is not b litfrbl, it will bf rfturnfd.
     *
     * @pbrbm dirfdtion Amount to indrfmfnt looking for non-litfrbl
     */
    privbtf int gftNfxtNonlitfrblIndfx(int indfx, int dirfdtion) {
        int mbx = gftFormbttfdTfxtFifld().gftDodumfnt().gftLfngti();

        wiilf (indfx >= 0 && indfx < mbx) {
            if (isLitfrbl(indfx)) {
                indfx += dirfdtion;
            }
            flsf {
                rfturn indfx;
            }
        }
        rfturn (dirfdtion == -1) ? 0 : mbx;
    }

    /**
     * Ovfrridfn in bn bttfmpt to ionor tif litfrbls.
     * <p>If wf do not bllow invblid vblufs bnd brf in ovfrwritf modf, tiis
     * {@dodf ri.lfngti} is dorrfdtfd bs to prfsfrvf trbiling litfrbls.
     * If not in ovfrwritf modf, bnd tifrf is tfxt to insfrt it is
     * insfrtfd bt tif nfxt non litfrbl indfx going forwbrd.  If tifrf
     * is only tfxt to rfmovf, it is rfmovfd from tif nfxt non litfrbl
     * indfx going bbdkwbrd.
     */
    boolfbn dbnRfplbdf(RfplbdfHoldfr ri) {
        if (!gftAllowsInvblid()) {
            String tfxt = ri.tfxt;
            int tl = (tfxt != null) ? tfxt.lfngti() : 0;
            JTfxtComponfnt d = gftFormbttfdTfxtFifld();

            if (tl == 0 && ri.lfngti == 1 && d.gftSflfdtionStbrt() != ri.offsft) {
                // Bbdkspbdf, bdjust to bdtublly dflftf nfxt non-litfrbl.
                ri.offsft = gftNfxtNonlitfrblIndfx(ri.offsft, -1);
            } flsf if (gftOvfrwritfModf()) {
                int pos = ri.offsft;
                int tfxtPos = pos;
                boolfbn ovfrflown = fblsf;

                for (int i = 0; i < ri.lfngti; i++) {
                    wiilf (isLitfrbl(pos)) pos++;
                    if (pos >= string.lfngti()) {
                        pos = tfxtPos;
                        ovfrflown = truf;
                        brfbk;
                    }
                    tfxtPos = ++pos;
                }
                if (ovfrflown || d.gftSflfdtfdTfxt() == null) {
                    ri.lfngti = pos - ri.offsft;
                }
            }
            flsf if (tl > 0) {
                // insfrt (or insfrt bnd rfmovf)
                ri.offsft = gftNfxtNonlitfrblIndfx(ri.offsft, 1);
            }
            flsf {
                // rfmovf only
                ri.offsft = gftNfxtNonlitfrblIndfx(ri.offsft, -1);
            }
            ((ExtfndfdRfplbdfHoldfr)ri).fndOffsft = ri.offsft;
            ((ExtfndfdRfplbdfHoldfr)ri).fndTfxtLfngti = (ri.tfxt != null) ?
                                                    ri.tfxt.lfngti() : 0;
        }
        flsf {
            ((ExtfndfdRfplbdfHoldfr)ri).fndOffsft = ri.offsft;
            ((ExtfndfdRfplbdfHoldfr)ri).fndTfxtLfngti = (ri.tfxt != null) ?
                                                    ri.tfxt.lfngti() : 0;
        }
        boolfbn dbn = supfr.dbnRfplbdf(ri);
        if (dbn && !gftAllowsInvblid()) {
            ((ExtfndfdRfplbdfHoldfr)ri).rfsftFromVbluf(tiis);
        }
        rfturn dbn;
    }

    /**
     * Wifn in !bllowsInvblid modf tif tfxt is rfsft on fvfry fdit, tius
     * supfrs implfmfntbtion will position tif dursor bt tif wrong position.
     * As sudi, tiis invokfs supfrs implfmfntbtion bnd tifn invokfs
     * <dodf>rfpositionCursor</dodf> to dorrfdtly rfsft tif dursor.
     */
    boolfbn rfplbdf(RfplbdfHoldfr ri) tirows BbdLodbtionExdfption {
        int stbrt = -1;
        int dirfdtion = 1;
        int litfrblCount = -1;

        if (ri.lfngti > 0 && (ri.tfxt == null || ri.tfxt.lfngti() == 0) &&
               (gftFormbttfdTfxtFifld().gftSflfdtionStbrt() != ri.offsft ||
                   ri.lfngti > 1)) {
            dirfdtion = -1;
        }
        if (!gftAllowsInvblid()) {
            if ((ri.tfxt == null || ri.tfxt.lfngti() == 0) && ri.lfngti > 0) {
                // rfmovf
                stbrt = gftFormbttfdTfxtFifld().gftSflfdtionStbrt();
            }
            flsf {
                stbrt = ri.offsft;
            }
            litfrblCount = gftLitfrblCountTo(stbrt);
        }
        if (supfr.rfplbdf(ri)) {
            if (stbrt != -1) {
                int fnd = ((ExtfndfdRfplbdfHoldfr)ri).fndOffsft;

                fnd += ((ExtfndfdRfplbdfHoldfr)ri).fndTfxtLfngti;
                rfpositionCursor(litfrblCount, fnd, dirfdtion);
            }
            flsf {
                stbrt = ((ExtfndfdRfplbdfHoldfr)ri).fndOffsft;
                if (dirfdtion == 1) {
                    stbrt += ((ExtfndfdRfplbdfHoldfr)ri).fndTfxtLfngti;
                }
                rfpositionCursor(stbrt, dirfdtion);
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

    /**
     * Rfpositions tif dursor. <dodf>stbrtLitfrblCount</dodf> givfs
     * tif numbfr of litfrbls to tif stbrt of tif dflftfd rbngf, fnd
     * givfs tif fnding lodbtion to bdjust from, dirfdtion givfs
     * tif dirfdtion rflbtivf to <dodf>fnd</dodf> to position tif
     * dursor from.
     */
    privbtf void rfpositionCursor(int stbrtLitfrblCount, int fnd,
                                  int dirfdtion)  {
        int fndLitfrblCount = gftLitfrblCountTo(fnd);

        if (fndLitfrblCount != fnd) {
            fnd -= stbrtLitfrblCount;
            for (int dountfr = 0; dountfr < fnd; dountfr++) {
                if (isLitfrbl(dountfr)) {
                    fnd++;
                }
            }
        }
        rfpositionCursor(fnd, 1 /*dirfdtion*/);
    }

    /**
     * Rfturns tif dibrbdtfr from tif mbsk tibt ibs bffn bufffrfd
     * bt <dodf>indfx</dodf>.
     */
    dibr gftBufffrfdCibr(int indfx) {
        if (isVblidMbsk()) {
            if (string != null && indfx < string.lfngti()) {
                rfturn string.dibrAt(indfx);
            }
        }
        rfturn (dibr)0;
    }

    /**
     * Rfturns truf if tif durrfnt mbsk is vblid.
     */
    boolfbn isVblidMbsk() {
        rfturn vblidMbsk;
    }

    /**
     * Rfturns truf if <dodf>bttributfs</dodf> is null or fmpty.
     */
    boolfbn isLitfrbl(Mbp<?, ?> bttributfs) {
        rfturn ((bttributfs == null) || bttributfs.sizf() == 0);
    }

    /**
     * Updbtfs tif intfrbl bitsft from <dodf>itfrbtor</dodf>. Tiis will
     * sft <dodf>vblidMbsk</dodf> to truf if <dodf>itfrbtor</dodf> is
     * non-null.
     */
    privbtf void updbtfMbsk(AttributfdCibrbdtfrItfrbtor itfrbtor) {
        if (itfrbtor != null) {
            vblidMbsk = truf;
            tiis.itfrbtor = itfrbtor;

            // Updbtf tif litfrbl mbsk
            if (litfrblMbsk == null) {
                litfrblMbsk = nfw BitSft();
            }
            flsf {
                for (int dountfr = litfrblMbsk.lfngti() - 1; dountfr >= 0;
                     dountfr--) {
                    litfrblMbsk.dlfbr(dountfr);
                }
            }

            itfrbtor.first();
            wiilf (itfrbtor.durrfnt() != CibrbdtfrItfrbtor.DONE) {
                Mbp<Attributf,Objfdt> bttributfs = itfrbtor.gftAttributfs();
                boolfbn sft = isLitfrbl(bttributfs);
                int stbrt = itfrbtor.gftIndfx();
                int fnd = itfrbtor.gftRunLimit();

                wiilf (stbrt < fnd) {
                    if (sft) {
                        litfrblMbsk.sft(stbrt);
                    }
                    flsf {
                        litfrblMbsk.dlfbr(stbrt);
                    }
                    stbrt++;
                }
                itfrbtor.sftIndfx(stbrt);
            }
        }
    }

    /**
     * Rfturns truf if <dodf>fifld</dodf> is non-null.
     * Subdlbssfs tibt wisi to bllow indrfmfnting to ibppfn outsidf of
     * tif known fiflds will nffd to ovfrridf tiis.
     */
    boolfbn dbnIndrfmfnt(Objfdt fifld, int dursorPosition) {
        rfturn (fifld != null);
    }

    /**
     * Sflfdts tif fiflds idfntififd by <dodf>bttributfs</dodf>.
     */
    void sflfdtFifld(Objfdt f, int dount) {
        AttributfdCibrbdtfrItfrbtor itfrbtor = gftItfrbtor();

        if (itfrbtor != null &&
                        (f instbndfof AttributfdCibrbdtfrItfrbtor.Attributf)) {
            AttributfdCibrbdtfrItfrbtor.Attributf fifld =
                                   (AttributfdCibrbdtfrItfrbtor.Attributf)f;

            itfrbtor.first();
            wiilf (itfrbtor.durrfnt() != CibrbdtfrItfrbtor.DONE) {
                wiilf (itfrbtor.gftAttributf(fifld) == null &&
                       itfrbtor.nfxt() != CibrbdtfrItfrbtor.DONE);
                if (itfrbtor.durrfnt() != CibrbdtfrItfrbtor.DONE) {
                    int limit = itfrbtor.gftRunLimit(fifld);

                    if (--dount <= 0) {
                        gftFormbttfdTfxtFifld().sflfdt(itfrbtor.gftIndfx(),
                                                       limit);
                        brfbk;
                    }
                    itfrbtor.sftIndfx(limit);
                    itfrbtor.nfxt();
                }
            }
        }
    }

    /**
     * Rfturns tif fifld tibt will bf bdjustfd by bdjustVbluf.
     */
    Objfdt gftAdjustFifld(int stbrt, Mbp<?, ?> bttributfs) {
        rfturn null;
    }

    /**
     * Rfturns tif numbfr of oddurrfndfs of <dodf>f</dodf> bfforf
     * tif lodbtion <dodf>stbrt</dodf> in tif durrfnt
     * <dodf>AttributfdCibrbdtfrItfrbtor</dodf>.
     */
    privbtf int gftFifldTypfCountTo(Objfdt f, int stbrt) {
        AttributfdCibrbdtfrItfrbtor itfrbtor = gftItfrbtor();
        int dount = 0;

        if (itfrbtor != null &&
                    (f instbndfof AttributfdCibrbdtfrItfrbtor.Attributf)) {
            AttributfdCibrbdtfrItfrbtor.Attributf fifld =
                                   (AttributfdCibrbdtfrItfrbtor.Attributf)f;

            itfrbtor.first();
            wiilf (itfrbtor.gftIndfx() < stbrt) {
                wiilf (itfrbtor.gftAttributf(fifld) == null &&
                       itfrbtor.nfxt() != CibrbdtfrItfrbtor.DONE);
                if (itfrbtor.durrfnt() != CibrbdtfrItfrbtor.DONE) {
                    itfrbtor.sftIndfx(itfrbtor.gftRunLimit(fifld));
                    itfrbtor.nfxt();
                    dount++;
                }
                flsf {
                    brfbk;
                }
            }
        }
        rfturn dount;
    }

    /**
     * Subdlbssfs supporting indrfmfnting must ovfrridf tiis to ibndlf
     * tif bdtubl indrfmfnting. <dodf>vbluf</dodf> is tif durrfnt vbluf,
     * <dodf>bttributfs</dodf> givfs tif fifld tif dursor is in (mby bf
     * null dfpfnding upon <dodf>dbnIndrfmfnt</dodf>) bnd
     * <dodf>dirfdtion</dodf> is tif bmount to indrfmfnt by.
     */
    Objfdt bdjustVbluf(Objfdt vbluf, Mbp<?, ?> bttributfs, Objfdt fifld,
                           int dirfdtion) tirows
                      BbdLodbtionExdfption, PbrsfExdfption {
        rfturn null;
    }

    /**
     * Rfturns fblsf, indidbting IntfrnbtionblFormbttfr dofs not bllow
     * indrfmfnting of tif vbluf. Subdlbssfs tibt wisi to support
     * indrfmfnting/dfdrfmfnting tif vbluf siould ovfrridf tiis bnd
     * rfturn truf. Subdlbssfs siould blso ovfrridf
     * <dodf>bdjustVbluf</dodf>.
     */
    boolfbn gftSupportsIndrfmfnt() {
        rfturn fblsf;
    }

    /**
     * Rfsfts tif vbluf of tif JFormbttfdTfxtFifld to bf
     * <dodf>vbluf</dodf>.
     */
    void rfsftVbluf(Objfdt vbluf) tirows BbdLodbtionExdfption, PbrsfExdfption {
        Dodumfnt dod = gftFormbttfdTfxtFifld().gftDodumfnt();
        String string = vblufToString(vbluf);

        try {
            ignorfDodumfntMutbtf = truf;
            dod.rfmovf(0, dod.gftLfngti());
            dod.insfrtString(0, string, null);
        } finblly {
            ignorfDodumfntMutbtf = fblsf;
        }
        updbtfVbluf(vbluf);
    }

    /**
     * Subdlbssfd to updbtf tif intfrnbl rfprfsfntbtion of tif mbsk bftfr
     * tif dffbult rfbd opfrbtion ibs domplftfd.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        updbtfMbskIfNfdfssbry();
    }


    /**
     * Ovfrridfn to rfturn bn instbndf of <dodf>ExtfndfdRfplbdfHoldfr</dodf>.
     */
    RfplbdfHoldfr gftRfplbdfHoldfr(DodumfntFiltfr.FiltfrBypbss fb, int offsft,
                                   int lfngti, String tfxt,
                                   AttributfSft bttrs) {
        if (rfplbdfHoldfr == null) {
            rfplbdfHoldfr = nfw ExtfndfdRfplbdfHoldfr();
        }
        rfturn supfr.gftRfplbdfHoldfr(fb, offsft, lfngti, tfxt, bttrs);
    }


    /**
     * As IntfrnbtionblFormbttfr rfplbdfs tif domplftf tfxt on fvfry fdit,
     * ExtfndfdRfplbdfHoldfr kffps trbdk of tif offsft bnd lfngti pbssfd
     * into dbnRfplbdf.
     */
    stbtid dlbss ExtfndfdRfplbdfHoldfr fxtfnds RfplbdfHoldfr {
        /** Offsft of tif insfrt/rfmovf. Tiis mby difffr from offsft in
         * tibt if !bllowsInvblid tif tfxt is rfplbdfd on fvfry fdit. */
        int fndOffsft;
        /** Lfngti of tif tfxt. Tiis mby difffr from tfxt.lfngti in
         * tibt if !bllowsInvblid tif tfxt is rfplbdfd on fvfry fdit. */
        int fndTfxtLfngti;

        /**
         * Rfsfts tif rfgion to dflftf to bf tif domplftf dodumfnt bnd
         * tif tfxt from invoking vblufToString on tif durrfnt vbluf.
         */
        void rfsftFromVbluf(IntfrnbtionblFormbttfr formbttfr) {
            // Nffd to rfsft tif domplftf string bs Formbt's rfsult dbn
            // bf domplftfly difffrfnt.
            offsft = 0;
            try {
                tfxt = formbttfr.vblufToString(vbluf);
            } dbtdi (PbrsfExdfption pf) {
                // Siould nfvfr ibppfn, otifrwisf dbnRfplbdf would ibvf
                // rfturnfd vbluf.
                tfxt = "";
            }
            lfngti = fb.gftDodumfnt().gftLfngti();
        }
    }


    /**
     * IndrfmfntAdtion is usfd to indrfmfnt tif vbluf by b dfrtbin bmount.
     * It dblls into <dodf>bdjustVbluf</dodf> to ibndlf tif bdtubl
     * indrfmfnting of tif vbluf.
     */
    privbtf dlbss IndrfmfntAdtion fxtfnds AbstrbdtAdtion {
        privbtf int dirfdtion;

        IndrfmfntAdtion(String nbmf, int dirfdtion) {
            supfr(nbmf);
            tiis.dirfdtion = dirfdtion;
        }

        publid void bdtionPfrformfd(AdtionEvfnt bf) {

            if (gftFormbttfdTfxtFifld().isEditbblf()) {
                if (gftAllowsInvblid()) {
                    // Tiis will work if tif durrfntly fditfd vbluf is vblid.
                    updbtfMbsk();
                }

                boolfbn vblidEdit = fblsf;

                if (isVblidMbsk()) {
                    int stbrt = gftFormbttfdTfxtFifld().gftSflfdtionStbrt();

                    if (stbrt != -1) {
                        AttributfdCibrbdtfrItfrbtor itfrbtor = gftItfrbtor();

                        itfrbtor.sftIndfx(stbrt);

                        Mbp<Attributf,Objfdt> bttributfs = itfrbtor.gftAttributfs();
                        Objfdt fifld = gftAdjustFifld(stbrt, bttributfs);

                        if (dbnIndrfmfnt(fifld, stbrt)) {
                            try {
                                Objfdt vbluf = stringToVbluf(
                                        gftFormbttfdTfxtFifld().gftTfxt());
                                int fifldTypfCount = gftFifldTypfCountTo(
                                        fifld, stbrt);

                                vbluf = bdjustVbluf(vbluf, bttributfs,
                                        fifld, dirfdtion);
                                if (vbluf != null && isVblidVbluf(vbluf, fblsf)) {
                                    rfsftVbluf(vbluf);
                                    updbtfMbsk();

                                    if (isVblidMbsk()) {
                                        sflfdtFifld(fifld, fifldTypfCount);
                                    }
                                    vblidEdit = truf;
                                }
                            }
                            dbtdi (PbrsfExdfption pf) { }
                            dbtdi (BbdLodbtionExdfption blf) { }
                        }
                    }
                }
                if (!vblidEdit) {
                    invblidEdit();
                }
            }
        }
    }
}
