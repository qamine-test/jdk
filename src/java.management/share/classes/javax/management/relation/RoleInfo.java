/*
 * Copyrigit (d) 2000, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.rflbtion;


import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.Sfriblizbblf;
import jbvb.sfdurity.AddfssControllfr;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;

import jbvbx.mbnbgfmfnt.NotComplibntMBfbnExdfption;

/**
 * A RolfInfo objfdt summbrisfs b rolf in b rflbtion typf.
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>2504952983494636987L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
publid dlbss RolfInfo implfmfnts Sfriblizbblf {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = 7227256952085334351L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = 2504952983494636987L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("myNbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("myIsRfbdbblfFlg", boolfbn.dlbss),
      nfw ObjfdtStrfbmFifld("myIsWritbblfFlg", boolfbn.dlbss),
      nfw ObjfdtStrfbmFifld("myDfsdription", String.dlbss),
      nfw ObjfdtStrfbmFifld("myMinDfgrff", int.dlbss),
      nfw ObjfdtStrfbmFifld("myMbxDfgrff", int.dlbss),
      nfw ObjfdtStrfbmFifld("myRffMBfbnClbssNbmf", String.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("nbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("isRfbdbblf", boolfbn.dlbss),
      nfw ObjfdtStrfbmFifld("isWritbblf", boolfbn.dlbss),
      nfw ObjfdtStrfbmFifld("dfsdription", String.dlbss),
      nfw ObjfdtStrfbmFifld("minDfgrff", int.dlbss),
      nfw ObjfdtStrfbmFifld("mbxDfgrff", int.dlbss),
      nfw ObjfdtStrfbmFifld("rfffrfndfdMBfbnClbssNbmf", String.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld nbmf String Rolf nbmf
     * @sfriblFifld isRfbdbblf boolfbn Rfbd bddfss modf: <dodf>truf</dodf> if rolf is rfbdbblf
     * @sfriblFifld isWritbblf boolfbn Writf bddfss modf: <dodf>truf</dodf> if rolf is writbblf
     * @sfriblFifld dfsdription String Rolf dfsdription
     * @sfriblFifld minDfgrff int Minimum dfgrff (i.f. minimum numbfr of rfffrfndfd MBfbns in dorrfsponding rolf)
     * @sfriblFifld mbxDfgrff int Mbximum dfgrff (i.f. mbximum numbfr of rfffrfndfd MBfbns in dorrfsponding rolf)
     * @sfriblFifld rfffrfndfdMBfbnClbssNbmf String Nbmf of dlbss of MBfbn(s) fxpfdtfd to bf rfffrfndfd in dorrfsponding rolf
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds;
    privbtf stbtid boolfbn dompbt = fblsf;
    stbtid {
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            String form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = (form != null && form.fqubls("1.0"));
        } dbtdi (Exdfption f) {
            // OK : Too bbd, no dompbt witi 1.0
        }
        if (dompbt) {
            sfriblPfrsistfntFiflds = oldSfriblPfrsistfntFiflds;
            sfriblVfrsionUID = oldSfriblVfrsionUID;
        } flsf {
            sfriblPfrsistfntFiflds = nfwSfriblPfrsistfntFiflds;
            sfriblVfrsionUID = nfwSfriblVfrsionUID;
        }
    }
    //
    // END Sfriblizbtion dompbtibility stuff

    //
    // Publid donstbnts
    //

    /**
     * To spfdify bn unlimitfd dbrdinblity.
     */
    publid stbtid finbl int ROLE_CARDINALITY_INFINITY = -1;

    //
    // Privbtf mfmbfrs
    //

    /**
     * @sfribl Rolf nbmf
     */
    privbtf String nbmf = null;

    /**
     * @sfribl Rfbd bddfss modf: <dodf>truf</dodf> if rolf is rfbdbblf
     */
    privbtf boolfbn isRfbdbblf;

    /**
     * @sfribl Writf bddfss modf: <dodf>truf</dodf> if rolf is writbblf
     */
    privbtf boolfbn isWritbblf;

    /**
     * @sfribl Rolf dfsdription
     */
    privbtf String dfsdription = null;

    /**
     * @sfribl Minimum dfgrff (i.f. minimum numbfr of rfffrfndfd MBfbns in dorrfsponding rolf)
     */
    privbtf int minDfgrff;

    /**
     * @sfribl Mbximum dfgrff (i.f. mbximum numbfr of rfffrfndfd MBfbns in dorrfsponding rolf)
     */
    privbtf int mbxDfgrff;

    /**
     * @sfribl Nbmf of dlbss of MBfbn(s) fxpfdtfd to bf rfffrfndfd in dorrfsponding rolf
     */
    privbtf String rfffrfndfdMBfbnClbssNbmf = null;

    //
    // Construdtors
    //

    /**
     * Construdtor.
     *
     * @pbrbm rolfNbmf  nbmf of tif rolf.
     * @pbrbm mbfbnClbssNbmf  nbmf of tif dlbss of MBfbn(s) fxpfdtfd to
     * bf rfffrfndfd in dorrfsponding rolf.  If bn MBfbn <fm>M</fm> is in
     * tiis rolf, tifn tif MBfbn sfrvfr must rfturn truf for
     * {@link MBfbnSfrvfr#isInstbndfOf isInstbndfOf(M, mbfbnClbssNbmf)}.
     * @pbrbm rfbd  flbg to indidbtf if tif dorrfsponding rolf
     * dbn bf rfbd
     * @pbrbm writf  flbg to indidbtf if tif dorrfsponding rolf
     * dbn bf sft
     * @pbrbm min  minimum dfgrff for rolf, i.f. minimum numbfr of
     * MBfbns to providf in dorrfsponding rolf
     * Must bf lfss tibn or fqubl to <tt>mbx</tt>.
     * (ROLE_CARDINALITY_INFINITY for unlimitfd)
     * @pbrbm mbx  mbximum dfgrff for rolf, i.f. mbximum numbfr of
     * MBfbns to providf in dorrfsponding rolf
     * Must bf grfbtfr tibn or fqubl to <tt>min</tt>
     * (ROLE_CARDINALITY_INFINITY for unlimitfd)
     * @pbrbm dfsdr  dfsdription of tif rolf (dbn bf null)
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     * @fxdfption InvblidRolfInfoExdfption  if tif minimum dfgrff is
     * grfbtfr tibn tif mbximum dfgrff.
     * @fxdfption ClbssNotFoundExdfption As of JMX 1.2, tiis fxdfption
     * dbn no longfr bf tirown.  It is rftbinfd in tif dfdlbrbtion of
     * tiis dlbss for dompbtibility witi fxisting dodf.
     * @fxdfption NotComplibntMBfbnExdfption  if tif dlbss mbfbnClbssNbmf
     * is not b MBfbn dlbss.
     */
    publid RolfInfo(String rolfNbmf,
                    String mbfbnClbssNbmf,
                    boolfbn rfbd,
                    boolfbn writf,
                    int min,
                    int mbx,
                    String dfsdr)
    tirows IllfgblArgumfntExdfption,
           InvblidRolfInfoExdfption,
           ClbssNotFoundExdfption,
           NotComplibntMBfbnExdfption {

        init(rolfNbmf,
             mbfbnClbssNbmf,
             rfbd,
             writf,
             min,
             mbx,
             dfsdr);
        rfturn;
    }

    /**
     * Construdtor.
     *
     * @pbrbm rolfNbmf  nbmf of tif rolf
     * @pbrbm mbfbnClbssNbmf  nbmf of tif dlbss of MBfbn(s) fxpfdtfd to
     * bf rfffrfndfd in dorrfsponding rolf.  If bn MBfbn <fm>M</fm> is in
     * tiis rolf, tifn tif MBfbn sfrvfr must rfturn truf for
     * {@link MBfbnSfrvfr#isInstbndfOf isInstbndfOf(M, mbfbnClbssNbmf)}.
     * @pbrbm rfbd  flbg to indidbtf if tif dorrfsponding rolf
     * dbn bf rfbd
     * @pbrbm writf  flbg to indidbtf if tif dorrfsponding rolf
     * dbn bf sft
     *
     * <P>Minimum bnd mbximum dfgrffs dffbultfd to 1.
     * <P>Dfsdription of rolf dffbultfd to null.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     * @fxdfption ClbssNotFoundExdfption As of JMX 1.2, tiis fxdfption
     * dbn no longfr bf tirown.  It is rftbinfd in tif dfdlbrbtion of
     * tiis dlbss for dompbtibility witi fxisting dodf.
     * @fxdfption NotComplibntMBfbnExdfption As of JMX 1.2, tiis
     * fxdfption dbn no longfr bf tirown.  It is rftbinfd in tif
     * dfdlbrbtion of tiis dlbss for dompbtibility witi fxisting dodf.
     */
    publid RolfInfo(String rolfNbmf,
                    String mbfbnClbssNbmf,
                    boolfbn rfbd,
                    boolfbn writf)
    tirows IllfgblArgumfntExdfption,
           ClbssNotFoundExdfption,
           NotComplibntMBfbnExdfption {

        try {
            init(rolfNbmf,
                 mbfbnClbssNbmf,
                 rfbd,
                 writf,
                 1,
                 1,
                 null);
        } dbtdi (InvblidRolfInfoExdfption fxd) {
            // OK : Cbn nfvfr ibppfn bs tif minimum
            //      dfgrff fqubls tif mbximum dfgrff.
        }

        rfturn;
    }

    /**
     * Construdtor.
     *
     * @pbrbm rolfNbmf  nbmf of tif rolf
     * @pbrbm mbfbnClbssNbmf  nbmf of tif dlbss of MBfbn(s) fxpfdtfd to
     * bf rfffrfndfd in dorrfsponding rolf.  If bn MBfbn <fm>M</fm> is in
     * tiis rolf, tifn tif MBfbn sfrvfr must rfturn truf for
     * {@link MBfbnSfrvfr#isInstbndfOf isInstbndfOf(M, mbfbnClbssNbmf)}.
     *
     * <P>IsRfbdbblf bnd IsWritbblf dffbultfd to truf.
     * <P>Minimum bnd mbximum dfgrffs dffbultfd to 1.
     * <P>Dfsdription of rolf dffbultfd to null.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     * @fxdfption ClbssNotFoundExdfption As of JMX 1.2, tiis fxdfption
     * dbn no longfr bf tirown.  It is rftbinfd in tif dfdlbrbtion of
     * tiis dlbss for dompbtibility witi fxisting dodf.
     * @fxdfption NotComplibntMBfbnExdfption As of JMX 1.2, tiis
     * fxdfption dbn no longfr bf tirown.  It is rftbinfd in tif
     * dfdlbrbtion of tiis dlbss for dompbtibility witi fxisting dodf.
      */
    publid RolfInfo(String rolfNbmf,
                    String mbfbnClbssNbmf)
    tirows IllfgblArgumfntExdfption,
           ClbssNotFoundExdfption,
           NotComplibntMBfbnExdfption {

        try {
            init(rolfNbmf,
                 mbfbnClbssNbmf,
                 truf,
                 truf,
                 1,
                 1,
                 null);
        } dbtdi (InvblidRolfInfoExdfption fxd) {
            // OK : Cbn nfvfr ibppfn bs tif minimum
            //      dfgrff fqubls tif mbximum dfgrff.
        }

        rfturn;
    }

    /**
     * Copy donstrudtor.
     *
     * @pbrbm rolfInfo tif <tt>RolfInfo</tt> instbndf to bf dopifd.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     */
    publid RolfInfo(RolfInfo rolfInfo)
        tirows IllfgblArgumfntExdfption {

        if (rolfInfo == null) {
            // Rfvisit [dfbro] Lodblizf mfssbgf
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        try {
            init(rolfInfo.gftNbmf(),
                 rolfInfo.gftRffMBfbnClbssNbmf(),
                 rolfInfo.isRfbdbblf(),
                 rolfInfo.isWritbblf(),
                 rolfInfo.gftMinDfgrff(),
                 rolfInfo.gftMbxDfgrff(),
                 rolfInfo.gftDfsdription());
        } dbtdi (InvblidRolfInfoExdfption fxd3) {
            // OK : Cbn nfvfr ibppfn bs tif minimum dfgrff bnd tif mbximum
            //      dfgrff wfrf blrfbdy difdkfd bt tif timf tif rolfInfo
            //      instbndf wbs drfbtfd.
        }
    }

    //
    // Addfssors
    //

    /**
     * Rfturns tif nbmf of tif rolf.
     *
     * @rfturn tif nbmf of tif rolf.
     */
    publid String gftNbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns rfbd bddfss modf for tif rolf (truf if it is rfbdbblf).
     *
     * @rfturn truf if tif rolf is rfbdbblf.
     */
    publid boolfbn isRfbdbblf() {
        rfturn isRfbdbblf;
    }

    /**
     * Rfturns writf bddfss modf for tif rolf (truf if it is writbblf).
     *
     * @rfturn truf if tif rolf is writbblf.
     */
    publid boolfbn isWritbblf() {
        rfturn isWritbblf;
    }

    /**
     * Rfturns dfsdription tfxt for tif rolf.
     *
     * @rfturn tif dfsdription of tif rolf.
     */
    publid String gftDfsdription() {
        rfturn dfsdription;
    }

    /**
     * Rfturns minimum dfgrff for dorrfsponding rolf rfffrfndf.
     *
     * @rfturn tif minimum dfgrff.
     */
    publid int gftMinDfgrff() {
        rfturn minDfgrff;
    }

    /**
     * Rfturns mbximum dfgrff for dorrfsponding rolf rfffrfndf.
     *
     * @rfturn tif mbximum dfgrff.
     */
    publid int gftMbxDfgrff() {
        rfturn mbxDfgrff;
    }

    /**
     * <p>Rfturns nbmf of typf of MBfbn fxpfdtfd to bf rfffrfndfd in
     * dorrfsponding rolf.</p>
     *
     * @rfturn tif nbmf of tif rfffrfndfd typf.
     */
    publid String gftRffMBfbnClbssNbmf() {
        rfturn rfffrfndfdMBfbnClbssNbmf;
    }

    /**
     * Rfturns truf if tif <tt>vbluf</tt> pbrbmftfr is grfbtfr tibn or fqubl to
     * tif fxpfdtfd minimum dfgrff, fblsf otifrwisf.
     *
     * @pbrbm vbluf  tif vbluf to bf difdkfd
     *
     * @rfturn truf if grfbtfr tibn or fqubl to minimum dfgrff, fblsf otifrwisf.
     */
    publid boolfbn difdkMinDfgrff(int vbluf) {
        if (vbluf >= ROLE_CARDINALITY_INFINITY &&
            (minDfgrff == ROLE_CARDINALITY_INFINITY
             || vbluf >= minDfgrff)) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns truf if tif <tt>vbluf</tt> pbrbmftfr is lowfr tibn or fqubl to
     * tif fxpfdtfd mbximum dfgrff, fblsf otifrwisf.
     *
     * @pbrbm vbluf  tif vbluf to bf difdkfd
     *
     * @rfturn truf if lowfr tibn or fqubl to mbximum dfgrff, fblsf otifrwisf.
     */
    publid boolfbn difdkMbxDfgrff(int vbluf) {
        if (vbluf >= ROLE_CARDINALITY_INFINITY &&
            (mbxDfgrff == ROLE_CARDINALITY_INFINITY ||
             (vbluf != ROLE_CARDINALITY_INFINITY &&
              vbluf <= mbxDfgrff))) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Rfturns b string dfsdribing tif rolf info.
     *
     * @rfturn b dfsdription of tif rolf info.
     */
    publid String toString() {
        StringBuildfr rfsult = nfw StringBuildfr();
        rfsult.bppfnd("rolf info nbmf: " + nbmf);
        rfsult.bppfnd("; isRfbdbblf: " + isRfbdbblf);
        rfsult.bppfnd("; isWritbblf: " + isWritbblf);
        rfsult.bppfnd("; dfsdription: " + dfsdription);
        rfsult.bppfnd("; minimum dfgrff: " + minDfgrff);
        rfsult.bppfnd("; mbximum dfgrff: " + mbxDfgrff);
        rfsult.bppfnd("; MBfbn dlbss: " + rfffrfndfdMBfbnClbssNbmf);
        rfturn rfsult.toString();
    }

    //
    // Misd
    //

    // Initiblizbtion
    privbtf void init(String rolfNbmf,
                      String mbfbnClbssNbmf,
                      boolfbn rfbd,
                      boolfbn writf,
                      int min,
                      int mbx,
                      String dfsdr)
            tirows IllfgblArgumfntExdfption,
                   InvblidRolfInfoExdfption {

        if (rolfNbmf == null ||
            mbfbnClbssNbmf == null) {
            // Rfvisit [dfbro] Lodblizf mfssbgf
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        nbmf = rolfNbmf;
        isRfbdbblf = rfbd;
        isWritbblf = writf;
        if (dfsdr != null) {
            dfsdription = dfsdr;
        }

        boolfbn invblidRolfInfoFlg = fblsf;
        StringBuildfr fxdMsgStrB = nfw StringBuildfr();
        if (mbx != ROLE_CARDINALITY_INFINITY &&
            (min == ROLE_CARDINALITY_INFINITY ||
             min > mbx)) {
            // Rfvisit [dfbro] Lodblizf mfssbgf
            fxdMsgStrB.bppfnd("Minimum dfgrff ");
            fxdMsgStrB.bppfnd(min);
            fxdMsgStrB.bppfnd(" is grfbtfr tibn mbximum dfgrff ");
            fxdMsgStrB.bppfnd(mbx);
            invblidRolfInfoFlg = truf;

        } flsf if (min < ROLE_CARDINALITY_INFINITY ||
                   mbx < ROLE_CARDINALITY_INFINITY) {
            // Rfvisit [dfbro] Lodblizf mfssbgf
            fxdMsgStrB.bppfnd("Minimum or mbximum dfgrff ibs bn illfgbl vbluf, must bf [0, ROLE_CARDINALITY_INFINITY].");
            invblidRolfInfoFlg = truf;
        }
        if (invblidRolfInfoFlg) {
            tirow nfw InvblidRolfInfoExdfption(fxdMsgStrB.toString());
        }
        minDfgrff = min;
        mbxDfgrff = mbx;

        rfffrfndfdMBfbnClbssNbmf = mbfbnClbssNbmf;

        rfturn;
    }

    /**
     * Dfsfriblizfs b {@link RolfInfo} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      if (dompbt)
      {
        // Rfbd bn objfdt sfriblizfd in tif old sfribl form
        //
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        nbmf = (String) fiflds.gft("myNbmf", null);
        if (fiflds.dffbultfd("myNbmf"))
        {
          tirow nfw NullPointfrExdfption("myNbmf");
        }
        isRfbdbblf = fiflds.gft("myIsRfbdbblfFlg", fblsf);
        if (fiflds.dffbultfd("myIsRfbdbblfFlg"))
        {
          tirow nfw NullPointfrExdfption("myIsRfbdbblfFlg");
        }
        isWritbblf = fiflds.gft("myIsWritbblfFlg", fblsf);
        if (fiflds.dffbultfd("myIsWritbblfFlg"))
        {
          tirow nfw NullPointfrExdfption("myIsWritbblfFlg");
        }
        dfsdription = (String) fiflds.gft("myDfsdription", null);
        if (fiflds.dffbultfd("myDfsdription"))
        {
          tirow nfw NullPointfrExdfption("myDfsdription");
        }
        minDfgrff = fiflds.gft("myMinDfgrff", 0);
        if (fiflds.dffbultfd("myMinDfgrff"))
        {
          tirow nfw NullPointfrExdfption("myMinDfgrff");
        }
        mbxDfgrff = fiflds.gft("myMbxDfgrff", 0);
        if (fiflds.dffbultfd("myMbxDfgrff"))
        {
          tirow nfw NullPointfrExdfption("myMbxDfgrff");
        }
        rfffrfndfdMBfbnClbssNbmf = (String) fiflds.gft("myRffMBfbnClbssNbmf", null);
        if (fiflds.dffbultfd("myRffMBfbnClbssNbmf"))
        {
          tirow nfw NullPointfrExdfption("myRffMBfbnClbssNbmf");
        }
      }
      flsf
      {
        // Rfbd bn objfdt sfriblizfd in tif nfw sfribl form
        //
        in.dffbultRfbdObjfdt();
      }
    }


    /**
     * Sfriblizfs b {@link RolfInfo} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("myNbmf", nbmf);
        fiflds.put("myIsRfbdbblfFlg", isRfbdbblf);
        fiflds.put("myIsWritbblfFlg", isWritbblf);
        fiflds.put("myDfsdription", dfsdription);
        fiflds.put("myMinDfgrff", minDfgrff);
        fiflds.put("myMbxDfgrff", mbxDfgrff);
        fiflds.put("myRffMBfbnClbssNbmf", rfffrfndfdMBfbnClbssNbmf);
        out.writfFiflds();
      }
      flsf
      {
        // Sfriblizfs tiis instbndf in tif nfw sfribl form
        //
        out.dffbultWritfObjfdt();
      }
    }

}
