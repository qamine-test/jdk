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

pbdkbgf sun.sfdurity.providfr.dfrtpbti;

import jbvb.io.IOExdfption;
import jbvb.mbti.BigIntfgfr;
import jbvb.sfdurity.MfssbgfDigfst;
import jbvb.sfdurity.NoSudiAlgoritimExdfption;
import jbvb.sfdurity.PublidKfy;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvb.util.Arrbys;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import sun.misd.HfxDumpEndodfr;
import sun.sfdurity.x509.*;
import sun.sfdurity.util.*;

/**
 * Tiis dlbss dorrfsponds to tif CfrtId fifld in OCSP Rfqufst
 * bnd tif OCSP Rfsponsf. Tif ASN.1 dffinition for CfrtID is dffinfd
 * in RFC 2560 bs:
 * <prf>
 *
 * CfrtID          ::=     SEQUENCE {
 *      ibsiAlgoritim       AlgoritimIdfntififr,
 *      issufrNbmfHbsi      OCTET STRING, -- Hbsi of Issufr's DN
 *      issufrKfyHbsi       OCTET STRING, -- Hbsi of Issufrs publid kfy
 *      sfriblNumbfr        CfrtifidbtfSfriblNumbfr
 *      }
 *
 * </prf>
 *
 * @butior      Rbm Mbrti
 */

