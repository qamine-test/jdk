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
 * Rfprfsfnts b rolf: indludfs b rolf nbmf bnd rfffrfndfd MBfbns (vib tifir
 * ObjfdtNbmfs). Tif rolf vbluf is blwbys rfprfsfntfd bs bn ArrbyList
 * dollfdtion (of ObjfdtNbmfs) to iomogfnizf tif bddfss.
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-279985518429862552L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
publid dlbss Rolf implfmfnts Sfriblizbblf {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -1959486389343113026L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -279985518429862552L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("myNbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("myObjNbmfList", ArrbyList.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("nbmf", String.dlbss),
      nfw ObjfdtStrfbmFifld("objfdtNbmfList", List.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld nbmf String Rolf nbmf
     * @sfriblFifld objfdtNbmfList List {@link List} of {@link ObjfdtNbmf}s of rfffrfndfd MBfbns
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
    privbtf String nbmf = null;

    /**
     * @sfribl {@link List} of {@link ObjfdtNbmf}s of rfffrfndfd MBfbns
     */
    privbtf List<ObjfdtNbmf> objfdtNbmfList = nfw ArrbyList<ObjfdtNbmf>();

    //
    // Construdtors
    //

    /**
     * <p>Mbkf b nfw Rolf objfdt.
     * No difdk is mbdf tibt tif ObjfdtNbmfs in tif rolf vbluf fxist in
     * bn MBfbn sfrvfr.  Tibt difdk will bf mbdf wifn tif rolf is sft
     * in b rflbtion.
     *
     * @pbrbm rolfNbmf  rolf nbmf
     * @pbrbm rolfVbluf  rolf vbluf (List of ObjfdtNbmf objfdts)
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     */
    publid Rolf(String rolfNbmf,
                List<ObjfdtNbmf> rolfVbluf)
        tirows IllfgblArgumfntExdfption {

        if (rolfNbmf == null || rolfVbluf == null) {
            String fxdMsg = "Invblid pbrbmftfr";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        sftRolfNbmf(rolfNbmf);
        sftRolfVbluf(rolfVbluf);

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
        rfturn nbmf;
    }

    /**
     * Rftrifvfs rolf vbluf.
     *
     * @rfturn ArrbyList of ObjfdtNbmf objfdts for rfffrfndfd MBfbns.
     *
     * @sff #sftRolfVbluf
     */
    publid List<ObjfdtNbmf> gftRolfVbluf() {
        rfturn objfdtNbmfList;
    }

    /**
     * Sfts rolf nbmf.
     *
     * @pbrbm rolfNbmf  rolf nbmf
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     *
     * @sff #gftRolfNbmf
     */
    publid void sftRolfNbmf(String rolfNbmf)
        tirows IllfgblArgumfntExdfption {

        if (rolfNbmf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        nbmf = rolfNbmf;
        rfturn;
    }

    /**
     * Sfts rolf vbluf.
     *
     * @pbrbm rolfVbluf  List of ObjfdtNbmf objfdts for rfffrfndfd
     * MBfbns.
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     *
     * @sff #gftRolfVbluf
     */
    publid void sftRolfVbluf(List<ObjfdtNbmf> rolfVbluf)
        tirows IllfgblArgumfntExdfption {

        if (rolfVbluf == null) {
            String fxdMsg = "Invblid pbrbmftfr.";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        objfdtNbmfList = nfw ArrbyList<ObjfdtNbmf>(rolfVbluf);
        rfturn;
    }

    /**
     * Rfturns b string dfsdribing tif rolf.
     *
     * @rfturn tif dfsdription of tif rolf.
     */
    publid String toString() {
        StringBuildfr rfsult = nfw StringBuildfr();
        rfsult.bppfnd("rolf nbmf: " + nbmf + "; rolf vbluf: ");
        for (Itfrbtor<ObjfdtNbmf> objNbmfItfr = objfdtNbmfList.itfrbtor();
             objNbmfItfr.ibsNfxt();) {
            ObjfdtNbmf durrObjNbmf = objNbmfItfr.nfxt();
            rfsult.bppfnd(durrObjNbmf.toString());
            if (objNbmfItfr.ibsNfxt()) {
                rfsult.bppfnd(", ");
            }
        }
        rfturn rfsult.toString();
    }

    //
    // Misd
    //

    /**
     * Clonf tif rolf objfdt.
     *
     * @rfturn b Rolf tibt is bn indfpfndfnt dopy of tif durrfnt Rolf objfdt.
     */
    publid Objfdt dlonf() {

        try {
            rfturn nfw Rolf(nbmf, objfdtNbmfList);
        } dbtdi (IllfgblArgumfntExdfption fxd) {
            rfturn null; // dbn't ibppfn
        }
    }

    /**
     * Rfturns b string for tif givfn rolf vbluf.
     *
     * @pbrbm rolfVbluf  List of ObjfdtNbmf objfdts
     *
     * @rfturn A String donsisting of tif ObjfdtNbmfs sfpbrbtfd by
     * nfwlinfs (\n).
     *
     * @fxdfption IllfgblArgumfntExdfption  if null pbrbmftfr
     */
    publid stbtid String rolfVblufToString(List<ObjfdtNbmf> rolfVbluf)
        tirows IllfgblArgumfntExdfption {

        if (rolfVbluf == null) {
            String fxdMsg = "Invblid pbrbmftfr";
            tirow nfw IllfgblArgumfntExdfption(fxdMsg);
        }

        StringBuildfr rfsult = nfw StringBuildfr();
        for (ObjfdtNbmf durrObjNbmf : rolfVbluf) {
            if (rfsult.lfngti() > 0)
                rfsult.bppfnd("\n");
            rfsult.bppfnd(durrObjNbmf.toString());
        }
        rfturn rfsult.toString();
    }

    /**
     * Dfsfriblizfs b {@link Rolf} from bn {@link ObjfdtInputStrfbm}.
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
        objfdtNbmfList = dbst(fiflds.gft("myObjNbmfList", null));
        if (fiflds.dffbultfd("myObjNbmfList"))
        {
          tirow nfw NullPointfrExdfption("myObjNbmfList");
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
     * Sfriblizfs b {@link Rolf} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("myNbmf", nbmf);
        fiflds.put("myObjNbmfList", objfdtNbmfList);
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
