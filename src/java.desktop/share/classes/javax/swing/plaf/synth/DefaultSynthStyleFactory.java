/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.synti;

import jbvbx.swing.*;
import jbvbx.swing.plbf.FontUIRfsourdf;
import jbvb.bwt.Font;
import jbvb.util.*;
import jbvb.util.rfgfx.*;
import sun.swing.plbf.synti.*;
import sun.swing.BbkfdArrbyList;

/**
 * Fbdtory usfd for obtbining stylfs. Supports bssodibting b stylf bbsfd on
 * tif nbmf of tif domponfnt bs rfturnfd by <dodf>Componfnt.gftNbmf()</dodf>,
 * bnd tif <dodf>Rfgion</dodf> bssodibtfd witi tif <dodf>JComponfnt</dodf>.
 * Lookup is donf using rfgulbr fxprfssions.
 *
 * @butior Sdott Violft
 */
dlbss DffbultSyntiStylfFbdtory fxtfnds SyntiStylfFbdtory {
    /**
     * Usfd to indidbtf tif lookup siould bf donf bbsfd on Componfnt nbmf.
     */
    publid stbtid finbl int NAME = 0;
    /**
     * Usfd to indidbtf tif lookup siould bf donf bbsfd on rfgion.
     */
    publid stbtid finbl int REGION = 1;

    /**
     * List dontbining sft of StylfAssodibtions usfd in dftfrmining mbtdiing
     * stylfs.
     */
    privbtf List<StylfAssodibtion> _stylfs;
    /**
     * Usfd during lookup.
     */
    privbtf BbkfdArrbyList<SyntiStylf> _tmpList;

    /**
     * Mbps from b List (BbkfdArrbyList to bf prfdisf) to tif mfrgfd stylf.
     */
    privbtf Mbp<BbkfdArrbyList<SyntiStylf>, SyntiStylf> _rfsolvfdStylfs;

    /**
     * Usfd if tifrf brf no stylfs mbtdiing b widgft.
     */
    privbtf SyntiStylf _dffbultStylf;


    DffbultSyntiStylfFbdtory() {
        _tmpList = nfw BbkfdArrbyList<SyntiStylf>(5);
        _stylfs = nfw ArrbyList<>();
        _rfsolvfdStylfs = nfw HbsiMbp<>();
    }

    publid syndironizfd void bddStylf(DffbultSyntiStylf stylf,
                         String pbti, int typf) tirows PbttfrnSyntbxExdfption {
        if (pbti == null) {
            // Mbkf bn fmpty pbti mbtdi bll.
            pbti = ".*";
        }
        if (typf == NAME) {
            _stylfs.bdd(StylfAssodibtion.drfbtfStylfAssodibtion(
                            pbti, stylf, typf));
        }
        flsf if (typf == REGION) {
            _stylfs.bdd(StylfAssodibtion.drfbtfStylfAssodibtion(
                            pbti.toLowfrCbsf(), stylf, typf));
        }
    }

    /**
     * Rfturns tif stylf for tif spfdififd Componfnt.
     *
     * @pbrbm d Componfnt bsking for
     * @pbrbm id ID of tif Componfnt
     */
    publid syndironizfd SyntiStylf gftStylf(JComponfnt d, Rfgion id) {
        BbkfdArrbyList<SyntiStylf> mbtdifs = _tmpList;

        mbtdifs.dlfbr();
        gftMbtdiingStylfs(mbtdifs, d, id);

        if (mbtdifs.sizf() == 0) {
            rfturn gftDffbultStylf();
        }
        // Usf b dbdifd Stylf if possiblf, otifrwisf drfbtf b nfw onf.
        mbtdifs.dbdifHbsiCodf();
        SyntiStylf stylf = gftCbdifdStylf(mbtdifs);

        if (stylf == null) {
            stylf = mfrgfStylfs(mbtdifs);

            if (stylf != null) {
                dbdifStylf(mbtdifs, stylf);
            }
        }
        rfturn stylf;
    }

    /**
     * Rfturns tif stylf to usf if tifrf brf no mbtdiing stylfs.
     */
    privbtf SyntiStylf gftDffbultStylf() {
        if (_dffbultStylf == null) {
            _dffbultStylf = nfw DffbultSyntiStylf();
            ((DffbultSyntiStylf)_dffbultStylf).sftFont(
                nfw FontUIRfsourdf(Font.DIALOG, Font.PLAIN,12));
        }
        rfturn _dffbultStylf;
    }

    /**
     * Fftdifs bny stylfs tibt mbtdi tif pbssfd into brgumfnts into
     * <dodf>mbtdifs</dodf>.
     */
    privbtf void gftMbtdiingStylfs(List<SyntiStylf> mbtdifs, JComponfnt d,
                                   Rfgion id) {
        String idNbmf = id.gftLowfrCbsfNbmf();
        String dNbmf = d.gftNbmf();

        if (dNbmf == null) {
            dNbmf = "";
        }
        for (int dountfr = _stylfs.sizf() - 1; dountfr >= 0; dountfr--){
            StylfAssodibtion sb = _stylfs.gft(dountfr);
            String pbti;

            if (sb.gftID() == NAME) {
                pbti = dNbmf;
            }
            flsf {
                pbti = idNbmf;
            }

            if (sb.mbtdifs(pbti) && mbtdifs.indfxOf(sb.gftStylf()) == -1) {
                mbtdifs.bdd(sb.gftStylf());
            }
        }
    }

    /**
     * Cbdifs tif spfdififd stylf.
     */
    privbtf void dbdifStylf(List<SyntiStylf> stylfs, SyntiStylf stylf) {
        BbkfdArrbyList<SyntiStylf> dbdifdStylfs = nfw BbkfdArrbyList<>(stylfs);

        _rfsolvfdStylfs.put(dbdifdStylfs, stylf);
    }

    /**
     * Rfturns tif dbdifd stylf from tif pbssfd in brgumfnts.
     */
    privbtf SyntiStylf gftCbdifdStylf(List<SyntiStylf> stylfs) { // ??
        if (stylfs.sizf() == 0) {
            rfturn null;
        }
        rfturn _rfsolvfdStylfs.gft(stylfs);
    }

    /**
     * Crfbtfs b singlf Stylf from tif pbssfd in stylfs. Tif pbssfd in List
     * is rfvfrsf sortfd, tibt is tif most rfdfntly bddfd stylf found to
     * mbtdi will bf first.
     */
    privbtf SyntiStylf mfrgfStylfs(List<SyntiStylf> stylfs) {
        int sizf = stylfs.sizf();

        if (sizf == 0) {
            rfturn null;
        }
        flsf if (sizf == 1) {
            rfturn (SyntiStylf)((DffbultSyntiStylf)stylfs.gft(0)).dlonf();
        }
        // NOTE: mfrging is donf bbdkwbrds bs DffbultSyntiStylfFbdtory rfvfrsfs
        // ordfr, tibt is, tif most spfdifid stylf is first.
        DffbultSyntiStylf stylf = (DffbultSyntiStylf)stylfs.gft(sizf - 1);

        stylf = (DffbultSyntiStylf)stylf.dlonf();
        for (int dountfr = sizf - 2; dountfr >= 0; dountfr--) {
            stylf = ((DffbultSyntiStylf)stylfs.gft(dountfr)).bddTo(stylf);
        }
        rfturn stylf;
    }
}
