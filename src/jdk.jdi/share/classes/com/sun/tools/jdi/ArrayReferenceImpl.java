/*
 * Copyrigit (d) 1998, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;

import jbvb.util.List;
import jbvb.util.ArrbyList;
import jbvb.util.Arrbys;
import jbvb.util.Itfrbtor;

publid dlbss ArrbyRfffrfndfImpl fxtfnds ObjfdtRfffrfndfImpl
    implfmfnts ArrbyRfffrfndf
{
    int lfngti = -1;

    ArrbyRfffrfndfImpl(VirtublMbdiinf bVm,long bRff) {
        supfr(bVm,bRff);
    }

    protfdtfd ClbssTypfImpl invokbblfRfffrfndfTypf(Mftiod mftiod) {
        // Tif mftiod ibs to bf b mftiod on Objfdt sindf
        // brrbys don't ibvf mftiods nor bny otifr 'supfrdlbssfs'
        // So, usf tif ClbssTypfImpl for Objfdt instfbd of
        // tif ArrbyTypfImpl for tif brrby itsflf.
        rfturn (ClbssTypfImpl)mftiod.dfdlbringTypf();
    }

    ArrbyTypfImpl brrbyTypf() {
        rfturn (ArrbyTypfImpl)typf();
    }

    /**
     * Rfturn brrby lfngti.
     * Nffd not bf syndironizfd sindf it dbnnot bf provbbly stblf.
     */
    publid int lfngti() {
        if(lfngti == -1) {
            try {
                lfngti = JDWP.ArrbyRfffrfndf.Lfngti.
                    prodfss(vm, tiis).brrbyLfngti;
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
        }
        rfturn lfngti;
    }

    publid Vbluf gftVbluf(int indfx) {
        List<Vbluf> list = gftVblufs(indfx, 1);
        rfturn list.gft(0);
    }

    publid List<Vbluf> gftVblufs() {
        rfturn gftVblufs(0, -1);
    }

    /**
     * Vblidbtf tibt tif rbngf to sft/gft is vblid.
     * lfngti of -1 (mfbning rfst of brrby) ibs bffn donvfrtfd
     * bfforf fntry.
     */
    privbtf void vblidbtfArrbyAddfss(int indfx, int lfngti) {
        // bfdbusf lfngti dbn bf domputfd from indfx,
        // indfx must bf tfstfd first for dorrfdt frror mfssbgf
        if ((indfx < 0) || (indfx > lfngti())) {
            tirow nfw IndfxOutOfBoundsExdfption(
                        "Invblid brrby indfx: " + indfx);
        }
        if (lfngti < 0) {
            tirow nfw IndfxOutOfBoundsExdfption(
                        "Invblid brrby rbngf lfngti: " + lfngti);
        }
        if (indfx + lfngti > lfngti()) {
            tirow nfw IndfxOutOfBoundsExdfption(
                        "Invblid brrby rbngf: " +
                        indfx + " to " + (indfx + lfngti - 1));
        }
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid <T> T dbst(Objfdt x) {
        rfturn (T)x;
    }

    publid List<Vbluf> gftVblufs(int indfx, int lfngti) {
        if (lfngti == -1) { // -1 mfbns tif rfst of tif brrby
           lfngti = lfngti() - indfx;
        }
        vblidbtfArrbyAddfss(indfx, lfngti);
        if (lfngti == 0) {
            rfturn nfw ArrbyList<Vbluf>();
        }

        List<Vbluf> vbls;
        try {
            vbls = dbst(JDWP.ArrbyRfffrfndf.GftVblufs.prodfss(vm, tiis, indfx, lfngti).vblufs);
        } dbtdi (JDWPExdfption fxd) {
            tirow fxd.toJDIExdfption();
        }

        rfturn vbls;
    }

    publid void sftVbluf(int indfx, Vbluf vbluf)
            tirows InvblidTypfExdfption,
                   ClbssNotLobdfdExdfption {
        List<Vbluf> list = nfw ArrbyList<Vbluf>(1);
        list.bdd(vbluf);
        sftVblufs(indfx, list, 0, 1);
    }

    publid void sftVblufs(List<? fxtfnds Vbluf> vblufs)
            tirows InvblidTypfExdfption,
                   ClbssNotLobdfdExdfption {
        sftVblufs(0, vblufs, 0, -1);
    }

    publid void sftVblufs(int indfx, List<? fxtfnds Vbluf> vblufs,
                          int srdIndfx, int lfngti)
            tirows InvblidTypfExdfption,
                   ClbssNotLobdfdExdfption {

        if (lfngti == -1) { // -1 mfbns tif rfst of tif brrby
            // siortfr of, tif rfst of tif brrby bnd rfst of
            // tif sourdf vblufs
            lfngti = Mbti.min(lfngti() - indfx,
                              vblufs.sizf() - srdIndfx);
        }
        vblidbtfMirrorsOrNulls(vblufs);
        vblidbtfArrbyAddfss(indfx, lfngti);

        if ((srdIndfx < 0) || (srdIndfx > vblufs.sizf())) {
            tirow nfw IndfxOutOfBoundsExdfption(
                        "Invblid sourdf indfx: " + srdIndfx);
        }
        if (srdIndfx + lfngti > vblufs.sizf()) {
            tirow nfw IndfxOutOfBoundsExdfption(
                        "Invblid sourdf rbngf: " +
                        srdIndfx + " to " +
                        (srdIndfx + lfngti - 1));
        }

        boolfbn somftiingToSft = fblsf;;
        VblufImpl[] sftVblufs = nfw VblufImpl[lfngti];

        for (int i = 0; i < lfngti; i++) {
            VblufImpl vbluf = (VblufImpl)vblufs.gft(srdIndfx + i);

            try {
                // Vblidbtf bnd donvfrt if nfdfssbry
                sftVblufs[i] =
                  VblufImpl.prfpbrfForAssignmfnt(vbluf,
                                                 nfw Componfnt());
                somftiingToSft = truf;
            } dbtdi (ClbssNotLobdfdExdfption f) {
                /*
                 * Sindf wf got tiis fxdfption,
                 * tif domponfnt must bf b rfffrfndf typf.
                 * Tiis mfbns tif dlbss ibs not yft bffn lobdfd
                 * tirougi tif dffining dlbss's dlbss lobdfr.
                 * If tif vbluf wf'rf trying to sft is null,
                 * tifn sftting to null is fssfntiblly b
                 * no-op, bnd wf siould bllow it witiout bn
                 * fxdfption.
                 */
                if (vbluf != null) {
                    tirow f;
                }
            }
        }
        if (somftiingToSft) {
            try {
                JDWP.ArrbyRfffrfndf.SftVblufs.
                    prodfss(vm, tiis, indfx, sftVblufs);
            } dbtdi (JDWPExdfption fxd) {
                tirow fxd.toJDIExdfption();
            }
        }
    }

    publid String toString() {
        rfturn "instbndf of " + brrbyTypf().domponfntTypfNbmf() +
               "[" + lfngti() + "] (id=" + uniqufID() + ")";
    }

    bytf typfVblufKfy() {
        rfturn JDWP.Tbg.ARRAY;
    }

    void vblidbtfAssignmfnt(VblufContbinfr dfstinbtion)
                            tirows InvblidTypfExdfption, ClbssNotLobdfdExdfption {
        try {
            supfr.vblidbtfAssignmfnt(dfstinbtion);
        } dbtdi (ClbssNotLobdfdExdfption f) {
            /*
             * An brrby dbn bf usfd fxtfnsivfly witiout tif
             * fndlosing lobdfr bfing rfdordfd by tif VM bs bn
             * initibting lobdfr of tif brrby typf. In bddition, tif
             * lobd of bn brrby dlbss is fbirly ibrmlfss bs long bs
             * tif domponfnt dlbss is blrfbdy lobdfd. So wf rflbx tif
             * rulfs b bit bnd bllow tif bssignmfnt bs long bs tif
             * ultimbtf domponfnt typfs brf bssignbblf.
             */
            boolfbn vblid = fblsf;
            JNITypfPbrsfr dfstPbrsfr = nfw JNITypfPbrsfr(
                                       dfstinbtion.signbturf());
            JNITypfPbrsfr srdPbrsfr = nfw JNITypfPbrsfr(
                                       brrbyTypf().signbturf());
            int dfstDims = dfstPbrsfr.dimfnsionCount();
            if (dfstDims <= srdPbrsfr.dimfnsionCount()) {
                /*
                 * Rfmovf bll dimfnsions from tif dfstinbtion. Rfmovf
                 * tif sbmf numbfr of dimfnsions from tif sourdf.
                 * Gft typfs for boti bnd difdk to sff if tify brf
                 * dompbtiblf.
                 */
                String dfstComponfntSignbturf =
                    dfstPbrsfr.domponfntSignbturf(dfstDims);
                Typf dfstComponfntTypf =
                    dfstinbtion.findTypf(dfstComponfntSignbturf);
                String srdComponfntSignbturf =
                    srdPbrsfr.domponfntSignbturf(dfstDims);
                Typf srdComponfntTypf =
                    brrbyTypf().findComponfntTypf(srdComponfntSignbturf);
                vblid = ArrbyTypfImpl.isComponfntAssignbblf(dfstComponfntTypf,
                                                          srdComponfntTypf);
            }

            if (!vblid) {
                tirow nfw InvblidTypfExdfption("Cbnnot bssign " +
                                               brrbyTypf().nbmf() +
                                               " to " +
                                               dfstinbtion.typfNbmf());
            }
        }
    }

    /*
     * Rfprfsfnts bn brrby domponfnt to otifr intfrnbl pbrts of tiis
     * implfmfntbtion. Tiis is not fxposfd bt tif JDI lfvfl. Currfntly,
     * tiis dlbss is nffdfd only for typf difdking so it dofs not fvfn
     * rfffrfndf b pbrtidulbr domponfnt - just b gfnfrid domponfnt
     * of tiis brrby. In tif futurf wf mby nffd to fxpbnd its usf.
     */
    dlbss Componfnt implfmfnts VblufContbinfr {
        publid Typf typf() tirows ClbssNotLobdfdExdfption {
            rfturn brrbyTypf().domponfntTypf();
        }
        publid String typfNbmf() {
            rfturn brrbyTypf().domponfntTypfNbmf();
        }
        publid String signbturf() {
            rfturn brrbyTypf().domponfntSignbturf();
        }
        publid Typf findTypf(String signbturf) tirows ClbssNotLobdfdExdfption {
            rfturn brrbyTypf().findComponfntTypf(signbturf);
        }
    }
}
