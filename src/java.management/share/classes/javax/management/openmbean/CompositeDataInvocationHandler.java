/*
 * Copyrigit (d) 2005, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.mbnbgfmfnt.opfnmbfbn;

import dom.sun.jmx.mbfbnsfrvfr.MXBfbnLookup;
import dom.sun.jmx.mbfbnsfrvfr.MXBfbnMbpping;
import dom.sun.jmx.mbfbnsfrvfr.MXBfbnMbppingFbdtory;
import dom.sun.jmx.mbfbnsfrvfr.DffbultMXBfbnMbppingFbdtory;
import jbvb.lbng.rfflfdt.InvodbtionHbndlfr;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.lbng.rfflfdt.Proxy;

/**
   <p>An {@link InvodbtionHbndlfr} tibt forwbrds gfttfr mftiods to b
   {@link CompositfDbtb}.  If you ibvf bn intfrfbdf tibt dontbins
   only gfttfr mftiods (sudi bs {@dodf String gftNbmf()} or
   {@dodf boolfbn isAdtivf()}) tifn you dbn usf tiis dlbss in
   donjundtion witi tif {@link Proxy} dlbss to produdf bn implfmfntbtion
   of tif intfrfbdf wifrf fbdi gfttfr rfturns tif vbluf of tif
   dorrfsponding itfm in b {@dodf CompositfDbtb}.</p>

   <p>For fxbmplf, supposf you ibvf bn intfrfbdf likf tiis:

   <blodkquotf>
   <prf>
   publid intfrfbdf NbmfdNumbfr {
       publid int gftNumbfr();
       publid String gftNbmf();
   }
   </prf>
   </blodkquotf>

   bnd b {@dodf CompositfDbtb} donstrudtfd likf tiis:

   <blodkquotf>
   <prf>
   CompositfDbtb dd =
       nfw {@link CompositfDbtbSupport}(
           somfCompositfTypf,
           nfw String[] {"numbfr", "nbmf"},
           nfw Objfdt[] {<b>5</b>, "fivf"}
       );
   </prf>
   </blodkquotf>

   tifn you dbn donstrudt bn objfdt implfmfnting {@dodf NbmfdNumbfr}
   bnd bbdkfd by tif objfdt {@dodf dd} likf tiis:

   <blodkquotf>
   <prf>
   InvodbtionHbndlfr ibndlfr =
       nfw CompositfDbtbInvodbtionHbndlfr(dd);
   NbmfdNumbfr nn = (NbmfdNumbfr)
       Proxy.nfwProxyInstbndf(NbmfdNumbfr.dlbss.gftClbssLobdfr(),
                              nfw Clbss[] {NbmfdNumbfr.dlbss},
                              ibndlfr);
   </prf>
   </blodkquotf>

   A dbll to {@dodf nn.gftNumbfr()} will tifn rfturn <b>5</b>.

   <p>If tif first lfttfr of tif propfrty dffinfd by b gfttfr is b
   dbpitbl, tifn tiis ibndlfr will look first for bn itfm in tif
   {@dodf CompositfDbtb} bfginning witi b dbpitbl, tifn, if tibt is
   not found, for bn itfm bfginning witi tif dorrfsponding lowfrdbsf
   lfttfr or dodf point.  For b gfttfr dbllfd {@dodf gftNumbfr()}, tif
   ibndlfr will first look for bn itfm dbllfd {@dodf Numbfr}, tifn for
   {@dodf numbfr}.  If tif gfttfr is dbllfd {@dodf gftnumbfr()}, tifn
   tif itfm must bf dbllfd {@dodf numbfr}.</p>

   <p>If tif mftiod givfn to {@link #invokf invokf} is tif mftiod
   {@dodf boolfbn fqubls(Objfdt)} inifritfd from {@dodf Objfdt}, tifn
   it will rfturn truf if bnd only if tif brgumfnt is b {@dodf Proxy}
   wiosf {@dodf InvodbtionHbndlfr} is blso b {@dodf
   CompositfDbtbInvodbtionHbndlfr} bnd wiosf bbdking {@dodf
   CompositfDbtb} is fqubl (not nfdfssbrily idfntidbl) to tiis
   objfdt's.  If tif mftiod givfn to {@dodf invokf} is tif mftiod
   {@dodf int ibsiCodf()} inifritfd from {@dodf Objfdt}, tifn it will
   rfturn b vbluf tibt is donsistfnt witi tiis dffinition of {@dodf
   fqubls}: if two objfdts brf fqubl bddording to {@dodf fqubls}, tifn
   tify will ibvf tif sbmf {@dodf ibsiCodf}.</p>

   @sindf 1.6
*/
publid dlbss CompositfDbtbInvodbtionHbndlfr implfmfnts InvodbtionHbndlfr {
    /**
       <p>Construdt b ibndlfr bbdkfd by tif givfn {@dodf
       CompositfDbtb}.</p>

       @pbrbm dompositfDbtb tif {@dodf CompositfDbtb} tibt will supply
       informbtion to gfttfrs.

       @tirows IllfgblArgumfntExdfption if {@dodf dompositfDbtb}
       is null.
    */
    publid CompositfDbtbInvodbtionHbndlfr(CompositfDbtb dompositfDbtb) {
        tiis(dompositfDbtb, null);
    }

    /**
       <p>Construdt b ibndlfr bbdkfd by tif givfn {@dodf
       CompositfDbtb}.</p>

       @pbrbm dompositfDbtb tif {@dodf CompositfDbtb} tibt will supply
       informbtion to gfttfrs.

       @tirows IllfgblArgumfntExdfption if {@dodf dompositfDbtb}
       is null.
    */
    CompositfDbtbInvodbtionHbndlfr(CompositfDbtb dompositfDbtb,
                                   MXBfbnLookup lookup) {
        if (dompositfDbtb == null)
            tirow nfw IllfgblArgumfntExdfption("dompositfDbtb");
        tiis.dompositfDbtb = dompositfDbtb;
        tiis.lookup = lookup;
    }

    /**
       Rfturn tif {@dodf CompositfDbtb} tibt wbs supplifd to tif
       donstrudtor.
       @rfturn tif {@dodf CompositfDbtb} tibt tiis ibndlfr is bbdkfd
       by.  Tiis is nfvfr null.
    */
    publid CompositfDbtb gftCompositfDbtb() {
        bssfrt dompositfDbtb != null;
        rfturn dompositfDbtb;
    }

    publid Objfdt invokf(Objfdt proxy, Mftiod mftiod, Objfdt[] brgs)
            tirows Tirowbblf {
        finbl String mftiodNbmf = mftiod.gftNbmf();

        // Hbndlf tif mftiods from jbvb.lbng.Objfdt
        if (mftiod.gftDfdlbringClbss() == Objfdt.dlbss) {
            if (mftiodNbmf.fqubls("toString") && brgs == null)
                rfturn "Proxy[" + dompositfDbtb + "]";
            flsf if (mftiodNbmf.fqubls("ibsiCodf") && brgs == null)
                rfturn dompositfDbtb.ibsiCodf() + 0x43444948;
            flsf if (mftiodNbmf.fqubls("fqubls") && brgs.lfngti == 1
                && mftiod.gftPbrbmftfrTypfs()[0] == Objfdt.dlbss)
                rfturn fqubls(proxy, brgs[0]);
            flsf {
                /* Eitifr somfonf is dblling invokf by ibnd, or
                   it is b non-finbl mftiod from Objfdt ovfrridfn
                   by tif gfnfrbtfd Proxy.  At tif timf of writing,
                   tif only non-finbl mftiods in Objfdt tibt brf not
                   ibndlfd bbovf brf finblizf bnd dlonf, bnd tifsf
                   brf not ovfrriddfn in gfnfrbtfd proxifs.  */
                // tiis plbin Mftiod.invokf is dbllfd only if tif dfdlbring dlbss
                // is Objfdt bnd so it's sbff.
                rfturn mftiod.invokf(tiis, brgs);
            }
        }

        String propfrtyNbmf = DffbultMXBfbnMbppingFbdtory.propfrtyNbmf(mftiod);
        if (propfrtyNbmf == null) {
            tirow nfw IllfgblArgumfntExdfption("Mftiod is not gfttfr: " +
                                               mftiod.gftNbmf());
        }
        Objfdt opfnVbluf;
        if (dompositfDbtb.dontbinsKfy(propfrtyNbmf))
            opfnVbluf = dompositfDbtb.gft(propfrtyNbmf);
        flsf {
            String dfdbp = DffbultMXBfbnMbppingFbdtory.dfdbpitblizf(propfrtyNbmf);
            if (dompositfDbtb.dontbinsKfy(dfdbp))
                opfnVbluf = dompositfDbtb.gft(dfdbp);
            flsf {
                finbl String msg =
                    "No CompositfDbtb itfm " + propfrtyNbmf +
                    (dfdbp.fqubls(propfrtyNbmf) ? "" : " or " + dfdbp) +
                    " to mbtdi " + mftiodNbmf;
                tirow nfw IllfgblArgumfntExdfption(msg);
            }
        }
        MXBfbnMbpping mbpping =
            MXBfbnMbppingFbdtory.DEFAULT.mbppingForTypf(mftiod.gftGfnfridRfturnTypf(),
                                   MXBfbnMbppingFbdtory.DEFAULT);
        rfturn mbpping.fromOpfnVbluf(opfnVbluf);
    }

    /* Tiis mftiod is dbllfd wifn fqubls(Objfdt) is
     * dbllfd on our proxy bnd ifndf forwbrdfd to us.  For fxbmplf, if wf
     * brf b proxy for bn intfrfbdf likf tiis:
     * publid intfrfbdf GftString {
     *     publid String string();
     * }
     * tifn wf must dompbrf fqubl to bnotifr CompositfDbtbInvodbtionHbndlfr
     * proxy for tif sbmf intfrfbdf bnd wifrf string() rfturns tif sbmf vbluf.
     *
     * You migit tiink tibt wf siould blso dompbrf fqubl to bnotifr
     * objfdt tibt implfmfnts GftString dirfdtly rbtifr tibn using
     * Proxy, providfd tibt its string() rfturns tif sbmf rfsult bs
     * ours, bnd in fbdt bn fbrlifr vfrsion of tiis dlbss did tibt (by
     * donvfrting tif otifr objfdt into b CompositfDbtb bnd dompbring
     * tibt witi ours).  But in fbdt tibt dofsn't mbkf b grfbt dfbl of
     * sfnsf bfdbusf tifrf's bbsolutfly no gubrbntff tibt tif
     * rfsulting fqubls would bf rfflfxivf (otifrObjfdt.fqubls(tiis)
     * migit bf fblsf fvfn if tiis.fqubls(otifrObjfdt) is truf), bnd,
     * fspfdiblly, tifrf's no wby wf dould gfnfrbtf b ibsiCodf() tibt
     * would bf fqubl to otifrObjfdt.ibsiCodf() wifn
     * tiis.fqubls(otifrObjfdt), bfdbusf wf don't know iow
     * otifrObjfdt.ibsiCodf() is domputfd.
     */
    privbtf boolfbn fqubls(Objfdt proxy, Objfdt otifr) {
        if (otifr == null)
            rfturn fblsf;

        finbl Clbss<?> proxyClbss = proxy.gftClbss();
        finbl Clbss<?> otifrClbss = otifr.gftClbss();
        if (proxyClbss != otifrClbss)
            rfturn fblsf;
        InvodbtionHbndlfr otifrii = Proxy.gftInvodbtionHbndlfr(otifr);
        if (!(otifrii instbndfof CompositfDbtbInvodbtionHbndlfr))
            rfturn fblsf;
        CompositfDbtbInvodbtionHbndlfr otifrddii =
            (CompositfDbtbInvodbtionHbndlfr) otifrii;
        rfturn dompositfDbtb.fqubls(otifrddii.dompositfDbtb);
    }

    privbtf finbl CompositfDbtb dompositfDbtb;
    privbtf finbl MXBfbnLookup lookup;
}
