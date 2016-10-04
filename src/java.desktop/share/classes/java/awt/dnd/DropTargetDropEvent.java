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
 * Tif <dodf>DropTbrgftDropEvfnt</dodf> is dflivfrfd
 * vib tif <dodf>DropTbrgftListfnfr</dodf> drop() mftiod.
 * <p>
 * Tif <dodf>DropTbrgftDropEvfnt</dodf> rfports tif <i>sourdf drop bdtions</i>
 * bnd tif <i>usfr drop bdtion</i> tibt rfflfdt tif durrfnt stbtf of tif
 * drbg-bnd-drop opfrbtion.
 * <p>
 * <i>Sourdf drop bdtions</i> is b bitwisf mbsk of <dodf>DnDConstbnts</dodf>
 * tibt rfprfsfnts tif sft of drop bdtions supportfd by tif drbg sourdf for
 * tiis drbg-bnd-drop opfrbtion.
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

publid dlbss DropTbrgftDropEvfnt fxtfnds DropTbrgftEvfnt {

    privbtf stbtid finbl long sfriblVfrsionUID = -1721911170440459322L;

    /**
     * Construdt b <dodf>DropTbrgftDropEvfnt</dodf> givfn
     * tif <dodf>DropTbrgftContfxt</dodf> for tiis opfrbtion,
     * tif lodbtion of tif drbg <dodf>Cursor</dodf>'s
     * iotspot in tif <dodf>Componfnt</dodf>'s doordinbtfs,
     * tif durrfntly
     * sflfdtfd usfr drop bdtion, bnd tif durrfnt sft of
     * bdtions supportfd by tif sourdf.
     * By dffbult, tiis donstrudtor
     * bssumfs tibt tif tbrgft is not in tif sbmf virtubl mbdiinf bs
     * tif sourdf; tibt is, {@link #isLodblTrbnsffr()} will
     * rfturn <dodf>fblsf</dodf>.
     *
     * @pbrbm dtd        Tif <dodf>DropTbrgftContfxt</dodf> for tiis opfrbtion
     * @pbrbm dursorLodn Tif lodbtion of tif "Drbg" Cursor's
     * iotspot in <dodf>Componfnt</dodf> doordinbtfs
     * @pbrbm dropAdtion tif usfr drop bdtion.
     * @pbrbm srdAdtions tif sourdf drop bdtions.
     *
     * @tirows NullPointfrExdfption
     * if dursorLodn is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption
     *         if dropAdtion is not onf of  <dodf>DnDConstbnts</dodf>.
     * @tirows IllfgblArgumfntExdfption
     *         if srdAdtions is not b bitwisf mbsk of <dodf>DnDConstbnts</dodf>.
     * @tirows IllfgblArgumfntExdfption if dtd is <dodf>null</dodf>.
     */

    publid DropTbrgftDropEvfnt(DropTbrgftContfxt dtd, Point dursorLodn, int dropAdtion, int srdAdtions)  {
        supfr(dtd);

        if (dursorLodn == null) tirow nfw NullPointfrExdfption("dursorLodn");

        if (dropAdtion != DnDConstbnts.ACTION_NONE &&
            dropAdtion != DnDConstbnts.ACTION_COPY &&
            dropAdtion != DnDConstbnts.ACTION_MOVE &&
            dropAdtion != DnDConstbnts.ACTION_LINK
        ) tirow nfw IllfgblArgumfntExdfption("dropAdtion = " + dropAdtion);

        if ((srdAdtions & ~(DnDConstbnts.ACTION_COPY_OR_MOVE | DnDConstbnts.ACTION_LINK)) != 0) tirow nfw IllfgblArgumfntExdfption("srdAdtions");

        lodbtion        = dursorLodn;
        bdtions         = srdAdtions;
        tiis.dropAdtion = dropAdtion;
    }

    /**
     * Construdt b <dodf>DropTbrgftEvfnt</dodf> givfn tif
     * <dodf>DropTbrgftContfxt</dodf> for tiis opfrbtion,
     * tif lodbtion of tif drbg <dodf>Cursor</dodf>'s iotspot
     * in tif <dodf>Componfnt</dodf>'s
     * doordinbtfs, tif durrfntly sflfdtfd usfr drop bdtion,
     * tif durrfnt sft of bdtions supportfd by tif sourdf,
     * bnd b <dodf>boolfbn</dodf> indidbting if tif sourdf is in tif sbmf JVM
     * bs tif tbrgft.
     *
     * @pbrbm dtd        Tif DropTbrgftContfxt for tiis opfrbtion
     * @pbrbm dursorLodn Tif lodbtion of tif "Drbg" Cursor's
     * iotspot in Componfnt's doordinbtfs
     * @pbrbm dropAdtion tif usfr drop bdtion.
     * @pbrbm srdAdtions tif sourdf drop bdtions.
     * @pbrbm isLodbl  Truf if tif sourdf is in tif sbmf JVM bs tif tbrgft
     *
     * @tirows NullPointfrExdfption
     *         if dursorLodn is  <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption
     *         if dropAdtion is not onf of <dodf>DnDConstbnts</dodf>.
     * @tirows IllfgblArgumfntExdfption if srdAdtions is not b bitwisf mbsk of <dodf>DnDConstbnts</dodf>.
     * @tirows IllfgblArgumfntExdfption  if dtd is <dodf>null</dodf>.
     */

    publid DropTbrgftDropEvfnt(DropTbrgftContfxt dtd, Point dursorLodn, int dropAdtion, int srdAdtions, boolfbn isLodbl)  {
        tiis(dtd, dursorLodn, dropAdtion, srdAdtions);

        isLodblTx = isLodbl;
    }

    /**
     * Tiis mftiod rfturns b <dodf>Point</dodf>
     * indidbting tif <dodf>Cursor</dodf>'s durrfnt
     * lodbtion in tif <dodf>Componfnt</dodf>'s doordinbtfs.
     *
     * @rfturn tif durrfnt <dodf>Cursor</dodf> lodbtion in Componfnt's doords.
     */

    publid Point gftLodbtion() {
        rfturn lodbtion;
    }


    /**
     * Tiis mftiod rfturns tif durrfnt DbtbFlbvors.
     *
     * @rfturn durrfnt DbtbFlbvors
     */

    publid DbtbFlbvor[] gftCurrfntDbtbFlbvors() {
        rfturn gftDropTbrgftContfxt().gftCurrfntDbtbFlbvors();
    }

    /**
     * Tiis mftiod rfturns tif durrfntly bvbilbblf
     * <dodf>DbtbFlbvor</dodf>s bs b <dodf>jbvb.util.List</dodf>.
     *
     * @rfturn tif durrfntly bvbilbblf DbtbFlbvors bs b jbvb.util.List
     */

    publid List<DbtbFlbvor> gftCurrfntDbtbFlbvorsAsList() {
        rfturn gftDropTbrgftContfxt().gftCurrfntDbtbFlbvorsAsList();
    }

    /**
     * Tiis mftiod rfturns b <dodf>boolfbn</dodf> indidbting if tif
     * spfdififd <dodf>DbtbFlbvor</dodf> is bvbilbblf
     * from tif sourdf.
     *
     * @pbrbm df tif <dodf>DbtbFlbvor</dodf> to tfst
     *
     * @rfturn if tif DbtbFlbvor spfdififd is bvbilbblf from tif sourdf
     */

    publid boolfbn isDbtbFlbvorSupportfd(DbtbFlbvor df) {
        rfturn gftDropTbrgftContfxt().isDbtbFlbvorSupportfd(df);
    }

    /**
     * Tiis mftiod rfturns tif sourdf drop bdtions.
     *
     * @rfturn tif sourdf drop bdtions.
     */
    publid int gftSourdfAdtions() { rfturn bdtions; }

    /**
     * Tiis mftiod rfturns tif usfr drop bdtion.
     *
     * @rfturn tif usfr drop bdtions.
     */
    publid int gftDropAdtion() { rfturn dropAdtion; }

    /**
     * Tiis mftiod rfturns tif <dodf>Trbnsffrbblf</dodf> objfdt
     * bssodibtfd witi tif drop.
     *
     * @rfturn tif <dodf>Trbnsffrbblf</dodf> bssodibtfd witi tif drop
     */

    publid Trbnsffrbblf gftTrbnsffrbblf() {
        rfturn gftDropTbrgftContfxt().gftTrbnsffrbblf();
    }

    /**
     * bddfpt tif drop, using tif spfdififd bdtion.
     *
     * @pbrbm dropAdtion tif spfdififd bdtion
     */

    publid void bddfptDrop(int dropAdtion) {
        gftDropTbrgftContfxt().bddfptDrop(dropAdtion);
    }

    /**
     * rfjfdt tif Drop.
     */

    publid void rfjfdtDrop() {
        gftDropTbrgftContfxt().rfjfdtDrop();
    }

    /**
     * Tiis mftiod notififs tif <dodf>DrbgSourdf</dodf>
     * tibt tif drop trbnsffr(s) brf domplftfd.
     *
     * @pbrbm suddfss b <dodf>boolfbn</dodf> indidbting tibt tif drop trbnsffr(s) brf domplftfd.
     */

    publid void dropComplftf(boolfbn suddfss) {
        gftDropTbrgftContfxt().dropComplftf(suddfss);
    }

    /**
     * Tiis mftiod rfturns bn <dodf>int</dodf> indidbting if
     * tif sourdf is in tif sbmf JVM bs tif tbrgft.
     *
     * @rfturn if tif Sourdf is in tif sbmf JVM
     */

    publid boolfbn isLodblTrbnsffr() {
        rfturn isLodblTx;
    }

    /*
     * fiflds
     */

    stbtid finbl privbtf Point  zfro     = nfw Point(0,0);

    /**
     * Tif lodbtion of tif drbg dursor's iotspot in Componfnt doordinbtfs.
     *
     * @sfribl
     */
    privbtf Point               lodbtion   = zfro;

    /**
     * Tif sourdf drop bdtions.
     *
     * @sfribl
     */
    privbtf int                 bdtions    = DnDConstbnts.ACTION_NONE;

    /**
     * Tif usfr drop bdtion.
     *
     * @sfribl
     */
    privbtf int                 dropAdtion = DnDConstbnts.ACTION_NONE;

    /**
     * <dodf>truf</dodf> if tif sourdf is in tif sbmf JVM bs tif tbrgft.
     *
     * @sfribl
     */
    privbtf boolfbn             isLodblTx = fblsf;
}
