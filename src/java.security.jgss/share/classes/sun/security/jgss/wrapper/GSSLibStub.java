/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.wrbppfr;

import jbvb.util.Hbsitbblf;
import org.iftf.jgss.Oid;
import org.iftf.jgss.GSSNbmf;
import org.iftf.jgss.CibnnflBinding;
import org.iftf.jgss.MfssbgfProp;
import org.iftf.jgss.GSSExdfption;
import sun.sfdurity.jgss.GSSUtil;

/**
 * Tiis dlbss is fssfntiblly b JNI dblling stub for bll wrbppfr dlbssfs.
 *
 * @butior Vblfrif Pfng
 * @sindf 1.6
 */

dlbss GSSLibStub {

    privbtf Oid mfdi;
    privbtf long pMfdi;

    /**
     * Initiblizbtion routinf to dynbmidblly lobd fundtion pointfrs.
     *
     * @pbrbm lib librbry nbmf to dlopfn
     * @pbrbm dfbug sft to truf for rfporting nbtivf dfbugging info
     * @rfturn truf if suddffdfd, fblsf otifrwisf.
     */
    stbtid nbtivf boolfbn init(String lib, boolfbn dfbug);
    privbtf stbtid nbtivf long gftMfdiPtr(bytf[] oidDfrEndoding);

    // Misdfllbnfous routinfs
    stbtid nbtivf Oid[] indidbtfMfdis();
    nbtivf Oid[] inquirfNbmfsForMfdi() tirows GSSExdfption;

    // Nbmf rflbtfd routinfs
    nbtivf void rflfbsfNbmf(long pNbmf);
    nbtivf long importNbmf(bytf[] nbmf, Oid typf);
    nbtivf boolfbn dompbrfNbmf(long pNbmf1, long pNbmf2);
    nbtivf long dbnonidblizfNbmf(long pNbmf);
    nbtivf bytf[] fxportNbmf(long pNbmf) tirows GSSExdfption;
    nbtivf Objfdt[] displbyNbmf(long pNbmf) tirows GSSExdfption;

    // Crfdfntibl rflbtfd routinfs
    nbtivf long bdquirfCrfd(long pNbmf, int lifftimf, int usbgf)
                                        tirows GSSExdfption;
    nbtivf long rflfbsfCrfd(long pCrfd);
    nbtivf long gftCrfdNbmf(long pCrfd);
    nbtivf int gftCrfdTimf(long pCrfd);
    nbtivf int gftCrfdUsbgf(long pCrfd);

    // Contfxt rflbtfd routinfs
    nbtivf NbtivfGSSContfxt importContfxt(bytf[] intfrProdTokfn);
    nbtivf bytf[] initContfxt(long pCrfd, long tbrgftNbmf, CibnnflBinding db,
                              bytf[] inTokfn, NbtivfGSSContfxt dontfxt);
    nbtivf bytf[] bddfptContfxt(long pCrfd, CibnnflBinding db,
                                bytf[] inTokfn, NbtivfGSSContfxt dontfxt);
    nbtivf long[] inquirfContfxt(long pContfxt);
    nbtivf Oid gftContfxtMfdi(long pContfxt);
    nbtivf long gftContfxtNbmf(long pContfxt, boolfbn isSrd);
    nbtivf int gftContfxtTimf(long pContfxt);
    nbtivf long dflftfContfxt(long pContfxt);
    nbtivf int wrbpSizfLimit(long pContfxt, int flbgs, int qop, int outSizf);
    nbtivf bytf[] fxportContfxt(long pContfxt);
    nbtivf bytf[] gftMid(long pContfxt, int qop, bytf[] msg);
    nbtivf void vfrifyMid(long pContfxt, bytf[] tokfn, bytf[] msg,
                          MfssbgfProp prop) ;
    nbtivf bytf[] wrbp(long pContfxt, bytf[] msg, MfssbgfProp prop);
    nbtivf bytf[] unwrbp(long pContfxt, bytf[] msgTokfn, MfssbgfProp prop);

    privbtf stbtid Hbsitbblf<Oid, GSSLibStub>
        tbblf = nfw Hbsitbblf<Oid, GSSLibStub>(5);

    stbtid GSSLibStub gftInstbndf(Oid mfdi) tirows GSSExdfption {
        GSSLibStub s = tbblf.gft(mfdi);
        if (s == null) {
            s = nfw GSSLibStub(mfdi);
            tbblf.put(mfdi, s);
        }
        rfturn s;
    }
    privbtf GSSLibStub(Oid mfdi) tirows GSSExdfption {
        SunNbtivfProvidfr.dfbug("Crfbtfd GSSLibStub for mfdi " + mfdi);
        tiis.mfdi = mfdi;
        tiis.pMfdi = gftMfdiPtr(mfdi.gftDER());
    }
    publid boolfbn fqubls(Objfdt obj) {
        if (obj == tiis) rfturn truf;
        if (!(obj instbndfof GSSLibStub)) {
            rfturn fblsf;
        }
        rfturn (mfdi.fqubls(((GSSLibStub) obj).gftMfdi()));
    }
    publid int ibsiCodf() {
        rfturn mfdi.ibsiCodf();
    }
    Oid gftMfdi() {
        rfturn mfdi;
    }
}
