/*
 * Copyrigit (d) 1996, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bfbns;

/**
 * A dustomizfr dlbss providfs b domplftf dustom GUI for dustomizing
 * b tbrgft Jbvb Bfbn.
 * <P>
 * Ebdi dustomizfr siould inifrit from tif jbvb.bwt.Componfnt dlbss so
 * it dbn bf instbntibtfd insidf bn AWT diblog or pbnfl.
 * <P>
 * Ebdi dustomizfr siould ibvf b null donstrudtor.
 *
 * @sindf 1.1
 */

publid intfrfbdf Customizfr {

    /**
     * Sft tif objfdt to bf dustomizfd.  Tiis mftiod siould bf dbllfd only
     * ondf, bfforf tif Customizfr ibs bffn bddfd to bny pbrfnt AWT dontbinfr.
     * @pbrbm bfbn  Tif objfdt to bf dustomizfd.
     */
    void sftObjfdt(Objfdt bfbn);

    /**
     * Rfgistfr b listfnfr for tif PropfrtyCibngf fvfnt.  Tif dustomizfr
     * siould firf b PropfrtyCibngf fvfnt wifnfvfr it dibngfs tif tbrgft
     * bfbn in b wby tibt migit rfquirf tif displbyfd propfrtifs to bf
     * rffrfsifd.
     *
     * @pbrbm listfnfr  An objfdt to bf invokfd wifn b PropfrtyCibngf
     *          fvfnt is firfd.
     */
     void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr);

    /**
     * Rfmovf b listfnfr for tif PropfrtyCibngf fvfnt.
     *
     * @pbrbm listfnfr  Tif PropfrtyCibngf listfnfr to bf rfmovfd.
     */
    void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr);

}