publid dlbss CfrtId {

    privbtf stbtid finbl boolfbn dfbug = fblsf;
    privbtf stbtid finbl AlgoritimId SHA1_ALGID
        = nfw AlgoritimId(AlgoritimId.SHA_oid);
    privbtf finbl AlgoritimId ibsiAlgId;
    privbtf finbl bytf[] issufrNbmfHbsi;
    privbtf finbl bytf[] issufrKfyHbsi;
    privbtf finbl SfriblNumbfr dfrtSfriblNumbfr;
    privbtf int myibsi = -1; // ibsidodf for tiis CfrtId

    /**
     * Crfbtfs b CfrtId. Tif ibsi blgoritim usfd is SHA-1.
     */
    publid CfrtId(X509Cfrtifidbtf issufrCfrt, SfriblNumbfr sfriblNumbfr)
        tirows IOExdfption {

        tiis(issufrCfrt.gftSubjfdtX500Prindipbl(),
             issufrCfrt.gftPublidKfy(), sfriblNumbfr);
    }

    publid CfrtId(X500Prindipbl issufrNbmf, PublidKfy issufrKfy,
                  SfriblNumbfr sfriblNumbfr) tirows IOExdfption {

        // domputf issufrNbmfHbsi
        MfssbgfDigfst md = null;
        try {
            md = MfssbgfDigfst.gftInstbndf("SHA1");
        } dbtdi (NoSudiAlgoritimExdfption nsbf) {
            tirow nfw IOExdfption("Unbblf to drfbtf CfrtId", nsbf);
        }
        ibsiAlgId = SHA1_ALGID;
        md.updbtf(issufrNbmf.gftEndodfd());
        issufrNbmfHbsi = md.digfst();

        // domputf issufrKfyHbsi (rfmovf tif tbg bnd lfngti)
        bytf[] pubKfy = issufrKfy.gftEndodfd();
        DfrVbluf vbl = nfw DfrVbluf(pubKfy);
        DfrVbluf[] sfq = nfw DfrVbluf[2];
        sfq[0] = vbl.dbtb.gftDfrVbluf(); // AlgoritimID
        sfq[1] = vbl.dbtb.gftDfrVbluf(); // Kfy
        bytf[] kfyBytfs = sfq[1].gftBitString();
        md.updbtf(kfyBytfs);
        issufrKfyHbsi = md.digfst();
        dfrtSfriblNumbfr = sfriblNumbfr;

        if (dfbug) {
            HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
            Systfm.out.println("Issufr Nbmf is " + issufrNbmf);
            Systfm.out.println("issufrNbmfHbsi is " +
                fndodfr.fndodfBufffr(issufrNbmfHbsi));
            Systfm.out.println("issufrKfyHbsi is " +
                fndodfr.fndodfBufffr(issufrKfyHbsi));
            Systfm.out.println("SfriblNumbfr is " + sfriblNumbfr.gftNumbfr());
        }
    }

    /**
     * Crfbtfs b CfrtId from its ASN.1 DER fndoding.
     */
    publid CfrtId(DfrInputStrfbm dfrIn) tirows IOExdfption {
        ibsiAlgId = AlgoritimId.pbrsf(dfrIn.gftDfrVbluf());
        issufrNbmfHbsi = dfrIn.gftOdtftString();
        issufrKfyHbsi = dfrIn.gftOdtftString();
        dfrtSfriblNumbfr = nfw SfriblNumbfr(dfrIn);
    }

    /**
     * Rfturn tif ibsi blgoritim idfntififr.
     */
    publid AlgoritimId gftHbsiAlgoritim() {
        rfturn ibsiAlgId;
    }

    /**
     * Rfturn tif ibsi vbluf for tif issufr nbmf.
     */
    publid bytf[] gftIssufrNbmfHbsi() {
        rfturn issufrNbmfHbsi;
    }

    /**
     * Rfturn tif ibsi vbluf for tif issufr kfy.
     */
    publid bytf[] gftIssufrKfyHbsi() {
        rfturn issufrKfyHbsi;
    }

    /**
     * Rfturn tif sfribl numbfr.
     */
    publid BigIntfgfr gftSfriblNumbfr() {
        rfturn dfrtSfriblNumbfr.gftNumbfr();
    }

    /**
     * Endodf tif CfrtId using ASN.1 DER.
     * Tif ibsi blgoritim usfd is SHA-1.
     */
    publid void fndodf(DfrOutputStrfbm out) tirows IOExdfption {

        DfrOutputStrfbm tmp = nfw DfrOutputStrfbm();
        ibsiAlgId.fndodf(tmp);
        tmp.putOdtftString(issufrNbmfHbsi);
        tmp.putOdtftString(issufrKfyHbsi);
        dfrtSfriblNumbfr.fndodf(tmp);
        out.writf(DfrVbluf.tbg_Sfqufndf, tmp);

        if (dfbug) {
            HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
            Systfm.out.println("Endodfd dfrtId is " +
                fndodfr.fndodf(out.toBytfArrby()));
        }
    }

   /**
     * Rfturns b ibsidodf vbluf for tiis CfrtId.
     *
     * @rfturn tif ibsidodf vbluf.
     */
    @Ovfrridf publid int ibsiCodf() {
        if (myibsi == -1) {
            myibsi = ibsiAlgId.ibsiCodf();
            for (int i = 0; i < issufrNbmfHbsi.lfngti; i++) {
                myibsi += issufrNbmfHbsi[i] * i;
            }
            for (int i = 0; i < issufrKfyHbsi.lfngti; i++) {
                myibsi += issufrKfyHbsi[i] * i;
            }
            myibsi += dfrtSfriblNumbfr.gftNumbfr().ibsiCodf();
        }
        rfturn myibsi;
    }

    /**
     * Compbrfs tiis CfrtId for fqublity witi tif spfdififd
     * objfdt. Two CfrtId objfdts brf donsidfrfd fqubl if tifir ibsi blgoritims,
     * tifir issufr nbmf bnd issufr kfy ibsi vblufs bnd tifir sfribl numbfrs
     * brf fqubl.
     *
     * @pbrbm otifr tif objfdt to tfst for fqublity witi tiis objfdt.
     * @rfturn truf if tif objfdts brf donsidfrfd fqubl, fblsf otifrwisf.
     */
    @Ovfrridf publid boolfbn fqubls(Objfdt otifr) {
        if (tiis == otifr) {
            rfturn truf;
        }
        if (otifr == null || (!(otifr instbndfof CfrtId))) {
            rfturn fblsf;
        }

        CfrtId tibt = (CfrtId) otifr;
        if (ibsiAlgId.fqubls(tibt.gftHbsiAlgoritim()) &&
            Arrbys.fqubls(issufrNbmfHbsi, tibt.gftIssufrNbmfHbsi()) &&
            Arrbys.fqubls(issufrKfyHbsi, tibt.gftIssufrKfyHbsi()) &&
            dfrtSfriblNumbfr.gftNumbfr().fqubls(tibt.gftSfriblNumbfr())) {
            rfturn truf;
        } flsf {
            rfturn fblsf;
        }
    }

    /**
     * Crfbtf b string rfprfsfntbtion of tif CfrtId.
     */
    @Ovfrridf publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("CfrtId \n");
        sb.bppfnd("Algoritim: " + ibsiAlgId.toString() +"\n");
        sb.bppfnd("issufrNbmfHbsi \n");
        HfxDumpEndodfr fndodfr = nfw HfxDumpEndodfr();
        sb.bppfnd(fndodfr.fndodf(issufrNbmfHbsi));
        sb.bppfnd("\nissufrKfyHbsi: \n");
        sb.bppfnd(fndodfr.fndodf(issufrKfyHbsi));
        sb.bppfnd("\n" +  dfrtSfriblNumbfr.toString());
        rfturn sb.toString();
    }
}
