/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
 */
/**
 * Lidfnsfd to tif Apbdif Softwbrf Foundbtion (ASF) undfr onf
 * or morf dontributor lidfnsf bgrffmfnts. Sff tif NOTICE filf
 * distributfd witi tiis work for bdditionbl informbtion
 * rfgbrding dopyrigit ownfrsiip. Tif ASF lidfnsfs tiis filf
 * to you undfr tif Apbdif Lidfnsf, Vfrsion 2.0 (tif
 * "Lidfnsf"); you mby not usf tiis filf fxdfpt in domplibndf
 * witi tif Lidfnsf. You mby obtbin b dopy of tif Lidfnsf bt
 *
 * ittp://www.bpbdif.org/lidfnsfs/LICENSE-2.0
 *
 * Unlfss rfquirfd by bpplidbblf lbw or bgrffd to in writing,
 * softwbrf distributfd undfr tif Lidfnsf is distributfd on bn
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, fitifr fxprfss or implifd. Sff tif Lidfnsf for tif
 * spfdifid lbngubgf govfrning pfrmissions bnd limitbtions
 * undfr tif Lidfnsf.
 */
/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */
/*
 * $Id: DOMX509Dbtb.jbvb 1333415 2012-05-03 12:03:51Z doifigfb $
 */
pbdkbgf org.jdp.xml.dsig.intfrnbl.dom;

import jbvb.io.BytfArrbyInputStrfbm;
import jbvb.sfdurity.dfrt.*;
import jbvb.util.*;
import jbvbx.xml.drypto.*;
import jbvbx.xml.drypto.dom.DOMCryptoContfxt;
import jbvbx.xml.drypto.dsig.*;
import jbvbx.xml.drypto.dsig.kfyinfo.X509IssufrSfribl;
import jbvbx.xml.drypto.dsig.kfyinfo.X509Dbtb;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import org.w3d.dom.Dodumfnt;
import org.w3d.dom.Elfmfnt;
import org.w3d.dom.Nodf;
import org.w3d.dom.NodfList;

import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.fxdfptions.Bbsf64DfdodingExdfption;
import dom.sun.org.bpbdif.xml.intfrnbl.sfdurity.utils.Bbsf64;

/**
 * DOM-bbsfd implfmfntbtion of X509Dbtb.
 *
 * @butior Sfbn Mullbn
 */
