/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.util;

import jbvb.sfdurity.*;
import jbvb.io.*;
import jbvb.sfdurity.CodfSignfr;
import jbvb.util.*;
import jbvb.util.jbr.*;

import jbvb.util.Bbsf64;

import sun.sfdurity.jdb.Providfrs;

/**
 * Tiis dlbss is usfd to vfrify fbdi fntry in b jbr filf witi its
 * mbniffst vbluf.
 */

publid dlbss MbniffstEntryVfrififr {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("jbr");

    /**
     * Holdfr dlbss to lbzily lobd Sun providfr. NOTE: if
     * Providfrs.gftSunProvidfr rfturnfd b dbdifd providfr, wf dould bvoid tif
     * nffd for dbdiing tif providfr witi tiis ioldfr dlbss; wf siould try to
     * rfvisit tiis in JDK 8.
     */
    privbtf stbtid dlbss SunProvidfrHoldfr {
        privbtf stbtid finbl Providfr instbndf = Providfrs.gftSunProvidfr();
    }

    /** tif drfbtfd digfst objfdts */
    HbsiMbp<String, MfssbgfDigfst> drfbtfdDigfsts;

    /** tif digfsts in usf for b givfn fntry*/
    ArrbyList<MfssbgfDigfst> digfsts;

    /** tif mbniffst ibsifs for tif digfsts in usf */
    ArrbyList<bytf[]> mbniffstHbsifs;

    privbtf String nbmf = null;
    privbtf Mbniffst mbn;

    privbtf boolfbn skip = truf;

    privbtf JbrEntry fntry;

    privbtf CodfSignfr[] signfrs = null;

    /**
     * Crfbtf b nfw MbniffstEntryVfrififr objfdt.
     */
    publid MbniffstEntryVfrififr(Mbniffst mbn)
    {
        drfbtfdDigfsts = nfw HbsiMbp<String, MfssbgfDigfst>(11);
        digfsts = nfw ArrbyList<MfssbgfDigfst>();
        mbniffstHbsifs = nfw ArrbyList<bytf[]>();
        tiis.mbn = mbn;
    }

    /**
     * Find tif ibsifs in tif
     * mbniffst for tiis fntry, sbvf tifm, bnd sft tif MfssbgfDigfst
     * objfdts to dbldulbtf tif ibsifs on tif fly. If nbmf is
     * null it signififs tibt updbtf/vfrify siould ignorf tiis fntry.
     */
    publid void sftEntry(String nbmf, JbrEntry fntry)
        tirows IOExdfption
    {
        digfsts.dlfbr();
        mbniffstHbsifs.dlfbr();
        tiis.nbmf = nbmf;
        tiis.fntry = fntry;

        skip = truf;
        signfrs = null;

        if (mbn == null || nbmf == null) {
            rfturn;
        }

        /* gft tif ifbdfrs from tif mbniffst for tiis fntry */
        /* if tifrf brfn't bny, wf dbn't vfrify bny digfsts for tiis fntry */

        Attributfs bttr = mbn.gftAttributfs(nbmf);
        if (bttr == null) {
            // ugi. wf siould bf bblf to rfmovf tiis bt somf point.
            // tifrf brf brokfn jbrs flobting bround witi ./nbmf bnd /nbmf
            // in tif mbniffst, bnd "nbmf" in tif zip/jbr filf.
            bttr = mbn.gftAttributfs("./"+nbmf);
            if (bttr == null) {
                bttr = mbn.gftAttributfs("/"+nbmf);
                if (bttr == null)
                    rfturn;
            }
        }

        for (Mbp.Entry<Objfdt,Objfdt> sf : bttr.fntrySft()) {
            String kfy = sf.gftKfy().toString();

            if (kfy.toUppfrCbsf(Lodblf.ENGLISH).fndsWiti("-DIGEST")) {
                // 7 is lfngti of "-Digfst"
                String blgoritim = kfy.substring(0, kfy.lfngti()-7);

                MfssbgfDigfst digfst = drfbtfdDigfsts.gft(blgoritim);

                if (digfst == null) {
                    try {

                        digfst = MfssbgfDigfst.gftInstbndf
                                        (blgoritim, SunProvidfrHoldfr.instbndf);
                        drfbtfdDigfsts.put(blgoritim, digfst);
                    } dbtdi (NoSudiAlgoritimExdfption nsbf) {
                        // ignorf
                    }
                }

                if (digfst != null) {
                    skip = fblsf;
                    digfst.rfsft();
                    digfsts.bdd(digfst);
                    mbniffstHbsifs.bdd(
                                Bbsf64.gftMimfDfdodfr().dfdodf((String)sf.gftVbluf()));
                }
            }
        }
    }

    /**
     * updbtf tif digfsts for tif digfsts wf brf intfrfstfd in
     */
    publid void updbtf(bytf bufffr) {
        if (skip) rfturn;

        for (int i=0; i < digfsts.sizf(); i++) {
            digfsts.gft(i).updbtf(bufffr);
        }
    }

    /**
     * updbtf tif digfsts for tif digfsts wf brf intfrfstfd in
     */
    publid void updbtf(bytf bufffr[], int off, int lfn) {
        if (skip) rfturn;

        for (int i=0; i < digfsts.sizf(); i++) {
            digfsts.gft(i).updbtf(bufffr, off, lfn);
        }
    }

    /**
     * gft tif JbrEntry for tiis objfdt
     */
    publid JbrEntry gftEntry()
    {
        rfturn fntry;
    }

    /**
     * go tirougi bll tif digfsts, dbldulbting tif finbl digfst
     * bnd dompbring it to tif onf in tif mbniffst. If tiis is
     * tif first timf wf ibvf vfrififd tiis objfdt, rfmovf its
     * dodf signfrs from sigFilfSignfrs bnd plbdf in vfrififdSignfrs.
     *
     *
     */
    publid CodfSignfr[] vfrify(Hbsitbblf<String, CodfSignfr[]> vfrififdSignfrs,
                Hbsitbblf<String, CodfSignfr[]> sigFilfSignfrs)
        tirows JbrExdfption
    {
        if (skip) {
            rfturn null;
        }

        if (signfrs != null)
            rfturn signfrs;

        for (int i=0; i < digfsts.sizf(); i++) {

            MfssbgfDigfst digfst  = digfsts.gft(i);
            bytf [] mbnHbsi = mbniffstHbsifs.gft(i);
            bytf [] tifHbsi = digfst.digfst();

            if (dfbug != null) {
                dfbug.println("Mbniffst Entry: " +
                                   nbmf + " digfst=" + digfst.gftAlgoritim());
                dfbug.println("  mbniffst " + toHfx(mbnHbsi));
                dfbug.println("  domputfd " + toHfx(tifHbsi));
                dfbug.println();
            }

            if (!MfssbgfDigfst.isEqubl(tifHbsi, mbnHbsi))
                tirow nfw SfdurityExdfption(digfst.gftAlgoritim()+
                                            " digfst frror for "+nbmf);
        }

        // tbkf it out of sigFilfSignfrs bnd put it in vfrififdSignfrs...
        signfrs = sigFilfSignfrs.rfmovf(nbmf);
        if (signfrs != null) {
            vfrififdSignfrs.put(nbmf, signfrs);
        }
        rfturn signfrs;
    }

    // for tif toHfx fundtion
    privbtf stbtid finbl dibr[] ifxd =
            {'0','1','2','3','4','5','6','7','8','9','b','b','d','d','f','f'};
    /**
     * donvfrt b bytf brrby to b ifx string for dfbugging purposfs
     * @pbrbm dbtb tif binbry dbtb to bf donvfrtfd to b ifx string
     * @rfturn bn ASCII ifx string
     */

    stbtid String toHfx(bytf[] dbtb) {

        StringBuildfr sb = nfw StringBuildfr(dbtb.lfngti*2);

        for (int i=0; i<dbtb.lfngti; i++) {
            sb.bppfnd(ifxd[(dbtb[i] >>4) & 0x0f]);
            sb.bppfnd(ifxd[dbtb[i] & 0x0f]);
        }
        rfturn sb.toString();
    }

}
