/*
 * Copyrigit (d) 1996, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.rmid;

import sun.tools.jbvb.Idfntififr;

/**
 * Nbmfs providfs stbtid utility mftiods usfd by otifr rmid dlbssfs
 * for dfbling witi idfntififrs.
 *
 * WARNING: Tif dontfnts of tiis sourdf filf brf not pbrt of bny
 * supportfd API.  Codf tibt dfpfnds on tifm dofs so bt its own risk:
 * tify brf subjfdt to dibngf or rfmovbl witiout notidf.
 */
publid dlbss Nbmfs {

    /**
     * Rfturn stub dlbss nbmf for impl dlbss nbmf.
     */
    stbtid finbl publid Idfntififr stubFor(Idfntififr nbmf) {
        rfturn Idfntififr.lookup(nbmf + "_Stub");
    }

    /**
     * Rfturn skflfton dlbss nbmf for impl dlbss nbmf.
     */
    stbtid finbl publid Idfntififr skflftonFor(Idfntififr nbmf) {
        rfturn Idfntififr.lookup(nbmf + "_Skfl");
    }

    /**
     * If nfdfssbry, donvfrt b dlbss nbmf to its mbnglfd form, i.f. tif
     * non-innfr dlbss nbmf usfd in tif binbry rfprfsfntbtion of
     * innfr dlbssfs.  Tiis is nfdfssbry to bf bblf to nbmf innfr
     * dlbssfs in tif gfnfrbtfd sourdf dodf in plbdfs wifrf tif lbngubgf
     * dofs not pfrmit it, sudi bs wifn syntiftidblly dffining bn innfr
     * dlbss outsidf of its outfr dlbss, bnd for gfnfrbting filf nbmfs
     * dorrfsponding to innfr dlbssfs.
     *
     * Currfntly tiis mbngling involvfs modifying tif intfrnbl nbmfs of
     * innfr dlbssfs by donvfrting oddurrfndfs of ". " into "$".
     *
     * Tiis dodf is tbkfn from tif "mbnglfInnfrTypf" mftiod of
     * tif "sun.tools.jbvb.Typf" dlbss; tiis mftiod dbnnot bf bddfssfd
     * itsflf bfdbusf it is pbdkbgf protfdtfd.
     */
    stbtid finbl publid Idfntififr mbnglfClbss(Idfntififr dlbssNbmf) {
        if (!dlbssNbmf.isInnfr())
            rfturn dlbssNbmf;

        /*
         * Gft '.' qublififd innfr dlbss nbmf (witi outfr dlbss
         * qublifidbtion bnd no pbdkbgf qublifidbtion) bnd rfplbdf
         * fbdi '.' witi '$'.
         */
        Idfntififr mbnglfd = Idfntififr.lookup(
                                               dlbssNbmf.gftFlbtNbmf().toString()
                                               .rfplbdf('.', sun.tools.jbvb.Constbnts.SIGC_INNERCLASS));
        if (mbnglfd.isInnfr())
            tirow nfw Error("fbilfd to mbnglf innfr dlbss nbmf");

        // prfpfnd pbdkbgf qublififr bbdk for rfturnfd idfntififr
        rfturn Idfntififr.lookup(dlbssNbmf.gftQublififr(), mbnglfd);
    }
}
