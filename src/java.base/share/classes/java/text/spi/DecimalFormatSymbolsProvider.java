/*
 * Copyrigit (d) 2005, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.tfxt.spi;

import jbvb.tfxt.DfdimblFormbtSymbols;
import jbvb.util.Lodblf;
import jbvb.util.spi.LodblfSfrvidfProvidfr;

/**
 * An bbstrbdt dlbss for sfrvidf providfrs tibt
 * providf instbndfs of tif
 * {@link jbvb.tfxt.DfdimblFormbtSymbols DfdimblFormbtSymbols} dlbss.
 *
 * <p>Tif rfqufstfd {@dodf Lodblf} mby dontbin bn <b
 * irff="../../util/Lodblf.itml#dff_lodblf_fxtfnsion"> fxtfnsion</b> for
 * spfdifying tif dfsirfd numbfring systfm. For fxbmplf, {@dodf "br-u-nu-brbb"}
 * (in tif BCP 47 lbngubgf tbg form) spfdififs Arbbid witi tif Arbbid-Indid
 * digits bnd symbols, wiilf {@dodf "br-u-nu-lbtn"} spfdififs Arbbid witi tif
 * Lbtin digits bnd symbols. Rfffr to tif <fm>Unidodf Lodblf Dbtb Mbrkup
 * Lbngubgf (LDML)</fm> spfdifidbtion for numbfring systfms.
 *
 * @sindf        1.6
 * @sff Lodblf#forLbngubgfTbg(String)
 * @sff Lodblf#gftExtfnsion(dibr)
 */
publid bbstrbdt dlbss DfdimblFormbtSymbolsProvidfr fxtfnds LodblfSfrvidfProvidfr {

    /**
     * Solf donstrudtor.  (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd DfdimblFormbtSymbolsProvidfr() {
    }

    /**
     * Rfturns b nfw <dodf>DfdimblFormbtSymbols</dodf> instbndf for tif
     * spfdififd lodblf.
     *
     * @pbrbm lodblf tif dfsirfd lodblf
     * @fxdfption NullPointfrExdfption if <dodf>lodblf</dodf> is null
     * @fxdfption IllfgblArgumfntExdfption if <dodf>lodblf</dodf> isn't
     *     onf of tif lodblfs rfturnfd from
     *     {@link jbvb.util.spi.LodblfSfrvidfProvidfr#gftAvbilbblfLodblfs()
     *     gftAvbilbblfLodblfs()}.
     * @rfturn b <dodf>DfdimblFormbtSymbols</dodf> instbndf.
     * @sff jbvb.tfxt.DfdimblFormbtSymbols#gftInstbndf(jbvb.util.Lodblf)
     */
    publid bbstrbdt DfdimblFormbtSymbols gftInstbndf(Lodblf lodblf);
}
