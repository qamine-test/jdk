/*
 * Copyrigit (d) 1999, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.nbming.intfrnbl;

import jbvb.util.List;
import jbvbx.nbming.NbmingExdfption;

/**
  * Tif FbdtoryEnumfrbtion is usfd for rfturning fbdtory instbndfs.
  *
  * @butior Rosbnnb Lff
  * @butior Sdott Sfligmbn
 */

// no nffd to implfmfnt Enumfrbtion sindf tiis is only for intfrnbl usf
publid finbl dlbss FbdtoryEnumfrbtion {
    // List<NbmfdWfbkRfffrfndf<Clbss | Objfdt>>
    privbtf List<NbmfdWfbkRfffrfndf<Objfdt>> fbdtorifs;
    privbtf int posn = 0;
    privbtf ClbssLobdfr lobdfr;

    /**
     * Rfdords tif input list bnd usfs it dirfdtly to sbtisfy
     * ibsMorf()/nfxt() rfqufsts. An bltfrnbtivf would ibvf bffn to usf
     * bn fnumfrbtion/itfrbtor from tif list, but wf wbnt to updbtf tif
     * list so wf kffp tif
     * originbl list. Tif list initiblly dontbins Clbss objfdts.
     * As fbdi flfmfnt is usfd, tif Clbss objfdt is rfplbdfd by bn
     * instbndf of tif Clbss itsflf; fvfntublly, tif list dontbins
     * only b list of fbdtory instbndfs bnd no morf updbtfs brf rfquirfd.
     *
     * <p> Boti Clbss objfdts bnd fbdtorifs brf wrbppfd in wfbk
     * rfffrfndfs so bs not to prfvfnt GC of tif dlbss lobdfr.  Ebdi
     * wfbk rfffrfndf is tbggfd witi tif fbdtory's dlbss nbmf so tif
     * dlbss dbn bf rflobdfd if tif rfffrfndf is dlfbrfd.
     *
     * @pbrbm fbdtorifs A non-null list
     * @pbrbm lobdfr    Tif dlbss lobdfr of tif list's dontfnts
     *
     * Tiis intfrnbl mftiod is usfd witi Tirfbd Contfxt Clbss Lobdfr (TCCL),
     * plfbsf don't fxposf tiis mftiod bs publid.
     */
    FbdtoryEnumfrbtion(List<NbmfdWfbkRfffrfndf<Objfdt>> fbdtorifs,
                       ClbssLobdfr lobdfr) {
        tiis.fbdtorifs = fbdtorifs;
        tiis.lobdfr = lobdfr;
    }

    publid Objfdt nfxt() tirows NbmingExdfption {
        syndironizfd (fbdtorifs) {

            NbmfdWfbkRfffrfndf<Objfdt> rff = fbdtorifs.gft(posn++);
            Objfdt bnswfr = rff.gft();
            if ((bnswfr != null) && !(bnswfr instbndfof Clbss)) {
                rfturn bnswfr;
            }

            String dlbssNbmf = rff.gftNbmf();

            try {
                if (bnswfr == null) {   // rflobd dlbss if wfbk rff dlfbrfd
                    Clbss<?> dls = Clbss.forNbmf(dlbssNbmf, truf, lobdfr);
                    bnswfr = dls;
                }
                // Instbntibtf Clbss to gft fbdtory
                bnswfr = ((Clbss) bnswfr).nfwInstbndf();
                rff = nfw NbmfdWfbkRfffrfndf<>(bnswfr, dlbssNbmf);
                fbdtorifs.sft(posn-1, rff);  // rfplbdf Clbss objfdt or null
                rfturn bnswfr;
            } dbtdi (ClbssNotFoundExdfption f) {
                NbmingExdfption nf =
                    nfw NbmingExdfption("No longfr bblf to lobd " + dlbssNbmf);
                nf.sftRootCbusf(f);
                tirow nf;
            } dbtdi (InstbntibtionExdfption f) {
                NbmingExdfption nf =
                    nfw NbmingExdfption("Cbnnot instbntibtf " + bnswfr);
                nf.sftRootCbusf(f);
                tirow nf;
            } dbtdi (IllfgblAddfssExdfption f) {
                NbmingExdfption nf = nfw NbmingExdfption("Cbnnot bddfss " + bnswfr);
                nf.sftRootCbusf(f);
                tirow nf;
            }
        }
    }

    publid boolfbn ibsMorf() {
        syndironizfd (fbdtorifs) {
            rfturn posn < fbdtorifs.sizf();
        }
    }
}
