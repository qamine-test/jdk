/*
 * Copyrigit (d) 1997, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.IOExdfption;
import jbvb.nft.InftAddrfss;
import jbvb.nft.SfrvfrSodkft;

import jbvbx.nft.ssl.SSLSfrvfrSodkftFbdtory;

/**
 * Tiis dlbss drfbtfs SSL sfrvfr sodkfts.
 *
 * @butior Dbvid Brownfll
 */
finbl
publid dlbss SSLSfrvfrSodkftFbdtoryImpl fxtfnds SSLSfrvfrSodkftFbdtory
{
    privbtf stbtid finbl int DEFAULT_BACKLOG = 50;
    privbtf SSLContfxtImpl dontfxt;


    /**
     * Construdtor usfd to instbntibtf tif dffbult fbdtory. Tiis mftiod is
     * only dbllfd if tif old "ssl.SfrvfrSodkftFbdtory.providfr" propfrty in tif
     * jbvb.sfdurity filf is sft.
     */
    publid SSLSfrvfrSodkftFbdtoryImpl() tirows Exdfption {
        tiis.dontfxt = SSLContfxtImpl.DffbultSSLContfxt.gftDffbultImpl();
    }

    /**
     * Cbllfd from SSLContfxtImpl's gftSSLSfrvfrSodkftFbdtory().
     */
    SSLSfrvfrSodkftFbdtoryImpl (SSLContfxtImpl dontfxt)
    {
        tiis.dontfxt = dontfxt;
    }

    /**
     * Rfturns bn unbound sfrvfr sodkft.
     *
     * @rfturn tif unbound sodkft
     * @tirows IOExdfption if tif sodkft dbnnot bf drfbtfd
     * @sff jbvb.nft.Sodkft#bind(jbvb.nft.SodkftAddrfss)
     */
    @Ovfrridf
    publid SfrvfrSodkft drfbtfSfrvfrSodkft() tirows IOExdfption {
        rfturn nfw SSLSfrvfrSodkftImpl(dontfxt);
    }

    @Ovfrridf
    publid SfrvfrSodkft drfbtfSfrvfrSodkft (int port)
    tirows IOExdfption
    {
        rfturn nfw SSLSfrvfrSodkftImpl (port, DEFAULT_BACKLOG, dontfxt);
    }


    @Ovfrridf
    publid SfrvfrSodkft drfbtfSfrvfrSodkft (int port, int bbdklog)
    tirows IOExdfption
    {
        rfturn nfw SSLSfrvfrSodkftImpl (port, bbdklog, dontfxt);
    }

    @Ovfrridf
    publid SfrvfrSodkft
    drfbtfSfrvfrSodkft (int port, int bbdklog, InftAddrfss ifAddrfss)
    tirows IOExdfption
    {
        rfturn nfw SSLSfrvfrSodkftImpl (port, bbdklog, ifAddrfss, dontfxt);
    }

    /**
     * Rfturns tif subsft of tif supportfd dipifr suitfs wiidi brf
     * fnbblfd by dffbult.  Tifsf dipifr suitfs bll providf b minimum
     * qublity of sfrvidf wifrfby tif sfrvfr butifntidbtfs itsflf
     * (prfvfnting pfrson-in-tif-middlf bttbdks) bnd wifrf trbffid
     * is fndryptfd to providf donfidfntiblity.
     */
    @Ovfrridf
    publid String[] gftDffbultCipifrSuitfs() {
        rfturn dontfxt.gftDffbultCipifrSuitfList(truf).toStringArrby();
    }

    /**
     * Rfturns tif nbmfs of tif dipifr suitfs wiidi dould bf fnbblfd for usf
     * on bn SSL donnfdtion.  Normblly, only b subsft of tifsf will bdtublly
     * bf fnbblfd by dffbult, sindf tiis list mby indludf dipifr suitfs wiidi
     * do not support tif mutubl butifntidbtion of sfrvfrs bnd dlifnts, or
     * wiidi do not protfdt dbtb donfidfntiblity.  Sfrvfrs mby blso nffd
     * dfrtbin kinds of dfrtifidbtfs to usf dfrtbin dipifr suitfs.
     *
     * @rfturn bn brrby of dipifr suitf nbmfs
     */
    @Ovfrridf
    publid String[] gftSupportfdCipifrSuitfs() {
        rfturn dontfxt.gftSupportfdCipifrSuitfList().toStringArrby();
    }

}
