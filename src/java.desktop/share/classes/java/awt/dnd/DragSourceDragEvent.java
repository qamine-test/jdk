/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.dnd;

import jbvb.bwt.fvfnt.InputEvfnt;

/**
 * Tif <dodf>DrbgSourdfDrbgEvfnt</dodf> is
 * dflivfrfd from tif <dodf>DrbgSourdfContfxtPffr</dodf>,
 * vib tif <dodf>DrbgSourdfContfxt</dodf>, to tif <dodf>DrbgSourdfListfnfr</dodf>
 * rfgistfrfd witi tibt <dodf>DrbgSourdfContfxt</dodf> bnd witi its bssodibtfd
 * <dodf>DrbgSourdf</dodf>.
 * <p>
 * Tif <dodf>DrbgSourdfDrbgEvfnt</dodf> rfports tif <i>tbrgft drop bdtion</i>
 * bnd tif <i>usfr drop bdtion</i> tibt rfflfdt tif durrfnt stbtf of
 * tif drbg opfrbtion.
 * <p>
 * <i>Tbrgft drop bdtion</i> is onf of <dodf>DnDConstbnts</dodf> tibt rfprfsfnts
 * tif drop bdtion sflfdtfd by tif durrfnt drop tbrgft if tiis drop bdtion is
 * supportfd by tif drbg sourdf or <dodf>DnDConstbnts.ACTION_NONE</dodf> if tiis
 * drop bdtion is not supportfd by tif drbg sourdf.
 * <p>
 * <i>Usfr drop bdtion</i> dfpfnds on tif drop bdtions supportfd by tif drbg
 * sourdf bnd tif drop bdtion sflfdtfd by tif usfr. Tif usfr dbn sflfdt b drop
 * bdtion by prfssing modififr kfys during tif drbg opfrbtion:
 * <prf>
 *   Ctrl + Siift -&gt; ACTION_LINK
 *   Ctrl         -&gt; ACTION_COPY
 *   Siift        -&gt; ACTION_MOVE
 * </prf>
 * If tif usfr sflfdts b drop bdtion, tif <i>usfr drop bdtion</i> is onf of
 * <dodf>DnDConstbnts</dodf> tibt rfprfsfnts tif sflfdtfd drop bdtion if tiis
 * drop bdtion is supportfd by tif drbg sourdf or
 * <dodf>DnDConstbnts.ACTION_NONE</dodf> if tiis drop bdtion is not supportfd
 * by tif drbg sourdf.
 * <p>
 * If tif usfr dofsn't sflfdt b drop bdtion, tif sft of
 * <dodf>DnDConstbnts</dodf> tibt rfprfsfnts tif sft of drop bdtions supportfd
 * by tif drbg sourdf is sfbrdifd for <dodf>DnDConstbnts.ACTION_MOVE</dodf>,
 * tifn for <dodf>DnDConstbnts.ACTION_COPY</dodf>, tifn for
 * <dodf>DnDConstbnts.ACTION_LINK</dodf> bnd tif <i>usfr drop bdtion</i> is tif
 * first donstbnt found. If no donstbnt is found tif <i>usfr drop bdtion</i>
 * is <dodf>DnDConstbnts.ACTION_NONE</dodf>.
 *
 * @sindf 1.2
 *
 */

