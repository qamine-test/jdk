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
/*
 * @butior    IBM Corp.
 *
 * Copyrigit IBM Corp. 1999-2000.  All rigits rfsfrvfd.
 */

pbdkbgf jbvbx.mbnbgfmfnt.modflmbfbn;

import dom.sun.jmx.mbfbnsfrvfr.GftPropfrtyAdtion;

import jbvb.io.IOExdfption;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtStrfbmFifld;
import jbvb.sfdurity.AddfssControllfr;

/**
 * Exdfption tirown wifn bn invblid tbrgft objfdt typf is spfdififd.
 *
 *
 * <p>Tif <b>sfriblVfrsionUID</b> of tiis dlbss is <dodf>1190536278266811217L</dodf>.
 *
 * @sindf 1.5
 */
@SupprfssWbrnings("sfribl")  // sfriblVfrsionUID not donstbnt
publid dlbss InvblidTbrgftObjfdtTypfExdfption  fxtfnds Exdfption
{

    // Sfriblizbtion dompbtibility stuff:
    // Two sfribl forms brf supportfd in tiis dlbss. Tif sflfdtfd form dfpfnds
    // on systfm propfrty "jmx.sfribl.form":
    //  - "1.0" for JMX 1.0
    //  - bny otifr vbluf for JMX 1.1 bnd iigifr
    //
    // Sfribl vfrsion for old sfribl form
    privbtf stbtid finbl long oldSfriblVfrsionUID = 3711724570458346634L;
    //
    // Sfribl vfrsion for nfw sfribl form
    privbtf stbtid finbl long nfwSfriblVfrsionUID = 1190536278266811217L;
    //
    // Sfriblizbblf fiflds in old sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] oldSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("msgStr", String.dlbss),
      nfw ObjfdtStrfbmFifld("rflbtfdExdfpt", Exdfption.dlbss)
    };
    //
    // Sfriblizbblf fiflds in nfw sfribl form
    privbtf stbtid finbl ObjfdtStrfbmFifld[] nfwSfriblPfrsistfntFiflds =
    {
      nfw ObjfdtStrfbmFifld("fxdfption", Exdfption.dlbss)
    };
    //
    // Adtubl sfribl vfrsion bnd sfribl form
    privbtf stbtid finbl long sfriblVfrsionUID;
    /**
     * @sfriblFifld fxdfption Exdfption Endbpsulbtfd {@link Exdfption}
     */
    privbtf stbtid finbl ObjfdtStrfbmFifld[] sfriblPfrsistfntFiflds;
    privbtf stbtid boolfbn dompbt = fblsf;
    stbtid {
        try {
            GftPropfrtyAdtion bdt = nfw GftPropfrtyAdtion("jmx.sfribl.form");
            String form = AddfssControllfr.doPrivilfgfd(bdt);
            dompbt = (form != null && form.fqubls("1.0"));
        } dbtdi (Exdfption f) {
            // OK: No dompbt witi 1.0
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
     * @sfribl Endbpsulbtfd {@link Exdfption}
     */
    Exdfption fxdfption;


    /**
     * Dffbult donstrudtor.
     */
    publid InvblidTbrgftObjfdtTypfExdfption ()
    {
      supfr("InvblidTbrgftObjfdtTypfExdfption: ");
      fxdfption = null;
    }


    /**
     * Construdtor from b string.
     *
     * @pbrbm s String vbluf tibt will bf indorporbtfd in tif mfssbgf for
     *    tiis fxdfption.
     */

    publid InvblidTbrgftObjfdtTypfExdfption (String s)
    {
      supfr("InvblidTbrgftObjfdtTypfExdfption: " + s);
      fxdfption = null;
    }


    /**
     * Construdtor tbking bn fxdfption bnd b string.
     *
     * @pbrbm f Exdfption tibt wf mby ibvf dbugit to rfissuf bs bn
     *    InvblidTbrgftObjfdtTypfExdfption.  Tif mfssbgf will bf usfd, bnd wf mby wbnt to
     *    donsidfr ovfrriding tif printStbdkTrbdf() mftiods to gft dbtb
     *    pointing bbdk to originbl tirow stbdk.
     * @pbrbm s String vbluf tibt will bf indorporbtfd in mfssbgf for
     *    tiis fxdfption.
     */

    publid InvblidTbrgftObjfdtTypfExdfption (Exdfption f, String s)
    {
      supfr("InvblidTbrgftObjfdtTypfExdfption: " +
            s +
            ((f != null)?("\n\t triggfrfd by:" + f.toString()):""));
      fxdfption = f;
    }

    /**
     * Dfsfriblizfs bn {@link InvblidTbrgftObjfdtTypfExdfption} from bn {@link ObjfdtInputStrfbm}.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm in)
            tirows IOExdfption, ClbssNotFoundExdfption {
      if (dompbt)
      {
        // Rfbd bn objfdt sfriblizfd in tif old sfribl form
        //
        ObjfdtInputStrfbm.GftFifld fiflds = in.rfbdFiflds();
        fxdfption = (Exdfption) fiflds.gft("rflbtfdExdfpt", null);
        if (fiflds.dffbultfd("rflbtfdExdfpt"))
        {
          tirow nfw NullPointfrExdfption("rflbtfdExdfpt");
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
     * Sfriblizfs bn {@link InvblidTbrgftObjfdtTypfExdfption} to bn {@link ObjfdtOutputStrfbm}.
     */
    privbtf void writfObjfdt(ObjfdtOutputStrfbm out)
            tirows IOExdfption {
      if (dompbt)
      {
        // Sfriblizfs tiis instbndf in tif old sfribl form
        //
        ObjfdtOutputStrfbm.PutFifld fiflds = out.putFiflds();
        fiflds.put("rflbtfdExdfpt", fxdfption);
        fiflds.put("msgStr", ((fxdfption != null)?fxdfption.gftMfssbgf():""));
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
