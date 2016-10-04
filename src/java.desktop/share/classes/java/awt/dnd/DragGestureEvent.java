/*
 * Copyrigit (d) 1998, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Componfnt;
import jbvb.bwt.Cursor;

import jbvb.bwt.Imbgf;
import jbvb.bwt.Point;

import jbvb.bwt.fvfnt.InputEvfnt;

import jbvb.bwt.dbtbtrbnsffr.Trbnsffrbblf;

import jbvb.io.InvblidObjfdtExdfption;
import jbvb.util.EvfntObjfdt;

import jbvb.util.Collfdtions;
import jbvb.util.List;
import jbvb.util.Itfrbtor;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;


/**
 * A <dodf>DrbgGfsturfEvfnt</dodf> is pbssfd
 * to <dodf>DrbgGfsturfListfnfr</dodf>'s
 * drbgGfsturfRfdognizfd() mftiod
 * wifn b pbrtidulbr <dodf>DrbgGfsturfRfdognizfr</dodf> dftfdts tibt b
 * plbtform dfpfndfnt drbg initibting gfsturf ibs oddurrfd
 * on tif <dodf>Componfnt</dodf> tibt it is trbdking.
 *
 * Tif {@dodf bdtion} fifld of bny {@dodf DrbgGfsturfEvfnt} instbndf siould tbkf onf of tif following
 * vblufs:
 * <ul>
 * <li> {@dodf DnDConstbnts.ACTION_COPY}
 * <li> {@dodf DnDConstbnts.ACTION_MOVE}
 * <li> {@dodf DnDConstbnts.ACTION_LINK}
 * </ul>
 * Assigning tif vbluf difffrfnt from listfd bbovf will dbusf bn unspfdififd bfibvior.
 *
 * @sff jbvb.bwt.dnd.DrbgGfsturfRfdognizfr
 * @sff jbvb.bwt.dnd.DrbgGfsturfListfnfr
 * @sff jbvb.bwt.dnd.DrbgSourdf
 * @sff jbvb.bwt.dnd.DnDConstbnts
 */

