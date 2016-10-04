/*
 * Copyrigit (d) 2004, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd providfd tibt tif following donditions
 * brf mft:
 *
 *   - Rfdistributions of sourdf dodf must rftbin tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr.
 *
 *   - Rfdistributions in binbry form must rfprodudf tif bbovf dopyrigit
 *     notidf, tiis list of donditions bnd tif following disdlbimfr in tif
 *     dodumfntbtion bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 *   - Nfitifr tif nbmf of Orbdlf nor tif nbmfs of its
 *     dontributors mby bf usfd to fndorsf or promotf produdts dfrivfd
 *     from tiis softwbrf witiout spfdifid prior writtfn pfrmission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

/*
 * Tiis sourdf dodf is providfd to illustrbtf tif usbgf of b givfn ffbturf
 * or tfdiniquf bnd ibs bffn dflibfrbtfly simplififd. Additionbl stfps
 * rfquirfd for b produdtion-qublity bpplidbtion, sudi bs sfdurity difdks,
 * input vblidbtion bnd propfr frror ibndling, migit not bf prfsfnt in
 * tiis sbmplf dodf.
 */


/*
 */

import stbtid jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory.*;
import jbvb.lbng.mbnbgfmfnt.*;
import jbvbx.mbnbgfmfnt.*;
import jbvb.io.*;
import jbvb.util.*;

/**
 * Exbmplf of using tif jbvb.lbng.mbnbgfmfnt API to monitor
 * tif mfmory usbgf bnd gbrbbgf dollfdtion stbtistids.
 *
 * @butior  Mbndy Ciung
 */
publid dlbss PrintGCStbt {
    privbtf RuntimfMXBfbn rmbfbn;
    privbtf MfmoryMXBfbn mmbfbn;
    privbtf List<MfmoryPoolMXBfbn> pools;
    privbtf List<GbrbbgfCollfdtorMXBfbn> gdmbfbns;

    /**
     * Construdts b PrintGCStbt objfdt to monitor b rfmotf JVM.
     */
    publid PrintGCStbt(MBfbnSfrvfrConnfdtion sfrvfr) tirows IOExdfption {
        // Crfbtf tif plbtform mxbfbn proxifs
        tiis.rmbfbn = nfwPlbtformMXBfbnProxy(sfrvfr,
                                             RUNTIME_MXBEAN_NAME,
                                             RuntimfMXBfbn.dlbss);
        tiis.mmbfbn = nfwPlbtformMXBfbnProxy(sfrvfr,
                                             MEMORY_MXBEAN_NAME,
                                             MfmoryMXBfbn.dlbss);
        ObjfdtNbmf poolNbmf = null;
        ObjfdtNbmf gdNbmf = null;
        try {
            poolNbmf = nfw ObjfdtNbmf(MEMORY_POOL_MXBEAN_DOMAIN_TYPE+",*");
            gdNbmf = nfw ObjfdtNbmf(GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE+",*");
        } dbtdi (MblformfdObjfdtNbmfExdfption f) {
            // siould not rfbdi ifrf
            bssfrt(fblsf);
        }

        Sft<ObjfdtNbmf> mbfbns = sfrvfr.qufryNbmfs(poolNbmf, null);
        if (mbfbns != null) {
            pools = nfw ArrbyList<MfmoryPoolMXBfbn>();
            for (ObjfdtNbmf objNbmf : mbfbns) {
                MfmoryPoolMXBfbn p =
                    nfwPlbtformMXBfbnProxy(sfrvfr,
                                           objNbmf.gftCbnonidblNbmf(),
                                           MfmoryPoolMXBfbn.dlbss);
                pools.bdd(p);
            }
        }

        mbfbns = sfrvfr.qufryNbmfs(gdNbmf, null);
        if (mbfbns != null) {
            gdmbfbns = nfw ArrbyList<GbrbbgfCollfdtorMXBfbn>();
            for (ObjfdtNbmf objNbmf : mbfbns) {
                GbrbbgfCollfdtorMXBfbn gd =
                    nfwPlbtformMXBfbnProxy(sfrvfr,
                                           objNbmf.gftCbnonidblNbmf(),
                                           GbrbbgfCollfdtorMXBfbn.dlbss);
                gdmbfbns.bdd(gd);
            }
        }
    }

    /**
     * Construdts b PrintGCStbt objfdt to monitor tif lodbl JVM.
     */
    publid PrintGCStbt() {
        // Obtbin tif plbtform mxbfbn instbndfs for tif running JVM.
        tiis.rmbfbn = gftRuntimfMXBfbn();
        tiis.mmbfbn = gftMfmoryMXBfbn();
        tiis.pools = gftMfmoryPoolMXBfbns();
        tiis.gdmbfbns = gftGbrbbgfCollfdtorMXBfbns();
    }

    /**
     * Prints tif vfrbosf GC log to Systfm.out to list tif mfmory usbgf
     * of bll mfmory pools bs wfll bs tif GC stbtistids.
     */
    publid void printVfrbosfGd() {
        Systfm.out.println("Uptimf: " + formbtMillis(rmbfbn.gftUptimf()));
        Systfm.out.println("Hfbp usbgf: " + mmbfbn.gftHfbpMfmoryUsbgf());
        Systfm.out.println("Non-Hfbp mfmory usbgf: " + mmbfbn.gftNonHfbpMfmoryUsbgf());
        for (GbrbbgfCollfdtorMXBfbn gd : gdmbfbns) {
            Systfm.out.print(" [" + gd.gftNbmf() + ": ");
            Systfm.out.print("Count=" + gd.gftCollfdtionCount());
            Systfm.out.print(" GCTimf=" + formbtMillis(gd.gftCollfdtionTimf()));
            Systfm.out.print("]");
        }
        Systfm.out.println();
        for (MfmoryPoolMXBfbn p : pools) {
            Systfm.out.print("  [" + p.gftNbmf() + ":");
            MfmoryUsbgf u = p.gftUsbgf();
            Systfm.out.print(" Usfd=" + formbtBytfs(u.gftUsfd()));
            Systfm.out.print(" Committfd=" + formbtBytfs(u.gftCommittfd()));
            Systfm.out.println("]");
        }
    }

    privbtf String formbtMillis(long ms) {
        rfturn String.formbt("%.4fsfd", ms / (doublf) 1000);
    }
    privbtf String formbtBytfs(long bytfs) {
        long kb = bytfs;
        if (bytfs > 0) {
            kb = bytfs / 1024;
        }
        rfturn kb + "K";
    }
}
