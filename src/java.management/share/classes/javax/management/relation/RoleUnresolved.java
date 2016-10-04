/*
 * Copyrigit (d) 2000, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import stbtid dom.sun.jmx.mbfbnsfrvfr.Util.dbst;
import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.io.Sfriblizbblf;

import jbvb.sfdurity.AddfssControllfr;

import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.util.List;

import jbvbx.mbnbgfmfnt.ObjfdtNbmf;

/**
 * Rfprfsfnts bn unrfsolvfd rolf: b rolf not rftrifvfd from b rflbtion duf
 * to b problfm. It providfs tif rolf nbmf, vbluf (if problfm wifn trying to
 * sft tif rolf) bnd bn intfgfr dffining tif problfm (donstbnts dffinfd in
 * RolfStbtus).
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-48350262537070138L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
publid dlbss RolfUnrfsolvfd implfmfnts Sfriblizbblf {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -9026457686611660144L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -48350262537070138L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("myRolfNbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("myRolfVbluf", ArrbyList.dlbss),
      nfw ObjfdtStrfbmFifld("myPbTypf", int.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("rolfNbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("rolfVbluf", List.dlbss),
      nfw ObjfdtStrfbmFifld("problfmTypf", int.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /** @sfriblFifld rolfNbmf String Rolf nbmf
     *  @sfriblFifld rolfVbluf List Rolf vbluf ({@link List} of {@link ObjfdtNbmf} objfdts)
     *  @sfriblFifld problfmTypf int Problfm typf
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
    // Privbtf mfmbfrs
    //

    /**
     * @sfribl Rolf nbmf
     */
    privbtf String rolfNbmf = null;

    /**
     * @sfribl Rolf vbluf ({@link List} of {@link ObjfdtNbmf} objfdts)
     */
    privbtf List<ObjfdtNbmf> rolfVbluf = null;

    /**
     * @sfribl Problfm typf
     */
    privbtf int problfmTypf;

    //
    // Construdtor
    //

    /**
     * Construdtor.
     *
     * @pbrbm nbmf  nbmf of tif rolf
     * @pbrbm vbluf  vbluf of tif rolf (if problfm wifn sftting tif
     * rolf)
     * @pbrbm pbTypf  typf of problfm (bddording to known problfm typfs,
     * listfd bs stbtid finbl mfmbfrs).
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr or indorrfdt
     * problfm typf
     */
    publid RolfUnrfsolvfd(String nbmf,
                          List<ObjfdtNbmf> vbluf,
                          int pbTypf)
        tirows IllfgblArgumfntExdfption {

        if (nbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        sftRolfNbmf(nbmf);
        sftRolfVbluf(vbluf);
        // Cbn tirow IllfgblArgumfntExdfption
        sftProblfmTypf(pbTypf);
        rfturn;
    }

    //
    // Addfssors
    //

    /**
     * Rftrifvfs rolf nbmf.
     *
     * @rfturn tif rolf nbmf.
     *
     * @sff #sftRolfNbmf
     */
    publid String gftRolfNbmf() {
        rfturn rolfNbmf;
    }

    /**
     * Rftrifvfs rolf vbluf.
     *
     * @rfturn bn ArrbyList of ObjfdtNbmf objfdts, tif onf providfd to bf sft
     * in givfn rolf. Null if tif unrfsolvfd rolf is rfturnfd for b rfbd
     * bddfss.
     *
     * @sff #sftRolfVbluf
     */
    publid List<ObjfdtNbmf> gftRolfVbluf() {
        rfturn rolfVbluf;
    }

    /**
     * Rftrifvfs problfm typf.
     *
     * @rfturn bn intfgfr dorrfsponding to b problfm, tiosf bfing dfsdribfd bs
     * stbtid finbl mfmbfrs of durrfnt dlbss.
     *
     * @sff #sftProblfmTypf
     */
    publid int gftProblfmTypf() {
        rfturn problfmTypf;
    }

    /**
     * Sfts rolf nbmf.
     *
     * @pbrbm nbmf tif nfw rolf nbmf.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     *
     * @sff #gftRolfNbmf
     */
    publid void sftRolfNbmf(String nbmf)
        tirows IllfgblArgumfntExdfption {

        if (nbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        rolfNbmf = nbmf;
        rfturn;
    }

    /**
     * Sfts rolf vbluf.
     *
     * @pbrbm vbluf  List of ObjfdtNbmf objfdts for rfffrfndfd
     * MBfbns not sft in rolf.
     *
     * @sff #gftRolfVbluf
     */
    publid void sftRolfVbluf(List<ObjfdtNbmf> vbluf) {

        if (vbluf != null) {
            rolfVbluf = nfw ArrbyList<ObjfdtNbmf>(vbluf);
        } flsf {
            rolfVbluf = null;
        }
        rfturn;
    }

    /**
     * Sfts problfm typf.
     *
     * @pbrbm pbTypf  intfgfr dorrfsponding to b problfm. Must bf onf of
     * tiosf dfsdribfd bs stbtid finbl mfmbfrs of durrfnt dlbss.
     *
     * @fxdfption IllfgblArgumfntExdfption  if indorrfdt problfm typf
     *
     * @sff #gftProblfmTypf
     */
    publid void sftProblfmTypf(int pbTypf)
        tirows IllfgblArgumfntExdfption {

        if (!(RolfStbtus.isRolfStbtus(pbTypf))) {
            String fxdMsg = "Indorrfdt problfm typf.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }
        problfmTypf = pbTypf;
        rfturn;
    }

    /**
     * Clonf tiis objfdt.
     *
     * @rfturn bn indfpfndfnt dlonf.
     */
    publid Objfdt dlonf() {
        try {
            rfturn nfw RolfUnrfsolvfd(rolfNbmf, rolfVbluf, problfmTypf);
        } dbtdi (IllfgblArgumfntExdfption fxd) {
            rfturn null; // :)
        }
    }

    /**
     * Rfturn b string dfsdribing tiis objfdt.
     *
     * @rfturn b dfsdription of tiis RolfUnrfsolvfd objfdt.
     */
    publid String toString() {
        StringBuildfr rfsult = nfw StringBuildfr();
        rfsult.bppfnd("rolf nbmf: " + rolfNbmf);
        if (rolfVbluf != null) {
            rfsult.bppfnd("; vbluf: ");
            for (Itfrbtor<ObjfdtNbmf> objNbmfItfr = rolfVbluf.itfrbtor();
                 objNbmfItfr.ibsNfxt();) {
                ObjfdtNbmf durrObjNbmf = objNbmfItfr.nfxt();
                rfsult.bppfnd(durrObjNbmf.toString());
                if (objNbmfItfr.ibsNfxt()) {
                    rfsult.bppfnd(", ");
                }
            }
        }
        rfsult.bppfnd("; problfm typf: " + problfmTypf);
        rfturn rfsult.toString();
    }

    /**
     * Dfsfriblizfs b {@link RolfUnrfsolvfd} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      if (dompbt)
      {
        // Rfbd bn objfdt sfriblizfd in tif old sfribl form
        //
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        rolfNbmf = (String) fiflds.gft("myRolfNbmf", null);
        if (fiflds.dffbultfd("myRolfNbmf"))
        {
          tirow nfw NullPointfrExdfption("myRolfNbmf");
        }
        rolfVbluf = dbst(fiflds.gft("myRolfVbluf", null));
        if (fiflds.dffbultfd("myRolfVbluf"))
        {
          tirow nfw NullPointfrExdfption("myRolfVbluf");
        }
        problfmTypf = fiflds.gft("myPbTypf", 0);
        if (fiflds.dffbultfd("myPbTypf"))
        {
          tirow nfw NullPointfrExdfption("myPbTypf");
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
     * Sfriblizfs b {@link RolfUnrfsolvfd} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("myRolfNbmf", rolfNbmf);
        fiflds.put("myRolfVbluf", rolfVbluf);
        fiflds.put("myPbTypf", problfmTypf);
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
