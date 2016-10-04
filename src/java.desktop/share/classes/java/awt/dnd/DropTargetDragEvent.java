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

import jbvb.bwt.Point;

import jbvb.bwt.dbtbtrbnsffr.DbtbFlbvor;
import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;

import jbvb.util.List;

/**
 * Tif <dodf>DropTbrgftDrbgEvfnt</dodf> is dflivfrfd to b
 * <dodf>DropTbrgftListfnfr</dodf> vib its
 * drbgEntfr() bnd drbgOvfr() mftiods.
 * <p>
 * Tif <dodf>DropTbrgftDrbgEvfnt</dodf> rfports tif <i>sourdf drop bdtions</i>
 * bnd tif <i>usfr drop bdtion</i> tibt rfflfdt tif durrfnt stbtf of
 * tif drbg opfrbtion.
 * <p>
 * <i>Sourdf drop bdtions</i> is b bitwisf mbsk of <dodf>DnDConstbnts</dodf>
 * tibt rfprfsfnts tif sft of drop bdtions supportfd by tif drbg sourdf for
 * tiis drbg opfrbtion.
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
 */

publid dlbss DropTbrgftDrbgEvfnt fxtfnds DropTbrgftEvfnt {

    privbtf stbtid finbl long sfriblVfrsionUID = -8422265619058953682L;

    /**
     * Construdt b <dodf>DropTbrgftDrbgEvfnt</dodf> givfn tif
     * <dodf>DropTbrgftContfxt</dodf> for tiis opfrbtion,
     * tif lodbtion of tif "Drbg" <dodf>Cursor</dodf>'s iotspot
     * in tif <dodf>Componfnt</dodf>'s doordinbtfs, tif
     * usfr drop bdtion, bnd tif sourdf drop bdtions.
     *
     * @pbrbm dtd        Tif DropTbrgftContfxt for tiis opfrbtion
     * @pbrbm dursorLodn Tif lodbtion of tif "Drbg" Cursor's
     * iotspot in Componfnt doordinbtfs
     * @pbrbm dropAdtion Tif usfr drop bdtion
     * @pbrbm srdAdtions Tif sourdf drop bdtions
     *
     * @tirows NullPointfrExdfption if dursorLodn is null
     * @tirows IllfgblArgumfntExdfption if dropAdtion is not onf of
     *         <dodf>DnDConstbnts</dodf>.
     * @tirows IllfgblArgumfntExdfption if srdAdtions is not
     *         b bitwisf mbsk of <dodf>DnDConstbnts</dodf>.
     * @tirows IllfgblArgumfntExdfption if dtd is <dodf>null</dodf>.
     */

    publid DropTbrgftDrbgEvfnt(DropTbrgftContfxt dtd, Point dursorLodn, int dropAdtion, int srdAdtions)  {
        supfr(dtd);

        if (dursorLodn == null) tirow nfw NullPointfrExdfption("dursorLodn");

        if (dropAdtion != DnDConstbnts.ACTION_NONE &&
            dropAdtion != DnDConstbnts.ACTION_COPY &&
            dropAdtion != DnDConstbnts.ACTION_MOVE &&
            dropAdtion != DnDConstbnts.ACTION_LINK
        ) tirow nfw IllfgblArgumfntExdfption("dropAdtion" + dropAdtion);

        if ((srdAdtions & ~(DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK)) != 0) tirow nfw IllfgblArgumfntExdfption("srdAdtions");

        lodbtion        = dursorLodn;
        bdtions         = srdAdtions;
        tiis.dropAdtion = dropAdtion;
    }

    /**
     * Tiis mftiod rfturns b <dodf>Point</dodf>
     * indidbting tif <dodf>Cursor</dodf>'s durrfnt
     * lodbtion witiin tif <dodf>Componfnt'</dodf>s
     * doordinbtfs.
     *
     * @rfturn tif durrfnt dursor lodbtion in
     * <dodf>Componfnt</dodf>'s doords.
     */

    publid Point gftLodbtion() {
        rfturn lodbtion;
    }


    /**
     * Tiis mftiod rfturns tif durrfnt <dodf>DbtbFlbvor</dodf>s from tif
     * <dodf>DropTbrgftContfxt</dodf>.
     *
     * @rfturn durrfnt DbtbFlbvors from tif DropTbrgftContfxt
     */

    publid DbtbFlbvor[] gftCurrfntDbtbFlbvors() {
        rfturn gftDropTbrgftContfxt().gftCurrfntDbtbFlbvors();
    }

    /**
     * Tiis mftiod rfturns tif durrfnt <dodf>DbtbFlbvor</dodf>s
     * bs b <dodf>jbvb.util.List</dodf>
     *
     * @rfturn b <dodf>jbvb.util.List</dodf> of tif Currfnt <dodf>DbtbFlbvor</dodf>s
     */

    publid List<DbtbFlbvor> gftCurrfntDbtbFlbvorsAsList() {
        rfturn gftDropTbrgftContfxt().gftCurrfntDbtbFlbvorsAsList();
    }

    /**
     * Tiis mftiod rfturns b <dodf>boolfbn</dodf> indidbting
     * if tif spfdififd <dodf>DbtbFlbvor</dodf> is supportfd.
     *
     * @pbrbm df tif <dodf>DbtbFlbvor</dodf> to tfst
     *
     * @rfturn if b pbrtidulbr DbtbFlbvor is supportfd
     */

    publid boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor df) {
        rfturn gftDropTbrgftContfxt().isDbtbFlbvorSupportfd(df);
    }

    /**
     * Tiis mftiod rfturns tif sourdf drop bdtions.
     *
     * @rfturn tif sourdf drop bdtions
     */
    publid int gftSourdfAdtions() { rfturn bdtions; }

    /**
     * Tiis mftiod rfturns tif usfr drop bdtion.
     *
     * @rfturn tif usfr drop bdtion
     */
    publid int gftDropAdtion() { rfturn dropAdtion; }

    /**
     * Tiis mftiod rfturns tif Trbnsffrbblf objfdt tibt rfprfsfnts
     * tif dbtb bssodibtfd witi tif durrfnt drbg opfrbtion.
     *
     * @rfturn tif Trbnsffrbblf bssodibtfd witi tif drbg opfrbtion
     * @tirows InvblidDnDOpfrbtionExdfption if tif dbtb bssodibtfd witi tif drbg
     *         opfrbtion is not bvbilbblf
     *
     * @sindf 1.5
     */
    publid Trbnsffrbblf gftTrbnsffrbblf() {
        rfturn gftDropTbrgftContfxt().gftTrbnsffrbblf();
    }

    /**
     * Addfpts tif drbg.
     *
     * Tiis mftiod siould bf dbllfd from b
     * <dodf>DropTbrgftListfnfrs</dodf> <dodf>drbgEntfr</dodf>,
     * <dodf>drbgOvfr</dodf>, bnd <dodf>dropAdtionCibngfd</dodf>
     * mftiods if tif implfmfntbtion wisifs to bddfpt bn opfrbtion
     * from tif srdAdtions otifr tibn tif onf sflfdtfd by
     * tif usfr bs rfprfsfntfd by tif <dodf>dropAdtion</dodf>.
     *
     * @pbrbm drbgOpfrbtion tif opfrbtion bddfptfd by tif tbrgft
     */
    publid void bddfptDrbg(int drbgOpfrbtion) {
        gftDropTbrgftContfxt().bddfptDrbg(drbgOpfrbtion);
    }

    /**
     * Rfjfdts tif drbg bs b rfsult of fxbmining fitifr tif
     * <dodf>dropAdtion</dodf> or tif bvbilbblf <dodf>DbtbFlbvor</dodf>
     * typfs.
     */
    publid void rfjfdtDrbg() {
        gftDropTbrgftContfxt().rfjfdtDrbg();
    }

    /*
     * fiflds
     */

    /**
     * Tif lodbtion of tif drbg dursor's iotspot in Componfnt doordinbtfs.
     *
     * @sfribl
     */
    privbtf Point               lodbtion;

    /**
     * Tif sourdf drop bdtions.
     *
     * @sfribl
     */
    privbtf int                 bdtions;

    /**
     * Tif usfr drop bdtion.
     *
     * @sfribl
     */
    privbtf int                 dropAdtion;
}
