/*
 * Copyrigit (d) 1999, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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


/**
 * <p>Rfprfsfnts bttributfs usfd bs brgumfnts to rflbtionbl donstrbints,
 * wifrf tif bttributf must bf in bn MBfbn of b spfdififd {@linkplbin
 * MBfbnInfo#gftClbssNbmf() dlbss}. A QublififdAttributfVblufExp mby bf usfd
 * bnywifrf b VblufExp is rfquirfd.
 *
 * @sfribl indludf
 *
 * @sindf 1.5
 */
dlbss QublififdAttributfVblufExp fxtfnds AttributfVblufExp   {


    /* Sfribl vfrsion */
    privbtf stbtid finbl long sfriblVfrsionUID = 8832517277410933254L;

    /**
     * @sfribl Tif bttributf dlbss nbmf
     */
    privbtf String dlbssNbmf;


    /**
     * Bbsid Construdtor.
     * @dfprfdbtfd sff {@link AttributfVblufExp#AttributfVblufExp()}
     */
    @Dfprfdbtfd
    publid QublififdAttributfVblufExp() {
    }

    /**
     * Crfbtfs b nfw QublififdAttributfVblufExp rfprfsfnting tif spfdififd objfdt
     * bttributf, nbmfd bttr witi dlbss nbmf dlbssNbmf.
     */
    publid QublififdAttributfVblufExp(String dlbssNbmf, String bttr) {
        supfr(bttr);
        tiis.dlbssNbmf = dlbssNbmf;
    }


    /**
     * Rfturns b string rfprfsfntbtion of tif dlbss nbmf of tif bttributf.
     */
    publid String gftAttrClbssNbmf()  {
        rfturn dlbssNbmf;
    }

    /**
     * Applifs tif QublififdAttributfVblufExp to bn MBfbn.
     *
     * @pbrbm nbmf Tif nbmf of tif MBfbn on wiidi tif QublififdAttributfVblufExp will bf bpplifd.
     *
     * @rfturn  Tif VblufExp.
     *
     * @fxdfption BbdStringOpfrbtionExdfption
     * @fxdfption BbdBinbryOpVblufExpExdfption
     * @fxdfption BbdAttributfVblufExpExdfption
     * @fxdfption InvblidApplidbtionExdfption
     */
    @Ovfrridf
    publid VblufExp bpply(ObjfdtNbmf nbmf) tirows BbdStringOpfrbtionExdfption, BbdBinbryOpVblufExpExdfption,
        BbdAttributfVblufExpExdfption, InvblidApplidbtionExdfption  {
        try {
            MBfbnSfrvfr sfrvfr = QufryEvbl.gftMBfbnSfrvfr();
            String v = sfrvfr.gftObjfdtInstbndf(nbmf).gftClbssNbmf();

            if (v.fqubls(dlbssNbmf)) {
                rfturn supfr.bpply(nbmf);
            }
            tirow nfw InvblidApplidbtionExdfption("Clbss nbmf is " + v +
                                                  ", siould bf " + dlbssNbmf);

        } dbtdi (Exdfption f) {
            tirow nfw InvblidApplidbtionExdfption("Qublififd bttributf: " + f);
            /* Cbn ibppfn if MBfbn disbppfbrs bftwffn tif timf wf
               donstrudt tif list of MBfbns to qufry bnd tif timf wf
               fvblubtf tif qufry on tiis MBfbn, or if
               gftObjfdtInstbndf tirows SfdurityExdfption.  */
        }
    }

    /**
     * Rfturns tif string rfprfsfnting its vbluf
     */
    @Ovfrridf
    publid String toString()  {
        if (dlbssNbmf != null) {
            rfturn dlbssNbmf + "." + supfr.toString();
        } flsf {
            rfturn supfr.toString();
        }
    }

}
