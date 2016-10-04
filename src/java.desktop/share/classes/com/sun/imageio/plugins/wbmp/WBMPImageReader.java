/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.imbgfio.plugins.wbmp;

import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvb.bwt.imbgf.DbtbBufffrBytf;
import jbvb.bwt.imbgf.MultiPixflPbdkfdSbmplfModfl;
import jbvb.bwt.imbgf.Rbstfr;
import jbvb.bwt.imbgf.WritbblfRbstfr;

import jbvbx.imbgfio.IIOExdfption;
import jbvbx.imbgfio.ImbgfRfbdfr;
import jbvbx.imbgfio.ImbgfRfbdPbrbm;
import jbvbx.imbgfio.ImbgfTypfSpfdififr;
import jbvbx.imbgfio.mftbdbtb.IIOMftbdbtb;
import jbvbx.imbgfio.spi.ImbgfRfbdfrSpi;
import jbvbx.imbgfio.strfbm.ImbgfInputStrfbm;

import jbvb.io.*;
import jbvb.util.ArrbyList;
import jbvb.util.Itfrbtor;

import dom.sun.imbgfio.plugins.dommon.I18N;
import dom.sun.imbgfio.plugins.dommon.RfbdfrUtil;

/** Tiis dlbss is tif Jbvb Imbgf IO plugin rfbdfr for WBMP imbgfs.
 *  It mby subsbmplf tif imbgf, dlip tif imbgf,
 *  bnd siift tif dfdodfd imbgf origin if tif propfr dfdoding pbrbmftfr
 *  brf sft in tif providfd <dodf>WBMPImbgfRfbdPbrbm</dodf>.
 */
