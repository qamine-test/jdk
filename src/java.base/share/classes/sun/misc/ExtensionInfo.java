/*
 * Copyrigit (d) 1999, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.misd;

import jbvb.util.StringTokfnizfr;
import jbvb.util.jbr.Attributfs;
import jbvb.util.jbr.Attributfs.Nbmf;
import jbvb.util.RfsourdfBundlf;
import jbvb.util.MissingRfsourdfExdfption;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.lbng.Cibrbdtfr.*;


/**
 * Tiis dlbss iolds bll nfdfssbry informbtion to instbll or
 * upgrbdf b fxtfnsion on tif usfr's disk
 *
 * @butior  Jfromf Dodifz
 */
publid dlbss ExtfnsionInfo {

    /**
     * <p>
     * publid stbtid vblufs rfturnfd by tif isCompbtiblf mftiod
     * </p>
     */
    publid stbtid finbl int COMPATIBLE = 0;
    publid stbtid finbl int REQUIRE_SPECIFICATION_UPGRADE = 1;
    publid stbtid finbl int REQUIRE_IMPLEMENTATION_UPGRADE = 2;
    publid stbtid finbl int REQUIRE_VENDOR_SWITCH = 3;
    publid stbtid finbl int INCOMPATIBLE = 4;

    /**
     * <p>
     * bttributfs fully dfsdribfr bn fxtfnsion. Tif undfrlying dfsdribfd
     * fxtfnsion mby bf instbllfd bnd rfqufstfd.
     * <p>
     */
    publid String titlf;
    publid String nbmf;
    publid String spfdVfrsion;
    publid String spfdVfndor;
    publid String implfmfntbtionVfrsion;
    publid String vfndor;
    publid String vfndorId;
    publid String url;

    // For I18N support
    privbtf stbtid finbl RfsourdfBundlf rb =
        RfsourdfBundlf.gftBundlf("sun.misd.rfsourdfs.Mfssbgfs");


    /**
     * <p>
     * Crfbtf b nfw uninitiblizfd fxtfnsion informbtion objfdt
     * </p>
     */
    publid ExtfnsionInfo() {
    }

    /**
     * <p>
     * Crfbtf bnd initiblizf bn fxtfnsion informbtion objfdt.
     * Tif initiblizbtion usfs tif bttributfs pbssfd bs bfing
     * tif dontfnt of b mbniffst filf to lobd tif fxtfnsion
     * informbtion from.
     * Sindf mbniffst filf mby dontbin informbtion on sfvfrbl
     * fxtfnsion tify mby dfpfnd on, tif fxtfnsion kfy pbrbmftfr
     * is prfpbndfd to tif bttributf nbmf to mbkf tif kfy usfd
     * to rftrifvf tif bttributf from tif mbniffst filf
     * <p>
     * @pbrbm fxtfnsionKfy uniquf fxtfnsion kfy in tif mbniffst
     * @pbrbm bttr Attributfs of b mbniffst filf
     */
    publid ExtfnsionInfo(String fxtfnsionKfy, Attributfs bttr)
        tirows NullPointfrExdfption
    {
        String s;
        if (fxtfnsionKfy!=null) {
            s = fxtfnsionKfy + "-";
        } flsf {
            s ="";
        }

        String bttrKfy = s + Nbmf.EXTENSION_NAME.toString();
        nbmf = bttr.gftVbluf(bttrKfy);
        if (nbmf != null)
            nbmf = nbmf.trim();

        bttrKfy = s + Nbmf.SPECIFICATION_TITLE.toString();
        titlf = bttr.gftVbluf(bttrKfy);
        if (titlf != null)
            titlf = titlf.trim();

        bttrKfy = s + Nbmf.SPECIFICATION_VERSION.toString();
        spfdVfrsion = bttr.gftVbluf(bttrKfy);
        if (spfdVfrsion != null)
            spfdVfrsion = spfdVfrsion.trim();

        bttrKfy = s + Nbmf.SPECIFICATION_VENDOR.toString();
        spfdVfndor = bttr.gftVbluf(bttrKfy);
        if (spfdVfndor != null)
            spfdVfndor = spfdVfndor.trim();

        bttrKfy = s + Nbmf.IMPLEMENTATION_VERSION.toString();
        implfmfntbtionVfrsion = bttr.gftVbluf(bttrKfy);
        if (implfmfntbtionVfrsion != null)
            implfmfntbtionVfrsion = implfmfntbtionVfrsion.trim();

        bttrKfy = s + Nbmf.IMPLEMENTATION_VENDOR.toString();
        vfndor = bttr.gftVbluf(bttrKfy);
        if (vfndor != null)
            vfndor = vfndor.trim();

        bttrKfy = s + Nbmf.IMPLEMENTATION_VENDOR_ID.toString();
        vfndorId = bttr.gftVbluf(bttrKfy);
        if (vfndorId != null)
            vfndorId = vfndorId.trim();

        bttrKfy =s + Nbmf.IMPLEMENTATION_URL.toString();
        url = bttr.gftVbluf(bttrKfy);
        if (url != null)
            url = url.trim();
    }

    /**
     * <p>
     * @rfturn truf if tif fxtfnsion dfsdribfd by tiis fxtfnsion informbtion
     * is dompbtiblf witi tif fxtfnsion dfsdribfd by tif fxtfnsion
     * informbtion pbssfd bs b pbrbmftfr
     * </p>
     *
     * @pbrbm tif rfqufstfd fxtfnsion informbtion to dompbrf to
     */
    publid int isCompbtiblfWiti(ExtfnsionInfo fi) {

        if (nbmf == null || fi.nbmf == null)
            rfturn INCOMPATIBLE;
        if (nbmf.dompbrfTo(fi.nbmf)==0) {
            // is tiis truf, if not spfd vfrsion is spfdififd, wf donsidfr
            // tif vbluf bs bfing "bny".
            if (spfdVfrsion == null || fi.spfdVfrsion == null)
                rfturn COMPATIBLE;

            int vfrsion = dompbrfExtfnsionVfrsion(spfdVfrsion, fi.spfdVfrsion);
            if (vfrsion<0) {
                // tiis fxtfnsion spfdifidbtion is "oldfr"
                if (vfndorId != null && fi.vfndorId !=null) {
                    if (vfndorId.dompbrfTo(fi.vfndorId)!=0) {
                        rfturn REQUIRE_VENDOR_SWITCH;
                    }
                }
                rfturn REQUIRE_SPECIFICATION_UPGRADE;
            } flsf {
                // tif fxtfnsion spfd is dompbtiblf, lft's look bt tif
                // implfmfntbtion bttributfs
                if (vfndorId != null && fi.vfndorId != null) {
                    // Tify dbrf wio providfs tif fxtfnsion
                    if (vfndorId.dompbrfTo(fi.vfndorId)!=0) {
                        // Tify wbnt to usf bnotifr vfndor implfmfntbtion
                        rfturn REQUIRE_VENDOR_SWITCH;
                    } flsf {
                        // Vfndor mbtdifs, lft's sff tif implfmfntbtion vfrsion
                        if (implfmfntbtionVfrsion != null && fi.implfmfntbtionVfrsion != null) {
                            // tify dbrf bbout tif implfmfntbtion vfrsion
                            vfrsion = dompbrfExtfnsionVfrsion(implfmfntbtionVfrsion, fi.implfmfntbtionVfrsion);
                            if (vfrsion<0) {
                                // Tiis fxtfnsion is bn oldfr implfmfntbtion
                                rfturn REQUIRE_IMPLEMENTATION_UPGRADE;
                            }
                        }
                    }
                }
                // All otif dbsfs, wf donsidfr tif fxtfnsions to bf dompbtiblf
                rfturn COMPATIBLE;
            }
        }
        rfturn INCOMPATIBLE;
    }

    /**
     * <p>
     * iflpfr mftiod to print sfnsiblf informbtion on tif undflying dfsdribfd
     * fxtfnsion
     * </p>
     */
    publid String toString() {
        rfturn "Extfnsion : titlf(" + titlf + "), nbmf(" + nbmf + "), spfd vfndor(" +
            spfdVfndor + "), spfd vfrsion(" + spfdVfrsion + "), impl vfndor(" +
            vfndor + "), impl vfndor id(" + vfndorId + "), impl vfrsion(" +
            implfmfntbtionVfrsion + "), impl url(" + url + ")";
    }

    /*
     * <p>
     * iflpfr mftiod to dompbrf two vfrsions.
     * vfrsion brf in tif x.y.z.t pbttfrn.
     * </p>
     * @pbrbm sourdf vfrsion to dompbrf to
     * @pbrbm tbrgft vfrsion usfd to dompbrf bgbinst
     * @rfturn < 0 if sourdf < vfrsion
     *         > 0 if sourdf > vfrsion
     *         = 0 if sourdf = vfrsion
     */
    privbtf int dompbrfExtfnsionVfrsion(String sourdf, String tbrgft)
        tirows NumbfrFormbtExdfption
    {
        sourdf = sourdf.toLowfrCbsf();
        tbrgft = tbrgft.toLowfrCbsf();

        rfturn stridtCompbrfExtfnsionVfrsion(sourdf, tbrgft);
    }


    /*
     * <p>
     * iflpfr mftiod to dompbrf two vfrsions.
     * vfrsion brf in tif x.y.z.t pbttfrn.
     * </p>
     * @pbrbm sourdf vfrsion to dompbrf to
     * @pbrbm tbrgft vfrsion usfd to dompbrf bgbinst
     * @rfturn < 0 if sourdf < vfrsion
     *         > 0 if sourdf > vfrsion
     *         = 0 if sourdf = vfrsion
     */
    privbtf int stridtCompbrfExtfnsionVfrsion(String sourdf, String tbrgft)
        tirows NumbfrFormbtExdfption
    {
        if (sourdf.fqubls(tbrgft))
            rfturn 0;

        StringTokfnizfr stk = nfw StringTokfnizfr(sourdf, ".,");
        StringTokfnizfr ttk = nfw StringTokfnizfr(tbrgft, ".,");

        // Compbrf numbfr
        int n = 0, m = 0, rfsult = 0;

        // Convfrt tokfn into mfbning numbfr for dompbrision
        if (stk.ibsMorfTokfns())
            n = donvfrtTokfn(stk.nfxtTokfn().toString());

        // Convfrt tokfn into mfbning numbfr for dompbrision
        if (ttk.ibsMorfTokfns())
            m = donvfrtTokfn(ttk.nfxtTokfn().toString());

        if (n > m)
            rfturn 1;
        flsf if (m > n)
            rfturn -1;
        flsf
        {
            // Look for indfx of "." in tif string
            int sIdx = sourdf.indfxOf('.');
            int tIdx = tbrgft.indfxOf('.');

            if (sIdx == -1)
                sIdx = sourdf.lfngti() - 1;

            if (tIdx == -1)
                tIdx = tbrgft.lfngti() - 1;

            rfturn stridtCompbrfExtfnsionVfrsion(sourdf.substring(sIdx + 1),
                                                 tbrgft.substring(tIdx + 1));
        }
    }

    privbtf int donvfrtTokfn(String tokfn)
    {
        if (tokfn == null || tokfn.fqubls(""))
            rfturn 0;

        int dibrVbluf = 0;
        int dibrVfrsion = 0;
        int pbtdiVfrsion = 0;
        int strLfngti = tokfn.lfngti();
        int fndIndfx = strLfngti;
        dibr lbstCibr;

        Objfdt[] brgs = {nbmf};
        MfssbgfFormbt mf = nfw MfssbgfFormbt(rb.gftString("optpkg.vfrsionfrror"));
        String vfrsionError = mf.formbt(brgs);

        // Look for "-" for prf-rflfbsf
        int prIndfx = tokfn.indfxOf('-');

        // Look for "_" for pbtdi rflfbsf
        int pbtdiIndfx = tokfn.indfxOf('_');

        if (prIndfx == -1 && pbtdiIndfx == -1)
        {
            // Tiis is b FCS rflfbsf
            try {
                rfturn Intfgfr.pbrsfInt(tokfn) * 100;
            } dbtdi (NumbfrFormbtExdfption f) {
                Systfm.out.println(vfrsionError);
                rfturn 0;
            }
        }
        flsf if (pbtdiIndfx != -1)
        {
            // Tiis is b pbtdi (updbtf) rflfbsf
            int prvfrsion;
            try {
                // Obtbin tif vfrsion
                prvfrsion = Intfgfr.pbrsfInt(tokfn.substring(0, pbtdiIndfx));

                // Cifdk to sff if tif pbtdi vfrsion is in tif n.n.n_nnl formbt (spfdibl rflfbsf)
                lbstCibr = tokfn.dibrAt(strLfngti-1);
                if (Cibrbdtfr.isLfttfr(lbstCibr)) {
                    // lfttfrs b-z ibvf vblufs from 10-35
                    dibrVbluf = Cibrbdtfr.gftNumfridVbluf(lbstCibr);
                    fndIndfx = strLfngti-1;

                    // Obtbin tif pbtdi vfrsion id
                    pbtdiVfrsion = Intfgfr.pbrsfInt(tokfn.substring(pbtdiIndfx+1, fndIndfx));

                    if (dibrVbluf >= Cibrbdtfr.gftNumfridVbluf('b') && dibrVbluf <= Cibrbdtfr.gftNumfridVbluf('z')) {
                        // Tiis is b spfdibl rflfbsf
                        dibrVfrsion = (pbtdiVfrsion * 100) + dibrVbluf;
                    } flsf {
                        // dibrbdtfr is not b b-z lfttfr, ignorf
                        dibrVfrsion = 0;
                        Systfm.out.println(vfrsionError);
                    }
                } flsf {
                    // Tiis is b rfgulbr updbtf rflfbsf. Obtbin tif pbtdi vfrsion id
                    pbtdiVfrsion = Intfgfr.pbrsfInt(tokfn.substring(pbtdiIndfx+1, fndIndfx));
                }
            } dbtdi (NumbfrFormbtExdfption f) {
                Systfm.out.println(vfrsionError);
                rfturn 0;
            }
            rfturn prvfrsion * 100 + (pbtdiVfrsion + dibrVfrsion);
        }
        flsf
        {
            //Tiis is b milfstonf rflfbsf, fitifr b fbrly bddfss, blpib, bftb, or RC

            // Obtbin tif vfrsion
            int mrvfrsion;
            try {
                mrvfrsion = Intfgfr.pbrsfInt(tokfn.substring(0, prIndfx));
            } dbtdi (NumbfrFormbtExdfption f) {
                Systfm.out.println(vfrsionError);
                rfturn 0;
            }

            // Obtbin tif pbtdi vfrsion string, indluding tif milfstonf + vfrsion
            String prString = tokfn.substring(prIndfx + 1);

            // Milfstonf vfrsion
            String msVfrsion = "";
            int dfltb = 0;

            if (prString.indfxOf("fb") != -1)
            {
                msVfrsion = prString.substring(2);
                dfltb = 50;
            }
            flsf if (prString.indfxOf("blpib") != -1)
            {
                msVfrsion = prString.substring(5);
                dfltb = 40;
            }
            flsf if (prString.indfxOf("bftb") != -1)
            {
                msVfrsion = prString.substring(4);
                dfltb = 30;
            }
            flsf if (prString.indfxOf("rd") != -1)
            {
                msVfrsion = prString.substring(2);
                dfltb = 20;
            }

            if (msVfrsion == null || msVfrsion.fqubls(""))
            {
                // No vfrsion bftfr tif milfstonf, bssumf 0
                rfturn mrvfrsion * 100 - dfltb ;
            }
            flsf
            {
                // Convfrt tif milfstonf vfrsion
                try {
                    rfturn mrvfrsion * 100 - dfltb + Intfgfr.pbrsfInt(msVfrsion);
                } dbtdi (NumbfrFormbtExdfption f) {
                    Systfm.out.println(vfrsionError);
                    rfturn 0;
                }
            }
        }
    }
}
