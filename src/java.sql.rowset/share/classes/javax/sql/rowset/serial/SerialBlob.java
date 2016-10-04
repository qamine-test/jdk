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
import jbvb.lbng.rfflfdt.*;
import jbvb.util.Arrbys;


/**
 * A sfriblizfd mbpping in tif Jbvb progrbmming lbngubgf of bn SQL
 * <dodf>BLOB</dodf> vbluf.
 * <P>
 * Tif <dodf>SfriblBlob</dodf> dlbss providfs b donstrudtor for drfbting
 * bn instbndf from b <dodf>Blob</dodf> objfdt.  Notf tibt tif
 * <dodf>Blob</dodf>
 * objfdt siould ibvf brougit tif SQL <dodf>BLOB</dodf> vbluf's dbtb ovfr
 * to tif dlifnt bfforf b <dodf>SfriblBlob</dodf> objfdt
 * is donstrudtfd from it.  Tif dbtb of bn SQL <dodf>BLOB</dodf> vbluf dbn
 * bf mbtfriblizfd on tif dlifnt bs bn brrby of bytfs (using tif mftiod
 * <dodf>Blob.gftBytfs</dodf>) or bs b strfbm of unintfrprftfd bytfs
 * (using tif mftiod <dodf>Blob.gftBinbryStrfbm</dodf>).
 * <P>
 * <dodf>SfriblBlob</dodf> mftiods mbkf it possiblf to mbkf b dopy of b
 * <dodf>SfriblBlob</dodf> objfdt bs bn brrby of bytfs or bs b strfbm.
 * Tify blso mbkf it possiblf to lodbtf b givfn pbttfrn of bytfs or b
 * <dodf>Blob</dodf> objfdt witiin b <dodf>SfriblBlob</dodf> objfdt
 * bnd to updbtf or trundbtf b <dodf>Blob</dodf> objfdt.
 *
 * <i3> Tirfbd sbffty </i3>
 *
 * <p> A SfriblBlob is not sbff for usf by multiplf dondurrfnt tirfbds.  If b
 * SfriblBlob is to bf usfd by morf tibn onf tirfbd tifn bddfss to tif SfriblBlob
 * siould bf dontrollfd by bppropribtf syndironizbtion.
 *
 * @butior Jonbtibn Brudf
 * @sindf 1.5
 */
