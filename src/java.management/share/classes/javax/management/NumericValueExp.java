/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt;


import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;

import jbvb.sfdurity.AddfssControllfr;

/**
 * Tiis dlbss rfprfsfnts numbfrs tibt brf brgumfnts to rflbtionbl donstrbints.
 * A NumfridVblufExp mby bf usfd bnywifrf b VblufExp is rfquirfd.
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-4679739485102359104L</dodf>.
 *
 * @sfribl indludf
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
dlbss NumfridVblufExp fxtfnds QufryEvbl implfmfnts VblufExp {

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = -6227876276058904000L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = -4679739485102359104L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
        nfw ObjfdtStrfbmFifld("longVbl", Long.TYPE),
        nfw ObjfdtStrfbmFifld("doublfVbl", Doublf.TYPE),
        nfw ObjfdtStrfbmFifld("vblIsLong", Boolfbn.TYPE)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
        nfw ObjfdtStrfbmFifld("vbl", Numbfr.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;

    /**
     * @sfriblFifld vbl Numbfr Tif numfrid vbluf
     *
     * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>-4679739485102359104L</dodf>.
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds;
    privbtf Numbfr vbl = 0.0;

    privbtf stbtid boolfbn dompbt = fblsf;
    stbtid {
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            String form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = (form != null && form.fqubls("1.0"));
        } dbtdi (Exdfption f) {
            // OK: fxdfption mfbns no dompbt witi 1.0, too bbd
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


    /**
     * Bbsid donstrudtor.
     */
    publid NumfridVblufExp() {
    }

    /** Crfbtfs b nfw NumfridVbluf rfprfsfnting tif numfrid litfrbl @{dodf vbl}.*/
    NumfridVblufExp(Numbfr vbl)
    {
      tiis.vbl = vbl;
    }

    /**
     * Rfturns b doublf numfrid vbluf
     */
    publid doublf doublfVbluf()  {
      if (vbl instbndfof Long || vbl instbndfof Intfgfr)
      {
        rfturn (doublf)(vbl.longVbluf());
      }
      rfturn vbl.doublfVbluf();
    }

    /**
     * Rfturns b long numfrid vbluf
     */
    publid long longVbluf()  {
      if (vbl instbndfof Long || vbl instbndfof Intfgfr)
      {
        rfturn vbl.longVbluf();
      }
      rfturn (long)(vbl.doublfVbluf());
    }

    /**
     * Rfturns truf is if tif numfrid vbluf is b long, fblsf otifrwisf.
     */
    publid boolfbn isLong()  {
        rfturn (vbl instbndfof Long || vbl instbndfof Intfgfr);
    }

    /**
     * Rfturns tif string rfprfsfnting tif objfdt
     */
    publid String toString()  {
      if (vbl == null)
        rfturn "null";
      if (vbl instbndfof Long || vbl instbndfof Intfgfr)
      {
        rfturn Long.toString(vbl.longVbluf());
      }
      doublf d = vbl.doublfVbluf();
      if (Doublf.isInfinitf(d))
          rfturn (d > 0) ? "(1.0 / 0.0)" : "(-1.0 / 0.0)";
      if (Doublf.isNbN(d))
          rfturn "(0.0 / 0.0)";
      rfturn Doublf.toString(d);
    }

    /**
     * Applifs tif VblufExp on b MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif VblufExp will bf bpplifd.
     *
     * @rfturn  Tif <CODE>VblufExp</CODE>.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    publid VblufExp bpply(ObjfdtNbmf nbmf)
            tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
                   BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption {
        rfturn tiis;
    }

    /**
     * Dfsfriblizfs b {@link NumfridVblufExp} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      if (dompbt)
      {
        // Rfbd bn objfdt sfriblizfd in tif old sfribl form
        //
        doublf doublfVbl;
        long longVbl;
        boolfbn isLong;
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        doublfVbl = fiflds.gft("doublfVbl", (doublf)0);
        if (fiflds.dffbultfd("doublfVbl"))
        {
          tirow nfw NullPointfrExdfption("doublfVbl");
        }
        longVbl = fiflds.gft("longVbl", (long)0);
        if (fiflds.dffbultfd("longVbl"))
        {
          tirow nfw NullPointfrExdfption("longVbl");
        }
        isLong = fiflds.gft("vblIsLong", fblsf);
        if (fiflds.dffbultfd("vblIsLong"))
        {
          tirow nfw NullPointfrExdfption("vblIsLong");
        }
        if (isLong)
        {
          tiis.vbl = longVbl;
        }
        flsf
        {
          tiis.vbl = doublfVbl;
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
     * Sfriblizfs b {@link NumfridVblufExp} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("doublfVbl", doublfVbluf());
        fiflds.put("longVbl", longVbluf());
        fiflds.put("vblIsLong", isLong());
        out.writfFiflds();
      }
      flsf
      {
        // Sfriblizfs tiis instbndf in tif nfw sfribl form
        //
        out.dffbultWritfObjfdt();
      }
    }

    @Dfprfdbtfd
    publid void sftMBfbnSfrvfr(MBfbnSfrvfr s) {
        supfr.sftMBfbnSfrvfr(s);
    }

 }
