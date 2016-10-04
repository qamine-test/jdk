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
import jbvbx.sql.*;
import jbvb.io.*;
import jbvb.mbti.*;
import jbvb.util.Arrbys;
import jbvb.util.Mbp;
import jbvb.util.Vfdtor;

import jbvbx.sql.rowsft.*;

/**
 * A sfriblizfd mbpping in tif Jbvb progrbmming lbngubgf of bn SQL
 * strudturfd typf. Ebdi bttributf tibt is not blrfbdy sfriblizfd
 * is mbppfd to b sfriblizfd form, bnd if bn bttributf is itsflf
 * b strudturfd typf, fbdi of its bttributfs tibt is not blrfbdy
 * sfriblizfd is mbppfd to b sfriblizfd form.
 * <P>
 * In bddition, tif strudturfd typf is dustom mbppfd to b dlbss in tif
 * Jbvb progrbmming lbngubgf if tifrf is sudi b mbpping, bs brf
 * its bttributfs, if bppropribtf.
 * <P>
 * Tif <dodf>SfriblStrudt</dodf> dlbss providfs b donstrudtor for drfbting
 * bn instbndf from b <dodf>Strudt</dodf> objfdt, b mftiod for rftrifving
 * tif SQL typf nbmf of tif SQL strudturfd typf in tif dbtbbbsf, bnd mftiods
 * for rftrifving its bttributf vblufs.
 *
 * <i3> Tirfbd sbffty </i3>
 *
 * A SfriblStrudt is not sbff for usf by multiplf dondurrfnt tirfbds.  If b
 * SfriblStrudt is to bf usfd by morf tibn onf tirfbd tifn bddfss to tif
 * SfriblStrudt siould bf dontrollfd by bppropribtf syndironizbtion.
 *
 * @sindf 1.5
 */
