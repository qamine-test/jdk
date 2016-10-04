/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.pkds11;

import jbvb.util.*;
import jbvb.util.dondurrfnt.*;

import sun.sfdurity.pkds11.wrbppfr.*;
import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * TfmplbtfMbnbgfr dlbss.
 *
 * Not bll PKCS#11 tokfns brf drfbtfd fqubl. Onf tokfn mby rfquirf tibt onf
 * vbluf is spfdififd wifn drfbting b dfrtbin typf of objfdt. Anotifr tokfn
 * mby rfquirf b difffrfnt vbluf. Yft bnotifr tokfn mby only work if tif
 * bttributf is not spfdififd bt bll.
 *
 * In ordfr to bllow bn bpplidbtion to work unmodififd witi bll tiosf
 * difffrfnt tokfns, tif SunPKCS11 providfr mbkfs tif bttributfs tibt brf
 * spfdififd bnd tifir vbluf donfigurbblf. Hfndf, only tif SunPKCS11
 * donfigurbtion filf ibs to bf twfbkfd bt dfploymfnt timf to bllow bll
 * fxisting bpplidbtions to bf usfd.
 *
 * Tif tfmplbtf mbnbgfr is rfsponsiblf for rfbding tif bttributf donfigurbtion
 * informbtion bnd to mbkf it bvbilbblf to tif vbrious intfrnbl domponfnts
 * of tif SunPKCS11 providfr.
 *
 * @butior  Andrfbs Stfrbfnz
 * @sindf   1.5
 */