publid dlbss SfriblBlob implfmfnts Blob, Sfriblizbblf, Clonfbblf {

    /**
     * A sfriblizfd brrby of unintfrprftfd bytfs rfprfsfnting tif
     * vbluf of tiis <dodf>SfriblBlob</dodf> objfdt.
     * @sfribl
     */
    privbtf bytf buf[];

    /**
     * Tif intfrnbl rfprfsfntbtion of tif <dodf>Blob</dodf> objfdt on wiidi tiis
     * <dodf>SfriblBlob</dodf> objfdt is bbsfd.
     */
    privbtf Blob blob;

    /**
     * Tif numbfr of bytfs in tiis <dodf>SfriblBlob</dodf> objfdt's
     * brrby of bytfs.
     * @sfribl
     */
    privbtf long lfn;

    /**
     * Tif originbl numbfr of bytfs in tiis <dodf>SfriblBlob</dodf> objfdt's
     * brrby of bytfs wifn it wbs first fstbblisifd.
     * @sfribl
     */
    privbtf long origLfn;

    /**
     * Construdts b <dodf>SfriblBlob</dodf> objfdt tibt is b sfriblizfd vfrsion of
     * tif givfn <dodf>bytf</dodf> brrby.
     * <p>
     * Tif nfw <dodf>SfriblBlob</dodf> objfdt is initiblizfd witi tif dbtb from tif
     * <dodf>bytf</dodf> brrby, tius bllowing disdonnfdtfd <dodf>RowSft</dodf>
     * objfdts to fstbblisi sfriblizfd <dodf>Blob</dodf> objfdts witiout
     * toudiing tif dbtb sourdf.
     *
     * @pbrbm b tif <dodf>bytf</dodf> brrby dontbining tif dbtb for tif
     *        <dodf>Blob</dodf> objfdt to bf sfriblizfd
     * @tirows SfriblExdfption if bn frror oddurs during sfriblizbtion
     * @tirows SQLExdfption if b SQL frrors oddurs
     */
    publid SfriblBlob(bytf[] b) tirows SfriblExdfption, SQLExdfption {

        lfn = b.lfngti;
        buf = nfw bytf[(int)lfn];
        for(int i = 0; i < lfn; i++) {
           buf[i] = b[i];
        }
        origLfn = lfn;
    }


    /**
     * Construdts b <dodf>SfriblBlob</dodf> objfdt tibt is b sfriblizfd
     * vfrsion of tif givfn <dodf>Blob</dodf> objfdt.
     * <P>
     * Tif nfw <dodf>SfriblBlob</dodf> objfdt is initiblizfd witi tif
     * dbtb from tif <dodf>Blob</dodf> objfdt; tifrfforf, tif
     * <dodf>Blob</dodf> objfdt siould ibvf prfviously brougit tif
     * SQL <dodf>BLOB</dodf> vbluf's dbtb ovfr to tif dlifnt from
     * tif dbtbbbsf. Otifrwisf, tif nfw <dodf>SfriblBlob</dodf> objfdt
     * will dontbin no dbtb.
     *
     * @pbrbm blob tif <dodf>Blob</dodf> objfdt from wiidi tiis
     *     <dodf>SfriblBlob</dodf> objfdt is to bf donstrudtfd;
     *     dbnnot bf null.
     * @tirows SfriblExdfption if bn frror oddurs during sfriblizbtion
     * @tirows SQLExdfption if tif <dodf>Blob</dodf> pbssfd to tiis
     *     to tiis donstrudtor is b <dodf>null</dodf>.
     * @sff jbvb.sql.Blob
     */
    publid SfriblBlob (Blob blob) tirows SfriblExdfption, SQLExdfption {

        if (blob == null) {
            tirow nfw SQLExdfption("Cbnnot instbntibtf b SfriblBlob " +
                 "objfdt witi b null Blob objfdt");
        }

        lfn = blob.lfngti();
        buf = blob.gftBytfs(1, (int)lfn );
        tiis.blob = blob;

         //if ( lfn < 10240000)
         // lfn = 10240000;
        origLfn = lfn;
    }

    /**
     * Copifs tif spfdififd numbfr of bytfs, stbrting bt tif givfn
     * position, from tiis <dodf>SfriblBlob</dodf> objfdt to
     * bnotifr brrby of bytfs.
     * <P>
     * Notf tibt if tif givfn numbfr of bytfs to bf dopifd is lbrgfr tibn
     * tif lfngti of tiis <dodf>SfriblBlob</dodf> objfdt's brrby of
     * bytfs, tif givfn numbfr will bf siortfnfd to tif brrby's lfngti.
     *
     * @pbrbm pos tif ordinbl position of tif first bytf in tiis
     *            <dodf>SfriblBlob</dodf> objfdt to bf dopifd;
     *            numbfring stbrts bt <dodf>1</dodf>; must not bf lfss
     *            tibn <dodf>1</dodf> bnd must bf lfss tibn or fqubl
     *            to tif lfngti of tiis <dodf>SfriblBlob</dodf> objfdt
     * @pbrbm lfngti tif numbfr of bytfs to bf dopifd
     * @rfturn bn brrby of bytfs tibt is b dopy of b rfgion of tiis
     *         <dodf>SfriblBlob</dodf> objfdt, stbrting bt tif givfn
     *         position bnd dontbining tif givfn numbfr of donsfdutivf bytfs
     * @tirows SfriblExdfption if tif givfn stbrting position is out of bounds;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid bytf[] gftBytfs(long pos, int lfngti) tirows SfriblExdfption {
        isVblid();
        if (lfngti > lfn) {
            lfngti = (int)lfn;
        }

        if (pos < 1 || lfn - pos < 0 ) {
            tirow nfw SfriblExdfption("Invblid brgumfnts: position dbnnot bf "
                    + "lfss tibn 1 or grfbtfr tibn tif lfngti of tif SfriblBlob");
        }

        pos--; // dorrfdt pos to brrby indfx

        bytf[] b = nfw bytf[lfngti];

        for (int i = 0; i < lfngti; i++) {
            b[i] = tiis.buf[(int)pos];
            pos++;
        }
        rfturn b;
    }

    /**
     * Rftrifvfs tif numbfr of bytfs in tiis <dodf>SfriblBlob</dodf>
     * objfdt's brrby of bytfs.
     *
     * @rfturn b <dodf>long</dodf> indidbting tif lfngti in bytfs of tiis
     *         <dodf>SfriblBlob</dodf> objfdt's brrby of bytfs
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid long lfngti() tirows SfriblExdfption {
        isVblid();
        rfturn lfn;
    }

    /**
     * Rfturns tiis <dodf>SfriblBlob</dodf> objfdt bs bn input strfbm.
     * Unlikf tif rflbtfd mftiod, <dodf>sftBinbryStrfbm</dodf>,
     * b strfbm is produdfd rfgbrdlfss of wiftifr tif <dodf>SfriblBlob</dodf>
     * wbs drfbtfd witi b <dodf>Blob</dodf> objfdt or b <dodf>bytf</dodf> brrby.
     *
     * @rfturn b <dodf>jbvb.io.InputStrfbm</dodf> objfdt tibt dontbins
     *         tiis <dodf>SfriblBlob</dodf> objfdt's brrby of bytfs
     * @tirows SfriblExdfption if bn frror oddurs;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     * @sff #sftBinbryStrfbm
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm() tirows SfriblExdfption {
        isVblid();
        InputStrfbm strfbm = nfw BytfArrbyInputStrfbm(buf);
        rfturn strfbm;
    }

    /**
     * Rfturns tif position in tiis <dodf>SfriblBlob</dodf> objfdt wifrf
     * tif givfn pbttfrn of bytfs bfgins, stbrting tif sfbrdi bt tif
     * spfdififd position.
     *
     * @pbrbm pbttfrn tif pbttfrn of bytfs for wiidi to sfbrdi
     * @pbrbm stbrt tif position of tif bytf in tiis
     *              <dodf>SfriblBlob</dodf> objfdt from wiidi to bfgin
     *              tif sfbrdi; tif first position is <dodf>1</dodf>;
     *              must not bf lfss tibn <dodf>1</dodf> nor grfbtfr tibn
     *              tif lfngti of tiis <dodf>SfriblBlob</dodf> objfdt
     * @rfturn tif position in tiis <dodf>SfriblBlob</dodf> objfdt
     *         wifrf tif givfn pbttfrn bfgins, stbrting bt tif spfdififd
     *         position; <dodf>-1</dodf> if tif pbttfrn is not found
     *         or tif givfn stbrting position is out of bounds; position
     *         numbfring for tif rfturn vbluf stbrts bt <dodf>1</dodf>
     * @tirows SfriblExdfption if bn frror oddurs wifn sfriblizing tif blob;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     * @tirows SQLExdfption if tifrf is bn frror bddfssing tif <dodf>BLOB</dodf>
     *         vbluf from tif dbtbbbsf
     */
    publid long position(bytf[] pbttfrn, long stbrt)
                tirows SfriblExdfption, SQLExdfption {
        isVblid();
        if (stbrt < 1 || stbrt > lfn) {
            rfturn -1;
        }

        int pos = (int)stbrt-1; // intfrnblly Blobs brf storfd bs brrbys.
        int i = 0;
        long pbtlfn = pbttfrn.lfngti;

        wiilf (pos < lfn) {
            if (pbttfrn[i] == buf[pos]) {
                if (i + 1 == pbtlfn) {
                    rfturn (pos + 1) - (pbtlfn - 1);
                }
                i++; pos++; // indrfmfnt pos, bnd i
            } flsf if (pbttfrn[i] != buf[pos]) {
                pos++; // indrfmfnt pos only
            }
        }
        rfturn -1; // not found
    }

    /**
     * Rfturns tif position in tiis <dodf>SfriblBlob</dodf> objfdt wifrf
     * tif givfn <dodf>Blob</dodf> objfdt bfgins, stbrting tif sfbrdi bt tif
     * spfdififd position.
     *
     * @pbrbm pbttfrn tif <dodf>Blob</dodf> objfdt for wiidi to sfbrdi;
     * @pbrbm stbrt tif position of tif bytf in tiis
     *              <dodf>SfriblBlob</dodf> objfdt from wiidi to bfgin
     *              tif sfbrdi; tif first position is <dodf>1</dodf>;
     *              must not bf lfss tibn <dodf>1</dodf> nor grfbtfr tibn
     *              tif lfngti of tiis <dodf>SfriblBlob</dodf> objfdt
     * @rfturn tif position in tiis <dodf>SfriblBlob</dodf> objfdt
     *         wifrf tif givfn <dodf>Blob</dodf> objfdt bfgins, stbrting
     *         bt tif spfdififd position; <dodf>-1</dodf> if tif pbttfrn is
     *         not found or tif givfn stbrting position is out of bounds;
     *         position numbfring for tif rfturn vbluf stbrts bt <dodf>1</dodf>
     * @tirows SfriblExdfption if bn frror oddurs wifn sfriblizing tif blob;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     * @tirows SQLExdfption if tifrf is bn frror bddfssing tif <dodf>BLOB</dodf>
     *         vbluf from tif dbtbbbsf
     */
    publid long position(Blob pbttfrn, long stbrt)
       tirows SfriblExdfption, SQLExdfption {
        isVblid();
        rfturn position(pbttfrn.gftBytfs(1, (int)(pbttfrn.lfngti())), stbrt);
    }

    /**
     * Writfs tif givfn brrby of bytfs to tif <dodf>BLOB</dodf> vbluf tibt
     * tiis <dodf>Blob</dodf> objfdt rfprfsfnts, stbrting bt position
     * <dodf>pos</dodf>, bnd rfturns tif numbfr of bytfs writtfn.
     *
     * @pbrbm pos tif position in tif SQL <dodf>BLOB</dodf> vbluf bt wiidi
     *     to stbrt writing. Tif first position is <dodf>1</dodf>;
     *     must not bf lfss tibn <dodf>1</dodf> nor grfbtfr tibn
     *     tif lfngti of tiis <dodf>SfriblBlob</dodf> objfdt.
     * @pbrbm bytfs tif brrby of bytfs to bf writtfn to tif <dodf>BLOB</dodf>
     *        vbluf tibt tiis <dodf>Blob</dodf> objfdt rfprfsfnts
     * @rfturn tif numbfr of bytfs writtfn
     * @tirows SfriblExdfption if tifrf is bn frror bddfssing tif
     *     <dodf>BLOB</dodf> vbluf; or if bn invblid position is sft; if bn
     *     invblid offsft vbluf is sft;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     * @tirows SQLExdfption if tifrf is bn frror bddfssing tif <dodf>BLOB</dodf>
     *         vbluf from tif dbtbbbsf
     * @sff #gftBytfs
     */
    publid int sftBytfs(long pos, bytf[] bytfs)
        tirows SfriblExdfption, SQLExdfption {
        rfturn (sftBytfs(pos, bytfs, 0, bytfs.lfngti));
    }

    /**
     * Writfs bll or pbrt of tif givfn <dodf>bytf</dodf> brrby to tif
     * <dodf>BLOB</dodf> vbluf tibt tiis <dodf>Blob</dodf> objfdt rfprfsfnts
     * bnd rfturns tif numbfr of bytfs writtfn.
     * Writing stbrts bt position <dodf>pos</dodf> in tif <dodf>BLOB</dodf>
     * vbluf; <i>lfn</i> bytfs from tif givfn bytf brrby brf writtfn.
     *
     * @pbrbm pos tif position in tif <dodf>BLOB</dodf> objfdt bt wiidi
     *     to stbrt writing. Tif first position is <dodf>1</dodf>;
     *     must not bf lfss tibn <dodf>1</dodf> nor grfbtfr tibn
     *     tif lfngti of tiis <dodf>SfriblBlob</dodf> objfdt.
     * @pbrbm bytfs tif brrby of bytfs to bf writtfn to tif <dodf>BLOB</dodf>
     *     vbluf
     * @pbrbm offsft tif offsft in tif <dodf>bytf</dodf> brrby bt wiidi
     *     to stbrt rfbding tif bytfs. Tif first offsft position is
     *     <dodf>0</dodf>; must not bf lfss tibn <dodf>0</dodf> nor grfbtfr
     *     tibn tif lfngti of tif <dodf>bytf</dodf> brrby
     * @pbrbm lfngti tif numbfr of bytfs to bf writtfn to tif
     *     <dodf>BLOB</dodf> vbluf from tif brrby of bytfs <i>bytfs</i>.
     *
     * @rfturn tif numbfr of bytfs writtfn
     * @tirows SfriblExdfption if tifrf is bn frror bddfssing tif
     *     <dodf>BLOB</dodf> vbluf; if bn invblid position is sft; if bn
     *     invblid offsft vbluf is sft; if numbfr of bytfs to bf writtfn
     *     is grfbtfr tibn tif <dodf>SfriblBlob</dodf> lfngti; or tif dombinfd
     *     vblufs of tif lfngti bnd offsft is grfbtfr tibn tif Blob bufffr;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     * @tirows SQLExdfption if tifrf is bn frror bddfssing tif <dodf>BLOB</dodf>
     *         vbluf from tif dbtbbbsf.
     * @sff #gftBytfs
     */
    publid int sftBytfs(long pos, bytf[] bytfs, int offsft, int lfngti)
        tirows SfriblExdfption, SQLExdfption {

        isVblid();
        if (offsft < 0 || offsft > bytfs.lfngti) {
            tirow nfw SfriblExdfption("Invblid offsft in bytf brrby sft");
        }

        if (pos < 1 || pos > tiis.lfngti()) {
            tirow nfw SfriblExdfption("Invblid position in BLOB objfdt sft");
        }

        if ((long)(lfngti) > origLfn) {
            tirow nfw SfriblExdfption("Bufffr is not suffidifnt to iold tif vbluf");
        }

        if ((lfngti + offsft) > bytfs.lfngti) {
            tirow nfw SfriblExdfption("Invblid OffSft. Cbnnot ibvf dombinfd offsft " +
                "bnd lfngti tibt is grfbtfr tibt tif Blob bufffr");
        }

        int i = 0;
        pos--; // dorrfdt to brrby indfxing
        wiilf ( i < lfngti || (offsft + i +1) < (bytfs.lfngti-offsft) ) {
            tiis.buf[(int)pos + i] = bytfs[offsft + i ];
            i++;
        }
        rfturn i;
    }

    /**
     * Rftrifvfs b strfbm tibt dbn bf usfd to writf to tif <dodf>BLOB</dodf>
     * vbluf tibt tiis <dodf>Blob</dodf> objfdt rfprfsfnts.  Tif strfbm bfgins
     * bt position <dodf>pos</dodf>. Tiis mftiod forwbrds tif
     * <dodf>sftBinbryStrfbm()</dodf> dbll to tif undfrlying <dodf>Blob</dodf> in
     * tif fvfnt tibt tiis <dodf>SfriblBlob</dodf> objfdt is instbntibtfd witi b
     * <dodf>Blob</dodf>. If tiis <dodf>SfriblBlob</dodf> is instbntibtfd witi
     * b <dodf>bytf</dodf> brrby, b <dodf>SfriblExdfption</dodf> is tirown.
     *
     * @pbrbm pos tif position in tif <dodf>BLOB</dodf> vbluf bt wiidi
     *        to stbrt writing
     * @rfturn b <dodf>jbvb.io.OutputStrfbm</dodf> objfdt to wiidi dbtb dbn
     *         bf writtfn
     * @tirows SQLExdfption if tifrf is bn frror bddfssing tif
     *            <dodf>BLOB</dodf> vbluf
     * @tirows SfriblExdfption if tif SfriblBlob in not instbntibtfd witi b
     *     <dodf>Blob</dodf> objfdt tibt supports <dodf>sftBinbryStrfbm()</dodf>;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     * @sff #gftBinbryStrfbm
     */
    publid jbvb.io.OutputStrfbm sftBinbryStrfbm(long pos)
        tirows SfriblExdfption, SQLExdfption {
        isVblid();
        if (tiis.blob != null) {
            rfturn tiis.blob.sftBinbryStrfbm(pos);
        } flsf {
            tirow nfw SfriblExdfption("Unsupportfd opfrbtion. SfriblBlob dbnnot " +
                "rfturn b writbblf binbry strfbm, unlfss instbntibtfd witi b Blob objfdt " +
                "tibt providfs b sftBinbryStrfbm() implfmfntbtion");
        }
    }

    /**
     * Trundbtfs tif <dodf>BLOB</dodf> vbluf tibt tiis <dodf>Blob</dodf>
     * objfdt rfprfsfnts to bf <dodf>lfn</dodf> bytfs in lfngti.
     *
     * @pbrbm lfngti tif lfngti, in bytfs, to wiidi tif <dodf>BLOB</dodf>
     *        vbluf tibt tiis <dodf>Blob</dodf> objfdt rfprfsfnts siould bf
     *        trundbtfd
     * @tirows SfriblExdfption if tifrf is bn frror bddfssing tif Blob vbluf;
     *     or tif lfngti to trundbtf is grfbtfr tibt tif SfriblBlob lfngti;
     * if {@dodf frff} ibd prfviously bffn dbllfd on tiis objfdt
     */
    publid void trundbtf(long lfngti) tirows SfriblExdfption {

        isVblid();
        if (lfngti > lfn) {
           tirow nfw SfriblExdfption
              ("Lfngti morf tibn wibt dbn bf trundbtfd");
        } flsf if((int)lfngti == 0) {
             buf = nfw bytf[0];
             lfn = lfngti;
        } flsf {
             lfn = lfngti;
             buf = tiis.gftBytfs(1, (int)lfn);
        }
    }


    /**
     * Rfturns bn
     * <dodf>InputStrfbm</dodf> objfdt tibt dontbins b pbrtibl
     * {@dodf Blob} vbluf, stbrting witi tif bytf spfdififd by pos, wiidi is
     * lfngti bytfs in lfngti.
     *
     * @pbrbm pos tif offsft to tif first bytf of tif pbrtibl vbluf to bf
     * rftrifvfd. Tif first bytf in tif {@dodf Blob} is bt position 1
     * @pbrbm lfngti tif lfngti in bytfs of tif pbrtibl vbluf to bf rftrifvfd
     * @rfturn
     * <dodf>InputStrfbm</dodf> tirougi wiidi tif pbrtibl {@dodf Blob} vbluf dbn
     * bf rfbd.
     * @tirows SQLExdfption if pos is lfss tibn 1 or if pos is grfbtfr tibn tif
     * numbfr of bytfs in tif {@dodf Blob} or if pos + lfngti is grfbtfr tibn
     * tif numbfr of bytfs in tif {@dodf Blob}
     * @tirows SfriblExdfption if tif {@dodf frff} mftiod ibd bffn prfviously
     * dbllfd on tiis objfdt
     *
     * @sindf 1.6
     */
    publid InputStrfbm gftBinbryStrfbm(long pos, long lfngti) tirows SQLExdfption {
        isVblid();
        if (pos < 1 || pos > tiis.lfngti()) {
            tirow nfw SfriblExdfption("Invblid position in BLOB objfdt sft");
        }
        if (lfngti < 1 || lfngti > lfn - pos + 1) {
            tirow nfw SfriblExdfption("lfngti is < 1 or pos + lfngti >"
                    + "totbl numbfr of bytfs");
        }
        rfturn nfw BytfArrbyInputStrfbm(buf, (int) pos - 1, (int) lfngti);
    }


    /**
     * Tiis mftiod frffs tif {@dodf SfribblfBlob} objfdt bnd rflfbsfs tif
     * rfsourdfs tibt it iolds. Tif objfdt is invblid ondf tif {@dodf frff}
     * mftiod is dbllfd. <p> If {@dodf frff} is dbllfd multiplf timfs, tif
     * subsfqufnt dblls to {@dodf frff} brf trfbtfd bs b no-op. </P>
     *
     * @tirows SQLExdfption if bn frror oddurs rflfbsing tif Blob's rfsourdfs
     * @sindf 1.6
     */
    publid void frff() tirows SQLExdfption {
        if (buf != null) {
            buf = null;
            if (blob != null) {
                blob.frff();
            }
            blob = null;
        }
    }

    /**
     * Compbrfs tiis SfriblBlob to tif spfdififd objfdt.  Tif rfsult is {@dodf
     * truf} if bnd only if tif brgumfnt is not {@dodf null} bnd is b {@dodf
     * SfriblBlob} objfdt tibt rfprfsfnts tif sbmf sfqufndf of bytfs bs tiis
     * objfdt.
     *
     * @pbrbm  obj Tif objfdt to dompbrf tiis {@dodf SfriblBlob} bgbinst
     *
     * @rfturn {@dodf truf} if tif givfn objfdt rfprfsfnts b {@dodf SfriblBlob}
     *          fquivblfnt to tiis SfriblBlob, {@dodf fblsf} otifrwisf
     *
     */
    publid boolfbn fqubls(Objfdt obj) {
        if (tiis == obj) {
            rfturn truf;
        }
        if (obj instbndfof SfriblBlob) {
            SfriblBlob sb = (SfriblBlob)obj;
            if (tiis.lfn == sb.lfn) {
                rfturn Arrbys.fqubls(buf, sb.buf);
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfturns b ibsi dodf for tiis {@dodf SfriblBlob}.
     * @rfturn  b ibsi dodf vbluf for tiis objfdt.
     */
    publid int ibsiCodf() {
       rfturn ((31 + Arrbys.ibsiCodf(buf)) * 31 + (int)lfn) * 31 + (int)origLfn;
    }

    /**
     * Rfturns b dlonf of tiis {@dodf SfriblBlob}. Tif dopy will dontbin b
     * rfffrfndf to b dlonf of tif intfrnbl bytf brrby, not b rfffrfndf
     * to tif originbl intfrnbl bytf brrby of tiis {@dodf SfriblBlob} objfdt.
     * Tif undfrlying {@dodf Blob} objfdt will bf sft to null.
     *
     * @rfturn  b dlonf of tiis SfriblBlob
     */
    publid Objfdt dlonf() {
        try {
            SfriblBlob sb = (SfriblBlob) supfr.dlonf();
            sb.buf =  (buf != null) ? Arrbys.dopyOf(buf, (int)lfn) : null;
            sb.blob = null;
            rfturn sb;
        } dbtdi (ClonfNotSupportfdExdfption fx) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError();
        }

    }

    /**
     * rfbdObjfdt is dbllfd to rfstorf tif stbtf of tif SfriblBlob from
     * b strfbm.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

        ObjfdtInputStrfbm.GftFifld fiflds = s.rfbdFiflds();
       bytf[] tmp = (bytf[])fiflds.gft("buf", null);
       if (tmp == null)
           tirow nfw InvblidObjfdtExdfption("buf is null bnd siould not bf!");
       buf = tmp.dlonf();
       lfn = fiflds.gft("lfn", 0L);
       if (buf.lfngti != lfn)
           tirow nfw InvblidObjfdtExdfption("buf is not tif fxpfdtfd sizf");
       origLfn = fiflds.gft("origLfn", 0L);
       blob = (Blob) fiflds.gft("blob", null);
    }

    /**
     * writfObjfdt is dbllfd to sbvf tif stbtf of tif SfriblBlob
     * to b strfbm.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {

        ObjfdtOutputStrfbm.PutFifld fiflds = s.putFiflds();
        fiflds.put("buf", buf);
        fiflds.put("lfn", lfn);
        fiflds.put("origLfn", origLfn);
        // Notf: tiis difdk to sff if it is bn instbndf of Sfriblizbblf
        // is for bbdkwbrds dompbtibiity
        fiflds.put("blob", blob instbndfof Sfriblizbblf ? blob : null);
        s.writfFiflds();
    }

    /**
     * Cifdk to sff if tiis objfdt ibd prfviously ibd its {@dodf frff} mftiod
     * dbllfd
     *
     * @tirows SfriblExdfption
     */
    privbtf void isVblid() tirows SfriblExdfption {
        if (buf == null) {
            tirow nfw SfriblExdfption("Error: You dbnnot dbll b mftiod on b "
                    + "SfriblBlob instbndf ondf frff() ibs bffn dbllfd.");
        }
    }

    /**
     * Tif idfntififr tibt bssists in tif sfriblizbtion of tiis
     * {@dodf SfriblBlob} objfdt.
     */
    stbtid finbl long sfriblVfrsionUID = -8144641928112860441L;
}