publid dlbss SfriblStrudt implfmfnts Strudt, Sfriblizbblf, Clonfbblf {


    /**
     * Tif SQL typf nbmf for tif strudturfd typf tibt tiis
     * <dodf>SfriblStrudt</dodf> objfdt rfprfsfnts.  Tiis is tif nbmf
     * usfd in tif SQL dffinition of tif SQL strudturfd typf.
     *
     * @sfribl
     */
    privbtf String SQLTypfNbmf;

    /**
     * An brrby of <dodf>Objfdt</dodf> instbndfs in  wiidi fbdi
     * flfmfnt is bn bttributf of tif SQL strudturfd typf tibt tiis
     * <dodf>SfriblStrudt</dodf> objfdt rfprfsfnts.  Tif bttributfs brf
     * ordfrfd bddording to tifir ordfr in tif dffinition of tif
     * SQL strudturfd typf.
     *
     * @sfribl
     */
    privbtf Objfdt bttribs[];

    /**
     * Construdts b <dodf>SfriblStrudt</dodf> objfdt from tif givfn
     * <dodf>Strudt</dodf> objfdt, using tif givfn <dodf>jbvb.util.Mbp</dodf>
     * objfdt for dustom mbpping tif SQL strudturfd typf or bny of its
     * bttributfs tibt brf SQL strudturfd typfs.
     *
     * @pbrbm in bn instbndf of {@dodf Strudt}
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @tirows SfriblExdfption if bn frror oddurs
     * @sff jbvb.sql.Strudt
     */
     publid SfriblStrudt(Strudt in, Mbp<String,Clbss<?>> mbp)
         tirows SfriblExdfption
     {

        try {

        // gft tif typf nbmf
        SQLTypfNbmf = in.gftSQLTypfNbmf();
        Systfm.out.println("SQLTypfNbmf: " + SQLTypfNbmf);

        // gft tif bttributfs of tif strudt
        bttribs = in.gftAttributfs(mbp);

        /*
         * tif brrby mby dontbin furtifr Strudts
         * bnd/or dlbssfs tibt ibvf bffn mbppfd,
         * otifr typfs tibt wf ibvf to sfriblizf
         */
        mbpToSfribl(mbp);

        } dbtdi (SQLExdfption f) {
            tirow nfw SfriblExdfption(f.gftMfssbgf());
        }
    }

     /**
      * Construdts b <dodf>SfriblStrudt</dodf> objfdt from tif
      * givfn <dodf>SQLDbtb</dodf> objfdt, using tif givfn typf
      * mbp to dustom mbp it to b dlbss in tif Jbvb progrbmming
      * lbngubgf.  Tif typf mbp givfs tif SQL typf bnd tif dlbss
      * to wiidi it is mbppfd.  Tif <dodf>SQLDbtb</dodf> objfdt
      * dffinfs tif dlbss to wiidi tif SQL typf will bf mbppfd.
      *
      * @pbrbm in bn instbndf of tif <dodf>SQLDbtb</dodf> dlbss
      *           tibt dffinfs tif mbpping of tif SQL strudturfd
      *           typf to onf or morf objfdts in tif Jbvb progrbmming lbngubgf
      * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
      *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
      *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
      *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
      *        tibt dffinfs iow tif UDT is to bf mbppfd
      * @tirows SfriblExdfption if bn frror oddurs
      */
    publid SfriblStrudt(SQLDbtb in, Mbp<String,Clbss<?>> mbp)
        tirows SfriblExdfption
    {

        try {

        //sft tif typf nbmf
        SQLTypfNbmf = in.gftSQLTypfNbmf();

        Vfdtor<Objfdt> tmp = nfw Vfdtor<>();
        in.writfSQL(nfw SQLOutputImpl(tmp, mbp));
        bttribs = tmp.toArrby();

        } dbtdi (SQLExdfption f) {
            tirow nfw SfriblExdfption(f.gftMfssbgf());
        }
    }


    /**
     * Rftrifvfs tif SQL typf nbmf for tiis <dodf>SfriblStrudt</dodf>
     * objfdt. Tiis is tif nbmf usfd in tif SQL dffinition of tif
     * strudturfd typf
     *
     * @rfturn b <dodf>String</dodf> objfdt rfprfsfnting tif SQL
     *         typf nbmf for tif SQL strudturfd typf tibt tiis
     *         <dodf>SfriblStrudt</dodf> objfdt rfprfsfnts
     * @tirows SfriblExdfption if bn frror oddurs
     */
    publid String gftSQLTypfNbmf() tirows SfriblExdfption {
        rfturn SQLTypfNbmf;
    }

    /**
     * Rftrifvfs bn brrby of <dodf>Objfdt</dodf> vblufs dontbining tif
     * bttributfs of tif SQL strudturfd typf tibt tiis
     * <dodf>SfriblStrudt</dodf> objfdt rfprfsfnts.
     *
     * @rfturn bn brrby of <dodf>Objfdt</dodf> vblufs, witi fbdi
     *         flfmfnt bfing bn bttributf of tif SQL strudturfd typf
     *         tibt tiis <dodf>SfriblStrudt</dodf> objfdt rfprfsfnts
     * @tirows SfriblExdfption if bn frror oddurs
     */
    publid Objfdt[]  gftAttributfs() tirows SfriblExdfption {
        Objfdt[] vbl = tiis.bttribs;
        rfturn (vbl == null) ? null : Arrbys.dopyOf(vbl, vbl.lfngti);
    }

    /**
     * Rftrifvfs tif bttributfs for tif SQL strudturfd typf tibt
     * tiis <dodf>SfriblStrudt</dodf> rfprfsfnts bs bn brrby of
     * <dodf>Objfdt</dodf> vblufs, using tif givfn typf mbp for
     * dustom mbpping if bppropribtf.
     *
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @rfturn bn brrby of <dodf>Objfdt</dodf> vblufs, witi fbdi
     *         flfmfnt bfing bn bttributf of tif SQL strudturfd
     *         typf tibt tiis <dodf>SfriblStrudt</dodf> objfdt
     *         rfprfsfnts
     * @tirows SfriblExdfption if bn frror oddurs
     */
    publid Objfdt[] gftAttributfs(Mbp<String,Clbss<?>> mbp)
        tirows SfriblExdfption
    {
        Objfdt[] vbl = tiis.bttribs;
        rfturn (vbl == null) ? null : Arrbys.dopyOf(vbl, vbl.lfngti);
    }


    /**
     * Mbps bttributfs of bn SQL strudturfd typf tibt brf not
     * sfriblizfd to b sfriblizfd form, using tif givfn typf mbp
     * for dustom mbpping wifn bppropribtf.  Tif following typfs
     * in tif Jbvb progrbmming lbngubgf brf mbppfd to tifir
     * sfriblizfd forms:  <dodf>Strudt</dodf>, <dodf>SQLDbtb</dodf>,
     * <dodf>Rff</dodf>, <dodf>Blob</dodf>, <dodf>Clob</dodf>, bnd
     * <dodf>Arrby</dodf>.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly bnd is not usfd by bn
     * bpplidbtion progrbmmfr.
     *
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt in wiidi
     *        fbdi fntry donsists of 1) b <dodf>String</dodf> objfdt
     *        giving tif fully qublififd nbmf of b UDT bnd 2) tif
     *        <dodf>Clbss</dodf> objfdt for tif <dodf>SQLDbtb</dodf> implfmfntbtion
     *        tibt dffinfs iow tif UDT is to bf mbppfd
     * @tirows SfriblExdfption if bn frror oddurs
     */
    privbtf void mbpToSfribl(Mbp<String,Clbss<?>> mbp) tirows SfriblExdfption {

        try {

        for (int i = 0; i < bttribs.lfngti; i++) {
            if (bttribs[i] instbndfof Strudt) {
                bttribs[i] = nfw SfriblStrudt((Strudt)bttribs[i], mbp);
            } flsf if (bttribs[i] instbndfof SQLDbtb) {
                bttribs[i] = nfw SfriblStrudt((SQLDbtb)bttribs[i], mbp);
            } flsf if (bttribs[i] instbndfof Blob) {
                bttribs[i] = nfw SfriblBlob((Blob)bttribs[i]);
            } flsf if (bttribs[i] instbndfof Clob) {
                bttribs[i] = nfw SfriblClob((Clob)bttribs[i]);
            } flsf if (bttribs[i] instbndfof Rff) {
                bttribs[i] = nfw SfriblRff((Rff)bttribs[i]);
            } flsf if (bttribs[i] instbndfof jbvb.sql.Arrby) {
                bttribs[i] = nfw SfriblArrby((jbvb.sql.Arrby)bttribs[i], mbp);
            }
        }

        } dbtdi (SQLExdfption f) {
            tirow nfw SfriblExdfption(f.gftMfssbgf());
        }
        rfturn;
    }

    /**
     * Compbrfs tiis SfriblStrudt to tif spfdififd objfdt.  Tif rfsult is
     * {@dodf truf} if bnd only if tif brgumfnt is not {@dodf null} bnd is b
     * {@dodf SfriblStrudt} objfdt wiosf bttributfs brf idfntidbl to tiis
     * objfdt's bttributfs
     *
     * @pbrbm  obj Tif objfdt to dompbrf tiis {@dodf SfriblStrudt} bgbinst
     *
     * @rfturn {@dodf truf} if tif givfn objfdt rfprfsfnts b {@dodf SfriblStrudt}
     *          fquivblfnt to tiis SfriblStrudt, {@dodf fblsf} otifrwisf
     *
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof SfriblStrudt) {
            SfriblStrudt ss = (SfriblStrudt)obj;
            rfturn SQLTypfNbmf.fqubls(ss.SQLTypfNbmf) &&
                    Arrbys.fqubls(bttribs, ss.bttribs);
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf SfriblStrudt}. Tif ibsi dodf for b
     * {@dodf SfriblStrudt} objfdt is domputfd using tif ibsi dodfs
     * of tif bttributfs of tif {@dodf SfriblStrudt} objfdt bnd its
     * {@dodf SQLTypfNbmf}
     *
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
        rfturn ((31 + Arrbys.ibsiCodf(bttribs)) * 31) * 31
                + SQLTypfNbmf.ibsiCodf();
    }

    /**
     * Rfturns b dlonf of tiis {@dodf SfriblStrudt}. Tif dopy will dontbin b
     * rfffrfndf to b dlonf of tif undfrlying bttribs brrby, not b rfffrfndf
     * to tif originbl undfrlying bttribs brrby of tiis {@dodf SfriblStrudt} objfdt.
     *
     * @rfturn  b dlonf of tiis SfriblStrudt
     */
    publid Objfdt dlonf() {
        try {
            SfriblStrudt ss = (SfriblStrudt) supfr.dlonf();
            ss.bttribs = Arrbys.dopyOf(bttribs, bttribs.lfngti);
            rfturn ss;
        } dbtdi (ClonfNotSupportfdExdfption fx) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError();
        }

    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif {@dodf SfriblStrudt} from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

       ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
       Objfdt[] tmp = (Objfdt[])fiflds.gft("bttribs", null);
       bttribs = tmp == null ? null : tmp.dlonf();
       SQLTypfNbmf = (String)fiflds.gft("SQLTypfNbmf", null);
    }

    /**
     * writfObjfdt is dbllfd to sbvf tif stbtf of tif {@dodf SfriblStrudt}
     * to b strfbm.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("bttribs", bttribs);
        fiflds.put("SQLTypfNbmf", SQLTypfNbmf);
        s.writfFiflds();
    }

    /**
     * Tif idfntififr tibt bssists in tif sfriblizbtion of tiis
     * <dodf>SfriblStrudt</dodf> objfdt.
     */
    stbtid finbl long sfriblVfrsionUID = -8322445504027483372L;
}