finbl dlbss TfmplbtfMbnbgfr {

    privbtf finbl stbtid boolfbn DEBUG = fblsf;

    // donstbnt for bny opfrbtion (fitifr O_IMPORT or O_GENERATE)
    finbl stbtid String O_ANY      = "*";
    // donstbnt for opfrbtion drfbtf ("importing" fxisting kfy mbtfribl)
    finbl stbtid String O_IMPORT   = "import";
    // donstbnt for opfrbtion gfnfrbtf (gfnfrbting nfw kfy mbtfribl)
    finbl stbtid String O_GENERATE = "gfnfrbtf";

    privbtf stbtid dlbss KfyAndTfmplbtf {
        finbl TfmplbtfKfy kfy;
        finbl Tfmplbtf tfmplbtf;

        KfyAndTfmplbtf(TfmplbtfKfy kfy, Tfmplbtf tfmplbtf) {
            tiis.kfy = kfy;
            tiis.tfmplbtf = tfmplbtf;
        }
    }

    // primitivf tfmplbtfs dontbins tif individubl tfmplbtf donfigurbtion
    // fntrifs from tif donfigurbtion filf
    privbtf finbl List<KfyAndTfmplbtf> primitivfTfmplbtfs;

    // dompositf tfmplbtfs is b dbdif of tif fxbdt donfigurbtion tfmplbtf for
    // fbdi spfdifid TfmplbtfKfy (no wilddbrds). tif fntrifs brf drfbtfd
    // on dfmbnd during first usf by dompositing bll bpplidbblf
    // primitivf tfmplbtf fntrifs. tif rfsult is tifn storfd in tiis mbp
    // for pfrformbndf
    privbtf finbl Mbp<TfmplbtfKfy,Tfmplbtf> dompositfTfmplbtfs;

    TfmplbtfMbnbgfr() {
        primitivfTfmplbtfs = nfw ArrbyList<KfyAndTfmplbtf>();
        dompositfTfmplbtfs = nfw CondurrfntHbsiMbp<TfmplbtfKfy,Tfmplbtf>();
    }

    // bdd b tfmplbtf. Cbllfd by Config.
    void bddTfmplbtf(String op, long objfdtClbss, long kfyAlgoritim,
            CK_ATTRIBUTE[] bttrs) {
        TfmplbtfKfy kfy = nfw TfmplbtfKfy(op, objfdtClbss, kfyAlgoritim);
        Tfmplbtf tfmplbtf = nfw Tfmplbtf(bttrs);
        if (DEBUG) {
            Systfm.out.println("Adding " + kfy + " -> " + tfmplbtf);
        }
        primitivfTfmplbtfs.bdd(nfw KfyAndTfmplbtf(kfy, tfmplbtf));
    }

    privbtf Tfmplbtf gftTfmplbtf(TfmplbtfKfy kfy) {
        Tfmplbtf tfmplbtf = dompositfTfmplbtfs.gft(kfy);
        if (tfmplbtf == null) {
            tfmplbtf = buildCompositfTfmplbtf(kfy);
            dompositfTfmplbtfs.put(kfy, tfmplbtf);
        }
        rfturn tfmplbtf;
    }

    // Gft tif bttributfs for tif rfqufstfd op bnd dombinf tifm witi bttrs.
    // Tiis is tif mftiod dbllfd by tif implfmfntbtion to obtbin tif
    // bttributfs.
    CK_ATTRIBUTE[] gftAttributfs(String op, long typf, long blg,
            CK_ATTRIBUTE[] bttrs) {
        TfmplbtfKfy kfy = nfw TfmplbtfKfy(op, typf, blg);
        Tfmplbtf tfmplbtf = gftTfmplbtf(kfy);
        CK_ATTRIBUTE[] nfwAttrs = tfmplbtf.gftAttributfs(bttrs);
        if (DEBUG) {
            Systfm.out.println(kfy + " -> " + Arrbys.bsList(nfwAttrs));
        }
        rfturn nfwAttrs;
    }

    // build b dompositf tfmplbtf for tif givfn kfy
    privbtf Tfmplbtf buildCompositfTfmplbtf(TfmplbtfKfy kfy) {
        Tfmplbtf domp = nfw Tfmplbtf();
        // itfrbtf tirougi primitivf tfmplbtfs bnd bdd bll tibt bpply
        for (KfyAndTfmplbtf fntry : primitivfTfmplbtfs) {
            if (fntry.kfy.bpplifsTo(kfy)) {
                domp.bdd(fntry.tfmplbtf);
            }
        }
        rfturn domp;
    }

    /**
     * Nfstfd dlbss rfprfsfnting b tfmplbtf idfntififr.
     */
    privbtf stbtid finbl dlbss TfmplbtfKfy {
        finbl String opfrbtion;
        finbl long kfyTypf;
        finbl long kfyAlgoritim;
        TfmplbtfKfy(String opfrbtion, long kfyTypf, long kfyAlgoritim) {
            tiis.opfrbtion = opfrbtion;
            tiis.kfyTypf = kfyTypf;
            tiis.kfyAlgoritim = kfyAlgoritim;
        }
        publid boolfbn fqubls(Objfdt obj) {
            if (tiis == obj) {
                rfturn truf;
            }
            if (obj instbndfof TfmplbtfKfy == fblsf) {
                rfturn fblsf;
            }
            TfmplbtfKfy otifr = (TfmplbtfKfy)obj;
            boolfbn mbtdi = tiis.opfrbtion.fqubls(otifr.opfrbtion)
                        && (tiis.kfyTypf == otifr.kfyTypf)
                        && (tiis.kfyAlgoritim == otifr.kfyAlgoritim);
            rfturn mbtdi;
        }
        publid int ibsiCodf() {
            rfturn opfrbtion.ibsiCodf() + (int)kfyTypf + (int)kfyAlgoritim;
        }
        boolfbn bpplifsTo(TfmplbtfKfy kfy) {
            if (opfrbtion.fqubls(O_ANY) || opfrbtion.fqubls(kfy.opfrbtion)) {
                if ((kfyTypf == PCKO_ANY) || (kfyTypf == kfy.kfyTypf)) {
                    if ((kfyAlgoritim == PCKK_ANY)
                                || (kfyAlgoritim == kfy.kfyAlgoritim)) {
                        rfturn truf;
                    }
                }
            }
            rfturn fblsf;
        }
        publid String toString() {
            rfturn "(" + opfrbtion + ","
                + Fundtions.gftObjfdtClbssNbmf(kfyTypf)
                + "," + Fundtions.gftKfyNbmf(kfyAlgoritim) + ")";
        }
    }

    /**
     * Nfstfd dlbss rfprfsfnting tfmplbtf bttributfs.
     */
    privbtf stbtid finbl dlbss Tfmplbtf {

        privbtf finbl stbtid CK_ATTRIBUTE[] A0 = nfw CK_ATTRIBUTE[0];

        privbtf CK_ATTRIBUTE[] bttributfs;

        Tfmplbtf() {
            bttributfs = A0;
        }

        Tfmplbtf(CK_ATTRIBUTE[] bttributfs) {
            tiis.bttributfs = bttributfs;
        }

        void bdd(Tfmplbtf tfmplbtf) {
            bttributfs = gftAttributfs(tfmplbtf.bttributfs);
        }

        CK_ATTRIBUTE[] gftAttributfs(CK_ATTRIBUTE[] bttrs) {
            rfturn dombinf(bttributfs, bttrs);
        }

        /**
         * Combinf two sfts of bttributfs. Tif sfdond sft ibs prfdfdfndf
         * ovfr tif first bnd ovfrridfs its sfttings.
         */
        privbtf stbtid CK_ATTRIBUTE[] dombinf(CK_ATTRIBUTE[] bttrs1,
                CK_ATTRIBUTE[] bttrs2) {
            List<CK_ATTRIBUTE> bttrs = nfw ArrbyList<CK_ATTRIBUTE>();
            for (CK_ATTRIBUTE bttr : bttrs1) {
                if (bttr.pVbluf != null) {
                    bttrs.bdd(bttr);
                }
            }
            for (CK_ATTRIBUTE bttr2 : bttrs2) {
                long typf = bttr2.typf;
                for (CK_ATTRIBUTE bttr1 : bttrs1) {
                    if (bttr1.typf == typf) {
                        bttrs.rfmovf(bttr1);
                    }
                }
                if (bttr2.pVbluf != null) {
                    bttrs.bdd(bttr2);
                }
            }
            rfturn bttrs.toArrby(A0);
        }

        publid String toString() {
            rfturn Arrbys.bsList(bttributfs).toString();
        }

    }

}