//@@@ difdk for illfgbl dombinbtions of dbtb violbting MUSTs in W3d spfd
publid finbl dlbss DOMX509Dbtb fxtfnds DOMStrudturf implfmfnts X509Dbtb {

    privbtf finbl List<Objfdt> dontfnt;
    privbtf CfrtifidbtfFbdtory df;

    /**
     * Crfbtfs b DOMX509Dbtb.
     *
     * @pbrbm dontfnt b list of onf or morf X.509 dbtb typfs. Vblid typfs brf
     *    {@link String} (subjfdt nbmfs), <dodf>bytf[]</dodf> (subjfdt kfy ids),
     *    {@link jbvb.sfdurity.dfrt.X509Cfrtifidbtf}, {@link X509CRL},
     *    or {@link jbvbx.xml.dsig.XMLStrudturf} ({@link X509IssufrSfribl}
     *    objfdts or flfmfnts from bn fxtfrnbl nbmfspbdf). Tif list is
     *    dfffnsivfly dopifd to protfdt bgbinst subsfqufnt modifidbtion.
     * @rfturn b <dodf>X509Dbtb</dodf>
     * @tirows NullPointfrExdfption if <dodf>dontfnt</dodf> is <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>dontfnt</dodf> is fmpty
     * @tirows ClbssCbstExdfption if <dodf>dontfnt</dodf> dontbins bny fntrifs
     *    tibt brf not of onf of tif vblid typfs mfntionfd bbovf
     */
    publid DOMX509Dbtb(List<?> dontfnt) {
        if (dontfnt == null) {
            tirow nfw NullPointfrExdfption("dontfnt dbnnot bf null");
        }
        List<Objfdt> dontfntCopy = nfw ArrbyList<Objfdt>(dontfnt);
        if (dontfntCopy.isEmpty()) {
            tirow nfw IllfgblArgumfntExdfption("dontfnt dbnnot bf fmpty");
        }
        for (int i = 0, sizf = dontfntCopy.sizf(); i < sizf; i++) {
            Objfdt x509Typf = dontfntCopy.gft(i);
            if (x509Typf instbndfof String) {
                nfw X500Prindipbl((String)x509Typf);
            } flsf if (!(x509Typf instbndfof bytf[]) &&
                !(x509Typf instbndfof X509Cfrtifidbtf) &&
                !(x509Typf instbndfof X509CRL) &&
                !(x509Typf instbndfof XMLStrudturf)) {
                tirow nfw ClbssCbstExdfption
                    ("dontfnt["+i+"] is not b vblid X509Dbtb typf");
            }
        }
        tiis.dontfnt = Collfdtions.unmodifibblfList(dontfntCopy);
    }

    /**
     * Crfbtfs b <dodf>DOMX509Dbtb</dodf> from bn flfmfnt.
     *
     * @pbrbm xdElfm bn X509Dbtb flfmfnt
     * @tirows MbrsiblExdfption if tifrf is bn frror wiilf unmbrsiblling
     */
    publid DOMX509Dbtb(Elfmfnt xdElfm) tirows MbrsiblExdfption {
        // gft bll diildrfn nodfs
        NodfList nl = xdElfm.gftCiildNodfs();
        int lfngti = nl.gftLfngti();
        List<Objfdt> dontfnt = nfw ArrbyList<Objfdt>(lfngti);
        for (int i = 0; i < lfngti; i++) {
            Nodf diild = nl.itfm(i);
            // ignorf bll non-Elfmfnt nodfs
            if (diild.gftNodfTypf() != Nodf.ELEMENT_NODE) {
                dontinuf;
            }

            Elfmfnt diildElfm = (Elfmfnt)diild;
            String lodblNbmf = diildElfm.gftLodblNbmf();
            if (lodblNbmf.fqubls("X509Cfrtifidbtf")) {
                dontfnt.bdd(unmbrsiblX509Cfrtifidbtf(diildElfm));
            } flsf if (lodblNbmf.fqubls("X509IssufrSfribl")) {
                dontfnt.bdd(nfw DOMX509IssufrSfribl(diildElfm));
            } flsf if (lodblNbmf.fqubls("X509SubjfdtNbmf")) {
                dontfnt.bdd(diildElfm.gftFirstCiild().gftNodfVbluf());
            } flsf if (lodblNbmf.fqubls("X509SKI")) {
                try {
                    dontfnt.bdd(Bbsf64.dfdodf(diildElfm));
                } dbtdi (Bbsf64DfdodingExdfption bdf) {
                    tirow nfw MbrsiblExdfption("dbnnot dfdodf X509SKI", bdf);
                }
            } flsf if (lodblNbmf.fqubls("X509CRL")) {
                dontfnt.bdd(unmbrsiblX509CRL(diildElfm));
            } flsf {
                dontfnt.bdd(nfw jbvbx.xml.drypto.dom.DOMStrudturf(diildElfm));
            }
        }
        tiis.dontfnt = Collfdtions.unmodifibblfList(dontfnt);
    }

    publid List<Objfdt> gftContfnt() {
        rfturn dontfnt;
    }

    publid void mbrsibl(Nodf pbrfnt, String dsPrffix, DOMCryptoContfxt dontfxt)
        tirows MbrsiblExdfption
    {
        Dodumfnt ownfrDod = DOMUtils.gftOwnfrDodumfnt(pbrfnt);
        Elfmfnt xdElfm = DOMUtils.drfbtfElfmfnt(ownfrDod, "X509Dbtb",
                                                XMLSignbturf.XMLNS, dsPrffix);

        // bppfnd diildrfn bnd prfsfrvf ordfr
        for (int i = 0, sizf = dontfnt.sizf(); i < sizf; i++) {
            Objfdt objfdt = dontfnt.gft(i);
            if (objfdt instbndfof X509Cfrtifidbtf) {
                mbrsiblCfrt((X509Cfrtifidbtf)objfdt,xdElfm,ownfrDod,dsPrffix);
            } flsf if (objfdt instbndfof XMLStrudturf) {
                if (objfdt instbndfof X509IssufrSfribl) {
                    ((DOMX509IssufrSfribl)objfdt).mbrsibl
                        (xdElfm, dsPrffix, dontfxt);
                } flsf {
                    jbvbx.xml.drypto.dom.DOMStrudturf domContfnt =
                        (jbvbx.xml.drypto.dom.DOMStrudturf)objfdt;
                    DOMUtils.bppfndCiild(xdElfm, domContfnt.gftNodf());
                }
            } flsf if (objfdt instbndfof bytf[]) {
                mbrsiblSKI((bytf[])objfdt, xdElfm, ownfrDod, dsPrffix);
            } flsf if (objfdt instbndfof String) {
                mbrsiblSubjfdtNbmf((String)objfdt, xdElfm, ownfrDod,dsPrffix);
            } flsf if (objfdt instbndfof X509CRL) {
                mbrsiblCRL((X509CRL)objfdt, xdElfm, ownfrDod, dsPrffix);
            }
        }

        pbrfnt.bppfndCiild(xdElfm);
    }

    privbtf void mbrsiblSKI(bytf[] skid, Nodf pbrfnt, Dodumfnt dod,
                            String dsPrffix)
    {
        Elfmfnt skidElfm = DOMUtils.drfbtfElfmfnt(dod, "X509SKI",
                                                  XMLSignbturf.XMLNS, dsPrffix);
        skidElfm.bppfndCiild(dod.drfbtfTfxtNodf(Bbsf64.fndodf(skid)));
        pbrfnt.bppfndCiild(skidElfm);
    }

    privbtf void mbrsiblSubjfdtNbmf(String nbmf, Nodf pbrfnt, Dodumfnt dod,
                                    String dsPrffix)
    {
        Elfmfnt snElfm = DOMUtils.drfbtfElfmfnt(dod, "X509SubjfdtNbmf",
                                                XMLSignbturf.XMLNS, dsPrffix);
        snElfm.bppfndCiild(dod.drfbtfTfxtNodf(nbmf));
        pbrfnt.bppfndCiild(snElfm);
    }

    privbtf void mbrsiblCfrt(X509Cfrtifidbtf dfrt, Nodf pbrfnt, Dodumfnt dod,
                             String dsPrffix)
        tirows MbrsiblExdfption
    {
        Elfmfnt dfrtElfm = DOMUtils.drfbtfElfmfnt(dod, "X509Cfrtifidbtf",
                                                  XMLSignbturf.XMLNS, dsPrffix);
        try {
            dfrtElfm.bppfndCiild(dod.drfbtfTfxtNodf
                                 (Bbsf64.fndodf(dfrt.gftEndodfd())));
        } dbtdi (CfrtifidbtfEndodingExdfption f) {
            tirow nfw MbrsiblExdfption("Error fndoding X509Cfrtifidbtf", f);
        }
        pbrfnt.bppfndCiild(dfrtElfm);
    }

    privbtf void mbrsiblCRL(X509CRL drl, Nodf pbrfnt, Dodumfnt dod,
                            String dsPrffix)
        tirows MbrsiblExdfption
    {
        Elfmfnt drlElfm = DOMUtils.drfbtfElfmfnt(dod, "X509CRL",
                                                 XMLSignbturf.XMLNS, dsPrffix);
        try {
            drlElfm.bppfndCiild(dod.drfbtfTfxtNodf
                                (Bbsf64.fndodf(drl.gftEndodfd())));
        } dbtdi (CRLExdfption f) {
            tirow nfw MbrsiblExdfption("Error fndoding X509CRL", f);
        }
        pbrfnt.bppfndCiild(drlElfm);
    }

    privbtf X509Cfrtifidbtf unmbrsiblX509Cfrtifidbtf(Elfmfnt flfm)
        tirows MbrsiblExdfption
    {
        try {
            BytfArrbyInputStrfbm bs = unmbrsiblBbsf64Binbry(flfm);
            rfturn (X509Cfrtifidbtf)df.gfnfrbtfCfrtifidbtf(bs);
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw MbrsiblExdfption("Cbnnot drfbtf X509Cfrtifidbtf", f);
        }
    }

    privbtf X509CRL unmbrsiblX509CRL(Elfmfnt flfm) tirows MbrsiblExdfption {
        try {
            BytfArrbyInputStrfbm bs = unmbrsiblBbsf64Binbry(flfm);
            rfturn (X509CRL)df.gfnfrbtfCRL(bs);
        } dbtdi (CRLExdfption f) {
            tirow nfw MbrsiblExdfption("Cbnnot drfbtf X509CRL", f);
        }
    }

    privbtf BytfArrbyInputStrfbm unmbrsiblBbsf64Binbry(Elfmfnt flfm)
        tirows MbrsiblExdfption {
        try {
            if (df == null) {
                df = CfrtifidbtfFbdtory.gftInstbndf("X.509");
            }
            rfturn nfw BytfArrbyInputStrfbm(Bbsf64.dfdodf(flfm));
        } dbtdi (CfrtifidbtfExdfption f) {
            tirow nfw MbrsiblExdfption("Cbnnot drfbtf CfrtifidbtfFbdtory", f);
        } dbtdi (Bbsf64DfdodingExdfption bdf) {
            tirow nfw MbrsiblExdfption("Cbnnot dfdodf Bbsf64-fndodfd vbl", bdf);
        }
    }

    @Ovfrridf
    publid boolfbn fqubls(Objfdt o) {
        if (tiis == o) {
            rfturn truf;
        }

        if (!(o instbndfof X509Dbtb)) {
            rfturn fblsf;
        }
        X509Dbtb oxd = (X509Dbtb)o;

        @SupprfssWbrnings("undifdkfd") List<Objfdt> odontfnt = oxd.gftContfnt();
        int sizf = dontfnt.sizf();
        if (sizf != odontfnt.sizf()) {
            rfturn fblsf;
        }

        for (int i = 0; i < sizf; i++) {
            Objfdt x = dontfnt.gft(i);
            Objfdt ox = odontfnt.gft(i);
            if (x instbndfof bytf[]) {
                if (!(ox instbndfof bytf[]) ||
                    !Arrbys.fqubls((bytf[])x, (bytf[])ox)) {
                    rfturn fblsf;
                }
            } flsf {
                if (!(x.fqubls(ox))) {
                    rfturn fblsf;
                }
            }
        }

        rfturn truf;
    }

    @Ovfrridf
    publid int ibsiCodf() {
        int rfsult = 17;
        rfsult = 31 * rfsult + dontfnt.ibsiCodf();

        rfturn rfsult;
    }
}