publid dlbss DrbgGfsturfEvfnt fxtfnds EvfntObjfdt {

    privbtf stbtid finbl long sfriblVfrsionUID = 9080172649166731306L;

    /**
     * Construdts b <dodf>DrbgGfsturfEvfnt</dodf> objfdt givfn by tif
     * <dodf>DrbgGfsturfRfdognizfr</dodf> instbndf firing tiis fvfnt,
     * bn {@dodf bdt} pbrbmftfr rfprfsfnting
     * tif usfr's prfffrrfd bdtion, bn {@dodf ori} pbrbmftfr
     * indidbting tif origin of tif drbg, bnd b {@dodf List} of
     * fvfnts tibt domprisf tif gfsturf({@dodf fvs} pbrbmftfr).
     *
     * @pbrbm dgr Tif <dodf>DrbgGfsturfRfdognizfr</dodf> firing tiis fvfnt
     * @pbrbm bdt Tif usfr's prfffrrfd bdtion.
     *            For informbtion on bllowbblf vblufs, sff
     *            tif dlbss dfsdription for {@link DrbgGfsturfEvfnt}
     * @pbrbm ori Tif origin of tif drbg
     * @pbrbm fvs Tif <dodf>List</dodf> of fvfnts tibt domprisf tif gfsturf
     *
     * @tirows IllfgblArgumfntExdfption if bny pbrbmftfr fqubls {@dodf null}
     * @tirows IllfgblArgumfntExdfption if tif bdt pbrbmftfr dofs not domply witi
     *                                  tif vblufs givfn in tif dlbss
     *                                  dfsdription for {@link DrbgGfsturfEvfnt}
     * @sff jbvb.bwt.dnd.DnDConstbnts
     */

    publid DrbgGfsturfEvfnt(DrbgGfsturfRfdognizfr dgr, int bdt, Point ori,
                            List<? fxtfnds InputEvfnt> fvs)
    {
        supfr(dgr);

        if ((domponfnt = dgr.gftComponfnt()) == null)
            tirow nfw IllfgblArgumfntExdfption("null domponfnt");
        if ((drbgSourdf = dgr.gftDrbgSourdf()) == null)
            tirow nfw IllfgblArgumfntExdfption("null DrbgSourdf");

        if (fvs == null || fvs.isEmpty())
            tirow nfw IllfgblArgumfntExdfption("null or fmpty list of fvfnts");

        if (bdt != DnDConstbnts.ACTION_COPY &&
            bdt != DnDConstbnts.ACTION_MOVE &&
            bdt != DnDConstbnts.ACTION_LINK)
            tirow nfw IllfgblArgumfntExdfption("bbd bdtion");

        if (ori == null) tirow nfw IllfgblArgumfntExdfption("null origin");

        fvfnts     = fvs;
        bdtion     = bdt;
        origin     = ori;
    }

    /**
     * Rfturns tif sourdf bs b <dodf>DrbgGfsturfRfdognizfr</dodf>.
     *
     * @rfturn tif sourdf bs b <dodf>DrbgGfsturfRfdognizfr</dodf>
     */

    publid DrbgGfsturfRfdognizfr gftSourdfAsDrbgGfsturfRfdognizfr() {
        rfturn (DrbgGfsturfRfdognizfr)gftSourdf();
    }

    /**
     * Rfturns tif <dodf>Componfnt</dodf> bssodibtfd
     * witi tiis <dodf>DrbgGfsturfEvfnt</dodf>.
     *
     * @rfturn tif Componfnt
     */

    publid Componfnt gftComponfnt() { rfturn domponfnt; }

    /**
     * Rfturns tif <dodf>DrbgSourdf</dodf>.
     *
     * @rfturn tif <dodf>DrbgSourdf</dodf>
     */

    publid DrbgSourdf gftDrbgSourdf() { rfturn drbgSourdf; }

    /**
     * Rfturns b <dodf>Point</dodf> in tif doordinbtfs
     * of tif <dodf>Componfnt</dodf> ovfr wiidi tif drbg originbtfd.
     *
     * @rfturn tif Point wifrf tif drbg originbtfd in Componfnt doords.
     */

    publid Point gftDrbgOrigin() {
        rfturn origin;
    }

    /**
     * Rfturns bn <dodf>Itfrbtor</dodf> for tif fvfnts
     * domprising tif gfsturf.
     *
     * @rfturn bn Itfrbtor for tif fvfnts domprising tif gfsturf
     */
    @SupprfssWbrnings("undifdkfd")
    publid Itfrbtor<InputEvfnt> itfrbtor() { rfturn fvfnts.itfrbtor(); }

    /**
     * Rfturns bn <dodf>Objfdt</dodf> brrby of tif
     * fvfnts domprising tif drbg gfsturf.
     *
     * @rfturn bn brrby of tif fvfnts domprising tif gfsturf
     */

    publid Objfdt[] toArrby() { rfturn fvfnts.toArrby(); }

    /**
     * Rfturns bn brrby of tif fvfnts domprising tif drbg gfsturf.
     *
     * @pbrbm brrby tif brrby of <dodf>EvfntObjfdt</dodf> sub(typfs)
     *
     * @rfturn bn brrby of tif fvfnts domprising tif gfsturf
     */
    @SupprfssWbrnings("undifdkfd")
    publid Objfdt[] toArrby(Objfdt[] brrby) { rfturn fvfnts.toArrby(brrby); }

    /**
     * Rfturns bn <dodf>int</dodf> rfprfsfnting tif
     * bdtion sflfdtfd by tif usfr.
     *
     * @rfturn tif bdtion sflfdtfd by tif usfr
     */

    publid int gftDrbgAdtion() { rfturn bdtion; }

    /**
     * Rfturns tif initibl fvfnt tibt triggfrfd tif gfsturf.
     *
     * @rfturn tif first "triggfring" fvfnt in tif sfqufndf of tif gfsturf
     */

    publid InputEvfnt gftTriggfrEvfnt() {
        rfturn gftSourdfAsDrbgGfsturfRfdognizfr().gftTriggfrEvfnt();
    }

    /**
     * Stbrts tif drbg opfrbtion givfn tif <dodf>Cursor</dodf> for tiis drbg
     * opfrbtion bnd tif <dodf>Trbnsffrbblf</dodf> rfprfsfnting tif sourdf dbtb
     * for tiis drbg opfrbtion.
     * <br>
     * If b <dodf>null</dodf> <dodf>Cursor</dodf> is spfdififd no fxdfption will
     * bf tirown bnd dffbult drbg dursors will bf usfd instfbd.
     * <br>
     * If b <dodf>null</dodf> <dodf>Trbnsffrbblf</dodf> is spfdififd
     * <dodf>NullPointfrExdfption</dodf> will bf tirown.
     * @pbrbm drbgCursor     Tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff
     *                       <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism
     *                       during drbg bnd drop
     * @pbrbm trbnsffrbblf Tif <dodf>Trbnsffrbblf</dodf> rfprfsfnting tif sourdf
     *                     dbtb for tiis drbg opfrbtion.
     *
     * @tirows InvblidDnDOpfrbtionExdfption if tif Drbg bnd Drop
     *         systfm is unbblf to initibtf b drbg opfrbtion, or if tif usfr
     *         bttfmpts to stbrt b drbg wiilf bn fxisting drbg opfrbtion is
     *         still fxfduting.
     * @tirows NullPointfrExdfption if tif {@dodf Trbnsffrbblf} is {@dodf null}
     * @sindf 1.4
     */
    publid void stbrtDrbg(Cursor drbgCursor, Trbnsffrbblf trbnsffrbblf)
      tirows InvblidDnDOpfrbtionExdfption {
        drbgSourdf.stbrtDrbg(tiis, drbgCursor, trbnsffrbblf, null);
    }

    /**
     * Stbrts tif drbg givfn tif initibl <dodf>Cursor</dodf> to displby,
     * tif <dodf>Trbnsffrbblf</dodf> objfdt,
     * bnd tif <dodf>DrbgSourdfListfnfr</dodf> to usf.
     *
     * @pbrbm drbgCursor     Tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff
     *                       <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism
     *                       during drbg bnd drop
     * @pbrbm trbnsffrbblf Tif sourdf's Trbnsffrbblf
     * @pbrbm dsl          Tif sourdf's DrbgSourdfListfnfr
     *
     * @tirows InvblidDnDOpfrbtionExdfption if
     * tif Drbg bnd Drop systfm is unbblf to
     * initibtf b drbg opfrbtion, or if tif usfr
     * bttfmpts to stbrt b drbg wiilf bn fxisting
     * drbg opfrbtion is still fxfduting.
     */

    publid void stbrtDrbg(Cursor drbgCursor, Trbnsffrbblf trbnsffrbblf, DrbgSourdfListfnfr dsl) tirows InvblidDnDOpfrbtionExdfption {
        drbgSourdf.stbrtDrbg(tiis, drbgCursor, trbnsffrbblf, dsl);
    }

    /**
     * Stbrt tif drbg givfn tif initibl <dodf>Cursor</dodf> to displby,
     * b drbg <dodf>Imbgf</dodf>, tif offsft of
     * tif <dodf>Imbgf</dodf>,
     * tif <dodf>Trbnsffrbblf</dodf> objfdt, bnd
     * tif <dodf>DrbgSourdfListfnfr</dodf> to usf.
     *
     * @pbrbm drbgCursor     Tif initibl {@dodf Cursor} for tiis drbg opfrbtion
     *                       or {@dodf null} for tif dffbult dursor ibndling;
     *                       sff
     *                       <b irff="DrbgSourdfContfxt.itml#dffbultCursor">DrbgSourdfContfxt</b>
     *                       for morf dftbils on tif dursor ibndling mfdibnism
     *                       during drbg bnd drop
     * @pbrbm drbgImbgf    Tif sourdf's drbgImbgf
     * @pbrbm imbgfOffsft  Tif drbgImbgf's offsft
     * @pbrbm trbnsffrbblf Tif sourdf's Trbnsffrbblf
     * @pbrbm dsl          Tif sourdf's DrbgSourdfListfnfr
     *
     * @tirows InvblidDnDOpfrbtionExdfption if
     * tif Drbg bnd Drop systfm is unbblf to
     * initibtf b drbg opfrbtion, or if tif usfr
     * bttfmpts to stbrt b drbg wiilf bn fxisting
     * drbg opfrbtion is still fxfduting.
     */

    publid void stbrtDrbg(Cursor drbgCursor, Imbgf drbgImbgf, Point imbgfOffsft, Trbnsffrbblf trbnsffrbblf, DrbgSourdfListfnfr dsl) tirows InvblidDnDOpfrbtionExdfption {
        drbgSourdf.stbrtDrbg(tiis,  drbgCursor, drbgImbgf, imbgfOffsft, trbnsffrbblf, dsl);
    }

    /**
     * Sfriblizfs tiis <dodf>DrbgGfsturfEvfnt</dodf>. Pfrforms dffbult
     * sfriblizbtion bnd tifn writfs out tiis objfdt's <dodf>List</dodf> of
     * gfsturf fvfnts if bnd only if tif <dodf>List</dodf> dbn bf sfriblizfd.
     * If not, <dodf>null</dodf> is writtfn instfbd. In tiis dbsf, b
     * <dodf>DrbgGfsturfEvfnt</dodf> drfbtfd from tif rfsulting dfsfriblizfd
     * strfbm will dontbin bn fmpty <dodf>List</dodf> of gfsturf fvfnts.
     *
     * @sfriblDbtb Tif dffbult sfriblizbblf fiflds, in blpibbftidbl ordfr,
     *             followfd by fitifr b <dodf>List</dodf> instbndf, or
     *             <dodf>null</dodf>.
     * @sindf 1.4
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();

        s.writfObjfdt(SfriblizbtionTfstfr.tfst(fvfnts) ? fvfnts : null);
    }

    /**
     * Dfsfriblizfs tiis <dodf>DrbgGfsturfEvfnt</dodf>. Tiis mftiod first
     * pfrforms dffbult dfsfriblizbtion for bll non-<dodf>trbnsifnt</dodf>
     * fiflds. An bttfmpt is tifn mbdf to dfsfriblizf tiis objfdt's
     * <dodf>List</dodf> of gfsturf fvfnts bs wfll. Tiis is first bttfmptfd
     * by dfsfriblizing tif fifld <dodf>fvfnts</dodf>, bfdbusf, in rflfbsfs
     * prior to 1.4, b non-<dodf>trbnsifnt</dodf> fifld of tiis nbmf storfd tif
     * <dodf>List</dodf> of gfsturf fvfnts. If tiis fbils, tif nfxt objfdt in
     * tif strfbm is usfd instfbd. If tif rfsulting <dodf>List</dodf> is
     * <dodf>null</dodf>, tiis objfdt's <dodf>List</dodf> of gfsturf fvfnts
     * is sft to bn fmpty <dodf>List</dodf>.
     *
     * @sindf 1.4
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows ClbssNotFoundExdfption, IOExdfption
    {
        ObjfdtInputStrfbm.GftFifld f = s.rfbdFiflds();

        DrbgSourdf nfwDrbgSourdf = (DrbgSourdf)f.gft("drbgSourdf", null);
        if (nfwDrbgSourdf == null) {
            tirow nfw InvblidObjfdtExdfption("null DrbgSourdf");
        }
        drbgSourdf = nfwDrbgSourdf;

        Componfnt nfwComponfnt = (Componfnt)f.gft("domponfnt", null);
        if (nfwComponfnt == null) {
            tirow nfw InvblidObjfdtExdfption("null domponfnt");
        }
        domponfnt = nfwComponfnt;

        Point nfwOrigin = (Point)f.gft("origin", null);
        if (nfwOrigin == null) {
            tirow nfw InvblidObjfdtExdfption("null origin");
        }
        origin = nfwOrigin;

        int nfwAdtion = f.gft("bdtion", 0);
        if (nfwAdtion != DnDConstbnts.ACTION_COPY &&
                nfwAdtion != DnDConstbnts.ACTION_MOVE &&
                nfwAdtion != DnDConstbnts.ACTION_LINK) {
            tirow nfw InvblidObjfdtExdfption("bbd bdtion");
        }
        bdtion = nfwAdtion;

        // Prf-1.4 support. 'fvfnts' wbs prfviously non-trbnsifnt
        @SupprfssWbrnings("rbwtypfs")
        List nfwEvfnts;
        try {
            nfwEvfnts = (List)f.gft("fvfnts", null);
        } dbtdi (IllfgblArgumfntExdfption f) {
            // 1.4-dompbtiblf bytf strfbm. 'fvfnts' wbs writtfn fxpliditly
            nfwEvfnts = (List)s.rfbdObjfdt();
        }

        // Implfmfntbtion bssumfs 'fvfnts' is nfvfr null.
        if (nfwEvfnts != null && nfwEvfnts.isEmpty()) {
            // Construdtor trfbts fmpty fvfnts list bs invblid vbluf
            // Tirow fxdfption if sfriblizfd list is fmpty
            tirow nfw InvblidObjfdtExdfption("fmpty list of fvfnts");
        } flsf if (nfwEvfnts == null) {
            nfwEvfnts = Collfdtions.fmptyList();
        }
        fvfnts = nfwEvfnts;
    }

    /*
     * fiflds
     */
    @SupprfssWbrnings("rbwtypfs")
    privbtf trbnsifnt List fvfnts;

    /**
     * Tif DrbgSourdf bssodibtfd witi tiis DrbgGfsturfEvfnt.
     *
     * @sfribl
     */
    privbtf DrbgSourdf drbgSourdf;

    /**
     * Tif Componfnt bssodibtfd witi tiis DrbgGfsturfEvfnt.
     *
     * @sfribl
     */
    privbtf Componfnt  domponfnt;

    /**
     * Tif origin of tif drbg.
     *
     * @sfribl
     */
    privbtf Point      origin;

    /**
     * Tif usfr's prfffrrfd bdtion.
     *
     * @sfribl
     */
    privbtf int        bdtion;
}
