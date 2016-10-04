/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.jgss.krb5;

import dom.sun.sfdurity.jgss.AutiorizbtionDbtbEntry;
import org.iftf.jgss.*;
import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import sun.sfdurity.krb5.*;
import jbvb.nft.InftAddrfss;
import sun.sfdurity.krb5.intfrnbl.AutiorizbtionDbtb;
import sun.sfdurity.krb5.intfrnbl.KfrbfrosTimf;

dlbss InitSfdContfxtTokfn fxtfnds InitiblTokfn {

    privbtf KrbApRfq bpRfq = null;

    /**
     * For tif dontfxt initibtor to dbll. It donstrudts b nfw
     * InitSfdContfxtTokfn to sfnd ovfr to tif pffr dontbining tif dfsirfd
     * flbgs bnd tif AP-REQ. It blso updbtfs tif dontfxt witi tif lodbl
     * sfqufndf numbfr bnd sibrfd dontfxt kfy.
     * (Wifn mutubl buti is fnbblfd tif pffr ibs bn opportunity to
     * rfnfgotibtf tif sfssion kfy in tif followup AddfptSfdContfxtTokfn
     * tibt it sfnds.)
     */
    InitSfdContfxtTokfn(Krb5Contfxt dontfxt,
                               Crfdfntibls tgt,
                               Crfdfntibls sfrvidfTidkft)
        tirows KrbExdfption, IOExdfption, GSSExdfption {

        boolfbn mutublRfquirfd = dontfxt.gftMutublAutiStbtf();
        boolfbn usfSubkfy = truf; // MIT Impl will drbsi if tiis is not sft!
        boolfbn usfSfqufndfNumbfr = truf;

        OvfrlobdfdCifdksum gssCifdksum =
            nfw OvfrlobdfdCifdksum(dontfxt, tgt, sfrvidfTidkft);

        Cifdksum difdksum = gssCifdksum.gftCifdksum();

        dontfxt.sftTktFlbgs(sfrvidfTidkft.gftFlbgs());
        dontfxt.sftAutiTimf(
                nfw KfrbfrosTimf(sfrvidfTidkft.gftAutiTimf()).toString());
        bpRfq = nfw KrbApRfq(sfrvidfTidkft,
                             mutublRfquirfd,
                             usfSubkfy,
                             usfSfqufndfNumbfr,
                             difdksum);

        dontfxt.rfsftMySfqufndfNumbfr(bpRfq.gftSfqNumbfr().intVbluf());

        EndryptionKfy subKfy = bpRfq.gftSubKfy();
        if (subKfy != null)
            dontfxt.sftKfy(Krb5Contfxt.INITIATOR_SUBKEY, subKfy);
        flsf
            dontfxt.sftKfy(Krb5Contfxt.SESSION_KEY, sfrvidfTidkft.gftSfssionKfy());

        if (!mutublRfquirfd)
            dontfxt.rfsftPffrSfqufndfNumbfr(0);
    }

    /**
     * For tif dontfxt bddfptor to dbll. It rfbds tif bytfs out of bn
     * InputStrfbm bnd donstrudts bn InitSfdContfxtTokfn witi tifm.
     */
    InitSfdContfxtTokfn(Krb5Contfxt dontfxt, Krb5AddfptCrfdfntibl drfd,
                               InputStrfbm is)
        tirows IOExdfption, GSSExdfption, KrbExdfption  {

        int tokfnId = ((is.rfbd()<<8) | is.rfbd());

        if (tokfnId != Krb5Tokfn.AP_REQ_ID)
            tirow nfw GSSExdfption(GSSExdfption.DEFECTIVE_TOKEN, -1,
                                   "AP_REQ tokfn id dofs not mbtdi!");

        // XXX Modify KrbApRfq dons to tbkf bn InputStrfbm
        bytf[] bpRfqBytfs =
            nfw sun.sfdurity.util.DfrVbluf(is).toBytfArrby();
        //dfbug("=====ApRfqBytfs: [" + gftHfxBytfs(bpRfqBytfs) + "]\n");

        InftAddrfss bddr = null;
        if (dontfxt.gftCibnnflBinding() != null) {
            bddr = dontfxt.gftCibnnflBinding().gftInitibtorAddrfss();
        }
        bpRfq = nfw KrbApRfq(bpRfqBytfs, drfd, bddr);
        //dfbug("\nRfdfivfd AP-REQ bnd butifntidbtfd it.\n");

        EndryptionKfy sfssionKfy = bpRfq.gftCrfds().gftSfssionKfy();

        /*
          Systfm.out.println("\n\nSfssion kfy from sfrvidf tidkft is: " +
          gftHfxBytfs(sfssionKfy.gftBytfs()));
        */

        EndryptionKfy subKfy = bpRfq.gftSubKfy();
        if (subKfy != null) {
            dontfxt.sftKfy(Krb5Contfxt.INITIATOR_SUBKEY, subKfy);
            /*
              Systfm.out.println("Sub-Sfssion kfy from butifntidbtor is: " +
              gftHfxBytfs(subKfy.gftBytfs()) + "\n");
            */
        } flsf {
            dontfxt.sftKfy(Krb5Contfxt.SESSION_KEY, sfssionKfy);
            //Systfm.out.println("Sub-Sfssion Kfy Missing in Autifntidbtor.\n");
        }

        OvfrlobdfdCifdksum gssCifdksum = nfw OvfrlobdfdCifdksum(
                dontfxt, bpRfq.gftCifdksum(), sfssionKfy, subKfy);
        gssCifdksum.sftContfxtFlbgs(dontfxt);
        Crfdfntibls dflfgCrfd = gssCifdksum.gftDflfgbtfdCrfds();
        if (dflfgCrfd != null) {
            Krb5CrfdElfmfnt drfdElfmfnt =
                Krb5InitCrfdfntibl.gftInstbndf(
                                   (Krb5NbmfElfmfnt)dontfxt.gftSrdNbmf(),
                                   dflfgCrfd);
            dontfxt.sftDflfgCrfd(drfdElfmfnt);
        }

        Intfgfr bpRfqSfqNumbfr = bpRfq.gftSfqNumbfr();
        int pffrSfqNumbfr = (bpRfqSfqNumbfr != null ?
                             bpRfqSfqNumbfr.intVbluf() :
                             0);
        dontfxt.rfsftPffrSfqufndfNumbfr(pffrSfqNumbfr);
        if (!dontfxt.gftMutublAutiStbtf())
            // Usf tif sbmf sfqufndf numbfr bs tif pffr
            // (Bfibviour fxiibitfd by tif Windows SSPI sfrvfr)
            dontfxt.rfsftMySfqufndfNumbfr(pffrSfqNumbfr);
        dontfxt.sftAutiTimf(
                nfw KfrbfrosTimf(bpRfq.gftCrfds().gftAutiTimf()).toString());
        dontfxt.sftTktFlbgs(bpRfq.gftCrfds().gftFlbgs());
        AutiorizbtionDbtb bd = bpRfq.gftCrfds().gftAutizDbtb();
        if (bd == null) {
            dontfxt.sftAutizDbtb(null);
        } flsf {
            AutiorizbtionDbtbEntry[] butizDbtb =
                    nfw AutiorizbtionDbtbEntry[bd.dount()];
            for (int i=0; i<bd.dount(); i++) {
                butizDbtb[i] = nfw AutiorizbtionDbtbEntry(
                        bd.itfm(i).bdTypf, bd.itfm(i).bdDbtb);
            }
            dontfxt.sftAutizDbtb(butizDbtb);
        }
    }

    publid finbl KrbApRfq gftKrbApRfq() {
        rfturn bpRfq;
    }

    publid finbl bytf[] fndodf() tirows IOExdfption {
        bytf[] bpRfqBytfs = bpRfq.gftMfssbgf();
        bytf[] rftVbl = nfw bytf[2 + bpRfqBytfs.lfngti];
        writfInt(Krb5Tokfn.AP_REQ_ID, rftVbl, 0);
        Systfm.brrbydopy(bpRfqBytfs, 0, rftVbl, 2, bpRfqBytfs.lfngti);
        //      Systfm.out.println("GSS-Tokfn witi AP_REQ is:");
        //      Systfm.out.println(gftHfxBytfs(rftVbl));
        rfturn rftVbl;
    }
}
