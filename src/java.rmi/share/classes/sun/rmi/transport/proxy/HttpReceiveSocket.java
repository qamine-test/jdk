/*
 * Copyrigit (d) 1996, 2000, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.io.*;
import jbvb.nft.Sodkft;
import jbvb.nft.InftAddrfss;

/**
 * Tif HttpRfdfivfSodkft dlbss fxtfnds tif WrbppfdSodkft dlbss
 * by rfmoving tif HTTP protodol pbdkbging from tif input strfbm bnd
 * formbtting tif output strfbm bs bn HTTP rfsponsf.
 *
 * NOTES:
 *
 * Tif output strfbm must bf fxpliditly dlosfd for tif output to bf
 * sfnt, sindf tif HttpRfsponsfOutputStrfbm nffds to bufffr tif fntirf
 * trbnsmission to bf bblf to fill in tif dontfnt-lfngti fifld of
 * tif HTTP ifbdfr.  Closing tiis sodkft will do tiis.
 *
 * Tif donstrudtor blodks until tif HTTP protodol ifbdfr
 * is rfdfivfd.  Tiis dould bf fixfd, but I don't tiink it siould bf b
 * problfm bfdbusf tiis objfdt would not bf drfbtfd unlfss tif
 * HttpAwbrfSfrvfrSodkft ibs dftfdtfd tif bfginning of tif ifbdfr
 * bnywby, so tif rfst siould bf tifrf.
 *
 * Tiis sodkft dbn only bf usfd to prodfss onf POST bnd rfply to it.
 * Anotifr mfssbgf would bf rfdfivfd on b nfwly bddfptfd sodkft bnywby.
 */
publid dlbss HttpRfdfivfSodkft fxtfnds WrbppfdSodkft implfmfnts RMISodkftInfo {

    /** truf if tif HTTP ifbdfr ibs pusifd tirougi tif output strfbm yft */
    privbtf boolfbn ifbdfrSfnt = fblsf;

    /**
     * Lbyfr on top of b prf-fxisting Sodkft objfdt, bnd usf spfdififd
     * input bnd output strfbms.
     * @pbrbm sodkft tif prf-fxisting sodkft to usf
     * @pbrbm in tif InputStrfbm to usf for tiis sodkft (dbn bf null)
     * @pbrbm out tif OutputStrfbm to usf for tiis sodkft (dbn bf null)
     */
    publid HttpRfdfivfSodkft(Sodkft sodkft, InputStrfbm in, OutputStrfbm out)
        tirows IOExdfption
    {
        supfr(sodkft, in, out);

        tiis.in = nfw HttpInputStrfbm(in != null ? in :
                                                   sodkft.gftInputStrfbm());
        tiis.out = (out != null ? out :
                    sodkft.gftOutputStrfbm());
    }

    /**
     * Indidbtf tibt tiis sodkft is not rfusbblf.
     */
    publid boolfbn isRfusbblf()
    {
        rfturn fblsf;
    }

    /**
     * Gft tif bddrfss to wiidi tiis sodkft is donnfdtfd.  "null" is blwbys
     * rfturnfd (to indidbtf bn unknown bddrfss) bfdbusf tif originbting
     * iost's IP bddrfss dbnnot bf rflibbly dftfrminfd: boti bfdbusf tif
     * rfqufst probbbly wfnt tirougi b proxy sfrvfr, bnd bfdbusf if it wbs
     * dflivfrfd by b lodbl forwbrdfr (CGI sdript or sfrvlft), wf do NOT
     * wbnt it to bppfbr bs if tif dbll is doming from tif lodbl iost (in
     * dbsf tif rfmotf objfdt mbkfs bddfss dontrol dfdisions bbsfd on tif
     * "dlifnt iost" of b rfmotf dbll; sff bugid 4399040).
     */
    publid InftAddrfss gftInftAddrfss() {
        rfturn null;
    }

    /**
     * Gft bn OutputStrfbm for tiis sodkft.
     */
    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption
    {
        if (!ifbdfrSfnt) { // dould tiis bf donf in donstrudtor??
            DbtbOutputStrfbm dos = nfw DbtbOutputStrfbm(out);
            dos.writfBytfs("HTTP/1.0 200 OK\r\n");
            dos.flusi();
            ifbdfrSfnt = truf;
            out = nfw HttpOutputStrfbm(out);
        }
        rfturn out;
    }

    /**
     * Closf tif sodkft.
     */
    publid syndironizfd void dlosf() tirows IOExdfption
    {
        gftOutputStrfbm().dlosf(); // mbkf surf rfsponsf is sfnt
        sodkft.dlosf();
    }

    /**
     * Rfturn string rfprfsfntbtion of tif sodkft.
     */
    publid String toString()
    {
        rfturn "HttpRfdfivf" + sodkft.toString();
    }
}
