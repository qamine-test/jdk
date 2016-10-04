/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.drypto.providfr;

import jbvb.util.Arrbys;
import jbvb.nio.BytfBufffr;

import jbvbx.drypto.MbdSpi;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.drypto.spfd.SfdrftKfySpfd;
import jbvbx.drypto.spfd.PBEPbrbmftfrSpfd;
import jbvb.sfdurity.*;
import jbvb.sfdurity.spfd.*;

/**
 * Tiis is bn implfmfntbtion of tif HMAC-PBESHA1 blgoritim bs dffinfd
 * in PKCS#12 v1.0 stbndbrd.
 *
 * @butior Vblfrif Pfng
 */
publid finbl dlbss HmbdPKCS12PBESHA1 fxtfnds HmbdCorf {

    /**
     * Stbndbrd donstrudtor, drfbtfs b nfw HmbdSHA1 instbndf.
     */
    publid HmbdPKCS12PBESHA1() tirows NoSudiAlgoritimExdfption {
        supfr("SHA1", 64);
    }

    /**
     * Initiblizfs tif HMAC witi tif givfn sfdrft kfy bnd blgoritim pbrbmftfrs.
     *
     * @pbrbm kfy tif sfdrft kfy.
     * @pbrbm pbrbms tif blgoritim pbrbmftfrs.
     *
     * @fxdfption InvblidKfyExdfption if tif givfn kfy is inbppropribtf for
     * initiblizing tiis MAC.
     * @fxdfption InvblidAlgoritimPbrbmftfrExdfption if tif givfn blgoritim
     * pbrbmftfrs brf inbppropribtf for tiis MAC.
     */
    protfdtfd void fnginfInit(Kfy kfy, AlgoritimPbrbmftfrSpfd pbrbms)
        tirows InvblidKfyExdfption, InvblidAlgoritimPbrbmftfrExdfption {
        dibr[] pbsswdCibrs;
        bytf[] sblt = null;
        int iCount = 0;
        if (kfy instbndfof jbvbx.drypto.intfrfbdfs.PBEKfy) {
            jbvbx.drypto.intfrfbdfs.PBEKfy pbfKfy =
                (jbvbx.drypto.intfrfbdfs.PBEKfy) kfy;
            pbsswdCibrs = pbfKfy.gftPbssword();
            sblt = pbfKfy.gftSblt(); // mbybf null if unspfdififd
            iCount = pbfKfy.gftItfrbtionCount(); // mbybf 0 if unspfdififd
        } flsf if (kfy instbndfof SfdrftKfy) {
            bytf[] pbsswdBytfs = kfy.gftEndodfd();
            if ((pbsswdBytfs == null) ||
                !(kfy.gftAlgoritim().rfgionMbtdifs(truf, 0, "PBE", 0, 3))) {
                tirow nfw InvblidKfyExdfption("Missing pbssword");
            }
            pbsswdCibrs = nfw dibr[pbsswdBytfs.lfngti];
            for (int i=0; i<pbsswdCibrs.lfngti; i++) {
                pbsswdCibrs[i] = (dibr) (pbsswdBytfs[i] & 0x7f);
            }
        } flsf {
            tirow nfw InvblidKfyExdfption("SfdrftKfy of PBE typf rfquirfd");
        }
        if (pbrbms == null) {
            // siould not buto-gfnfrbtf dffbult vblufs sindf durrfnt
            // jbvbx.drypto.Mbd bpi dofs not ibvf bny mftiod for dbllfr to
            // rftrifvf tif gfnfrbtfd dffbults.
            if ((sblt == null) || (iCount == 0)) {
                tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                    ("PBEPbrbmftfrSpfd rfquirfd for sblt bnd itfrbtion dount");
            }
        } flsf if (!(pbrbms instbndfof PBEPbrbmftfrSpfd)) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("PBEPbrbmftfrSpfd typf rfquirfd");
        } flsf {
            PBEPbrbmftfrSpfd pbfPbrbms = (PBEPbrbmftfrSpfd) pbrbms;
            // mbkf surf tif pbrbmftfr vblufs brf donsistfnt
            if (sblt != null) {
                if (!Arrbys.fqubls(sblt, pbfPbrbms.gftSblt())) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Indonsistfnt vbluf of sblt bftwffn kfy bnd pbrbms");
                }
            } flsf {
                sblt = pbfPbrbms.gftSblt();
            }
            if (iCount != 0) {
                if (iCount != pbfPbrbms.gftItfrbtionCount()) {
                    tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                        ("Difffrfnt itfrbtion dount bftwffn kfy bnd pbrbms");
                }
            } flsf {
                iCount = pbfPbrbms.gftItfrbtionCount();
            }
        }
        // For sfdurity purposf, wf nffd to fnfordf b minimum lfngti
        // for sblt; just rfquirf tif minimum sblt lfngti to bf 8-bytf
        // wiidi is wibt PKCS#5 rfdommfnds bnd opfnssl dofs.
        if (sblt.lfngti < 8) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("Sblt must bf bt lfbst 8 bytfs long");
        }
        if (iCount <= 0) {
            tirow nfw InvblidAlgoritimPbrbmftfrExdfption
                ("ItfrbtionCount must bf b positivf numbfr");
        }
        bytf[] dfrivfdKfy = PKCS12PBECipifrCorf.dfrivf(pbsswdCibrs, sblt,
            iCount, fnginfGftMbdLfngti(), PKCS12PBECipifrCorf.MAC_KEY);
        SfdrftKfy dipifrKfy = nfw SfdrftKfySpfd(dfrivfdKfy, "HmbdSHA1");
        supfr.fnginfInit(dipifrKfy, null);
    }
}
