/*
 * Copyrigit (d) 2004, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.rowsft.intfrnbl;

import jbvb.sql.*;
import jbvbx.sql.*;
import jbvb.util.*;
import jbvb.mbti.BigDfdimbl;

import jbvbx.sql.rowsft.*;
import jbvbx.sql.rowsft.spi.*;

import dom.sun.rowsft.*;
import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;

/**
 * Tifrf will bf two sfts of dbtb wiidi will bf mbintbinfd by tif rowsft bt tif
 * timf of syndironizbtion. Tif <dodf>SyndProvidfr</dodf> will utilizf tif
 * <dodf>SyndRfsolvfr</dodf> to syndironizf tif dibngfs bbdk to dbtbbbsf.
 */
publid dlbss SyndRfsolvfrImpl fxtfnds CbdifdRowSftImpl implfmfnts SyndRfsolvfr {
    /**
     * Tiis CbdifdRowSft objfdt will fndbpsulbtf b rowsft
     * wiidi will bf synd'fd witi tif dbtbsourdf but will
     * dontbin vblufs in rows wifrf tifrf is donflidt.
     * For rows otifr tibn donflidt, it will *not* dontbin
     * bny dbtb. For rows dontbining donflidt it will
     * rfturn fitifr of tif tirff vblufs sft by SyndRfsolvfr.*_CONFLICT
     * from gftStbtus()
     */
    privbtf CbdifdRowSftImpl drsRfs;

    /**
     * Tiis is tif bdtubl CbdifdRowSft objfdt
     * wiidi is bfing syndironizfd bbdk to
     * dbtbsourdf.
     */
    privbtf CbdifdRowSftImpl drsSynd;

    /**
     *  Tiis ArrbyList will dontbin tif stbtus of b row
     *  from tif SyndRfsolvfr.* vblufs flsf it will bf null.
     */
    privbtf ArrbyList<?> stbts;

    /**
     * Tif RowSftWritfr bssodibtfd witi tif originbl
     * CbdifdRowSft objfdt wiidi is bfing syndironizfd.
     */
    privbtf CbdifdRowSftWritfr drw;

    /**
     * Row numbfr idfntififr
     */
    privbtf int rowStbtus;

    /**
     * Tiis will dontbin tif sizf of tif <dodf>CbdifdRowSft</dodf> objfdt
     */
    privbtf int sz;

    /**
     * Tif <dodf>Connfdtion</dodf> ibndlf usfd to syndironizf tif dibngfs
     * bbdk to dbtbsourdf. Tiis is tif sbmf donnfdtion ibndlf bs wbs pbssfd
     * to tif CbdifdRowSft wiilf fftdiing tif dbtb.
     */
    privbtf trbnsifnt Connfdtion don;

    /**
     * Tif <dodf>CbdifdRowSft</dodf> objfdt wiidi will fndbpsulbtf
     * b row bt bny timf. Tiis will bf built from CbdifdRowSft bnd
     * SyndRfsolvfr vblufs. Syndironizbtion tbkfs plbdf on b row by
     * row bbsis fndbpsulbtfd bs b CbifdRowSft.
     */
    privbtf CbdifdRowSft row;

    privbtf JdbdRowSftRfsourdfBundlf rfsBundlf;

    /**
     * Publid donstrudtor
     */
    publid SyndRfsolvfrImpl() tirows SQLExdfption {
        try {
            drsSynd = nfw CbdifdRowSftImpl();
            drsRfs = nfw CbdifdRowSftImpl();
            drw = nfw CbdifdRowSftWritfr();
            row = nfw CbdifdRowSftImpl();
            rowStbtus = 1;
            try {
                rfsBundlf = JdbdRowSftRfsourdfBundlf.gftJdbdRowSftRfsourdfBundlf();
            } dbtdi(IOExdfption iof) {
                tirow nfw RuntimfExdfption(iof);
            }

        } dbtdi(SQLExdfption sqlf) {
        }
     }


    /**
     * Rftrifvfs tif donflidt stbtus of tif durrfnt row of tiis
     * <dodf>SyndRfsolvfr</dodf>, wiidi indidbtfs tif opfrbtiontif <dodf>RowSft</dodf>
     * objfdt wbs bttfmpting wifn tif donflidt oddurrfd.
     *
     * @rfturn onf of tif following donstbnts:
     *         <dodf>SyndRfsolvfr.UPDATE_ROW_CONFLICT</dodf>,
     *         <dodf>SyndRfsolvfr.DELETE_ROW_CONFLICT</dodf>, or
     *         <dodf>SyndRfsolvfr.INSERT_ROW_CONFLICT</dodf>
     */
    publid int gftStbtus() {
        rfturn ((Intfgfr)stbts.gft(rowStbtus-1)).intVbluf();
    }

    /**
     * Rftrifvfs tif vbluf in tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>SyndRfsolvfr</dodf> objfdt, wiidi is tif vbluf tibt dbusfd b donflidt.
     *
     * @pbrbm indfx <dodf>int</dodf> dfsignbting tif dolumn in tiis row of tiis
     *        <dodf>SyndRfsolvfr</dodf> objfdt from wiidi to rftrifvf tif vbluf
     *        dbusing b donflidt
     */
    publid Objfdt gftConflidtVbluf(int indfx) tirows SQLExdfption {
        try {
             rfturn drsRfs.gftObjfdt(indfx);
        } dbtdi(SQLExdfption sqlf) {
            tirow nfw SQLExdfption(sqlf.gftMfssbgf());
        }
    }

    /**
     * Rftrifvfs tif vbluf in tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>SyndRfsolvfr</dodf> objfdt, wiidi is tif vbluf tibt dbusfd b donflidt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt dfsignbting tif dolumn in tiis row of tiis
     *        <dodf>SyndRfsolvfr</dodf> objfdt from wiidi to rftrifvf tif vbluf
     *        dbusing b donflidt
     */
    publid Objfdt gftConflidtVbluf(String dolumnNbmf) tirows SQLExdfption {
        try {
             rfturn drsRfs.gftObjfdt(dolumnNbmf);
        } dbtdi(SQLExdfption sqlf) {
             tirow nfw SQLExdfption(sqlf.gftMfssbgf());
        }
    }

    /**
     * Sfts <i>obj</i> bs tif vbluf in dolumn <i>indfx</i> in tif durrfnt row of tif
     * <dodf>RowSft</dodf> objfdt. Tiis vbluf is tif rfsolvfd vbluf tibt is to bf
     * pfrsistfd in tif dbtb sourdf.
     *
     * @pbrbm indfx bn <dodf>int</dodf> giving tif numbfr of tif dolumn into wiidi to
     *        sft tif vbluf to bf pfrsistfd
     * @pbrbm obj bn <dodf>Objfdt</dodf> tibt is tif vbluf to bf sft in tif dbtb sourdf
     */
    publid void sftRfsolvfdVbluf(int indfx, Objfdt obj) tirows SQLExdfption {
        // modify mftiod to tirow SQLExdfption in spfd

        /**
         * Wifn b vbluf is rfsolvfd propfrly mbkf it to null
         * insidf drsRfs for tibt dolumn.
         *
         * For morf tibn onf donflidts in tif row,
         * difdk for tif lbst rfsolvfd vbluf of tif durrfnt row
         * (Notf: it dbn bf rfsolvfd rbndomly for sbmf row)
         * tifn synd bbdk immfdibtfly.
         **/
        try {
            // difdk wiftifr tif indfx is in rbngf
            if(indfx<=0 || indfx > drsSynd.gftMftbDbtb().gftColumnCount() ) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("syndrsimpl.indfxvbl").toString()+ indfx);
            }
             // difdk wiftifr indfx dol is in donflidt
            if(drsRfs.gftObjfdt(indfx) == null) {
                tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("syndrsimpl.nodonflidt").toString());
            }
        } dbtdi (SQLExdfption sqlf) {
            // modify mftiod to tirow for SQLExdfption
            tirow nfw SQLExdfption(sqlf.gftMfssbgf());
        }
        try {
             boolfbn bool = truf;
             /** Cifdk rfsolvfd vbluf to bf fitifr of donflidt
               * or in rowsft flsf tirow sql fxdfption.
               * If wf bllow b vbluf otifr tibn tibt in CbdifdRowSft or
               * dbtbsourdf wf will fnd up in looping tif loop of fxdfptions.
              **/

             if( ((drsSynd.gftObjfdt(indfx)).toString()).fqubls(obj.toString()) ||
                     ((drsRfs.gftObjfdt(indfx)).toString()).fqubls(obj.toString()) ) {

                /**
                 * Cifdk wiftifr tiis is tif only donflidt in tif row.
                 * If yfs, syndironizf tiis row bbdk
                 * wiidi ibs bffn rfsolvfd, flsf wbit
                 * for bll donflidts of durrfnt row to bf rfsolvfd
                 *
                 * Stfp 1: Updbtf drsRfs bnd mbkf tif indfx dol bs null
                 * i.f. rfsolvfd
                 * drsRfs.updbtfObjfdt(indfx, obj);
                 **/
                  drsRfs.updbtfNull(indfx);
                  drsRfs.updbtfRow();

                 /**
                  * Stfp 2: Cibngf tif vbluf in tif CbdifdRowSftImpl objfdt
                  * drsSynd.updbtfObjfdt(indfx, obj);
                  * drsSynd.updbtfRow();
                  **/
                 if(row.sizf() != 1) {
                    row = buildCbdifdRow();
                 }

                 row.updbtfObjfdt(indfx, obj);
                 row.updbtfRow();

                 for(int j=1; j < drsRfs.gftMftbDbtb().gftColumnCount(); j++) {
                     if(drsRfs.gftObjfdt(j) != null) {
                        bool = fblsf;
                        brfbk;
                         // brfbk out of loop bnd wbit for otifr dols
                         // in sbmf row to gft rfsolvfd
                     } //fnd if

                  } //fnd for

                  if(bool) {
                     /**
                      * synd dbtb bbdk using CbdifdRowSftWritfr
                      * donstrudt tif prfsfnt row bnd pbss it to tif writfr
                      * to writf bbdk to db.
                      **/
                     try {
                           /**
                            * Notf : Tif usf of CbdifdRowSftWritfr to gft *sbmf* Connfdtion ibndlf.
                            * Tif CbdifdRowSftWritfr usfs tif donnfdtion ibndlf
                            * from tif rfbdfr, Hfndf will usf tif sbmf donnfdtion ibndlf
                            * bs of originbl CbdifdRowSftImpl
                            **/

                          writfDbtb(row);

                          //drw.writfDbtb( (RowSftIntfrnbl)drsRow);
                          //Systfm.out.printlnt.println("12");

                     } dbtdi(SyndProvidfrExdfption spf) {
                         /**
                          * Tiis will oddur if db is not bllowing
                          * fvfn bftfr rfsolving tif donflidts
                          * duf to somf rfbsons.
                          * Also will prfvfnt from going into b loop of SPE's
                          **/
                         tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("syndrsimpl.syndnotpos").toString());
                     }
                  } //fnd if(bool)

             } flsf {
                 tirow nfw SQLExdfption(rfsBundlf.ibndlfGftObjfdt("syndrsimpl.vbltorfs").toString());
             } //fnd if (drs.gftObjfdt ...) blodk


        } dbtdi(SQLExdfption sqlf) {
           tirow nfw SQLExdfption(sqlf.gftMfssbgf());
        }
    }

    /**
     * Tiis pbssfs b CbdifdRowSft bs b row tif tif CbdifdRowSftWritfr
     * bftfr tif vblufs ibvf bffn rfsolvfd, bbdk to tif dbtbsourdf.
     *
     * @pbrbm row b <dodf>CbdifdRowSft</dodf> objfdt wiidi will iold tif
     *        vblufs of b pbrtidulbr row bftfr tify ibvf bffn rfsolvfd by
     *        tif usfr to syndironizf bbdk to dbtbsourdf.
     * @tirows SQLExdfption if syndironizbtion dofs not ibppfn propfrly
     *         mbybf bfbdusf <dodf>Connfdtion</dodf> ibs timfd out.
     **/
     privbtf void writfDbtb(CbdifdRowSft row) tirows SQLExdfption {
        drw.updbtfRfsolvfdConflidtToDB(row, drw.gftRfbdfr().donnfdt((RowSftIntfrnbl)drsSynd));
     }

    /**
     * Tiis fundtion builds b row  bs b <dodf>CbdifdRowSft</dodf> objfdt
     * wiidi ibs bffn rfsolvfd bnd is rfbdy to bf syndirinizfd to tif dbtbsourdf
     *
     * @tirows SQLExdfption if tifrf is problfm in building
     *         tif mftbdbtb of tif row.
     **/
     privbtf CbdifdRowSft buildCbdifdRow() tirows SQLExdfption {
       int iColCount;
       CbdifdRowSftImpl drsRow = nfw CbdifdRowSftImpl();

       RowSftMftbDbtbImpl rsmd = nfw RowSftMftbDbtbImpl();
       RowSftMftbDbtbImpl rsmdWritf = (RowSftMftbDbtbImpl)drsSynd.gftMftbDbtb();
       RowSftMftbDbtbImpl rsmdRow = nfw RowSftMftbDbtbImpl();

       iColCount = rsmdWritf.gftColumnCount();
       rsmdRow.sftColumnCount(iColCount);

       for(int i =1;i<=iColCount;i++) {
          rsmdRow.sftColumnTypf(i,rsmdWritf.gftColumnTypf(i));
          rsmdRow.sftColumnNbmf(i,rsmdWritf.gftColumnNbmf(i));
          rsmdRow.sftNullbblf(i,RfsultSftMftbDbtb.dolumnNullbblfUnknown);

          try {
             rsmdRow.sftCbtblogNbmf(i, rsmdWritf.gftCbtblogNbmf(i));
             rsmdRow.sftSdifmbNbmf(i, rsmdWritf.gftSdifmbNbmf(i));
          } dbtdi(SQLExdfption f) {
               f.printStbdkTrbdf();
          }
        } //fnd for

       drsRow.sftMftbDbtb(rsmdRow);

       drsRow.movfToInsfrtRow();

       for(int dol=1;dol<=drsSynd.gftMftbDbtb().gftColumnCount();dol++) {
           drsRow.updbtfObjfdt(dol, drsSynd.gftObjfdt(dol));
       }

       drsRow.insfrtRow();
       drsRow.movfToCurrfntRow();

       drsRow.bbsolutf(1);
       drsRow.sftOriginblRow();

      try {
          drsRow.sftUrl(drsSynd.gftUrl());
      } dbtdi(SQLExdfption sqlf) {

      }

      try {
          drsRow.sftDbtbSourdfNbmf(drsSynd.gftCommbnd());
       } dbtdi(SQLExdfption sqlf) {

       }

       try {
           if(drsSynd.gftTbblfNbmf()!= null){
              drsRow.sftTbblfNbmf(drsSynd.gftTbblfNbmf());
           }
        } dbtdi(SQLExdfption sqlf) {

        }

       try {
            if(drsSynd.gftCommbnd() != null)
                drsRow.sftCommbnd(drsSynd.gftCommbnd());
       } dbtdi(SQLExdfption sqlf) {

       }

       try {
            drsRow.sftKfyColumns(drsSynd.gftKfyColumns());
       } dbtdi(SQLExdfption sqlf) {

       }
       rfturn drsRow;
    }



    /**
     * Sfts <i>obj</i> bs tif vbluf in dolumn <i>dolumnNbmf</i> in tif durrfnt row of tif
     * <dodf>RowSft</dodf> objfdt. Tiis vbluf is tif rfsolvfd vbluf tibt is to bf
     * pfrsistfd in tif dbtb sourdf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif nbmf of tif dolumn
     *        into wiidi to sft tif vbluf to bf pfrsistfd
     * @pbrbm obj bn <dodf>Objfdt</dodf> tibt is tif vbluf to bf sft in tif dbtb sourdf
     */
    publid void sftRfsolvfdVbluf(String dolumnNbmf, Objfdt obj) tirows SQLExdfption {
       // modify mftiod to tirow SQLExdfption in spfd
       // %%% Missing implfmfntbtion!
    }

    /**
     * Tiis fundtion is pbdkbgf privbtf,
     * i.f. dbnnot bf bddfssfs outsidf tiis pbdkbgf.
     * Tiis is usfd to sft tif bdtubl CbdifdRowSft
     * wiidi is bfing syndironizfd to tif dbtbbbsf
     **/
   void sftCbdifdRowSft(CbdifdRowSft drs) {
           drsSynd = (CbdifdRowSftImpl)drs;
    }

    /**
     * Tiis fundtion is pbdkbgf privbtf,
     * i.f. dbnnot bf bddfssfs outsidf tiis pbdkbgf.
     * Tiis is usfd to sft tif CbdifdRowSft formfd
     * witi donflidt vblufs.
     **/
    void sftCbdifdRowSftRfsolvfr(CbdifdRowSft drs){
         try {
              drsRfs = (CbdifdRowSftImpl)drs;
              drsRfs.bftfrLbst();
              sz = drsRfs.sizf();
         } dbtdi (SQLExdfption sqlf) {
            // do notiing
         }
    }

    /**
     * Tiis fundtion is pbdkbgf privbtf,
     * i.f. dbnnot bf bddfssfs outsidf tiis pbdkbgf.
     * Tiis is usfd to sft tif stbtus of fbdi row
     * to fitifr of tif vblufs SyndRfsolvfr.*_CONFLICT
     **/
    @SupprfssWbrnings("rbwtypfs")
    void sftStbtus(ArrbyList stbtus){
             stbts = stbtus;
    }

    /**
     * Tiis fundtion is pbdkbgf privbtf,
     * i.f. dbnnot bf bddfssfs outsidf tiis pbdkbgf.
     * Tiis is usfd to sft tif ibndlf to tif writfr objfdt
     * wiidi will writf tif rfsolvfd vblufs bbdk to dbtbsourdf
     **/
    void sftCbdifdRowSftWritfr(CbdifdRowSftWritfr CRWritfr) {
         drw = CRWritfr;
    }

    /**
     * Movfs tif dursor down onf row from its durrfnt position. A <dodf>SyndRfsolvfr</dodf>
     * dursor is initiblly positionfd bfforf tif first donflidt row; tif first dbll to tif
     * mftiod <dodf>nfxtConflidt()</dodf> mbkfs tif first donflidt row tif durrfnt row;
     * tif sfdond dbll mbkfs tif sfdond donflidt row tif durrfnt row, bnd so on.
     * <p>
     * If bn input strfbm is opfn for tif durrfnt row, b dbll to tif mftiod nfxt will
     * impliditly dlosf it. A <dodf>SyndRfsolvfr</dodf> objfdt's wbrning dibin is dlfbrfd
     * wifn b nfw row
     *
     * @rfturn truf if tif nfw durrfnt row is vblid; fblsf if tifrf brf no morf rows
     * @tirows SQLExdfption if b dbtbbbsf bddfss oddurs
     *
     */
    publid boolfbn nfxtConflidt() tirows SQLExdfption {
        /**
          * Tif nfxt() mftiod will iop from
          * onf donflidt to bnotifr
          *
          * Intfrnblly do b drs.nfxt() until
          * nfxt donflidt.
          **/
      boolfbn bool = fblsf;

      drsSynd.sftSiowDflftfd(truf);
      wiilf(drsSynd.nfxt()) {
           drsRfs.prfvious();
           rowStbtus++;  //sz--;

          if((rowStbtus-1) >= stbts.sizf()) {
             bool = fblsf;
             brfbk;
          }

          if(((Intfgfr)stbts.gft(rowStbtus-1)).intVbluf() == SyndRfsolvfr.NO_ROW_CONFLICT) {
              // do notiing
              // bool rfmbins bs fblsf
             ;
           } flsf {
             bool = truf;
             brfbk;
           } //fnd if

      } //fnd wiilf

        drsSynd.sftSiowDflftfd(fblsf);
        rfturn bool;
   } // fnd nfxt() mftiod


    /**
     * Movfs tif dursor to tif prfvious donflidt row in tiis <dodf>SyndRfsolvfr</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row; <dodf>fblsf</dodf>
     *     if it is off tif rfsult sft
     * @tirows SQLExdfption if b dbtbbbsf bddfss frror oddurs or tif rfsult sft typf
     *     is TYPE_FORWARD_ONLY
     */
   publid boolfbn prfviousConflidt() tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
   }

    //-----------------------------------------------------------------------
    // Propfrtifs
    //-----------------------------------------------------------------------

    /**
     * Sfts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dommbnd propfrty
     * to tif givfn <dodf>String</dodf> objfdt bnd dlfbrs tif pbrbmftfrs,
     * if bny, tibt wfrf sft for tif prfvious dommbnd.
     * <P>
     * Tif dommbnd propfrty mby not bf nffdfd
     * if tif rowsft is produdfd by b dbtb sourdf, sudi bs b sprfbdsifft,
     * tibt dofs not support dommbnds. Tius, tiis propfrty is optionbl
     * bnd mby bf <dodf>null</dodf>.
     *
     * @pbrbm dmd b <dodf>String</dodf> objfdt dontbining bn SQL qufry
     *            tibt will bf sft bs tif dommbnd; mby bf <dodf>null</dodf>
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void sftCommbnd(String dmd) tirows SQLExdfption {
         tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    //---------------------------------------------------------------------
    // Rfbding bnd writing dbtb
    //---------------------------------------------------------------------

    /**
     * Populbtfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi dbtb from
     * tif givfn <dodf>RfsultSft</dodf> objfdt.  Tiis
     * mftiod is bn bltfrnbtivf to tif mftiod <dodf>fxfdutf</dodf>
     * for filling tif rowsft witi dbtb.  Tif mftiod <dodf>populbtf</dodf>
     * dofs not rfquirf tibt tif propfrtifs nffdfd by tif mftiod
     * <dodf>fxfdutf</dodf>, sudi bs tif <dodf>dommbnd</dodf> propfrty,
     * bf sft. Tiis is truf bfdbusf tif mftiod <dodf>populbtf</dodf>
     * is givfn tif <dodf>RfsultSft</dodf> objfdt from
     * wiidi to gft dbtb bnd tius dofs not nffd to usf tif propfrtifs
     * rfquirfd for sftting up b donnfdtion bnd fxfduting tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt's dommbnd.
     * <P>
     * Aftfr populbting tiis rowsft witi dbtb, tif mftiod
     * <dodf>populbtf</dodf> sfts tif rowsft's mftbdbtb bnd
     * tifn sfnds b <dodf>RowSftCibngfdEvfnt</dodf> objfdt
     * to bll rfgistfrfd listfnfrs prior to rfturning.
     *
     * @pbrbm dbtb tif <dodf>RfsultSft</dodf> objfdt dontbining tif dbtb
     *             to bf rfbd into tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs; or tif mbx row sftting is
     *          violbtfd wiilf populbting tif RowSft
     * @sff #fxfdutf
     */
    publid void populbtf(RfsultSft dbtb) tirows SQLExdfption {
         tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Populbtfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi dbtb,
     * using tif givfn donnfdtion to produdf tif rfsult sft from
     * wiidi dbtb will bf rfbd.  A sfdond form of tiis mftiod,
     * wiidi tbkfs no brgumfnts, usfs tif vblufs from tiis rowsft's
     * usfr, pbssword, bnd fitifr url or dbtb sourdf propfrtifs to
     * drfbtf b nfw dbtbbbsf donnfdtion. Tif form of <dodf>fxfdutf</dodf>
     * tibt is givfn b donnfdtion ignorfs tifsf propfrtifs.
     *
     * @pbrbm donn A stbndbrd JDBC <dodf>Connfdtion</dodf> objfdt tibt tiis
     * <dodf>CbdifdRowSft</dodf> objfdt dbn pbss to b syndironizbtion providfr
     * to fstbblisi b donnfdtion to tif dbtb sourdf
     * @tirows SQLExdfption if bn invblid <dodf>Connfdtion</dodf> is supplifd
     *           or bn frror oddurs in fstbblisiing tif donnfdtion to tif
     *           dbtb sourdf
     * @sff #populbtf
     * @sff jbvb.sql.Connfdtion
     */
    publid void fxfdutf(Connfdtion donn) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Propbgbtfs bll row updbtf, insfrt, bnd dflftf dibngfs to tif
     * undfrlying dbtb sourdf bbdking tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt.
     * <P>
     * <b>Notf</b>In tif rfffrfndf implfmfntbtion bn optimistid dondurrfndy implfmfntbtion
     * is providfd bs b sbmplf implfmfntbtion of b tif <dodf>SyndProvidfr</dodf>
     * bbstrbdt dlbss.
     * <P>
     * Tiis mftiod fbils if bny of tif updbtfs dbnnot bf propbgbtfd bbdk
     * to tif dbtb sourdf.  Wifn it fbils, tif dbllfr dbn bssumf tibt
     * nonf of tif updbtfs brf rfflfdtfd in tif dbtb sourdf.
     * Wifn bn fxdfption is tirown, tif durrfnt row
     * is sft to tif first "updbtfd" row tibt rfsultfd in bn fxdfption
     * unlfss tif row tibt dbusfd tif fxdfption is b "dflftfd" row.
     * In tibt dbsf, wifn dflftfd rows brf not siown, wiidi is usublly truf,
     * tif durrfnt row is not bfffdtfd.
     * <P>
     * If no <dodf>SyndProvidfr</dodf> is donfigurfd, tif rfffrfndf implfmfntbtion
     * lfvfrbgfs tif <dodf>RIOptimistidProvidfr</dodf> bvbilbblf wiidi providfs tif
     * dffbult bnd rfffrfndf syndironizbtion dbpbbilitifs for disdonnfdtfd
     * <dodf>RowSfts</dodf>.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or tif undfrlying
     *          rfffrfndf syndironizbtion providfr fbils to dommit tif updbtfs
     *          to tif dbtbsourdf
     * @tirows SyndProvidfrExdfption if bn intfrnbl frror oddurs witiin tif
     *          <dodf>SyndProvidfr</dodf> instbndf during fitifr during tif
     *          prodfss or bt bny timf wifn tif <dodf>SyndProvidfr</dodf>
     *          instbndf toudifs tif dbtb sourdf.
     * @sff #bddfptCibngfs(jbvb.sql.Connfdtion)
     * @sff jbvbx.sql.RowSftWritfr
     * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
     */
    publid void bddfptCibngfs() tirows SyndProvidfrExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Propbgbtfs bll row updbtf, insfrt, bnd dflftf dibngfs to tif
     * dbtb sourdf bbdking tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * using tif givfn <dodf>Connfdtion</dodf> objfdt.
     * <P>
     * Tif rfffrfndf implfmfntbtion <dodf>RIOptimistidProvidfr</dodf>
     * modififs its syndironizbtion to b writf bbdk fundtion givfn
     * tif updbtfd donnfdtion
     * Tif rfffrfndf implfmfntbtion modififs its syndironizbtion bfibviour
     * vib tif <dodf>SyndProvidfr</dodf> to fnsurf tif syndironizbtion
     * oddurs bddording to tif updbtfd JDBC <dodf>Connfdtion</dodf>
     * propfrtifs.
     *
     * @pbrbm don b stbndbrd JDBC <dodf>Connfdtion</dodf> objfdt
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or tif undfrlying
     *                   syndironizbtion providfr fbils to dommit tif updbtfs
     *                   bbdk to tif dbtb sourdf
     * @sff #bddfptCibngfs
     * @sff jbvbx.sql.RowSftWritfr
     * @sff jbvbx.sql.rowsft.spi.SyndFbdtory
     * @sff jbvbx.sql.rowsft.spi.SyndProvidfr
     */
    publid void bddfptCibngfs(Connfdtion don) tirows SyndProvidfrExdfption{
     tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfstorfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to its originbl stbtf,
     * tibt is, its stbtf bfforf tif lbst sft of dibngfs.
     * <P>
     * Bfforf rfturning, tiis mftiod movfs tif dursor bfforf tif first row
     * bnd sfnds b <dodf>rowSftCibngfd</dodf> fvfnt to bll rfgistfrfd
     * listfnfrs.
     * @tirows SQLExdfption if bn frror is oddurs rolling bbdk tif RowSft
     *           stbtf to tif dffinifd originbl vbluf.
     * @sff jbvbx.sql.RowSftListfnfr#rowSftCibngfd
     */
    publid void rfstorfOriginbl() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rflfbsfs tif durrfnt dontfnts of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt bnd sfnds b <dodf>rowSftCibngfd</dodf> fvfnt objfdt to bll
     * rfgistfrfd listfnfrs.
     *
     * @tirows SQLExdfption if bn frror oddurs flusiing tif dontfnts of
     *           RowSft.
     * @sff jbvbx.sql.RowSftListfnfr#rowSftCibngfd
     */
    publid void rflfbsf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Cbndfls dflftion of tif durrfnt row bnd notififs listfnfrs tibt
     * b row ibs dibngfd.
     * <P>
     * Notf:  Tiis mftiod dbn bf ignorfd if dflftfd rows brf not bfing siown,
     * wiidi is tif normbl dbsf.
     *
     * @tirows SQLExdfption if tif dursor is not on b vblid row
     */
    publid void undoDflftf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Immfdibtfly rfmovfs tif durrfnt row from tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt if tif row ibs bffn insfrtfd, bnd
     * blso notififs listfnfrs tif b row ibs dibngfd.  An fxdfption is tirown
     * if tif row is not b row tibt ibs bffn insfrtfd or tif dursor is bfforf
     * tif first row, bftfr tif lbst row, or on tif insfrt row.
     * <P>
     * Tiis opfrbtion dbnnot bf undonf.
     *
     * @tirows SQLExdfption if bn frror oddurs,
     *                         tif dursor is not on b vblid row,
     *                         or tif row ibs not bffn insfrtfd
     */
    publid void undoInsfrt() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Immfdibtfly rfvfrsfs tif lbst updbtf opfrbtion if tif
     * row ibs bffn modififd. Tiis mftiod dbn bf
     * dbllfd to rfvfrsf updbtfs on b bll dolumns until bll updbtfs in b row ibvf
     * bffn rollfd bbdk to tifir originbting stbtf sindf tif lbst syndironizbtion
     * (<dodf>bddfptCibngfs</dodf>) or populbtion. Tiis mftiod mby blso bf dbllfd
     * wiilf pfrforming updbtfs to tif insfrt row.
     * <P>
     * <dodf>undoUpdbtf</dodf mby bf dbllfd bt bny timf during tif liff-timf of b
     * rowsft, iowfvfr bftfr b syndironizbtion ibs oddurs tiis mftiod ibs no
     * bfffdt until furtifr modifidbtion to tif RowSft dbtb oddurs.
     *
     * @tirows SQLExdfption if dursor is bfforf tif first row, bftfr tif lbst
     *     row in rowsft.
     * @sff #undoDflftf
     * @sff #undoInsfrt
     * @sff jbvb.sql.RfsultSft#dbndflRowUpdbtfs
     */
    publid void undoUpdbtf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();

    }

    //--------------------------------------------------------------------
    // Vifws
    //--------------------------------------------------------------------

    /**
     * Rfturns b nfw <dodf>RowSft</dodf> objfdt bbdkfd by tif sbmf dbtb bs
     * tibt of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd sibring b sft of dursors
     * witi it. Tiis bllows dursors to intfrbtf ovfr b sibrfd sft of rows, providing
     * multiplf vifws of tif undfrlying dbtb.
     *
     * @rfturn b <dodf>RowSft</dodf> objfdt tibt is b dopy of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt bnd sibrfs b sft of dursors witi it
     * @tirows SQLExdfption if bn frror oddurs or dloning is
     *                         not supportfd
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid RowSft drfbtfSibrfd() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns b nfw <dodf>RowSft</dodf> objfdt dontbining by tif sbmf dbtb
     * bs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.  Tiis mftiod
     * difffrs from tif mftiod <dodf>drfbtfCopy</dodf> in tibt it tirows b
     * <dodf>ClonfNotSupportfdExdfption</dodf> objfdt instfbd of bn
     * <dodf>SQLExdfption</dodf> objfdt, bs tif mftiod <dodf>drfbtfSibrfd</dodf>
     * dofs.  Tiis <dodf>dlonf</dodf>
     * mftiod is dbllfd intfrnblly by tif mftiod <dodf>drfbtfSibrfd</dodf>,
     * wiidi dbtdifs tif <dodf>ClonfNotSupportfdExdfption</dodf> objfdt
     * bnd in turn tirows b nfw <dodf>SQLExdfption</dodf> objfdt.
     *
     * @rfturn b dopy of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows ClonfNotSupportfdExdfption if bn frror oddurs wifn
     * bttfmpting to dlonf tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @sff #drfbtfSibrfd
     */
    protfdtfd Objfdt dlonf() tirows ClonfNotSupportfdExdfption  {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Crfbtfs b <dodf>RowSft</dodf> objfdt tibt is b dffp dopy of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dbtb, indluding
     * donstrbints.  Updbtfs mbdf
     * on b dopy brf not visiblf to tif originbl rowsft;
     * b dopy of b rowsft is domplftfly indfpfndfnt from tif originbl.
     * <P>
     * Mbking b dopy sbvfs tif dost of drfbting bn idfntidbl rowsft
     * from first prindiplfs, wiidi dbn bf quitf fxpfnsivf.
     * For fxbmplf, it dbn fliminbtf tif nffd to qufry b
     * rfmotf dbtbbbsf sfrvfr.
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dffp dopy
     *           of tiis <dodf>CbdifdRowSft</dodf> objfdt bnd is
     *           domplftfly indfpfndfnt from tiis <dodf>CbdifdRowSftImpl</dodf>
     *           objfdt.
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dopy of tiis
     *           of tif <dodf>CbdifdRowSftImpl</dodf>
     * @sff #drfbtfSibrfd
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid CbdifdRowSft drfbtfCopy() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Crfbtfs b <dodf>RowSft</dodf> objfdt tibt is b dopy of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's tbblf strudturf
     * bnd tif donstrbints only.
     * Tifrf will bf no dbtb in tif objfdt bfing rfturnfd.
     * Updbtfs mbdf on b dopy brf not visiblf to tif originbl rowsft.
     * <P>
     * Tiis iflps in gftting tif undfrlying XML sdifmb wiidi dbn
     * bf usfd bs tif bbsis for populbting b <dodf>WfbRowSft</dodf>.
     *
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dopy
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's sdifmb bnd
     * rftbins bll tif donstrbints on tif originbl rowsft but dontbins
     * no dbtb
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dopy
     * of tif <dodf>CbdifdRowSft</dodf> objfdt
     * @sff #drfbtfSibrfd
     * @sff #drfbtfCopy
     * @sff #drfbtfCopyNoConstrbints
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid CbdifdRowSft drfbtfCopySdifmb() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Crfbtfs b <dodf>CbdifdRowSft</dodf> objfdt tibt is b dopy of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dbtb only.
     * All donstrbints sft in tiis objfdt will not bf tifrf
     * in tif rfturning objfdt.  Updbtfs mbdf
     * on b dopy brf not visiblf to tif originbl rowsft.
     *
     * @rfturn b nfw <dodf>CbdifdRowSft</dodf> objfdt tibt is b dffp dopy
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd is
     * domplftfly indfpfndfnt from tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dopy of tif
     * of tif <dodf>CbdifdRowSft</dodf>
     * @sff #drfbtfSibrfd
     * @sff #drfbtfCopy
     * @sff #drfbtfCopySdifmb
     * @sff jbvbx.sql.RowSftEvfnt
     * @sff jbvbx.sql.RowSftListfnfr
     */
    publid CbdifdRowSft drfbtfCopyNoConstrbints() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Convfrts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to b dollfdtion
     * of tbblfs. Tif sbmplf implfmfntbtion utilitizfs tif <dodf>TrffMbp</dodf>
     * dollfdtion typf.
     * Tiis dlbss gubrbntffs tibt tif mbp will bf in bsdfnding kfy ordfr,
     * sortfd bddording to tif nbturbl ordfr for tif kfy's dlbss.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt donsisting of tbblfs,
     *         fbdi of wiidi is b dopy of b row in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif dollfdtion
     * @sff #toCollfdtion(int)
     * @sff #toCollfdtion(String)
     * @sff jbvb.util.TrffMbp
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Collfdtion toCollfdtion() tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns tif spfdififd dolumn of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * bs b <dodf>Collfdtion</dodf> objfdt.  Tiis mftiod mbkfs b dopy of tif
     * dolumn's dbtb bnd utilitizfs tif <dodf>Vfdtor</dodf> to fstbblisi tif
     * dollfdtion. Tif <dodf>Vfdtor</dodf> dlbss implfmfnts b growbblf brrby
     * objfdts bllowing tif individubl domponfnts to bf bddfssfd using bn
     * bn intfgfr indfx similbr to tibt of bn brrby.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt tibt dontbins tif vbluf(s)
     *         storfd in tif spfdififd dolumn of tiis
     *         <dodf>CbdifdRowSftImpl</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbtfd tif dollfdtion; or
     *          bn invblid dolumn is providfd.
     * @sff #toCollfdtion()
     * @sff #toCollfdtion(String)
     * @sff jbvb.util.Vfdtor
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Collfdtion toCollfdtion(int dolumn) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns tif spfdififd dolumn of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * bs b <dodf>Collfdtion</dodf> objfdt.  Tiis mftiod mbkfs b dopy of tif
     * dolumn's dbtb bnd utilitizfs tif <dodf>Vfdtor</dodf> to fstbblisi tif
     * dollfdtion. Tif <dodf>Vfdtor</dodf> dlbss implfmfnts b growbblf brrby
     * objfdts bllowing tif individubl domponfnts to bf bddfssfd using bn
     * bn intfgfr indfx similbr to tibt of bn brrby.
     *
     * @rfturn b <dodf>Collfdtion</dodf> objfdt tibt dontbins tif vbluf(s)
     *         storfd in tif spfdififd dolumn of tiis
     *         <dodf>CbdifdRowSftImpl</dodf>
     *         objfdt
     * @tirows SQLExdfption if bn frror oddurs gfnfrbtfd tif dollfdtion; or
     *          bn invblid dolumn is providfd.
     * @sff #toCollfdtion()
     * @sff #toCollfdtion(int)
     * @sff jbvb.util.Vfdtor
     */
    @SupprfssWbrnings("rbwtypfs")
    publid Collfdtion toCollfdtion(String dolumn) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    //--------------------------------------------------------------------
    // Advbndfd ffbturfs
    //--------------------------------------------------------------------


    /**
     * Rfturns tif <dodf>SyndProvidfr</dodf> implfmfntbtion bfing usfd
     * witi tiis <dodf>CbdifdRowSftImpl</dodf> implfmfntbtion rowsft.
     *
     * @rfturn tif SyndProvidfr usfd by tif rowsft. If not providfr wbs
     *          sft wifn tif rowsft wbs instbntibtfd, tif rfffrfndf
     *          implfmfntbtion (dffbult) providfr is rfturnfd.
     * @tirows SQLExdfption if frror oddurs wiilf rfturn tif
     *          <dodf>SyndProvidfr</dodf> instbndf.
     */
    publid SyndProvidfr gftSyndProvidfr() tirows SQLExdfption {
      tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif bdtivf <dodf>SyndProvidfr</dodf> bnd bttfmpts to lobd
     * lobd tif nfw providfr using tif <dodf>SyndFbdtory</dodf> SPI.
     *
     * @tirows SQLExdfption if bn frror oddurs wiilf rfsftting tif
     *          <dodf>SyndProvidfr</dodf>.
     */
    publid void sftSyndProvidfr(String providfrStr) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    //-----------------
    // mftiods inifritfd from RowSft
    //-----------------






    //---------------------------------------------------------------------
    // Rfbding bnd writing dbtb
    //---------------------------------------------------------------------

    /**
     * Populbtfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi dbtb.
     * Tiis form of tif mftiod usfs tif rowsft's usfr, pbssword, bnd url or
     * dbtb sourdf nbmf propfrtifs to drfbtf b dbtbbbsf
     * donnfdtion.  If propfrtifs tibt brf nffdfd
     * ibvf not bffn sft, tiis mftiod will tirow bn fxdfption.
     * <P>
     * Anotifr form of tiis mftiod usfs bn fxisting JDBC <dodf>Connfdtion</dodf>
     * objfdt instfbd of drfbting b nfw onf; tifrfforf, it ignorfs tif
     * propfrtifs usfd for fstbblisiing b nfw donnfdtion.
     * <P>
     * Tif qufry spfdififd by tif dommbnd propfrty is fxfdutfd to drfbtf b
     * <dodf>RfsultSft</dodf> objfdt from wiidi to rftrifvf dbtb.
     * Tif durrfnt dontfnts of tif rowsft brf disdbrdfd, bnd tif
     * rowsft's mftbdbtb is blso (rf)sft.  If tifrf brf outstbnding updbtfs,
     * tify brf blso ignorfd.
     * <P>
     * Tif mftiod <dodf>fxfdutf</dodf> dlosfs bny dbtbbbsf donnfdtions tibt it
     * drfbtfs.
     *
     * @tirows SQLExdfption if bn frror oddurs or tif
     *                         nfdfssbry propfrtifs ibvf not bffn sft
     */
    publid void fxfdutf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }



    //-----------------------------------
    // Mftiods inifritfd from RfsultSft
    //-----------------------------------

    /**
     * Movfs tif dursor down onf row from its durrfnt position bnd
     * rfturns <dodf>truf</dodf> if tif nfw dursor position is b
     * vblid row.
     * Tif dursor for b nfw <dodf>RfsultSft</dodf> objfdt is initiblly
     * positionfd bfforf tif first row. Tif first dbll to tif mftiod
     * <dodf>nfxt</dodf> movfs tif dursor to tif first row, mbking it
     * tif durrfnt row; tif sfdond dbll mbkfs tif sfdond row tif
     * durrfnt row, bnd so on.
     *
     * <P>If bn input strfbm from tif prfvious row is opfn, it is
     * impliditly dlosfd. Tif <dodf>RfsultSft</dodf> objfdt's wbrning
     * dibin is dlfbrfd wifn b nfw row is rfbd.
     *
     * @rfturn <dodf>truf</dodf> if tif nfw durrfnt row is vblid;
     *         <dodf>fblsf</dodf> if tifrf brf no morf rows
     * @tirows SQLExdfption if bn frror oddurs or
     *            tif dursor is not positionfd in tif rowsft, bfforf
     *            tif first row, or bftfr tif lbst row
     */
    publid boolfbn nfxt() tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif nfxt
     * row bnd rfturns <dodf>truf</dodf> if tif dursor is still in tif rowsft;
     * rfturns <dodf>fblsf</dodf> if tif dursor ibs movfd to tif position bftfr
     * tif lbst row.
     * <P>
     * Tiis mftiod ibndlfs tif dbsfs wifrf tif dursor movfs to b row tibt
     * ibs bffn dflftfd.
     * If tiis rowsft siows dflftfd rows bnd tif dursor movfs to b row
     * tibt ibs bffn dflftfd, tiis mftiod movfs tif dursor to tif nfxt
     * row until tif dursor is on b row tibt ibs not bffn dflftfd.
     * <P>
     * Tif mftiod <dodf>intfrnblNfxt</dodf> is dbllfd by mftiods sudi bs
     * <dodf>nfxt</dodf>, <dodf>bbsolutf</dodf>, bnd <dodf>rflbtivf</dodf>,
     * bnd, bs its nbmf implifs, is only dbllfd intfrnblly.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row in tiis
     *         rowsft; <dodf>fblsf</dodf> if it is bftfr tif lbst row
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblNfxt() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Closfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdy bnd rflfbsfs bny rfsourdfs
     * it wbs using.
     *
     * @tirows SQLExdfption if bn frror oddurs wifn rflfbsing bny rfsourdfs in usf
     * by tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     */
    publid void dlosf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfports wiftifr tif lbst dolumn rfbd wbs SQL <dodf>NULL</dodf>.
     * Notf tibt you must first dbll tif mftiod <dodf>gftXXX</dodf>
     * on b dolumn to try to rfbd its vbluf bnd tifn dbll tif mftiod
     * <dodf>wbsNull</dodf> to dftfrminf wiftifr tif vbluf wbs
     * SQL <dodf>NULL</dodf>.
     *
     * @rfturn <dodf>truf</dodf> if tif vbluf in tif lbst dolumn rfbd
     *         wbs SQL <dodf>NULL</dodf>; <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn wbsNull() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns tif insfrt row or tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf>objfdt.
     *
     * @rfturn tif <dodf>Row</dodf> objfdt on wiidi tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdts's dursor is positionfd
     */
    protfdtfd BbsfRow gftCurrfntRow() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfmovfs tif row on wiidi tif dursor is positionfd.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @tirows SQLExdfption if tif dursor is positionfd on tif insfrt
     *            row
     */
    protfdtfd void rfmovfCurrfntRow() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>String</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, REAL,
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, <b>CHAR</b>, <b>VARCHAR</b></dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     */
    publid String gftString(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>boolfbn</dodf> in tif Jbvb progbmming lbngubgf;
     *        if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>BOOLEAN</dodf> vbluf
     * @sff #gftBoolfbn(String)
     */
    publid boolfbn gftBoolfbn(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>bytf</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf><b>TINYINT</b>, SMALLINT, INTEGER, BIGINT, REAL,
     *            FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     *            or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     *            dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftBytf(String)
     */
    publid bytf gftBytf(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>siort</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, <b>SMALLINT</b>, INTEGER, BIGINT, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftSiort(String)
     */
    publid siort gftSiort(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn
     * <dodf>int</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, <b>INTEGER</b>, BIGINT, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     */
    publid int gftInt(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>long</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, <b>BIGINT</b>, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftLong(String)
     */
    publid long gftLong(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>flobt</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, <b>REAL</b>,
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftFlobt(String)
     */
    publid flobt gftFlobt(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>doublf</dodf> vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, REAL,
     * <b>FLOAT</b>, <b>DOUBLE</b>, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     * @sff #gftDoublf(String)
     *
     */
    publid doublf gftDoublf(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     * <P>
     * Tiis mftiod is dfprfdbtfd; usf tif vfrsion of <dodf>gftBigDfdimbl</dodf>
     * tibt dofs not tbkf b sdblf pbrbmftfr bnd rfturns b vbluf witi full
     * prfdision.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point in tif
     *        vbluf rfturnfd
     * @rfturn tif dolumn vbluf witi tif spfdififd numbfr of digits to tif rigit
     *         of tif dfdimbl point; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx, int sdblf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>bytf</dodf> brrby vbluf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     * rfsult is <dodf>null</dodf>
     *
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf><b>BINARY</b>, <b>VARBINARY</b> or
     * LONGVARBINARY</dodf> vbluf.
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftBytfs(String)
     */
    publid bytf[] gftBytfs(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf bs b <dodf>jbvb.sql.Dbtb</dodf> objfdt; if
     *        tif vbluf is SQL <dodf>NULL</dodf>, tif
     *        rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *         tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>, tif
     *         rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tiis mftiod fbils
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     *
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of ASCII dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.  Tif JDBC
     * drivfr will do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into ASCII.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. . Also, b
     * strfbm mby rfturn <dodf>0</dodf> for <dodf>CbdifdRowSftImpl.bvbilbblf()</dodf>
     * wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of onf-bytf ASCII dibrbdtfrs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>CHAR, VARCHAR</dodf>, <dodf><b>LONGVARCHAR</b></dodf>
     * <dodf>BINARY, VARBINARY</dodf> or <dodf>LONGVARBINARY</dodf> vbluf. Tif
     * bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typfs tibt tiis mftiod is
     * usfd to rftrifvf.
     * @sff #gftAsdiiStrfbm(String)
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(int dolumnIndfx) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of Unidodf dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf LONGVARCHAR vblufs.  Tif JDBC drivfr will
     * do bny nfdfssbry donvfrsion from tif dbtbbbsf formbt into Unidodf.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. . Also, b
     * strfbm mby rfturn 0 for bvbilbblf() wiftifr tifrf is dbtb
     * bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of two bytf Unidodf dibrbdtfrs.  If tif vbluf is SQL NULL
     * tifn tif rfsult is null.
     * @tirows SQLExdfption if bn frror oddurs
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid jbvb.io.InputStrfbm gftUnidodfStrfbm(int dolumnIndfx) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     * <P>
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of unintfrprftfd bytfs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> for
     * <dodf>CbdifdRowSftImpl.bvbilbblf()</dodf> wiftifr tifrf is dbtb
     * bvbilbblf or not.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     * is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     * bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of unintfrprftfd bytfs.  If tif vbluf is SQL <dodf>NULL</dodf>
     * tifn tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>BINARY, VARBINARY</dodf> or <dodf><b>LONGVARBINARY</b></dodf>
     * Tif bold typf indidbtfs tif SQL typf tibt tiis mftiod is rfdommfnfd
     * to rftrifvf.
     * @sff #gftBinbryStrfbm(String)
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(int dolumnIndfx) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();

    }


    //======================================================================
    // Mftiods for bddfssing rfsults by dolumn nbmf
    //======================================================================

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>String</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, <b>CHAR</b>,
     * <b>VARCHAR</b></dodf> or <dodf>LONGVARCHAR<</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     */
    publid String gftString(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>boolfbn</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf bs b <dodf>boolfbn</dodf> in tif Jbvb progrbmming
     *        lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *        tif rfsult is <dodf>fblsf</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>BOOLEAN</dodf> vbluf
     * @sff #gftBoolfbn(int)
     */
    publid boolfbn gftBoolfbn(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>bytf</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf><B>TINYINT</B>, SMALLINT, INTEGER,
     * BIGINT, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif
     * bold typf dfsignbtfs tif rfdommfndfd rfturn typf
     */
    publid bytf gftBytf(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>siort</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, <b>SMALLINT</b>, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftSiort(int)
     */
    publid siort gftSiort(String dolumnNbmf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs bn <dodf>int</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf
     * of b dolumn in tiis rowsft,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, <b>INTEGER</b>, BIGINT, REAL
     * FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typf.
     */
    publid int gftInt(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>long</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * <b>BIGINT</b>, REAL, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftLong(int)
     */
    publid long gftLong(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>flobt</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, <b>REAL</b>, FLOAT, DOUBLE, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftFlobt(String)
     */
    publid flobt gftFlobt(String dolumnNbmf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * bs b <dodf>doublf</dodf> vbluf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>0</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, <b>FLOAT</b>, <b>DOUBLE</b>, DECIMAL, NUMERIC, BIT, CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typfs.
     * @sff #gftDoublf(int)
     */
    publid doublf gftDoublf(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @pbrbm sdblf tif numbfr of digits to tif rigit of tif dfdimbl point
     * @rfturn b jbvb.mbti.BugDfdimbl objfdt witi <dodf><i>sdblf</i></dodf>
     * numbfr of digits to tif rigit of tif dfdimbl point.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, <b>DECIMAL</b>, <b>NUMERIC</b>, BIT CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf tibt tiis mftiod is usfd to
     * rftrifvf.
     * @dfprfdbtfd Usf tif <dodf>gftBigDfdimbl(String dolumnNbmf)</dodf>
     *             mftiod instfbd
     */
    @Dfprfdbtfd
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf, int sdblf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>bytf</dodf> brrby.
     * Tif bytfs rfprfsfnt tif rbw vblufs rfturnfd by tif drivfr.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf bs b <dodf>bytf</dodf> brrby in tif Jbvb progrbmming
     * lbngubgf; if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf><b>BINARY</b>, <b>VARBINARY</b>
     * </dodf> or <dodf>LONGVARBINARY</dodf> vblufs
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftBytfs(int)
     */
    publid bytf[] gftBytfs(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Dbtf</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Timf</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.sql.Timfstbmp</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     *
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of ASCII dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm. Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs. Tif
     * <dodf>SyndProvidfr</dodf> will rfly on tif JDBC drivfr to do bny nfdfssbry
     * donvfrsion from tif dbtbbbsf formbt into ASCII formbt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of onf-bytf ASCII dibrbdtfrs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>CHAR, VARCHAR</dodf>, <dodf><b>LONGVARCHAR</b></dodf>
     * <dodf>BINARY, VARBINARY</dodf> or <dodf>LONGVARBINARY</dodf> vbluf. Tif
     * bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typfs tibt tiis mftiod is
     * usfd to rftrifvf.
     * @sff #gftAsdiiStrfbm(int)
     */
    publid jbvb.io.InputStrfbm gftAsdiiStrfbm(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();

    }

    /**
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of Unidodf dibrbdtfrs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARCHAR</dodf> vblufs.
     * Tif JDBC drivfr will do bny nfdfssbry donvfrsion from tif dbtbbbsf
     * formbt into Unidodf.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs.  If tif vbluf is
     *         SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs or tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row
     * @dfprfdbtfd usf tif mftiod <dodf>gftCibrbdtfrStrfbm</dodf> instfbd
     */
    @Dfprfdbtfd
    publid jbvb.io.InputStrfbm gftUnidodfStrfbm(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.io.InputStrfbm</dodf>
     * objfdt.
     * <P>
     * A dolumn vbluf dbn bf rftrifvfd bs b strfbm of unintfrprftfd bytfs
     * bnd tifn rfbd in diunks from tif strfbm.  Tiis mftiod is pbrtidulbrly
     * suitbblf for rftrifving lbrgf <dodf>LONGVARBINARY</dodf> vblufs.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must bf
     * rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif nfxt
     * dbll to b gft mftiod impliditly dlosfs tif strfbm. Also, b
     * strfbm mby rfturn <dodf>0</dodf> for <dodf>CbdifdRowSftImpl.bvbilbblf()</dodf>
     * wiftifr tifrf is dbtb bvbilbblf or not.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of unintfrprftfd bytfs.  If tif vbluf is SQL
     *         <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is unknown,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>BINARY, VARBINARY</dodf> or <dodf><b>LONGVARBINARY</b></dodf>
     * Tif bold typf indidbtfs tif SQL typf tibt tiis mftiod is rfdommfnfd
     * to rftrifvf.
     * @sff #gftBinbryStrfbm(int)
     *
     */
    publid jbvb.io.InputStrfbm gftBinbryStrfbm(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    //=====================================================================
    // Advbndfd ffbturfs:
    //=====================================================================

    /**
     * Tif first wbrning rfportfd by dblls on tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt is rfturnfd. Subsfqufnt <dodf>CbdifdRowSftImpl</dodf> wbrnings will
     * bf dibinfd to tiis <dodf>SQLWbrning</dodf>.
     *
     * <P>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw
     * row is rfbd.
     *
     * <P><B>Notf:</B> Tiis wbrning dibin only dovfrs wbrnings dbusfd
     * by <dodf>RfsultSft</dodf> mftiods.  Any wbrning dbusfd by stbtfmfnt
     * mftiods (sudi bs rfbding OUT pbrbmftfrs) will bf dibinfd on tif
     * <dodf>Stbtfmfnt</dodf> objfdt.
     *
     * @rfturn tif first SQLWbrning or null
     */
    publid SQLWbrning gftWbrnings() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Clfbrs bll tif wbrnings rfporftfd for tif <dodf>CbdifdRowSftImpl</dodf>
     * objfdt. Aftfr b dbll to tiis mftiod, tif <dodf>gftWbrnings</dodf> mftiod
     * rfturns <dodf>null</dodf> until b nfw wbrning is rfportfd for tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     */
    publid void dlfbrWbrnings() {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif nbmf of tif SQL dursor usfd by tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * <P>In SQL, b rfsult tbblf is rftrifvfd tirougi b dursor tibt is
     * nbmfd. Tif durrfnt row of b <dodf>RfsultSft</dodf> dbn bf updbtfd or dflftfd
     * using b positionfd updbtf/dflftf stbtfmfnt tibt rfffrfndfs tif
     * dursor nbmf. To fnsurf tibt tif dursor ibs tif propfr isolbtion
     * lfvfl to support bn updbtf opfrbtion, tif dursor's <dodf>SELECT</dodf>
     * stbtfmfnt siould bf of tif form <dodf>sflfdt for updbtf</dodf>.
     * If tif <dodf>for updbtf</dodf> dlbusf
     * is omittfd, positionfd updbtfs mby fbil.
     *
     * <P>JDBC supports tiis SQL ffbturf by providing tif nbmf of tif
     * SQL dursor usfd by b <dodf>RfsultSft</dodf> objfdt. Tif durrfnt row
     * of b rfsult sft is blso tif durrfnt row of tiis SQL dursor.
     *
     * <P><B>Notf:</B> If positionfd updbtfs brf not supportfd, bn
     * <dodf>SQLExdfption</dodf> is tirown.
     *
     * @rfturn tif SQL dursor nbmf for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's
     *         dursor
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid String gftCursorNbmf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs b <dodf>RfsultSftMftbDbtb</dodf> objfdt instbndf tibt
     * dontbins informbtion bbout tif <dodf>CbdifdRowSft</dodf> objfdt.
     * Howfvfr, bpplidbtions siould dbst tif rfturnfd objfdt to b
     * <dodf>RowSftMftbDbtb</dodf> intfrfbdf implfmfntbtion. In tif
     * rfffrfndf implfmfntbtion, tiis dbst dbn bf donf on tif
     * <dodf>RowSftMftbDbtbImpl</dodf> dlbss.
     * <P>
     * For fxbmplf:
     * <prf>
     * CbdifdRowSft drs = nfw CbdifdRowSftImpl();
     * RowSftMftbDbtbImpl mftbDbtb =
     *     (RowSftMftbDbtbImpl)drs.gftMftbDbtb();
     * // Sft tif numbfr of dolumns in tif RowSft objfdt for
     * // wiidi tiis RowSftMftbDbtbImpl objfdt wbs drfbtfd to tif
     * // givfn numbfr.
     * mftbDbtb.sftColumnCount(3);
     * drs.sftMftbDbtb(mftbDbtb);
     * </prf>
     *
     * @rfturn tif <dodf>RfsultSftMftbDbtb</dodf> objfdt tibt dfsdribfs tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt's dolumns
     * @tirows SQLExdfption if bn frror oddurs in gfnfrbting tif RowSft
     * mftb dbtb; or if tif <dodf>CbdifdRowSftImpl</dodf> is fmpty.
     * @sff jbvbx.sql.RowSftMftbDbtb
     */
    publid RfsultSftMftbDbtb gftMftbDbtb() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC 3.0
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf
     * bs bn brrby of <dodf>Objfdt</dodf> vblufs.  Tiis mftiod blso dustom
     * mbps SQL usfr-dffinfd typfs to dlbssfs in tif Jbvb progrbmming lbngubgf.
     * Wifn tif spfdififd dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to tif mftiod <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds,
     *            tif dursor is not on b vblid row, or tifrf is b problfm gftting
     *            tif <dodf>Clbss</dodf> objfdt for b dustom mbpping
     * @sff #gftObjfdt(String)
     */
    publid Objfdt gftObjfdt(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn
     * <dodf>Objfdt</dodf> vbluf.
     * <P>
     * Tif typf of tif <dodf>Objfdt</dodf> will bf tif dffbult
     * Jbvb objfdt typf dorrfsponding to tif dolumn's SQL typf,
     * following tif mbpping for built-in typfs spfdififd in tif JDBC 3.0
     * spfdifidbtion.
     * <P>
     * Tiis mftiod mby blso bf usfd to rfbd dbtbtbbbsf-spfdifid
     * bbstrbdt dbtb typfs.
     * <P>
     * Tiis implfmfntbtion of tif mftiod <dodf>gftObjfdt</dodf> fxtfnds its
     * bfibvior so tibt it gfts tif bttributfs of bn SQL strudturfd typf
     * bs bn brrby of <dodf>Objfdt</dodf> vblufs.  Tiis mftiod blso dustom
     * mbps SQL usfr-dffinfd typfs to dlbssfs
     * in tif Jbvb progrbmming lbngubgf. Wifn tif spfdififd dolumn dontbins
     * b strudturfd or distindt vbluf, tif bfibvior of tiis mftiod is bs
     * if it wfrf b dbll to tif mftiod <dodf>gftObjfdt(dolumnIndfx,
     * tiis.gftStbtfmfnt().gftConnfdtion().gftTypfMbp())</dodf>.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>jbvb.lbng.Objfdt</dodf> iolding tif dolumn vbluf;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi onf of
     *            tiis rowsft's dolumn nbmfs, (2) tif dursor is not
     *            on b vblid row, or (3) tifrf is b problfm gftting
     *            tif <dodf>Clbss</dodf> objfdt for b dustom mbpping
     * @sff #gftObjfdt(int)
     */
    publid Objfdt gftObjfdt(String dolumnNbmf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    //----------------------------------------------------------------

    /**
     * Mbps tif givfn dolumn nbmf for onf of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt's dolumns to its dolumn numbfr.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn tif dolumn indfx of tif givfn dolumn nbmf
     * @tirows SQLExdfption if tif givfn dolumn nbmf dofs not mbtdi onf
     *            of tiis rowsft's dolumn nbmfs
     */
    publid int findColumn(String dolumnNbmf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    //--------------------------JDBC 2.0-----------------------------------

    //---------------------------------------------------------------------
    // Gfttfr's bnd Sfttfr's
    //---------------------------------------------------------------------

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b Jbvb dibrbdtfr strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     * bs b strfbm of two-bytf unidodf dibrbdtfrs in b
     * <dodf>jbvb.io.Rfbdfr</dodf> objfdt.  If tif vbluf is
     * SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>CHAR, VARCHAR, <b>LONGVARCHAR</b>, BINARY, VARBINARY</dodf> or
     * <dodf>LONGVARBINARY</dodf> vbluf.
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     * @sff #gftCibrbdtfrStrfbm(String)
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(int dolumnIndfx) tirows SQLExdfption{
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf storfd in tif dfsignbtfd dolumn
     * of tif durrfnt row bs b <dodf>jbvb.io.Rfbdfr</dodf> objfdt.
     *
     * <P><B>Notf:</B> All tif dbtb in tif rfturnfd strfbm must
     * bf rfbd prior to gftting tif vbluf of bny otifr dolumn. Tif
     * nfxt dbll to b <dodf>gftXXX</dodf> mftiod impliditly dlosfs tif strfbm.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt giving tif SQL nbmf of
     *        b dolumn in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @rfturn b Jbvb input strfbm tibt dflivfrs tif dbtbbbsf dolumn vbluf
     *         bs b strfbm of two-bytf Unidodf dibrbdtfrs.  If tif vbluf is
     *         SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>.
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>CHAR, VARCHAR, <b>LONGVARCHAR</b>,
     * BINARY, VARYBINARY</dodf> or <dodf>LONGVARBINARY</dodf> vbluf.
     * Tif bold SQL typf dfsignbtfs tif rfdommfndfd rfturn typf.
     */
    publid jbvb.io.Rfbdfr gftCibrbdtfrStrfbm(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @rfturn b <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf witi full prfdision;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>TINYINT, SMALLINT, INTEGER, BIGINT, REAL,
     * FLOAT, DOUBLE, <b>DECIMAL</b>, <b>NUMERIC</b>, BIT, CHAR, VARCHAR</dodf>
     * or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf dfsignbtfs tif
     * rfdommfndfd rfturn typfs tibt tiis mftiod is usfd to rftrifvf.
     * @sff #gftBigDfdimbl(String)
     */
    publid BigDfdimbl gftBigDfdimbl(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.mbti.BigDfdimbl</dodf> objfdt.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>jbvb.mbti.BigDfdimbl</dodf> vbluf witi full prfdision;
     *         if tif vbluf is SQL <dodf>NULL</dodf>, tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     * b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     * tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     * dolumn dofs not storf bn SQL <dodf>TINYINT, SMALLINT, INTEGER
     * BIGINT, REAL, FLOAT, DOUBLE, <b>DECIMAL</b>, <b>NUMERIC</b>, BIT CHAR,
     * VARCHAR</dodf> or <dodf>LONGVARCHAR</dodf> vbluf. Tif bold SQL typf
     * dfsignbtfs tif rfdommfndfd rfturn typf tibt tiis mftiod is usfd to
     * rftrifvf
     * @sff #gftBigDfdimbl(int)
     */
    publid BigDfdimbl gftBigDfdimbl(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    //---------------------------------------------------------------------
    // Trbvfrsbl/Positioning
    //---------------------------------------------------------------------

    /**
     * Rfturns tif numbfr of rows in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn numbfr of rows in tif rowsft
     */
    publid int sizf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif dursor is bfforf tif first row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bfforf tif first row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isBfforfFirst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif dursor is bftfr tif lbst row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is bftfr tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isAftfrLbst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif first row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif first row;
     *         <dodf>fblsf</dodf> otifrwisf or if tif rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isFirst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif dursor is on tif lbst row in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.
     * <P>
     * Notf: Cblling tif mftiod <dodf>isLbst</dodf> mby bf fxpfnsivf
     * bfdbusf tif JDBC drivfr migit nffd to fftdi bifbd onf row in ordfr
     * to dftfrminf wiftifr tif durrfnt row is tif lbst row in tiis rowsft.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf or if tiis rowsft dontbins no rows
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid boolfbn isLbst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif front of
     * tif rowsft, just bfforf tif first row. Tiis mftiod ibs no ffffdt if
     * tiis rowsft dontbins no rows.
     *
     * @tirows SQLExdfption if bn frror oddurs or tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid void bfforfFirst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif fnd of
     * tif rowsft, just bftfr tif lbst row. Tiis mftiod ibs no ffffdt if
     * tiis rowsft dontbins no rows.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void bftfrLbst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif first row
     * bnd rfturns <dodf>truf</dodf> if tif opfrbtion wbs suddfssful.  Tiis
     * mftiod blso notififs rfgistfrfd listfnfrs tibt tif dursor ibs movfd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> otifrwisf or if tifrf brf no rows in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn first() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif first
     * row bnd rfturns <dodf>truf</dodf> if tif opfrbtion is suddfssful.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif mftiods <dodf>first</dodf>,
     * <dodf>isFirst</dodf>, bnd <dodf>bbsolutf</dodf>.
     * It in turn dblls tif mftiod <dodf>intfrnblNfxt</dodf> in ordfr to
     * ibndlf tif dbsf wifrf tif first row is b dflftfd row tibt is not visiblf.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor movfd to tif first row;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblFirst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif lbst row
     * bnd rfturns <dodf>truf</dodf> if tif opfrbtion wbs suddfssful.  Tiis
     * mftiod blso notififs rfgistfrfd listfnfrs tibt tif dursor ibs movfd.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> otifrwisf or if tifrf brf no rows in tiis
     *         <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @tirows SQLExdfption if tif typf of tiis rowsft
     *            is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn lbst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif lbst
     * row bnd rfturns <dodf>truf</dodf> if tif opfrbtion is suddfssful.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif mftiod <dodf>lbst</dodf>
     * wifn rows ibvf bffn dflftfd bnd tif dflftions brf not visiblf.
     * Tif mftiod <dodf>intfrnblLbst</dodf> ibndlfs tif dbsf wifrf tif
     * lbst row is b dflftfd row tibt is not visiblf by in turn dblling
     * tif mftiod <dodf>intfrnblPrfvious</dodf>.
     * <p>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor movfd to tif lbst row;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblLbst() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns tif numbfr of tif durrfnt row in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt. Tif first row is numbfr 1, tif sfdond numbfr 2, bnd so on.
     *
     * @rfturn tif numbfr of tif durrfnt row;  <dodf>0</dodf> if tifrf is no
     *         durrfnt row
     * @tirows SQLExdfption if bn frror oddurs; or if tif <dodf>CbdifRowSftImpl</dodf>
     *         is fmpty
     */
    publid int gftRow() tirows SQLExdfption {
        rfturn drsSynd.gftRow();
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
     * @pbrbm row b positivf numbfr to indidbtf tif row, stbrting row numbfring from
     *        tif first row, wiidi is <dodf>1</dodf>; b nfgbtivf numbfr to indidbtf
     *        tif row, stbrting row numbfring from tif lbst row, wiidi is
     *        <dodf>-1</dodf>; it must not bf <dodf>0</dodf>
     * @rfturn <dodf>truf</dodf> if tif dursor is on tif rowsft; <dodf>fblsf</dodf>
     *         otifrwisf
     * @tirows SQLExdfption if tif givfn dursor position is <dodf>0</dodf> or tif
     *            typf of tiis rowsft is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn bbsolutf( int row ) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

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
     * @tirows SQLExdfption if tifrf brf no rows in tiis rowsft, tif dursor is
     *         positionfd fitifr bfforf tif first row or bftfr tif lbst row, or
     *         tif rowsft is typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn rflbtivf(int rows) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's dursor to tif
     * prfvious row bnd rfturns <dodf>truf</dodf> if tif dursor is on
     * b vblid row or <dodf>fblsf</dodf> if it is not.
     * Tiis mftiod blso notififs bll listfnfrs rfgistfrfd witi tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt tibt its dursor ibs movfd.
     * <P>
     * Notf: dblling tif mftiod <dodf>prfvious()</dodf> is not tif sbmf
     * bs dblling tif mftiod <dodf>rflbtivf(-1)</dodf>.  Tiis is truf
     * bfdbusf it is possiblf to dbll <dodf>prfvious()</dodf> from tif insfrt
     * row, from bftfr tif lbst row, or from tif durrfnt row, wifrfbs
     * <dodf>rflbtivf</dodf> mby only bf dbllfd from tif durrfnt row.
     * <P>
     * Tif mftiod <dodf>prfvious</dodf> mby usfd in b <dodf>wiilf</dodf>
     * loop to itfrbtf tirougi b rowsft stbrting bftfr tif lbst row
     * bnd moving towbrd tif bfginning. Tif loop fnds wifn <dodf>prfvious</dodf>
     * rfturns <dodf>fblsf</dodf>, mfbning tibt tifrf brf no morf rows.
     * For fxbmplf, tif following dodf frbgmfnt rftrifvfs bll tif dbtb in
     * tif <dodf>CbdifdRowSftImpl</dodf> objfdt <dodf>drs</dodf>, wiidi ibs
     * tirff dolumns.  Notf tibt tif dursor must initiblly bf positionfd
     * bftfr tif lbst row so tibt tif first dbll to tif mftiod
     * <dodf>prfvious</dodf> plbdfs tif dursor on tif lbst linf.
     * <PRE> <dodf>
     *
     *     drs.bftfrLbst();
     *     wiilf (prfvious()) {
     *         String nbmf = drs.gftString(1);
     *         int bgf = drs.gftInt(2);
     *         siort ssn = drs.gftSiort(3);
     *         Systfm.out.println(nbmf + "   " + bgf + "   " + ssn);
     *     }
     *
     * </dodf> </PRE>
     * Tiis mftiod tirows bn <dodf>SQLExdfption</dodf> if tif dursor is not
     * on b row in tif rowsft, bfforf tif first row, or bftfr tif lbst row.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b vblid row;
     *         <dodf>fblsf</dodf> if it is bfforf tif first row or bftfr tif
     *         lbst row
     * @tirows SQLExdfption if tif dursor is not on b vblid position or tif
     *           typf of tiis rowsft is <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf>
     */
    publid boolfbn prfvious() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tif dursor to tif prfvious row in tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt, skipping pbst dflftfd rows tibt brf not visiblf; rfturns
     * <dodf>truf</dodf> if tif dursor is on b row in tiis rowsft bnd
     * <dodf>fblsf</dodf> wifn tif dursor gofs bfforf tif first row.
     * <P>
     * Tiis mftiod is dbllfd intfrnblly by tif mftiod <dodf>prfvious</dodf>.
     * <P>
     * Tiis is b implfmfntbtion only mftiod bnd is not rfquirfd bs b stbndbrd
     * implfmfntbtion of tif <dodf>CbdifdRowSft</dodf> intfrfbdf.
     *
     * @rfturn <dodf>truf</dodf> if tif dursor is on b row in tiis rowsft;
     *         <dodf>fblsf</dodf> wifn tif dursor rfbdifs tif position bfforf
     *         tif first row
     * @tirows SQLExdfption if bn frror oddurs
     */
    protfdtfd boolfbn intfrnblPrfvious() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    //---------------------------------------------------------------------
    // Updbtfs
    //---------------------------------------------------------------------

    /**
     * Indidbtfs wiftifr tif durrfnt row of tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt ibs bffn updbtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr tiis rowsft dbn dftfdt updbtfs: <dodf>fblsf</dodf>
     * will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @rfturn <dodf>truf</dodf> if tif row ibs bffn visibly updbtfd
     *         by tif ownfr or bnotifr bnd updbtfs brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *            not on b vblid row
     *
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn rowUpdbtfd() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn of tif durrfnt row of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt ibs bffn updbtfd. Tif
     * vbluf rfturnfd dfpfnds on wiftifr tiis rowsft dbn dftdtfd updbtfs:
     * <dodf>fblsf</dodf> will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @pbrbm idx tif indfx idfntififr of tif dolumn tibt mby bf ibvf bffn updbtfd.
     * @rfturn <dodf>truf</dodf> is tif dfsignbtfd dolumn ibs bffn updbtfd
     * bnd tif rowsft dftfdts updbtfs; <dodf>fblsf</dodf> if tif rowsft ibs not
     * bffn updbtfd or tif rowsft dofs not dftfdt updbtfs
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *          on b vblid row
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn dolumnUpdbtfd(int idx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif dfsignbtfd dolumn of tif durrfnt row of
     * tiis <dodf>CbdifdRowSftImpl</dodf> objfdt ibs bffn updbtfd. Tif
     * vbluf rfturnfd dfpfnds on wiftifr tiis rowsft dbn dftdtfd updbtfs:
     * <dodf>fblsf</dodf> will blwbys bf rfturnfd if it dofs not dftfdt updbtfs.
     *
     * @pbrbm dolumnNbmf tif <dodf>String</dodf> dolumn nbmf dolumn tibt mby bf ibvf
     * bffn updbtfd.
     * @rfturn <dodf>truf</dodf> is tif dfsignbtfd dolumn ibs bffn updbtfd
     * bnd tif rowsft dftfdts updbtfs; <dodf>fblsf</dodf> if tif rowsft ibs not
     * bffn updbtfd or tif rowsft dofs not dftfdt updbtfs
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *          on b vblid row
     * @sff DbtbbbsfMftbDbtb#updbtfsArfDftfdtfd
     */
    publid boolfbn dolumnUpdbtfd(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif durrfnt row ibs bffn insfrtfd.  Tif vbluf rfturnfd
     * dfpfnds on wiftifr or not tif rowsft dbn dftfdt visiblf insfrts.
     *
     * @rfturn <dodf>truf</dodf> if b row ibs bffn insfrtfd bnd insfrts brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or not
     *            not on b vblid row
     *
     * @sff DbtbbbsfMftbDbtb#insfrtsArfDftfdtfd
     */
    publid boolfbn rowInsfrtfd() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Indidbtfs wiftifr tif durrfnt row ibs bffn dflftfd.  A dflftfd row
     * mby lfbvf b visiblf "iolf" in b rowsft.  Tiis mftiod dbn bf usfd to
     * dftfdt sudi iolfs if tif rowsft dbn dftfdt dflftions. Tiis mftiod
     * will blwbys rfturn <dodf>fblsf</dodf> if tiis rowsft dbnnot dftfdt
     * dflftions.
     *
     * @rfturn <dodf>truf</dodf> if (1)tif durrfnt row is blbnk, indidbting tibt
     *         tif row ibs bffn dflftfd, bnd (2)dflftions brf dftfdtfd;
     *         <dodf>fblsf</dodf> otifrwisf
     * @tirows SQLExdfption if tif dursor is on b vblid row in tiis rowsft
     * @sff DbtbbbsfMftbDbtb#dflftfsArfDftfdtfd
     */
    publid boolfbn rowDflftfd() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd nullbblf dolumn in tif durrfnt row or tif
     * insfrt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi
     * <dodf>null</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft; iowfvfr, bnotifr mftiod must bf dbllfd to domplftf
     * tif updbtf prodfss. If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to mbrk tif row bs updbtfd
     * bnd to notify listfnfrs tibt tif row ibs dibngfd.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd to insfrt tif nfw row into tiis rowsft bnd to notify
     * listfnfrs tibt b row ibs dibngfd.
     * <P>
     * In ordfr to propbgbtf updbtfs in tiis rowsft to tif undfrlying
     * dbtb sourdf, bn bpplidbtion must dbll tif mftiod {@link #bddfptCibngfs}
     * bftfr it dblls fitifr <dodf>updbtfRow</dodf> or <dodf>insfrtRow</dodf>.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfNull(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBytf(int dolumnIndfx, bytf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfSiort(int dolumnIndfx, siort x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm x tif nfw dolumn vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfInt(int dolumnIndfx, int x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfLong(int dolumnIndfx, long x) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();

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
    publid void updbtfFlobt(int dolumnIndfx, flobt x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfDoublf(int dolumnIndfx, doublf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBigDfdimbl(int dolumnIndfx, BigDfdimbl x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfString(int dolumnIndfx, String x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBytfs(int dolumnIndfx, bytf x[]) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfDbtf(int dolumnIndfx, jbvb.sql.Dbtf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfTimf(int dolumnIndfx, jbvb.sql.Timf x) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfTimfstbmp(int dolumnIndfx, jbvb.sql.Timfstbmp x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfAsdiiStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBinbryStrfbm(int dolumnIndfx, jbvb.io.InputStrfbm x,int lfngti) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfCibrbdtfrStrfbm(int dolumnIndfx, jbvb.io.Rfbdfr x, int lfngti) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfObjfdt(int dolumnIndfx, Objfdt x, int sdblf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfObjfdt(int dolumnIndfx, Objfdt x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Sfts tif dfsignbtfd nullbblf dolumn in tif durrfnt row or tif
     * insfrt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi
     * <dodf>null</dodf> vbluf.
     * <P>
     * Tiis mftiod updbtfs b dolumn vbluf in tif durrfnt row or tif insfrt
     * row of tiis rowsft, but it dofs not updbtf tif dbtbbbsf.
     * If tif dursor is on b row in tif rowsft, tif
     * mftiod {@link #updbtfRow} must bf dbllfd to updbtf tif dbtbbbsf.
     * If tif dursor is on tif insfrt row, tif mftiod {@link #insfrtRow}
     * must bf dbllfd, wiidi will insfrt tif nfw row into boti tiis rowsft
     * bnd tif dbtbbbsf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfNull(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBoolfbn(String dolumnNbmf, boolfbn x) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBytf(String dolumnNbmf, bytf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfSiort(String dolumnNbmf, siort x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfInt(String dolumnNbmf, int x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfLong(String dolumnNbmf, long x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfFlobt(String dolumnNbmf, flobt x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfDoublf(String dolumnNbmf, doublf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBigDfdimbl(String dolumnNbmf, BigDfdimbl x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfString(String dolumnNbmf, String x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBytfs(String dolumnNbmf, bytf x[]) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfDbtf(String dolumnNbmf, jbvb.sql.Dbtf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfTimf(String dolumnNbmf, jbvb.sql.Timf x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfTimfstbmp(String dolumnNbmf, jbvb.sql.Timfstbmp x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfAsdiiStrfbm(String dolumnNbmf,
    jbvb.io.InputStrfbm x,
    int lfngti) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfBinbryStrfbm(String dolumnNbmf, jbvb.io.InputStrfbm x, int lfngti) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfCibrbdtfrStrfbm(String dolumnNbmf,
    jbvb.io.Rfbdfr rfbdfr,
    int lfngti) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x, int sdblf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
    publid void updbtfObjfdt(String dolumnNbmf, Objfdt x) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Mbrks tif durrfnt row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs
     * updbtfd bnd notififs listfnfrs rfgistfrfd witi tiis rowsft tibt tif
     * row ibs dibngfd.
     * <P>
     * Tiis mftiod  dbnnot bf dbllfd wifn tif dursor is on tif insfrt row, bnd
     * it siould bf dbllfd bfforf tif dursor movfs to bnotifr row.  If it is
     * dbllfd bftfr tif dursor movfs to bnotifr row, tiis mftiod ibs no ffffdt,
     * bnd tif updbtfs mbdf bfforf tif dursor movfd will bf lost.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row or tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRow() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Dflftfs tif durrfnt row from tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bnd
     * notififs listfnfrs rfgistfrfd witi tiis rowsft tibt b row ibs dibngfd.
     * Tiis mftiod dbnnot bf dbllfd wifn tif dursor is on tif insfrt row.
     * <P>
     * Tiis mftiod mbrks tif durrfnt row bs dflftfd, but it dofs not dflftf
     * tif row from tif undfrlying dbtb sourdf.  Tif mftiod
     * <dodf>bddfptCibngfs</dodf> must bf dbllfd to dflftf tif row in
     * tif dbtb sourdf.
     *
     * @tirows SQLExdfption if (1) tiis mftiod is dbllfd wifn tif dursor
     *            is on tif insfrt row, bfforf tif first row, or bftfr tif
     *            lbst row or (2) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void dflftfRow() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif durrfnt row witi its originbl vbluf bnd mbrks tif row bs
     * not updbtfd, tius undoing bny dibngfs mbdf to tif row sindf tif
     * lbst dbll to tif mftiods <dodf>updbtfRow</dodf> or <dodf>dflftfRow</dodf>.
     * Tiis mftiod siould bf dbllfd only wifn tif dursor is on b row in
     * tiis rowsft.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row, bfforf tif
     *            first row, or bftfr tif lbst row
     */
    publid void rffrfsiRow() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rolls bbdk bny updbtfs mbdf to tif durrfnt row of tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bnd notififs listfnfrs tibt
     * b row ibs dibngfd.  To ibvf bn ffffdt, tiis mftiod
     * must bf dbllfd bftfr bn <dodf>updbtfXXX</dodf> mftiod ibs bffn
     * dbllfd bnd bfforf tif mftiod <dodf>updbtfRow</dodf> ibs bffn dbllfd.
     * If no updbtfs ibvf bffn mbdf or tif mftiod <dodf>updbtfRow</dodf>
     * ibs blrfbdy bffn dbllfd, tiis mftiod ibs no ffffdt.
     *
     * @tirows SQLExdfption if tif dursor is on tif insfrt row, bfforf tif
     *            first row, or bftfr tif lbst row
     */
    publid void dbndflRowUpdbtfs() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Movfs tif dursor for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt to
     * tif durrfnt row.  Tif durrfnt row is tif row tif dursor wbs on
     * wifn tif mftiod <dodf>movfToInsfrtRow</dodf> wbs dbllfd.
     * <P>
     * Cblling tiis mftiod ibs no ffffdt unlfss it is dbllfd wiilf tif
     * dursor is on tif insfrt row.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void movfToCurrfntRow() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns <dodf>null</dodf>.
     *
     * @rfturn <dodf>null</dodf>
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid Stbtfmfnt gftStbtfmfnt() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Objfdt</dodf> in
     * tif Jbvb progrbmming lbngubgf, using tif givfn
     * <dodf>jbvb.util.Mbp</dodf> objfdt to dustom mbp tif vbluf if
     * bppropribtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt siowing tif mbpping
     *            from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming
     *            lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL vbluf
     * @tirows SQLExdfption if tif givfn dolumn indfx is out of bounds or
     *            tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row
     */
    publid Objfdt gftObjfdt(int dolumnIndfx,
                            jbvb.util.Mbp<String,Clbss<?>> mbp)
          tirows SQLExdfption
    {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL<dodf> REF</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>REF</dodf> vbluf
     * @sff #gftRff(String)
     */
    publid Rff gftRff(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting bn SQL <dodf>BLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>BLOB</dodf> vbluf
     * @sff #gftBlob(String)
     */
    publid Blob gftBlob(int dolumnIndfx) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting bn SQL <dodf>CLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>CLOB</dodf> vbluf
     * @sff #gftClob(String)
     */
    publid Clob gftClob(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tiis rowsft
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>ARRAY</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     *            SQL <dodf>ARRAY</dodf> vbluf
     * @sff #gftArrby(String)
     */
    publid Arrby gftArrby(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Objfdt</dodf> in
     * tif Jbvb progrbmming lbngubgf, using tif givfn
     * <dodf>jbvb.util.Mbp</dodf> objfdt to dustom mbp tif vbluf if
     * bppropribtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm mbp b <dodf>jbvb.util.Mbp</dodf> objfdt siowing tif mbpping
     *        from SQL typf nbmfs to dlbssfs in tif Jbvb progrbmming
     *        lbngubgf
     * @rfturn bn <dodf>Objfdt</dodf> rfprfsfnting tif SQL vbluf
     * @tirows SQLExdfption if tif givfn dolumn nbmf is not tif nbmf of
     *         b dolumn in tiis rowsft or tif dursor is not on onf of
     *         tiis rowsft's rows or its insfrt row
     */
    publid Objfdt gftObjfdt(String dolumnNbmf,
                            jbvb.util.Mbp<String,Clbss<?>> mbp)
    tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Rff</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Rff</dodf> objfdt rfprfsfnting bn SQL<dodf> REF</dodf> vbluf
     * @tirows SQLExdfption  if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dolumn vbluf
     *            is not bn SQL <dodf>REF</dodf> vbluf
     * @sff #gftRff(int)
     */
    publid Rff gftRff(String dolNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Blob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Blob</dodf> objfdt rfprfsfnting bn SQL <dodf>BLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>BLOB</dodf> vbluf
     * @sff #gftBlob(int)
     */
    publid Blob gftBlob(String dolNbmf) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>Clob</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn b <dodf>Clob</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>CLOB</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>CLOB</dodf> vbluf
     * @sff #gftClob(int)
     */
    publid Clob gftClob(String dolNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs bn <dodf>Arrby</dodf> objfdt
     * in tif Jbvb progrbmming lbngugbgf.
     *
     * @pbrbm dolNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @rfturn bn <dodf>Arrby</dodf> objfdt rfprfsfnting bn SQL
     *         <dodf>ARRAY</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>ARRAY</dodf> vbluf
     * @sff #gftArrby(int)
     */
    publid Arrby gftArrby(String dolNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Dbtf gftDbtf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Dbtf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Dbtf gftDbtf(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timf gftTimf(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timf</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timf gftTimf(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.sql.Timfstbmp</dodf>
     * objfdt, using tif givfn <dodf>Cblfndbr</dodf> objfdt to donstrudt bn
     * bppropribtf millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnIndfx tif first dolumn is <dodf>1</dodf>, tif sfdond
     *        is <dodf>2</dodf>, bnd so on; must bf <dodf>1</dodf> or lbrgfr
     *        bnd fqubl to or lfss tibn tif numbfr of dolumns in tif rowsft
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>TIME</dodf> or
     *            <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(int dolumnIndfx, Cblfndbr dbl) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tif durrfnt row
     * of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt bs b
     * <dodf>jbvb.sql.Timfstbmp</dodf> objfdt, using tif givfn
     * <dodf>Cblfndbr</dodf> objfdt to donstrudt bn bppropribtf
     * millisfdond vbluf for tif dbtf.
     *
     * @pbrbm dolumnNbmf b <dodf>String</dodf> objfdt tibt must mbtdi tif
     *        SQL nbmf of b dolumn in tiis rowsft, ignoring dbsf
     * @pbrbm dbl tif <dodf>jbvb.util.Cblfndbr</dodf> objfdt to usf in
     *            donstrudting tif dbtf
     * @rfturn tif dolumn vbluf; if tif vbluf is SQL <dodf>NULL</dodf>,
     *         tif rfsult is <dodf>null</dodf>
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf is not tif nbmf of
     *            b dolumn in tiis rowsft, (2) tif dursor is not on onf of
     *            tiis rowsft's rows or its insfrt row, or (3) tif dfsignbtfd
     *            dolumn dofs not storf bn SQL <dodf>DATE</dodf>,
     *            <dodf>TIME</dodf>, or <dodf>TIMESTAMP</dodf> vbluf
     */
    publid jbvb.sql.Timfstbmp gftTimfstbmp(String dolumnNbmf, Cblfndbr dbl) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /*
     * RowSftIntfrnbl Intfrfbdf
     */

    /**
     * Rftrifvfs tif <dodf>Connfdtion</dodf> objfdt pbssfd to tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt.  Tiis donnfdtion mby bf
     * usfd to populbtf tiis rowsft witi dbtb or to writf dbtb bbdk
     * to its undfrlying dbtb sourdf.
     *
     * @rfturn tif <dodf>Connfdtion</dodf> objfdt pbssfd to tiis rowsft;
     *         mby bf <dodf>null</dodf> if tifrf is no donnfdtion
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid Connfdtion gftConnfdtion() tirows SQLExdfption{
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif mftbdbtb for tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * witi tif givfn <dodf>RowSftMftbDbtb</dodf> objfdt.
     *
     * @pbrbm md b <dodf>RowSftMftbDbtb</dodf> objfdt instbndf dontbining
     *            mftbdbtb bbout tif dolumsn in tif rowsft
     * @tirows SQLExdfption if invblid mftb dbtb is supplifd to tif
     *            rowsft
     */
    publid void sftMftbDbtb(RowSftMftbDbtb md) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns b rfsult sft dontbining tif originbl vbluf of tif rowsft. Tif
     * originbl vbluf is tif stbtf of tif <dodf>CbdifdRowSftImpl</dodf> bftfr tif
     * lbst populbtion or syndironizbtion (wiidifvfr oddurrfd most rfdfntly) witi
     * tif dbtb sourdf.
     * <p>
     * Tif dursor is positionfd bfforf tif first row in tif rfsult sft.
     * Only rows dontbinfd in tif rfsult sft rfturnfd by <dodf>gftOriginbl()</dodf>
     * brf sbid to ibvf bn originbl vbluf.
     *
     * @rfturn tif originbl rfsult sft of tif rowsft
     * @tirows SQLExdfption if bn frror oddurs produdf tif
     *           <dodf>RfsultSft</dodf> objfdt
     */
    publid RfsultSft gftOriginbl() tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns b rfsult sft dontbining tif originbl vbluf of tif durrfnt
     * row only.
     * Tif originbl vbluf is tif stbtf of tif <dodf>CbdifdRowSftImpl</dodf> bftfr
     * tif lbst populbtion or syndironizbtion (wiidifvfr oddurrfd most rfdfntly)
     * witi tif dbtb sourdf.
     *
     * @rfturn tif originbl rfsult sft of tif row
     * @tirows SQLExdfption if tifrf is no durrfnt row
     * @sff #sftOriginblRow
     */
    publid RfsultSft gftOriginblRow() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();

    }

    /**
     * Mbrks tif durrfnt row in tiis rowsft bs bfing bn originbl row.
     *
     * @tirows SQLExdfption if tifrf is no durrfnt row
     * @sff #gftOriginblRow
     */
    publid void sftOriginblRow() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Mbrks bll rows in tiis rowsft bs bfing originbl rows. Any updbtfs
     * mbdf to tif rows bfdomf tif originbl vblufs for tif rowsft.
     * Cblls to tif mftiod <dodf>sftOriginbl</dodf> donnot bf rfvfrsfd.
     *
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void sftOriginbl() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns bn idfntififr for tif objfdt (tbblf) tibt wbs usfd to drfbtf tiis
     * rowsft.
     *
     * @rfturn b <dodf>String</dodf> objfdt tibt idfntififs tif tbblf from
     *         wiidi tiis <dodf>CbdifdRowSftImpl</dodf> objfdt wbs dfrivfd
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid String gftTbblfNbmf() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif idfntififr for tif tbblf from wiidi tiis rowsft wbs dfrivfd
     * to tif givfn tbblf nbmf.
     *
     * @pbrbm tbbNbmf b <dodf>String</dodf> objfdt tibt idfntififs tif
     *          tbblf from wiidi tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     *          wbs dfrivfd
     * @tirows SQLExdfption if bn frror oddurs
     */
    publid void sftTbblfNbmf(String tbbNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rfturns tif dolumns tibt mbkf b kfy to uniqufly idfntify b
     * row in tiis <dodf>CbdifdRowSftImpl</dodf> objfdt.
     *
     * @rfturn bn brrby of dolumn numbfrs tibt donstitutfs b primbry
     *           kfy for tiis rowsft. Tiis brrby siould bf fmpty
     *           if no dolumn is rfprfsfntitivf of b primbry kfy
     * @tirows SQLExdfption if tif rowsft is fmpty or no dolumns
     *           brf dfsignbtfd bs primbry kfys
     * @sff #sftKfyColumns
     */
    publid int[] gftKfyColumns() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Sfts tiis <dodf>CbdifdRowSftImpl</dodf> objfdt's
     * <dodf>kfyCols</dodf> fifld witi tif givfn brrby of dolumn
     * numbfrs, wiidi forms b kfy for uniqufly idfntifying b row
     * in tiis rowsft.
     *
     * @pbrbm kfys bn brrby of <dodf>int</dodf> indidbting tif
     *        dolumns tibt form b primbry kfy for tiis
     *        <dodf>CbdifdRowSftImpl</dodf> objfdt; fvfry
     *        flfmfnt in tif brrby must bf grfbtfr tibn
     *        <dodf>0</dodf> bnd lfss tibn or fqubl to tif numbfr
     *        of dolumns in tiis rowsft
     * @tirows SQLExdfption if bny of tif numbfrs in tif
     *            givfn brrby is not vblid for tiis rowsft
     * @sff #gftKfyColumns
     */
    publid void sftKfyColumns(int [] kfys) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
     * @pbrbm rff tif nfw dolumn <dodf>jbvb.sql.Rff</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *        (2) tif dursor is not on onf of tiis rowsft's rows or its
     *        insfrt row, or (3) tiis rowsft is
     *        <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(int dolumnIndfx, jbvb.sql.Rff rff) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
     * @pbrbm rff tif nfw dolumn <dodf>jbvb.sql.Rff</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *        nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *        onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *        rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfRff(String dolumnNbmf, jbvb.sql.Rff rff) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
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
     * @pbrbm d tif nfw dolumn <dodf>Clob vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *        (2) tif dursor is not on onf of tiis rowsft's rows or its
     *        insfrt row, or (3) tiis rowsft is
     *        <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(int dolumnIndfx, Clob d) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
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
     * @pbrbm d tif nfw dolumn <dodf>Clob</dodf>vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfClob(String dolumnNbmf, Clob d) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Blob</dodf> vbluf.
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
     * @pbrbm b tif nfw dolumn <dodf>Blob</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBlob(int dolumnIndfx, Blob b) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Blob </dodf> vbluf.
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
     * @pbrbm b tif nfw dolumn <dodf>Blob</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfBlob(String dolumnNbmf, Blob b) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Arrby</dodf> vblufs.
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
     * @pbrbm b tif nfw dolumn <dodf>Arrby</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     *            (2) tif dursor is not on onf of tiis rowsft's rows or its
     *            insfrt row, or (3) tiis rowsft is
     *            <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfArrby(int dolumnIndfx, Arrby b) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd dolumn in fitifr tif durrfnt row or tif insfrt
     * row of tiis <dodf>CbdifdRowSftImpl</dodf> objfdt witi tif givfn
     * <dodf>jbvb.sql.Arrby</dodf> vbluf.
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
     * @pbrbm b tif nfw dolumn <dodf>Arrby</dodf> vbluf
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf dofs not mbtdi tif
     *            nbmf of b dolumn in tiis rowsft, (2) tif dursor is not on
     *            onf of tiis rowsft's rows or its insfrt row, or (3) tiis
     *            rowsft is <dodf>RfsultSft.CONCUR_READ_ONLY</dodf>
     */
    publid void updbtfArrby(String dolumnNbmf, Arrby b) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.nft.URL</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b jbvb.nft.URL objfdt dontbining tif rfsourdf rfffrfndf dfsdribfd by
     * tif URL
     * @tirows SQLExdfption if (1) tif givfn dolumn indfx is out of bounds,
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>DATALINK</dodf> vbluf.
     * @sff #gftURL(String)
     */
    publid jbvb.nft.URL gftURL(int dolumnIndfx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif vbluf of tif dfsignbtfd dolumn in tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt bs b <dodf>jbvb.nft.URL</dodf> objfdt
     * in tif Jbvb progrbmming lbngubgf.
     *
     * @rfturn b jbvb.nft.URL objfdt dontbining tif rfsourdf rfffrfndf dfsdribfd by
     * tif URL
     * @tirows SQLExdfption if (1) tif givfn dolumn nbmf not tif nbmf of b dolumn
     * in tiis rowsft, or
     * (2) tif dursor is not on onf of tiis rowsft's rows or its
     * insfrt row, or (3) tif dfsignbtfd dolumn dofs not storf bn
     * SQL <dodf>DATALINK</dodf> vbluf.
     * @sff #gftURL(int)
     */
    publid jbvb.nft.URL gftURL(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();

    }

    /**
     * Tif first wbrning rfportfd by dblls on tiis <dodf>CbdifdRowSftImpl</dodf>
     * objfdt is rfturnfd. Subsfqufnt <dodf>CbdifdRowSftImpl</dodf> wbrnings will
     * bf dibinfd to tiis <dodf>SQLWbrning</dodf>. All <dodf>RowSftWbrnings</dodf>
     * wbrnings brf gfnfrbtfd in tif disdonnfdtfd fnvironmfnt bnd rfmbin b
     * sfpfrbtf wbrning dibin to tibt providfd by tif <dodf>gftWbrnings</dodf>
     * mftiod.
     *
     * <P>Tif wbrning dibin is butombtidblly dlfbrfd fbdi timf b nfw
     * row is rfbd.
     *
     * <P><B>Notf:</B> Tiis wbrning dibin only dovfrs wbrnings dbusfd
     * by <dodf>CbdifdRowSft</dodf> (bnd tifir diild intfrfbdf)
     * mftiods. All <dodf>SQLWbrnings</dodf> dbn bf obtbinfd using tif
     * <dodf>gftWbrnings</dodf> mftiod wiidi trbdks wbrnings gfnfrbtfd
     * by tif undfrlying JDBC drivfr.
     * @rfturn tif first SQLWbrning or null
     *
     */
    publid RowSftWbrning gftRowSftWbrnings() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

     /**
     * Commits bll dibngfs pfrformfd by tif <dodf>bddfptCibngfs()</dodf>
     * mftiods
     *
     * @sff jbvb.sql.Connfdtion#dommit
     */
    publid void dommit() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rolls bbdk bll dibngfs pfrformfd by tif <dodf>bddfptCibngfs()</dodf>
     * mftiods
     *
     * @sff jbvb.sql.Connfdtion#rollbbdk
     */
    publid void rollbbdk() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rolls bbdk bll dibngfs pfrformfd by tif <dodf>bddfptCibngfs()</dodf>
     * to tif lbst <dodf>Sbvfpoint</dodf> trbnsbdtion mbrkfr.
     *
     * @sff jbvb.sql.Connfdtion#rollbbdk(Sbvfpoint)
     */
    publid void rollbbdk(Sbvfpoint s) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn int brrby.
     * Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnIdxfs tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnIdx is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(int [])</dodf>
     */
    publid void unsftMbtdiColumn(int[] dolumnIdxfs) tirows SQLExdfption {
         tirow nfw UnsupportfdOpfrbtionExdfption();
    }

   /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn String brrby.
     * Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnIdxfs tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnNbmf is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(String [])</dodf>
     */
    publid void unsftMbtdiColumn(String[] dolumnIdxfs) tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif dolumn nbmf bs <dodf>String</dodf> brrby
     * tibt wbs sft using <dodf>sftMbtdiColumn(String [])</dodf>
     * for tiis rowsft.
     *
     * @rfturn b <dodf>String</dodf> brrby objfdt tibt dontbins tif dolumn nbmfs
     *         for tif rowsft wiidi ibs tiis tif mbtdi dolumns
     *
     * @tirows SQLExdfption if bn frror oddurs or dolumn nbmf is not sft
     */
    publid String[] gftMbtdiColumnNbmfs() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Rftrifvfs tif dolumn id bs <dodf>int</dodf> brrby tibt wbs sft using
     * <dodf>sftMbtdiColumn(int [])</dodf> for tiis rowsft.
     *
     * @rfturn b <dodf>int</dodf> brrby objfdt tibt dontbins tif dolumn ids
     *         for tif rowsft wiidi ibs tiis bs tif mbtdi dolumns.
     *
     * @tirows SQLExdfption if bn frror oddurs or dolumn indfx is not sft
     */
    publid int[] gftMbtdiColumnIndfxfs() tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn int brrby.
     * Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumnIndfxfs</dodf> is dbllfd.
     *
     * @pbrbm dolumnIdxfs tif indfxfs into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs; tif
     *        first pbrbmftfr is 0, tif sfdond is 1, bnd so on; must bf
     *        <dodf>0</dodf> or grfbtfr
     * @tirows SQLExdfption if bn frror oddurs or tif
     *                         pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(int[] dolumnIdxfs) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn String brrby.
     *  Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumn</dodf> is dbllfd.
     *
     * @pbrbm dolumnNbmfs tif nbmf of tif dolumn into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(String[] dolumnNbmfs) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>int</dodf>
     * objfdt.  Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumn</dodf> is dbllfd.
     *
     * @pbrbm dolumnIdx tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs; tif
     *        first pbrbmftfr is 0, tif sfdond is 1, bnd so on; must bf
     *        <dodf>0</dodf> or grfbtfr
     * @tirows SQLExdfption if bn frror oddurs or tif
     *                         pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(int dolumnIdx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Sfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf>
     * objfdt.  Tiis forms tif bbsis of tif join for tif
     * <dodf>JoinRowSft</dodf> bs tif dolumn wiidi will form tif bbsis of tif
     * join.
     * <P>
     * Tif pbrbmftfr vbluf sft by tiis mftiod is storfd intfrnblly bnd
     * will bf supplifd bs tif bppropribtf pbrbmftfr in tiis rowsft's
     * dommbnd wifn tif mftiod <dodf>gftMbtdiColumn</dodf> is dbllfd.
     *
     * @pbrbm dolumnNbmf tif nbmf of tif dolumn into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds
     */
    publid void sftMbtdiColumn(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>int</dodf>
     * objfdt.  Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnIdx tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnIdx is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(int)</dodf>
     */
    publid void unsftMbtdiColumn(int dolumnIdx) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Unsfts tif dfsignbtfd pbrbmftfr to tif givfn <dodf>String</dodf>
     * objfdt.  Tiis wbs sft using <dodf>sftMbtdiColumn</dodf>
     * bs tif dolumn wiidi will form tif bbsis of tif join.
     * <P>
     * Tif pbrbmftfr vbluf unsft by tiis mftiod siould bf sbmf
     * bs wbs sft.
     *
     * @pbrbm dolumnNbmf tif indfx into tiis rowsft
     *        objfdt's intfrnbl rfprfsfntbtion of pbrbmftfr vblufs
     * @tirows SQLExdfption if bn frror oddurs or tif
     *  pbrbmftfr indfx is out of bounds or if tif dolumnNbmf is
     *  not tif sbmf bs sft using <dodf>sftMbtdiColumn(String)</dodf>
     */
    publid void unsftMbtdiColumn(String dolumnNbmf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Notififs rfgistfrfd listfnfrs tibt b RowSft objfdt in tif givfn RowSftEvfnt
     * objfdt ibs populbtfd b numbfr of bdditionbl rows. Tif <dodf>numRows</dodf> pbrbmftfr
     * fnsurfs tibt tiis fvfnt will only bf firfd fvfry <dodf>numRow</dodf>.
     * <p>
     * Tif sourdf of tif fvfnt dbn bf rftrifvfd witi tif mftiod fvfnt.gftSourdf.
     *
     * @pbrbm fvfnt b <dodf>RowSftEvfnt</dodf> objfdt tibt dontbins tif
     *     <dodf>RowSft</dodf> objfdt tibt is tif sourdf of tif fvfnts
     * @pbrbm numRows wifn populbting, tif numbfr of rows intfrvbl on wiidi tif
     *     <dodf>CbdifdRowSft</dodf> populbtfd siould firf; tif dffbult vbluf
     *     is zfro; dbnnot bf lfss tibn <dodf>fftdiSizf</dodf> or zfro
     */
    publid void rowSftPopulbtfd(RowSftEvfnt fvfnt, int numRows) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
     * Populbtfs tiis <dodf>CbdifdRowSft</dodf> objfdt witi dbtb from
     * tif givfn <dodf>RfsultSft</dodf> objfdt. Wiilf rflbtfd to tif <dodf>populbtf(RfsultSft)</dodf>
     * mftiod, bn bdditionbl pbrbmftfr is providfd to bllow stbrting position witiin
     * tif <dodf>RfsultSft</dodf> from wifrf to populbtf tif CbdifdRowSft
     * instbndf.
     *
     * Tiis mftiod is bn bltfrnbtivf to tif mftiod <dodf>fxfdutf</dodf>
     * for filling tif rowsft witi dbtb.  Tif mftiod <dodf>populbtf</dodf>
     * dofs not rfquirf tibt tif propfrtifs nffdfd by tif mftiod
     * <dodf>fxfdutf</dodf>, sudi bs tif <dodf>dommbnd</dodf> propfrty,
     * bf sft. Tiis is truf bfdbusf tif mftiod <dodf>populbtf</dodf>
     * is givfn tif <dodf>RfsultSft</dodf> objfdt from
     * wiidi to gft dbtb bnd tius dofs not nffd to usf tif propfrtifs
     * rfquirfd for sftting up b donnfdtion bnd fxfduting tiis
     * <dodf>CbdifdRowSftImpl</dodf> objfdt's dommbnd.
     * <P>
     * Aftfr populbting tiis rowsft witi dbtb, tif mftiod
     * <dodf>populbtf</dodf> sfts tif rowsft's mftbdbtb bnd
     * tifn sfnds b <dodf>RowSftCibngfdEvfnt</dodf> objfdt
     * to bll rfgistfrfd listfnfrs prior to rfturning.
     *
     * @pbrbm dbtb tif <dodf>RfsultSft</dodf> objfdt dontbining tif dbtb
     *             to bf rfbd into tiis <dodf>CbdifdRowSftImpl</dodf> objfdt
     * @pbrbm stbrt tif intfgfr spfdifing tif position in tif
     *        <dodf>RfsultSft</dodf> objfdt to popultbtf tif
     *        <dodf>CbdifdRowSftImpl</dodf> objfdt.
     * @tirows SQLExdfption if bn frror oddurs; or tif mbx row sftting is
     *          violbtfd wiilf populbting tif RowSft.Also id tif stbrt position
     *          is nfgbtivf.
     * @sff #fxfdutf
     */
     publid void populbtf(RfsultSft dbtb, int stbrt) tirows SQLExdfption{
        tirow nfw UnsupportfdOpfrbtionExdfption();

     }

    /**
     * Tif nfxtPbgf gfts tif nfxt pbgf, tibt is b <dodf>CbdifdRowSftImpl</dodf> objfdt
     * dontbining tif numbfr of rows spfdififd by pbgf sizf.
     * @rfturn boolfbn vbluf truf indidbting wiftifr tifrf brf morf pbgfs to domf bnd
     *         fblsf indidbting tibt tiis is tif lbst pbgf.
     * @tirows SQLExdfption if bn frror oddurs or tiis dbllfd bfforf dblling populbtf.
     */
     publid boolfbn nfxtPbgf() tirows SQLExdfption {
         tirow nfw UnsupportfdOpfrbtionExdfption();
     }

    /**
     * Tiis is tif sfttfr fundtion for sftting tif sizf of tif pbgf, wiidi spfdififs
     * iow mbny rows ibvf to bf rftrivfd bt b timf.
     *
     * @pbrbm sizf wiidi is tif pbgf sizf
     * @tirows SQLExdfption if sizf is lfss tibn zfro or grfbtfr tibn mbx rows.
     */
     publid void sftPbgfSizf (int sizf) tirows SQLExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption();
     }

    /**
     * Tiis is tif gfttfr fundtion for tif sizf of tif pbgf.
     *
     * @rfturn bn intfgfr tibt is tif pbgf sizf.
     */
    publid int gftPbgfSizf() {
        tirow nfw UnsupportfdOpfrbtionExdfption();
    }


    /**
     * Rftrifvfs tif dbtb prfsfnt in tif pbgf prior to tif pbgf from wifrf it is
     * dbllfd.
     * @rfturn boolfbn vbluf truf if it rftrifvfs tif prfvious pbgf, flbsf if it
     *         is on tif first pbgf.
     * @tirows SQLExdfption if it is dbllfd bfforf populbtf is dbllfd or RfsultSft
     *         is of typf <dodf>RfsultSft.TYPE_FORWARD_ONLY</dodf> or if bn frror
     *         oddurs.
     */
    publid boolfbn prfviousPbgf() tirows SQLExdfption {
       tirow nfw UnsupportfdOpfrbtionExdfption();
    }

    /**
       * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will
       * ibvf tif spfdififd numbfr of bytfs. Tif drivfr dofs tif nfdfssbry donvfrsion
       * from Jbvb dibrbdtfr formbt to tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
       * It is intfndfd for usf wifn updbting NCHAR,NVARCHAR bnd LONGNVARCHAR dolumns.
       * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or
       * tif insfrt row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf;
       * instfbd tif updbtfRow or insfrtRow mftiods brf dbllfd to updbtf tif dbtbbbsf.
       *
       * @pbrbm dolumnIndfx - tif first dolumn is 1, tif sfdond is 2, ...
       * @pbrbm x - tif nfw dolumn vbluf
       * @pbrbm lfngti - tif lfngti of tif strfbm
       * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
       * @sindf 1.6
       */
       publid void updbtfNCibrbdtfrStrfbm(int dolumnIndfx,
                            jbvb.io.Rfbdfr x,
                            int lfngti)
                            tirows SQLExdfption {
          tirow nfw UnsupportfdOpfrbtionExdfption("Opfrbtion not yft supportfd");
       }

     /**
       * Updbtfs tif dfsignbtfd dolumn witi b dibrbdtfr strfbm vbluf, wiidi will
       * ibvf tif spfdififd numbfr of bytfs. Tif drivfr dofs tif nfdfssbry donvfrsion
       * from Jbvb dibrbdtfr formbt to tif nbtionbl dibrbdtfr sft in tif dbtbbbsf.
       * It is intfndfd for usf wifn updbting NCHAR,NVARCHAR bnd LONGNVARCHAR dolumns.
       * Tif updbtfr mftiods brf usfd to updbtf dolumn vblufs in tif durrfnt row or
       * tif insfrt row. Tif updbtfr mftiods do not updbtf tif undfrlying dbtbbbsf;
       * instfbd tif updbtfRow or insfrtRow mftiods brf dbllfd to updbtf tif dbtbbbsf.
       *
       * @pbrbm dolumnNbmf - nbmf of tif Column
       * @pbrbm x - tif nfw dolumn vbluf
       * @pbrbm lfngti - tif lfngti of tif strfbm
       * @fxdfption SQLExdfption if b dbtbbbsf bddfss frror oddurs
       * @sindf 1.6
       */
       publid void updbtfNCibrbdtfrStrfbm(String dolumnNbmf,
                            jbvb.io.Rfbdfr x,
                            int lfngti)
                            tirows SQLExdfption {
          tirow nfw UnsupportfdOpfrbtionExdfption("Opfrbtion not yft supportfd");
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

       stbtid finbl long sfriblVfrsionUID = -3345004441725080251L;
} //fnd dlbss
