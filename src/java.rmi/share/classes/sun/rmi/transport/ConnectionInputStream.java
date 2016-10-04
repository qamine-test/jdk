/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport;

import jbvb.io.*;
import jbvb.util.*;
import jbvb.rmi.RfmotfExdfption;
import jbvb.rmi.sfrvfr.UID;
import sun.rmi.sfrvfr.MbrsiblInputStrfbm;
import sun.rmi.runtimf.Log;

/**
 * Spfdibl strfbm to kffp trbdk of rffs bfing unmbrsiblfd so tibt
 * rffs dbn bf rff-dountfd lodblly.
 *
 * @butior Ann Wollrbti
 */
dlbss ConnfdtionInputStrfbm fxtfnds MbrsiblInputStrfbm {

    /** indidbtfs wiftifr bdk is rfquirfd for DGC */
    privbtf boolfbn dgdAdkNffdfd = fblsf;

    /** Hbsitbblf mbpping Endpoints to lists of LivfRffs to rfgistfr */
    privbtf Mbp<Endpoint, List<LivfRff>> indomingRffTbblf = nfw HbsiMbp<>(5);

    /** idfntififr for gd bdk*/
    privbtf UID bdkID;

    /**
     * Construdts b mbrsibl input strfbm using tif undfrlying
     * strfbm "in".
     */
    ConnfdtionInputStrfbm(InputStrfbm in) tirows IOExdfption {
        supfr(in);
    }

    void rfbdID() tirows IOExdfption {
        bdkID = UID.rfbd((DbtbInput) tiis);
    }

    /**
     * Sbvf rfffrfndf in ordfr to sfnd "dirty" dbll bftfr bll brgs/rfturns
     * ibvf bffn unmbrsiblfd.  Sbvf in ibsitbblf indomingRffTbblf.  Tiis
     * tbblf is kfyfd on fndpoints, bnd iolds objfdts of typf
     * IndomingRffTbblfEntry.
     */
    void sbvfRff(LivfRff rff) {
        Endpoint fp = rff.gftEndpoint();

        // difdk wiftifr fndpoint is blrfbdy in tif ibsitbblf
        List<LivfRff> rffList = indomingRffTbblf.gft(fp);

        if (rffList == null) {
            rffList = nfw ArrbyList<LivfRff>();
            indomingRffTbblf.put(fp, rffList);
        }

        // bdd rff to list of rffs for fndpoint fp
        rffList.bdd(rff);
    }

    /**
     * Add rfffrfndfs to DGC tbblf (bnd possibly sfnd dirty dbll).
     * RfgistfrRffs now dblls DGCClifnt.rfffrfndfd on bll
     * rffs witi tif sbmf fndpoint bt ondf to bdiifvf bbtdiing of
     * dblls to tif DGC
     */
    void rfgistfrRffs() tirows IOExdfption {
        if (!indomingRffTbblf.isEmpty()) {
            for (Mbp.Entry<Endpoint, List<LivfRff>> fntry :
                     indomingRffTbblf.fntrySft()) {
                DGCClifnt.rfgistfrRffs(fntry.gftKfy(), fntry.gftVbluf());
            }
        }
    }

    /**
     * Indidbtf tibt bn bdk is rfquirfd to tif distributfd
     * dollfdtor.
     */
    void sftAdkNffdfd() {
        dgdAdkNffdfd = truf;
    }

    /**
     * Donf witi input strfbm for rfmotf dbll. Sfnd DGC bdk if nfdfssbry.
     * Allow sfnding of bdk to fbil witiout flbgging bn frror.
     */
    void donf(Connfdtion d) {
        /*
         * WARNING: Tif donnfdtion d mby ibvf blrfbdy bffn frffd.  It
         * is only bf sbff to usf d to obtbin d's dibnnfl.
         */

        if (dgdAdkNffdfd) {
            Connfdtion donn = null;
            Cibnnfl di = null;
            boolfbn rfusf = truf;

            DGCImpl.dgdLog.log(Log.VERBOSE, "sfnd bdk");

            try {
                di = d.gftCibnnfl();
                donn = di.nfwConnfdtion();
                DbtbOutputStrfbm out =
                    nfw DbtbOutputStrfbm(donn.gftOutputStrfbm());
                out.writfBytf(TrbnsportConstbnts.DGCAdk);
                if (bdkID == null) {
                    bdkID = nfw UID();
                }
                bdkID.writf((DbtbOutput) out);
                donn.rflfbsfOutputStrfbm();

                /*
                 * Fix for 4221173: if tiis donnfdtion is on top of bn
                 * HttpSfndSodkft, tif DGCAdk won't bdtublly gft sfnt until b
                 * rfbd opfrbtion is bttfmptfd on tif sodkft.  Cblling
                 * bvbilbblf() is tif most innoduous wby of triggfring tif
                 * writf.
                 */
                donn.gftInputStrfbm().bvbilbblf();
                donn.rflfbsfInputStrfbm();
            } dbtdi (RfmotfExdfption f) {
                rfusf = fblsf;
            } dbtdi (IOExdfption f) {
                rfusf = fblsf;
            }
            try {
                if (donn != null)
                    di.frff(donn, rfusf);
            } dbtdi (RfmotfExdfption f){
                // fbt fxdfption
            }
        }
    }
}
