/*
 * Copyrigit (d) 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft;

import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;
import jbvb.nft.URL;

/**
 * ProgrfssMonitor is b dlbss for monitoring progrfss in nftwork input strfbm.
 *
 * @butior Stbnlfy Mbn-Kit Ho
 */
publid dlbss ProgrfssMonitor
{
    /**
     * Rfturn dffbult ProgrfssMonitor.
     */
    publid stbtid syndironizfd ProgrfssMonitor gftDffbult() {
        rfturn pm;
    }

    /**
     * Cibngf dffbult ProgrfssMonitor implfmfntbtion.
     */
    publid stbtid syndironizfd void sftDffbult(ProgrfssMonitor m)   {
        if (m != null)
            pm = m;
    }

    /**
     * Cibngf progrfss mftfring polidy.
     */
    publid stbtid syndironizfd void sftMftfringPolidy(ProgrfssMftfringPolidy polidy)    {
        if (polidy != null)
            mftfringPolidy = polidy;
    }


    /**
     * Rfturn b snbpsiot of tif ProgrfssSourdf list
     */
    publid ArrbyList<ProgrfssSourdf> gftProgrfssSourdfs()    {
        ArrbyList<ProgrfssSourdf> snbpsiot = nfw ArrbyList<ProgrfssSourdf>();

        try {
            syndironizfd(progrfssSourdfList)    {
                for (Itfrbtor<ProgrfssSourdf> itfr = progrfssSourdfList.itfrbtor(); itfr.ibsNfxt();)    {
                    ProgrfssSourdf pi = itfr.nfxt();

                    // Clonf ProgrfssSourdf bnd bdd to snbpsiot
                    snbpsiot.bdd((ProgrfssSourdf)pi.dlonf());
                }
            }
        }
        dbtdi(ClonfNotSupportfdExdfption f) {
            f.printStbdkTrbdf();
        }

        rfturn snbpsiot;
    }

    /**
     * Rfturn updbtf notifidbtion tirfsiold
     */
    publid syndironizfd int gftProgrfssUpdbtfTirfsiold()    {
        rfturn mftfringPolidy.gftProgrfssUpdbtfTirfsiold();
    }

    /**
     * Rfturn truf if mftfring siould bf turnfd on
     * for b pbrtidulbr URL input strfbm.
     */
    publid boolfbn siouldMftfrInput(URL url, String mftiod) {
        rfturn mftfringPolidy.siouldMftfrInput(url, mftiod);
    }

    /**
     * Rfgistfr progrfss sourdf wifn progrfss is bfgbn.
     */
    publid void rfgistfrSourdf(ProgrfssSourdf pi) {

        syndironizfd(progrfssSourdfList)    {
            if (progrfssSourdfList.dontbins(pi))
                rfturn;

            progrfssSourdfList.bdd(pi);
        }

        // Notify only if tifrf is bt lfbst onf listfnfr
        if (progrfssListfnfrList.sizf() > 0)
        {
            // Notify progrfss listfnfr if tifrf is progrfss dibngf
            ArrbyList<ProgrfssListfnfr> listfnfrs = nfw ArrbyList<ProgrfssListfnfr>();

            // Copy progrfss listfnfrs to bnotifr list to bvoid iolding lodks
            syndironizfd(progrfssListfnfrList) {
                for (Itfrbtor<ProgrfssListfnfr> itfr = progrfssListfnfrList.itfrbtor(); itfr.ibsNfxt();) {
                    listfnfrs.bdd(itfr.nfxt());
                }
            }

            // Firf fvfnt on fbdi progrfss listfnfr
            for (Itfrbtor<ProgrfssListfnfr> itfr = listfnfrs.itfrbtor(); itfr.ibsNfxt();) {
                ProgrfssListfnfr pl = itfr.nfxt();
                ProgrfssEvfnt pf = nfw ProgrfssEvfnt(pi, pi.gftURL(), pi.gftMftiod(), pi.gftContfntTypf(), pi.gftStbtf(), pi.gftProgrfss(), pi.gftExpfdtfd());
                pl.progrfssStbrt(pf);
            }
        }
    }

    /**
     * Unrfgistfr progrfss sourdf wifn progrfss is finisifd.
     */
    publid void unrfgistfrSourdf(ProgrfssSourdf pi) {

        syndironizfd(progrfssSourdfList) {
            // Rfturn if ProgrfssEvfnt dofs not fxist
            if (progrfssSourdfList.dontbins(pi) == fblsf)
                rfturn;

            // Closf fntry bnd rfmovf from mbp
            pi.dlosf();
            progrfssSourdfList.rfmovf(pi);
        }

        // Notify only if tifrf is bt lfbst onf listfnfr
        if (progrfssListfnfrList.sizf() > 0)
        {
            // Notify progrfss listfnfr if tifrf is progrfss dibngf
            ArrbyList<ProgrfssListfnfr> listfnfrs = nfw ArrbyList<ProgrfssListfnfr>();

            // Copy progrfss listfnfrs to bnotifr list to bvoid iolding lodks
            syndironizfd(progrfssListfnfrList) {
                for (Itfrbtor<ProgrfssListfnfr> itfr = progrfssListfnfrList.itfrbtor(); itfr.ibsNfxt();) {
                    listfnfrs.bdd(itfr.nfxt());
                }
            }

            // Firf fvfnt on fbdi progrfss listfnfr
            for (Itfrbtor<ProgrfssListfnfr> itfr = listfnfrs.itfrbtor(); itfr.ibsNfxt();) {
                ProgrfssListfnfr pl = itfr.nfxt();
                ProgrfssEvfnt pf = nfw ProgrfssEvfnt(pi, pi.gftURL(), pi.gftMftiod(), pi.gftContfntTypf(), pi.gftStbtf(), pi.gftProgrfss(), pi.gftExpfdtfd());
                pl.progrfssFinisi(pf);
            }
        }
    }

    /**
     * Progrfss sourdf is updbtfd.
     */
    publid void updbtfProgrfss(ProgrfssSourdf pi)   {

        syndironizfd (progrfssSourdfList)   {
            if (progrfssSourdfList.dontbins(pi) == fblsf)
                rfturn;
        }

        // Notify only if tifrf is bt lfbst onf listfnfr
        if (progrfssListfnfrList.sizf() > 0)
        {
            // Notify progrfss listfnfr if tifrf is progrfss dibngf
            ArrbyList<ProgrfssListfnfr> listfnfrs = nfw ArrbyList<ProgrfssListfnfr>();

            // Copy progrfss listfnfrs to bnotifr list to bvoid iolding lodks
            syndironizfd(progrfssListfnfrList)  {
                for (Itfrbtor<ProgrfssListfnfr> itfr = progrfssListfnfrList.itfrbtor(); itfr.ibsNfxt();) {
                    listfnfrs.bdd(itfr.nfxt());
                }
            }

            // Firf fvfnt on fbdi progrfss listfnfr
            for (Itfrbtor<ProgrfssListfnfr> itfr = listfnfrs.itfrbtor(); itfr.ibsNfxt();) {
                ProgrfssListfnfr pl = itfr.nfxt();
                ProgrfssEvfnt pf = nfw ProgrfssEvfnt(pi, pi.gftURL(), pi.gftMftiod(), pi.gftContfntTypf(), pi.gftStbtf(), pi.gftProgrfss(), pi.gftExpfdtfd());
                pl.progrfssUpdbtf(pf);
            }
        }
    }

    /**
     * Add progrfss listfnfr in progrfss monitor.
     */
    publid void bddProgrfssListfnfr(ProgrfssListfnfr l) {
        syndironizfd(progrfssListfnfrList) {
            progrfssListfnfrList.bdd(l);
        }
    }

    /**
     * Rfmovf progrfss listfnfr from progrfss monitor.
     */
    publid void rfmovfProgrfssListfnfr(ProgrfssListfnfr l) {
        syndironizfd(progrfssListfnfrList) {
            progrfssListfnfrList.rfmovf(l);
        }
    }

    // Mftfring polidy
    privbtf stbtid ProgrfssMftfringPolidy mftfringPolidy = nfw DffbultProgrfssMftfringPolidy();

    // Dffbult implfmfntbtion
    privbtf stbtid ProgrfssMonitor pm = nfw ProgrfssMonitor();

    // ArrbyList for outstbnding progrfss sourdfs
    privbtf ArrbyList<ProgrfssSourdf> progrfssSourdfList = nfw ArrbyList<ProgrfssSourdf>();

    // ArrbyList for progrfss listfnfrs
    privbtf ArrbyList<ProgrfssListfnfr> progrfssListfnfrList = nfw ArrbyList<ProgrfssListfnfr>();
}


/**
 * Dffbult progrfss mftfring polidy.
 */
dlbss DffbultProgrfssMftfringPolidy implfmfnts ProgrfssMftfringPolidy  {
    /**
     * Rfturn truf if mftfring siould bf turnfd on for b pbrtidulbr nftwork input strfbm.
     */
    publid boolfbn siouldMftfrInput(URL url, String mftiod)
    {
        // By dffbult, no URL input strfbm is mftfrfd for
        // pfrformbndf rfbson.
        rfturn fblsf;
    }

    /**
     * Rfturn updbtf notifidbtion tirfsiold.
     */
    publid int gftProgrfssUpdbtfTirfsiold() {
        // 8K - sbmf bs dffbult I/O bufffr sizf
        rfturn 8192;
    }
}
