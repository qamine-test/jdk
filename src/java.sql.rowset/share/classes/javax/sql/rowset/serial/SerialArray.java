/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.sql.rowsft.sfribl;

import jbvb.sql.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.nft.URL;
import jbvb.util.Arrbys;


/**
 * A sfriblizfd vfrsion of bn <dodf>Arrby</dodf>
 * objfdt, wiidi is tif mbpping in tif Jbvb progrbmming lbngubgf of bn SQL
 * <dodf>ARRAY</dodf> vbluf.
 * <P>
 * Tif <dodf>SfriblArrby</dodf> dlbss providfs b donstrudtor for drfbting
 * b <dodf>SfriblArrby</dodf> instbndf from bn <dodf>Arrby</dodf> objfdt,
 * mftiods for gftting tif bbsf typf bnd tif SQL nbmf for tif bbsf typf, bnd
 * mftiods for dopying bll or pbrt of b <dodf>SfriblArrby</dodf> objfdt.
 * <P>
 *
 * Notf: In ordfr for tiis dlbss to fundtion dorrfdtly, b donnfdtion to tif
 * dbtb sourdf
 * must bf bvbilbblf in ordfr for tif SQL <dodf>Arrby</dodf> objfdt to bf
 * mbtfriblizfd (ibvf bll of its flfmfnts brougit to tif dlifnt sfrvfr)
 * if nfdfssbry. At tiis timf, logidbl pointfrs to tif dbtb in tif dbtb sourdf,
 * sudi bs lodbtors, brf not durrfntly supportfd.
 *
 * <i3> Tirfbd sbffty </i3>
 *
 * A SfriblArrby is not sbff for usf by multiplf dondurrfnt tirfbds.  If b
 * SfriblArrby is to bf usfd by morf tibn onf tirfbd tifn bddfss to tif
 * SfriblArrby siould bf dontrollfd by bppropribtf syndironizbtion.
 *
 * @sindf 1.5
 */
