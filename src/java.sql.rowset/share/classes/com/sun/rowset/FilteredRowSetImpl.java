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

pbdkbgf dom.sun.rowsft;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.mbti.*;

import jbvbx.sql.rowsft.*;
import jbvbx.sql.rowsft.spi.*;
import jbvbx.sql.rowsft.sfribl.*;
import dom.sun.rowsft.providfrs.*;
import dom.sun.rowsft.intfrnbl.*;

/**
 * Tif stbndbrd implfmfntbtion of tif <dodf>FiltfrfdRowSft</dodf> intfrfbdf. Sff tif intfrfbdf
 * dffinition for full bfibvior bnd implfmfntbtion rfquirfmfnts.
 *
 * @sff jbvbx.sql.rowsft.Prfdidbtf
 * @butior Jonbtibn Brudf, Amit Hbndb
 */

publid dlbss FiltfrfdRowSftImpl fxtfnds WfbRowSftImpl implfmfnts Sfriblizbblf, Clonfbblf, FiltfrfdRowSft {

    privbtf Prfdidbtf p;

    privbtf boolfbn onInsfrtRow = fblsf;


    /**
     * Construdt b <dodf>FiltfrfdRowSft</dodf>
     */
    publid FiltfrfdRowSftImpl() tirows SQLExdfption {
        supfr();
    }

    /**
     * Construdt b <dodf>FiltfrfdRowSft</dodf> witi b spfdififd syndironizbtion
     * providfr.
     *
     * @pbrbm fnv b Hbsitbblf dontbining b dfsirfd syndidonizbtbtion providfr
     * nbmf-vbluf pbir.
     */
    @SupprfssWbrnings("rbwtypfs")
    publid FiltfrfdRowSftImpl(Hbsitbblf fnv) tirows SQLExdfption {
        supfr(fnv);
    }

    /**
     * Apply tif prfdidbtf for tiis filtfr
     *
     * @pbrbm p bn implfmfntbtion of tif prfdidbtf intfrfbdf
     */
    publid void sftFiltfr(Prfdidbtf p) tirows SQLExdfption {
        tiis.p = p;
    }

    /**
     * Rftrifvf tif filtfr bdtivf for tiis <dodf>FiltfrfdRowSft</dodf>
     *
     * @rfturn b <dodf>Prfdidbtf</dodf> objfdt instbndf
     */
    publid Prfdidbtf gftFiltfr() {
        rfturn tiis.p;
    }

    /**
     * Ovfr-riding <dodf>intfrnblNfxt()</dodf> implfmfntbtion. Tiis mftiod
     * bpplifs tif filtfr on tif <dodf>RowSft</dodf> fbdi timf tif dursor is bdvbndfd or
     * mbnipulbtfd. It movfs tif dursor to tif nfxt row bddording to tif sft
     * prfdidbtf bnd rfturns <dodf>truf</dodf> if tif dursor is still witiin tif rowsft or
     * <dodf>fblsf</dodf> if tif dursor position is ovfr tif lbst row
     *
     * @rfturn truf if ovfr tif vblid row in tif rowsft; fblsf if ovfr tif lbst
     * row
     */
    protfdtfd boolfbn intfrnblNfxt() tirows SQLExdfption {
        // CbdifdRowSftImpl.nfxt() intfrnblly dblls
        // tiis(drs).intfrnblNfxt() NOTE: tiis iolds drs objfdt
        // So wifn frs.nfxt() is dbllfd,
        // intfrnblly tiis(frs).intfrnblNfxt() will bf dbllfd
        // wiidi will bf notiing but tiis mftiod.
        // bfdbusf tiis iolds frs objfdt

        // kffp on doing supfr.intfrnblNfxt()
        // rbtifr tibn doing it ondf.


         // p.fvblubtf will iflp us in dibnging tif dursor
         // bnd difdking tif nfxt vbluf by rfturning truf or fblsf.
         // to fit tif filtfr

         // So wiilf() loop will ibvf b "rbndom dombinbtion" of
         // truf bnd fblsf rfturnfd dfpfnding upon tif rfdords
         // brf in or out of filtfr.
         // Wf nffd to trbvfrsf from prfsfnt dursorPos till fnd,
         // wiftifr truf or fblsf bnd difdk fbdi row for "filtfr"
         // "till wf gft b "truf"


         boolfbn bool = fblsf;

         for(int rows=tiis.gftRow(); rows<=tiis.sizf();rows++) {
             bool = supfr.intfrnblNfxt();

             if( !bool || p == null) {
               rfturn bool;
             }
             if(p.fvblubtf(tiis)){
                   brfbk;
             }

         }

       rfturn bool;
    }


    /**
     * Ovfr-riding <dodf>intfrnblPrfvious()</dodf> implfmfntbtion. Tiis mftiod
     * bpplifs tif filtfr on tif <dodf>RowSft</dodf> fbdi timf tif dursor is movfd bbdkwbrd or
     * mbnipulbtfd. It movfs tif dursor to tif prfvious row bddording to tif sft
     * prfdidbtf bnd rfturns <dodf>truf</dodf> if tif dursor is still witiin tif rowsft or
     * <dodf>fblsf</dodf> if tif dursor position is ovfr tif lbst row
     *
     * @rfturn truf if ovfr tif vblid row in tif rowsft; fblsf if ovfr tif lbst
     * row
     */
    protfdtfd boolfbn intfrnblPrfvious() tirows SQLExdfption {
         boolfbn bool = fblsf;
         // witi prfvious movf bbdkwbrds,
         // i.f. from bny rfdord towbrds first rfdord

         for(int rows=tiis.gftRow(); rows>0;rows--) {

             bool = supfr.intfrnblPrfvious();

             if( p == null) {
               rfturn bool;
             }

             if(p.fvblubtf(tiis)){
                   brfbk;
             }

         }

       rfturn bool;
    }


    /**
     * Ovfr-riding <dodf>intfrnblFirst()</dodf> implfmfntbtion. Tiis mftiod
     * bpplifs tif filtfr on tif <dodf>RowSft</dodf> fbdi timf tif dursor is movfd to first
     * row. It movfs tif dursor to tif first row bddording to tif sft
     * prfdidbtf bnd rfturns <dodf>truf</dodf> if tif dursor is still witiin tif rowsft or
     * <dodf>fblsf</dodf> if tif dursor position is ovfr tif lbst row
     *
     * @rfturn truf if ovfr tif vblid row in tif rowsft; fblsf if ovfr tif lbst
     * row
     */
    protfdtfd boolfbn intfrnblFirst() tirows SQLExdfption {

        // from first till prfsfnt dursor position(go forwbrd),
        // find tif bdtubl first wiidi mbtdifs tif filtfr.

         boolfbn bool = supfr.intfrnblFirst();

         if( p == null) {
               rfturn bool;
             }

         wiilf(bool) {

             if(p.fvblubtf(tiis)){
                   brfbk;
             }
        bool = supfr.intfrnblNfxt();
        }
     rfturn bool;
    }


    /**
     * Ovfr-riding <dodf>intfrnblLbst()</dodf> implfmfntbtion. Tiis mftiod
     * bpplifs tif filtfr on tif <dodf>RowSft</dodf> fbdi timf tif dursor is movfd to
     * lbst row. It movfs tif dursor to tif lbst row bddording to tif sft
     * prfdidbtf bnd rfturns <dodf>truf</dodf> if tif dursor is still witiin tif rowsft or
     * <dodf>fblsf</dodf> if tif dursor position is ovfr tif lbst row
     *
     * @rfturn truf if ovfr tif vblid row in tif rowsft; fblsf if ovfr tif lbst
     * row
     */
    protfdtfd boolfbn intfrnblLbst() tirows SQLExdfption {
        // from lbst to tif prfsfnt dursor position(go bbdkwbrd),
        // find tif bdtubl lbst wiidi mbtdifs tif filtfr.

         boolfbn bool = supfr.intfrnblLbst();

         if( p == null) {
               rfturn bool;
             }

         wiilf(bool) {

             if(p.fvblubtf(tiis)){
                   brfbk;
             }

        bool = supfr.intfrnblPrfvious();

        }
     rfturn bool;

   } // fnd intfrnblLbst()
   /**
     * Movfs tif dursor tif spfdififd numbfr of rows from tif durrfnt
     * position, witi b positivf numbfr moving it forwbrd bnd b
     * nfgbtivf numbfr moving it bbdkwbrd.
     * <P>
     * If tif numbfr is positivf, tif dursor movfs tif spfdififd numbfr of
     * rows towbrd tif fnd of tif rowsft, stbrting bt tif durrfnt row.
     * For fxbmplf, tif following dommbnd, in wiidi
     * <dodf>drs</dodf> is b <dodf>CbdifdRowSftImpl</dodf> objfdt witi 100 rows,
     * movfs tif dursor forwbrd four rows from tif durrfnt row.  If tif
     * durrfnt row is 50, tif dursor would movf to row 54.
     * <PRE><dodf>
     *
     *    drs.rflbtivf(4);
     *
     * </dodf> </PRE>
     * <P>
     * If tif numbfr is nfgbtivf, tif dursor movfs bbdk towbrd tif bfginning
     * tif spfdififd numbfr of rows, stbrting bt tif durrfnt row.
     * For fxbmplf, dblling tif mftiod
     * <dodf>bbsolutf(-1)</dodf> positions tif dursor on tif lbst row,
     * <dodf>bbsolutf(-2)</dodf> movfs it on tif nfxt-to-lbst row, bnd so on.
     * If tif <dodf>CbdifdRowSftImpl</dodf> objfdt <dodf>drs</dodf> ibs fivf rows,
     * tif following dommbnd movfs tif dursor to tif fourti-to-lbst row, wiidi
     * in tif dbsf of b  rowsft witi fivf rows, is blso tif sfdond row
     * from tif bfginning.
     * <PRE><dodf>
     *
     *    drs.bbsolutf(-4);
     *
     * </dodf> </PRE>
     *
     * If tif numbfr spfdififd is lbrgfr tibn tif numbfr of rows, tif dursor
     * will movf to tif position bftfr tif lbst row. If tif numbfr spfdififd
     * would movf tif dursor onf or morf rows bfforf tif first row, tif dursor
     * movfs to tif position bfforf tif first row. In boti dbsfs, tiis mftiod
     * tirows bn <dodf>SQLExdfption</dodf>.
     * <P>
     * Notf: Cblling <dodf>bbsolutf(1)</dodf> is tif sbmf bs dblling tif
     * mftiod <dodf>first()</dodf>.  Cblling <dodf>bbsolutf(-1)</dodf> is tif
     * sbmf bs dblling <dodf>lbst()</dodf>.  Cblling <dodf>rflbtivf(0)</dodf>
     * is vblid, but it dofs not dibngf tif dursor position.
     *
     * @pbrbm rows bn <dodf>int</dodf> indidbting tif numbfr of rows to movf
     *             tif dursor, stbrting bt tif durrfnt row; b positivf numbfr
     *             movfs tif dursor forwbrd; b nfgbtivf numbfr movfs tif dursor
     *             bbdkwbrd; must not movf tif dursor pbst tif vblid
     *             rows
     * @rfturn <dodf>truf</dodf> if tif dursor is on b row in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tif rowsft is typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
   publid boolfbn rflbtivf(int rows) tirows SQLExdfption {

      boolfbn rftvbl;
      boolfbn bool = fblsf;
      boolfbn boolvbl = fblsf;

      if(gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
         tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.rflbtivf").toString());
      }

      if( rows > 0 ) {

         int i = 0;
         wiilf( i < (rows)) {

            if( isAftfrLbst() ) {
               rfturn fblsf;
            }
            bool = intfrnblNfxt();
            i++;
         }

         rftvbl = bool;
      } flsf {
         int j = rows;
         wiilf( (j) < 0 ) {

           if( isBfforfFirst() ) {
              rfturn fblsf;
           }
           boolvbl = intfrnblPrfvious();
           j++;
         }
         rftvbl = boolvbl;
      }
      if(rows != 0)
          notifyCursorMovfd();
      rfturn rftvbl;
   }

   /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif row numbfr
     * spfdififd.
     *
     * <p>If tif numbfr is positivf, tif dursor movfs to bn bbsolutf row witi
     * rfspfdt to tif bfginning of tif rowsft.  Tif first row is row 1, tif sfdond
     * is row 2, bnd so on.  For fxbmplf, tif following dommbnd, in wiidi
     * <dodf>drs</dodf> is b <dodf>CbdifdRowSftImpl</dodf> objfdt, movfs tif dursor
     * to tif fourti row, stbrting from tif bfginning of tif rowsft.
     * <PRE><dodf>
     *
     *    drs.bbsolutf(4);
     *
     * </dodf> </PRE>
     * <P>
     * If tif numbfr is nfgbtivf, tif dursor movfs to bn bbsolutf row position
     * witi rfspfdt to tif fnd of tif rowsft.  For fxbmplf, dblling
     * <dodf>bbsolutf(-1)</dodf> positions tif dursor on tif lbst row,
     * <dodf>bbsolutf(-2)</dodf> movfs it on tif nfxt-to-lbst row, bnd so on.
     * If tif <dodf>CbdifdRowSftImpl</dodf> objfdt <dodf>drs</dodf> ibs fivf rows,
     * tif following dommbnd movfs tif dursor to tif fourti-to-lbst row, wiidi
     * in tif dbsf of b  rowsft witi fivf rows, is blso tif sfdond row, dounting
     * from tif bfginning.
     * <PRE><dodf>
     *
     *    drs.bbsolutf(-4);
     *
     * </dodf> </PRE>
     *
     * If tif numbfr spfdififd is lbrgfr tibn tif numbfr of rows, tif dursor
     * will movf to tif position bftfr tif lbst row. If tif numbfr spfdififd
     * would movf tif dursor onf or morf rows bfforf tif first row, tif dursor
     * movfs to tif position bfforf tif first row.
     * <P>
     * Notf: Cblling <dodf>bbsolutf(1)</dodf> is tif sbmf bs dblling tif
     * mftiod <dodf>first()</dodf>.  Cblling <dodf>bbsolutf(-1)</dodf> is tif
     * sbmf bs dblling <dodf>lbst()</dodf>.
     *
     * @pbrbm rows b positivf numbfr to indidbtf tif row, stbrting row numbfring from
     *        tif first row, wiidi is <dodf>1</dodf>; b nfgbtivf numbfr to indidbtf
     *        tif row, stbrting row numbfring from tif lbst row, wiidi is
     *        <dodf>-1</dodf>; it must not bf <dodf>0</dodf>
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif rowsft; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tif givfn dursor position is <dodf>0</dodf> or tif
     *            typf of tiis rowsft is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn bbsolutf(int rows) tirows SQLExdfption {

      boolfbn rftvbl;
      boolfbn bool = fblsf;

      if(rows == 0 || gftTypf() == RfsultSft.TYPE_FORWARD_ONLY) {
         tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.bbsolutf").toString());
      }

      if (rows > 0) {
         bool = intfrnblFirst();

         int i = 0;
         wiilf(i < (rows-1)) {
            if( isAftfrLbst() ) {
               rfturn fblsf;
            }
            bool = intfrnblNfxt();
            i++;
         }
         rftvbl = bool;
      } flsf {
         bool = intfrnblLbst();

         int j = rows;
         wiilf((j+1) < 0 ) {
            if( isBfforfFirst() ) {
               rfturn fblsf;
            }
            bool = intfrnblPrfvious();
            j++;
         }
         rftvbl = bool;
      }
      notifyCursorMovfd();
      rfturn rftvbl;
   }

   /**
     * Movfs tif dursor for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * to tif insfrt row.  Tif durrfnt row in tif rowsft is rfmfmbfrfd
     * wiilf tif dursor is on tif insfrt row.
     * <P>
     * Tif insfrt row is b spfdibl row bssodibtfd witi bn updbtbblf
     * rowsft.  It is fssfntiblly b bufffr wifrf b nfw row mby
     * bf donstrudtfd by dblling tif bppropribtf <dodf>updbtfXXX</dodf>
     * mftiods to bssign b vbluf to fbdi dolumn in tif row.  A domplftf
     * row must bf donstrudtfd; tibt is, fvfry dolumn tibt is not nullbblf
     * must bf bssignfd b vbluf.  In ordfr for tif nfw row to bfdomf pbrt
     * of tiis rowsft, tif mftiod <dodf>insfrtRow</dodf> must bf dbllfd
     * bfforf tif dursor is movfd bbdk to tif rowsft.
     * <P>
     * Only dfrtbin mftiods mby bf invokfd wiilf tif dursor is on tif insfrt
     * row; mbny mftiods tirow bn fxdfption if tify brf dbllfd wiilf tif
     * dursor is tifrf.  In bddition to tif <dodf>updbtfXXX</dodf>
     * bnd <dodf>insfrtRow</dodf> mftiods, only tif <dodf>gftXXX</dodf> mftiods
     * mby bf dbllfd wifn tif dursor is on tif insfrt row.  A <dodf>gftXXX</dodf>
     * mftiod siould bf dbllfd on b dolumn only bftfr bn <dodf>updbtfXXX</dodf>
     * mftiod ibs bffn dbllfd on tibt dolumn; otifrwisf, tif vbluf rfturnfd is
     * undftfrminfd.
     *
     * @tirows SQLExdfption if tiis <dodf>CbdifdRowSftImpl</dodf> objfdt is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void movfToInsfrtRow() tirows SQLExdfption {

      onInsfrtRow = truf;
      supfr.movfToInsfrtRow();
   }

   /**
     * Tiis is fxplbnbtion for tif ovfrriding of tif updbtfXXX fundtions.
     * Tifsf fundtions ibvf bffn ovfrridfn to fnsurf tibt only dorrfdt
     * vblufs tibt pbss tif dritfrib for tif filtfr brf bdtbully insfrtfd.
     * Tif fvblubtion of wiftifr b pbrtidulbr vbluf pbssfs tif dritfrib
     * of tif filtfr is donf using tif fvblubtf fundtion in tif Prfdidbtf
     * intfrfbdf.
     *
     * Tif difdking dbn will donf in tif fvblubtf fundtion wiidi is implfmfntfd
     * in tif dlbss tibt implfmfnts tif Prfdidbtf intfrfbdf. So tif difdking
     * dbn vbry from onf implfmfntbtion to bnotifr.
     *
     * Somf bdditionbl points ifrf on tif following:
     * 1. updbtfBytfs()     - sindf tif fvblubtf fundtion tbkfs Objfdt bs pbrbmftfr
     *                        b String is donstrudtfd from tif bytf brrby bnd would
     *                        pbssfd to tif fvblubtf fundtion.
     * 2. updbtfXXXstrfbm() - ifrf it would suffidf to pbss tif strfbm ibndlf
     *                        to tif fvblubtf fundtion bnd tif implfmfntbtion
     *                        of tif fvblubtf fundtion dbn do tif dompbrision
     *                        bbsfd on tif strfbm bnd blso typf of dbtb.
     */


   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>int</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfInt(int dolumnIndfx , int x) tirows SQLExdfption {

     boolfbn bool;

     if(onInsfrtRow) {
        if(p != null) {
           bool = p.fvblubtf(Intfgfr.vblufOf(x),dolumnIndfx);

           if(!bool) {
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
           }
        }
     }

     supfr.updbtfInt(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>int</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfInt(String dolumnNbmf , int x) tirows SQLExdfption {

       tiis.updbtfInt(findColumn(dolumnNbmf), x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>boolfbn</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBoolfbn(int dolumnIndfx, boolfbn x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(Boolfbn.vblufOf(x) , dolumnIndfx);

            if(!bool) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
         }
      }

      supfr.updbtfBoolfbn(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>boolfbn</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBoolfbn(String dolumnNbmf , boolfbn x) tirows SQLExdfption {

      tiis.updbtfBoolfbn(findColumn(dolumnNbmf),x);
   }



    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBytf(int dolumnIndfx , bytf x) tirows SQLExdfption {
      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(Bytf.vblufOf(x),dolumnIndfx);

            if(!bool) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
          }
      }

      supfr.updbtfBytf(dolumnIndfx,x);
   }


   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBytf(String dolumnNbmf , bytf x) tirows SQLExdfption {

      tiis.updbtfBytf(findColumn(dolumnNbmf),x);
   }


   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>siort</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfSiort( int dolumnIndfx , siort x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(Siort.vblufOf(x), dolumnIndfx);

            if(!bool) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
          }
      }

      supfr.updbtfSiort(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>siort</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfSiort( String dolumnNbmf , siort x) tirows SQLExdfption {

      tiis.updbtfSiort(findColumn(dolumnNbmf),x);
   }


   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>long</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfLong(int dolumnIndfx , long x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(Long.vblufOf(x), dolumnIndfx);

            if(!bool) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
          }
      }

      supfr.updbtfLong(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>long</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfLong( String dolumnNbmf , long x) tirows SQLExdfption {

      tiis.updbtfLong(findColumn(dolumnNbmf) , x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>flobt</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfFlobt(int dolumnIndfx , flobt x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(Flobt.vblufOf(x), dolumnIndfx);

            if(!bool) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
          }
      }

      supfr.updbtfFlobt(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>flobt</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfFlobt(String dolumnNbmf , flobt x) tirows SQLExdfption {

      tiis.updbtfFlobt(findColumn(dolumnNbmf),x);
   }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>doublf</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfDoublf(int dolumnIndfx , doublf x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(Doublf.vblufOf(x) , dolumnIndfx);

            if(!bool) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
          }
      }

      supfr.updbtfDoublf(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>doublf</dodf> vbluf.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfDoublf(String dolumnNbmf , doublf x) tirows SQLExdfption {

      tiis.updbtfDoublf(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBigDfdimbl(int dolumnIndfx , BigDfdimbl x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
            bool = p.fvblubtf(x,dolumnIndfx);

            if(!bool) {
               tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
            }
          }
      }

      supfr.updbtfBigDfdimbl(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBigDfdimbl(String dolumnNbmf , BigDfdimbl x) tirows SQLExdfption {

      tiis.updbtfBigDfdimbl(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>String</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to mbrk tif row bs updbtfd.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd to insfrt tif nfw row into tiis rowsft bnd mbrk it
     * bs insfrtfd. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     * <P>
     * Tif mftiod <dodf>bddfptCibngfs</dodf> must bf dbllfd if tif
     * updbtfd vblufs brf to bf writtfn bbdk to tif undfrlying dbtbbbsf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfString(int dolumnIndfx , String x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
           bool = p.fvblubtf(x,dolumnIndfx);

           if(!bool) {
              tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
           }
         }
      }

      supfr.updbtfString(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>String</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfString(String dolumnNbmf , String x) tirows SQLExdfption {

      tiis.updbtfString(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> brrby.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBytfs(int dolumnIndfx , bytf []x) tirows SQLExdfption {

      boolfbn bool;
      String vbl = "";

      Bytf [] obj_brr = nfw Bytf[x.lfngti];

      for(int i = 0; i < x.lfngti; i++) {
         obj_brr[i] = Bytf.vblufOf(x[i]);
         vbl = vbl.dondbt(obj_brr[i].toString());
     }


      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(vbl,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfBytfs(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>bytf</dodf> brrby.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBytfs(String dolumnNbmf , bytf []x) tirows SQLExdfption {

      tiis.updbtfBytfs(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Dbtf</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif typf of tif dfsignbtfd dolumn is not
     *            bn SQL <dodf>DATE</dodf> or <dodf>TIMESTAMP</dodf>, or
     *            (4) tiis rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfDbtf(int dolumnIndfx , jbvb.sql.Dbtf x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfDbtf(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Dbtf</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif typf
     *            of tif dfsignbtfd dolumn is not bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf>, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfDbtf(String dolumnNbmf , jbvb.sql.Dbtf x) tirows SQLExdfption {

      tiis.updbtfDbtf(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timf</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif typf of tif dfsignbtfd dolumn is not
     *            bn SQL <dodf>TIME</dodf> or <dodf>TIMESTAMP</dodf>, or
     *            (4) tiis rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfTimf(int dolumnIndfx , Timf x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x, dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfTimf(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timf</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif typf
     *            of tif dfsignbtfd dolumn is not bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf>, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfTimf(String dolumnNbmf , Timf x) tirows SQLExdfption {

      tiis.updbtfTimf(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timfstbmp</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif typf of tif dfsignbtfd dolumn is not
     *            bn SQL <dodf>DATE</dodf>, <dodf>TIME</dodf>, or
     *            <dodf>TIMESTAMP</dodf>, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfTimfstbmp(int dolumnIndfx , Timfstbmp x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfTimfstbmp(dolumnIndfx,x);
   }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Timfstbmp</dodf> objfdt.
     *
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif typf
     *            of tif dfsignbtfd dolumn is not bn SQL <dodf>DATE</dodf>,
     *            <dodf>TIME</dodf>, or <dodf>TIMESTAMP</dodf>, or (4) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfTimfstbmp(String dolumnNbmf , Timfstbmp x) tirows SQLExdfption {

      tiis.updbtfTimfstbmp(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * ASCII strfbm vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif numbfr of onf-bytf ASCII dibrbdtfrs in tif strfbm
     * @tirows SQLExdfption if tiis mftiod is invokfd
     */
   publid void updbtfAsdiiStrfbm(int dolumnIndfx , jbvb.io.InputStrfbm x ,int lfngti) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfAsdiiStrfbm(dolumnIndfx,x,lfngti);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * ASCII strfbm vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm lfngti tif numbfr of onf-bytf ASCII dibrbdtfrs in tif strfbm
     */
   publid void updbtfAsdiiStrfbm(String dolumnNbmf , jbvb.io.InputStrfbm x , int lfngti) tirows SQLExdfption {

      tiis.updbtfAsdiiStrfbm(findColumn(dolumnNbmf),x,lfngti);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.Rfbdfr</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>,
     *          <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>, <dodf>VARCHAR</dodf>,
     *          or <dodf>LONGVARCHAR</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in dibrbdtfrs
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif dbtb in tif strfbm is not b binbry or
     *            dibrbdtfr typf, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfCibrbdtfrStrfbm(int dolumnIndfx , jbvb.io.Rfbdfr x , int lfngti) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfCibrbdtfrStrfbm(dolumnIndfx,x,lfngti);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm rfbdfr tif nfw dolumn vbluf; must bf b
     * <dodf>jbvb.io.Rfbdfr</dodf> dontbining <dodf>BINARY</dodf>,
     * <dodf>VARBINARY</dodf>, <dodf>LONGVARBINARY</dodf>, <dodf>CHAR</dodf>,
     * <dodf>VARCHAR</dodf>, or <dodf>LONGVARCHAR</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in dibrbdtfrs
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif dbtb
     *            in tif strfbm is not b binbry or dibrbdtfr typf, or (4) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfCibrbdtfrStrfbm(String dolumnNbmf , jbvb.io.Rfbdfr rfbdfr, int lfngti) tirows SQLExdfption {
      tiis.updbtfCibrbdtfrStrfbm(findColumn(dolumnNbmf), rfbdfr, lfngti);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.InputStrfbm</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>, or
     *          <dodf>LONGVARBINARY</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in bytfs
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, (3) tif dbtb in tif strfbm is not binbry, or
     *            (4) tiis rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBinbryStrfbm(int dolumnIndfx , jbvb.io.InputStrfbm x , int lfngti) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfBinbryStrfbm(dolumnIndfx,x,lfngti);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.io.InputStrfbm</dodf> objfdt.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf; must bf b <dodf>jbvb.io.InputStrfbm</dodf>
     *          dontbining <dodf>BINARY</dodf>, <dodf>VARBINARY</dodf>, or
     *          <dodf>LONGVARBINARY</dodf> dbtb
     * @pbrbm lfngti tif lfngti of tif strfbm in bytfs
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, (3) tif dbtb
     *            in tif strfbm is not binbry, or (4) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfBinbryStrfbm(String dolumnNbmf , jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {

      tiis.updbtfBinbryStrfbm(findColumn(dolumnNbmf),x,lfngti);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfObjfdt(int dolumnIndfx , Objfdt x) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfObjfdt(dolumnIndfx,x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfObjfdt(String dolumnNbmf , Objfdt x) tirows SQLExdfption {

      tiis.updbtfObjfdt(findColumn(dolumnNbmf),x);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.  Tif <dodf>sdblf</dodf> pbrbmftfr indidbtfs
     * tif numbfr of digits to tif rigit of tif dfdimbl point bnd is ignorfd
     * if tif nfw dolumn vbluf is not b typf tibt will bf mbppfd to bn SQL
     * <dodf>DECIMAL</dodf> or <dodf>NUMERIC</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point (for
     *              <dodf>DECIMAL</dodf> bnd <dodf>NUMERIC</dodf> typfs only)
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfObjfdt(int dolumnIndfx , Objfdt x , int sdblf) tirows SQLExdfption {

      boolfbn bool;

      if(onInsfrtRow) {
         if(p != null) {
             bool = p.fvblubtf(x,dolumnIndfx);

             if(!bool) {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("filtfrfdrowsftimpl.notbllowfd").toString());
             }
         }
      }

      supfr.updbtfObjfdt(dolumnIndfx,x,sdblf);
   }

   /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>Objfdt</dodf> vbluf.  Tif <dodf>sdblf</dodf> pbrbmftfr
     * indidbtfs tif numbfr of digits to tif rigit of tif dfdimbl point
     * bnd is ignorfd if tif nfw dolumn vbluf is not b typf tibt will bf
     *  mbppfd to bn SQL <dodf>DECIMAL</dodf> or <dodf>NUMERIC</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in fitifr tif durrfnt row or
     * tif insfrt row of tiis rowsft, but it dofs not updbtf tif
     * dbtbbbsf.  If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf. Boti of tifsf mftiods must bf dbllfd bfforf tif
     * dursor movfs to bnotifr row.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm x tif nfw dolumn vbluf
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point (for
     *              <dodf>DECIMAL</dodf> bnd <dodf>NUMERIC</dodf> typfs only)
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void updbtfObjfdt(String dolumnNbmf , Objfdt x, int sdblf) tirows SQLExdfption {

      tiis.updbtfObjfdt(findColumn(dolumnNbmf),x,sdblf);
   }

   /**
     * Insfrts tif dontfnts of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's insfrt
     * row into tiis rowsft immfdibtfly following tif durrfnt row.
     * If tif durrfnt row is tif
     * position bftfr tif lbst row or bfforf tif first row, tif nfw row will
     * bf insfrtfd bt tif fnd of tif rowsft.  Tiis mftiod blso notififs
     * listfnfrs rfgistfrfd witi tiis rowsft tibt tif row ibs dibngfd.
     * <P>
     * Tif dursor must bf on tif insfrt row wifn tiis mftiod is dbllfd.
     *
     * @tirows SQLExdfption if (1) tif dursor is not on tif insfrt row,
     *            (2) onf or morf of tif non-nullbblf dolumns in tif insfrt
     *            row ibs not bffn givfn b vbluf, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
   publid void insfrtRow() tirows SQLExdfption {

      onInsfrtRow = fblsf;
      supfr.insfrtRow();
   }

   /**
    * Tiis mftiod rf populbtfs tif rfsBundlf
    * during tif dfsfriblizbtion prodfss
    *
    */
   privbtf void rfbdObjfdt(ObjfdtInputStrfbm ois) tirows IOExdfption, ClbssNotFoundExdfption {
       // Dffbult stbtf initiblizbtion ibppfns ifrf
       ois.dffbultRfbdObjfdt();
       // Initiblizbtion of trbnsifnt Rfs Bundlf ibppfns ifrf .
       try {
          rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
       } dbtdi(IOExdfption iof) {
           tirow nfw RuntimfExdfption(iof);
       }

   }

   stbtid finbl long sfriblVfrsionUID = 6178454588413509360L;
} // fnd FiltfrfdRowSftImpl dlbss