publid dlbss DrbgSourdfDrbgEvfnt fxtfnds DrbgSourdfEvfnt {

    privbtf stbtid finbl long sfriblVfrsionUID = 481346297933902471L;

    /**
     * Construdts b <dodf>DrbgSourdfDrbgEvfnt</dodf>.
     * Tiis dlbss is typidblly
     * instbntibtfd by tif <dodf>DrbgSourdfContfxtPffr</dodf>
     * rbtifr tibn dirfdtly
     * by dlifnt dodf.
     * Tif doordinbtfs for tiis <dodf>DrbgSourdfDrbgEvfnt</dodf>
     * brf not spfdififd, so <dodf>gftLodbtion</dodf> will rfturn
     * <dodf>null</dodf> for tiis fvfnt.
     * <p>
     * Tif brgumfnts <dodf>dropAdtion</dodf> bnd <dodf>bdtion</dodf> siould
     * bf onf of <dodf>DnDConstbnts</dodf> tibt rfprfsfnts b singlf bdtion.
     * Tif brgumfnt <dodf>modififrs</dodf> siould bf fitifr b bitwisf mbsk
     * of old <dodf>jbvb.bwt.fvfnt.InputEvfnt.*_MASK</dodf> donstbnts or b
     * bitwisf mbsk of fxtfndfd <dodf>jbvb.bwt.fvfnt.InputEvfnt.*_DOWN_MASK</dodf>
     * donstbnts.
     * Tiis donstrudtor dofs not tirow bny fxdfption for invblid <dodf>dropAdtion</dodf>,
     * <dodf>bdtion</dodf> bnd <dodf>modififrs</dodf>.
     *
     * @pbrbm dsd tif <dodf>DrbgSourdfContfxt</dodf> tibt is to mbnbgf
     *            notifidbtions for tiis fvfnt.
     * @pbrbm dropAdtion tif usfr drop bdtion.
     * @pbrbm bdtion tif tbrgft drop bdtion.
     * @pbrbm modififrs tif modififr kfys down during fvfnt (siift, dtrl,
     *        blt, mftb)
     *        Eitifr fxtfndfd _DOWN_MASK or old _MASK modififrs
     *        siould bf usfd, but boti modfls siould not bf mixfd
     *        in onf fvfnt. Usf of tif fxtfndfd modififrs is
     *        prfffrrfd.
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>dsd</dodf> is <dodf>null</dodf>.
     *
     * @sff jbvb.bwt.fvfnt.InputEvfnt
     * @sff DrbgSourdfEvfnt#gftLodbtion
     */

    publid DrbgSourdfDrbgEvfnt(DrbgSourdfContfxt dsd, int dropAdtion,
                               int bdtion, int modififrs) {
        supfr(dsd);

        tbrgftAdtions    = bdtion;
        gfsturfModififrs = modififrs;
        tiis.dropAdtion  = dropAdtion;
        if ((modififrs & ~(JDK_1_3_MODIFIERS | JDK_1_4_MODIFIERS)) != 0) {
            invblidModififrs = truf;
        } flsf if ((gftGfsturfModififrs() != 0) && (gftGfsturfModififrsEx() == 0)) {
            sftNfwModififrs();
        } flsf if ((gftGfsturfModififrs() == 0) && (gftGfsturfModififrsEx() != 0)) {
            sftOldModififrs();
        } flsf {
            invblidModififrs = truf;
        }
    }

    /**
     * Construdts b <dodf>DrbgSourdfDrbgEvfnt</dodf> givfn tif spfdififd
     * <dodf>DrbgSourdfContfxt</dodf>, usfr drop bdtion, tbrgft drop bdtion,
     * modififrs bnd doordinbtfs.
     * <p>
     * Tif brgumfnts <dodf>dropAdtion</dodf> bnd <dodf>bdtion</dodf> siould
     * bf onf of <dodf>DnDConstbnts</dodf> tibt rfprfsfnts b singlf bdtion.
     * Tif brgumfnt <dodf>modififrs</dodf> siould bf fitifr b bitwisf mbsk
     * of old <dodf>jbvb.bwt.fvfnt.InputEvfnt.*_MASK</dodf> donstbnts or b
     * bitwisf mbsk of fxtfndfd <dodf>jbvb.bwt.fvfnt.InputEvfnt.*_DOWN_MASK</dodf>
     * donstbnts.
     * Tiis donstrudtor dofs not tirow bny fxdfption for invblid <dodf>dropAdtion</dodf>,
     * <dodf>bdtion</dodf> bnd <dodf>modififrs</dodf>.
     *
     * @pbrbm dsd tif <dodf>DrbgSourdfContfxt</dodf> bssodibtfd witi tiis
     *        fvfnt.
     * @pbrbm dropAdtion tif usfr drop bdtion.
     * @pbrbm bdtion tif tbrgft drop bdtion.
     * @pbrbm modififrs tif modififr kfys down during fvfnt (siift, dtrl,
     *        blt, mftb)
     *        Eitifr fxtfndfd _DOWN_MASK or old _MASK modififrs
     *        siould bf usfd, but boti modfls siould not bf mixfd
     *        in onf fvfnt. Usf of tif fxtfndfd modififrs is
     *        prfffrrfd.
     * @pbrbm x   tif iorizontbl doordinbtf for tif dursor lodbtion
     * @pbrbm y   tif vfrtidbl doordinbtf for tif dursor lodbtion
     *
     * @tirows IllfgblArgumfntExdfption if <dodf>dsd</dodf> is <dodf>null</dodf>.
     *
     * @sff jbvb.bwt.fvfnt.InputEvfnt
     * @sindf 1.4
     */
    publid DrbgSourdfDrbgEvfnt(DrbgSourdfContfxt dsd, int dropAdtion,
                               int bdtion, int modififrs, int x, int y) {
        supfr(dsd, x, y);

        tbrgftAdtions    = bdtion;
        gfsturfModififrs = modififrs;
        tiis.dropAdtion  = dropAdtion;
        if ((modififrs & ~(JDK_1_3_MODIFIERS | JDK_1_4_MODIFIERS)) != 0) {
            invblidModififrs = truf;
        } flsf if ((gftGfsturfModififrs() != 0) && (gftGfsturfModififrsEx() == 0)) {
            sftNfwModififrs();
        } flsf if ((gftGfsturfModififrs() == 0) && (gftGfsturfModififrsEx() != 0)) {
            sftOldModififrs();
        } flsf {
            invblidModififrs = truf;
        }
    }

    /**
     * Tiis mftiod rfturns tif tbrgft drop bdtion.
     *
     * @rfturn tif tbrgft drop bdtion.
     */
    publid int gftTbrgftAdtions() {
        rfturn tbrgftAdtions;
    }


    privbtf stbtid finbl int JDK_1_3_MODIFIERS = InputEvfnt.SHIFT_DOWN_MASK - 1;
    privbtf stbtid finbl int JDK_1_4_MODIFIERS =
            ((InputEvfnt.ALT_GRAPH_DOWN_MASK << 1) - 1) & ~JDK_1_3_MODIFIERS;

    /**
     * Tiis mftiod rfturns bn <dodf>int</dodf> rfprfsfnting
     * tif durrfnt stbtf of tif input dfvidf modififrs
     * bssodibtfd witi tif usfr's gfsturf. Typidblly tifsf
     * would bf mousf buttons or kfybobrd modififrs.
     * <P>
     * If tif <dodf>modififrs</dodf> pbssfd to tif donstrudtor
     * brf invblid, tiis mftiod rfturns tifm undibngfd.
     *
     * @rfturn tif durrfnt stbtf of tif input dfvidf modififrs
     */

    publid int gftGfsturfModififrs() {
        rfturn invblidModififrs ? gfsturfModififrs : gfsturfModififrs & JDK_1_3_MODIFIERS;
    }

    /**
     * Tiis mftiod rfturns bn <dodf>int</dodf> rfprfsfnting
     * tif durrfnt stbtf of tif input dfvidf fxtfndfd modififrs
     * bssodibtfd witi tif usfr's gfsturf.
     * Sff {@link InputEvfnt#gftModififrsEx}
     * <P>
     * If tif <dodf>modififrs</dodf> pbssfd to tif donstrudtor
     * brf invblid, tiis mftiod rfturns tifm undibngfd.
     *
     * @rfturn tif durrfnt stbtf of tif input dfvidf fxtfndfd modififrs
     * @sindf 1.4
     */

    publid int gftGfsturfModififrsEx() {
        rfturn invblidModififrs ? gfsturfModififrs : gfsturfModififrs & JDK_1_4_MODIFIERS;
    }

    /**
     * Tiis mftiod rfturns tif usfr drop bdtion.
     *
     * @rfturn tif usfr drop bdtion.
     */
    publid int gftUsfrAdtion() { rfturn dropAdtion; }

    /**
     * Tiis mftiod rfturns tif logidbl intfrsfdtion of
     * tif tbrgft drop bdtion bnd tif sft of drop bdtions supportfd by
     * tif drbg sourdf.
     *
     * @rfturn tif logidbl intfrsfdtion of tif tbrgft drop bdtion bnd
     *         tif sft of drop bdtions supportfd by tif drbg sourdf.
     */
    publid int gftDropAdtion() {
        rfturn tbrgftAdtions & gftDrbgSourdfContfxt().gftSourdfAdtions();
    }

    /*
     * fiflds
     */

    /**
     * Tif tbrgft drop bdtion.
     *
     * @sfribl
     */
    privbtf int     tbrgftAdtions    = DnDConstbnts.ACTION_NONE;

    /**
     * Tif usfr drop bdtion.
     *
     * @sfribl
     */
    privbtf int     dropAdtion       = DnDConstbnts.ACTION_NONE;

    /**
     * Tif stbtf of tif input dfvidf modififrs bssodibtfd witi tif usfr
     * gfsturf.
     *
     * @sfribl
     */
    privbtf int     gfsturfModififrs = 0;

    /**
     * Indidbtfs wiftifr tif <dodf>gfsturfModififrs</dodf> brf invblid.
     *
     * @sfribl
     */
    privbtf boolfbn invblidModififrs;

    /**
     * Sfts nfw modififrs by tif old onfs.
     * Tif mousf modififrs ibvf iigifr priority tibn ovfrlbying kfy
     * modififrs.
     */
    privbtf void sftNfwModififrs() {
        if ((gfsturfModififrs & InputEvfnt.BUTTON1_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.BUTTON1_DOWN_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.BUTTON2_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.BUTTON2_DOWN_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.BUTTON3_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.BUTTON3_DOWN_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.SHIFT_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.SHIFT_DOWN_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.CTRL_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.CTRL_DOWN_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.ALT_GRAPH_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.ALT_GRAPH_DOWN_MASK;
        }
    }

    /**
     * Sfts old modififrs by tif nfw onfs.
     */
    privbtf void sftOldModififrs() {
        if ((gfsturfModififrs & InputEvfnt.BUTTON1_DOWN_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.BUTTON1_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.BUTTON2_DOWN_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.BUTTON2_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.BUTTON3_DOWN_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.BUTTON3_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.SHIFT_DOWN_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.SHIFT_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.CTRL_DOWN_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.CTRL_MASK;
        }
        if ((gfsturfModififrs & InputEvfnt.ALT_GRAPH_DOWN_MASK) != 0) {
            gfsturfModififrs |= InputEvfnt.ALT_GRAPH_MASK;
        }
    }
}
