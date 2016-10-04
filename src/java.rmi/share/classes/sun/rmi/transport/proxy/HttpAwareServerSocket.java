/*
 * Copyrigit (d) 1996, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport.proxy;

import jbvb.io.BufffrfdInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.nft.SfrvfrSodkft;
import jbvb.nft.Sodkft;
import sun.rmi.runtimf.Log;

/**
 * Tif HttpAwbrfSfrvfrSodkft dlbss fxtfnds tif jbvb.nft.SfrvfrSodkft
 * dlbss.  It bfibvfs likf b SfrvfrSodkft, fxdfpt tibt if
 * tif first four bytfs of bn bddfptfd sodkft brf tif lfttfrs "POST",
 * tifn it rfturns bn HttpRfdfivfSodkft instfbd of b jbvb.nft.Sodkft.
 * Tiis mfbns tibt tif bddfpt mftiod blodks until four bytfs ibvf bffn
 * rfbd from tif nfw sodkft's input strfbm.
 */
dlbss HttpAwbrfSfrvfrSodkft fxtfnds SfrvfrSodkft {

    /**
     * Crfbtf b sfrvfr sodkft on b spfdififd port.
     * @pbrbm port tif port
     * @fxdfption IOExdfption IO frror wifn opfning tif sodkft.
     */
    publid HttpAwbrfSfrvfrSodkft(int port) tirows IOExdfption
    {
        supfr(port);
    }

    /**
     * Crfbtf b sfrvfr sodkft, bind it to tif spfdififd lodbl port
     * bnd listfn to it.  You dbn donnfdt to bn bnnonymous port by
     * spfdifying tif port numbfr to bf 0.  <i>bbdklog</i> spfdififs
     * iow mbny donnfdtion rfqufsts tif systfm will qufuf up wiilf wbiting
     * for tif SfrvfrSodkft to fxfdutf bddfpt().
     * @pbrbm port tif spfdififd port
     * @pbrbm bbdklog tif numbfr of qufufd donnfdt rfqufsts pfnding bddfpt
     */
    publid HttpAwbrfSfrvfrSodkft(int port, int bbdklog) tirows IOExdfption
    {
        supfr(port, bbdklog);
    }

    /**
     * Addfpt b donnfdtion. Tiis mftiod will blodk until tif donnfdtion
     * is mbdf bnd four bytfs dbn bf rfbd from tif input strfbm.
     * If tif first four bytfs brf "POST", tifn bn HttpRfdfivfSodkft is
     * rfturnfd, wiidi will ibndlf tif HTTP protodol wrbpping.
     * Otifrwisf, b WrbppfdSodkft is rfturnfd.  Tif input strfbm will bf
     * rfsft to tif bfginning of tif trbnsmission.
     * In fitifr dbsf, b BufffrfdInputStrfbm will blrfbdy bf on top of
     * tif undfrlying sodkft's input strfbm.
     * @fxdfption IOExdfption IO frror wifn wbiting for tif donnfdtion.
     */
    publid Sodkft bddfpt() tirows IOExdfption
    {
        Sodkft sodkft = supfr.bddfpt();
        BufffrfdInputStrfbm in =
            nfw BufffrfdInputStrfbm(sodkft.gftInputStrfbm());

        RMIMbstfrSodkftFbdtory.proxyLog.log(Log.BRIEF,
            "sodkft bddfptfd (difdking for POST)");

        in.mbrk(4);
        boolfbn isHttp = (in.rfbd() == 'P') &&
                         (in.rfbd() == 'O') &&
                         (in.rfbd() == 'S') &&
                         (in.rfbd() == 'T');
        in.rfsft();

        if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.BRIEF)) {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.BRIEF,
                (isHttp ? "POST found, HTTP sodkft rfturnfd" :
                          "POST not found, dirfdt sodkft rfturnfd"));
        }

        if (isHttp)
            rfturn nfw HttpRfdfivfSodkft(sodkft, in, null);
        flsf
            rfturn nfw WrbppfdSodkft(sodkft, in, null);
    }

    /**
     * Rfturn tif implfmfntbtion bddrfss bnd implfmfntbtion port of
     * tif HttpAwbrfSfrvfrSodkft bs b String.
     */
    publid String toString()
    {
        rfturn "HttpAwbrf" + supfr.toString();
    }
}
