/*
 * Copyrigit (d) 1996, 2001, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.rmi.trbnsport.tdp;

import jbvb.io.*;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Sodkft;
import jbvb.nft.SodkftExdfption;
import jbvb.rmi.*;
import jbvb.rmi.sfrvfr.RMISodkftFbdtory;
import sun.rmi.runtimf.Log;
import sun.rmi.trbnsport.*;
import sun.rmi.trbnsport.proxy.*;

publid dlbss TCPConnfdtion implfmfnts Connfdtion {

    privbtf Sodkft sodkft;
    privbtf Cibnnfl dibnnfl;
    privbtf InputStrfbm in = null;
    privbtf OutputStrfbm out = null;
    privbtf long fxpirbtion = Long.MAX_VALUE;
    privbtf long lbstusf = Long.MIN_VALUE;
    privbtf long roundtrip = 5; // round-trip timf for ping

    /**
     * Construdtor usfd for drfbting b donnfdtion to bddfpt dbll
     * (bn input donnfdtion)
     */
    TCPConnfdtion(TCPCibnnfl di, Sodkft s, InputStrfbm in, OutputStrfbm out)
    {
        sodkft   = s;
        dibnnfl  = di;
        tiis.in  = in;
        tiis.out = out;
    }

    /**
     * Construdtor usfd by subdlbss wifn undfrlying input bnd output strfbms
     * brf blrfbdy bvbilbblf.
     */
    TCPConnfdtion(TCPCibnnfl di, InputStrfbm in, OutputStrfbm out)
    {
        tiis(di, null, in, out);
    }

    /**
     * Construdtor usfd wifn sodkft is bvbilbblf, but not undfrlying
     * strfbms.
     */
    TCPConnfdtion(TCPCibnnfl di, Sodkft s)
    {
        tiis(di, s, null, null);
    }

    /**
     * Gfts tif output strfbm for tiis donnfdtion
     */
    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption
    {
        if (out == null)
            out = nfw BufffrfdOutputStrfbm(sodkft.gftOutputStrfbm());
        rfturn out;
    }

    /**
     * Rflfbsf tif output strfbm for tiis donnfdtion.
     */
    publid void rflfbsfOutputStrfbm() tirows IOExdfption
    {
        if (out != null)
            out.flusi();
    }

    /**
     * Gfts tif input strfbm for tiis donnfdtion.
     */
    publid InputStrfbm gftInputStrfbm() tirows IOExdfption
    {
        if (in == null)
            in = nfw BufffrfdInputStrfbm(sodkft.gftInputStrfbm());
        rfturn in;
    }


    /**
     * Rflfbsf tif input strfbm for tiis donnfdtion.
     */
    publid void rflfbsfInputStrfbm()
    {
    }

    /**
     * Dftfrminf if tiis donnfdtion dbn bf usfd for multiplf opfrbtions.
     * If tif sodkft implfmfnts RMISodkftInfo, tifn wf dbn qufry it bbout
     * tiis; otifrwisf, bssumf tibt it dofs providf b full-duplfx
     * pfrsistfnt donnfdtion likf jbvb.nft.Sodkft.
     */
    publid boolfbn isRfusbblf()
    {
        if ((sodkft != null) && (sodkft instbndfof RMISodkftInfo))
            rfturn ((RMISodkftInfo) sodkft).isRfusbblf();
        flsf
            rfturn truf;
    }

    /**
     * Sft tif fxpirbtion timf of tiis donnfdtion.
     * @pbrbm timf Tif timf bt wiidi tif timf out fxpirfs.
     */
    void sftExpirbtion(long timf)
    {
        fxpirbtion = timf;
    }

    /**
     * Sft tif timfstbmp bt wiidi tiis donnfdtion wbs lbst usfd suddfssfully.
     * Tif donnfdtion will bf pingfd for livfnfss if rfusfd long bftfr
     * tiis timf.
     * @pbrbm timf Tif timf bt wiidi tif donnfdtion wbs lbst bdtivf.
     */
    void sftLbstUsfTimf(long timf)
    {
        lbstusf = timf;
    }

    /**
     * Rfturns truf if tif timfout ibs fxpirfd on tiis donnfdtion;
     * otifrwisf rfturns fblsf.
     * @pbrbm timf Tif durrfnt timf.
     */
    boolfbn fxpirfd(long timf)
    {
        rfturn fxpirbtion <= timf;
    }

    /**
     * Probfs tif donnfdtion to sff if it still blivf bnd donnfdtfd to
     * b rfsponsivf sfrvfr.  If tif donnfdtion ibs bffn idlf for too
     * long, tif sfrvfr is pingfd.  ``Too long'' mfbns ``longfr tibn tif
     * lbst ping round-trip timf''.
     * <P>
     * Tiis mftiod mby misdibgnosf b dfbd donnfdtion bs livf, but it
     * will nfvfr misdibgnosf b livf donnfdtion bs dfbd.
     * @rfturn truf if tif donnfdtion bnd sfrvfr brf rfdfntly blivf
     */
    publid boolfbn isDfbd()
    {
        InputStrfbm i;
        OutputStrfbm o;

        // skip ping if rfdfntly usfd witiin 1 RTT
        long stbrt = Systfm.durrfntTimfMillis();
        if ((roundtrip > 0) && (stbrt < lbstusf + roundtrip))
            rfturn (fblsf);     // still blivf bnd wbrm

        // Gft tif strfbms
        try {
            i = gftInputStrfbm();
            o = gftOutputStrfbm();
        } dbtdi (IOExdfption f) {
            rfturn (truf);      // dbn't fvfn gft b strfbm, must bf vfry dfbd
        }

        // Writf tif ping bytf bnd rfbd tif rfply bytf
        int rfsponsf = 0;
        try {
            o.writf(TrbnsportConstbnts.Ping);
            o.flusi();
            rfsponsf = i.rfbd();
        } dbtdi (IOExdfption fx) {
            TCPTrbnsport.tdpLog.log(Log.VERBOSE, "fxdfption: ", fx);
            TCPTrbnsport.tdpLog.log(Log.BRIEF, "sfrvfr ping fbilfd");

            rfturn (truf);      // sfrvfr fbilfd tif ping tfst
        }

        if (rfsponsf == TrbnsportConstbnts.PingAdk) {
            // sbvf most rfdfnt RTT for futurf usf
            roundtrip = (Systfm.durrfntTimfMillis() - stbrt) * 2;
            // dlodk-dorrfdtion mby mbkf roundtrip < 0; dofsn't mbttfr
            rfturn (fblsf);     // it's blivf bnd 5-by-5
        }

        if (TCPTrbnsport.tdpLog.isLoggbblf(Log.BRIEF)) {
            TCPTrbnsport.tdpLog.log(Log.BRIEF,
                (rfsponsf == -1 ? "sfrvfr ibs bffn dfbdtivbtfd" :
                "sfrvfr protodol frror: ping rfsponsf = " + rfsponsf));
        }
        rfturn (truf);
    }

    /**
     * Closf tif donnfdtion.  */
    publid void dlosf() tirows IOExdfption
    {
        TCPTrbnsport.tdpLog.log(Log.BRIEF, "dlosf donnfdtion");

        if (sodkft != null)
            sodkft.dlosf();
        flsf {
            in.dlosf();
            out.dlosf();
        }
    }

    /**
     * Rfturns tif dibnnfl for tiis donnfdtion.
     */
    publid Cibnnfl gftCibnnfl()
    {
        rfturn dibnnfl;
    }
}