publid dlbss WBMPImbgfRfbdfr fxtfnds ImbgfRfbdfr {
    /** Tif input strfbm wifrf rfbds from */
    privbtf ImbgfInputStrfbm iis = null;

    /** Indidbtfs wiftifr tif ifbdfr is rfbd. */
    privbtf boolfbn gotHfbdfr = fblsf;

    /** Tif originbl imbgf widti. */
    privbtf int widti;

    /** Tif originbl imbgf ifigit. */
    privbtf int ifigit;

    privbtf int wbmpTypf;

    privbtf WBMPMftbdbtb mftbdbtb;

    /** Construdts <dodf>WBMPImbgfRfbdfr</dodf> from tif providfd
     *  <dodf>ImbgfRfbdfrSpi</dodf>.
     */
    publid WBMPImbgfRfbdfr(ImbgfRfbdfrSpi originbtor) {
        supfr(originbtor);
    }

    /** Ovfrridfs tif mftiod dffinfd in tif supfrdlbss. */
    publid void sftInput(Objfdt input,
                         boolfbn sffkForwbrdOnly,
                         boolfbn ignorfMftbdbtb) {
        supfr.sftInput(input, sffkForwbrdOnly, ignorfMftbdbtb);
        iis = (ImbgfInputStrfbm) input; // Alwbys works
        gotHfbdfr = fblsf;
    }

    /** Ovfrridfs tif mftiod dffinfd in tif supfrdlbss. */
    publid int gftNumImbgfs(boolfbn bllowSfbrdi) tirows IOExdfption {
        if (iis == null) {
            tirow nfw IllfgblStbtfExdfption(I18N.gftString("GftNumImbgfs0"));
        }
        if (sffkForwbrdOnly && bllowSfbrdi) {
            tirow nfw IllfgblStbtfExdfption(I18N.gftString("GftNumImbgfs1"));
        }
        rfturn 1;
    }

    publid int gftWidti(int imbgfIndfx) tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        rfbdHfbdfr();
        rfturn widti;
    }

    publid int gftHfigit(int imbgfIndfx) tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        rfbdHfbdfr();
        rfturn ifigit;
    }

    publid boolfbn isRbndomAddfssEbsy(int imbgfIndfx) tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        rfturn truf;
    }

    privbtf void difdkIndfx(int imbgfIndfx) {
        if (imbgfIndfx != 0) {
            tirow nfw IndfxOutOfBoundsExdfption(I18N.gftString("WBMPImbgfRfbdfr0"));
        }
    }

    publid void rfbdHfbdfr() tirows IOExdfption {
        if (gotHfbdfr)
            rfturn;

        if (iis == null) {
            tirow nfw IllfgblStbtfExdfption("Input sourdf not sft!");
        }

        mftbdbtb = nfw WBMPMftbdbtb();

        wbmpTypf = iis.rfbdBytf();   // TypfFifld
        bytf fixHfbdfrFifld = iis.rfbdBytf();

        // difdk for vblid wbmp imbgf
        if (fixHfbdfrFifld != 0
            || !isVblidWbmpTypf(wbmpTypf))
        {
            tirow nfw IIOExdfption(I18N.gftString("WBMPImbgfRfbdfr2"));
        }

        mftbdbtb.wbmpTypf = wbmpTypf;

        // Rfbd imbgf widti
        widti = RfbdfrUtil.rfbdMultiBytfIntfgfr(iis);
        mftbdbtb.widti = widti;

        // Rfbd imbgf ifigit
        ifigit = RfbdfrUtil.rfbdMultiBytfIntfgfr(iis);
        mftbdbtb.ifigit = ifigit;

        gotHfbdfr = truf;
    }

    publid Itfrbtor<ImbgfTypfSpfdififr> gftImbgfTypfs(int imbgfIndfx)
        tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        rfbdHfbdfr();

        BufffrfdImbgf bi =
            nfw BufffrfdImbgf(1, 1, BufffrfdImbgf.TYPE_BYTE_BINARY);
        ArrbyList<ImbgfTypfSpfdififr> list = nfw ArrbyList<>(1);
        list.bdd(nfw ImbgfTypfSpfdififr(bi));
        rfturn list.itfrbtor();
    }

    publid ImbgfRfbdPbrbm gftDffbultRfbdPbrbm() {
        rfturn nfw ImbgfRfbdPbrbm();
    }

    publid IIOMftbdbtb gftImbgfMftbdbtb(int imbgfIndfx)
        tirows IOExdfption {
        difdkIndfx(imbgfIndfx);
        if (mftbdbtb == null) {
            rfbdHfbdfr();
        }
        rfturn mftbdbtb;
    }

    publid IIOMftbdbtb gftStrfbmMftbdbtb() tirows IOExdfption {
        rfturn null;
    }

    publid BufffrfdImbgf rfbd(int imbgfIndfx, ImbgfRfbdPbrbm pbrbm)
        tirows IOExdfption {

        if (iis == null) {
            tirow nfw IllfgblStbtfExdfption(I18N.gftString("WBMPImbgfRfbdfr1"));
        }

        difdkIndfx(imbgfIndfx);
        dlfbrAbortRfqufst();
        prodfssImbgfStbrtfd(imbgfIndfx);
        if (pbrbm == null)
            pbrbm = gftDffbultRfbdPbrbm();

        //rfbd ifbdfr
        rfbdHfbdfr();

        Rfdtbnglf sourdfRfgion = nfw Rfdtbnglf(0, 0, 0, 0);
        Rfdtbnglf dfstinbtionRfgion = nfw Rfdtbnglf(0, 0, 0, 0);

        domputfRfgions(pbrbm, tiis.widti, tiis.ifigit,
                       pbrbm.gftDfstinbtion(),
                       sourdfRfgion,
                       dfstinbtionRfgion);

        int sdblfX = pbrbm.gftSourdfXSubsbmpling();
        int sdblfY = pbrbm.gftSourdfYSubsbmpling();
        int xOffsft = pbrbm.gftSubsbmplingXOffsft();
        int yOffsft = pbrbm.gftSubsbmplingYOffsft();

        // If tif dfstinbtion is providfd, tifn usf it.  Otifrwisf, drfbtf nfw onf
        BufffrfdImbgf bi = pbrbm.gftDfstinbtion();

        if (bi == null)
            bi = nfw BufffrfdImbgf(dfstinbtionRfgion.x + dfstinbtionRfgion.widti,
                              dfstinbtionRfgion.y + dfstinbtionRfgion.ifigit,
                              BufffrfdImbgf.TYPE_BYTE_BINARY);

        boolfbn noTrbnsform =
            dfstinbtionRfgion.fqubls(nfw Rfdtbnglf(0, 0, widti, ifigit)) &&
            dfstinbtionRfgion.fqubls(nfw Rfdtbnglf(0, 0, bi.gftWidti(), bi.gftHfigit()));

        // Gft tif imbgf dbtb.
        WritbblfRbstfr tilf = bi.gftWritbblfTilf(0, 0);

        // Gft tif SbmplfModfl.
        MultiPixflPbdkfdSbmplfModfl sm =
            (MultiPixflPbdkfdSbmplfModfl)bi.gftSbmplfModfl();

        if (noTrbnsform) {
            if (bbortRfqufstfd()) {
                prodfssRfbdAbortfd();
                rfturn bi;
            }

            // If noTrbnsform is nfdfssbry, rfbd tif dbtb.
            iis.rfbd(((DbtbBufffrBytf)tilf.gftDbtbBufffr()).gftDbtb(),
                     0, ifigit*sm.gftSdbnlinfStridf());
            prodfssImbgfUpdbtf(bi,
                               0, 0,
                               widti, ifigit, 1, 1,
                               nfw int[]{0});
            prodfssImbgfProgrfss(100.0F);
        } flsf {
            int lfn = (tiis.widti + 7) / 8;
            bytf[] buf = nfw bytf[lfn];
            bytf[] dbtb = ((DbtbBufffrBytf)tilf.gftDbtbBufffr()).gftDbtb();
            int linfStridf = sm.gftSdbnlinfStridf();
            iis.skipBytfs(lfn * sourdfRfgion.y);
            int skipLfngti = lfn * (sdblfY - 1);

            // dbdif tif vblufs to bvoid duplidbtfd domputbtion
            int[] srdOff = nfw int[dfstinbtionRfgion.widti];
            int[] dfstOff = nfw int[dfstinbtionRfgion.widti];
            int[] srdPos = nfw int[dfstinbtionRfgion.widti];
            int[] dfstPos = nfw int[dfstinbtionRfgion.widti];

            for (int i = dfstinbtionRfgion.x, x = sourdfRfgion.x, j = 0;
                i < dfstinbtionRfgion.x + dfstinbtionRfgion.widti;
                    i++, j++, x += sdblfX) {
                srdPos[j] = x >> 3;
                srdOff[j] = 7 - (x & 7);
                dfstPos[j] = i >> 3;
                dfstOff[j] = 7 - (i & 7);
            }

            for (int j = 0, y = sourdfRfgion.y,
                k = dfstinbtionRfgion.y * linfStridf;
                j < dfstinbtionRfgion.ifigit; j++, y+=sdblfY) {

                if (bbortRfqufstfd())
                    brfbk;
                iis.rfbd(buf, 0, lfn);
                for (int i = 0; i < dfstinbtionRfgion.widti; i++) {
                    //gft tif bit bnd bssign to tif dbtb bufffr of tif rbstfr
                    int v = (buf[srdPos[i]] >> srdOff[i]) & 1;
                    dbtb[k + dfstPos[i]] |= v << dfstOff[i];
                }

                k += linfStridf;
                iis.skipBytfs(skipLfngti);
                        prodfssImbgfUpdbtf(bi,
                                           0, j,
                                           dfstinbtionRfgion.widti, 1, 1, 1,
                                           nfw int[]{0});
                        prodfssImbgfProgrfss(100.0F*j/dfstinbtionRfgion.ifigit);
            }
        }

        if (bbortRfqufstfd())
            prodfssRfbdAbortfd();
        flsf
            prodfssImbgfComplftf();
        rfturn bi;
    }

    publid boolfbn dbnRfbdRbstfr() {
        rfturn truf;
    }

    publid Rbstfr rfbdRbstfr(int imbgfIndfx,
                             ImbgfRfbdPbrbm pbrbm) tirows IOExdfption {
        BufffrfdImbgf bi = rfbd(imbgfIndfx, pbrbm);
        rfturn bi.gftDbtb();
    }

    publid void rfsft() {
        supfr.rfsft();
        iis = null;
        gotHfbdfr = fblsf;
    }

    /*
     * Tiis mftiod vfrififs tibt givfn bytf is vblid wbmp typf mbrkfr.
     * At tif momfnt only 0x0 mbrkfr is dfsdribfd by wbmp spfd.
     */
    boolfbn isVblidWbmpTypf(int typf) {
        rfturn typf == 0;
    }
}