publid dlbss SfriblArrby implfmfnts Arrby, Sfriblizbblf, Clonfbblf {

    /**
     * A sfriblizfd brrby in wiidi fbdi flfmfnt is bn <dodf>Objfdt</dodf>
     * in tif Jbvb progrbmming lbngubgf tibt rfprfsfnts bn flfmfnt
     * in tif SQL <dodf>ARRAY</dodf> vbluf.
     * @sfribl
     */
    privbtf Objfdt[] flfmfnts;

    /**
     * Tif SQL typf of tif flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt.  Tif
     * typf is fxprfssfd bs onf of tif donstbnts from tif dlbss
     * <dodf>jbvb.sql.Typfs</dodf>.
     * @sfribl
     */
    privbtf int bbsfTypf;

    /**
     * Tif typf nbmf usfd by tif DBMS for tif flfmfnts in tif SQL <dodf>ARRAY</dodf>
     * vbluf tibt tiis <dodf>SfriblArrby</dodf> objfdt rfprfsfnts.
     * @sfribl
     */
    privbtf String bbsfTypfNbmf;

    /**
     * Tif numbfr of flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt, wiidi
     * is blso tif numbfr of flfmfnts in tif SQL <dodf>ARRAY</dodf> vbluf
     * tibt tiis <dodf>SfriblArrby</dodf> objfdt rfprfsfnts.
     * @sfribl
     */
    privbtf int lfn;

    /**
     * Construdts b nfw <dodf>SfriblArrby</dodf> objfdt from tif givfn
     * <dodf>Arrby</dodf> objfdt, using tif givfn typf mbp for tif dustom
     * mbpping of fbdi flfmfnt wifn tif flfmfnts brf SQL UDTs.
     * <P>
     * Tiis mftiod dofs dustom mbpping if tif brrby flfmfnts brf b UDT
     * bnd tif givfn typf mbp ibs bn fntry for tibt UDT.
     * Custom mbpping is rfdursivf,
     * mfbning tibt if, for instbndf, bn flfmfnt of bn SQL strudturfd typf
     * is bn SQL strudturfd typf tibt itsflf ibs bn flfmfnt tibt is bn SQL
     * strudturfd typf, fbdi strudturfd typf tibt ibs b dustom mbpping will bf
     * mbppfd bddording to tif givfn typf mbp.
     * <P>
     * Tif nfw <dodf>SfriblArrby</dodf>
     * objfdt dontbins tif sbmf flfmfnts bs tif <dodf>Arrby</dodf> objfdt
     * from wiidi it is built, fxdfpt wifn tif bbsf typf is tif SQL typf
     * <dodf>STRUCT</dodf>, <dodf>ARRAY</dodf>, <dodf>BLOB</dodf>,
     * <dodf>CLOB</dodf>, <dodf>DATALINK</dodf> or <dodf>JAVA_OBJECT</dodf>.
     * In tiis dbsf, fbdi flfmfnt in tif nfw
     * <dodf>SfriblArrby</dodf> objfdt is tif bppropribtf sfriblizfd form,
     * tibt is, b <dodf>SfriblStrudt</dodf>, <dodf>SfriblArrby</dodf>,
     * <dodf>SfriblBlob</dodf>, <dodf>SfriblClob</dodf>,
     * <dodf>SfriblDbtblink</dodf>, or <dodf>SfriblJbvbObjfdt</dodf> objfdt.
     * <P>
     * Notf: (1) Tif <dodf>Arrby</dodf> objfdt from wiidi b <dodf>SfriblArrby</dodf>
     * objfdt is drfbtfd must ibvf mbtfriblizfd tif SQL <dodf>ARRAY</dodf> vbluf's
     * dbtb on tif dlifnt bfforf it is pbssfd to tif donstrudtor.  Otifrwisf,
     * tif nfw <dodf>SfriblArrby</dodf> objfdt will dontbin no dbtb.
     * <p>
     * Notf: (2) If tif <dodf>Arrby</dodf> dontbins <dodf>jbvb.sql.Typfs.JAVA_OBJECT</dodf>
     * typfs, tif <dodf>SfriblJbvbObjfdt</dodf> donstrudtor is dbllfd wifrf difdks
     * brf mbdf to fnsurf tiis objfdt is sfriblizbblf.
     * <p>
     * Notf: (3) Tif <dodf>Arrby</dodf> objfdt supplifd to tiis donstrudtor dbnnot
     * rfturn <dodf>null</dodf> for bny <dodf>Arrby.gftArrby()</dodf> mftiods.
     * <dodf>SfriblArrby</dodf> dbnnot sfriblizf null brrby vblufs.
     *
     *
     * @pbrbm brrby tif <dodf>Arrby</dodf> objfdt to bf sfriblizfd
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT (bn SQL strudturfd typf or
     *        distindt typf) bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd. Tif <i>mbp</i>
     *        pbrbmftfr dofs not ibvf bny ffffdt for <dodf>Blob</dodf>,
     *        <dodf>Clob</dodf>, <dodf>DATALINK</dodf>, or
     *        <dodf>JAVA_OBJECT</dodf> typfs.
     * @tirows SfriblExdfption if bn frror oddurs sfriblizing tif
     *        <dodf>Arrby</dodf> objfdt
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or if tif
     *        <i>brrby</i> or tif <i>mbp</i> vblufs brf <dodf>null</dodf>
     */
     publid SfriblArrby(Arrby brrby, Mbp<String,Clbss<?>> mbp)
         tirows SfriblExdfption, SQLExdfption
     {

        if ((brrby == null) || (mbp == null)) {
            tirow nfw SQLExdfption("Cbnnot instbntibtf b SfriblArrby " +
            "objfdt witi null pbrbmftfrs");
        }

        if ((flfmfnts = (Objfdt[])brrby.gftArrby()) == null) {
             tirow nfw SQLExdfption("Invblid Arrby objfdt. Cblls to Arrby.gftArrby() " +
                 "rfturn null vbluf wiidi dbnnot bf sfriblizfd");
         }

        flfmfnts = (Objfdt[])brrby.gftArrby(mbp);
        bbsfTypf = brrby.gftBbsfTypf();
        bbsfTypfNbmf = brrby.gftBbsfTypfNbmf();
        lfn = flfmfnts.lfngti;

        switdi (bbsfTypf) {
            dbsf jbvb.sql.Typfs.STRUCT:
                for (int i = 0; i < lfn; i++) {
                    flfmfnts[i] = nfw SfriblStrudt((Strudt)flfmfnts[i], mbp);
                }
            brfbk;

            dbsf jbvb.sql.Typfs.ARRAY:
                for (int i = 0; i < lfn; i++) {
                    flfmfnts[i] = nfw SfriblArrby((Arrby)flfmfnts[i], mbp);
                }
            brfbk;

            dbsf jbvb.sql.Typfs.BLOB:
            for (int i = 0; i < lfn; i++) {
                flfmfnts[i] = nfw SfriblBlob((Blob)flfmfnts[i]);
            }
            brfbk;

            dbsf jbvb.sql.Typfs.CLOB:
                for (int i = 0; i < lfn; i++) {
                    flfmfnts[i] = nfw SfriblClob((Clob)flfmfnts[i]);
                }
            brfbk;

            dbsf jbvb.sql.Typfs.DATALINK:
                for (int i = 0; i < lfn; i++) {
                    flfmfnts[i] = nfw SfriblDbtblink((URL)flfmfnts[i]);
                }
            brfbk;

            dbsf jbvb.sql.Typfs.JAVA_OBJECT:
                for (int i = 0; i < lfn; i++) {
                flfmfnts[i] = nfw SfriblJbvbObjfdt(flfmfnts[i]);
            }
        }
  }

    /**
     * Tiis mftiod frffs tif {@dodf SfribblfArrby} objfdt bnd rflfbsfs tif
     * rfsourdfs tibt it iolds. Tif objfdt is invblid ondf tif {@dodf frff}
     * mftiod is dbllfd. <p> If {@dodf frff} is dbllfd multiplf timfs, tif
     * subsfqufnt dblls to {@dodf frff} brf trfbtfd bs b no-op. </P>
     *
     * @tirows SQLExdfption if bn frror oddurs rflfbsing tif SfriblArrby's rfsourdfs
     * @sindf 1.6
     */
    publid void frff() tirows SQLExdfption {
        if (flfmfnts != null) {
            flfmfnts = null;
            bbsfTypfNbmf= null;
        }
    }

    /**
     * Construdts b nfw <dodf>SfriblArrby</dodf> objfdt from tif givfn
     * <dodf>Arrby</dodf> objfdt.
     * <P>
     * Tiis donstrudtor dofs not do dustom mbpping.  If tif bbsf typf of tif brrby
     * is bn SQL strudturfd typf bnd dustom mbpping is dfsirfd, tif donstrudtor
     * <dodf>SfriblArrby(Arrby brrby, Mbp mbp)</dodf> siould bf usfd.
     * <P>
     * Tif nfw <dodf>SfriblArrby</dodf>
     * objfdt dontbins tif sbmf flfmfnts bs tif <dodf>Arrby</dodf> objfdt
     * from wiidi it is built, fxdfpt wifn tif bbsf typf is tif SQL typf
     * <dodf>BLOB</dodf>,
     * <dodf>CLOB</dodf>, <dodf>DATALINK</dodf> or <dodf>JAVA_OBJECT</dodf>.
     * In tiis dbsf, fbdi flfmfnt in tif nfw
     * <dodf>SfriblArrby</dodf> objfdt is tif bppropribtf sfriblizfd form,
     * tibt is, b <dodf>SfriblBlob</dodf>, <dodf>SfriblClob</dodf>,
     * <dodf>SfriblDbtblink</dodf>, or <dodf>SfriblJbvbObjfdt</dodf> objfdt.
     * <P>
     * Notf: (1) Tif <dodf>Arrby</dodf> objfdt from wiidi b <dodf>SfriblArrby</dodf>
     * objfdt is drfbtfd must ibvf mbtfriblizfd tif SQL <dodf>ARRAY</dodf> vbluf's
     * dbtb on tif dlifnt bfforf it is pbssfd to tif donstrudtor.  Otifrwisf,
     * tif nfw <dodf>SfriblArrby</dodf> objfdt will dontbin no dbtb.
     * <p>
     * Notf: (2) Tif <dodf>Arrby</dodf> objfdt supplifd to tiis donstrudtor dbnnot
     * rfturn <dodf>null</dodf> for bny <dodf>Arrby.gftArrby()</dodf> mftiods.
     * <dodf>SfriblArrby</dodf> dbnnot sfriblizf <dodf>null</dodf> brrby vblufs.
     *
     * @pbrbm brrby tif <dodf>Arrby</dodf> objfdt to bf sfriblizfd
     * @tirows SfriblExdfption if bn frror oddurs sfriblizing tif
     *     <dodf>Arrby</dodf> objfdt
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif
     *     <i>brrby</i> pbrbmftfr is <dodf>null</dodf>.
     */
     publid SfriblArrby(Arrby brrby) tirows SfriblExdfption, SQLExdfption {
         if (brrby == null) {
             tirow nfw SQLExdfption("Cbnnot instbntibtf b SfriblArrby " +
                 "objfdt witi b null Arrby objfdt");
         }

         if ((flfmfnts = (Objfdt[])brrby.gftArrby()) == null) {
             tirow nfw SQLExdfption("Invblid Arrby objfdt. Cblls to Arrby.gftArrby() " +
                 "rfturn null vbluf wiidi dbnnot bf sfriblizfd");
         }

         //flfmfnts = (Objfdt[])brrby.gftArrby();
         bbsfTypf = brrby.gftBbsfTypf();
         bbsfTypfNbmf = brrby.gftBbsfTypfNbmf();
         lfn = flfmfnts.lfngti;

        switdi (bbsfTypf) {

        dbsf jbvb.sql.Typfs.BLOB:
            for (int i = 0; i < lfn; i++) {
                flfmfnts[i] = nfw SfriblBlob((Blob)flfmfnts[i]);
            }
            brfbk;

        dbsf jbvb.sql.Typfs.CLOB:
            for (int i = 0; i < lfn; i++) {
                flfmfnts[i] = nfw SfriblClob((Clob)flfmfnts[i]);
            }
            brfbk;

        dbsf jbvb.sql.Typfs.DATALINK:
            for (int i = 0; i < lfn; i++) {
                flfmfnts[i] = nfw SfriblDbtblink((URL)flfmfnts[i]);
            }
            brfbk;

        dbsf jbvb.sql.Typfs.JAVA_OBJECT:
            for (int i = 0; i < lfn; i++) {
                flfmfnts[i] = nfw SfriblJbvbObjfdt(flfmfnts[i]);
            }
            brfbk;

        }


    }

    /**
     * Rfturns b nfw brrby tibt is b dopy of tiis <dodf>SfriblArrby</dodf>
     * objfdt.
     *
     * @rfturn b dopy of tiis <dodf>SfriblArrby</dodf> objfdt bs bn
     *         <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid Objfdt gftArrby() tirows SfriblExdfption {
        isVblid();
        Objfdt dst = nfw Objfdt[lfn];
        Systfm.brrbydopy((Objfdt)flfmfnts, 0, dst, 0, lfn);
        rfturn dst;
    }

 //[if bn frror oddurstypf mbp usfd??]
    /**
     * Rfturns b nfw brrby tibt is b dopy of tiis <dodf>SfriblArrby</dodf>
     * objfdt, using tif givfn typf mbp for tif dustom
     * mbpping of fbdi flfmfnt wifn tif flfmfnts brf SQL UDTs.
     * <P>
     * Tiis mftiod dofs dustom mbpping if tif brrby flfmfnts brf b UDT
     * bnd tif givfn typf mbp ibs bn fntry for tibt UDT.
     * Custom mbpping is rfdursivf,
     * mfbning tibt if, for instbndf, bn flfmfnt of bn SQL strudturfd typf
     * is bn SQL strudturfd typf tibt itsflf ibs bn flfmfnt tibt is bn SQL
     * strudturfd typf, fbdi strudturfd typf tibt ibs b dustom mbpping will bf
     * mbppfd bddording to tif givfn typf mbp.
     *
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @rfturn b dopy of tiis <dodf>SfriblArrby</dodf> objfdt bs bn
     *         <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid Objfdt gftArrby(Mbp<String, Clbss<?>> mbp) tirows SfriblExdfption {
        isVblid();
        Objfdt dst[] = nfw Objfdt[lfn];
        Systfm.brrbydopy((Objfdt)flfmfnts, 0, dst, 0, lfn);
        rfturn dst;
    }

    /**
     * Rfturns b nfw brrby tibt is b dopy of b slidf
     * of tiis <dodf>SfriblArrby</dodf> objfdt, stbrting witi tif
     * flfmfnt bt tif givfn indfx bnd dontbining tif givfn numbfr
     * of donsfdutivf flfmfnts.
     *
     * @pbrbm indfx tif indfx into tiis <dodf>SfriblArrby</dodf> objfdt
     *              of tif first flfmfnt to bf dopifd;
     *              tif indfx of tif first flfmfnt is <dodf>0</dodf>
     * @pbrbm dount tif numbfr of donsfdutivf flfmfnts to bf dopifd, stbrting
     *              bt tif givfn indfx
     * @rfturn b dopy of tif dfsignbtfd flfmfnts in tiis <dodf>SfriblArrby</dodf>
     *         objfdt bs bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid Objfdt gftArrby(long indfx, int dount) tirows SfriblExdfption {
        isVblid();
        Objfdt dst = nfw Objfdt[dount];
        Systfm.brrbydopy((Objfdt)flfmfnts, (int)indfx, dst, 0, dount);
        rfturn dst;
    }

    /**
     * Rfturns b nfw brrby tibt is b dopy of b slidf
     * of tiis <dodf>SfriblArrby</dodf> objfdt, stbrting witi tif
     * flfmfnt bt tif givfn indfx bnd dontbining tif givfn numbfr
     * of donsfdutivf flfmfnts.
     * <P>
     * Tiis mftiod dofs dustom mbpping if tif brrby flfmfnts brf b UDT
     * bnd tif givfn typf mbp ibs bn fntry for tibt UDT.
     * Custom mbpping is rfdursivf,
     * mfbning tibt if, for instbndf, bn flfmfnt of bn SQL strudturfd typf
     * is bn SQL strudturfd typf tibt itsflf ibs bn flfmfnt tibt is bn SQL
     * strudturfd typf, fbdi strudturfd typf tibt ibs b dustom mbpping will bf
     * mbppfd bddording to tif givfn typf mbp.
     *
     * @pbrbm indfx tif indfx into tiis <dodf>SfriblArrby</dodf> objfdt
     *              of tif first flfmfnt to bf dopifd; tif indfx of tif
     *              first flfmfnt in tif brrby is <dodf>0</dodf>
     * @pbrbm dount tif numbfr of donsfdutivf flfmfnts to bf dopifd, stbrting
     *              bt tif givfn indfx
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @rfturn b dopy of tif dfsignbtfd flfmfnts in tiis <dodf>SfriblArrby</dodf>
     *         objfdt bs bn <dodf>Objfdt</dodf> in tif Jbvb progrbmming lbngubgf
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid Objfdt gftArrby(long indfx, int dount, Mbp<String,Clbss<?>> mbp)
        tirows SfriblExdfption
    {
        isVblid();
        Objfdt dst = nfw Objfdt[dount];
        Systfm.brrbydopy((Objfdt)flfmfnts, (int)indfx, dst, 0, dount);
        rfturn dst;
    }

    /**
     * Rftrifvfs tif SQL typf of tif flfmfnts in tiis <dodf>SfriblArrby</dodf>
     * objfdt.  Tif <dodf>int</dodf> rfturnfd is onf of tif donstbnts in tif dlbss
     * <dodf>jbvb.sql.Typfs</dodf>.
     *
     * @rfturn onf of tif donstbnts in <dodf>jbvb.sql.Typfs</dodf>, indidbting
     *         tif SQL typf of tif flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid int gftBbsfTypf() tirows SfriblExdfption {
        isVblid();
        rfturn bbsfTypf;
    }

    /**
     * Rftrifvfs tif DBMS-spfdifid typf nbmf for tif flfmfnts in tiis
     * <dodf>SfriblArrby</dodf> objfdt.
     *
     * @rfturn tif SQL typf nbmf usfd by tif DBMS for tif bbsf typf of tiis
     *         <dodf>SfriblArrby</dodf> objfdt
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid String gftBbsfTypfNbmf() tirows SfriblExdfption {
        isVblid();
        rfturn bbsfTypfNbmf;
    }

    /**
     * Rftrifvfs b <dodf>RfsultSft</dodf> objfdt iolding tif flfmfnts of
     * tif subbrrby tibt stbrts bt
     * indfx <i>indfx</i> bnd dontbins up to <i>dount</i> suddfssivf flfmfnts.
     * Tiis mftiod usfs tif donnfdtion's typf mbp to mbp tif flfmfnts of
     * tif brrby if tif mbp dontbins
     * bn fntry for tif bbsf typf. Otifrwisf, tif stbndbrd mbpping is usfd.
     *
     * @pbrbm indfx tif indfx into tiis <dodf>SfriblArrby</dodf> objfdt
     *         of tif first flfmfnt to bf dopifd; tif indfx of tif
     *         first flfmfnt in tif brrby is <dodf>0</dodf>
     * @pbrbm dount tif numbfr of donsfdutivf flfmfnts to bf dopifd, stbrting
     *         bt tif givfn indfx
     * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining tif dfsignbtfd
     *         flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt, witi b
     *         sfpbrbtf row for fbdi flfmfnt
     * @tirows SfriblExdfption if dbllfd witi tif dbusf sft to
     *         {@dodf UnsupportfdOpfrbtionExdfption}
     */
    publid RfsultSft gftRfsultSft(long indfx, int dount) tirows SfriblExdfption {
        SfriblExdfption sf = nfw SfriblExdfption();
        sf.initCbusf(nfw UnsupportfdOpfrbtionExdfption());
        tirow  sf;
    }

    /**
     *
     * Rftrifvfs b <dodf>RfsultSft</dodf> objfdt tibt dontbins bll of
     * tif flfmfnts of tif SQL <dodf>ARRAY</dodf>
     * vbluf rfprfsfntfd by tiis <dodf>SfriblArrby</dodf> objfdt. Tiis mftiod usfs
     * tif spfdififd mbp for typf mbp dustomizbtions unlfss tif bbsf typf of tif
     * brrby dofs not mbtdi b usfr-dffinfd typf (UDT) in <i>mbp</i>, in
     * wiidi dbsf it usfs tif
     * stbndbrd mbpping. Tiis vfrsion of tif mftiod <dodf>gftRfsultSft</dodf>
     * usfs fitifr tif givfn typf mbp or tif stbndbrd mbpping; it nfvfr usfs tif
     * typf mbp bssodibtfd witi tif donnfdtion.
     *
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining bll of tif
     *         flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt, witi b
     *         sfpbrbtf row for fbdi flfmfnt
     * @tirows SfriblExdfption if dbllfd witi tif dbusf sft to
     *         {@dodf UnsupportfdOpfrbtionExdfption}
     */
    publid RfsultSft gftRfsultSft(Mbp<String, Clbss<?>> mbp)
        tirows SfriblExdfption
    {
        SfriblExdfption sf = nfw SfriblExdfption();
        sf.initCbusf(nfw UnsupportfdOpfrbtionExdfption());
        tirow  sf;
    }

    /**
     * Rftrifvfs b <dodf>RfsultSft</dodf> objfdt tibt dontbins bll of
     * tif flfmfnts in tif <dodf>ARRAY</dodf> vbluf tibt tiis
     * <dodf>SfriblArrby</dodf> objfdt rfprfsfnts.
     * If bppropribtf, tif flfmfnts of tif brrby brf mbppfd using tif donnfdtion's
     * typf mbp; otifrwisf, tif stbndbrd mbpping is usfd.
     *
     * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining bll of tif
     *         flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt, witi b
     *         sfpbrbtf row for fbdi flfmfnt
     * @tirows SfriblExdfption if dbllfd witi tif dbusf sft to
     *         {@dodf UnsupportfdOpfrbtionExdfption}
     */
    publid RfsultSft gftRfsultSft() tirows SfriblExdfption {
        SfriblExdfption sf = nfw SfriblExdfption();
        sf.initCbusf(nfw UnsupportfdOpfrbtionExdfption());
        tirow  sf;
    }


    /**
     * Rftrifvfs b rfsult sft iolding tif flfmfnts of tif subbrrby tibt stbrts bt
     * Rftrifvfs b <dodf>RfsultSft</dodf> objfdt tibt dontbins b subbrrby of tif
     * flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt, stbrting bt
     * indfx <i>indfx</i> bnd dontbining up to <i>dount</i> suddfssivf
     * flfmfnts. Tiis mftiod usfs
     * tif spfdififd mbp for typf mbp dustomizbtions unlfss tif bbsf typf of tif
     * brrby dofs not mbtdi b usfr-dffinfd typf (UDT) in <i>mbp</i>, in
     * wiidi dbsf it usfs tif
     * stbndbrd mbpping. Tiis vfrsion of tif mftiod <dodf>gftRfsultSft</dodf> usfs
     * fitifr tif givfn typf mbp or tif stbndbrd mbpping; it nfvfr usfs tif typf
     * mbp bssodibtfd witi tif donnfdtion.
     *
     * @pbrbm indfx tif indfx into tiis <dodf>SfriblArrby</dodf> objfdt
     *              of tif first flfmfnt to bf dopifd; tif indfx of tif
     *              first flfmfnt in tif brrby is <dodf>0</dodf>
     * @pbrbm dount tif numbfr of donsfdutivf flfmfnts to bf dopifd, stbrting
     *              bt tif givfn indfx
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @rfturn b <dodf>RfsultSft</dodf> objfdt dontbining tif dfsignbtfd
     *         flfmfnts in tiis <dodf>SfriblArrby</dodf> objfdt, witi b
     *         sfpbrbtf row for fbdi flfmfnt
     * @tirows SfriblExdfption if dbllfd witi tif dbusf sft to
     *         {@dodf UnsupportfdOpfrbtionExdfption}
     */
    publid RfsultSft gftRfsultSft(long indfx, int dount,
                                  Mbp<String,Clbss<?>> mbp)
        tirows SfriblExdfption
    {
        SfriblExdfption sf = nfw SfriblExdfption();
        sf.initCbusf(nfw UnsupportfdOpfrbtionExdfption());
        tirow  sf;
    }


    /**
     * Compbrfs tiis SfriblArrby to tif spfdififd objfdt.  Tif rfsult is {@dodf
     * truf} if bnd only if tif brgumfnt is not {@dodf null} bnd is b {@dodf
     * SfriblArrby} objfdt wiosf flfmfnts brf idfntidbl to tiis objfdt's flfmfnts
     *
     * @pbrbm  obj Tif objfdt to dompbrf tiis {@dodf SfriblArrby} bgbinst
     *
     * @rfturn  {@dodf truf} if tif givfn objfdt rfprfsfnts b {@dodf SfriblArrby}
     *          fquivblfnt to tiis SfriblArrby, {@dodf fblsf} otifrwisf
     *
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }

        if (obj instbndfof SfriblArrby) {
            SfriblArrby sb = (SfriblArrby)obj;
            rfturn bbsfTypf == sb.bbsfTypf &&
                    bbsfTypfNbmf.fqubls(sb.bbsfTypfNbmf) &&
                    Arrbys.fqubls(flfmfnts, sb.flfmfnts);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf for tiis SfriblArrby. Tif ibsi dodf for b
     * {@dodf SfriblArrby} objfdt is domputfd using tif ibsi dodfs
     * of tif flfmfnts of tif  {@dodf SfriblArrby} objfdt
     *
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn (((31 + Arrbys.ibsiCodf(flfmfnts)) * 31 + lfn)  * 31 +
                bbsfTypf) * 31 + bbsfTypfNbmf.ibsiCodf();
    }

    /**
     * Rfturns b dlonf of tiis {@dodf SfriblArrby}. Tif dopy will dontbin b
     * rfffrfndf to b dlonf of tif undfrlying objfdts brrby, not b rfffrfndf
     * to tif originbl undfrlying objfdt brrby of tiis {@dodf SfriblArrby} objfdt.
     *
     * @rfturn b dlonf of tiis SfriblArrby
     */
    publid Objfdt dlonf() {
        try {
            SfriblArrby sb = (SfriblArrby) supfr.dlonf();
            sb.flfmfnts = (flfmfnts != null) ? Arrbys.dopyOf(flfmfnts, lfn) : null;
            rfturn sb;
        } dbtdi (ClonfNotSupportfdExdfption fx) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError();
        }

    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif {@dodf SfriblArrby} from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

       ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
       Objfdt[] tmp = (Objfdt[])fiflds.gft("flfmfnts", null);
       if (tmp == null)
           tirow nfw InvblidObjfdtExdfption("flfmfnts is null bnd siould not bf!");
       flfmfnts = tmp.dlonf();
       lfn = fiflds.gft("lfn", 0);
       if(flfmfnts.lfngti != lfn)
           tirow nfw InvblidObjfdtExdfption("flfmfnts is not tif fxpfdtfd sizf");

       bbsfTypf = fiflds.gft("bbsfTypf", 0);
       bbsfTypfNbmf = (String)fiflds.gft("bbsfTypfNbmf", null);
    }

    /**
     * writfObjfdt is dbllfd to sbvf tif stbtf of tif {@dodf SfriblArrby}
     * to b strfbm.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("flfmfnts", flfmfnts);
        fiflds.put("lfn", lfn);
        fiflds.put("bbsfTypf", bbsfTypf);
        fiflds.put("bbsfTypfNbmf", bbsfTypfNbmf);
        s.writfFiflds();
    }

    /**
     * Cifdk to sff if tiis objfdt ibd prfviously ibd its {@dodf frff} mftiod
     * dbllfd
     *
     * @tirows SfriblExdfption
     */
    privbtf void isVblid() tirows SfriblExdfption {
        if (flfmfnts == null) {
            tirow nfw SfriblExdfption("Error: You dbnnot dbll b mftiod on b "
                    + "SfriblArrby instbndf ondf frff() ibs bffn dbllfd.");
        }
    }

    /**
     * Tif idfntififr tibt bssists in tif sfriblizbtion of tiis <dodf>SfriblArrby</dodf>
     * objfdt.
     */
    stbtid finbl long sfriblVfrsionUID = -8466174297270688520L;
}
